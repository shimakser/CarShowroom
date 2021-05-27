package by.shimakser.dao;

import by.shimakser.model.Car;

import java.sql.SQLException;

public interface CarDao extends Dao<Car>{
    Car findCarByTitle(String title) throws SQLException;
}
