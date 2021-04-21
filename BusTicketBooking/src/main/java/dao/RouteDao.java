package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class RouteDao {
	private Connection connection;
	public RouteDao(Connection connection) {
		this.connection=connection;
	}
	public String[] getSourceCities() {
		try(Statement statement = this.connection.createStatement()){
			ResultSet rs = statement.executeQuery("select source from routes");
			ArrayList<String> cities = new ArrayList<>();
			while(rs.next()) {
				cities.add(rs.getString("source"));
			}
			return cities.toArray(new String[cities.size()]);
		} catch (SQLException e) {
			e.printStackTrace();
			return new String[0];
		}
	}
	public String[] getDestCities(String source) {
		try(Statement statement = this.connection.createStatement()){
			ResultSet rs = statement.executeQuery("select destination from routes where source='"+source+"'");
			ArrayList<String> cities = new ArrayList<>();
			while(rs.next()) {
				cities.add(rs.getString("destination"));
			}
			return cities.toArray(new String[cities.size()]);
		} catch (SQLException e) {
			e.printStackTrace();
			return new String[0];
		}
	}
	public boolean delete(String routeId) {
		try(PreparedStatement statement = this.connection.prepareStatement("delete from routes where id=?")){
			statement.setInt(1, Integer.valueOf(routeId));
			return statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	public boolean create(String source, String destination) {
		try(PreparedStatement statement = this.connection.prepareStatement("insert into routes (source, destination) values (?,?)")){
			statement.setString(1, source);
			statement.setString(2,destination);
			return statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}
