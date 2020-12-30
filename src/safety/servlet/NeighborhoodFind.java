
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
@WebServlet("/findneighborhood")
public class NeighborhoodFind extends HttpServlet {
	
	protected NeighborhoodsDao neighborhoodsDao;
	protected NeighborhoodEventReportsDao neighborhoodEventReportsDao;
	
	@Override
	public void init() throws ServletException {
		neighborhoodsDao = NeighborhoodsDao.getInstance();
		neighborhoodEventReportsDao = NeighborhoodEventReportsDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map to store messages.
		Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("messages", messages);
		
		Neighborhoods neighborhood = null;
		NeighborhoodEventReports neighborhoodEventReports = null;
		// User's first name is retrieved from the URL query string
		String mcpp = req.getParameter("mcpp");
		if (mcpp == null || mcpp.trim().isEmpty()) {
            messages.put("success", "Please enter a valid mcpp.");
        } else {
        	
        	try {
        		neighborhood = neighborhoodsDao.getNeighborhoodByMCPP(mcpp);
        		neighborhoodEventReports = neighborhoodEventReportsDao.getNeighborhoodEventTypeReportByMCPP(mcpp);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for " + mcpp);
        	// Save the previous search term, so it can be used as the default
        	// in the input box when rendering UserFind.jsp.
        	messages.put("previousMcpp", mcpp);
        }
        req.setAttribute("neighborhood", neighborhood);
        req.setAttribute("neighborhoodEventReports", neighborhoodEventReports);
        
        req.getRequestDispatcher("/NeighborhoodFind.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        Neighborhoods neighborhood = null;
        NeighborhoodEventReports neighborhoodEventReports = null;
        
        // Retrieve and validate name.
        // firstname is retrieved from the form POST submission. By default, it
        // is populated by the URL query string (in UserFind.jsp).
        String mcpp = req.getParameter("mcpp");
        if (mcpp == null || mcpp.trim().isEmpty()) {
            messages.put("success", "Please enter a valid mcpp.");
        } else {
        	// Retrieve BlogUsers, and store as a message.
        	try {
        		neighborhood = neighborhoodsDao.getNeighborhoodByMCPP(mcpp);
        		neighborhoodEventReports = neighborhoodEventReportsDao.getNeighborhoodEventTypeReportByMCPP(mcpp);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for " + mcpp);
        }
        req.setAttribute("neighborhood", neighborhood);
        req.setAttribute("neighborhoodEventReports", neighborhoodEventReports);
        
        req.getRequestDispatcher("/NeighborhoodFind.jsp").forward(req, resp);
    }
}
