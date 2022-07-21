<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.io.BufferedReader" %>
<%@ page import="com.benjaminjmiller.customerswebsite.DataRetriever" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Customer Management</title>
        <link href="style.jsp" rel="stylesheet">
    </head>
    <body>
        <h1>Customers</h1>
        <table>
            <tr>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Rewards Member?</th>
                <th>Rewards Points</th>
            </tr>
            <%
            BufferedReader bufferedReader = DataRetriever.getCustomerData();
            String line = bufferedReader.readLine();
            while (line != null) {
                String[] parts = line.split(",");
                boolean isRewardsMember = false;
                if (!parts[9].equals("")) {
                    isRewardsMember = Boolean.parseBoolean(parts[9]);
                }
                String isRewardsMemberString = isRewardsMember ? "Yes" : "No";
                long rewardsPoints = 0;
                if (parts.length == 11 && !parts[10].equals("")) {
                    rewardsPoints = Long.parseLong(parts[10]);
                }
                %>
                <tr>
                    <td><%= parts[0] %></td><td><%= parts[1] %></td><td><%= isRewardsMemberString %></td><td><%= rewardsPoints %></td>
                </tr>
                <%
                line = bufferedReader.readLine();
            }
            %>
        </table>
    </body>
</html>
