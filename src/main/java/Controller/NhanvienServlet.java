package Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.BEAN.Nhanvien;
import Model.BEAN.Phongban;
import Model.BO.NhanvienBO;

@WebServlet("/NhanvienServlet")
public class NhanvienServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private NhanvienBO nhanvienBO;
    public NhanvienServlet() {
    	nhanvienBO = new NhanvienBO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if(action == null) action = "";
		switch (action) {
			case "guiDelete1":
				showGuiDelete1(request, response);
				break;
			case "delete1":
				delete(request, response);
				break;
			case "guiDeleteMany":
				guiDeleteMany(request, response);
				break;
			case "search":
				guiSearch(request, response);
				break;
			default:
				getListNhanVien(request, response);
				break;
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if(action == null) action = "";
		switch (action) {
			case "deleteMany":
				deleteMany(request, response);
				break;
		}
	}


	public void getListNhanVien(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Nhanvien> nhanviens = nhanvienBO.getListNhanVien();
		request.setAttribute("nhanviens", nhanviens);
		request.getRequestDispatcher("xemnhanvien.jsp").forward(request, response);	
	}
	

	private void showGuiDelete1(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean isLogin = false;
		if(request.getSession().getAttribute("login") != null) {
			isLogin = (boolean) request.getSession().getAttribute("login");
		}
		
		if(isLogin) {
			List<Nhanvien> nhanviens = nhanvienBO.getListNhanVien();
			request.setAttribute("nhanviens", nhanviens);
			request.getRequestDispatcher("xoamotnhanvien.jsp").forward(request, response);
		}
		else {
			request.setAttribute("currentPage", "PhongbanServlet?action=guiDelete1");
			response.sendRedirect("login.jsp");
		}
	}
	
	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String IDNV = request.getParameter("IDNV");
		boolean isSuccess = nhanvienBO.delete(IDNV);
		System.out.println(isSuccess);
		if(isSuccess) {
			response.sendRedirect("NhanvienServlet?action=guiDelete1");
		}
	}
	
	private void guiDeleteMany(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean isLogin = false;
		if(request.getSession().getAttribute("login") != null) {
			isLogin = (boolean) request.getSession().getAttribute("login");
		}
		
		if(isLogin) {
			List<Nhanvien> nhanviens = nhanvienBO.getListNhanVien();
			request.setAttribute("nhanviens", nhanviens);
			request.getRequestDispatcher("xoanhieunhanvien.jsp").forward(request, response);
		}
		else {
			request.setAttribute("currentPage", "PhongbanServlet?action=guiDeleteMany");
			response.sendRedirect("login.jsp");
		}
	}
	
	private void deleteMany(HttpServletRequest request, HttpServletResponse response) throws IOException {
		List<String> IDNVs = nhanvienBO.getListIDNV();
		for(String IDNV : IDNVs) {
			String temp = request.getParameter(IDNV);
			if(temp != null) {
				nhanvienBO.delete(IDNV);
			}
		}
		response.sendRedirect("NhanvienServlet?action=guiDeleteMany");
	}

	private void guiSearch(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		List<Nhanvien> nhanviens = new ArrayList<>();
		String searchBy = request.getParameter("searchBy");
		String searchVal = request.getParameter("searchVal");
		if(searchBy == null) {
			nhanviens = nhanvienBO.search("", "");
		} else nhanviens = nhanvienBO.search(searchBy, searchVal);
		request.setAttribute("nhanviens", nhanviens);
		request.getRequestDispatcher("search.jsp").forward(request, response);
	}
}
