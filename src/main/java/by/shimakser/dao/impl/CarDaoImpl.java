package by.shimakser.dao.impl;

import by.shimakser.dao.CarDao;
import by.shimakser.hiber.SessionUtil;
import by.shimakser.model.Car;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.List;

public class CarDaoImpl extends SessionUtil implements CarDao {

    @Override
    public Car add(Car car) throws SQLException {
        openTransactionSession();
        Session session = getSession();
        session.save(car);
        closeTransactionSesstion();
        return car;
    }

    @Override
    public List<Car> findAll() throws SQLException {
        openTransactionSession();
        Session session = getSession();
        List<Car> cars = session.createQuery("FROM Car").list();
        closeTransactionSesstion();
        return cars;
    }

    @Override
    public Car findById(Long id) throws SQLException {
        openTransactionSession();
        Session session = getSession();
        Car car = session.get(Car.class, id);
        closeTransactionSesstion();
        return car;
    }

    @Override
    public Car update(Car car) throws SQLException {
        openTransactionSession();
        Session session = getSession();
        session.saveOrUpdate(car);
        closeTransactionSesstion();
        return car;
    }

    @Override
    public void deleteById(Long id) throws SQLException {
        openTransactionSession();
        Session session = getSession();
        Car car = session.get(Car.class, id);
        session.remove(car);
        closeTransactionSesstion();
    }

    @Override
    public Car findCarByTitle(String title) throws SQLException {
        openTransactionSession();
        Session session = getSession();
        List<Car> cars = session.createQuery("FROM Car").list();
        Car searchingCar = new Car();
        for (Car car: cars) {
            if (car.getTitle().equals(title)) {
                return searchingCar = car;
            }
        }
        closeTransactionSesstion();
        return searchingCar;
    }
}
