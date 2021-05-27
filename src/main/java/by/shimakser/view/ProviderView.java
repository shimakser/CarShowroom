package by.shimakser.view;

import by.shimakser.model.Provider;
import by.shimakser.service.ProviderService;
import org.hibernate.service.spi.ServiceException;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ProviderView {

    private ProviderService providerService = new ProviderService();
    private Scanner firstScan = new Scanner(System.in);
    private Scanner secondScan = new Scanner(System.in);

    private static void actions() {
        System.out.println("\nChoose providers action:\n"
                + "1-Show all row.\n"
                + "2-Insert new row.\n"
                + "3-Delete row.\n"
                + "4-Update row.\n"
                + "5-Search by id.\n"
                + "\t0-Exit.");
    }

    public void runProviderView() throws SQLException {
        actions();
        Scanner mainScan = new Scanner(System.in);
        try {
            int numOfPoint = mainScan.nextInt();
            while (numOfPoint != 0) {
                switch (numOfPoint) {
                    case 1:
                        printAllProviders();
                        actions();
                        numOfPoint = mainScan.nextInt();
                        break;
                    case 2:
                        addProvider();

                        actions();
                        numOfPoint = mainScan.nextInt();
                        break;
                    case 3:
                        deleteProvider();

                        actions();
                        numOfPoint = mainScan.nextInt();
                        break;
                    case 4:
                        updateProvider();

                        actions();
                        numOfPoint = mainScan.nextInt();
                        break;
                    case 5:
                        getByIdProvider();

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

    public void addProvider() throws SQLException {
        try {
            Provider provider = new Provider();

            System.out.println("Enter provider name: ");
            String providerName = firstScan.nextLine();
            provider.setProviderName(providerName);

            System.out.println("Enter provider country:");
            String providerCountry = secondScan.nextLine();
            provider.setCountry(providerCountry);

            System.out.println("Enter provider city: ");
            String providerCity = firstScan.nextLine();
            provider.setCity(providerCity);

            providerService.saveProvider(provider);
            System.out.println("Provider has been created successfully");
        } catch (InputMismatchException e) {
            System.out.print(e.getMessage());
        }
    }

    public void updateProvider() throws SQLException {
        try {
            System.out.println("Enter id in order to find element :");
            Long id = Long.parseLong(secondScan.next());

            Provider newProvider = providerService.getProviderById(id);

            System.out.println("Enter provider name: ");
            String providerName = firstScan.nextLine();
            newProvider.setProviderName(providerName);

            System.out.println("Enter provider country:");
            String providerCountry = secondScan.nextLine();
            newProvider.setCountry(providerCountry);

            System.out.println("Enter provider city: ");
            String providerCity = firstScan.nextLine();
            newProvider.setCity(providerCity);

            providerService.updateProvider(newProvider);
            System.out.println("Provider with id " + id + " has been updated successfully");
        } catch (InputMismatchException e) {
            System.out.print(e.getMessage());
        }
    }

    public void deleteProvider() throws SQLException {
        System.out.println("Enter id in order to delete row:");
        Long id = Long.parseLong(secondScan.next());
        try {
            providerService.deleteProvider(id);
        } catch (ServiceException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Provider with id " + id + " has been deleted successfully");
    }

    public void printAllProviders() throws SQLException {
        List<Provider> providers = providerService.printAllProviders();
        System.out.println("List of all providers:");
        providers.forEach(tariff1 -> System.out.println(tariff1.toString()));
    }

    public void getByIdProvider() throws SQLException {
        System.out.println("Enter id in order to get provider:");
        Long id = Long.parseLong(firstScan.next());
        try {
            if (providerService.getProviderById(id) != null) {
                System.out.println(providerService.getProviderById(id).toString());
            } else {
                System.out.println("This id is doesn't exist");
            }

        } catch (NullPointerException e) {
            System.out.println("There is no such number ");
            getByIdProvider();
        }
    }
}
