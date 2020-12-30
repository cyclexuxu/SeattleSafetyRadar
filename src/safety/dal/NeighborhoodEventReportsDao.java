package safety.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import safety.model.NeighborhoodEventReports;

public class NeighborhoodEventReportsDao {
	
	protected ConnectionManager connectionManager;

    // Single pattern: instantiation is limited to one object.
    private static NeighborhoodEventReportsDao instance = null;

    protected NeighborhoodEventReportsDao() {
        connectionManager = new ConnectionManager();
    }

    public static NeighborhoodEventReportsDao getInstance() {
        if (instance == null) {
            instance = new NeighborhoodEventReportsDao();
        }
        return instance;
    }
    
    public NeighborhoodEventReports getNeighborhoodEventTypeReportByMCPP(String MCPP) throws SQLException {
        
        String selectMostFreqEvent = "SELECT EventType "
        + "FROM SafetyEvents "
        + "WHERE MCPP=? "
        + "GROUP BY EventType "
        + "ORDER BY COUNT(*) DESC "
        + "LIMIT 1;";
        
        String selectLeastFreqEvent = "SELECT EventType "
                + "FROM SafetyEvents "
                + "WHERE MCPP=? "
                + "GROUP BY EventType "
                + "ORDER BY COUNT(*) ASC "
                + "LIMIT 1;";
        
        Connection connection = null;
        PreparedStatement selectStmt = null;
        PreparedStatement selectStmt2 = null;
        ResultSet results = null;
        ResultSet results2 = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectMostFreqEvent);
            selectStmt.setString(1, MCPP);
            results = selectStmt.executeQuery();
            String mostFreqEventType = null;
            if (results.next()) {
                mostFreqEventType = results.getString("EventType");
            }
            selectStmt2 = connection.prepareStatement(selectLeastFreqEvent);
            selectStmt2.setString(1, MCPP);
            results2 = selectStmt2.executeQuery();
            String leastFreqEventType = null;
            if (results2.next()) {
                leastFreqEventType = results2.getString("EventType");
            }
            
            NeighborhoodEventReports neighborhoodEventReports = new NeighborhoodEventReports(MCPP,mostFreqEventType,leastFreqEventType);
            return neighborhoodEventReports;
                
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (selectStmt != null) {
                selectStmt.close();
            }
            if (results != null) {
                results.close();
            }
        }
    }

}

