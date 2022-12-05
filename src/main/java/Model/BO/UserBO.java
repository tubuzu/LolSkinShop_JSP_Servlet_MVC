package Model.BO;

import java.util.List;

import Model.BEAN.User;
import Model.DAO.UserDAO;

public class UserBO {
	private UserDAO DAO;
	
	public UserBO() {
		DAO = new UserDAO();
	}
	
	public List<User> getListUser() {
		return DAO.getListUser();
	}
	
	public List<User> getUserByID(String ID) {
		return DAO.getUserByID(ID);
	}
	public String getUserIdByUsernamePassword(String Username, String Password) {
		return DAO.getUserIdByUsernamePassword(Username, Password);
	}
	public List<String> getListID() {
		return DAO.getListID();
	}
	public boolean addUser(String Username, String Password) {
		return DAO.addUser(Username, Password);
	}
//	public boolean addSkin(String SkinID, String UserID) {
//		return DAO.addSkin(SkinID, UserID);
//	}
	public boolean incBalance(String ID, int Amount) {
		return DAO.incBalance(ID, Amount);
	}
	public boolean decBalance(String ID, String SkinID) {
		return DAO.decBalance(ID, SkinID);
	}
	public boolean delete(String ID) {
		return DAO.delete(ID);
	}
	
	public boolean update(String Id, String Username, String Password, String Balance) {
		return DAO.update(Id, Username, Password, Balance);
	}
	
	public List<User> searchByUserNameAndMinBalance(String searchUsername, String minBalance) {
		return DAO.searchByUserNameAndMinBalance(searchUsername, minBalance);
	}
	
	
//	public List<Nhanvien> search(String searchBy, String searchVal) {
//		if(searchBy.equalsIgnoreCase("All")) {
//			return DAO.searchAll(searchVal);
//		}
//		else if(searchBy.equals("")) {
//			return DAO.getListNhanVien();
//		}
//		else {
//			return DAO.search(searchBy, searchVal);
//		}
//	}
}
