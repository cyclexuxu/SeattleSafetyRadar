package safety.dal;

import safety.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReviewsDao {
    protected ConnectionManager connectionManager;

    private static ReviewsDao instance = null;

    protected ReviewsDao() {
        connectionManager = new ConnectionManager();
    }

    public static ReviewsDao getInstance() {
        if (instance == null) {
            instance = new ReviewsDao();
        }
        return instance;
    }

    public Reviews create(Reviews review) throws SQLException {
        String insertReview = "INSERT INTO Reviews(Content, CreatedTime, UserName, EventId) " + "VALUES(?,?,?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        ResultSet resultKey = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertReview, Statement.RETURN_GENERATED_KEYS);
            insertStmt.setString(1, review.getContent());
            insertStmt.setTimestamp(2, new Timestamp(review.getCreatedTime().getTime()));
            insertStmt.setString(3, review.getUser().getUserName());
            insertStmt.setInt(4, review.getEvent().getEventId());
            insertStmt.executeUpdate();

            resultKey = insertStmt.getGeneratedKeys();

            int reviewId = -1;
            if (resultKey.next()) {
                reviewId = resultKey.getInt(1);
            } else {
                throw new SQLException("Unable to retrieve auto-generated key.");
            }
            review.setReviewId(reviewId);

            return review;
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

    public Reviews getReviewById(int reviewId) throws SQLException {
        String selectReview = "SELECT ReviewId, Content, CreatedTime, UserName, EventId " + "FROM Reviews "
                + "WHERE ReviewId=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectReview);
            selectStmt.setInt(1, reviewId);
            results = selectStmt.executeQuery();
            UsersDao usersDao = UsersDao.getInstance();
            SafetyEventDao safetyEventDao = SafetyEventDao.getInstance();
            if (results.next()) {
                int resultReviewId = results.getInt("ReviewId");
                String content = results.getString("Content");
                Date createdTime = new Date(results.getTimestamp("CreatedTime").getTime());
                Users user = usersDao.getUserFromUserName(results.getString("UserName"));
                SafetyEvent event = safetyEventDao.getSafetyEventById(results.getInt("EventId"));
                Reviews review = new Reviews(resultReviewId, content, createdTime, user, event);
                return review;
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

    public List<Reviews> getReviewsByUserName(String UserName) throws SQLException {
        List<Reviews> reviews = new ArrayList<>();
        String selectReviews = "SELECT ReviewId, Content, CreatedTime, UserName, EventId " + "FROM Reviews "
                + "WHERE UserName=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectReviews);
            selectStmt.setString(1, UserName);
            results = selectStmt.executeQuery();
            UsersDao usersDao = UsersDao.getInstance();
            SafetyEventDao safetyEventDao = SafetyEventDao.getInstance();
            while (results.next()) {
                int resultReviewId = results.getInt("ReviewId");
                String content = results.getString("Content");
                Date createdTime = new Date(results.getTimestamp("CreatedTime").getTime());
                Users user = usersDao.getUserFromUserName(results.getString("UserName"));
                SafetyEvent event = safetyEventDao.getSafetyEventById(results.getInt("EventId"));
                Reviews review = new Reviews(resultReviewId, content, createdTime, user, event);
                reviews.add(review);
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
        return reviews;
    }

    public List<Reviews> getReviewsByEventId(int eventId) throws SQLException {
        List<Reviews> reviews = new ArrayList<>();
        String selectReviews = "SELECT ReviewId, Content, CreatedTime, UserName, EventId " + "FROM Reviews "
                + "WHERE EventId=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectReviews);
            selectStmt.setInt(1, eventId);
            results = selectStmt.executeQuery();
            UsersDao usersDao = UsersDao.getInstance();
            SafetyEventDao safetyEventDao = SafetyEventDao.getInstance();
            while (results.next()) {
                int resultReviewId = results.getInt("ReviewId");
                String content = results.getString("Content");
                Date createdTime = new Date(results.getTimestamp("CreatedTime").getTime());
                Users user = usersDao.getUserFromUserName(results.getString("UserName"));
                SafetyEvent event = safetyEventDao.getSafetyEventById(results.getInt("EventId"));
                Reviews review = new Reviews(resultReviewId, content, createdTime, user, event);
                reviews.add(review);
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
        return reviews;
    }

    public Reviews delete(Reviews review) throws SQLException {
        String deleteReviews = "DELETE FROM Reviews WHERE ReviewId=?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;
        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deleteReviews);
            deleteStmt.setLong(1, review.getReviewId());
            deleteStmt.executeUpdate();

            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (deleteStmt != null) {
                deleteStmt.close();
            }
        }
    }

    public Reviews updateContent(Reviews review, String newContent) throws SQLException {
        String updateContent = "UPDATE Reviews SET Content=? WHERE ReviewId=?;";
        Connection connection = null;
        PreparedStatement updateStmt = null;
        try {
            connection = connectionManager.getConnection();
            updateStmt = connection.prepareStatement(updateContent);
            updateStmt.setString(1, newContent);
            updateStmt.setInt(2, review.getReviewId());
            updateStmt.executeUpdate();

            // Update the user parameter before returning to the caller.
            review.setContent(newContent);
            return review;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (updateStmt != null) {
                updateStmt.close();
            }
        }
    }

}
