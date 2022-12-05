package Model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Model.BEAN.Admin;

public class CheckLoginDAO {
	private final String CHECK_ADMIN_LOGIN = "select * from admin where username = ? and password = ?";
	private final String CHECK_USER_LOGIN = "select * from user where username = ? and password = ?";

	public int checkLogin(String username, String password) {
		try (Connection connection = ConnectDatabase.getConnection();
				PreparedStatement APreparedStatement = connection.prepareStatement(CHECK_ADMIN_LOGIN);
				PreparedStatement UPreparedStatement = connection.prepareStatement(CHECK_USER_LOGIN);) {

			APreparedStatement.setString(1, username);
			APreparedStatement.setString(2, password);
			ResultSet result = APreparedStatement.executeQuery();

			if (result.next()) return 2;
			else {
				UPreparedStatement.setString(1, username);
				UPreparedStatement.setString(2, password);
				result = UPreparedStatement.executeQuery();
				if (result.next()) return 1;
				else return 0;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
}
