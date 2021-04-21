<%@page import="model.User"%>
<header id="header">
	<div class="inner">
		<div class="logo"><img src="/BusTicketBooking/buslogo.png" alt="Bus logo"
			/></div>
		<%
		if (session.getAttribute("user") != null) {
		%>
		<%
		if (((User) session.getAttribute("user")).getUserType().equals("user")) {
		%>
		<a href="/BusTicketBooking/buses/search">Search buses</a><br> <a
			href="/BusTicketBooking/passengers/list">Passengers</a><br> <a
			href="/BusTicketBooking/bookings/list">My Booking</a><br> <a
			href="/BusTicketBooking/users/get">My Profile</a><br>
		<%
		} else {
		%>
		<a href="/BusTicketBooking/buses/list">Manage Buses</a><br> <a
			href="/BusTicketBooking/routes/list">Add Route</a><br> <a
			href="/BusTicketBooking/bookings/list">View Bookings</a><br> <a
			href="/BusTicketBooking/users/get">My Profile</a><br>
		<%
		}
		%>
		<a href="/BusTicketBooking/users/logout" id="logout">Logout</a><br>

		<%
		}
		%>

	</div>
</header>
