package safety.model;

import java.util.Date;

public class Crimes extends SafetyEvent {
	private Date offenseStartDateTime;
	private Date offenseEndDateTime;
	private CrimeAgainstCategory crimeAgainstCategory;
	private String offense;
	
	
	public enum CrimeAgainstCategory{
		SOCIETY, PROPERTY, PERSON, NOT_A_CRIME
	}

	public Crimes(double latitude, double longitude, Date dateTime, Date reportTime, EventType eventType,
			String mCPP, Date offenseStartDateTime, Date offenseEndDateTime, CrimeAgainstCategory crimeAgainstCategory, String offense) {
		super(latitude, longitude, dateTime, reportTime, eventType, mCPP);
		this.offenseStartDateTime = offenseStartDateTime;
		this.offenseEndDateTime = offenseEndDateTime;
		this.crimeAgainstCategory = crimeAgainstCategory;
		this.offense = offense;
	}


	public Crimes(int eventId, double latitude, double longitude, Date dateTime, Date reportTime, EventType eventType,
			String mCPP, Date offenseStartDateTime, Date offenseEndDateTime, CrimeAgainstCategory crimeAgainstCategory, String offense) {
		super(eventId, latitude, longitude, dateTime, reportTime, eventType, mCPP);
		this.offenseStartDateTime = offenseStartDateTime;
		this.offenseEndDateTime = offenseEndDateTime;
		this.crimeAgainstCategory = crimeAgainstCategory;
		this.offense = offense;
	}


	public Date getOffenseStartDateTime() {
		return offenseStartDateTime;
	}


	public void setOffenseStartDateTime(Date offenseStartDateTime) {
		this.offenseStartDateTime = offenseStartDateTime;
	}


	public Date getOffenseEndDateTime() {
		return offenseEndDateTime;
	}


	public void setOffenseEndDateTime(Date offenseEndDateTime) {
		this.offenseEndDateTime = offenseEndDateTime;
	}

	

	public CrimeAgainstCategory getCrimeAgainstCategory() {
		return crimeAgainstCategory;
	}


	public void setCrimeAgainstCategory(CrimeAgainstCategory crimeAgainstCategory) {
		this.crimeAgainstCategory = crimeAgainstCategory;
	}


	public String getOffense() {
		return offense;
	}


	public void setOffense(String offense) {
		this.offense = offense;
	}
	
	
	
	
	
	

}
