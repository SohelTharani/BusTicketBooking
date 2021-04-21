package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import javax.annotation.PreDestroy;

import dao.UserDao;
import db.DBConnection;
import model.User;
import model.UserDetails;
import util.EmailUtility;
import util.ParameterUtil;

public class UserService {

	private Connection connection;
	private UserDao userDao;
	public UserService() throws Exception {
		DBConnection dbConnection = new DBConnection();
		this.connection=dbConnection.getJdbcConnection();	
		this.userDao= new UserDao(this.connection);
	}
	@PreDestroy
	public void closeConnection() throws SQLException {
		this.connection.close();
	}
	public UserDetails getUser(int userId) {
		UserDetails user =userDao.getUser(Integer.valueOf(userId));
		return user;
	}
	public UserDetails updateUser(int userId, Map<String, String[]> paramMap) {
		String username = ParameterUtil.getValue(paramMap, "username");
		String password = ParameterUtil.getValue(paramMap, "password");
		String mobileNumber = ParameterUtil.getValue(paramMap, "mobile");
		UserDetails user = userDao.updateUser(username,mobileNumber,userId);
		return user;
	}
	public User login(Map<String, String[]> paramMap) {
		String email = ParameterUtil.getValue(paramMap, "email");
		String password = ParameterUtil.getValue(paramMap, "password");
		if(email==null||password==null) {
			return null;
		}
		User user = userDao.login(email,password);

		return user;
	}
	public boolean register(Map<String, String[]> paramMap) {
		String username = ParameterUtil.getValue(paramMap, "username");
		String password = ParameterUtil.getValue(paramMap, "password");
		String email = ParameterUtil.getValue(paramMap, "email");
		String mobileNumber = ParameterUtil.getValue(paramMap, "mobile");
		String userType = "user";
		if(userDao.getUserByEmail(email)!=null) {
			return false;
		}
		if(username==null || password==null||email==null||mobileNumber==null) {
			return false;
		}
		int userId = userDao.register(username,password,email,mobileNumber,userType);
		if(userId>0) {
			return true;
		}
		String message= "<h1> User Registration successfully.</h1><br></p>You can login using the email: "+email;
		String subject = "Registration confirmation";
		EmailUtility.sendEmail(email, subject, message);
		return false;
	}
}
