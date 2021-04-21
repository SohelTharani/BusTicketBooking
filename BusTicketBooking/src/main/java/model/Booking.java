package model;

public class Booking {
	public Booking(int id, String busDate, String busName, String passengerName, double price) {
		super();
		this.id = id;
		this.busDate = busDate;
		this.busName = busName;
		this.passengerName = passengerName;
		this.price = price;
	}
	private int id;
	private String busDate;
	private String busName;
	private String passengerName;
	private double price;
	private String source;
	private String destination;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBusDate() {
		return busDate;
	}
	public void setBusDate(String busDate) {
		this.busDate = busDate;
	}
	public String getBusName() {
		return busName;
	}
	public void setBusName(String busName) {
		this.busName = busName;
	}
	public String getPassengerName() {
		return passengerName;
	}
	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
}
