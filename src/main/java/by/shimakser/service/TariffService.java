package by.shimakser.service;

import by.shimakser.dao.Dao;
import by.shimakser.dao.impl.TariffDaoImpl;
import by.shimakser.model.Tariff;

import java.sql.SQLException;
import java.util.List;

public class TariffService {

    private Dao<Tariff> carDao = new TariffDaoImpl();

    public List<Tariff> printAllTariffs() throws SQLException {
        return carDao.findAll();
    }

    public void saveTariff(Tariff tariff) throws SQLException {
        carDao.add(tariff);
    }

    public void deleteTariff(Long id) throws SQLException {
        carDao.deleteById(id);
    }

    public void updateTariff(Tariff newTariff) throws SQLException {
        carDao.update(newTariff);
    }

    public Tariff getTariffById(Long id) throws SQLException {
        return carDao.findById(id);
    }
}
