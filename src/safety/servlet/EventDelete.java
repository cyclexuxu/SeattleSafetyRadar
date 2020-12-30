package safety.servlet;

import safety.dal.*;
import safety.model.SafetyEvent;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@SuppressWarnings("serial")

@WebServlet("/eventdelete")
public class EventDelete extends HttpServlet {
	
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
        // Provide a title and render the JSP.
        messages.put("title", "Delete An Safety Event");        
        req.getRequestDispatcher("/EventDelete.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        String id = req.getParameter("id");
        if (id == null || id.trim().isEmpty()) {
            messages.put("title", "Invalid ID");
            messages.put("disableSubmit", "true");
        } else {
        	// Delete the BlogUser.
	        try {
	        	SafetyEvent safetyEvent = safetyEventDao.getSafetyEventById(Integer.parseInt(id));
	        	safetyEvent = safetyEventDao.delete(safetyEvent);
	        	// Update the message.
		        if (safetyEvent == null) {
		            messages.put("title", "Successfully deleted " + id);
		            messages.put("disableSubmit", "true");
		        } else {
		        	messages.put("title", "Failed to delete " + id);
		        	messages.put("disableSubmit", "false");
		        }
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/EventDelete.jsp").forward(req, resp);
    }
}
