package Model.BO;

import java.util.List;

import Model.BEAN.Phongban;
import Model.DAO.PhongbanDAO;

public class PhongbanBO {
private PhongbanDAO DAO;
	
	public PhongbanBO() {
		DAO = new PhongbanDAO();
	}
	
	public List<Phongban> getListPhongBan() {
		return DAO.getListPhongBan();
	}
	public Phongban getPhongBanByID(String IDPB) {
		return DAO.getPhongBanByID(IDPB);
	}
	
	public boolean update(String IDPB, String Tenpb, String Mota) {
		return DAO.update(IDPB, Tenpb, Mota);
	}
	public boolean delete(String IDPB) {
		return DAO.delete(IDPB);
	}
}
