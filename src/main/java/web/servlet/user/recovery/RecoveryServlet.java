package web.servlet.user.recovery;

import web.servlet.Constants;
import web.validator.UserValidator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Random;

@WebServlet(urlPatterns = "/recovery", name = Constants.NAME_RECOVERY_SERVLET)
public class RecoveryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getServletContext().getRequestDispatcher(Constants.PATH_RECOVERY_USER_LINK_JSP).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserValidator userValidator = new UserValidator();
        HttpSession session = req.getSession();

        String userLogin = req.getParameter("login");

        if (userValidator.existsUser(userLogin)) {
            Random random = new Random(1000000000);
            String recoveryWord = String.valueOf(random.nextInt());

            session.setAttribute("recoveryword", recoveryWord);
            session.setAttribute("login", req.getParameter("login"));

            req.getServletContext().getRequestDispatcher(Constants.PATH_NEW_PASSWORD_LINK_JSP).forward(req, resp);
        } else {
            req.setAttribute("msgErrorLogin", Constants.MSG_ERROR_USER_NOT_FOUND);
            req.getServletContext().getRequestDispatcher(Constants.PATH_RECOVERY_USER_LINK_JSP).forward(req, resp);
        }
    }
}
