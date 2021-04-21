package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

import javax.annotation.PreDestroy;

import dao.BusDao;
import db.DBConnection;
import model.Bus;
import util.ParameterUtil;

public class BusService {

	private Connection connection;
	private BusDao busDao;
	public BusService() throws Exception {
		DBConnection dbConnection = new DBConnection();
		this.connection=dbConnection.getJdbcConnection();
		this.busDao=new BusDao(this.connection);
	}
	@PreDestroy
	public void closeConnection() throws SQLException {
		this.connection.close();
	}
	public Bus[] getAll() {
		Bus[] buses = busDao.getAll();
		return buses;
	}

	public boolean delete(String busId) {
		return busDao.delete(busId);
	}

	public boolean create(Map<String, String[]> attributes, String userId) {
		String name = ParameterUtil.getValue(attributes, "bname");
		String source = ParameterUtil.getValue(attributes, "source");
		String destination = ParameterUtil.getValue(attributes, "destination");
		String datetime = ParameterUtil.getValue(attributes, "depatureDatetime");
		String price = ParameterUtil.getValue(attributes, "price");
		String seats = ParameterUtil.getValue(attributes, "seats");
		return busDao.create(name,source,destination,datetime,price,seats);	
	}

	public Bus[] searchBuses(Map<String, String[]> attributes) {
		String source = ParameterUtil.getValue(attributes, "source");
		String destination = ParameterUtil.getValue(attributes, "destination");
		LocalDate localDate = LocalDate.parse(ParameterUtil.getValue(attributes, "travelDate"));
		LocalDateTime startofDay = localDate.atStartOfDay();
		LocalDateTime endOfDay = startofDay.plusDays(1);
		Bus[] bus = busDao.searchBuses(source,destination, startofDay,endOfDay);
		return bus;
	}

}
