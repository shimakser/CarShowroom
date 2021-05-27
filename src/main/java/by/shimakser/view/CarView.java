package by.shimakser.view;

import by.shimakser.model.Car;
import by.shimakser.model.Provider;
import by.shimakser.service.CarService;
import by.shimakser.service.ProviderService;
import org.hibernate.service.spi.ServiceException;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class CarView {

    private CarService carService = new CarService();
    private ProviderService providerService = new ProviderService();
    private Scanner firstScan = new Scanner(System.in);
    private Scanner secondScan = new Scanner(System.in);

    private static void actions() {
        System.out.println("\nChoose cars action:\n"
                + "1-Show all row.\n"
                + "2-Insert new row.\n"
                + "3-Delete row.\n"
                + "4-Update row.\n"
                + "5-Search by id.\n"
                + "6-Search by title.\n"
                + "\t0-Exit.");
    }

    public void runCarView() throws SQLException {
        actions();
        Scanner mainScan = new Scanner(System.in);
        try {
            int numOfPoint = mainScan.nextInt();
            while (numOfPoint != 0) {
                switch (numOfPoint) {
                    case 1:
                        printAllCars();
                        actions();
                        numOfPoint = mainScan.nextInt();
                        break;
                    case 2:
                        addCar();

                        actions();
                        numOfPoint = mainScan.nextInt();
                        break;
                    case 3:
                        deleteCar();

                        actions();
                        numOfPoint = mainScan.nextInt();
                        break;
                    case 4:
                        updateCar();

                        actions();
                        numOfPoint = mainScan.nextInt();
                        break;
                    case 5:
                        getByIdCar();

                        actions();
                        numOfPoint = mainScan.nextInt();
                        break;
                    case 6:
                        getCarByTitle();

                        actions();
                        numOfPoint = mainScan.nextInt();
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

    public void addCar() throws SQLException {
        try {
            Car car = new Car();

            System.out.println("Enter car title : ");
            String carTitle = firstScan.nextLine();
            car.setTitle(carTitle);

            System.out.println("Enter car model:");
            String carModel = firstScan.nextLine();
            car.setModel(carModel);

            System.out.println("Enter car year:");
            String carYear = firstScan.nextLine();
            car.setYear(carYear);

            System.out.println("Enter car color:");
            String carColor = firstScan.nextLine();
            car.setColor(carColor);

            Provider testProvider = providerService.getProviderById(1L);
            if (testProvider != null) {
                System.out.println("Enter provider_id:");
                String carProvider = firstScan.nextLine();
                Provider provider = providerService.getProviderById((long) Integer.parseInt(carProvider));
                car.setProvider(provider);
            } else car.setProvider(null);

            carService.saveCar(car);
            System.out.println("Car has been created successfully");
        } catch (InputMismatchException e) {
            System.out.print(e.getMessage());
        }
    }

    public void updateCar() throws SQLException {
        try {
            System.out.println("Enter id in order to find element:");
            Long id = Long.parseLong(secondScan.next());

            Car newCar = carService.getCarById(id);

            System.out.println("Enter car title: ");
            String carTitle = firstScan.nextLine();
            newCar.setTitle(carTitle);

            System.out.println("Enter car model:");
            String carModel = firstScan.nextLine();
            newCar.setModel(carModel);

            System.out.println("Enter car year:");
            String carYear = firstScan.nextLine();
            newCar.setYear(carYear);

            System.out.println("Enter car color:");
            String carColor = firstScan.nextLine();
            newCar.setColor(carColor);

            Provider testProvider = providerService.getProviderById(1L);
            if (testProvider != null) {
                System.out.println("Enter provider_id:");
                String carProvider = secondScan.nextLine();
                Provider provider = providerService.getProviderById((long) Integer.parseInt(carProvider));
                newCar.setProvider(provider);
            } else newCar.setProvider(null);


            carService.updateCar(newCar);
            System.out.println("Car with id " + id + " has been updated successfully");
        } catch (InputMismatchException e) {
            System.out.print(e.getMessage());
        }
    }

    public void deleteCar() throws SQLException {
        System.out.println("Enter id in order to delete row:");
        Long id = Long.parseLong(secondScan.next());
        try {
            carService.deleteCar(id);
        } catch (ServiceException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Car with id " + id + " has been deleted successfully");
    }

    public void printAllCars() throws SQLException {
        List<Car> cars = carService.printAllCars();
        System.out.println("List of all cars:");
        cars.forEach(tariff1 -> System.out.println(tariff1.toString()));
    }

    public void getByIdCar() throws SQLException {
        System.out.println("Enter id in order to get car:");
        Long id = Long.parseLong(firstScan.next());
        try {
            if (carService.getCarById(id) != null) {
                System.out.println(carService.getCarById(id).toString());
            } else {
                System.out.println("This id is doesn't exist");
            }

        } catch (NullPointerException e) {
            System.out.println("There is no such number ");
            getByIdCar();
        }
    }

    public void getCarByTitle() throws SQLException {
        System.out.println("Enter title in order to get car:");
        String title = secondScan.next();
        try {
            if (carService.getCarByTitle(title) != null) {
                System.out.println(carService.getCarByTitle(title).toString());
            } else {
                System.out.println("This title is doesn't exist");
            }

        } catch (NullPointerException e) {
            System.out.println("There is no such number ");
            getCarByTitle();
        }
    }
}
