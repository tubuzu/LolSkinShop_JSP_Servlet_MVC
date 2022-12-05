package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.BEAN.User;
import Model.BO.CheckLoginBO;
import Model.BO.UserBO;


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
		int check = checkLoginBO.checkLogin(username, password);
		if (check == 1) {
			UserBO userBO = new UserBO();
			String userID = userBO.getUserIdByUsernamePassword(username, password);
			request.getSession().setAttribute("userID", userID);
		}
		request.getSession().setAttribute("login", check != 0);
		request.getSession().setAttribute("isAdmin", check == 2);
		
		String currentPage = "";
		if(check != 0) {
			currentPage = (String) request.getSession().getAttribute("currentPage");
			if(currentPage == null)
				currentPage = "SkinServlet";
		} else {
			currentPage = "login.jsp";
		}
		response.sendRedirect(currentPage);
	}

}
