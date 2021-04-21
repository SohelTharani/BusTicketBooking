package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import model.Booking;

public class BookingDao {

	private Connection connection;
	public BookingDao(Connection connection) {
		this.connection=connection;
	}
	public boolean create(int busId, int userId, LocalDateTime busDate, LocalDate bookingDate, int passengerId, double price) {
		try(PreparedStatement stmt = this.connection.prepareStatement("insert into booking (busId,userId, passengerId, bookingDate, busDate,price) values (?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS)){
			stmt.setInt(1, busId);
			stmt.setInt(2, userId);
			stmt.setInt(3, passengerId);
			stmt.setTimestamp(4, Timestamp.valueOf(bookingDate.atStartOfDay()));
			stmt.setTimestamp(5,Timestamp.valueOf(busDate));
			stmt.setDouble(6,price);
			stmt.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public Booking[] getAll(int userId) {
		try(PreparedStatement stmt = this.connection.prepareStatement("select b.bookingId as id,b.busDate as busDate,bs.busName as busName,p.passengerName as passengerName,b.price as price from booking b, passenger p, buses bs where b.userId=? and b.busId =bs.id and p.id=b.passengerId ;")){
			stmt.setInt(1, userId);
			ResultSet rs = stmt.executeQuery();
			ArrayList<Booking> bookings = new ArrayList<Booking>();
			
			while(rs.next()) {
				bookings.add(new Booking(rs.getInt("id"),rs.getTimestamp("busDate").toLocalDateTime().format(DateTimeFormatter.ISO_DATE_TIME),rs.getString("busName"),rs.getString("passengerName"),rs.getDouble("price")));
			}
			return bookings.toArray(new Booking[bookings.size()]);
		} catch (SQLException e) {
			e.printStackTrace();
			return new Booking[0];
		}
	}
	public Booking get(int userId, int bookingId) {
		try(PreparedStatement stmt = this.connection.prepareStatement("select b.bookingId as id,b.busDate as busDate,bs.busName as busName, bs.source as source, bs.destination as destination,p.passengerName as passengerName,b.price as price from booking b, passenger p, buses bs where b.bookingId= ? and b.userId=? and b.busId =bs.id and p.id=b.passengerId ;")){
			stmt.setInt(2, userId);
			stmt.setInt(1, bookingId);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				Booking booking = new Booking(rs.getInt("id"),rs.getTimestamp("busDate").toLocalDateTime().format(DateTimeFormatter.ISO_DATE_TIME),rs.getString("busName"),rs.getString("passengerName"),rs.getDouble("price"));
				booking.setSource(rs.getString("source"));
				booking.setDestination(rs.getString("destination"));
				return booking;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public boolean delete(int userId, String bookingId) {
		try(PreparedStatement stmt = this.connection.prepareStatement("delete from booking where userId = ? and bookingId = ?")){
			stmt.setInt(1, userId);
			stmt.setInt(2, Integer.valueOf(bookingId));
			stmt.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}
