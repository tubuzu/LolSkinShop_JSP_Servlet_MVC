package Model.BO;

import Model.DAO.CheckLoginDAO;

public class CheckLoginBO {
	private CheckLoginDAO checkLoginDAO;
	
	public CheckLoginBO() {
		checkLoginDAO = new CheckLoginDAO();
	}
	
	public int checkLogin(String username, String password) {
		return checkLoginDAO.checkLogin(username, password);
	}
}
