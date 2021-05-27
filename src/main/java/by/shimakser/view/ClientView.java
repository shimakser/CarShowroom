package by.shimakser.view;

import by.shimakser.model.Client;
import by.shimakser.service.ClientService;
import org.hibernate.service.spi.ServiceException;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ClientView {

    private ClientService clientService = new ClientService();
    private Scanner firstScan = new Scanner(System.in);
    private Scanner secondScan = new Scanner(System.in);

    private static void actions() {
        System.out.println("\nChoose clients action:\n"
                + "1-Show all row.\n"
                + "2-Insert new row.\n"
                + "3-Delete row.\n"
                + "4-Update row.\n"
                + "5-Search by id.\n"
                + "\t0-Exit.");
    }

    public void runClientView() throws SQLException {
        actions();
        Scanner mainScan = new Scanner(System.in);
        try {
            int numOfPoint = mainScan.nextInt();
            while (numOfPoint != 0) {
                switch (numOfPoint) {
                    case 1:
                        printAllClients();
                        actions();
                        numOfPoint = mainScan.nextInt();
                        break;
                    case 2:
                        addClient();

                        actions();
                        numOfPoint = mainScan.nextInt();
                        break;
                    case 3:
                        deleteClient();

                        actions();
                        numOfPoint = mainScan.nextInt();
                        break;
                    case 4:
                        updateClient();

                        actions();
                        numOfPoint = mainScan.nextInt();
                        break;
                    case 5:
                        getByIdClient();

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

    public void addClient() throws SQLException {
        try {
            Client client = new Client();

            System.out.println("Enter client name: ");
            String clientName = firstScan.nextLine();
            client.setName(clientName);

            System.out.println("Enter surname:");
            String clientSurname = secondScan.nextLine();
            client.setSurename(clientSurname);

            System.out.println("Enter pasport:");
            String clientPasport = firstScan.nextLine();
            client.setPasport(clientPasport);

            System.out.println("Enter tepephone:");
            String clientTelephone = secondScan.nextLine();
            client.setTepephone(clientTelephone);

            clientService.saveClient(client);
            System.out.println("Client has been created successfully");
        } catch (InputMismatchException e) {
            System.out.print(e.getMessage());
        }
    }

    public void updateClient() throws SQLException {
        try {
            System.out.println("Enter id in order to find element :");
            Long id = Long.parseLong(secondScan.next());

            Client newClient = clientService.getClientById(id);

            System.out.println("Enter client name: ");
            String clientName = firstScan.nextLine();
            newClient.setName(clientName);

            System.out.println("Enter surname:");
            String clientSurname = secondScan.nextLine();
            newClient.setSurename(clientSurname);

            System.out.println("Enter pasport:");
            String clientPasport = firstScan.nextLine();
            newClient.setPasport(clientPasport);

            System.out.println("Enter tepephone:");
            String clientTelephone = secondScan.nextLine();
            newClient.setTepephone(clientTelephone);

            clientService.updateClient(newClient);
            System.out.println("Client with id " + id + " has been updated successfully");
        } catch (InputMismatchException e) {
            System.out.print(e.getMessage());
        }
    }

    public void deleteClient() throws SQLException {
        System.out.println("Enter id in order to delete row:");
        Long id = Long.parseLong(secondScan.next());
        try {
            clientService.deleteClient(id);
        } catch (ServiceException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Client with id " + id + " has been deleted successfully");
    }

    public void printAllClients() throws SQLException {
        List<Client> clients = clientService.printAllClients();
        System.out.println("List of all clients:");
        clients.forEach(tariff1 -> System.out.println(tariff1.toString()));
    }

    public void getByIdClient() throws SQLException {
        System.out.println("Enter id in order to get client:");
        Long id = Long.parseLong(firstScan.next());
        try {
            if (clientService.getClientById(id) != null) {
                System.out.println(clientService.getClientById(id).toString());
            } else {
                System.out.println("This id is doesn't exist");
            }

        } catch (NullPointerException e) {
            System.out.println("There is no such number ");
            getByIdClient();
        }
    }
}
