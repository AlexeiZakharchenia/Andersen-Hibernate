package web;

import utils.UserUtils;
import utils.impl.UserUtilsImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(value = "/subscriber")
public class SubscriberServlet extends HttpServlet {
    UserUtils userUtils = new UserUtilsImpl();

    private final String SUBSCRIBER = "subscriber";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String userName = (String) session.getAttribute("name");
        String pass = (String) session.getAttribute("password");

        if (userUtils.isValid(userName, pass) && userUtils.hasRole(userName, SUBSCRIBER)) {
            req.setAttribute("roles", userUtils.getAllOtherRoles(userName, SUBSCRIBER));
            req.getRequestDispatcher("/WEB-INF/pages/subscriber.jsp").forward(req, resp);
            return;
        }

        req.getRequestDispatcher("/WEB-INF/pages/error.jsp").forward(req, resp);
    }
}
