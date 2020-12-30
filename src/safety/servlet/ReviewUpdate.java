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

@WebServlet("/updatereview")
public class ReviewUpdate extends HttpServlet {
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

        // Retrieve user and validate.

        String reviewId = req.getParameter("id");
        if (reviewId == null || reviewId.trim().isEmpty()) {
            messages.put("success", "Please enter a valid id.");
        } else {
            try {
                Reviews review = reviewsDao.getReviewById(Integer.parseInt(reviewId));

                if (review == null) {
                    messages.put("success", "review does not exist.");
                }
                req.setAttribute("review", review);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
        }


        req.getRequestDispatcher("/ReviewUpdate.jsp").forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve user and validate.

        String reviewId = req.getParameter("reviewId");
        if (reviewId == null || reviewId.trim().isEmpty()) {
            messages.put("success", "Please enter a valid id.");
        } else {
            try {
                Reviews review = reviewsDao.getReviewById(Integer.parseInt(reviewId));


                if (review == null) {
                    messages.put("success", "review does not exist. No update to perform.");
                } else {
                    String newContent = req.getParameter("content");
                    if (newContent == null || newContent.trim().isEmpty()) {
                        messages.put("success", "Please enter a valid content.");
                    } else {
                        review = reviewsDao.updateContent(review, newContent);
                        messages.put("success", "Successfully updated content" + newContent);
                    }
                }
                req.setAttribute("review", review);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
        }

        req.getRequestDispatcher("/ReviewUpdate.jsp").forward(req, resp);

    }

}
