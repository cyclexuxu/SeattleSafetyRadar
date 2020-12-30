package safety.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import safety.model.*;
import safety.model.Users.UserType;

/**
 * 
 * @author Team 4
 * Data access object (DAO) class to interact with the underlying Users table in
 * MySQL instance. This is used to store {@link Users} into MySQL instance and 
 * retrieve {@link Users} from MySQL instance.
 */
public class UsersDao {
	
	protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object
	private static UsersDao instance = null;
	
	protected UsersDao() {
		connectionManager = new ConnectionManager();
	}
	
	public static UsersDao getInstance() {
		if(instance == null) {
			instance = new UsersDao();
		}
		return instance;
	}
	
	/**
	 * Save the Users instance by storing it in MySQL instance.
	 * This runs an INSERT statement.
	 * @param user Users instance.
	 * @return Users instance.
	 * @throws SQLException If there is a problem with the database connection.
	 */
	public Users create(Users user) throws SQLException {
		// SQL Query statement
		String insertUser = "INSERT INTO Users(UserName,Password,FirstName,LastName,Email,Street1,Street2,"+
		"City,Zip,State,Country,Latitude,Longitude,UserType) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		
		try {
			// Get the connection to database
			connection = connectionManager.getConnection();
			// Prepare statement
			insertStmt = connection.prepareStatement(insertUser);
			// Substitute values
			insertStmt.setString(1, user.getUserName());
			// Check for null field: Password
			if(user.getPassword() == null) {
				insertStmt.setNull(2, Types.VARCHAR);
			} else {
				insertStmt.setString(2, user.getPassword());
			}
			insertStmt.setString(3, user.getFirstName());
			// Check for null field: LastName
			if(user.getLastName() == null) {
				insertStmt.setNull(4, Types.VARCHAR);
			} else {
				insertStmt.setString(4, user.getLastName());
			}
			insertStmt.setString(5, user.getEmail());
			// Check for null field: Street1
			if(user.getStreet1() == null) {
				insertStmt.setNull(6, Types.VARCHAR);
			} else {
				insertStmt.setString(6, user.getStreet1());
			}
			// Check for null field: Street2
			if(user.getStreet2() == null) {
				insertStmt.setNull(7, Types.VARCHAR);
			} else {
				insertStmt.setString(7, user.getStreet2());
			}
			// Check for null field: City
			if(user.getCity() == null) {
				insertStmt.setNull(8, Types.VARCHAR);
			} else {
				insertStmt.setString(8, user.getCity());
			}
			// Check for null field: Zip
			if(user.getZip() == null) {
				insertStmt.setNull(9, Types.INTEGER);
			} else {
				insertStmt.setInt(9, user.getZip());
			}
			// Check for null field: State
			if(user.getState() == null) {
				insertStmt.setNull(10, Types.VARCHAR);
			} else {
				insertStmt.setString(10, user.getState());
			}
			// Check for null field: Country
			if(user.getCountry() == null) {
				insertStmt.setNull(11, Types.VARCHAR);
			} else {
				insertStmt.setString(11, user.getCountry());
			}
			// Check for null field: Latitude
			if(user.getLatitude() == null) {
				insertStmt.setNull(12, Types.VARCHAR);
			} else {
				insertStmt.setDouble(12, user.getLatitude());
			}
			// Check for null field: Latitude
			if(user.getLongitude() == null) {
				insertStmt.setNull(13, Types.VARCHAR);
			} else {
				insertStmt.setDouble(13, user.getLongitude());
			}
			// Check for null field: UserType
			if(user.getUserType() == null) {
				insertStmt.setNull(14, Types.VARCHAR);
			} else {
				insertStmt.setString(14, user.getUserType().name());
			}
			// Execute Query
			insertStmt.executeUpdate();
			return user;
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
	
	/**
	 * Get instance of User from given UserName. Password is not retrieved.
	 * @param userName UserName to look for.
	 * @return User instance.
	 * @throws SQLException If there is a problem with the database connection.
	 */
	public Users getUserFromUserName(String userName) throws SQLException {
		String selectUser =
			"SELECT UserName, FirstName, LastName, Email, Street1, Street2, City, Zip,"+
		    " State, Country, Latitude, Longitude, UserType " +
			"FROM Users " +
			"WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectUser);
			selectStmt.setString(1, userName);
			results = selectStmt.executeQuery();
			if(results.next()) {
				String resultUserName = results.getString("UserName");
				String firstName = results.getString("FirstName");
				String lastName = results.getString("LastName");
				String email = results.getString("Email");
				String street1 = results.getString("Street1");
				String street2 = results.getString("Street2");
				String city = results.getString("City");
				Integer zip = results.getInt("Zip");
				String state = results.getString("State");
				String country = results.getString("Country");
				Double latitude = results.getDouble("Latitude");
				Double longitude = results.getDouble("Longitude");
				UserType type = UserType.valueOf(results.getString("UserType"));
				Users user = new Users(resultUserName,firstName,lastName,email,street1,street2,city,zip,state,country,latitude,longitude,type);
				return user;
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
	
	/**
	 * Get instance of User from given First Name. Password is not retrieved.
	 * @param firstName First Name to look for.
	 * @return User instance.
	 * @throws SQLException If there is a problem with the database connection.
	 */
	public List<Users> getUsersFromFirstName(String firstName)
			throws SQLException {
		List<Users> users = new ArrayList<Users>();
		String selectUsers =
			"SELECT UserName, FirstName, LastName, Email, Street1, Street2, City, Zip,"+
				    " State, Country, Latitude, Longitude, UserType " +
					"FROM Users " +
					"WHERE FirstName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectUsers);
			selectStmt.setString(1, firstName);
			results = selectStmt.executeQuery();
			while(results.next()) {
				String userName = results.getString("UserName");
				String resultFirstName = results.getString("FirstName");
				String lastName = results.getString("LastName");
				String email = results.getString("Email");
				String street1 = results.getString("Street1");
				String street2 = results.getString("Street2");
				String city = results.getString("City");
				Integer zip = results.getInt("Zip");
				String state = results.getString("State");
				String country = results.getString("Country");
				Double latitude = results.getDouble("Latitude");
				Double longitude = results.getDouble("Longitude");
				UserType type = UserType.valueOf(results.getString("UserType"));
				Users user = new Users(userName,resultFirstName,lastName,email,street1,street2,city,zip,state,country,latitude,longitude,type);
				users.add(user);
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
		return users;
	}
	
	/**
	 * Update the Password of the Users instance.
	 * This runs a UPDATE statement.
	 */
	public Users updatePassword(Users user, String newPassword) throws SQLException {
		String updatePassword = "UPDATE Users SET Password=? WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updatePassword);
			updateStmt.setString(1, newPassword);
			updateStmt.setString(2, user.getUserName());
			updateStmt.executeUpdate();
			
			// Update the user parameter before returning to the caller.
			user.setPassword(newPassword);
			return user;
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
	 * Update the First Name of the Users instance.
	 * This runs a UPDATE statement.
	 */
	public Users updateFirstName(Users user, String newFirstName) throws SQLException {
		String updatePassword = "UPDATE Users SET FirstName=? WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updatePassword);
			updateStmt.setString(1, newFirstName);
			updateStmt.setString(2, user.getUserName());
			updateStmt.executeUpdate();
			
			// Update the user parameter before returning to the caller.
			user.setFirstName(newFirstName);
			return user;
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
	 * Update the Last Name of the Users instance.
	 * This runs a UPDATE statement.
	 */
	public Users updateLastName(Users user, String newLastName) throws SQLException {
		String updateLastName = "UPDATE Users SET LastName=? WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateLastName);
			updateStmt.setString(1, newLastName);
			updateStmt.setString(2, user.getUserName());
			updateStmt.executeUpdate();
			
			// Update the user parameter before returning to the caller.
			user.setLastName(newLastName);
			return user;
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
	 * Update the Email of the Users instance.
	 * This runs a UPDATE statement.
	 */
	public Users updateEmail(Users user, String newEmail) throws SQLException {
		String updateEmail = "UPDATE Users SET Email=? WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateEmail);
			updateStmt.setString(1, newEmail);
			updateStmt.setString(2, user.getUserName());
			updateStmt.executeUpdate();
			
			// Update the user parameter before returning to the caller.
			user.setEmail(newEmail);
			return user;
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
	 * Update the Street1 of the Users instance.
	 * This runs a UPDATE statement.
	 */
	public Users updateStreet1(Users user, String newStreet1) throws SQLException {
		String updateStreet1 = "UPDATE Users SET Street1=? WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateStreet1);
			updateStmt.setString(1, newStreet1);
			updateStmt.setString(2, user.getUserName());
			updateStmt.executeUpdate();
			
			// Update the user parameter before returning to the caller.
			user.setStreet1(newStreet1);
			return user;
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
	 * Update the Street2 of the Users instance.
	 * This runs a UPDATE statement.
	 */
	public Users updateStreet2(Users user, String newStreet2) throws SQLException {
		String updateStreet2 = "UPDATE Users SET Street2=? WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateStreet2);
			updateStmt.setString(1, newStreet2);
			updateStmt.setString(2, user.getUserName());
			updateStmt.executeUpdate();
			
			// Update the user parameter before returning to the caller.
			user.setStreet2(newStreet2);
			return user;
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
	 * Update the City of the Users instance.
	 * This runs a UPDATE statement.
	 */
	public Users updateCity(Users user, String newCity) throws SQLException {
		String updateCity = "UPDATE Users SET City=? WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateCity);
			updateStmt.setString(1, newCity);
			updateStmt.setString(2, user.getUserName());
			updateStmt.executeUpdate();
			
			// Update the user parameter before returning to the caller.
			user.setCity(newCity);
			return user;
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
	 * Update the Zip of the Users instance.
	 * This runs a UPDATE statement.
	 */
	public Users updateZip(Users user, Integer newZip) throws SQLException {
		String updateZip = "UPDATE Users SET Zip=? WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateZip);
			updateStmt.setInt(1, newZip);
			updateStmt.setString(2, user.getUserName());
			updateStmt.executeUpdate();
			
			// Update the user parameter before returning to the caller.
			user.setZip(newZip);
			return user;
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
	 * Update the State of the Users instance.
	 * This runs a UPDATE statement.
	 */
	public Users updateState(Users user, String newState) throws SQLException {
		String updateState = "UPDATE Users SET State=? WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateState);
			updateStmt.setString(1, newState);
			updateStmt.setString(2, user.getUserName());
			updateStmt.executeUpdate();
			
			// Update the user parameter before returning to the caller.
			user.setCity(newState);
			return user;
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
	 * Update the Country of the Users instance.
	 * This runs a UPDATE statement.
	 */
	public Users updateCountry(Users user, String newCountry) throws SQLException {
		String updateCountry = "UPDATE Users SET State=? WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateCountry);
			updateStmt.setString(1, newCountry);
			updateStmt.setString(2, user.getUserName());
			updateStmt.executeUpdate();
			
			// Update the user parameter before returning to the caller.
			user.setCity(newCountry);
			return user;
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
	 * Update the Latitude of the Users instance.
	 * This runs a UPDATE statement.
	 */
	public Users updateLatitude(Users user, Double newLatitude) throws SQLException {
		String updateLatitude = "UPDATE Users SET Latitude=? WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateLatitude);
			updateStmt.setDouble(1, newLatitude);
			updateStmt.setString(2, user.getUserName());
			updateStmt.executeUpdate();
			
			// Update the user parameter before returning to the caller.
			user.setLatitude(newLatitude);
			return user;
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
	 * Update the Longitude of the Users instance.
	 * This runs a UPDATE statement.
	 */
	public Users updateLongitude(Users user, Double newLongitude) throws SQLException {
		String updateLongitude = "UPDATE Users SET Latitude=? WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateLongitude);
			updateStmt.setDouble(1, newLongitude);
			updateStmt.setString(2, user.getUserName());
			updateStmt.executeUpdate();
			
			// Update the user parameter before returning to the caller.
			user.setLatitude(newLongitude);
			return user;
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
	 * Update the UserType of the Users instance.
	 * This runs a UPDATE statement.
	 */
	public Users updateUserType(Users user, UserType newUserType) throws SQLException {
		String updateUserType = "UPDATE Users SET UserType=? WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateUserType);
			updateStmt.setString(1, newUserType.name());
			updateStmt.setString(2, user.getUserName());
			updateStmt.executeUpdate();
			
			// Update the user parameter before returning to the caller.
			user.setUserType(newUserType);
			return user;
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
	 * Delete the Users instance.
	 * This runs a DELETE statement.
	 */
	public Users delete(Users user) throws SQLException {
		String deleteUser = "DELETE FROM Users WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteUser);
			deleteStmt.setString(1, user.getUserName());
			deleteStmt.executeUpdate();

			// Return null so the caller can no longer operate on the Users instance.
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
