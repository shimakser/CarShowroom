package by.shimakser.dao;

import java.sql.SQLException;
import java.util.List;

public interface Dao<T> {
    T add(T t) throws SQLException;

    List<T> findAll() throws SQLException;

    T findById(Long id) throws SQLException;

    T update(T data) throws SQLException;

    void deleteById(Long id) throws SQLException;
}
