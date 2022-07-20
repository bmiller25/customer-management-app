<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Customer Management</title>
    </head>
    <body>
        <%
        String data = "customer data";
        %>
        <h1>Customers</h1>
        <ul>
            <% for (int i = 0; i < 10; i++) { %>
                <li><%= data %></li>
            <% } %>
        </ul>
    </body>
</html>
