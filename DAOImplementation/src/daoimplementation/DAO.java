package daoimplementation;

import java.sql.SQLException;
import java.util.List;

public interface DAO<R> {

    R get(int id) throws SQLException;

    List<R> getAll() throws SQLException;

    int save(R t) throws SQLException;

    int insert(R t) throws SQLException;

    int update(R t) throws SQLException;

    int delete(R t) throws SQLException;

}
