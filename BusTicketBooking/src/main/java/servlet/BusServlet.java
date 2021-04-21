package servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Bus;
import model.User;
import service.BusService;
import service.RouteService;
import util.ActionConstants;
import util.ParameterUtil;

@WebServlet("/buses/*")
public class BusServlet extends HttpServlet {

	private BusService busService;
	private RouteService routeService;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			busService = new BusService();
			routeService = new RouteService();
			String action = req.getPathInfo();
			
			Map<String, String[]> attributes = req.getParameterMap();
			if (action.contains(ActionConstants.SEARCH)) {
				String[] fromCities = routeService.getSourceCities();
				req.setAttribute("source", fromCities);
				
				req.getRequestDispatcher("/searchbus.jsp").forward(req, resp);
			} else if (action.contains(ActionConstants.LIST)) {
				if(req.getSession().getAttribute("user")!=null && ((User)req.getSession().getAttribute("user")).getUserType().equals("admin")) {
					Bus[] bus = busService.getAll();
					req.setAttribute("buses", bus);
					String[] sources = routeService.getSourceCities();
					req.setAttribute("source", sources);
					req.getRequestDispatcher("/manageBuses.jsp").forward(req, resp);
				}else {
					resp.sendRedirect("/BusTicketBooking/users/login");
				}
				
			} else {
				resp.sendRedirect("/404.jsp");
			}
		} catch (Exception e) {
			req.setAttribute("error", e.getMessage());
			req.getRequestDispatcher("/error.jsp").forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			busService = new BusService();
			Map<String, String[]> attributes = req.getParameterMap();
			if (req.getSession() != null) {
				String userId = req.getSession().getAttribute("user").toString();
				String action = req.getPathInfo();
				if (action.contains(ActionConstants.SEARCH)){
					Bus[] buses = busService.searchBuses(attributes);
					req.setAttribute("buses", buses);
					String[] fromCities = routeService.getSourceCities();
					req.setAttribute("source", fromCities);
					
					if (buses.length == 0) {
						req.setAttribute("message", "No bus found");
					}
					req.getRequestDispatcher("/searchbus.jsp").forward(req, resp);
				}else if(action.contains(ActionConstants.DELETE)) {
					String busId = req.getParameter("busId");
					boolean success = busService.delete(busId);
					if (success) {
						Bus[] buses = busService.getAll();
						req.setAttribute("buses", buses);
						req.setAttribute("message", "The bus has been deleted successfully");
						req.getRequestDispatcher("/manageBuses.jsp").forward(req, resp);
					} else {
						Bus[] buses = busService.getAll();
						req.setAttribute("buses", buses);
						req.setAttribute("message", "Failed to delete the bus");
						req.getRequestDispatcher("/manageBuses.jsp").forward(req, resp);
					}
				}
				else {
					busService.create(attributes, userId);
					Bus[] buses = busService.getAll();
					req.setAttribute("buses", buses);
					req.setAttribute("message", "Bus added successfully");
					req.getRequestDispatcher("/manageBuses.jsp").forward(req, resp);
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
