package Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.BEAN.Skin;
//import Model.BEAN.Phongban;
import Model.BO.SkinBO;
import Model.BO.UserBO;

@WebServlet("/SkinServlet")
public class SkinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SkinBO skinBO;

	public SkinServlet() {
		skinBO = new SkinBO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action == null)
			action = "";
		switch (action) {
		case "guiAdd":
			showGuiAdd(request, response);
			break;
		case "guiManage":
			showGuiManage(request, response);
			break;
		case "guiUpdate":
			showGuiUpdate(request, response);
			break;
		default:
			getListSkin(request, response);
			break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action == null)
			action = "";
		switch (action) {
		case "add":
			addSkin(request, response);
			break;
		case "update":
			update(request, response);
			break;
		case "deleteMany":
			deleteMany(request, response);
			break;
		case "guiManage":
			showGuiManage(request, response);
			break;
		default:
			getListSkin(request, response);
			break;
		}
	}

	public void getListSkin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String searchName = "";
		String searchTier = "";
		if (request.getParameter("Name") != null) {
			searchName = request.getParameter("Name");
		}
		if (request.getParameter("Tier") != null) {
			searchTier = request.getParameter("Tier");
		}
		List<Skin> skins = skinBO.getListSkin();
		if (searchName.length() > 0 || searchTier.length() > 0) {
			skins = skinBO.searchByNameAndTier(searchName, searchTier);
		}
		request.setAttribute("skins", skins);
		boolean isLogin = false;
		boolean isAdmin = false;
		if (request.getSession().getAttribute("login") != null
				&& request.getSession().getAttribute("isAdmin") != null) {
			isLogin = (boolean) request.getSession().getAttribute("login");
			isAdmin = (boolean) request.getSession().getAttribute("isAdmin");
		}
		if (isLogin && !isAdmin) {
			UserBO userBO = new UserBO();
			int balance = userBO.getUserByID(request.getSession().getAttribute("userID").toString()).get(0).getBalance();
			request.setAttribute("balance", balance);
		}
		request.getRequestDispatcher("xemskin.jsp").forward(request, response);
	}

	private void addSkin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean isLogin = false;
		boolean isAdmin = false;
		if (request.getSession().getAttribute("login") != null
				&& request.getSession().getAttribute("isAdmin") != null) {
			isLogin = (boolean) request.getSession().getAttribute("login");
			isAdmin = (boolean) request.getSession().getAttribute("isAdmin");
		}
		if (isLogin && isAdmin) {
			String Name = request.getParameter("Name");
			String Img = request.getParameter("Img");
			int Price = Integer.parseInt(request.getParameter("Price"));
			int Tier = Integer.parseInt(request.getParameter("Tier"));
			String Champion = request.getParameter("Champion");
			boolean isSuccess = skinBO.addSkin(Name, Img, Price, Tier, Champion);
			System.out.println(isSuccess);
			if (isSuccess) {
				response.sendRedirect("SkinServlet?action=guiAdd");
			}
		} else if (isLogin) {
			response.sendRedirect("SkinServlet");
		} else
			response.sendRedirect("login.jsp");
//		request.getRequestDispatcher("xemskin.jsp").forward(request, response);
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
			response.sendRedirect("addSkinForm.jsp");
		} else if (isLogin) {
			request.setAttribute("currentPage", "SkinServlet?action=guiAdd");
			response.sendRedirect("SkinServlet");
		} else {
			request.setAttribute("currentPage", "SkinServlet?action=guiAdd");
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
			String ID = request.getParameter("updateSkinID");
			List<Skin> skins = skinBO.getSkinByID(ID);
			if (skins.size() > 0) {
				request.setAttribute("skin", skins.get(0));
				request.getRequestDispatcher("updateSkinForm.jsp").forward(request, response);
			} else {
				request.setAttribute("currentPage", "SkinServlet?action=guiUpdate");
				response.sendRedirect("SkinServlet?action=guiManage");
			}
//			response.sendRedirect("addSkinForm.jsp");
		} else if (isLogin) {
			request.setAttribute("currentPage", "SkinServlet?action=guiAdd");
			response.sendRedirect("SkinServlet");
		} else {
			request.setAttribute("currentPage", "SkinServlet?action=guiAdd");
			response.sendRedirect("login.jsp");
		}
	}

	private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if (request.getParameter("skinID") == null || request.getParameter("Name") == null
				|| request.getParameter("Img") == null || request.getParameter("Price") == null
				|| request.getParameter("Tier") == null || request.getParameter("Champion") == null) {
			System.out.println(request.getParameter("skinID") + request.getParameter("Name") + request.getParameter("Img") + request.getParameter("Price") + request.getParameter("Tier") + request.getParameter("Champion"));
			response.sendRedirect("SkinServlet?action=guiManage");
		} else {
			System.out.println("updategui");
			String skinID = request.getParameter("skinID");
			String Name = request.getParameter("Name");
			String Img = request.getParameter("Img");
			String Price = request.getParameter("Price");
			String Tier = request.getParameter("Tier");
			String Champion = request.getParameter("Champion");
			boolean isSuccess = skinBO.update(skinID, Name, Img, Integer.parseInt(Price), Integer.parseInt(Tier),
					Champion);
			System.out.println(isSuccess);
			if (isSuccess) {
				response.sendRedirect("SkinServlet?action=guiManage");
			} else
				response.sendRedirect("SkinServlet?action=guiManage");
		}
	}

	private void deleteMany(HttpServletRequest request, HttpServletResponse response) throws IOException {
		List<String> IDs = skinBO.getListID();
		for (String ID : IDs) {
			String temp = request.getParameter(ID);
			if (temp != null) {
				skinBO.delete(ID);
			}
		}
		response.sendRedirect("SkinServlet?action=guiManage");
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
			String searchName = "";
			String searchTier = "";
			if (request.getParameter("skinName") != null) {
				searchName = request.getParameter("skinName");
			}
			if (request.getParameter("skinTier") != null) {
				searchTier = request.getParameter("skinTier");
			}
			List<Skin> skins = skinBO.getListSkin();
			if (searchName.length() > 0 || searchTier.length() > 0) {
				skins = skinBO.searchByNameAndTier(searchName, searchTier);
			}
			request.setAttribute("skins", skins);
			request.getRequestDispatcher("manageSkin.jsp").forward(request, response);
		} else if (isLogin) {
			request.setAttribute("currentPage", "SkinServlet");
			response.sendRedirect("SkinServlet");
		} else {
			request.setAttribute("currentPage", "SkinServlet");
			response.sendRedirect("login.jsp");
		}
	}
}
