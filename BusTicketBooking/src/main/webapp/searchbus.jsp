<%@page import="model.Bus"%>
<html>    
<head>    
    <title>Bus Booking System - Book Buses</title>    
    <link rel="stylesheet" type="text/css" href="/BusTicketBooking/style.css">    
    <style>
        .bookBusArea{  
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
  .businner{
                width: 80%;
                max-width: 1920px;
                background-color: #f2f2f2;
                margin: 10px auto;
                padding: 16px;
                
            }
            button{
                width: 80%;  
                background-color: lightgreen;
            }

    </style>
   
</head>    
<body>   
<jsp:include page="header.jsp" /> 
    <h2>Book Buses</h2><br>    
    
    <section class="bookBusArea">
    	    <%
String message = request.getAttribute("message")!=null?request.getAttribute("message").toString():"";
String error = request.getAttribute("error")!=null?request.getAttribute("error").toString():"";
%>
   <jsp:include page="messageFragment.jsp">  
		<jsp:param name="message" value="<%=message%>" />
		<jsp:param name="error" value="<%=error%>" />  
	</jsp:include>  
  
        <div class="inner">    
            <form id="searchBusForm" method="post" action="">    
                <label>From</label>    
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

                <label>Travel Date</label>
                <input type="date" id="travelDate" name="travelDate" placeholder="Select Travel Date">    
                <button type="submit" id="searchBus" name="searchBus">Search Bus</button>       
            </form>
        </div>     
    </section>  
        <section id="busResultArea" class="marginTop50">
            <div class="container">
                <% 
                if(request.getAttribute("buses")!=null){
                Bus[] buses = ((Bus[])request.getAttribute("buses"));

                for(int i=0;i<buses.length;i++){ 
                Bus bus = buses[i];
                int busId = bus.getId();
            %>

                <div class="businner row">
                    <div class="col3">
                        <h3><%=bus.getName()%></h3>                        
                    </div>
                    
                    <div class="col4">
                        <h3> Departure Time - <%=bus.getTime()%> </h3>
                    </div>

                    <div class="col2 text-center">
                        <h3>$ <%=bus.getPrice()%></h3>
                    </div>

                    <div class='col2 text-center'>
                        <p> <%=bus.getSeats()%> </p>
                    </div>

                    <div class="col-2 marginTop20">
                    <a href="/BusTicketBooking/bookings/create?busId=<%=busId %>&price=<%=bus.getPrice()%>">
                        <button id="bookticket" name="bookticket" type="button">Book</button>
                     </a>
                    </div>
                </div>
                <%}}%>
            </div>
        </section>   
 <script src="/BusTicketBooking/populate.js" type="text/javascript"></script>
</body>    
</html>