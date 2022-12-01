package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.BO.CheckLoginBO;


@WebServlet("/CheckLoginServlet")
public class CheckLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CheckLoginServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		CheckLoginBO checkLoginBO = new CheckLoginBO();
		boolean check = checkLoginBO.checkLogin(username, password);
		request.getSession().setAttribute("login", check);
		
		String currentPage = "";
		if(check) {
			currentPage = (String) request.getSession().getAttribute("currentPage");
			if(currentPage == null)
				currentPage = "PhongbanServlet";
		} else {
			currentPage = "login.jsp";
		}
		response.sendRedirect(currentPage);
	}

}
