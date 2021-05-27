package by.shimakser.view;

import by.shimakser.model.*;
import by.shimakser.service.CarService;
import by.shimakser.service.ClientService;
import by.shimakser.service.RentService;
import by.shimakser.service.TariffService;
import org.hibernate.service.spi.ServiceException;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class RentView {

    private RentService rentService = new RentService();
    private CarService carService = new CarService();
    private ClientService clientService = new ClientService();
    private TariffService tariffService = new TariffService();

    private Scanner firstScan = new Scanner(System.in);
    private Scanner secondScan = new Scanner(System.in);

    private static void actions() {
        System.out.println("\nChoose rents action:\n"
                + "1-Show all row.\n"
                + "2-Insert new row.\n"
                + "3-Delete row.\n"
                + "4-Update row.\n"
                + "5-Search by id.\n"
                + "\t0-Exit.");
    }

    public void runRentView() throws SQLException {
        actions();
        Scanner mainScan = new Scanner(System.in);
        try {
            int numOfPoint = mainScan.nextInt();
            while (numOfPoint != 0) {
                switch (numOfPoint) {
                    case 1:
                        printAllRents();
                        actions();
                        numOfPoint = mainScan.nextInt();
                        break;
                    case 2:
                        addRent();

                        actions();
                        numOfPoint = mainScan.nextInt();
                        break;
                    case 3:
                        deleteRent();

                        actions();
                        numOfPoint = mainScan.nextInt();
                        break;
                    case 4:
                        updateRent();

                        actions();
                        numOfPoint = mainScan.nextInt();
                        break;
                    case 5:
                        getByIdRent();

                        actions();
                        numOfPoint = mainScan.nextInt();
                        break;
                    case 0:
                        numOfPoint = 0;
                        break;
                    default:
                        System.out.println("There is no such command.");
                        actions();
                        numOfPoint = mainScan.nextInt();
                }
            }
        } catch (InputMismatchException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addRent() throws SQLException {
        try {
            Rent rent = new Rent();

            System.out.println("Enter rent beginning date: ");
            String rentBeginningDate = firstScan.nextLine();
            rent.setBeginningDate(rentBeginningDate);

            System.out.println("Enter rent finishing date:");
            String rentFinishingDate = secondScan.nextLine();
            rent.setFinishingDate(rentFinishingDate);

            System.out.println("Enter rent number of cars: ");
            String rentNumberOfCars = firstScan.nextLine();
            rent.setNumberOfCars(rentNumberOfCars);

            Client testClient = clientService.getClientById(1L);
            if (testClient != null) {
                System.out.println("Enter client_id:");
                String rentClientId = secondScan.nextLine();
                Client client = clientService.getClientById((long) Integer.parseInt(rentClientId));
                rent.setClient(client);
            } else rent.setClient(null);

            Car testCar = carService.getCarById(1L);
            if (testCar != null) {
                System.out.println("Enter car_id: ");
                String rentCarId = firstScan.nextLine();
                Car car = carService.getCarById((long) Integer.parseInt(rentCarId));
                rent.setCar(car);
            } else rent.setCar(null);

            Tariff testTariff = tariffService.getTariffById(1L);
            if (testTariff != null) {
                System.out.println("Enter tariff_id:");
                String rentTariffId = secondScan.nextLine();
                Tariff tariff = tariffService.getTariffById((long) Integer.parseInt(rentTariffId));
                rent.setTariff(tariff);
            } else rent.setTariff(null);

            rentService.saveRent(rent);
            System.out.println("Rent has been created successfully");
        } catch (InputMismatchException e) {
            System.out.print(e.getMessage());
        }
    }

    public void updateRent() throws SQLException {
        try {
            System.out.println("Enter id in order to find element :");
            Long id = Long.parseLong(secondScan.next());

            Rent newRent = rentService.getRentById(id);

            System.out.println("Enter rent beginning date: ");
            String rentBeginningDate = firstScan.nextLine();
            newRent.setBeginningDate(rentBeginningDate);

            System.out.println("Enter rent finishing date:");
            String rentFinishingDate = secondScan.nextLine();
            newRent.setFinishingDate(rentFinishingDate);

            System.out.println("Enter rent number of cars: ");
            String rentNumberOfCars = firstScan.nextLine();
            newRent.setNumberOfCars(rentNumberOfCars);

            Client testClient = clientService.getClientById(1L);
            if (testClient != null) {
                System.out.println("Enter client_id:");
                String rentClientId = secondScan.nextLine();
                Client client = clientService.getClientById((long) Integer.parseInt(rentClientId));
                newRent.setClient(client);
            } else newRent.setClient(null);

            Car testCar = carService.getCarById(1L);
            if (testCar != null) {
                System.out.println("Enter car_id: ");
                String rentCarId = firstScan.nextLine();
                Car car = carService.getCarById((long) Integer.parseInt(rentCarId));
                newRent.setCar(car);
            } else newRent.setCar(null);

            Tariff testTariff = tariffService.getTariffById(1L);
            if (testTariff != null) {
                System.out.println("Enter tariff_id:");
                String rentTariffId = secondScan.nextLine();
                Tariff tariff = tariffService.getTariffById((long) Integer.parseInt(rentTariffId));
                newRent.setTariff(tariff);
            } else newRent.setTariff(null);

            rentService.updateRent(newRent);
            System.out.println("Rent with id " + id + " has been updated successfully");
        } catch (InputMismatchException e) {
            System.out.print(e.getMessage());
        }
    }

    public void deleteRent() throws SQLException {
        System.out.println("Enter id in order to delete row:");
        Long id = Long.parseLong(secondScan.next());
        try {
            rentService.deleteRent(id);
        } catch (ServiceException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Rent with id " + id + " has been deleted successfully");
    }

    public void printAllRents() throws SQLException {
        List<Rent> rents = rentService.printAllRents();
        System.out.println("List of all rents:");
        rents.forEach(tariff1 -> System.out.println(tariff1.toString()));
    }

    public void getByIdRent() throws SQLException {
        System.out.println("Enter id in order to get rent:");
        Long id = Long.parseLong(firstScan.next());
        try {
            if (rentService.getRentById(id) != null) {
                System.out.println(rentService.getRentById(id).toString());
            } else {
                System.out.println("This id is doesn't exist");
            }

        } catch (NullPointerException e) {
            System.out.println("There is no such number ");
            getByIdRent();
        }
    }
}
