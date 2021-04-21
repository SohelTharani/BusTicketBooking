<%@page import="model.Passenger"%> 
<html>    
<head>    
    <title>Bus Booking System - Traveller Details</title>    
    <link rel="stylesheet" type="text/css" href="/BusTicketBooking/style.css">    
    <style>
        .travellerDetailsArea{  
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
    <h2>Traveller Details</h2><br>    
    <section class="travellerDetailsArea">
        <div class="inner">    
            <form id="travellerForm" method="post" action="/BusTicketBooking/bookings/create">    
                <label>Passengers</label>    
                <select id="passenger" name="passenger">
                    <option>-- Select Passenger --</option>
                    <% 
                    Passenger[] passengers = (Passenger[])request.getAttribute("passengers");

                    for(int i=0;i<passengers.length;i++){ 
	                    Passenger passenger = passengers[i];
	                    int passengerId = passenger.getId();
	                    String passengerName = passenger.getFullName();
                %>  
                    <option value="<%=passengerId%>"><%=passengerName%></option>
                    <%}%>
                </select>    
                <input type="hidden" name="busId" value="<%=session.getAttribute("busId").toString()%>"/>
                <input type="hidden" name="price" value="<%=session.getAttribute("price").toString()%>"/>
                <div><label>Amount: <%=session.getAttribute("price").toString()%></label></div>
                <label>Credit Card Number</label>    
                <input type="number" id="creditCardNumber" name="creditCardNumber" placeholder="Enter your credit card number">
                <label>Credit Card Name: </label>                        
                <input type="text" id="creditCardName" name="creditCardName" placeholder="Enter your credit card name"  />
                <div class="row">
                    <div class="col8"><label>Credit Card Expiry Date: </label></div>
                    <div class="col4"><label>CVV: </label></div>
                </div>
                <div class="row">
                    <div class="col4"><input type="number" id="creditCardExpiryMonth" name="creditCardExpiryMonth" placeholder="MM"  length=2 max="12"/></div>
                    <div class="col4"><input type="number" id="creditCardExpiryYear" name="creditCardExpiryYear" placeholder="YYYY" length=4 min=2021 maxlength="3" /></div>
                    <div class="col4"><input type="number" id="creditCardCvv" name="creditCardCvv" placeholder="CVV" length=3 maxlength="3" /></div>
                </div>
                <button type="submit" >Book Ticket</button>       
            </form>
        </div>     
    </section>    
</body>    
</html>