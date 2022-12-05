package Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.BEAN.User;
import Model.BEAN.Transaction;
import Model.BO.UserBO;
import Model.BO.TransactionBO;

@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserBO userBO;

	public UserServlet() {
		userBO = new UserBO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action == null)
			action = "";
		switch (action) {
		case "guiRecharge":
			showGuiRecharge(request, response);
			break;
		case "guiAdd":
			showGuiAdd(request, response);
			break;
		case "guiManage":
			showGuiManage(request, response);
			break;
		case "guiUpdate":
			showGuiUpdate(request, response);
			break;
		case "guiMySkin":
			showGuiMySkin(request, response);
			break;
		default:
			getListUser(request, response);
			break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action == null)
			action = "";
		switch (action) {
		case "guiManage":
			showGuiManage(request, response);
			break;
		case "add":
			addUser(request, response);
			break;
		case "incBalance":
			incBalance(request, response);
			break;
		case "deleteMany":
			deleteMany(request, response);
			break;
		case "update":
			update(request, response);
			break;
		default:
			break;
		}
	}

	public void getListUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<User> users = userBO.getListUser();
		request.setAttribute("users", users);
		request.getRequestDispatcher("xemUser.jsp").forward(request, response);
	}
	
	private void showGuiAdd(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean isLogin = false;
		boolean isAdmin = false;
		if (request.getSession().getAttribute("login") != null
				&& request.getSession().getAttribute("isAdmin") != null) {
			isLogin = (boolean) request.getSession().getAttribute("login");
			isAdmin = (boolean) request.getSession().getAttribute("isAdmin");
		}

		if (isLogin && isAdmin) {
//			request.getRequestDispatcher("addSkinForm.jsp").forward(request, response);
			response.sendRedirect("addUserForm.jsp");
		} else if (isLogin) {
			request.setAttribute("currentPage", "UserServlet?action=guiAdd");
			response.sendRedirect("SkinServlet");
		} else {
			request.setAttribute("currentPage", "UserServlet?action=guiAdd");
			response.sendRedirect("login.jsp");
		}
	}

	private void addUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean isLogin = false;
		boolean isAdmin = false;
		if (request.getSession().getAttribute("login") != null
				&& request.getSession().getAttribute("isAdmin") != null) {
			isLogin = (boolean) request.getSession().getAttribute("login");
			isAdmin = (boolean) request.getSession().getAttribute("isAdmin");
		}
		if (isLogin && isAdmin) {
			String Username = request.getParameter("Username");
			String Password = request.getParameter("Password");
			boolean isSuccess = userBO.addUser(Username, Password);
			System.out.println(isSuccess);
			if (isSuccess) {
				response.sendRedirect("UserServlet?action=guiAdd");
			}
		} else if (isLogin) {
			response.sendRedirect("SkinServlet");
		} else
			response.sendRedirect("login.jsp");
	}

	private void incBalance(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean isLogin = false;
		boolean isAdmin = false;
		if (request.getSession().getAttribute("login") != null
				&& request.getSession().getAttribute("isAdmin") != null) {
			isLogin = (boolean) request.getSession().getAttribute("login");
			isAdmin = (boolean) request.getSession().getAttribute("isAdmin");
		}

		if (isLogin && !isAdmin) {
			String ID = (String) request.getSession().getAttribute("userID");
			int Amount = Integer.parseInt(request.getParameter("Amount"));
			boolean isSuccess = userBO.incBalance(ID, Amount);
			System.out.println(isSuccess);
			if (isSuccess) {
				response.sendRedirect("SkinServlet");
			}
		} else if (isAdmin) {
			request.setAttribute("currentPage", "UserServlet?action=guiRecharge");
			response.sendRedirect("SkinServlet");
		} else {
			request.setAttribute("currentPage", "UserServlet?action=guiRecharge");
			response.sendRedirect("login.jsp");
		}
	}

	private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if (request.getParameter("userID") == null || request.getParameter("Username") == null ||  request.getParameter("Password") == null || request.getParameter("Balance") == null) {
			System.out.println(request.getParameter("userID") + request.getParameter("Username") + request.getParameter("Password") + request.getParameter("Price") + request.getParameter("Balance"));
		} else {
			System.out.println("updategui");
			String userID = request.getParameter("userID");
			String Username = request.getParameter("Username");
			String Password = request.getParameter("Password");
			String Balance = request.getParameter("Balance");
			boolean isSuccess = userBO.update(userID, Username, Password, Balance);
			System.out.println(isSuccess);
		}
		response.sendRedirect("UserServlet?action=guiManage");
	}
	
