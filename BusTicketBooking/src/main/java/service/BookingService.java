package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

import javax.annotation.PreDestroy;

import dao.BookingDao;
import dao.BusDao;
import db.DBConnection;
import model.Booking;
import model.Bus;
import util.ParameterUtil;

public class BookingService {

	private Connection connection;
	private BookingDao bookingDao;
	private BusDao busDao;
	public BookingService() throws Exception {
		DBConnection dbConnection = new DBConnection();
		this.connection=dbConnection.getJdbcConnection();	
		this.bookingDao = new BookingDao(this.connection);
		this.busDao=new BusDao(this.connection);
	}
	@PreDestroy
	public void closeConnection() throws SQLException {
		this.connection.close();
	}
	public Booking[] getAll(int userId) {
		return bookingDao.getAll(userId);
	}

	public void create(Map<String, String[]> attributes, int userId) {
		int busId = Integer.valueOf(ParameterUtil.getValue(attributes, "busId"));
		LocalDate bookingDate = LocalDate.now();
		Bus bus = busDao.getBus(busId);
		LocalDateTime busDate = bus.getLocalDateTime();
		int passengerId = Integer.valueOf(ParameterUtil.getValue(attributes, "passenger"));
		double price = Double.valueOf(ParameterUtil.getValue(attributes, "price"));
		bookingDao.create(busId,userId,busDate,bookingDate,passengerId,price);
	}

	public boolean delete(int userId, String bookingId) {
		return bookingDao.delete(userId,bookingId);		
	}
	public Booking get(int userId, int ticketId) {
		Booking booking = bookingDao.get(userId,ticketId);
		return booking;
	}

}
