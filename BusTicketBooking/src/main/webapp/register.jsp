<!DOCTYPE html>    
<html>    
<head>    
    <title>Bus Booking System - Registration</title>    
    <link rel="stylesheet" type="text/css" href="../style.css">    
    <style>
        .registrationArea{  
            width: 382px;  
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
<%
String message = request.getAttribute("message")!=null?request.getAttribute("message").toString():"";
String error = request.getAttribute("error")!=null?request.getAttribute("error").toString():"";
%>
    <h2>Registration</h2><br>    
    <jsp:include page="messageFragment.jsp">  
		<jsp:param name="message" value="<%=message%>" />
		<jsp:param name="error" value="<%=error%>" />  
	</jsp:include>  
    <section class="registrationArea">
        <div class="inner">    
            <form id="registrationForm" method="POST" action="/BusTicketBooking/users/register">    
                <label>Full Name</label>    
                <input type="text" id="username" name="username" placeholder="Enter full name">    
                <label>Email</label>    
                <input type="email" id="email" name="email" placeholder="Enter E-mail">    
                <label>Password</label>    
                <input type="Password" id="password" name="password" placeholder="Enter password">    
                <label>Mobile Number</label>    
                <input type="text" id="mobile" name="mobile" placeholder="Enter mobile number">    
                <button type="submit" id="register" name="register">Register</button>       
            </form>
        </div>     
    </section>    
</body>    
</html>