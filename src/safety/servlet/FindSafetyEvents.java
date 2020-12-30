package safety.servlet;

import safety.dal.*;
import safety.model.*;
import safety.model.SafetyEvent.EventType;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@SuppressWarnings("serial")

@WebServlet("/findevents")
public class FindSafetyEvents extends HttpServlet {
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

        List<SafetyEvent> events = new ArrayList<>();
        
        // Retrieve and validate name.
        // event_type is retrieved from the URL query string.
        String event_type = req.getParameter("event_type");
        if (event_type == null || event_type.trim().isEmpty()) {
            messages.put("success", "Please enter a valid event type.");
        } else {
        	// Retrieve BlogUsers, and store as a message.
        	try {
            	events = safetyEventDao.getSafetyEventByType(EventType.valueOf(event_type));
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for " + event_type);
        	// Save the previous search term, so it can be used as the default
        	// in the input box when rendering FindUsers.jsp.
        	messages.put("previousEventType", event_type);
        }
        req.setAttribute("events", events);
        
        req.getRequestDispatcher("/FindSafetyEvents.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<SafetyEvent> events = new ArrayList<SafetyEvent>();
        
        // Retrieve and validate name.
        // event_type is retrieved from the form POST submission. By default, it
        // is populated by the URL query string (in FindUsers.jsp).
        String event_type = req.getParameter("event_type");
        if (event_type == null || event_type.trim().isEmpty()) {
            messages.put("success", "Please enter a valid event type.");
        } else {
        	// Retrieve BlogUsers, and store as a message.
        	try {
            	events = safetyEventDao.getSafetyEventByType(EventType.valueOf(event_type));
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for " + event_type);
        }
        req.setAttribute("events", events);
        
        req.getRequestDispatcher("/FindSafetyEvents.jsp").forward(req, resp);
    }
}
