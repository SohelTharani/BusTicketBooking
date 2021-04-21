package servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Booking;
import model.Passenger;
import service.BookingService;
import service.PassengerService;
import util.ActionConstants;
import util.ParameterUtil;
import util.SessionUtil;

@WebServlet("/bookings/*")
public class BookingServlet extends HttpServlet {

	private static final long serialVersionUID = -8770733897532609126L;

	private BookingService bookingService;
	private PassengerService passengerService;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			bookingService = new BookingService();
			passengerService = new PassengerService();
			String action = req.getPathInfo();
			int userId = SessionUtil.getUserId(req);

			if (action.contains(ActionConstants.LIST)) {
				Booking[] bookings = bookingService.getAll(userId);
				req.setAttribute("bookings", bookings);
				req.getRequestDispatcher("/bookings.jsp").forward(req, resp);
			} else if (action.contains(ActionConstants.CREATE)) {
				Map<String, String[]> attributes = req.getParameterMap();
				req.getSession().setAttribute("busId", ParameterUtil.getValue(attributes, "busId"));
				req.getSession().setAttribute("price", ParameterUtil.getValue(attributes, "price"));
				Passenger[] passenger = passengerService.getAll(userId);
				req.setAttribute("passengers", passenger);
				req.getRequestDispatcher("/bookingTicket.jsp").forward(req, resp);
			} else if (action.contains(ActionConstants.DELETE)) {
				String bookingId = req.getParameter("bookingId");
				boolean success = bookingService.delete(userId, bookingId);
				if (success) {
					Booking[] bookings = bookingService.getAll(userId);
					req.setAttribute("bookings", bookings);
					req.setAttribute("message", "Your reservation has been cancelled successfully");
					req.getRequestDispatcher("/bookings.jsp").forward(req, resp);
				} else {
					Booking[] bookings = bookingService.getAll(userId);
					req.setAttribute("bookings", bookings);
					req.setAttribute("message", "Failed to cancel the reservation");
					req.getRequestDispatcher("/bookings.jsp").forward(req, resp);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			bookingService = new BookingService();

			Map<String, String[]> attributes = req.getParameterMap();
			String action = req.getPathInfo();
			if (req.getSession() != null) {
				int userId = SessionUtil.getUserId(req);

				bookingService.create(attributes, userId);
				Booking[] bookings = bookingService.getAll(userId);
				req.setAttribute("bookings", bookings);
				req.setAttribute("message", "Your ticket has been booked successfully");
				req.getRequestDispatcher("/bookings.jsp").forward(req, resp);

			} else {
				resp.sendRedirect("/login");
			}
		} catch (Exception e) {
			req.setAttribute("error", e.getMessage());
			req.getRequestDispatcher("/error.jsp").forward(req, resp);
		}
	}

}
