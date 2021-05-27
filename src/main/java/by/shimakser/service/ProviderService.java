package by.shimakser.service;

import by.shimakser.dao.Dao;
import by.shimakser.dao.impl.ProviderDaoImpl;
import by.shimakser.model.Provider;

import java.sql.SQLException;
import java.util.List;

public class ProviderService {

    private Dao<Provider> providerDao = new ProviderDaoImpl();

    public List<Provider> printAllProviders() throws SQLException {
        return providerDao.findAll();
    }

    public void saveProvider(Provider provider) throws SQLException {
        providerDao.add(provider);
    }

    public void deleteProvider(Long id) throws SQLException {
        providerDao.deleteById(id);
    }

    public void updateProvider(Provider newProvider) throws SQLException {
        providerDao.update(newProvider);
    }

    public Provider getProviderById(Long id) throws SQLException {
        return providerDao.findById(id);
    }
}
