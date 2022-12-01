package Model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Model.BEAN.Phongban;
import Model.BEAN.Phongban;

public class PhongbanDAO {
	private static final String GET_ALL = "select * from phongban";
	private static final String GET_BY_ID = "select * from phongban where IDPB = ?";
	private static final String UPDATE = "update phongban set Tenpb = ?, Mota = ? where IDPB = ?";
	private static final String DELETE = "delete from phongban where IDPB = ?";

	public List<Phongban> getListPhongBan() {
		List<Phongban> phongbans = new ArrayList<>();
		try(Connection connection = ConnectDatabase.getConnection();
			Statement statement = connection.createStatement();) 
		{
			ResultSet resultSet = statement.executeQuery(GET_ALL);
			while(resultSet.next()) {
				String IDPB = resultSet.getString(1);
				String Tenpb = resultSet.getString(2);
				String Mota = resultSet.getString(3);
				phongbans.add(new Phongban(IDPB, Tenpb, Mota));
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return phongbans;
	}
	
	public Phongban getPhongBanByID(String IDPB) {
		Phongban pb = null;
		try(Connection connection = ConnectDatabase.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_ID)) 
		{
			preparedStatement.setString(1, IDPB);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				String Tenpb = resultSet.getString(2);
				String Mota = resultSet.getString(3);
				pb = new Phongban(IDPB, Tenpb, Mota);
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return pb;
	}
	
	public boolean update(String IDPB, String Tenpb, String Mota) {
		int row = 0;
		try(Connection connection = ConnectDatabase.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) 
		{
			preparedStatement.setString(1, Tenpb);
			preparedStatement.setString(2, Mota);
			preparedStatement.setString(3, IDPB);
			row = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return row >= 1;
	}
	
	public boolean delete(String IDPB) {
		int row = 0;
		try(Connection connection = ConnectDatabase.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) 
		{
			preparedStatement.setString(1, IDPB);
			row = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return row >= 1;
	}
}
