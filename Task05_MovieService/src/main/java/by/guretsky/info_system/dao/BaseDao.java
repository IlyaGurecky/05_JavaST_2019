package by.guretsky.info_system.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class BaseDao {
    protected Connection connection;

    public void setConnection(final Connection dbConnection) {
        this.connection = dbConnection;
    }

    protected void closeResources(final PreparedStatement st,
                                  final ResultSet result) throws SQLException {
        if (st != null) {
            st.close();
        }
        if (result != null) {
            result.close();
        }
    }
}
