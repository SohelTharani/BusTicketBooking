package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

import model.Bus;

public class BusDao {

	private Connection connection;
	public BusDao(Connection connection) {
		this.connection=connection;
	}
	public boolean create(String name, String source, String destination, String datetime, String price, String seats) {
		try(PreparedStatement statement = this.connection.prepareStatement("insert into buses (busName, source, destination, departureTime, seats,price) values (?,?,?,?,?,?)")){
			statement.setString(1, name);
			statement.setString(2, source);
			statement.setString(3, destination);
			statement.setTimestamp(4, Timestamp.valueOf(LocalDateTime.parse(datetime)));
			statement.setDouble(6,Double.valueOf(price));
			statement.setInt(5, Integer.valueOf(seats));
			return statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	public Bus[] getAll() {
		try(Statement statement=this.connection.createStatement()){
			ResultSet rs = statement.executeQuery("select * from buses");
			ArrayList<Bus> buses = new ArrayList<Bus>();
			
			while(rs.next()) {
				buses.add(new Bus(rs.getInt("id"),rs.getString("busName"),rs.getString("source"),rs.getString("destination"), rs.getTimestamp("departureTime").toLocalDateTime(),rs.getDouble("price"),rs.getInt("seats")));
			}
			return buses.toArray(new Bus[buses.size()]);
		}catch (SQLException e) {
			e.printStackTrace();
			return new Bus[0];
		}
	}
	
	public Bus getBus(int busId) {
		try(Statement statement=this.connection.createStatement()){
			ResultSet rs = statement.executeQuery("select * from buses where id="+busId);
			
			while(rs.next()) {
				return new Bus(rs.getInt("id"),rs.getString("busName"),rs.getString("source"),rs.getString("destination"), rs.getTimestamp("departureTime").toLocalDateTime(),rs.getDouble("price"),rs.getInt("seats"));
			}
			return null;
		}catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	public boolean delete(String busId) {
		try(PreparedStatement statement = this.connection.prepareStatement("delete from buses where id=?")){
			statement.setInt(1, Integer.valueOf(busId));
			return statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	public Bus[] searchBuses(String source, String destination, LocalDateTime startofDay, LocalDateTime endOfDay) {
		try(PreparedStatement statement=this.connection.prepareStatement("select * from buses where source=? and destination=? and departureTime<? and departureTime>?")){
			statement.setString(1, source);
			statement.setString(2, destination);
			statement.setTimestamp(3, Timestamp.valueOf(endOfDay));
			statement.setTimestamp(4, Timestamp.valueOf(startofDay));
			ResultSet rs = statement.executeQuery();
			ArrayList<Bus> buses = new ArrayList<Bus>();
			while(rs.next()) {
				buses.add(new Bus(rs.getInt("id"),rs.getString("busName"),rs.getString("source"),rs.getString("destination"), rs.getTimestamp("departureTime").toLocalDateTime(),rs.getDouble("price"),rs.getInt("seats")));
			}
			return buses.toArray(new Bus[buses.size()]);
		}catch (SQLException e) {
			e.printStackTrace();
			return new Bus[0];
		}
	}
}
