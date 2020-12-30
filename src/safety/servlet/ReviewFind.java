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

@SuppressWarnings("serial")
@WebServlet("/findreviews")
public class ReviewFind extends HttpServlet {

    protected ReviewsDao reviewsDao;

    @Override
    public void init() throws ServletException {
        reviewsDao = ReviewsDao.getInstance();
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<Reviews> reviews = new ArrayList<Reviews>();


        // Retrieve reviews depending on valid eventId or UserName.
//        String eventId = req.getParameter("eventId");
        String userName = req.getParameter("userName");

        try {
//            if (eventId != null && !eventId.trim().isEmpty()) {
//                reviews = reviewsDao.getReviewsByEventId(Integer.parseInt(eventId));
//                messages.put("success", "Reviews for event " + eventId);
//            } else 
            	if (userName != null && !userName.trim().isEmpty()) {

                reviews = reviewsDao.getReviewsByUserName(userName);
                messages.put("success", "Reviews for User" + userName);

            } else {
                messages.put("success", "Invalid eventid and UserName.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IOException(e);
        }

        req.setAttribute("reviews", reviews);

        req.getRequestDispatcher("/ReviewFind.jsp").forward(req, resp);

    }
}
