<%@page import="model.Booking"%>
<html>
    <head>
        <title>Bus Booking System - Bus Schedule</title>
        <link rel="stylesheet" href="/BusTicketBooking/style.css">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <style>
            .businner{
                width: 80%;
                max-width: 1920px;
                background-color: #f2f2f2;
                margin: 10px auto;
                padding: 16px;
                
            }
            label{  
                color: lightgray;  
                font-size: 17px;
                cursor: pointer;  
            }

            button{
                float: right;  
                background-color: lightgreen;
            }

        </style>
    </head>
    <body>
    <jsp:include page="header.jsp" />
    <h2>Your bookings</h2>
        <section id="busResultArea" class="marginTop50">
        
            <div class="container">
                	    <%
String message = request.getAttribute("message")!=null?request.getAttribute("message").toString():"";
String error = request.getAttribute("error")!=null?request.getAttribute("error").toString():"";
%>
   <jsp:include page="messageFragment.jsp">  
		<jsp:param name="message" value="<%=message%>" />
		<jsp:param name="error" value="<%=error%>" />  
	</jsp:include>  
  
                <% 
                    Booking[] bookings = (Booking[])request.getAttribute("bookings");

                    for(int i=0;i<bookings.length;i++){ 
                    Booking booking = bookings[i];
                    String busdate= booking.getBusDate();
                    int bookingId = booking.getId();
                %>

                <div class="businner"> 
                    <div class="row">
                        <div class="col4">
                            <h3><%=booking.getBusName()%></h3>                        
                        </div>
                    
                        <div class="col4">
                            <h3> Departure Time: <%=busdate%> </h3>
                        </div>

                        <div class="col2 text-center">
                            <h3>$ <%=booking.getPrice()%></h3>
                        </div>

                       
                        
                    </div>
                    <div class="row">
                        <div class="col4">
                            <h3>Passenger Name: <%=booking.getPassengerName()%></h3>                        
                        </div>
                        
                        <div class="col2  marginTop20">
                            <a href="/BusTicketBooking/bookings/delete?bookingId=<%=bookingId%>"><button type="button" id="cancelticket" name="cancelticket">Cancel</button></a>
                            
                        </div>
                        
                     <div class="col2  marginTop20">
                        <a href="/BusTicketBooking/viewticket/<%=bookingId%>" target="blank"><button type="button">View Ticket</button></a>
                            
                        </div>
                    </div>
                </div>
                <%}%>
            </div>
        </section>        
    </body>
</html>
