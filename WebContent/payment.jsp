<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="com.shashi.service.impl.*, com.shashi.service.*, com.shashi.beans.*, java.util.*, javax.servlet.ServletOutputStream, java.io.*"%>
<!DOCTYPE html>
<html>
<head>
    <title>Payments</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/changes.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
</head>
<body style="background-color: #E6F9E6;">
<% 
    String userName = (String) session.getAttribute("username");
    String password = (String) session.getAttribute("password");
    if (userName == null || password == null) { 
        response.sendRedirect("login.jsp?message=Session Expired, Login Again!!"); 
    } 

    String sAmount = request.getParameter("amount");
    double amount = (sAmount != null) ? Double.parseDouble(sAmount) : 0; 
    Double discountedAmount = (Double) request.getAttribute("discountedAmount");
    double finalAmount = (discountedAmount != null) ? discountedAmount : amount;
%>

<jsp:include page="header.jsp" />

<div class="container">
    <div class="row" style="margin-top: 5px; margin-left: 2px; margin-right: 2px;">
        <form action="ApplyDiscountServlet" method="post" class="col-md-6 col-md-offset-3" style="border: 2px solid black; border-radius: 10px; background-color: #FFE5CC; padding: 10px;">
            <% String msg = (String) request.getAttribute("message"); 
                if (msg != null) { 
            %> 
                <div class="alert alert-info"><%=msg %></div> 
            <% } %> 
            <div class="row">
                <div class="col-md-8 form-group">
                    <label>Discount Code</label>
                    <input type="text" name="discountCode" class="form-control" placeholder="Enter code">
                </div>
                <div class="col-md-4 form-group">
                    <label>&nbsp;</label>
                    <button type="submit" class="btn btn-warning form-control">Apply</button>
                    <input type="hidden" name="amount" value="<%=amount%>">
                </div>
            </div>
        </form>

        <form action="./OrderServlet" method="post" class="col-md-6 col-md-offset-3" style="border: 2px solid black; border-radius: 10px; background-color: #FFE5CC; padding: 10px;">
            <div style="font-weight: bold;" class="text-center">
                <div class="form-group">
                    <img src="images/profile.jpg" alt="Payment Proceed" height="100px" />
                    <h2 style="color: green;">Credit Card Payment</h2>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12 form-group">
                    <label>Name of Card Holder</label>
                    <input type="text" name="cardholder" class="form-control" required>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12 form-group">
                    <label>Enter Credit Card Number</label>
                    <input type="number" name="cardnumber" class="form-control" required>
                </div>
            </div>
            <div class="row">
                <div class="col-md-6 form-group">
                    <label>Expiry Month</label>
                    <input type="number" name="expmonth" class="form-control" required>
                </div>
                <div class="col-md-6 form-group">
                    <label>Expiry Year</label>
                    <input type="number" name="expyear" class="form-control" required>
                </div>
            </div>
            <div class="row text-center">
                <div class="col-md-6 form-group">
                    <label>Enter CVV</label>
                    <input type="number" name="cvv" class="form-control" required>
                    <input type="hidden" name="amount" value="<%=finalAmount%>">
                </div>
                <div class="col-md-6 form-group">
                    <label>&nbsp;</label>
                    <button type="submit" class="form-control btn btn-success"> Pay : Rs <%=finalAmount%> </button>
                </div>
            </div>
        </form>
    </div>
</div>

<%@ include file="footer.html"%>
</body>
</html>
