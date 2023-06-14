package lk.ijse.restaurant.dao;

import java.sql.SQLException;
import java.util.List;

public interface CrudDAO<T> extends SuperDAO {

    List<T> loadAll() throws SQLException;

    int save(T entity) throws SQLException;

    T search(String id) throws SQLException;

    int update(T entity) throws SQLException;

    int delete(String id) throws SQLException;

    String generateNextId() throws SQLException;
}
