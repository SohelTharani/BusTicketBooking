<!DOCTYPE html>    
<html>    
<head>    
    <title>Bus Booking System - Login</title>    
    <link rel="stylesheet" type="text/css" href="../style.css">    
    <style>
        .loginArea{  
            width: 500px;  
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
    <h2>Login</h2><br>    
    <%
String message = request.getAttribute("message")!=null?request.getAttribute("message").toString():"";
String error = request.getAttribute("error")!=null?request.getAttribute("error").toString():"";
%>
   <jsp:include page="messageFragment.jsp">  
		<jsp:param name="message" value="<%=message%>" />
		<jsp:param name="error" value="<%=error%>" />  
	</jsp:include>  
  
    <section class="loginArea">
        <div class="inner">    
            <form id="loginForm" method="post" action="/BusTicketBooking/users/login">    
                <label>User Name</label>    
                <input type="email" id="email" name="email" placeholder="Username">    
                <label>Password</label>    
                <input type="Password" id="password" name="password" placeholder="Password">    
                <button type="submit" id="login" name="login">Login</button>       
                <a href="/BusTicketBooking/users/register" class="button">New User? Sign up</a>
            </form>
        </div>     
    </section>    
</body>    
</html>