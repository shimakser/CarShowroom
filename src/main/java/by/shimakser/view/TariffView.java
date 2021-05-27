package by.shimakser.view;

import by.shimakser.model.Tariff;
import by.shimakser.service.TariffService;
import org.hibernate.service.spi.ServiceException;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class TariffView {

    private TariffService tariffService = new TariffService();
    private Scanner firstScan = new Scanner(System.in);
    private Scanner secondScan = new Scanner(System.in);

    private static void actions() {
        System.out.println("\nChoose tariffs action:\n"
                + "1-Show all row.\n"
                + "2-Insert new row.\n"
                + "3-Delete row.\n"
                + "4-Update row.\n"
                + "5-Search by id.\n"
                + "\t0-Exit.");
    }

    public void runTariffView() throws SQLException {
        actions();
        Scanner mainScan = new Scanner(System.in);
        try {
            int numOfPoint = mainScan.nextInt();
            while (numOfPoint != 0) {
                switch (numOfPoint) {
                    case 1:
                        printAllTariffs();
                        actions();
                        numOfPoint = mainScan.nextInt();
                        break;
                    case 2:
                        addTariff();

                        actions();
                        numOfPoint = mainScan.nextInt();
                        break;
                    case 3:
                        deleteTariff();

                        actions();
                        numOfPoint = mainScan.nextInt();
                        break;
                    case 4:
                        updateTariff();

                        actions();
                        numOfPoint = mainScan.nextInt();
                        break;
                    case 5:
                        getByIdTariff();

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

    public void addTariff() throws SQLException {
        try {
            Tariff tariff = new Tariff();

            System.out.println("Enter tariff name : ");
            String tariffName = firstScan.nextLine();
            tariff.setTariffName(tariffName);

            System.out.println("Enter price per day:");
            String pricePerDay = secondScan.nextLine();
            tariff.setPricePerDay(pricePerDay);

            tariffService.saveTariff(tariff);
            System.out.println("Tariff has been created successfully");
        } catch (InputMismatchException e) {
            System.out.print(e.getMessage());
        }
    }

    public void updateTariff() throws SQLException {
        try {
            System.out.println("Enter id in order to find element :");
            Long id = Long.parseLong(secondScan.next());

            Tariff newTariff = tariffService.getTariffById(id);

            System.out.println("Enter tariff name : ");
            String tariffName = firstScan.nextLine();
            newTariff.setTariffName(tariffName);

            System.out.println("Enter price per day:");
            String pricePerDay = firstScan.nextLine();
            newTariff.setPricePerDay(pricePerDay);

            tariffService.updateTariff(newTariff);
            System.out.println("Tariff with id " + id + " has been updated successfully");
        } catch (InputMismatchException e) {
            System.out.print(e.getMessage());
        }
    }

    public void deleteTariff() throws SQLException {
        System.out.println("Enter id in order to delete row:");
        Long id = Long.parseLong(secondScan.next());
        try {
            tariffService.deleteTariff(id);
        } catch (ServiceException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Tariff with id " + id + " has been deleted successfully");
    }

    public void printAllTariffs() throws SQLException {
        List<Tariff> tariffs = tariffService.printAllTariffs();
        System.out.println("List of all tariffs:");
        tariffs.forEach(tariff1 -> System.out.println(tariff1.toString()));
    }

    public void getByIdTariff() throws SQLException {
        System.out.println("Enter id in order to get tariff:");
        Long id = Long.parseLong(firstScan.next());
        try {
            if (tariffService.getTariffById(id) != null) {
                System.out.println(tariffService.getTariffById(id).toString());
            } else {
                System.out.println("This id is doesn't exist");
            }

        } catch (NullPointerException e) {
            System.out.println("There is no such number ");
            getByIdTariff();
        }
    }
}
