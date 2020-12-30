package safety.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import safety.model.*;

public class RatingsHistoryRecordsDao {
	
	protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
	private static RatingsHistoryRecordsDao instance = null;
	protected RatingsHistoryRecordsDao() {
		connectionManager = new ConnectionManager();
	}
	public static RatingsHistoryRecordsDao getInstance() {
		if(instance == null) {
			instance = new RatingsHistoryRecordsDao();
		}
		return instance;
	 }
	
	/**
	 * Create the RatingHistoryRecordsDao instance.
	 * This runs a DELETE statement.
	 */
	
	public RatingsHistoryRecords create(RatingsHistoryRecords ratingHistoryRecords) throws SQLException {

		String insertRating = "INSERT INTO RatingsHistoryRecords(CurrentRating,SafetyRankingUP,CreatedDate,MCPP,ReportID) VALUES(?,?,?,?,?);";

		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertRating);
			insertStmt.setInt(1, ratingHistoryRecords.getRecordHistoryID());
			insertStmt.setDouble(2, ratingHistoryRecords.getCurrentRating());
			insertStmt.setBoolean(3,ratingHistoryRecords.getSafetyRankingUP());
			insertStmt.setDate(4, (java.sql.Date) ratingHistoryRecords.getCurrentDate());
			insertStmt.setString(5, ratingHistoryRecords.getNeighborhoods().getMCPP());
			insertStmt.setInt(6, ratingHistoryRecords.getRatingReports().getReportId());

			insertStmt.executeUpdate();
			
