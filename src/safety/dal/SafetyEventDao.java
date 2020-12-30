package safety.dal;

import safety.model.*;
import safety.model.SafetyEvent.EventType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 * 
 * @author Team 4
 *
 */
public class SafetyEventDao {
	protected ConnectionManager connectionManager;

	private static SafetyEventDao instance = null;
	protected SafetyEventDao() {
		connectionManager = new ConnectionManager();
	}
	public static SafetyEventDao getInstance() {
		if(instance == null) {
			instance = new SafetyEventDao();
		}
		return instance;
	}

	public SafetyEvent create(SafetyEvent event) throws SQLException {
		String insertevent =
			"INSERT INTO SafetyEvents(Latitude, Longitude, Date, ReportTime, EventType, MCPP) " +
			"VALUES(?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			// SafetyEvent has an auto-generated key. So we want to retrieve that key.
			insertStmt = connection.prepareStatement(insertevent,
				Statement.RETURN_GENERATED_KEYS);
			insertStmt.setDouble(1, event.getLatitude());
			insertStmt.setDouble(2, event.getLongitude());
			insertStmt.setTimestamp(3, new Timestamp(event.getDateTime().getTime()));
			insertStmt.setTimestamp(4, new Timestamp(event.getReportTime().getTime()));
			insertStmt.setString(5, event.getEventType().name());
			insertStmt.setString(6, event.getMCPP());
			insertStmt.executeUpdate();
			
			// Retrieve the auto-generated key and set it, so it can be used by the caller.
			// For more details, see:
			// http://dev.mysql.com/doc/connector-j/en/connector-j-usagenotes-last-insert-id.html
			resultKey = insertStmt.getGeneratedKeys();
			int eventId = -1;
			if(resultKey.next()) {
				eventId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			event.setEventId(eventId);
			return event;
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
			if(resultKey != null) {
				resultKey.close();
			}
		}
	}

	/**
	 * Update the event type of the SafetyEvent instance.
	 * This runs a UPDATE statement.
	 */
	public SafetyEvent updateEventType(SafetyEvent event, EventType eventType) throws SQLException {
		String updateevent = "UPDATE SafetyEvents SET EventType=?,Date=? WHERE EventID=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateevent);
			updateStmt.setString(1, eventType.name());
			// Sets the Created timestamp to the current time.
			Date newCreatedTimestamp = new Date();
			updateStmt.setTimestamp(2, new Timestamp(newCreatedTimestamp.getTime()));
			updateStmt.setInt(3, event.getEventId());
			updateStmt.executeUpdate();

			// Update the event param before returning to the caller.
			event.setEventType(eventType);;
			event.setDateTime(newCreatedTimestamp);
			return event;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(updateStmt != null) {
				updateStmt.close();
			}
		}
	}
	
	/**
	 * Update the MCPP of the SafetyEvent instance.
	 * This runs a UPDATE statement.
	 */
	public SafetyEvent updateMCPP(SafetyEvent event, String MCPP) throws SQLException {
		String updateevent = "UPDATE SafetyEvents SET MCPP=?,Date=? WHERE EventID=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateevent);
			updateStmt.setString(1, MCPP);
			// Sets the Created timestamp to the current time.
			Date newCreatedTimestamp = new Date();
			updateStmt.setTimestamp(2, new Timestamp(newCreatedTimestamp.getTime()));
			updateStmt.setInt(3, event.getEventId());
			updateStmt.executeUpdate();

			// Update the event param before returning to the caller.
			event.setMCPP(MCPP);
			event.setDateTime(newCreatedTimestamp);
			return event;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(updateStmt != null) {
				updateStmt.close();
			}
		}
	}

	/**
	 * Delete the SafetyEvent instance.
	 * This runs a DELETE statement.
	 */
	public SafetyEvent delete(SafetyEvent event) throws SQLException {
		String deleteEvent = "DELETE FROM SafetyEvents WHERE EventID=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteEvent);
			deleteStmt.setInt(1, event.getEventId());
			deleteStmt.executeUpdate();

			// Return null so the caller can no longer operate on the SafetyEvent instance.
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

	public List<SafetyEvent> getSafetyEventByType(EventType type) throws SQLException {
		List<SafetyEvent> SafetyEvents = new ArrayList<SafetyEvent>();
		String selectevent =
			"SELECT EventID, Latitude, Longitude, Date, ReportTime, EventType, MCPP " +
			"FROM SafetyEvents " +
			"WHERE EventType=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectevent);
			selectStmt.setString(1, type.name());
			results = selectStmt.executeQuery();
			while(results.next()) {
				int resultEventId = results.getInt("EventID");
				double lat = results.getDouble("Latitude");
				double lon = results.getDouble("Longitude");
				Date date = new Date(results.getTimestamp("Date").getTime());
				Date reportTime = new Date(results.getTimestamp("ReportTime").getTime());
				EventType eventType = EventType.valueOf(results.getString("EventType"));
				String MCPP = results.getString("MCPP");
				SafetyEvent event = new SafetyEvent(resultEventId, lat, lon, date, reportTime, eventType, MCPP);
				SafetyEvents.add(event);
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
		return SafetyEvents;
	}

	/**
	 * Get the all the SafetyEvent for a user.
	 */
	public List<SafetyEvent> getSafetyEventByMCPP(String MCPP) throws SQLException {
		List<SafetyEvent> SafetyEvents = new ArrayList<SafetyEvent>();
		String selectevent =
			"SELECT EventID, Latitude, Longitude, Date, ReportTime, EventType, MCPP " +
			"FROM SafetyEvents " +
			"WHERE MCPP=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectevent);
			selectStmt.setString(1, MCPP);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int resultEventId = results.getInt("EventID");
				double lat = results.getDouble("Latitude");
				double lon = results.getDouble("Longitude");
				Date date = new Date(results.getTimestamp("Date").getTime());
				Date reportTime = new Date(results.getTimestamp("ReportTime").getTime());
				EventType eventType = EventType.valueOf(results.getString("EventType"));
				String mcpp = results.getString("MCPP");
				SafetyEvent event = new SafetyEvent(resultEventId, lat, lon, date, reportTime, eventType, mcpp);
				SafetyEvents.add(event);
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
		return SafetyEvents;
	}
	
	
	public SafetyEvent getSafetyEventById(int id) throws SQLException {
		// List<SafetyEvent> SafetyEvents = new ArrayList<SafetyEvent>();
		String selectevent =
			"SELECT EventID, Latitude, Longitude, Date, ReportTime, EventType, MCPP " +
			"FROM SafetyEvents " +
			"WHERE EventID=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectevent);
			selectStmt.setInt(1, id);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int resultEventId = results.getInt("EventID");
				double lat = results.getDouble("Latitude");
				double lon = results.getDouble("Longitude");
				Date date = new Date(results.getTimestamp("Date").getTime());
				Date reportTime = new Date(results.getTimestamp("ReportTime").getTime());
				EventType eventType = EventType.valueOf(results.getString("EventType"));
				String MCPP = results.getString("MCPP");
				SafetyEvent event = new SafetyEvent(resultEventId, lat, lon, date, reportTime, eventType, MCPP);
				// SafetyEvents.add(event);
				return event;
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
	
	
	
	
}
