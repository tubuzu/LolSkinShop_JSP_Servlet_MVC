package Model.BO;

//import java.util.ArrayList;
import java.util.List;

import Model.BEAN.Skin;
import Model.DAO.SkinDAO;

public class SkinBO {
	private SkinDAO DAO;
	
	public SkinBO() {
		DAO = new SkinDAO();
	}
	
	public List<Skin> getListSkin() {
		return DAO.getListSkin();
	}
	
	public List<Skin> searchByNameAndTier(String searchName, String searchTier) {
		return DAO.searchByNameAndTier(searchName, searchTier);
	}
	
	public Boolean addSkin(String Name, String Img, int Price, int Tier, String Champion) {
		return DAO.addSkin(Name, Img, Price, Tier, Champion);
	}
	
	public int getSkinPriceById(String Id) {
		return DAO.getSkinPriceById(Id);
	}
	
	public List<Skin> getSkinByID(String ID) {
		return DAO.getSkinByID(ID);
	}
	public List<String> getListID() {
		return DAO.getListID();
	}
	public boolean delete(String ID) {
		return DAO.delete(ID);
	}
	public boolean update(String Id, String Name, String Img, int Price, int Tier, String Champion) {
		return DAO.update(Id, Name, Img, Price, Tier, Champion);
	}
//	
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
