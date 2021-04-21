package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Booking;
import service.BookingService;
import util.ActionConstants;
import util.SessionUtil;

@WebServlet("/viewticket/*")
public class ViewTicketServlet extends HttpServlet {

	private BookingService bookingService;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			bookingService = new BookingService();
			String ticketId = req.getPathInfo();
			int userId = SessionUtil.getUserId(req);
			if (userId >0) {
				Booking booking = bookingService.get(userId, Integer.valueOf(ticketId.substring(1)));
				req.setAttribute("booking", booking);
				req.getRequestDispatcher("/viewticket.jsp").forward(req, resp);
			} else {
				resp.sendRedirect("/BusTicketBooking/users/login");
			}
		} catch (Exception e) {
			req.setAttribute("error", e.getMessage());
			req.getRequestDispatcher("/error.jsp").forward(req, resp);
		}
	}

}
