package Model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Model.BEAN.Skin;
import Model.BEAN.User;
import Model.BO.SkinBO;

public class UserDAO {
	private static final String GET_ALL = "select * from user";
	private static final String GET_LIST_BY_ID = "select * from user where id = ?";
	private static final String GET_ID_BY_USERNAME_PASSWORD = "select * from user where username = ? and password = ?";
	private static final String ADD_USER = "insert into user (ID, username, password, balance) VALUES (NULL, ?, ?, '0')";
	private static final String INC_BALANCE = "update user SET balance = ? WHERE user.ID = ?";
	private static final String DEC_BALANCE = "update user SET balance = ? WHERE user.ID = ?";
	private static final String DELETE = "delete from user where id = ?";
	private static final String GET_ALL_ID = "select id from user";
	private static final String UPDATE = "UPDATE user SET username = ?, password = ?, balance = ? WHERE user.ID = ?";
	private static final String SEARCH = "select * from user where ? like ?";

	public List<User> getListUser() {
		List<User> users = new ArrayList<>();
		try (Connection connection = ConnectDatabase.getConnection();
				Statement statement = connection.createStatement();) {
			ResultSet resultSet = statement.executeQuery(GET_ALL);
			while (resultSet.next()) {
				String Id = resultSet.getString(1);
				String Username = resultSet.getString(2);
				String Password = resultSet.getString(3);
				String Balance = resultSet.getString(4);
				users.add(new User(Id, Username, Password, Integer.parseInt(Balance)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}

	public String getUserIdByUsernamePassword(String Username, String Password) {
		String Id = "";
		try (Connection connection = ConnectDatabase.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(GET_ID_BY_USERNAME_PASSWORD)) {
			preparedStatement.setString(1, Username);
			preparedStatement.setString(2, Password);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Id = resultSet.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Id;
	}

	public List<User> getUserByID(String ID) {
		List<User> users = new ArrayList<>();
		try (Connection connection = ConnectDatabase.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(GET_LIST_BY_ID)) {
			preparedStatement.setString(1, ID);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				String Id = resultSet.getString(1);
				String Username = resultSet.getString(2);
				String Password = resultSet.getString(3);
				int Balance = Integer.parseInt(resultSet.getString(4));
				users.add(new User(Id, Username, Password, Balance));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}

	public List<String> getListID() {
		List<String> IDs = new ArrayList<>();
		try (Connection connection = ConnectDatabase.getConnection();
				Statement statement = connection.createStatement();) {
			ResultSet resultSet = statement.executeQuery(GET_ALL_ID);
			while (resultSet.next()) {
				IDs.add(resultSet.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return IDs;
	}

	public Boolean addUser(String Username, String Password) {
		int row = 0;
		try (Connection connection = ConnectDatabase.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(ADD_USER)) {
			System.out.println(Username + Password);
			System.out.println(ADD_USER);
			preparedStatement.setString(1, Username);
			preparedStatement.setString(2, Password);
			System.out.println(preparedStatement.toString());
			row = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return row >= 1;
	}

	public boolean delete(String ID) {
		int row = 0;
		Connection connection = null;
		try {
			connection = ConnectDatabase.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(DELETE);
			preparedStatement.setString(1, ID);
			row = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return row >= 1;
	}

	public boolean incBalance(String ID, int Amount) {
		int row = 0;
		Connection connection = null;
		try {
			connection = ConnectDatabase.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(GET_LIST_BY_ID);
			preparedStatement.setString(1, ID);
			ResultSet resultSet = preparedStatement.executeQuery();
			int currBalance = 0;
			while (resultSet.next()) {
				currBalance = Integer.parseInt(resultSet.getString(4));
			}
			String newBalance = String.valueOf(currBalance + Amount);
			preparedStatement = connection.prepareStatement(INC_BALANCE);
			preparedStatement.setString(1, newBalance);
			preparedStatement.setString(2, ID);
			row = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return row >= 1;
	}

	public boolean decBalance(String ID, String SkinId) {
		int row = 0;
		Connection connection = null;
		try {
			connection = ConnectDatabase.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(GET_LIST_BY_ID);
			preparedStatement.setString(1, ID);
			ResultSet resultSet = preparedStatement.executeQuery();
			int currBalance = 0;
			while (resultSet.next()) {
				currBalance = Integer.parseInt(resultSet.getString(4));
			}
			SkinBO skinBO = new SkinBO();
			int price = skinBO.getSkinPriceById(SkinId);
			if (currBalance < price)
				return false;
			String newBalance = String.valueOf(currBalance - price);
			preparedStatement = connection.prepareStatement(DEC_BALANCE);
			preparedStatement.setString(1, newBalance);
			preparedStatement.setString(2, ID);
			row = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return row >= 1;
	}

	public List<User> searchByUserNameAndMinBalance(String searchUsername, String minBalance) {
		List<User> users = new ArrayList<>();
		if (minBalance == "")
			minBalance = "0";
		try (Connection connection = ConnectDatabase.getConnection();
				PreparedStatement statement = connection.prepareStatement("select * from user where username like '%"
						+ searchUsername + "%' and balance > " + Integer.parseInt(minBalance));) {
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				String Id = resultSet.getString(1);
				String Username = resultSet.getString(2);
				String Password = resultSet.getString(3);
				String Balance = resultSet.getString(4);
				users.add(new User(Id, Username, Password, Integer.parseInt(Balance)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}

	public boolean update(String Id, String Username, String Password, String Balance) {
		int row = 0;
		Connection connection = null;
		try {
			connection = ConnectDatabase.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);
			preparedStatement.setString(1, Username);
			preparedStatement.setString(2, Password);
			preparedStatement.setString(3, Balance);
			preparedStatement.setString(4, Id);
			row = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return row >= 1;
	}
	
//	public boolean addSkin(String SkinID, String UserID) {
//		int row = 0;
//		Connection connection = null;
//		try {
//			connection = ConnectDatabase.getConnection();
//			PreparedStatement preparedStatement = connection.prepareStatement("select * from skin where id = ?");
//			preparedStatement.setString(1, SkinID);
//			ResultSet resultSet = preparedStatement.executeQuery();
//			String skinID = "";
//			while (resultSet.next()) {
//				skinID = resultSet.getString(1);
//			}
//			if (skinID == "") return false;
//			
//			List<String> skins = getUserByID(UserID).getSkins();
//			if(skins.size() == 0) {
//				skins = new ArrayList<String>();
//			}
//			List<String> currSkins = new ArrayList<>(skins);
//			System.out.println(currSkins);
//			currSkins.add(skinID);
//			System.out.println("skins.size: "+currSkins.size());
//			String updatedSkins = String.join(",", currSkins);
//			
//			preparedStatement = connection.prepareStatement(UPDATE_SKINS);
//			preparedStatement.setString(1, updatedSkins);
//			preparedStatement.setString(2, UserID);
//			row = preparedStatement.executeUpdate();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return row >= 1;
//	}
}
