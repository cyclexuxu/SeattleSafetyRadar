package safety.model;

import java.util.Date;

public class Fire extends SafetyEvent{
	private String address;
	private String type;
	public Fire(double latitude, double longitude, Date dateTime, Date reportTime, EventType eventType, String mCPP, String address, String type) {
		super(latitude, longitude, dateTime, reportTime, eventType, mCPP);
		this.address = address;
		this.type = type;
	}
	public Fire(int eventId, double latitude, double longitude, Date dateTime, Date reportTime, EventType eventType,
			String mCPP, String address, String type) {
		super(eventId, latitude, longitude, dateTime, reportTime, eventType, mCPP);
		this.address = address;
		this.type = type;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
