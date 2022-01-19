package by.tms.servlet;

import service.CalculatorService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/calculator")
public class CalculatorServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String valueFirst = req.getParameter("op1+");
        String valueSecond = req.getParameter("op2");
        String operation = req.getParameter("operat");

        CalculatorService calculatorService = new CalculatorService(valueFirst, valueSecond, operation);

        double operatorFirstDouble = 0.0;
        double operatorTwoDouble = 0.0;
        double result = 0.0;

        boolean isTrueInput = false;

        if (valueFirst == null) {
            resp.getWriter().println("NULL");
        }

        if (isNumber(valueFirst) && isNumber(valueSecond)) {
            operatorFirstDouble = Double.parseDouble(valueFirst);
            operatorTwoDouble = Double.parseDouble(valueSecond);
            isTrueInput = true;
        }

        if (isTrueInput) {
            if (operation.equals("sum")) {
                result = operatorFirstDouble + operatorTwoDouble;
            } else if (operation.equals("dif")) {
                result = operatorFirstDouble - operatorTwoDouble;
            } else if (operation.equals("div")) {
                result = operatorFirstDouble / operatorTwoDouble;
            } else if (operation.equals("mult")) {
                result = operatorFirstDouble * operatorTwoDouble;
            }

            resp.getWriter().println(valueFirst + " " + operation  + " " + valueSecond + " = " + result);
        } else {
            resp.getWriter().println("Invalid operation");
        }
    }

    private boolean isNumber(String inputOperation) {
        try {
            Double.parseDouble(inputOperation);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}

