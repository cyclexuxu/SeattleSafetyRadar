package safety.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import safety.dal.*;
import safety.model.*;
import safety.model.Users.UserType;

@SuppressWarnings("serial")
@WebServlet("/userupdate")
public class UserUpdate extends HttpServlet {
	
	protected UsersDao usersDao;
	
	@Override
	public void init() throws ServletException {
		usersDao = UsersDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve user and validate.
        String userName = req.getParameter("username");
        if (userName == null || userName.trim().isEmpty()) {
            messages.put("success", "Please enter a valid UserName.");
        } else {
        	try {
        		Users user = usersDao.getUserFromUserName(userName);
        		if(user == null) {
        			messages.put("success", "UserName does not exist.");
        		}
        		req.setAttribute("user", user);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/UserUpdate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, ArrayList<String>> messages = new HashMap<String, ArrayList<String>>();
        ArrayList<String> successes = new ArrayList<String>();
        req.setAttribute("messages", messages);

        // Retrieve user and validate.
        String userName = req.getParameter("username");
        if (userName == null || userName.trim().isEmpty()) {
        	successes.add("Please enter a valid UserName.");
        } else {
        	try {
        		Users user = usersDao.getUserFromUserName(userName);
        		if(user == null) {
        			// if the user doesn't exist
        			successes.add("UserName does not exist. No update to perform.");
        		} else {
        			// Password
        			String newPassword = req.getParameter("password");
        			if (newPassword != null && !newPassword.trim().isEmpty()) {
        				user = usersDao.updatePassword(user, newPassword);
        				successes.add("Successfully updated Password");
        	        }
        			// FirstName
        			String newFirstName = req.getParameter("firstname");
        			if (newFirstName != null && !newFirstName.trim().isEmpty()) {
        				user = usersDao.updateFirstName(user, newFirstName);
        				successes.add("Successfully updated FirstName");
        	        }
        			// LastName
        			String newLastName = req.getParameter("lastname");
        			if (newLastName != null && !newLastName.trim().isEmpty()) {
        				user = usersDao.updateLastName(user, newLastName);
        				successes.add("Successfully updated LastName");
        	        }
        			// Email
        			String newEmail = req.getParameter("email");
        			if (newEmail != null && !newEmail.trim().isEmpty()) {
        				user = usersDao.updateEmail(user, newEmail);
        				successes.add("Successfully updated Email");
        	        }
        			// Street1
        			String newStreet1 = req.getParameter("street1");
        			if (newStreet1 != null && !newStreet1.trim().isEmpty()) {
        				user = usersDao.updateStreet1(user, newStreet1);
        				successes.add("Successfully updated Street1");
        	        }
        			// Street2
        			String newStreet2 = req.getParameter("street2");
        			if (newStreet2 != null && !newStreet2.trim().isEmpty()) {
        				user = usersDao.updateStreet2(user, newStreet2);
        				successes.add("Successfully updated Street2");
        	        }
        			// city
        			String newCity = req.getParameter("city");
        			if (newCity != null && !newCity.trim().isEmpty()) {
        				user = usersDao.updateCity(user, newCity);
        				successes.add("Successfully updated City");
        	        }
        			// zip
        			String newZip = req.getParameter("zip");
        			if (newZip != null && !newZip.trim().isEmpty()) {
        				user = usersDao.updateZip(user, Integer.valueOf(newZip));
        				successes.add("Successfully updated Zip");
        	        }
        			// State
        			String newState = req.getParameter("state");
        			if (newState != null && !newState.trim().isEmpty()) {
        				user = usersDao.updateState(user, newState);
        				successes.add("Successfully updated State");
        	        }
        			// Country
        			String newCountry = req.getParameter("country");
        			if (newCountry != null && !newCountry.trim().isEmpty()) {
        				user = usersDao.updateCountry(user, newCountry);
        				successes.add("Successfully updated Country");
        	        }
        			// Latitude
        			String newLatitude = req.getParameter("latitude");
        			if (newLatitude != null && !newLatitude.trim().isEmpty()) {
        				user = usersDao.updateLatitude(user, Double.valueOf(newLatitude));
        				successes.add("Successfully updated Latitude");
        	        }
        			// Longitude
        			String newLongitude = req.getParameter("longitude");
        			if (newLongitude != null && !newLongitude.trim().isEmpty()) {
        				user = usersDao.updateLongitude(user, Double.valueOf(newLongitude));
        				successes.add("Successfully updated Longitude");
        	        }
        			// UserType
        			String newType = req.getParameter("usertype");
        			if (newType != null && !newType.trim().isEmpty()) {
        				user = usersDao.updateUserType(user, UserType.valueOf(newType));
        				successes.add("Successfully updated UserType");
        	        }
        		}
        		req.setAttribute("user", user);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        // add all messages
        messages.put("success", successes);
        req.setAttribute("messages", messages);
        req.getRequestDispatcher("/UserUpdate.jsp").forward(req, resp);
    }
}
