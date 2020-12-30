package safety.model;

import java.util.Date;

public class RatingsHistoryRecords {
	protected int recordHistoryID;
	protected double currentRating;
	protected Boolean safetyRankingUP;
	protected Date currentDate;
	protected Neighborhoods neighborhoods;
	protected RatingReports ratingReports;
	
	public RatingsHistoryRecords(int recordHistoryID, double currentRating, Boolean safetyRankingUP, Date currentDate,
			Neighborhoods neighborhoods, RatingReports ratingReports) {
		this.recordHistoryID = recordHistoryID;
		this.currentRating = currentRating;
		this.safetyRankingUP = safetyRankingUP;
		this.currentDate = currentDate;
		this.neighborhoods = neighborhoods;
		this.ratingReports = ratingReports;
	}
	
	public RatingsHistoryRecords(double currentRating, Boolean safetyRankingUP, Date currentDate,
			Neighborhoods neighborhoods, RatingReports ratingReports) {
		this.currentRating = currentRating;
		this.safetyRankingUP = safetyRankingUP;
		this.currentDate = currentDate;
		this.neighborhoods = neighborhoods;
		this.ratingReports = ratingReports;
	}

	/** Getters and setters. */
	public int getRecordHistoryID() {
		return recordHistoryID;
	}

	public void setRecordHistoryID(int recordHistoryID) {
		this.recordHistoryID = recordHistoryID;
	}

	public double getCurrentRating() {
		return currentRating;
	}

	public void setCurrentRating(double currentRating) {
		this.currentRating = currentRating;
	}

	public Boolean getSafetyRankingUP() {
		return safetyRankingUP;
	}

	public void setSafetyRankingUP(Boolean safetyRankingUP) {
		this.safetyRankingUP = safetyRankingUP;
	}

	public Date getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(Date currentDate) {
		this.currentDate = currentDate;
	}

	public Neighborhoods getNeighborhoods() {
		return neighborhoods;
	}

	public void setNeighborhood(Neighborhoods neighborhoods) {
		this.neighborhoods = neighborhoods;
	}

	public RatingReports getRatingReports() {
		return ratingReports;
	}

	public void setRatingReports(RatingReports ratingReports) {
		this.ratingReports = ratingReports;
	}
	

}

