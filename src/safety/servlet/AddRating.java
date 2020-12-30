package safety.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
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
@WebServlet("/addrating")
public class AddRating extends HttpServlet {
	
	protected RatingsDao ratingsDao;
	
	@Override
	public void init() throws ServletException {
		ratingsDao = RatingsDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        //Just render the JSP.   
        req.getRequestDispatcher("/AddRating.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        UsersDao usersDao = UsersDao.getInstance();
        NeighborhoodsDao neighborhoodsDao = NeighborhoodsDao.getInstance();
        
        // Retrieve and validate name.
        String mcpp = req.getParameter("mcpp");
        if (mcpp == null || mcpp.trim().isEmpty()) {
            messages.put("success", "Invalid mcpp");
        } else {
        	// Create the User.
        	double rating = Double.valueOf(req.getParameter("rating"));
        	String comment = req.getParameter("comment");
        	String userName = req.getParameter("username");
        	
        
	        try {
	        	// Exercise: parse the input for StatusLevel.
	        	Users user = usersDao.getUserFromUserName(userName);
	        	Neighborhoods neighborhoods = neighborhoodsDao.getNeighborhoodByMCPP(mcpp);
	        	Ratings ratings = new Ratings(new Date(),rating,comment,user,neighborhoods);
	        	ratings = ratingsDao.create(ratings);
	        	messages.put("success", "Successfully created rating" + ratings.getRatingId());
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/AddRating.jsp").forward(req, resp);
    }
}

