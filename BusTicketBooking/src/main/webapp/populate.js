function getDestination(src){
	var xhttp = new XMLHttpRequest();
	var source = src.value;
  	xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	      document.getElementById("destination").innerHTML = this.responseText;
	    }
  	};
	xhttp.open("GET", "/BusTicketBooking/routes/destination?source="+source, true);
	xhttp.send();
  }
  
(function() {
var y= new Date();
var today= dateString = y.getFullYear()+'-'+(y.getMonth()<9?'0':'')+(y.getMonth()+1)+'-'+(y.getDate()<10?'0':'')+(y.getDate());
var dateField = document.getElementById('travelDate');
console.log('Today is:',today);
if(dateField !=undefined){
console.log("Applying min value")
dateField.min=today;
}
})();