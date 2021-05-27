package by.shimakser.dao.impl;

import by.shimakser.dao.Dao;
import by.shimakser.hiber.SessionUtil;
import by.shimakser.model.Rent;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.List;

public class RentDaoImpl extends SessionUtil implements Dao<Rent> {

    @Override
    public Rent add(Rent rent) throws SQLException {
        openTransactionSession();
        Session session = getSession();
        session.save(rent);
        closeTransactionSesstion();
        return rent;
    }

    @Override
    public List<Rent> findAll() throws SQLException {
        openTransactionSession();
        Session session = getSession();
        List<Rent> rents = session.createQuery("FROM Rent").list();
        closeTransactionSesstion();
        return rents;
    }

    @Override
    public Rent findById(Long id) throws SQLException {
        openTransactionSession();
        Session session = getSession();
        Rent rent = session.get(Rent.class, id);
        closeTransactionSesstion();
        return rent;
    }

    @Override
    public Rent update(Rent rent) throws SQLException {
        openTransactionSession();
        Session session = getSession();
        session.saveOrUpdate(rent);
        closeTransactionSesstion();
        return rent;
    }

    @Override
    public void deleteById(Long id) throws SQLException {
        openTransactionSession();
        Session session = getSession();
        Rent rent = session.get(Rent.class, id);
        session.remove(rent);
        closeTransactionSesstion();
    }
}
