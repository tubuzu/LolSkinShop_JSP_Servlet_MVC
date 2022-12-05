package Model.BO;

import java.util.List;

import Model.BEAN.Transaction;
import Model.DAO.TransactionDAO;

public class TransactionBO {
	private TransactionDAO DAO;
	
	public TransactionBO() {
		DAO = new TransactionDAO();
	}
	
	public List<Transaction> getListTransaction() {
		return DAO.getListTransaction();
	}
	
	public List<Transaction> getListTransactionByUserID(String ID) {
		return DAO.getListTransactionByUserID(ID);
	}
	public Transaction getTransactionByID(String ID) {
		return DAO.getTransactionByID(ID);
	}
	public boolean addTransaction(String SkinId, String UserId) {
		return DAO.addTransaction(SkinId, UserId);
	}
//	public boolean delete(String ID) {
//		return DAO.delete(ID);
//	}
}