			resultKey = insertStmt.getGeneratedKeys();
			int recordHistoryID = -1;
			if(resultKey.next()) {
				recordHistoryID = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			
			ratingHistoryRecords.setRecordHistoryID(recordHistoryID);
			return ratingHistoryRecords;
			
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

	
	public List<RatingsHistoryRecords> getRatingHistoryRecordsByReportId(int reportId) throws SQLException {
		List<RatingsHistoryRecords> ratingHistoryRecords = new ArrayList<RatingsHistoryRecords>();
		String selectRatingHistoryRecords =

			"SELECT RecordHistoryID,CurrentRating,SafetyRankingUP,CreatedDate,MCPP,ReportID " +

			"FROM RatingsHistoryRecords " +
			"WHERE ReportID=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRatingHistoryRecords);
			selectStmt.setInt(1, reportId);
			results = selectStmt.executeQuery();
			NeighborhoodsDao neighborhoodsDao = NeighborhoodsDao.getInstance();
			RatingReportsDao ratingReportsDao = RatingReportsDao.getInstance();
			while(results.next()) {
				int recordHistoryID = results.getInt("RecordHistoryID");
				double currentRating = results.getDouble("CurrentRating");
				Boolean safetyRankingUP = results.getBoolean("SafetyRankingUP");

				Date currentDate =  new Date(results.getTimestamp("CreatedDate").getTime());
				Neighborhoods neighborhoods = neighborhoodsDao.getNeighborhoodByMCPP(results.getString("MCPP"));
				RatingReports ratingReports1 = ratingReportsDao.getRatingReportByReportId(reportId);
				
				RatingsHistoryRecords ratingHistoryRecord = new RatingsHistoryRecords(recordHistoryID, currentRating, safetyRankingUP,
						currentDate, neighborhoods, ratingReports1);
				ratingHistoryRecords.add(ratingHistoryRecord);
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
		return ratingHistoryRecords;
	}
	
	
	
	/**
	 * Get the all the RatingHistoryRecords from MCPP.
	 */
	public List<RatingsHistoryRecords> getRatingHistoryRecordsFromMCPP(String mcpp) throws SQLException {
		List<RatingsHistoryRecords> ratingHistoryRecords = new ArrayList<RatingsHistoryRecords>();
		String selectRatingHistoryRecords =

			"SELECT RecordHistoryID,CurrentRating,SafetyRankingUP,CreatedDate,MCPP,ReportID " +

			"FROM RatingsHistoryRecords " +
			"WHERE MCPP=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRatingHistoryRecords);
			selectStmt.setString(1, mcpp);
			results = selectStmt.executeQuery();
			NeighborhoodsDao neighborhoodsDao = NeighborhoodsDao.getInstance();
			RatingReportsDao ratingReportsDao = RatingReportsDao.getInstance();
		
			while(results.next()) {
				int recordHistoryID = results.getInt("RecordHistoryID");
				double currentRating = results.getDouble("CurrentRating");
				Boolean safetyRankingUP = results.getBoolean("SafetyRankingUP");

				Date currentDate =  new Date(results.getTimestamp("CreatedDate").getTime());

				Neighborhoods neighborhoods = neighborhoodsDao.getNeighborhoodByMCPP(results.getString("MCPP"));
				RatingReports ratingReport = ratingReportsDao.getRatingReportByReportId(results.getInt("ReportID"));
				
				RatingsHistoryRecords ratingHistoryRecord = new RatingsHistoryRecords(recordHistoryID, currentRating, safetyRankingUP,
						currentDate, neighborhoods, ratingReport);
				ratingHistoryRecords.add(ratingHistoryRecord);
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
		return ratingHistoryRecords;
	}
	
	public List<RatingsHistoryRecords> getRatingHistoryRecordsByReportIdAndMCPP(int reportId,String mcpp) throws SQLException {
		List<RatingsHistoryRecords> ratingHistoryRecords = new ArrayList<RatingsHistoryRecords>();
		if (reportId == -1 && mcpp == null) {
			return ratingHistoryRecords;
		}
		if (reportId == -1) {
			return this.getRatingHistoryRecordsFromMCPP(mcpp);
		}
		if (mcpp == null) {
			return this.getRatingHistoryRecordsByReportId(reportId);
		}
		String selectRatingHistoryRecords =

			"SELECT RecordHistoryID,CurrentRating,SafetyRankingUP,CreatedDate,MCPP,ReportID " +

			"FROM RatingsHistoryRecords " +
			"WHERE MCPP=? AND ReportID=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRatingHistoryRecords);
			selectStmt.setString(1, mcpp);
			selectStmt.setInt(2,reportId);
			results = selectStmt.executeQuery();
			NeighborhoodsDao neighborhoodsDao = NeighborhoodsDao.getInstance();
			RatingReportsDao ratingReportsDao = RatingReportsDao.getInstance();
		
			while(results.next()) {
				int recordHistoryID = results.getInt("RecordHistoryID");
				double currentRating = results.getDouble("CurrentRating");
				Boolean safetyRankingUP = results.getBoolean("SafetyRankingUP");

				Date currentDate =  new Date(results.getTimestamp("CreatedDate").getTime());

				Neighborhoods neighborhoods = neighborhoodsDao.getNeighborhoodByMCPP(results.getString("MCPP"));
				RatingReports ratingReport = ratingReportsDao.getRatingReportByReportId(results.getInt("ReportID"));
				
				RatingsHistoryRecords ratingHistoryRecord = new RatingsHistoryRecords(recordHistoryID, currentRating, safetyRankingUP,
						currentDate, neighborhoods, ratingReport);
				ratingHistoryRecords.add(ratingHistoryRecord);
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
		return ratingHistoryRecords;
	}
	
	/**
	 * Delete the RatingHistoryRecordsDao instance.
	 * This runs a DELETE statement.
	 */
	public RatingsHistoryRecords delete(RatingsHistoryRecords ratingHistoryRecords) throws SQLException {
		String deletePerson = "DELETE FROM RatingHistoryRecords WHERE RecordHistoryID=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deletePerson);
			deleteStmt.setInt(1, ratingHistoryRecords.getRecordHistoryID());
			deleteStmt.executeUpdate();

			// Return null so the caller can no longer operate on the RatingHistoryRecords instance.
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
