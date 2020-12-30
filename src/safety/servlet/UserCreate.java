package safety.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import safety.dal.*;
import safety.model.*;

@SuppressWarnings("serial")
@WebServlet("/usercreate")
public class UserCreate extends HttpServlet {
	
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
        //Just render the JSP.   
        req.getRequestDispatcher("/UserCreate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        String userName = req.getParameter("username");
        if (userName == null || userName.trim().isEmpty()) {
            messages.put("success", "Invalid UserName");
        } else {
        	// Create the User.
        	String firstName = req.getParameter("firstname");
        	String password = req.getParameter("password");
        	String lastName = req.getParameter("lastname");
        	String email = req.getParameter("email");
        	String street1 = req.getParameter("street1");
        	String street2 = req.getParameter("street");
        	String city = req.getParameter("city");
        	Integer zip = Integer.valueOf(req.getParameter("zip"));
        	String state = req.getParameter("state");
        	String country = req.getParameter("country");
        	Double latitude  = Double.valueOf(req.getParameter("latitude"));
        	Double longitude = Double.valueOf(req.getParameter("longitude"));
        	Users.UserType type = Users.UserType.valueOf(req.getParameter("usertype"));
	        try {
	        	// Exercise: parse the input for StatusLevel.
	        	Users user = new Users(userName, password, firstName, lastName, email,
	        			street1, street2, city, zip, state, country, latitude, longitude, type);
	        	user = usersDao.create(user);
	        	messages.put("success", "Successfully created " + userName);
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/UserCreate.jsp").forward(req, resp);
    }
}

