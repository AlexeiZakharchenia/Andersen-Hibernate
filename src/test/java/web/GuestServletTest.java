package web;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import utils.UserUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@RunWith(MockitoJUnitRunner.class)
public class GuestServletTest {

    @Mock
    private UserUtils userUtils;
    @Mock
    private ServletConfig servletConfig;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private RequestDispatcher requestDispatcher;
    @Mock
    private HttpSession session;

    @InjectMocks
    private GuestServlet guestServlet = new GuestServlet();

    @Before
    public void setup(){
        Mockito.when(request.getRequestDispatcher(ArgumentMatchers.anyString())).thenReturn(requestDispatcher);
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute(ArgumentMatchers.matches("name"))).thenReturn("testName");
        Mockito.when(session.getAttribute(ArgumentMatchers.matches("password"))).thenReturn("testPassword");

        Mockito.when(userUtils.hasRole("testName", "guest")).thenReturn(true);
    }

    @Test
    public void doGetWhenAllisValid() throws ServletException, IOException {
        Mockito.when(userUtils.isValid("testName", "testPassword")).thenReturn(true);

        guestServlet.init(servletConfig);
        guestServlet.doGet(request, response);

        Mockito.verify(request).getRequestDispatcher("/WEB-INF/pages/guest.jsp");
        Mockito.verify(requestDispatcher).forward(request, response);
    }

    @Test
    public void doGetError() throws ServletException, IOException {
        Mockito.when(userUtils.isValid("testName", "testPassword")).thenReturn(false);

        guestServlet.init(servletConfig);
        guestServlet.doGet(request, response);

        Mockito.verify(request).getRequestDispatcher("/WEB-INF/pages/error.jsp");
        Mockito.verify(requestDispatcher).forward(request, response);
    }
}