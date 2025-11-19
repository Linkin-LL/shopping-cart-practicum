package com.shashi.srv;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ApplyDiscountServlet")
public class ApplyDiscountServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // >>> CHANGED: only doPost is needed
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // >>> END

        String code = request.getParameter("discountCode");
        double amount = 0;

        try {
            amount = Double.parseDouble(request.getParameter("amount"));
        } catch (Exception e) {
            amount = 0;
        }

        double discountedAmount = amount;

        // >>> ADDED DISCOUNT RULES
        if (code != null && code.equalsIgnoreCase("SAVE10")) {
            discountedAmount = amount * 0.90;
            request.setAttribute("message", "Discount Applied: 10% OFF");
        } else if (code != null && code.equalsIgnoreCase("SAVE20")) {
            discountedAmount = amount * 0.80;
            request.setAttribute("message", "Discount Applied: 20% OFF");
        } else {
            request.setAttribute("message", "Invalid Discount Code");
        }
        // >>> END

        request.setAttribute("discountedAmount", discountedAmount);

        // >>> CHANGED: forward back to payment.jsp
        RequestDispatcher rd = request.getRequestDispatcher("payment.jsp");
        rd.forward(request, response);
        // >>> END
    }
}
