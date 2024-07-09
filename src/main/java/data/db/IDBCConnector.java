

package data.db;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface IDBCConnector {
    void connect() throws SQLException;
    boolean execute(String sql) throws SQLException;
    ResultSet executeQuery(String sql) throws SQLException;
    void close() throws SQLException;

    CallableStatement prepareStatement(String sqlRequest);

    Connection getConnection();
}