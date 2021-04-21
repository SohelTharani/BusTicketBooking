package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;

import javax.annotation.PreDestroy;

import dao.RouteDao;
import db.DBConnection;
import model.Route;
import util.ParameterUtil;

public class RouteService {

	private Connection connection;
	private RouteDao routeDao;
	public RouteService() throws Exception {
		DBConnection dbConnection = new DBConnection();
		this.connection=dbConnection.getJdbcConnection();	
		this.routeDao=new RouteDao(this.connection);
	}
	@PreDestroy
	public void closeConnection() throws SQLException {
		this.connection.close();
	}
	public Route[] getAll() {
		try(Statement statement = this.connection.createStatement()){
			ResultSet rs = statement.executeQuery("select * from routes");
			ArrayList<Route> routes = new ArrayList<>();
			while(rs.next()) {
				routes.add(new Route(rs.getInt("id"),rs.getString("source"),rs.getString("destination")));
			}
			return routes.toArray(new Route[routes.size()]);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public boolean create(Map<String, String[]> attributes) {
		String source = ParameterUtil.getValue(attributes, "source");
		String destination = ParameterUtil.getValue(attributes, "destination");
		return routeDao.create(source,destination);
	}
	public boolean delete(Map<String, String[]> attributes) {
		String routeId = ParameterUtil.getValue(attributes, "routeId");
		return routeDao.delete(routeId);
	}
	public String[] getSourceCities() {
		String[] sourceCities = routeDao.getSourceCities();
		
		return sourceCities;
	}
	public String[] getDestinationCities(Map<String,String[]> attributes) {
		String source = ParameterUtil.getValue(attributes, "source");
		String[] cities = routeDao.getDestCities(source);
		return cities;
	}
}
