package com.shashi.srv;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.shashi.service.DiscountContext;

@WebServlet("/ApplyDiscountServlet")
public class ApplyDiscountServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("discountCode");
        double amount = 0;

        try {
            amount = Double.parseDouble(request.getParameter("amount"));
        } catch (Exception e) {
            amount = 0;
        }

        // Create DiscountContext to handle discount logic
        DiscountContext discountContext = new DiscountContext();
        double discountedAmount = discountContext.applyDiscount(code, amount);

        // Add message based on discount application
        if (discountedAmount < amount) {
            request.setAttribute("message", "Discount Applied: " + (amount - discountedAmount) + " OFF");
        } else {
            request.setAttribute("message", "Invalid or Duplicate Discount Code");
        }

        // Forward to payment.jsp with the updated discounted amount
        request.setAttribute("discountedAmount", discountedAmount);
        RequestDispatcher rd = request.getRequestDispatcher("payment.jsp");
        rd.forward(request, response);
    }
}
