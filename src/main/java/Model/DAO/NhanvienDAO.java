package Model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Model.BEAN.Nhanvien;

public class NhanvienDAO {
	private static final String GET_ALL = "select * from nhanvien";
	private static final String GET_LIST_BY_IDPB = "select * from nhanvien where IDPB = ?";
	private static final String DELETE = "delete from nhanvien where IDNV = ?";
	private static final String GET_ALL_IDNV = "select IDNV from nhanvien";
	private static final String SEARCH = "select * from nhanvien where ? like ?";
	private static final String SEARCH_ALL = "select * from nhanvien where Hoten like ? or IDNV like ? or Diachi like ? or IDPB like ?";

	public List<Nhanvien> getListNhanVien() {
		List<Nhanvien> nhanviens = new ArrayList<>();
		try (Connection connection = ConnectDatabase.getConnection();
				Statement statement = connection.createStatement();) {
			ResultSet resultSet = statement.executeQuery(GET_ALL);
			while (resultSet.next()) {
				String IDNV = resultSet.getString(1);
				String Hoten = resultSet.getString(2);
				String Diachi = resultSet.getString(3);
				String IDPB = resultSet.getString(4);
				nhanviens.add(new Nhanvien(IDNV, Hoten, Diachi, IDPB));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nhanviens;
	}

	public List<Nhanvien> getListNhanVienByIDPB(String IDPB) {
		List<Nhanvien> nhanviens = new ArrayList<>();
		try (Connection connection = ConnectDatabase.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(GET_LIST_BY_IDPB)) {
			preparedStatement.setString(1, IDPB);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				String IDNV = resultSet.getString(1);
				String Hoten = resultSet.getString(2);
				String Diachi = resultSet.getString(3);
				nhanviens.add(new Nhanvien(IDNV, Hoten, Diachi, IDPB));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nhanviens;
	}

	public List<String> getListIDNV() {
		List<String> IDNVs = new ArrayList<>();
		try (Connection connection = ConnectDatabase.getConnection();
				Statement statement = connection.createStatement();) {
			ResultSet resultSet = statement.executeQuery(GET_ALL_IDNV);
			while (resultSet.next()) {
				IDNVs.add(resultSet.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return IDNVs;
	}

	public boolean delete(String IDNV) {
		int row = 0;
		Connection connection = null;
		try {
			connection = ConnectDatabase.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(DELETE);
			preparedStatement.setString(1, IDNV);
			row = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return row >= 1;
	}

	public List<Nhanvien> search(String searchBy, String searchVal) {
		System.out.println(searchBy);
		System.out.println(searchVal);
		List<Nhanvien> nhanviens = new ArrayList<>();
		String query = "select * from nhanvien where " + searchBy + " like '%" + searchVal + "%'";
		try (Connection connection = ConnectDatabase.getConnection();
				PreparedStatement statement = connection.prepareStatement(query);) {
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				String IDNV = resultSet.getString(1);
				String Hoten = resultSet.getString(2);
				String Diachi = resultSet.getString(3);
				String IDPB = resultSet.getString(4);
				nhanviens.add(new Nhanvien(IDNV, Hoten, Diachi, IDPB));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(nhanviens.size());
		return nhanviens;
	}

	public List<Nhanvien> searchAll(String searchVal) {
		List<Nhanvien> nhanviens = new ArrayList<>();
		String search = "%" + searchVal + "%";
		try (Connection connection = ConnectDatabase.getConnection();
				PreparedStatement statement = connection.prepareStatement(SEARCH_ALL);) {
			statement.setString(1, search);
			statement.setString(2, search);
			statement.setString(3, search);
			statement.setString(4, search);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				String IDNV = resultSet.getString(1);
				String Hoten = resultSet.getString(2);
				String Diachi = resultSet.getString(3);
				String IDPB = resultSet.getString(4);
				nhanviens.add(new Nhanvien(IDNV, Hoten, Diachi, IDPB));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nhanviens;
	}
}
