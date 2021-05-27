package by.shimakser.service;

import by.shimakser.dao.Dao;
import by.shimakser.dao.impl.ClientDaoImpl;
import by.shimakser.model.Client;

import java.sql.SQLException;
import java.util.List;

public class ClientService {

    private Dao<Client> clientDao = new ClientDaoImpl();

    public List<Client> printAllClients() throws SQLException {
        return clientDao.findAll();
    }

    public void saveClient(Client client) throws SQLException {
        clientDao.add(client);
    }

    public void deleteClient(Long id) throws SQLException {
        clientDao.deleteById(id);
    }

    public void updateClient(Client newClient) throws SQLException {
        clientDao.update(newClient);
    }

    public Client getClientById(Long id) throws SQLException {
        return clientDao.findById(id);
    }
}
