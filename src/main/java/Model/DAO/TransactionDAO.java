package Model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Model.BEAN.Transaction;

public class TransactionDAO {
	private static final String GET_ALL = "select * from transaction";
	private static final String GET_LIST_BY_ID = "select * from transaction where id = ?";
	private static final String GET_LIST_BY_USERID = "select * from transaction where userId = ?";
//	private static final String GET_ID_BY_USERNAME_PASSWORD = "select * from user where username = ? and password = ?";
	private static final String ADD_TRANSACTION = "insert into transaction (skinId, userId) VALUES (?,?)";
//	private static final String UPDATE_SKINS = "update user SET skins = ? WHERE user.ID = ?";
//	private static final String INC_BALANCE = "update user SET balance = ? WHERE user.ID = ?";
//	private static final String DEC_BALANCE = "update user SET balance = ? WHERE user.ID = ?";
//	private static final String DELETE = "delete from user where id = ?";
//	private static final String GET_ALL_ID = "select id from user";

	public List<Transaction> getListTransaction() {
		List<Transaction> transactions = new ArrayList<>();
		try (Connection connection = ConnectDatabase.getConnection();
				Statement statement = connection.createStatement();) {
			ResultSet resultSet = statement.executeQuery(GET_ALL);
			while (resultSet.next()) {
				String Id = resultSet.getString(1);
				String SkinId = resultSet.getString(2);
				String UserId = resultSet.getString(3);
				String CreatedAt = resultSet.getString(3);
				transactions.add(new Transaction(Id, SkinId, UserId, CreatedAt));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return transactions;
	}

	public List<Transaction> getListTransactionByUserID(String UserID) {
		List<Transaction> transactions = new ArrayList<>();
		try (Connection connection = ConnectDatabase.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(GET_LIST_BY_USERID)) {
			preparedStatement.setString(1, UserID);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				String Id = resultSet.getString(1);
				String SkinId = resultSet.getString(2);
				String UserId = resultSet.getString(3);
				String CreatedAt = resultSet.getString(4);
				transactions.add(new Transaction(Id, SkinId, UserId, CreatedAt));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return transactions;
	}
	
	public Transaction getTransactionByID(String ID) {
		Transaction transaction = new Transaction();
		try (Connection connection = ConnectDatabase.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(GET_LIST_BY_ID)) {
			preparedStatement.setString(1, ID);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				String Id = resultSet.getString(1);
				String SkinId = resultSet.getString(2);
				String UserId = resultSet.getString(3);
				String CreatedAt = resultSet.getString(3);
				transaction = new Transaction(Id, SkinId, UserId, CreatedAt);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return transaction;
	}

//	public List<String> getListID() {
//		List<String> IDs = new ArrayList<>();
//		try (Connection connection = ConnectDatabase.getConnection();
//				Statement statement = connection.createStatement();) {
//			ResultSet resultSet = statement.executeQuery(GET_ALL_ID);
//			while (resultSet.next()) {
//				IDs.add(resultSet.getString(1));
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return IDs;
//	}
	
	public Boolean addTransaction(String SkinId, String UserId) {
		int row = 0;
		try (Connection connection = ConnectDatabase.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(ADD_TRANSACTION)) {
			preparedStatement.setString(1, SkinId);
			preparedStatement.setString(2, UserId);
			System.out.println(preparedStatement.toString());
			row = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return row >= 1;
	}

//	public boolean delete(String ID) {
//		int row = 0;
//		Connection connection = null;
//		try {
//			connection = ConnectDatabase.getConnection();
//			PreparedStatement preparedStatement = connection.prepareStatement(DELETE);
//			preparedStatement.setString(1, ID);
//			row = preparedStatement.executeUpdate();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return row >= 1;
//	}
}
