package by.shimakser.dao.impl;

import by.shimakser.dao.Dao;
import by.shimakser.hiber.SessionUtil;
import by.shimakser.model.Provider;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.List;

public class ProviderDaoImpl extends SessionUtil implements Dao<Provider> {

    @Override
    public Provider add(Provider provider) throws SQLException {
        openTransactionSession();
        Session session = getSession();
        session.save(provider);
        closeTransactionSesstion();
        return provider;
    }

    @Override
    public List<Provider> findAll() throws SQLException {
        openTransactionSession();
        Session session = getSession();
        List<Provider> providers = session.createQuery("FROM Provider").list();
        closeTransactionSesstion();
        return providers;
    }

    @Override
    public Provider findById(Long id) throws SQLException {
        openTransactionSession();
        Session session = getSession();
        Provider provider = session.get(Provider.class, id);
        closeTransactionSesstion();
        return provider;
    }

    @Override
    public Provider update(Provider provider) throws SQLException {
        openTransactionSession();
        Session session = getSession();
        session.saveOrUpdate(provider);
        closeTransactionSesstion();
        return provider;
    }

    @Override
    public void deleteById(Long id) throws SQLException {
        openTransactionSession();
        Session session = getSession();
        Provider provider = session.get(Provider.class, id);
        session.remove(provider);
        closeTransactionSesstion();
    }
}
