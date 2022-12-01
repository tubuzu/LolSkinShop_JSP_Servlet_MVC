package Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.BEAN.Nhanvien;
import Model.BEAN.Phongban;
import Model.BO.NhanvienBO;
import Model.BO.PhongbanBO;

@WebServlet("/PhongbanServlet")
public class PhongbanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private PhongbanBO phongbanBO;
    private NhanvienBO nhanvienBO;
    public PhongbanServlet() {
    	phongbanBO = new PhongbanBO();
    	nhanvienBO = new NhanvienBO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if(action == null) action = "";
		switch(action) {
			case "detail":
				getNhanviensByIDPB(request, response);
				break;
			case "update":
				showFormUpdate(request, response);
				break;
			default:
				getListPhongBan(request, response);
		}
	}


	private void showFormUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean isLogin = false;
		if(request.getSession().getAttribute("login") != null) {
			isLogin = (boolean) request.getSession().getAttribute("login");
		}
		
		if(isLogin) {
			String IDPB = (String) request.getParameter("IDPB");
			String notify = "";
			Phongban phongban = phongbanBO.getPhongBanByID(IDPB);
			request.setAttribute("notify", notify);
			request.setAttribute("phongban", phongban);
			request.getRequestDispatcher("capnhatphongban.jsp").forward(request, response);
		}
		else {
			request.setAttribute("currentPage", "PhongbanServlet?action=update");
			response.sendRedirect("login.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if(action == null) action = "";
		switch(action) {
			case "update":
				System.out.println("update");
				update(request, response);
				break;
			default:
				getListPhongBan(request, response);
		}
	}
	
	private void getListPhongBan(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Phongban> phongbans = phongbanBO.getListPhongBan();
		request.setAttribute("phongbans", phongbans);
		request.getRequestDispatcher("xemphongban.jsp").forward(request, response);
	}
	
	private void getNhanviensByIDPB(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String IDPB = request.getParameter("IDPB");
		List<Nhanvien> nhanviens = nhanvienBO.getListNhanVienByIDPB(IDPB);
		request.setAttribute("nhanviens", nhanviens);
		request.getRequestDispatcher("xemnhanvien.jsp").forward(request, response);
	}
	
	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String IDPB = request.getParameter("IDPB");
		String Tenpb = request.getParameter("Tenpb");
		String Mota = request.getParameter("Mota");
		System.out.println(IDPB);
		System.out.println(Tenpb);
		boolean isSuccess = phongbanBO.update(IDPB, Tenpb, Mota);
		if(isSuccess) {
			getListPhongBan(request, response);
		} else {
			Phongban phongban = new Phongban(IDPB, Tenpb, Mota);
			String notify = "Cập nhật thất bại !!!";
			request.setAttribute("notify", notify);
			request.setAttribute("phongban", phongban);
			request.getRequestDispatcher("capnhatphongban.jsp").forward(request, response);
		}
	}
}
