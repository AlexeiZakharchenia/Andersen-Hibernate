package web;

import utils.UserUtils;
import utils.impl.UserUtilsImpl;

import javax.servlet.GenericServlet;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(value = "/admin")
public class AdminServlet extends HttpServlet {
    private final String ADMIN = "admin";
    UserUtils userUtils = new UserUtilsImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String userName = (String) session.getAttribute("name");
        String pass = (String) session.getAttribute("password");

        if (userUtils.isValid(userName, pass) && userUtils.hasRole(userName, ADMIN)) {
            req.setAttribute("roles", userUtils.getAllOtherRoles(userName, ADMIN));
            req.getRequestDispatcher("/WEB-INF/pages/admin.jsp").forward(req, resp);
            return;
        }

        req.getRequestDispatcher("/WEB-INF/pages/error.jsp").forward(req, resp);
    }
}
