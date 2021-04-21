<%@page import="model.Bus"%>
<html>
<head>
<title>Bus Booking System - Bus Details</title>
<link rel="stylesheet" type="text/css"
	href="/BusTicketBooking/style.css">
<script src="/BusTicketBooking/populate.js" type="text/javascript"></script>
<style>
.busDetailsArea {
	width: 80%;
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
	<h2>Manage Buses</h2>
	    <%
String message = request.getAttribute("message")!=null?request.getAttribute("message").toString():"";
String error = request.getAttribute("error")!=null?request.getAttribute("error").toString():"";
%>
   <jsp:include page="messageFragment.jsp">  
		<jsp:param name="message" value="<%=message%>" />
		<jsp:param name="error" value="<%=error%>" />  
	</jsp:include>  
  
	<br>
	<section class="busDetailsArea">
		<div class="inner">
			<form id="busForm" method="post" action="">
				<label>Bus Name</label> <input type="text" id="bname" name="bname"
					placeholder="Enter bus name"> <label>From</label>
					 <select
					id="source" name="source" onChange=getDestination(this)>

					<option>--Select Source--</option>
					<% 
                            String[] sourceCity = {};
                        	if(request.getAttribute("source")!=null){
                        		sourceCity = ((String[])request.getAttribute("source"));
                        	}
            
                            for(int i=0;i<sourceCity.length;i++){ 
                                String source = sourceCity[i];
                                
                        %>
					<option value="<%=source%>"><%=source%></option>
					<%}%>
				</select> <label>To</label> <select id="destination" name="destination">

				</select> <label>Departure Date-Time</label> <input type="datetime-local"
					id="depatureDatetime" name="depatureDatetime"
					placeholder="Enter departure date time"> <label>Price</label>
				<input type="number" id="price" name="price"
					placeholder="Enter price"> <label>No. of Seats</label> <input
					type="number" id="seats" name="seats"
					placeholder="Enter no. of seats">
				<button type="submit" id="searchBus" name="searchBus">Update
					Bus Schedule</button>
			</form>
			<%if(request.getAttribute("buses")!=null){ %>
			<table>
				<tr>
					<td>Bus Name</td>
					<td>Source</td>
					<td>Destination</td>
					<td>Date & Time</td>
					<td>Price</td>
					<td>Seats</td>
					<td>Action</td>
				</tr>
				<%
				
            Bus[] buses = (Bus[])request.getAttribute("buses");
            for(int i=0;i<buses.length;i++){
            %>
				<tr>
					<td><%=buses[i].getName()%></td>
					<td><%=buses[i].getSource()%></td>
					<td><%=buses[i].getDestination()%></td>
					<td><%=buses[i].getTime()%></td>
					<td><%=buses[i].getPrice()%></td>
					<td><%=buses[i].getSeats()%></td>
					<td>
						<form method="post" action="/BusTicketBooking/buses/delete">
							<input type="hidden" value="<%=buses[i].getId()%>"
								name="busId">
							<button type="submit">Delete</button>
						</form>
					</td>
				</tr>
				<%} %>
			</table>
			<%}else{ %>
			<p style="text-align:center">No buses found!</p>
			<%} %>
		</div>
	</section>
</body>
</html>