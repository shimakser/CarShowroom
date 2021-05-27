package by.shimakser.dao.impl;

import by.shimakser.dao.Dao;
import by.shimakser.hiber.SessionUtil;
import by.shimakser.model.Client;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.List;

public class ClientDaoImpl extends SessionUtil implements Dao<Client> {

    @Override
    public Client add(Client client) throws SQLException {
        openTransactionSession();
        Session session = getSession();
        session.save(client);
        closeTransactionSesstion();
        return client;
    }

    @Override
    public List<Client> findAll() throws SQLException {
        openTransactionSession();
        Session session = getSession();
        List<Client> clients = session.createQuery("FROM Client").list();
        closeTransactionSesstion();
        return clients;
    }

    @Override
    public Client findById(Long id) throws SQLException {
        openTransactionSession();
        Session session = getSession();
        Client client = session.get(Client.class, id);
        closeTransactionSesstion();
        return client;
    }

    @Override
    public Client update(Client client) throws SQLException {
        openTransactionSession();
        Session session = getSession();
        session.saveOrUpdate(client);
        closeTransactionSesstion();
        return client;
    }

    @Override
    public void deleteById(Long id) throws SQLException {
        openTransactionSession();
        Session session = getSession();
        Client client = session.get(Client.class, id);
        session.remove(client);
        closeTransactionSesstion();
    }
}
