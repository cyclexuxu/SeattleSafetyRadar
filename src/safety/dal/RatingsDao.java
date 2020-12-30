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

/**
 * @author ruoyunsun
 *
 */
public class RatingsDao {
	
	protected ConnectionManager connectionManager;

	// Single pattern: instantiation is limited to one object.
	private static RatingsDao instance = null;
	protected RatingsDao() {
		connectionManager = new ConnectionManager();
	}
	public static RatingsDao getInstance() {
		if(instance == null) {
			instance = new RatingsDao();
		}
		return instance;
	}
	
	public Ratings create(Ratings rating) throws SQLException {
		String insertRating = "INSERT INTO Ratings(CreatedTime,Rating,Comment,UserName,MCPP) VALUES(?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertRating,
					Statement.RETURN_GENERATED_KEYS);
			
			insertStmt.setTimestamp(1, new Timestamp(rating.getCreatedTime().getTime()));
			insertStmt.setDouble(2, rating.getRating());
			insertStmt.setString(3, rating.getComment());
			insertStmt.setString(4, rating.getUser().getUserName());
			insertStmt.setString(5, rating.getNeighborhood().getMCPP());
			
			insertStmt.executeUpdate();
			
			resultKey = insertStmt.getGeneratedKeys();
			int ratingId = -1;
			if(resultKey.next()) {
				ratingId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			rating.setRatingId(ratingId);
			return rating;
			
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
	
	public Ratings getRatingByRatingId(int ratingId) throws SQLException {
		String selectRating =
				"SELECT RatingID,CreatedTime,Rating,Comment,UserName,MCPP " +
				"FROM Ratings " +
				"WHERE RatingID=?;";
			Connection connection = null;
			PreparedStatement selectStmt = null;
			ResultSet results = null;
			try {
				connection = connectionManager.getConnection();
				selectStmt = connection.prepareStatement(selectRating);
				selectStmt.setInt(1, ratingId);
				results = selectStmt.executeQuery();
				UsersDao usersDao = UsersDao.getInstance();
				NeighborhoodsDao neighborhoodsDao = NeighborhoodsDao.getInstance();
				
				if(results.next()) {
					int resultRatingId = results.getInt("RatingID");
					Date createdTime =  new Date(results.getTimestamp("CreatedTime").getTime());	
					double rating = results.getDouble("Rating");
					String comment = results.getString("Comment");
					String userName = results.getString("UserName");
					String mcpp = results.getString("MCPP");
					
					Users user = usersDao.getUserFromUserName(userName);
					Neighborhoods neighborhood = neighborhoodsDao.getNeighborhoodByMCPP(mcpp);
					Ratings resultRating = new Ratings(resultRatingId,createdTime,rating,comment,user,neighborhood);
					return resultRating;
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
	
	public List<Ratings> getRatingByUserName(String userName) throws SQLException {
		List<Ratings> ratings = new ArrayList<>();
		
		String selectRatings =
				"SELECT RatingID,CreatedTime,Rating,Comment,UserName,MCPP " +
				"FROM Ratings " +
				"WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRatings);
			selectStmt.setString(1, userName);
			results = selectStmt.executeQuery();
			UsersDao usersDao = UsersDao.getInstance();
			NeighborhoodsDao neighborhoodsDao = NeighborhoodsDao.getInstance();
			while(results.next()) {
				int resultRatingId = results.getInt("RatingID");
				Date createdTime =  new Date(results.getTimestamp("CreatedTime").getTime());	
				double rating = results.getDouble("Rating");
				String comment = results.getString("Comment");
				String resultUserName = results.getString("UserName");
				String mcpp = results.getString("MCPP");

				Users user = usersDao.getUserFromUserName(resultUserName);
				Neighborhoods neighborhood = neighborhoodsDao.getNeighborhoodByMCPP(mcpp);
				Ratings resultRating = new Ratings(resultRatingId,createdTime,rating,comment,user,neighborhood);
				ratings.add(resultRating);
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
		return ratings;
	};
	
	public List<Ratings> getRatingByMCPP(String mcpp) throws SQLException {
		List<Ratings> ratings = new ArrayList<>();
		
		String selectRatings =
				"SELECT RatingID,CreatedTime,Rating,Comment,UserName,MCPP " +
				"FROM Ratings " +
				"WHERE MCPP=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRatings);
			selectStmt.setString(1, mcpp);
			results = selectStmt.executeQuery();
			UsersDao usersDao = UsersDao.getInstance();
			NeighborhoodsDao neighborhoodsDao = NeighborhoodsDao.getInstance();
			while(results.next()) {
				int resultRatingId = results.getInt("RatingID");
				Date createdTime =  new Date(results.getTimestamp("CreatedTime").getTime());	
				double rating = results.getDouble("Rating");
				String comment = results.getString("Comment");
				String userName = results.getString("UserName");
				String resultMcpp = results.getString("MCPP");

				Users user = usersDao.getUserFromUserName(userName);
				Neighborhoods neighborhood = neighborhoodsDao.getNeighborhoodByMCPP(resultMcpp);
				Ratings resultRating = new Ratings(resultRatingId,createdTime,rating,comment,user,neighborhood);
				ratings.add(resultRating);
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
		return ratings;
	};
	
	public Ratings updateRating(Ratings rating, double newRating) throws SQLException {
		String updateCompany = "UPDATE Ratings SET Rating=? WHERE RatingID=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateCompany);
			updateStmt.setDouble(1, newRating);
			updateStmt.setInt(2, rating.getRatingId());
			updateStmt.executeUpdate();
			
			rating.setRating(newRating);
			return rating;
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
	 * delete ratings
	 * @param rating
	 * @return rating
	 * @throws SQLException
	 */
	public Ratings delete(Ratings rating) throws SQLException {
		String deleteRating = "DELETE FROM Ratings WHERE RatingID=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteRating);
			deleteStmt.setInt(1, rating.getRatingId());
			int affectedRows = deleteStmt.executeUpdate();
			if (affectedRows == 0) {
				throw new SQLException("No records available to delete for RatingID=" + rating.getRatingId());
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
