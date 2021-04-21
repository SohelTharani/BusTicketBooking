package servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Passenger;
import service.PassengerService;
import util.ActionConstants;
import util.SessionUtil;

@WebServlet("/passengers/*")
public class PassengerServlet extends HttpServlet {

	private PassengerService passengerService;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			passengerService = new PassengerService();
			String action = req.getPathInfo();
			if (req.getSession() != null) {
				int userId = SessionUtil.getUserId(req);
				if (action.contains(ActionConstants.LIST)) {
					Passenger[] passengers = passengerService.getAll(userId);
					req.setAttribute("passengers", passengers);
					req.getRequestDispatcher("/passengers.jsp").forward(req, resp);
				}
			} else {
				resp.sendRedirect("/login");
			}
		} catch (Exception e) {
			req.setAttribute("error", e.getMessage());
			req.getRequestDispatcher("/error.jsp").forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			
			passengerService = new PassengerService();
			String action = req.getPathInfo();
			Map<String, String[]> attributes = req.getParameterMap();
			if (req.getSession() != null) {
				int userId = SessionUtil.getUserId(req);
				if (action.contains(ActionConstants.CREATE)) {
					boolean created = passengerService.create(userId, attributes);
					if (created) {
						Passenger[] passengers = passengerService.getAll(userId);
						req.setAttribute("passengers", passengers);
						req.setAttribute("message", "Passenger added successfully!");
						req.getRequestDispatcher("/passengers.jsp").forward(req, resp);
					} else {
						req.setAttribute("error", "Failed to add passenger");
						req.getRequestDispatcher("/passengers.jsp").forward(req, resp);
					}
				}else if(action.contains(ActionConstants.DELETE)) {
					 passengerService.delete(userId, attributes);
					 Passenger[] passengers = passengerService.getAll(userId);
						req.setAttribute("passengers", passengers);
						req.setAttribute("message", "Passenger deleted successfully!");
						req.getRequestDispatcher("/passengers.jsp").forward(req, resp);
				}
			} else {
				resp.sendRedirect("/login");
			}
		} catch (Exception e) {
			req.setAttribute("error", e.getMessage());
			req.getRequestDispatcher("/error.jsp").forward(req, resp);
		}

	}

}
