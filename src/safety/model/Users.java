package safety.model;


/**
 * 
 * @author Team 4
 * Users is a simple, plain old java object (POJO).
 *
 */
public class Users {
	
	protected String userName;
	protected String password;
	protected String firstName;
	protected String lastName;
	protected String email;
	protected String street1;
	protected String street2;
	protected String city;
	protected Integer zip;
	protected String state;
	protected String country;
	protected Double latitude;
	protected Double longitude;
	protected UserType userType;
	
	/**
	 * Enum for user types.
	 */
	public enum UserType {
		REGULAR, MILITARY, OFFICIAL
	}
	
	

	/**
	 * Constructor with all fields
	 * @param userName UserName specified by the user.
	 * @param password Password specified by the user.
	 * @param firstName User's first name.
	 * @param lastName User's last name.
	 * @param email User's email.
	 * @param street1 User's address street 1 field.
	 * @param street2 User's address street 2 field.
	 * @param city User's address city field.
	 * @param zip User's address zip field.
	 * @param state User's address state field.
	 * @param country User's address country field.
	 * @param latitude User's address latitude field.
	 * @param longitude User's address longitude field.
	 * @param userType User's type represented by enum.
	 */
	public Users(String userName, String password, String firstName, String lastName, String email, String street1,
			String street2, String city, Integer zip, String state, String country, Double latitude, Double longitude,
			UserType userType) {
		super();
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.street1 = street1;
		this.street2 = street2;
		this.city = city;
		this.zip = zip;
		this.state = state;
		this.country = country;
		this.latitude = latitude;
		this.longitude = longitude;
		this.userType = userType;
	}
	
	/**
	 * Constructor with only non-null fields.
	 * @param userName UserName specified by the user.
	 * @param firstName User's first name.
	 * @param email User's email.
	 */
	public Users(String userName, String firstName, String email) {
		this.userName = userName;
		this.firstName = firstName;
		this.email = email;
	}

	/**
	 * Constructor with only user name. Used in case of user deletion.
	 * @param userName UserName specified by the user.
	 */
	public Users(String userName) {
		this.userName = userName;
	}
	
	/**
	 * Constructor with all fields except password.
	 * @param userName UserName specified by the user.
	 * @param firstName User's first name.
	 * @param lastName User's last name.
	 * @param email User's email.
	 * @param street1 User's address street 1 field.
	 * @param street2 User's address street 2 field.
	 * @param city User's address city field.
	 * @param zip User's address zip field.
	 * @param state User's address state field.
	 * @param country User's address country field.
	 * @param latitude User's address latitude field.
	 * @param longitude User's address longitude field.
	 * @param userType User's type represented by enum.
	 */
	public Users(String userName, String firstName, String lastName, String email, String street1,
			String street2, String city, Integer zip, String state, String country, Double latitude, Double longitude,
			UserType userType) {
		super();
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.street1 = street1;
		this.street2 = street2;
		this.city = city;
		this.zip = zip;
		this.state = state;
		this.country = country;
		this.latitude = latitude;
		this.longitude = longitude;
		this.userType = userType;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the street1
	 */
	public String getStreet1() {
		return street1;
	}

	/**
	 * @param street1 the street1 to set
	 */
	public void setStreet1(String street1) {
		this.street1 = street1;
	}

	/**
	 * @return the street2
	 */
	public String getStreet2() {
		return street2;
	}

	/**
	 * @param street2 the street2 to set
	 */
	public void setStreet2(String street2) {
		this.street2 = street2;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the zip
	 */
	public Integer getZip() {
		return zip;
	}

	/**
	 * @param zip the zip to set
	 */
	public void setZip(Integer zip) {
		this.zip = zip;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the latitude
	 */
	public Double getLatitude() {
		return latitude;
	}

	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	/**
	 * @return the longitude
	 */
	public Double getLongitude() {
		return longitude;
	}

	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	/**
	 * @return the userType
	 */
	public UserType getUserType() {
		return userType;
	}

	/**
	 * @param userType the userType to set
	 */
	public void setUserType(UserType userType) {
		this.userType = userType;
	}
}
