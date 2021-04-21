package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Bus {

	private int id;
	public Bus(int id, String name, String source, String destination, LocalDateTime time, double price, int seats) {
		super();
		this.id = id;
		this.name = name;
		this.source = source;
		this.destination = destination;
		this.time = time;
		this.price = price;
		this.seats = seats;
	}
	private String name;
	private String source;
	private String destination;
	private LocalDateTime time;
	private double price;
	private int seats;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getTime() {
		return time.format(DateTimeFormatter.ISO_DATE_TIME).split("T")[0]+" "+time.format(DateTimeFormatter.ISO_DATE_TIME).split("T")[1];
	}
	public LocalDateTime getLocalDateTime() {
		return this.time;
	}
	public void setTime(LocalDateTime time) {
		this.time = time;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getSeats() {
		return seats;
	}
	public void setSeats(int seats) {
		this.seats = seats;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	}