//	private void decBalance(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		boolean isLogin = false;
//		boolean isAdmin = false;
//		if (request.getSession().getAttribute("login") != null
//				&& request.getSession().getAttribute("isAdmin") != null) {
//			isLogin = (boolean) request.getSession().getAttribute("login");
//			isAdmin = (boolean) request.getSession().getAttribute("isAdmin");
//		}
//
//		if (isLogin && !isAdmin) {
//			String SkinID = request.getParameter("Id");
//			String ID = (String) request.getSession().getAttribute("userID");
//			boolean isSuccess = userBO.decBalance(ID, SkinID);
//			System.out.println(isSuccess);
//			if (isSuccess) {
//				response.sendRedirect("SkinServlet");
//			}
//		} else if (isAdmin) {
//			response.sendRedirect("SkinServlet");
//		} else {
//			request.setAttribute("currentPage", "SkinServlet");
//			response.sendRedirect("login.jsp");
//		}
//	}
	
	private void deleteMany(HttpServletRequest request, HttpServletResponse response) throws IOException {
		List<String> IDs = userBO.getListID();
		for (String ID : IDs) {
			String temp = request.getParameter(ID);
			if (temp != null) {
				userBO.delete(ID);
			}
		}
		response.sendRedirect("UserServlet?action=guiManage");
	}

	private void showGuiRecharge(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean isLogin = false;
		boolean isAdmin = false;
		if (request.getSession().getAttribute("login") != null
				&& request.getSession().getAttribute("isAdmin") != null) {
			isLogin = (boolean) request.getSession().getAttribute("login");
			isAdmin = (boolean) request.getSession().getAttribute("isAdmin");
		}

		if (isLogin && !isAdmin) {
			request.getRequestDispatcher("naptien.jsp").forward(request, response);
		} else if (isAdmin) {
			request.setAttribute("currentPage", "UserServlet?action=guiRecharge");
			response.sendRedirect("SkinServlet");
		} else {
			request.setAttribute("currentPage", "UserServlet?action=guiRecharge");
			response.sendRedirect("login.jsp");
		}
	}
	
	private void showGuiManage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean isLogin = false;
		boolean isAdmin = false;
		if (request.getSession().getAttribute("login") != null
				&& request.getSession().getAttribute("isAdmin") != null) {
			isLogin = (boolean) request.getSession().getAttribute("login");
			isAdmin = (boolean) request.getSession().getAttribute("isAdmin");
		}
		if (isLogin && isAdmin) {
			String searchUsername = "";
			String minBalance = "";
			if (request.getParameter("searchUsername") != null) {
				searchUsername = request.getParameter("searchUsername");
			}
			if (request.getParameter("minBalance") != null) {
				minBalance = request.getParameter("minBalance");
			}
			List<User> users = userBO.getListUser();
			if (searchUsername.length() > 0 || minBalance.length() > 0) {
				users = userBO.searchByUserNameAndMinBalance(searchUsername, minBalance);
			}
			request.setAttribute("users", users);
			request.getRequestDispatcher("manageUser.jsp").forward(request, response);
		} else if (isLogin) {
			request.setAttribute("currentPage", "SkinServlet");
			response.sendRedirect("SkinServlet");
		} else {
			request.setAttribute("currentPage", "SkinServlet");
			response.sendRedirect("login.jsp");
		}
	}
	
	private void showGuiMySkin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean isLogin = false;
		boolean isAdmin = false;
		if (request.getSession().getAttribute("login") != null
				&& request.getSession().getAttribute("isAdmin") != null) {
			isLogin = (boolean) request.getSession().getAttribute("login");
			isAdmin = (boolean) request.getSession().getAttribute("isAdmin");
		}
		if (isLogin && !isAdmin) {
			TransactionBO transactionBO = new TransactionBO();
			String userID = (String) request.getSession().getAttribute("userID");
			List<Transaction> transactions = transactionBO.getListTransactionByUserID(userID);
			request.setAttribute("transactions", transactions);
			request.getRequestDispatcher("myskin.jsp").forward(request, response);
		} else if (isLogin) {
			request.setAttribute("currentPage", "UserServlet?action=guiMySkin");
			response.sendRedirect("SkinServlet");
		} else {
			request.setAttribute("currentPage", "UserServlet?action=guiMySkin");
			response.sendRedirect("login.jsp");
		}
	}
	
	
	private void showGuiUpdate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean isLogin = false;
		boolean isAdmin = false;
		if (request.getSession().getAttribute("login") != null
				&& request.getSession().getAttribute("isAdmin") != null) {
			isLogin = (boolean) request.getSession().getAttribute("login");
			isAdmin = (boolean) request.getSession().getAttribute("isAdmin");
		}

		if (isLogin && isAdmin) {
			String ID = request.getParameter("updateUserID");
			List<User> users = userBO.getUserByID(ID);
			if (users.size() > 0) {
				request.setAttribute("user", users.get(0));
				request.getRequestDispatcher("updateUserForm.jsp").forward(request, response);
			} else {
				request.setAttribute("currentPage", "UserServlet?action=guiUpdate");
				response.sendRedirect("UserServlet?action=guiManage");
			}
		} else if (isLogin) {
			request.setAttribute("currentPage", "UserServlet?action=guiUpdate");
			response.sendRedirect("SkinServlet");
		} else {
			request.setAttribute("currentPage", "UserServlet?action=guiUpdate");
			response.sendRedirect("login.jsp");
		}
	}
}
