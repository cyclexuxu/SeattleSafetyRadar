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

import safety.dal.RankingDao;

import safety.model.Ranking;

@SuppressWarnings("serial")
@WebServlet("/neighborhoodRanking")
public class NeighborhoodRanking extends HttpServlet {

	protected RankingDao rankingDao;
	
	@Override
	public void init() throws ServletException {
		rankingDao = RankingDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
		
		// Retrieve and validate UserName.
        String order = req.getParameter("order");
        String limit = req.getParameter("limit");
        if (order == null || order.trim().isEmpty()) {
            messages.put("title", "Invalid order.");
        } else {
        	messages.put("title", "Order: " + order);
        }
        
        if (limit == null || limit.trim().isEmpty()) {
            messages.put("title", "Invalid limit.");
        } else {
        	messages.put("title", "Limit: " + limit);
        }
        
        // Retrieve BlogUsers, and store in the request.
        List<Ranking> rankings = new ArrayList<>();
        try {
        	rankings = rankingDao.createRanking(order,limit);
        } catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
        }
        req.setAttribute("rankings", rankings);
        req.getRequestDispatcher("/Ranking.jsp").forward(req, resp);
  
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
		
		// Retrieve and validate UserName.
        String order = req.getParameter("order");
        String limit = req.getParameter("limit");
        if (order == null || order.trim().isEmpty()) {
            messages.put("title", "Invalid order.");
        } else {
        	messages.put("title", "Order: " + order);
        }
        
        if (limit == null || limit.trim().isEmpty()) {
            messages.put("title", "Invalid limit.");
        } else {
        	messages.put("title", "Limit: " + limit);
        }
        
        // Retrieve BlogUsers, and store in the request.
        List<Ranking> rankings = new ArrayList<>();
        try {
        	rankings = rankingDao.createRanking(order, limit);
        } catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
        }
        req.setAttribute("rankings", rankings);
        req.getRequestDispatcher("/Ranking.jsp").forward(req, resp);
	}

}
