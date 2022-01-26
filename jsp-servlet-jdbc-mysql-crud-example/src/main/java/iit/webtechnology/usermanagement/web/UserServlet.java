package iit.webtechnology.usermanagement.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import iit.webtechnology.usermanagement.dao.UserDAO;
import iit.webtechnology.usermanagement.model.User;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/")

public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	public UserServlet() {
		this.userDAO = new UserDAO();
	}

	private void onload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();
		System.out.println(action);
		switch (action) {
		case "/new":
			showNewForm(request, response);
			break;
		case "/insert":
			try {
				insertUser(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		case "/edit":
			showEditForm(request, response);
			break;
		case "/delete":
			try {
				deleteUser(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		case "/update":
			try {
				updateUser(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		default:
			listUsers(request, response);
			break;
		}

	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("userForm.jsp");
		dispatcher.forward(request, response);
	}

	private void insertUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String country = request.getParameter("country");
		User newUser = new User(name, email, country);
		userDAO.insertUser(newUser);
		response.sendRedirect(request.getContextPath() + "/list");
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		User currentUser = userDAO.selectUser(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("userForm.jsp");
		request.setAttribute("user", currentUser);
		dispatcher.forward(request, response);
	}

	private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		System.out.println(request.getParameter("id"));
		int id = Integer.parseInt(request.getParameter("id"));
		userDAO.deleteUser(id);
		response.sendRedirect(request.getContextPath() + "/list");
	}

	private void updateUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String country = request.getParameter("country");
		int id = Integer.parseInt(request.getParameter("id"));
		User newUser = new User(id, name, email, country);
		userDAO.updateUser(newUser);
		response.sendRedirect(request.getContextPath() + "/list");
	}

	private void listUsers(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<User> users = userDAO.selectAllUsers();
		request.setAttribute("listUser", users);
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
