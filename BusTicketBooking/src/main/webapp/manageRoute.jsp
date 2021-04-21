<%@page import="model.Route"%>

<!DOCTYPE html>
<html>
<head>
<title>Bus Booking System - Bus Details</title>
<link rel="stylesheet" type="text/css" href="/BusTicketBooking/style.css">
<style>
.routeDetailsArea {
	width: 382px;
	overflow: hidden;
	margin: auto;
	padding: 80px;
	background: #23463f;
	border-radius: 15px;
}

label {
	color: lightblue;
	font-size: 17px;
	cursor: pointer;
}
</style>
</head>
<body>
<jsp:include page="header.jsp" />
	<h2>Bus Route Details</h2>
	<br>
	<%
String message = request.getAttribute("message")!=null?request.getAttribute("message").toString():"";
String error = request.getAttribute("error")!=null?request.getAttribute("error").toString():"";
%>
	<jsp:include page="messageFragment.jsp">
		<jsp:param name="message" value="<%=message%>" />
		<jsp:param name="error" value="<%=error%>" />
	</jsp:include>

	<section class="routeDetailsArea">
		<div class="inner">
			<form id="busRouteForm" method="post"
				action="/BusTicketBooking/routes/create">
				<label>From</label>    
                <input type="text" id="source" name="source" placeholder="Source City">    
                <label>To</label>    
                <input type="text" id="destination" name="destination" placeholder="Destination City"> 
				<button type="submit" id="route" name="route">Add Bus Route</button>
			</form>

			<table>
				<tr>
					<td>Source</td>
					<td>Destination</td>
					<td>Action</td>
				</tr>
				<%
            Route[] routes = (Route[])request.getAttribute("routes");
            for(int i=0;i<routes.length;i++){
            %>
				<tr>
					<td>
						<%=routes[i].getSource()%>
					</td>
					<td>
						<%=routes[i].getDestination()%>
					</td>
					<td>
						<form method="post" action="/BusTicketBooking/routes/delete">
							<input type="hidden" value="<%=routes[i].getId()%>" name="routeId">
							<button type="submit">Delete</button>
						</form>
					</td>
				</tr>
				<%} %>
			</table>
		</div>
	</section>
</body>
</html>