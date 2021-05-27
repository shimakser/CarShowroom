package by.shimakser.view;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MainView {

    private CarView carView;
    private ClientView clientView;
    private ProviderView providerView;
    private RentView rentView;
    private TariffView tariffView;

    private static MainView mainView;

    private MainView() {
        carView = new CarView();
        clientView = new ClientView();
        providerView = new ProviderView();
        rentView = new RentView();
        tariffView = new TariffView();
    }

    public static MainView getInstance() {
        if (mainView == null) {
            mainView = new MainView();
        }
        return mainView;
    }

    private static void actions() {
        System.out.println("\nChoose action:\n"
                + "1-Cars.\n"
                + "2-Clients.\n"
                + "3-Providers.\n"
                + "4-Rents.\n"
                + "5-Tariffs.\n"
                + "\t0-Exit.");
    }

    public void runMainView() throws SQLException {
        actions();
        Scanner mainScan = new Scanner(System.in);

        try {
            int numOfPoint = mainScan.nextInt();
            while (numOfPoint != 0) {
                switch (numOfPoint) {
                    case 1:
                        runCar();
                        break;
                    case 2:
                        runClient();
                        break;
                    case 3:
                        runProvider();
                        break;
                    case 4:
                        runRent();
                        break;
                    case 5:
                        runTariff();
                        break;
                    case 0:
                        numOfPoint = 0;
                        break;
                    default:
                        System.out.println("There is no such command.");
                        System.out.println("Choose action:");
                        numOfPoint = mainScan.nextInt();
                }
            }
        } catch (InputMismatchException e) {
            System.out.println(e.getMessage());
        }
        mainScan.close();
    }

    public void runCar() throws SQLException {
        carView.runCarView();
    }

    public void runClient() throws SQLException {
        clientView.runClientView();
    }

    public void runProvider() throws SQLException {
        providerView.runProviderView();
    }

    public void runRent() throws SQLException {
        rentView.runRentView();
    }

    public void runTariff() throws SQLException {
        tariffView.runTariffView();
    }

}
