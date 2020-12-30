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

import safety.dal.SafetyEventDao;
import safety.model.SafetyEvent;


@SuppressWarnings("serial")

@WebServlet("/eventupdate")
public class UpdataEvent extends HttpServlet {
	protected SafetyEventDao safetyEventDao;
	
	@Override
	public void init() throws ServletException {
		safetyEventDao = SafetyEventDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve user and validate.
        String id = req.getParameter("id");
        if (id == null || id.trim().isEmpty()) {
            messages.put("success", "Please enter a valid id.");
        } else {
        	try {
        		SafetyEvent safetyEvent = safetyEventDao.getSafetyEventById(Integer.parseInt(id));
        		if(safetyEvent == null) {
        			messages.put("success", "Safety event does not exist.");
        		}
        		req.setAttribute("safetyevent", safetyEvent);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/UpdateEvent.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve user and validate.
        String id = req.getParameter("id");
        if (id == null || id.trim().isEmpty()) {
            messages.put("success", "Please enter a valid id.");
        } else {
        	try {
        		SafetyEvent safetyEvent = safetyEventDao.getSafetyEventById(Integer.parseInt(id));
        		if(safetyEvent == null) {
        			messages.put("success", "Safety event does not exist. No update to perform.");
        		} else {
        			String newMCPP = req.getParameter("MCPP");
        			if (newMCPP == null || newMCPP.trim().isEmpty()) {
        	            messages.put("success", "Please enter a valid MCPP.");
        	        } else {
        	        	safetyEvent = safetyEventDao.updateMCPP(safetyEvent, newMCPP);
        	        	messages.put("success", "Successfully updated " + newMCPP);
        	        }
        		}
        		req.setAttribute("safety", safetyEvent);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/UpdateEvent.jsp").forward(req, resp);
    }
	
}
