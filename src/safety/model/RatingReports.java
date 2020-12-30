package safety.model;

import java.util.Date;

public class RatingReports {
	
	protected int reportId;
	protected Date createdDate;
	
	public RatingReports(int reportId, Date createdDate) {
		this.reportId = reportId;
		this.createdDate = createdDate;
	}

	public RatingReports(Date createdDate) {
		this.createdDate = createdDate;
	}

	public int getReportId() {
		return reportId;
	}

	public void setReportId(int reportId) {
		this.reportId = reportId;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

}
