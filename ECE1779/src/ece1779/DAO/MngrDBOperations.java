package ece1779.DAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.dbcp.datasources.SharedPoolDataSource;

import ece1779.GlobalValues;

public class MngrDBOperations {

	private SharedPoolDataSource dbcp;

	public MngrDBOperations(SharedPoolDataSource dbcp) throws SQLException {
		this.dbcp = dbcp;

	}

	/**
	 * clean up MySQL
	 * 
	 * @throws SQLException
	 */
	public void deleteAllDB() throws SQLException {
		Connection con = this.dbcp.getConnection();
		Statement statement = con.createStatement();
		try {

			// delete everything from both the users and images table in the
			// database
			statement.executeUpdate("DELETE FROM " + GlobalValues.dbTable_Users);
			statement.executeUpdate("DELETE FROM " + GlobalValues.dbTable_Images);
			
			// reset the auto increment for both tables
			statement.executeUpdate("ALTER TABLE " + GlobalValues.dbTable_Users + " AUTO_INCREMENT = 1");
			statement.executeUpdate("ALTER TABLE " + GlobalValues.dbTable_Images + " AUTO_INCREMENT = 1");

		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {

			}

		}
	}
}