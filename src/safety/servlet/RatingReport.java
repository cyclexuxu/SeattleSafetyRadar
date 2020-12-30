package safety.servlet;

import safety.dal.*;
import safety.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/ratingreports")
public class RatingReport extends HttpServlet {
	
	protected RatingsHistoryRecordsDao ratingsHistoryRecordsDao;
	protected RatingReportsDao ratingReportsDao;
	
	@Override
	public void init() throws ServletException {
		ratingsHistoryRecordsDao = RatingsHistoryRecordsDao.getInstance();
		ratingReportsDao = RatingReportsDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
		
		// Retrieve and validate UserName.
        String mcpp = req.getParameter("mcpp");
        if (mcpp == null || mcpp.trim().isEmpty()) {
            messages.put("title", "Invalid mcpp.");
        } else {
        	messages.put("title", "RatingReport for " + mcpp);
        }
        
     // Retrieve and validate UserName.
        String createdDate = req.getParameter("createdDate");
        Date date;
        if (createdDate == null || createdDate.trim().isEmpty()) {
        	date = new Date();
        } else {
        	try {
        		date = new SimpleDateFormat("dd/MM/yyyy").parse(createdDate);
        	} catch (Exception e) {
        		date = new Date();
        	}
        }
        
        int id;
        try {
        	id = ratingReportsDao.getRatingReportByCreatedDate(date).getReportId();
        } catch (Exception e) {
        	id = -1;
        }
        
        // Retrieve BlogUsers, and store in the request.
        List<RatingsHistoryRecords> ratingReports = new ArrayList<RatingsHistoryRecords>();
        try {
        	ratingReports = ratingsHistoryRecordsDao.getRatingHistoryRecordsByReportIdAndMCPP(id,mcpp);
        } catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
        }
        req.setAttribute("ratingReports", ratingReports);
        req.getRequestDispatcher("/RatingReport.jsp").forward(req, resp);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
		
		// Retrieve and validate UserName.
        String mcpp = req.getParameter("MCPP");
        if (mcpp == null || mcpp.trim().isEmpty()) {
            messages.put("title", "Invalid mcpp.");
        } else {
        	messages.put("title", "RatingReport for " + mcpp);
        }
        
     // Retrieve and validate UserName.
        String createdDate = req.getParameter("date");
        Date date;
        if (createdDate == null || createdDate.trim().isEmpty()) {
        	date = new Date();
        } else {
        	try {
        		date = new SimpleDateFormat("dd/MM/yyyy").parse(createdDate);
        	} catch (Exception e) {
        		date = new Date();
        	}
        }
        
        int id;
        try {
        	id = ratingReportsDao.getRatingReportByCreatedDate(date).getReportId();
        } catch (Exception e) {
        	id = -1;
        }
        
        // Retrieve BlogUsers, and store in the request.
        List<RatingsHistoryRecords> ratingReports = new ArrayList<RatingsHistoryRecords>();
        try {
        	ratingReports = ratingsHistoryRecordsDao.getRatingHistoryRecordsByReportIdAndMCPP(id,mcpp);
        } catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
        }
        req.setAttribute("ratingReports", ratingReports);
        req.getRequestDispatcher("/RatingReport.jsp").forward(req, resp);
	}
}
