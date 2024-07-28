

package data.db;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface IDBCConnector {
    public void connect();
    boolean execute(String sql);
    ResultSet executeQuery(String sql) throws SQLException;
    void close() throws SQLException;

    CallableStatement prepareStatement(String sqlRequest);

    Connection getConnection();
}