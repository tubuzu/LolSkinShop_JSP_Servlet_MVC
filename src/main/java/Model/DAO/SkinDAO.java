package Model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Model.BEAN.Skin;

public class SkinDAO {
	private static final String GET_ALL = "select * from skin";
	private static final String GET_LIST_BY_ID = "select * from skin where ID = ?";
	private static final String ADD_SKIN = "insert into skin (ID, name, img, price, tier, champion) VALUES (NULL, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_ID = "select ID from skin";
	private static final String UPDATE = "UPDATE skin SET name = ?, img = ?, price = ?, tier = ?, champion = ? WHERE skin.ID = ?";
	private static final String DELETE = "delete from skin where ID = ?";
	private static final String SEARCH = "select * from skin where ? like ?";
	private static final String SEARCH_BY_NAME_AND_TIER = "select * from skin where name like ? and tier like ?";

	public List<Skin> getListSkin() {
		List<Skin> skins = new ArrayList<>();
		try (Connection connection = ConnectDatabase.getConnection();
				Statement statement = connection.createStatement();) {
			ResultSet resultSet = statement.executeQuery(GET_ALL);
			while (resultSet.next()) {
				String Id = resultSet.getString(1);
				String Name = resultSet.getString(2);
				String Img = resultSet.getString(3);
				int Price = resultSet.getInt(4);
				int Tier = resultSet.getInt(5);
				String Champion = resultSet.getString(6);
				skins.add(new Skin(Id, Name, Img, Price, Tier, Champion));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return skins;
	}

	public List<Skin> getSkinByID(String ID) {
		List<Skin> skins = new ArrayList<>();
		try (Connection connection = ConnectDatabase.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(GET_LIST_BY_ID)) {
			preparedStatement.setString(1, ID);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				String Id = resultSet.getString(1);
				String Name = resultSet.getString(2);
				String Img = resultSet.getString(3);
				int Price = resultSet.getInt(4);
				int Tier = resultSet.getInt(5);
				String Champion = resultSet.getString(6);
				skins.add(new Skin(Id, Name, Img, Price, Tier, Champion));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return skins;
	}

	public int getSkinPriceById(String ID) {
		int price = 0;
		try (Connection connection = ConnectDatabase.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(GET_LIST_BY_ID)) {
			preparedStatement.setString(1, ID);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				price = Integer.parseInt(resultSet.getString(4));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return price;
	}

	public Boolean addSkin(String Name, String Img, int Price, int Tier, String Champion) {
		int row = 0;
		try (Connection connection = ConnectDatabase.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(ADD_SKIN)) {
			System.out.println(Name + Img + Price + Tier + Champion);
			System.out.println(ADD_SKIN);
			preparedStatement.setString(1, Name);
			preparedStatement.setString(2, Img);
			preparedStatement.setString(3, String.valueOf(Price));
			preparedStatement.setString(4, String.valueOf(Tier));
			preparedStatement.setString(5, Champion);

			System.out.println(preparedStatement.toString());
			row = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return row >= 1;
	}

	public List<Skin> searchByNameAndTier(String searchName, String searchTier) {
		List<Skin> skins = new ArrayList<>();
		if (Integer.parseInt(searchTier) == 0)
			searchTier = "";
		try (Connection connection = ConnectDatabase.getConnection();
				PreparedStatement statement = connection.prepareStatement("select * from skin where name like '%"
						+ searchName + "%' and tier like '%" + searchTier + "%'");) {
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				String Id = resultSet.getString(1);
				String Name = resultSet.getString(2);
				String Img = resultSet.getString(3);
				int Price = resultSet.getInt(4);
				int Tier = resultSet.getInt(5);
				String Champion = resultSet.getString(6);
				skins.add(new Skin(Id, Name, Img, Price, Tier, Champion));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return skins;
	}

//	public List<Skin> getListNhanVienByIDPB(String IDPB) {
//		List<Skin> nhanviens = new ArrayList<>();
//		try (Connection connection = ConnectDatabase.getConnection();
//				PreparedStatement preparedStatement = connection.prepareStatement(GET_LIST_BY_IDPB)) {
//			preparedStatement.setString(1, IDPB);
//			ResultSet resultSet = preparedStatement.executeQuery();
//			while (resultSet.next()) {
//				String IDNV = resultSet.getString(1);
//				String Hoten = resultSet.getString(2);
//				String Diachi = resultSet.getString(3);
//				nhanviens.add(new Skin(IDNV, Hoten, Diachi, IDPB));
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return nhanviens;
//	}
//
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
	
	public boolean update(String Id, String Name, String Img, int Price, int Tier, String Champion) {
		int row = 0;
		Connection connection = null;
		try {
			connection = ConnectDatabase.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);
			preparedStatement.setString(1, Name);
			preparedStatement.setString(2, Img);
			preparedStatement.setString(3, String.valueOf(Price));
			preparedStatement.setString(4, String.valueOf(Tier));
			preparedStatement.setString(5, Champion);
			preparedStatement.setString(6, Id);
			row = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return row >= 1;
	}
//
//	public List<Nhanvien> search(String searchBy, String searchVal) {
//		System.out.println(searchBy);
//		System.out.println(searchVal);
//		List<Nhanvien> nhanviens = new ArrayList<>();
//		String query = "select * from nhanvien where " + searchBy + " like '%" + searchVal + "%'";
//		try (Connection connection = ConnectDatabase.getConnection();
//				PreparedStatement statement = connection.prepareStatement(query);) {
//			ResultSet resultSet = statement.executeQuery();
//			while (resultSet.next()) {
//				String IDNV = resultSet.getString(1);
//				String Hoten = resultSet.getString(2);
//				String Diachi = resultSet.getString(3);
//				String IDPB = resultSet.getString(4);
//				nhanviens.add(new Nhanvien(IDNV, Hoten, Diachi, IDPB));
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		System.out.println(nhanviens.size());
//		return nhanviens;
//	}
//
//	public List<Nhanvien> searchAll(String searchVal) {
//		List<Nhanvien> nhanviens = new ArrayList<>();
//		String search = "%" + searchVal + "%";
//		try (Connection connection = ConnectDatabase.getConnection();
//				PreparedStatement statement = connection.prepareStatement(SEARCH_ALL);) {
//			statement.setString(1, search);
//			statement.setString(2, search);
//			statement.setString(3, search);
//			statement.setString(4, search);
//			ResultSet resultSet = statement.executeQuery();
//			while (resultSet.next()) {
//				String IDNV = resultSet.getString(1);
//				String Hoten = resultSet.getString(2);
//				String Diachi = resultSet.getString(3);
//				String IDPB = resultSet.getString(4);
//				nhanviens.add(new Nhanvien(IDNV, Hoten, Diachi, IDPB));
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return nhanviens;
//	}
}
