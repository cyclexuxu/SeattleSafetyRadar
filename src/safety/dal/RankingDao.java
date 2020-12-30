package safety.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import safety.model.*;

public class RankingDao {
	
	protected ConnectionManager connectionManager;

	// Single pattern: instantiation is limited to one object.
	private static RankingDao instance = null;
	protected RankingDao() {
		connectionManager = new ConnectionManager();
	}
	public static RankingDao getInstance() {
		if(instance == null) {
			instance = new RankingDao();
		}
		return instance;
	}
	
	public List<Ranking> createRanking(String order, String limit) throws SQLException {
		List<Ranking> rankings = new ArrayList<>();
		
		String selectRnkings =
				"SELECT AVG(Rating) AS R,MCPP "
				+ "FROM Ratings "
				+ "GROUP BY MCPP "
				+ "ORDER BY AVG(Rating) " + order + " " 
				+ "LIMIT ?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRnkings);
			//selectStmt.setString(1, order);
			selectStmt.setInt(1, Integer.valueOf(limit));
			results = selectStmt.executeQuery();
			int resultRatingId = 1;
			while(results.next()) {
				
				
				double rating = results.getDouble("R");
				String mcpp = results.getString("MCPP");

				Ranking resultRanking = new Ranking(rating,mcpp,resultRatingId);
				resultRatingId = resultRatingId + 1;
				rankings.add(resultRanking);
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
		return rankings;
		
	}

}
