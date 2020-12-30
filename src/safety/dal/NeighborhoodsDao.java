package safety.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import safety.model.*;

public class NeighborhoodsDao {
    protected ConnectionManager connectionManager;

    // Single pattern: instantiation is limited to one object.
    private static NeighborhoodsDao instance = null;

    protected NeighborhoodsDao() {
        connectionManager = new ConnectionManager();
    }

    public static NeighborhoodsDao getInstance() {
        if (instance == null) {
            instance = new NeighborhoodsDao();
        }
        return instance;
    }

    public Neighborhoods create(Neighborhoods neighborhoods) throws SQLException {
        String insertNeighborhoods = "INSERT INTO Neighborhoods(MCPP, Area, PoliceStation, policeStationAddress, distanceToPoliceStation,fireStation,fireStationAddress,distanceToFireStation) "
                + "VALUES(?,?,?,?,?,?,?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        ResultSet resultKey = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertNeighborhoods);
            insertStmt.setString(1, neighborhoods.getMCPP());
            insertStmt.setLong(2, neighborhoods.getArea());
            insertStmt.setString(3, neighborhoods.getPoliceStation());
            insertStmt.setString(4, neighborhoods.getPoliceStationAddress());
            insertStmt.setDouble(5, neighborhoods.getDistanceToPoliceStation());
            insertStmt.setString(6, neighborhoods.getFireStatin());
            insertStmt.setString(7, neighborhoods.getFireStationAddress());
            insertStmt.setDouble(8, neighborhoods.getDistanceToFireStation());
            insertStmt.executeUpdate();

            return neighborhoods;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (insertStmt != null) {
                insertStmt.close();
            }
            if (resultKey != null) {
                resultKey.close();
            }
        }
    }

    public Neighborhoods getNeighborhoodByMCPP(String MCPP) throws SQLException {
        String selectNeighborhoods = "SELECT MCPP, Area, PoliceStation, policeStationAddress, distanceToPoliceStation,fireStation,fireStationAddress,distanceToFireStation "
                + "FROM Neighborhoods " + "WHERE MCPP=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectNeighborhoods);
            selectStmt.setString(1, MCPP);
            results = selectStmt.executeQuery();
            if (results.next()) {
                String resultMCPP = results.getString("MCPP");
                Long area = results.getLong("Area");
                String policeStation = results.getString("PoliceStation");
                String policeStationAddress = results.getString("policeStationAddress");
                Double distanceToPoliceStation = results.getDouble("distanceToPoliceStation");
                String fireStatin = results.getString("fireStation");
                String fireStationAddress = results.getString("fireStationAddress");
                Double distanceToFireStation = results.getDouble("distanceToFireStation");

                Neighborhoods neighborhoods = new Neighborhoods(resultMCPP, area, policeStation, policeStationAddress,
                        distanceToPoliceStation, fireStatin, fireStationAddress, distanceToFireStation);
                return neighborhoods;
            }
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
        return null;
    }

}
