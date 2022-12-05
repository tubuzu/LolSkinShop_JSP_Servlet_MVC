package Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.BEAN.User;
import Model.BO.UserBO;
import Model.BO.TransactionBO;

@WebServlet("/TransactionServlet")
public class TransactionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TransactionBO transactionBO;

	public TransactionServlet() {
		transactionBO = new TransactionBO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action == null)
			action = "";
		switch (action) {
//		case "buySkin":
//			showGuiRecharge(request, response);
//			break;
		default:
			break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action == null)
			action = "";
		switch (action) {
		case "buySkin":
			addTransaction(request, response);
			break;
		default:
			break;
		}
	}

//	public void getListUser(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		List<User> users = userBO.getListUser();
//		request.setAttribute("users", users);
//		request.getRequestDispatcher("xemUser.jsp").forward(request, response);
//	}
	
	private void addTransaction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean isLogin = false;
		boolean isAdmin = false;
		if (request.getSession().getAttribute("login") != null
				&& request.getSession().getAttribute("isAdmin") != null) {
			isLogin = (boolean) request.getSession().getAttribute("login");
			isAdmin = (boolean) request.getSession().getAttribute("isAdmin");
		}
		if (isLogin && !isAdmin) {
			String SkinId = request.getParameter("Id");
			String UserId = (String) request.getSession().getAttribute("userID");
			boolean isSuccess = transactionBO.addTransaction(SkinId, UserId);
			System.out.println(isSuccess);
			if (isSuccess) {
				UserBO userBO = new UserBO();
				userBO.decBalance(UserId, SkinId);
				response.sendRedirect("SkinServlet");
			}
		} else if (isAdmin) {
			response.sendRedirect("SkinServlet");
		} else
			response.sendRedirect("login.jsp");
	}
}
