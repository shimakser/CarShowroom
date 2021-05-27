package by.shimakser.dao.impl;

import by.shimakser.dao.Dao;
import by.shimakser.hiber.SessionUtil;
import by.shimakser.model.Tariff;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.List;

public class TariffDaoImpl extends SessionUtil implements Dao<Tariff> {

    @Override
    public Tariff add(Tariff tariff) throws SQLException {
        openTransactionSession();
        Session session = getSession();
        session.save(tariff);
        closeTransactionSesstion();
        return tariff;
    }

    @Override
    public List<Tariff> findAll() throws SQLException {
        openTransactionSession();
        Session session = getSession();
        List<Tariff> tariffs = session.createQuery("FROM Tariff").list();
        closeTransactionSesstion();
        return tariffs;
    }

    @Override
    public Tariff findById(Long id) throws SQLException {
        openTransactionSession();
        Session session = getSession();
        Tariff tariff = session.get(Tariff.class, id);
        closeTransactionSesstion();
        return tariff;
    }

    @Override
    public Tariff update(Tariff tariff) throws SQLException {
        openTransactionSession();
        Session session = getSession();
        session.saveOrUpdate(tariff);
        closeTransactionSesstion();
        return tariff;
    }

    @Override
    public void deleteById(Long id) throws SQLException {
        openTransactionSession();
        Session session = getSession();
        Tariff tariff = session.get(Tariff.class, id);
        session.remove(tariff);
        closeTransactionSesstion();
    }
}
