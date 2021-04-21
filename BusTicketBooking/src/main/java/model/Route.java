package model;

public class Route {

	private int id;
	private String source;
	private String destination;
	public Route(int int1, String string, String string2) {
		this.id = int1;
		this.source=string;
		this.destination=string2;
	}
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
}
