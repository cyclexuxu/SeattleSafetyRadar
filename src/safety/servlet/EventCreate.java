package safety.servlet;

import safety.dal.*;
import safety.model.*;
import safety.model.SafetyEvent.EventType;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")

@WebServlet("/eventcreate")
public class EventCreate extends HttpServlet {
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
        //Just render the JSP.   
        req.getRequestDispatcher("/EventCreate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        String mcpp = req.getParameter("MCPP");
        if (mcpp == null || mcpp.trim().isEmpty()) {
            messages.put("success", "Invalid MCPP");
        } else {
        	// Create the SafetyEvent.
        	double lat = Double.valueOf(req.getParameter("latitude"));
        	double lon = Double.valueOf(req.getParameter("longitude"));
        	Date date = new Date();
        	// dob must be in the format yyyy-mm-dd.
        	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        	String stringDob = req.getParameter("reporttime");
        	Date reportTime  = new Date();
        	try {
        		reportTime = dateFormat.parse(stringDob);
        	} catch (ParseException e) {
        		e.printStackTrace();
				throw new IOException(e);
        	}
        	EventType type = EventType.valueOf(req.getParameter("eventtype"));
        	String MCPP = req.getParameter("MCPP");
	        try {
	        	// Exercise: parse the input for StatusLevel.
	        	SafetyEvent event = new SafetyEvent(lat, lon, date, reportTime, type, MCPP);
	        	event = safetyEventDao.create(event);
	        	messages.put("success", "Successfully created " + event.getEventType().name());
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/EventCreate.jsp").forward(req, resp);
    }
}
