<%@page import="model.UserDetails"%>  
<html>    
<head>    
    <title>Bus Booking System - My Profile</title>    
    <link rel="stylesheet" type="text/css" href="/BusTicketBooking/style.css">    
    <style>
        .editProfileArea{  
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
<jsp:include page="header.jsp" />
    <h2>Edit Profile</h2><br> 
    	    <%
String message = request.getAttribute("message")!=null?request.getAttribute("message").toString():"";
String error = request.getAttribute("error")!=null?request.getAttribute("error").toString():"";
%>
   <jsp:include page="messageFragment.jsp">  
		<jsp:param name="message" value="<%=message%>" />
		<jsp:param name="error" value="<%=error%>" />  
	</jsp:include>  
    
    <%
    UserDetails user = ((UserDetails)request.getAttribute("user"));
    int userId = user.getId();
    String fullname = user.getFullName();
    String mobile=user.getMobileNumber();
    %>
    <section class="editProfileArea">
        <div class="inner">    
            <form id="editProfileForm" method="post" action="/BusTicketBooking/users/update">
                <input type="hidden" id="userId" name="userId" value="<%=userId%>">    
                <label>Full Name</label>    
                <input type="text" id="username" name="username" value="<%=fullname%>" placeholder="Enter full name">    
                <label>Mobile Number</label>    
                <input type="text" id="mobile" name="mobile" value="<%=mobile%>" placeholder="Enter mobile number">    
                <button type="submit" id="editProfile" name="editProfile">Edit Profile</button>       
            </form>
        </div>     
    </section>    
</body>    
</html>