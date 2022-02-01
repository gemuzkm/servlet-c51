package web.filter.user.revocery;

import web.filter.Constants;
import web.validator.ValueValidator;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(servletNames = "UpdatePasswordServlet")
public class UpdatePasswordFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {

        if (req.getMethod().equals("POST")) {
            ValueValidator valueValidator = new ValueValidator();
            HttpSession session = req.getSession();

            String newPassword = req.getParameter("newpassword").trim();
            String inputSecretWord = req.getParameter("recword").trim();
            String secretWord = (String) session.getAttribute("recoveryword");

            if (valueValidator.isStringEmpty(newPassword) || valueValidator.isStringEmpty(inputSecretWord)) {
                if (valueValidator.isStringEmpty(newPassword)) {
                    req.setAttribute("msgErrorPassword", Constants.MSG_ERROR_PASSWORD_EMPTY);
                }

                if (valueValidator.isStringEmpty(inputSecretWord)) {
                    req.setAttribute("msgErrorRecoveryWord", Constants.MSG_ERROR_SERCRET_WOWRD_EMPTY);
                } else if (!secretWord.equals(inputSecretWord)) {
                    req.setAttribute("msgErrorRecoveryWord", Constants.MSG_ERROR_SECRET_WORD_WRONG);
                }

                req.getServletContext().getRequestDispatcher(Constants.NEW_PASSWORD_LINK_JSP).forward(req, res);
            }

        }

        chain.doFilter(req, res);
    }
}