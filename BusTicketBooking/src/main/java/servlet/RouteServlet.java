package servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Route;
import service.RouteService;
import util.ActionConstants;
import util.SessionUtil;

@WebServlet("/routes/*")
public class RouteServlet extends HttpServlet {

	private RouteService routeService;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			routeService = new RouteService();
			String action = req.getPathInfo();
			if (action.contains(ActionConstants.DESTINATION)) {
				Map<String, String[]> attributes = req.getParameterMap();
				String[] destination = routeService.getDestinationCities(attributes);
				String options = "<option>--Select Destination--</option>";
				for (int i = 0; i < destination.length; i++) {
					options += String.format("<option value='%s'>%s</option>\n", destination[i], destination[i]);
				}
				resp.getWriter().write(options);
				return;
			}
			if (SessionUtil.getUser(req) == null) {
				resp.sendRedirect("/BusTicketBooking/users/login");
			}
			int userId = SessionUtil.getUserId(req);
			if (action.contains(ActionConstants.LIST) && SessionUtil.getUserType(req).equals("admin")) {
				Route[] routes = routeService.getAll();
				req.setAttribute("routes", routes);
				req.getRequestDispatcher("/manageRoute.jsp").forward(req, resp);
			} else {
				resp.sendRedirect("/error/401");
			}

		} catch (Exception e) {
			req.setAttribute("error", e.getMessage());
			req.getRequestDispatcher("/error.jsp").forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (SessionUtil.getUser(req) == null) {
			resp.sendRedirect("/BusTicketBooking/users/login");
		}
		try {
			routeService = new RouteService();
			String action = req.getPathInfo();
			Map<String, String[]> attributes = req.getParameterMap();
			if (req.getSession() != null) {
				int userId = SessionUtil.getUserId(req);
				if (action.contains(ActionConstants.CREATE)) {
					routeService.create(attributes);
					Route[] routes = routeService.getAll();
					req.setAttribute("routes", routes);
					req.setAttribute("message", "Route added successfully");
					req.getRequestDispatcher("/manageRoute.jsp").forward(req, resp);
				} else if (action.contains(ActionConstants.DELETE)) {
					routeService.delete(attributes);
					Route[] routes = routeService.getAll();
					req.setAttribute("routes", routes);
					req.setAttribute("message", "Route deleted successfully");
					req.getRequestDispatcher("/manageRoute.jsp").forward(req, resp);
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
