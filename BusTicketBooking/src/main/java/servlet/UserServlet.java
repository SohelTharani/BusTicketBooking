package servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;
import model.UserDetails;
import service.UserService;
import util.ActionConstants;
import util.SessionUtil;

@WebServlet("/users/*")
public class UserServlet extends HttpServlet{

	private static final long serialVersionUID = 7302381689834867912L;
	private UserService userService;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			userService = new UserService();
			String action = req.getPathInfo();
			if(action.contains(ActionConstants.GET)) {
				if (SessionUtil.getUser(req) == null) {
					resp.sendRedirect("/BusTicketBooking/users/login");
					return;
				}
				int userId = SessionUtil.getUserId(req);
				UserDetails userDetails = userService.getUser(userId);
				req.setAttribute("user", userDetails);
				req.getRequestDispatcher("/updateprofile.jsp").forward(req,resp);
			}else if(action.contains(ActionConstants.LOGIN)) {
				req.getRequestDispatcher("/login.jsp").forward(req, resp);
			}else if(action.contains(ActionConstants.REGISTER)) {
				req.getRequestDispatcher("/register.jsp").forward(req, resp);
			}else if(action.contains(ActionConstants.LOGOUT)) {
				req.getSession().removeAttribute("user");
				resp.sendRedirect("/BusTicketBooking/users/login");
			}
		} catch (Exception e) {
			req.setAttribute("error", e.getMessage());
			req.getRequestDispatcher("/error.jsp").forward(req, resp);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			userService = new UserService();
			String action = req.getPathInfo();
			Map<String,String[]> paramMap = req.getParameterMap();
			if(action.contains(ActionConstants.UPDATE)) {
				int userId = SessionUtil.getUserId(req);
				UserDetails userDetails = userService.updateUser(userId,paramMap);
				req.setAttribute("user", userDetails);
				req.setAttribute("message", "User details updated successfully");
				req.getRequestDispatcher("/updateprofile.jsp").forward(req,resp);
			}else if(action.contains(ActionConstants.LOGIN)) {
				User user = userService.login(paramMap);
				if(user!=null) {
					req.getSession().setAttribute("user", user);
					if(user.getUserType().equals("user"))
						resp.sendRedirect("/BusTicketBooking/buses/search");
					else
						resp.sendRedirect("/BusTicketBooking/buses/list");
				}else {
					req.setAttribute("error", "Username or password incorrect");
				}
			}else if(action.contains(ActionConstants.REGISTER)) {
				boolean register = userService.register(paramMap);
				if(register) {
				req.setAttribute("message", "Registration successful");
				req.getRequestDispatcher("/login.jsp").forward(req, resp);
				}else {
					req.setAttribute("error", "Please enter valid values for all fields.Email ID must be unique");
					req.getRequestDispatcher("/register.jsp").forward(req, resp);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("error", e.getMessage());
			req.getRequestDispatcher("/error.jsp").forward(req, resp);
		}
		
	}
	
}
