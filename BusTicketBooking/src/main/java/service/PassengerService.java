package service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;

import javax.annotation.PreDestroy;

import dao.PassengerDao;
import db.DBConnection;
import model.Passenger;
import util.ParameterUtil;

public class PassengerService {

	private Connection connection;
	private PassengerDao passengerDao;
	public PassengerService() throws Exception {
		DBConnection dbConnection = new DBConnection();
		this.connection=dbConnection.getJdbcConnection();	
		this.passengerDao=new PassengerDao(this.connection);
	}
	@PreDestroy
	public void closeConnection() throws SQLException {
		this.connection.close();
	}
	public Passenger[] getAll(int userId) {
		return passengerDao.getAll(userId);
	}
	public boolean create(int userId, Map<String, String[]> attributes) {
		String fullName=ParameterUtil.getValue(attributes, "fullName");
		String email=ParameterUtil.getValue(attributes, "email");
		String gender=ParameterUtil.getValue(attributes, "gender");
		int age=Integer.valueOf(ParameterUtil.getValue(attributes, "age"));
		String mobileNumber=ParameterUtil.getValue(attributes, "mobileNumber");
		
		return passengerDao.create(userId, fullName, email, gender, age, mobileNumber);
	}
	
	public void delete(int userId, Map<String, String[]> attributes) {
		String passengerId = ParameterUtil.getValue(attributes, "passengerId");
		passengerDao.delete(userId, Integer.valueOf(passengerId));
	}
}
