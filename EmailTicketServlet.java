package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Booking;
import model.User;
import model.UserDetails;
import service.BookingService;
import service.UserService;
import util.ActionConstants;
import util.EmailUtility;
import util.SessionUtil;

@WebServlet("/emailticket/*")
public class EmailTicketServlet extends HttpServlet {

	private BookingService bookingService;
	private UserService usersvc;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			bookingService = new BookingService();
			String ticketId = req.getPathInfo();
			int userId = SessionUtil.getUserId(req);
			if (userId >0) {
				Booking booking = bookingService.get(userId, Integer.valueOf(ticketId.substring(1)));
				req.setAttribute("booking", booking);
				UserDetails u =usersvc.getUser(userId);
				String body = "Your tickect booked ";
				//u.getEmail()
				EmailUtility.sendEmail("remsi0763@gmail.com", "Ticket Booking", body); 
				
				
				
				//req.getRequestDispatcher("/viewticket.jsp").forward(req, resp);
			} else {
				resp.sendRedirect("/BusTicketBooking/users/login");
			}
		} catch (Exception e) {
			req.setAttribute("error", e.getMessage());
			req.getRequestDispatcher("/error.jsp").forward(req, resp);
		}
	}

}
