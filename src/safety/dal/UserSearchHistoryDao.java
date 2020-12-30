package safety.dal;

import safety.model.*;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.Statement;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserSearchHistoryDao {
    protected ConnectionManager connectionManager;

    // Single pattern: instantiation is limited to one object
    private static UserSearchHistoryDao instance = null;

    protected UserSearchHistoryDao() {
        connectionManager = new ConnectionManager();
    }

    public static UserSearchHistoryDao getInstance() {
        if (instance == null) {
            instance = new UserSearchHistoryDao();
        }
        return instance;
    }

    public UserSearchHistory create(UserSearchHistory userSearchHistory) throws SQLException {
        String insertRating = "INSERT INTO RatingHistoryRecords(CreatedTime,EventType,UserName,MCPP,CurrentRating) VALUES(?,?,?,?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        ResultSet resultKey = null;

        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertRating);
            insertStmt.setTimestamp(1, new Timestamp(userSearchHistory.getCreatedTime().getTime()));
            insertStmt.setString(2, userSearchHistory.getEventType().name());
            insertStmt.setString(3, userSearchHistory.getUser().getUserName());
            insertStmt.setString(4, userSearchHistory.getNeighborhood().getMCPP());
            insertStmt.setDouble(5, userSearchHistory.getRating());
            insertStmt.executeUpdate();

            resultKey = insertStmt.getGeneratedKeys();
            int searchId = -1;
            if (resultKey.next()) {
                searchId = resultKey.getInt(1);
            } else {
                throw new SQLException("Unable to retrieve auto-generated key.");
            }

            userSearchHistory.setSearchId(searchId);
            return userSearchHistory;

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
        }
    }

    public List<UserSearchHistory> getUserSearchHistoryByUserName(String userName) throws SQLException {
        List<UserSearchHistory> userSearchHistorys = new ArrayList<UserSearchHistory>();
        String selectuserSearchHistory = "SELECT SearchId,CreatedTime,EventType,UserName,MCPP,CurrentRating "
                + "FROM UserSearchHistory " + "WHERE UserName=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectuserSearchHistory);
            selectStmt.setString(1, userName);
            results = selectStmt.executeQuery();
            UsersDao usersDao = UsersDao.getInstance();
            NeighborhoodsDao neighborhoodsDao = NeighborhoodsDao.getInstance();
            while (results.next()) {
                int searchId = results.getInt("SearchId");
                Date createdTime = new Date(results.getTimestamp("CreatedTime").getTime());
                UserSearchHistory.EventType eventType = UserSearchHistory.EventType
                        .valueOf(results.getString("EventType"));
                Users user = usersDao.getUserFromUserName(results.getString("UserName"));
                Neighborhoods neighborhoods = neighborhoodsDao.getNeighborhoodByMCPP(results.getString("MCPP"));
                Double rating = results.getDouble("CurrentRating");
                UserSearchHistory userSearchHistory = new UserSearchHistory(searchId, createdTime, eventType, user,
                        neighborhoods, rating);
                userSearchHistorys.add(userSearchHistory);
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
        return userSearchHistorys;
    }

    public UserSearchHistory getUserSearchHistoryByUserNameAndMCPP(String userName, String MCPP) throws SQLException {

        String selectuserSearchHistory = "SELECT SearchId,CreatedTime,EventType,UserName,MCPP,CurrentRating "
                + "FROM UserSearchHistory " + "WHERE UserName=? AND MCPP=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectuserSearchHistory);
            selectStmt.setString(1, userName);
            selectStmt.setString(2, MCPP);
            results = selectStmt.executeQuery();
            UsersDao usersDao = UsersDao.getInstance();
            NeighborhoodsDao neighborhoodsDao = NeighborhoodsDao.getInstance();
            if (results.next()) {
                int searchId = results.getInt("SearchId");
                Date createdTime = new Date(results.getTimestamp("CreatedTime").getTime());
                UserSearchHistory.EventType eventType = UserSearchHistory.EventType
                        .valueOf(results.getString("EventType"));
                Users user = usersDao.getUserFromUserName(results.getString("UserName"));
                Neighborhoods neighborhoods = neighborhoodsDao.getNeighborhoodByMCPP(results.getString("MCPP"));
                Double rating = results.getDouble("CurrentRating");
                UserSearchHistory userSearchHistory = new UserSearchHistory(searchId, createdTime, eventType, user,
                        neighborhoods, rating);
                return userSearchHistory;
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

    public UserSearchHistory getUserSearchHistoryBySearchId(String searchId) throws SQLException {

        String selectuserSearchHistory = "SELECT SearchId,CreatedTime,EventType,UserName,MCPP,CurrentRating "
                + "FROM UserSearchHistory " + "WHERE SearchId=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectuserSearchHistory);
            selectStmt.setString(1, searchId);
            results = selectStmt.executeQuery();
            UsersDao usersDao = UsersDao.getInstance();
            NeighborhoodsDao neighborhoodsDao = NeighborhoodsDao.getInstance();
            if (results.next()) {
                int resultSearchId = results.getInt("SearchId");
                Date createdTime = new Date(results.getTimestamp("CreatedTime").getTime());
                UserSearchHistory.EventType eventType = UserSearchHistory.EventType
                        .valueOf(results.getString("EventType"));
                Users user = usersDao.getUserFromUserName(results.getString("UserName"));
                Neighborhoods neighborhoods = neighborhoodsDao.getNeighborhoodByMCPP(results.getString("MCPP"));
                Double rating = results.getDouble("CurrentRating");
                UserSearchHistory userSearchHistory = new UserSearchHistory(resultSearchId, createdTime, eventType,
                        user, neighborhoods, rating);
                return userSearchHistory;
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
