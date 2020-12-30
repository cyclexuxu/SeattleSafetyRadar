package safety.dal;

import safety.model.*;
import safety.model.SafetyEvent.EventType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FireDao extends SafetyEventDao{
	private static FireDao instance = null;
	protected FireDao() {
		super();
	}
	public static FireDao getInstance() {
		if(instance == null) {
			instance = new FireDao();
		}
		return instance;
	}

	public Fire create(Fire fireEvent) throws SQLException {
		SafetyEvent event = create(new SafetyEvent(fireEvent.getLatitude(), fireEvent.getLongitude(), fireEvent.getDateTime(),
				fireEvent.getReportTime(), fireEvent.getEventType(), fireEvent.getMCPP()));
		String insertevent =
			"INSERT INTO Fires(EventID, Address, Type) " +
			"VALUES(?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			// Fire has an auto-generated key. So we want to retrieve that key.
			insertStmt = connection.prepareStatement(insertevent);
			insertStmt.setInt(1, event.getEventId());
			insertStmt.setString(2, fireEvent.getAddress());
			insertStmt.setString(3, fireEvent.getType());
			insertStmt.executeUpdate();
			
			int eventId = event.getEventId();
			fireEvent.setEventId(eventId);
			return fireEvent;
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
	 * Update the event type of the Fire instance.
	 * This runs a UPDATE statement.
	 */
	public Fire updateType(Fire fireEvent, String type) throws SQLException {
		// update the date in safety events
		this.updateEventType(new SafetyEvent(fireEvent.getEventId(), fireEvent.getLatitude(), fireEvent.getLongitude(), fireEvent.getDateTime(),
				fireEvent.getReportTime(), fireEvent.getEventType(), fireEvent.getMCPP()), fireEvent.getEventType());
		// update the type in fire
		String updateevent = "UPDATE Fires SET Type=? WHERE EventID=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateevent);
			updateStmt.setString(1, type);
			// Sets the Created timestamp to the current time.
			updateStmt.setInt(2, fireEvent.getEventId());
			updateStmt.executeUpdate();

			// Update the event param before returning to the caller.
			fireEvent.setType(type);
			return fireEvent;
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
	 * Update the MCPP of the Fire instance.
	 * This runs a UPDATE statement.
	 */
	public Fire updateAddress(Fire fireEvent, String address) throws SQLException {
		// update the date in safety events
		this.updateEventType(new SafetyEvent(fireEvent.getEventId(), fireEvent.getLatitude(), fireEvent.getLongitude(), fireEvent.getDateTime(),
				fireEvent.getReportTime(), fireEvent.getEventType(), fireEvent.getMCPP()), fireEvent.getEventType());
		// update the type in fire
		String updateevent = "UPDATE Fires SET Address=? WHERE EventID=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateevent);
			updateStmt.setString(1, address);
			// Sets the Created timestamp to the current time.
			updateStmt.setInt(2, fireEvent.getEventId());
			updateStmt.executeUpdate();

			// Update the event param before returning to the caller.
			fireEvent.setAddress(address);
			return fireEvent;
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
	 * Delete the Fire instance.
	 * This runs a DELETE statement.
	 */
	public Fire delete(Fire event) throws SQLException {
		String deleteEvent = "DELETE FROM Fires WHERE EventID=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteEvent);
			deleteStmt.setInt(1, event.getEventId());
			deleteStmt.executeUpdate();

			super.delete(event);
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

	public List<Fire> getFireByType(String type) throws SQLException {
		List<Fire> Fires = new ArrayList<Fire>();
		String selectevent =
			"SELECT Fires.EventID AS EventID, Latitude, Longitude, Date, ReportTime, EventType, MCPP, Address, Type " +
			"FROM Fires INNER JOIN SafetyEvents " +
			"  ON Fires.EventID = SafetyEvents.EventID " +
			"WHERE Type=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectevent);
			selectStmt.setString(1, type);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int resultEventId = results.getInt("EventID");
				double lat = results.getDouble("Latitude");
				double lon = results.getDouble("Longitude");
				Date date = new Date(results.getTimestamp("Date").getTime());
				Date reportTime = new Date(results.getTimestamp("ReportTime").getTime());
				EventType eventType = EventType.valueOf(results.getString("EventType"));
				String MCPP = results.getString("MCPP");
				String address = results.getString("Address");
				String type_fire = results.getString("Type");
				Fire event = new Fire(resultEventId, lat, lon, date, reportTime, eventType, MCPP, address, type_fire);
				Fires.add(event);
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
		return Fires;
	}

	/**
	 * Get the all the Fire for a user.
	 */
	public List<Fire> getFireByAddress(String address) throws SQLException {
		List<Fire> Fires = new ArrayList<Fire>();
		String selectevent =
			"SELECT Fires.EventID AS EventID, Latitude, Longitude, Date, ReportTime, EventType, MCPP, Address, Type " +
			"FROM Fires INNER JOIN SafetyEvents " +
			"  ON Fires.EventID = SafetyEvents.EventID " +
			"WHERE Address=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectevent);
			selectStmt.setString(1, address);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int resultEventId = results.getInt("EventID");
				double lat = results.getDouble("Latitude");
				double lon = results.getDouble("Longitude");
				Date date = new Date(results.getTimestamp("Date").getTime());
				Date reportTime = new Date(results.getTimestamp("ReportTime").getTime());
				EventType eventType = EventType.valueOf(results.getString("EventType"));
				String MCPP = results.getString("MCPP");
				String address_fire = results.getString("Address");
				String type_fire = results.getString("Type");
				Fire event = new Fire(resultEventId, lat, lon, date, reportTime, eventType, MCPP, address_fire, type_fire);
				Fires.add(event);
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
		return Fires;
	}
}
