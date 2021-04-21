package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Passenger;

public class PassengerDao {

	private Connection connection;
	public PassengerDao(Connection connection) {
		this.connection=connection;
	}
	public Passenger[] getAll(int userId) {
		try(Statement stmt = this.connection.createStatement()){
			ResultSet rs = stmt.executeQuery("select * from passenger where userId="+userId);
			ArrayList<Passenger> passengers = new ArrayList<>();
			while(rs.next()) {
				passengers.add(new Passenger(rs.getInt("id"),rs.getString("passengerName"),rs.getString("email"),rs.getString("gender"),rs.getInt("age"),rs.getString("mobile")));
			}
			return passengers.toArray(new Passenger[passengers.size()]);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new Passenger[0];
	}
	
	public boolean create(int userId, String fullName, String email, String gender, int age, String mobileNumber) {
		try(PreparedStatement stmt = this.connection.prepareStatement("insert into passenger (passengerName, email, gender, age,mobile,userId) values (?,?,?,?,?,?)")){
			stmt.setString(1, fullName);
			stmt.setString(2, email);
			stmt.setString(3, gender);
			stmt.setInt(4,age);
			stmt.setString(5, mobileNumber);
			stmt.setInt(6, userId);
			stmt.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean delete(int userId, int passengerId) {
		try(PreparedStatement stmt = this.connection.prepareStatement("delete from passenger where userid=? and id=?")){
			stmt.setInt(1, userId);
			stmt.setInt(2, passengerId);
			return stmt.executeUpdate()>0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}
