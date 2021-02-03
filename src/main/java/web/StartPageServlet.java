package web;

import dao.RoleDao;
import dao.impl.RoleDaoImpl;
import utils.UserUtils;
import utils.impl.UserUtilsImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet(value = "/hello")
public class StartPageServlet extends HttpServlet {
    UserUtils userService = new UserUtilsImpl();
    RoleDao roleDao = new RoleDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/pages/start.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doPost");

        String name = req.getParameter("name");
        String pass = req.getParameter("password");

        if(userService.isValid(name, pass)) {
            HttpSession session = req.getSession();
            session.setAttribute("name", name);
            session.setAttribute("password", pass);
            resp.sendRedirect(req.getContextPath() + "/" + roleDao.getAllByUserName(name).get(0).getName());
            return;
        }

        req.setAttribute("error", "Bad input parameters");
        doGet(req, resp);
    }
}
