package Model.BO;

import java.util.ArrayList;
import java.util.List;

import Model.BEAN.Nhanvien;
import Model.DAO.NhanvienDAO;

public class NhanvienBO {
	private NhanvienDAO DAO;
	
	public NhanvienBO() {
		DAO = new NhanvienDAO();
	}
	
	public List<Nhanvien> getListNhanVien() {
		return DAO.getListNhanVien();
	}
	
	public List<Nhanvien> getListNhanVienByIDPB(String IDPB) {
		return DAO.getListNhanVienByIDPB(IDPB);
	}
	public List<String> getListIDNV() {
		return DAO.getListIDNV();
	}
	public boolean delete(String IDNV) {
		return DAO.delete(IDNV);
	}
	
	public List<Nhanvien> search(String searchBy, String searchVal) {
		if(searchBy.equalsIgnoreCase("All")) {
			return DAO.searchAll(searchVal);
		}
		else if(searchBy.equals("")) {
			return DAO.getListNhanVien();
		}
		else {
			return DAO.search(searchBy, searchVal);
		}
	}
}
