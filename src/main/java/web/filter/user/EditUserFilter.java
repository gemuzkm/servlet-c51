package web.filter.user;

import entity.User;
import web.filter.Constants;
import web.validator.UserValidator;
import web.validator.ValueValidator;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(servletNames = Constants.NAME_EDIT_USER_SERVLET)
public class EditUserFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpSession session = req.getSession();
        User user = new User();

        if (session.getAttribute("user") != null) {
            user = (User) session.getAttribute("user");
        } else {
            req.setAttribute("mgsError", Constants.MSG_ERROR_NO_ACCESS);
            req.getServletContext().getRequestDispatcher(Constants.PATH_HOME_LINK_JSP).forward(req, res);
        }

        if (user.getRole() != 1) {
            req.setAttribute("mgsError", Constants.MSG_ERROR_NO_ACCESS);
            req.getServletContext().getRequestDispatcher(Constants.PATH_HOME_LINK_JSP).forward(req, res);
        }

        if (req.getMethod().equals("GET")) {
            session.setAttribute("urlUserEdit", req.getQueryString());
        }

        if (req.getMethod().equals("POST")) {

            ValueValidator valueValidator = new ValueValidator();
            UserValidator userValidator = new UserValidator();

            String name = req.getParameter("name").trim();
            String login = req.getParameter("login").trim();
            String password = req.getParameter("password").trim();
            String roleString = req.getParameter("role").trim();
            int role = Integer.parseInt(roleString);

            if (valueValidator.isNull(name) || valueValidator.isNull(login) || valueValidator.isNull(password)) {
                if (valueValidator.isNull(name)) {
                    session.setAttribute("msgErrorName", Constants.MSG_ERROR_NAME_EMPTY);
                }

                if (valueValidator.isNull(login)) {
                    session.setAttribute("msgErrorLogin", Constants.MSG_ERROR_LOGIN_EMPTY);
                }

                if (valueValidator.isNull(password)) {
                    session.setAttribute("msgErrorPassword", Constants.MSG_ERROR_PASSWORD_EMPTY);
                }

                res.sendRedirect(Constants.URL_USER_EDIT +"?" + (String) session.getAttribute("urlUserEdit"));
                return;
            }

            if (valueValidator.isStringEmpty(name) || valueValidator.isStringEmpty(login) || valueValidator.isStringEmpty(password) || !userValidator.validUserRole(role)) {
                if (valueValidator.isStringEmpty(name)) {
                    session.setAttribute("msgErrorName", Constants.MSG_ERROR_NAME_EMPTY);
                }

                if (valueValidator.isStringEmpty(login)) {
                    session.setAttribute("msgErrorLogin", Constants.MSG_ERROR_LOGIN_EMPTY);
                }

                if (valueValidator.isStringEmpty(password)) {
                    session.setAttribute("msgErrorPassword", Constants.MSG_ERROR_PASSWORD_EMPTY);
                }

                if (!userValidator.validUserRole(role)) {
                    session.setAttribute("msgErrorRole", Constants.MSG_ERROR_ROLE_INVALID);
                }

                res.sendRedirect(Constants.URL_USER_EDIT +"?" + (String) session.getAttribute("urlUserEdit"));
                return;
            }
        }

        chain.doFilter(req, res);
    }
}
