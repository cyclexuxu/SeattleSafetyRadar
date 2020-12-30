package safety.model;

import java.util.Date;

public class SafetyEvent {
	private int eventId;
	private double latitude;
	private double longitude;
	private Date dateTime;
	private Date reportTime;
	private EventType eventType;
	private String MCPP;
	
	
	public enum EventType{
		CRIME, FIRE, POLICE_SHOOTING
	}

	// need to update MCPP to type Neighborhood object!!!
	public SafetyEvent(int eventId, double latitude, double longitude, Date dateTime, Date reportTime,
			EventType eventType, String mCPP) {
		this.eventId = eventId;
		this.latitude = latitude;
		this.longitude = longitude;
		this.dateTime = dateTime;
		this.reportTime = reportTime;
		this.eventType = eventType;
		MCPP = mCPP;
	}
	
	public SafetyEvent(double latitude, double longitude, Date dateTime, Date reportTime,
			EventType eventType, String mCPP) {
		this.latitude = latitude;
		this.longitude = longitude;
		this.dateTime = dateTime;
		this.reportTime = reportTime;
		this.eventType = eventType;
		MCPP = mCPP;
	}

	public SafetyEvent(int eventId) {
		super();
		this.eventId = eventId;
	}

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public Date getReportTime() {
		return reportTime;
	}

	public void setReportTime(Date reportTime) {
		this.reportTime = reportTime;
	}

	public EventType getEventType() {
		return eventType;
	}

	public void setEventType(EventType eventType) {
		this.eventType = eventType;
	}

	public String getMCPP() {
		return MCPP;
	}

	public void setMCPP(String mCPP) {
		MCPP = mCPP;
	}
	
	
}
