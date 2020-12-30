package safety.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;


import safety.model.*;

public class RatingReportsDao {
	
	protected ConnectionManager connectionManager;

	// Single pattern: instantiation is limited to one object.
	private static RatingReportsDao instance = null;
	protected RatingReportsDao() {
		connectionManager = new ConnectionManager();
	}
	public static RatingReportsDao getInstance() {
		if(instance == null) {
			instance = new RatingReportsDao();
		}
		return instance;
	}

	public RatingReports create(RatingReports ratingReport) throws SQLException {
		String insertRatingReport = "INSERT INTO RatingReports(CreatedDate) VALUES(?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertRatingReport);
			insertStmt.setTimestamp(1, new Timestamp(ratingReport.getCreatedDate().getTime()));
			
			insertStmt.executeUpdate();
			
			resultKey = insertStmt.getGeneratedKeys();
			int reportId = -1;
			if(resultKey.next()) {
				reportId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			ratingReport.setReportId(reportId);
			return ratingReport;
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(insertStmt != null) {
				insertStmt.close();
			}
		}
	}
	
	public RatingReports getRatingReportByReportId(int reportId) throws SQLException {
		String selectRatingReport =
				"SELECT ReportID,CreatedDate " +
				"FROM RatingReports " +
				"WHERE ReportID=?;";
			Connection connection = null;
			PreparedStatement selectStmt = null;
			ResultSet results = null;
			try {
				connection = connectionManager.getConnection();
				selectStmt = connection.prepareStatement(selectRatingReport);
				selectStmt.setInt(1, reportId);
				results = selectStmt.executeQuery();
				
				if(results.next()) {
					int resultReportId = results.getInt("ReportID");
					Date createdDate =  new Date(results.getTimestamp("CreatedDate").getTime());	
					
					RatingReports ratingReport = new RatingReports(resultReportId,createdDate);
					return ratingReport;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw e;
			} finally {
				if(connection != null) {
					connection.close();
				}
				if(selectStmt != null) {
					selectStmt.close();
				}
				if(results != null) {
					results.close();
				}
			}
			return null;
	}
	
	public RatingReports getRatingReportByCreatedDate(Date created) throws SQLException {
		String selectRatingReport =
				"SELECT ReportID,CreatedDate " +
				"FROM RatingReports " +
				"WHERE CreatedDate='" + String.valueOf(created.getYear()+1900) + "-" + String.valueOf(created.getMonth()+1) + "-" + String.valueOf(created.getDate()+1) + "'";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRatingReport);
			//selectStmt.setTimestamp(1, new Timestamp(created.getTime()));
			results = selectStmt.executeQuery();
		
			if (results.next()) {
				int resultReportId = results.getInt("ReportID");
				Date createdDate =  new Date(results.getTimestamp("CreatedDate").getTime());	
				
				RatingReports ratingReport = new RatingReports(resultReportId,createdDate);
				
				return ratingReport;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return null;
		
	}
	
	public RatingReports delete(RatingReports ratingReport) throws SQLException {
		String deleteRatingReport = "DELETE FROM RatingReports WHERE ReportID=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteRatingReport);
			deleteStmt.setInt(1, ratingReport.getReportId());
			int affectedRows = deleteStmt.executeUpdate();
			if (affectedRows == 0) {
				throw new SQLException("No records available to delete for RatingID=" + ratingReport.getReportId());
			}
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(deleteStmt != null) {
				deleteStmt.close();
			}
		}
	}
		
	
}
