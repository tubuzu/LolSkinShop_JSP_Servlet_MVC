package Model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Model.BEAN.Admin;

public class CheckLoginDAO {
	private final String CHECK_LOGIN = "select * from Admin where Username = ? and Password = ?";
	public boolean checkLogin(String username, String password) {
		try(Connection connection = ConnectDatabase.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(CHECK_LOGIN);) {
			
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			ResultSet result = preparedStatement.executeQuery();
			
			if(result == null)
				return false;
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
}
