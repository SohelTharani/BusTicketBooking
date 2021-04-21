package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.User;
import model.UserDetails;

public class UserDao {

	private Connection connection;

	public UserDao(Connection connection) {
		this.connection = connection;
	}

	public int register(String username, String password, String email, String mobileNumber, String userType) {
		int userId = 0;
		try (PreparedStatement statement = this.connection.prepareStatement(
				"insert into users (userName,email,password,userType,mobile) values (?,?,?,?,?)",
				Statement.RETURN_GENERATED_KEYS)) {
			statement.setString(1, username);
			statement.setString(2, email);
			statement.setString(3, password);
			statement.setString(4, userType);
			statement.setString(5, mobileNumber);
			statement.execute();
			ResultSet rs = statement.getGeneratedKeys();
			if (rs.next()) {
				userId = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return userId;
		}
		return userId;
	}

	public User login(String username, String password) {
		try (PreparedStatement statement = this.connection
				.prepareStatement("select userId,userName, email, mobile,userType from users where email=? and password=?")) {
			statement.setString(1, username);
			statement.setString(2, password);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("userId"));
				user.setEmail(rs.getString("email"));
				user.setUserType(rs.getString("userType"));
				user.setFullName(rs.getString("userName"));
				user.setMobileNumber(rs.getString("mobile"));
				return user;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public UserDetails updateUser(String username, String mobileNumber, int userId) {
		UserDetails user = new UserDetails();
		try (PreparedStatement statement = this.connection.prepareStatement(
				"update users set username=?, mobile=? where userId=?")) {
			statement.setString(1, username);
			statement.setString(2, mobileNumber);
			statement.setInt(3, userId);
			int updated = statement.executeUpdate();
			user.setFullName(username);
			user.setMobileNumber(mobileNumber);
			user.setId(userId);
		} catch (SQLException e) {
			e.printStackTrace();
			return user;
		}
		return user;
	}

	public UserDetails getUser(Integer userId) {
		try (PreparedStatement statement = this.connection
				.prepareStatement("select userName, email, mobile,userType from users where userId=?")) {
			statement.setInt(1, userId);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				UserDetails user = new UserDetails();
				user.setEmail(rs.getString("email"));
				user.setFullName(rs.getString("userName"));
				user.setMobileNumber(rs.getString("mobile"));
				return user;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public UserDetails getUserByEmail(String emailId) {
		try (PreparedStatement statement = this.connection
				.prepareStatement("select userName, email, mobile,userType from users where email=?")) {
			statement.setString(1, emailId);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				UserDetails user = new UserDetails();
				user.setEmail(rs.getString("email"));
				user.setFullName(rs.getString("userName"));
				user.setMobileNumber(rs.getString("mobile"));
				return user;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
