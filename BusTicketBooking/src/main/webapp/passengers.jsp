<%@page import="model.Passenger"%>   
<html>    
<head>    
    <title>Bus Booking System - Traveller Details</title>    
    <link rel="stylesheet" type="text/css" href="/BusTicketBooking/style.css">    
    <style>
        .travellerDetailsArea{  
            width: 80%;  
            overflow: hidden;  
            margin: auto;  
            padding: 80px;  
            background: #23463f;  
            border-radius: 15px ;  
          
        }
        label{  
            color: lightblue;  
            font-size: 17px;
            cursor: pointer;  
        }  
  

    </style>
</head>    
<body>    
<jsp:include page="header.jsp" />
    <h2>Traveller Details</h2><br>  
        <%
String message = request.getAttribute("message")!=null?request.getAttribute("message").toString():"";
String error = request.getAttribute("error")!=null?request.getAttribute("error").toString():"";
%>
   <jsp:include page="messageFragment.jsp">  
		<jsp:param name="message" value="<%=message%>" />
		<jsp:param name="error" value="<%=error%>" />  
	</jsp:include>  
    
    <section class="travellerDetailsArea">
        <div class="inner">    
            <form id="travellerForm" method="post" action="/BusTicketBooking/passengers/create">    
                <label>Full Name</label>    
                <input type="text" id="fullName" name="fullName" placeholder="Enter full name">    
                <label>Gender</label>   <div></div> 
                <input type="radio" id="gender" name="gender" value="male" checked="true"><span style="color: lightblue;">Male</span>
                <input type="radio" id="gender" name="gender" value="female"><span style="color: lightblue;">Female</span>
                <div></div>
                <label>Email</label>
                <input type="email" id="email" name="email" placeholder="Enter E-mail">
                <label>Mobile Number</label>
                <input type="number" id="mobileNumber" name="mobileNumber" placeholder="Enter mobile number">
                <input type="number" id="age" name="age" placeholder="Enter age" max=99 min=1>    
                <button type="submit" id="addpassenger" name="addpassenger">Add Passenger</button>       
            </form>
        </div>     
        <%if(request.getAttribute("passengers")!=null){ %>
			<table>
				<tr>
					<td>Passenger Name</td>
					<td>Email</td>
					<td>Gender</td>
					<td>Mobile Number</td>
					<td>Age</td>
					<td>Action</td>
				</tr>
				<%
				
            Passenger[] passengers = (Passenger[])request.getAttribute("passengers");
            for(int i=0;i<passengers.length;i++){
            %>
				<tr>
					<td><%=passengers[i].getFullName()%></td>
					<td><%=passengers[i].getEmail()%></td>
					<td><%=passengers[i].getGender().equals("male")?"Male":"Female"%></td>
					<td><%=passengers[i].getMobileNumber()%></td>
					<td><%=passengers[i].getAge()%></td>
					<td>
						<form method="post" action="/BusTicketBooking/passengers/delete">
							<input type="hidden" value="<%=passengers[i].getId()%>"
								name="passengerId">
							<button type="submit">Delete</button>
						</form>
					</td>
				</tr>
				<%} %>
			</table>
			<%}else{ %>
			<p style="text-align:center">No passengers found!</p>
			<%} %>
     
    </section>   	
</body>    
</html>