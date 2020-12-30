package safety.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

@WebServlet("/createreview")
public class ReviewCreate extends HttpServlet {
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
        // Just render the JSP.
        req.getRequestDispatcher("/ReviewCreate.jsp").forward(req, resp);

    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        String eventid = req.getParameter("eventId");

        if (eventid == null || eventid.trim().isEmpty()) {
            messages.put("success", "Invalid eventid");
        } else {
            // Create the SafetyEvent.

            String content = req.getParameter("content");

            // dob must be in the format yyyy-mm-dd.
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String stringTime = req.getParameter("createdTime");

            Date reportTime = new Date();
            try {
                reportTime = dateFormat.parse(stringTime);
            } catch (ParseException e) {
                e.printStackTrace();
                throw new IOException(e);
            }

            String userName = req.getParameter("userName");
            int eventId = Integer.valueOf(req.getParameter("eventId"));
            try {
                Users user = UsersDao.getInstance().getUserFromUserName(userName);
                SafetyEvent event = SafetyEventDao.getInstance().getSafetyEventById(eventId);

                Reviews review = new Reviews(content, reportTime, user, event);
                review = reviewsDao.create(review);
                messages.put("success", "Successfully created review" + review.getContent());
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
        }

        req.getRequestDispatcher("/ReviewCreate.jsp").forward(req, resp);

    }
}
