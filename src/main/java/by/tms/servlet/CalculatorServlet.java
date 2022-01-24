package by.tms.servlet;

import service.CalculatorService;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/calculator", name = "CalculatorServlet")
public class CalculatorServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService = new UserService();
        HttpSession session = req.getSession();
        String sessionID = session.getId();

        if (session.getAttribute("login") == null) {
            resp.getWriter().println("Welcome, Anonymous. You are not authorized.");
        } else if (userService.changedUserSessionID(session.getAttribute("login").toString(), sessionID)) {
            resp.getWriter().println("Welcome, Anonymous. You are not authorized.");
        } else {
            String valueFirst = req.getParameter("value1");
            String valueSecond = req.getParameter("value2");
            String operation = req.getParameter("operation");
            String userLogin = session.getAttribute("login").toString();

            CalculatorService calculatorService = new CalculatorService(valueFirst, valueSecond, operation, userLogin);

            resp.getWriter().println(calculatorService.getResult());
        }
    }
}

