<%@page import="model.Booking"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ticket</title>
</head>
<style>
table{
width:100%;
}
td{
text-align:center;
}
table,tr,td{
border:1px solid black;
border-collapse:collapse;
}
</style>
<body>
<%
Booking booking = (Booking)request.getAttribute("booking");
String busName =booking.getBusName();
String departure= booking.getBusDate();
String passengerName = booking.getPassengerName();
double price = booking.getPrice();
String source = booking.getSource();
String destination=  booking.getDestination();
%>
<table>
<tr>
<td colspan=2>Booking Details</td>
</tr>
<tr>
<td>Bus Name:<%=busName %></td>
<td>Departure Date & Time:<%=departure %></td>
</tr>
<tr>
<td>From:<%=source %></td>
<td>To:<%=destination %></td>
</tr>
<tr>
<td>Passenger Name: <%=passengerName %></td>
<td>Price: <%=price %></td>
</tr>
<tr>
<td colspan=2>
Happy Journey !
</td>
</tr>
</table>

<script>
setTimeout(function(){
	window.print();
},2000)
</script>
</body>
</html>