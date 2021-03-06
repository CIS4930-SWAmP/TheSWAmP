var request = new XMLHttpRequest();
var requestTickets = new XMLHttpRequest();
var url = window.location.href;
var eventId = url.substr(url.indexOf('#')+1,url.length - url.indexOf('#')-1);

request.open('GET', 'http://localhost:8080/TheSWAmP-2.0.3.RELEASE/store/events/'+eventId, true);
request.onload = function () {
    if(request.readyState === 4) {
        if (request.status === 200) {
            var data = JSON.parse(this.response);

            //Update Event Details
            document.getElementById('eventName').innerHTML = data.title;

            var dateFormat = data.date.substr(6) + '-' + data.date.substr(0,4);
            document.getElementById('eventDate').innerHTML = `Date: ${dateFormat}`;

            document.getElementById('eventDesc').innerHTML = data.desc;

            var option = document.getElementById('name');
            option.text = data.title;
            option.value = data.title;

        } else {
            console.log('Error');
        }
    }
};

requestTickets.open('GET', 'http://localhost:8080/TheSWAmP-2.0.3.RELEASE/store/tickets/event/'+eventId, true);
requestTickets.onload = function () {
    if(requestTickets.readyState === 4) {
        if (requestTickets.status === 200) {
            var data = JSON.parse(this.response);
            data.forEach(ticket => {

                const ticketDiv = document.getElementById('ticketDiv');

                const table =document.getElementById('tickets');
                const list = document.createElement('tr');

                //columns
                const quantity = document.createElement('td');
                if(ticket.quantity === 1){
                    quantity.textContent = ticket.quantity + ' Ticket';
                }
                else {
                    quantity.textContent = ticket.quantity + ' Tickets';
                }

                const price = document.createElement('td');
                price.textContent = ticket.price;

                const avail = document.createElement('td');
                avail.textContent = ticket.availability;

                var contact = document.createElement('td');
                //Get contact info
                var requestUser = new XMLHttpRequest();
                requestUser.open('GET', 'http://localhost:8080/TheSWAmP-2.0.3.RELEASE/store/users/id/'+ticket.sellerId, true);
                requestUser.send();
                requestUser.onreadystatechange = function () {
                    if(requestUser.readyState === 4) {
                        if (requestUser.status === 200) {
                            var data = JSON.parse(this.response);
                            contact.textContent = data.phone;
                        } else {
                            console.log('Error');
                        }
                    }
                };

                //Append Columns to list
                list.appendChild(quantity);
                list.appendChild(price);
                list.appendChild(avail);
                list.appendChild(contact);

                //Append list to table
                table.appendChild(list);
            })
        } else {
            console.log('Error');
        }
    }
};

request.send();
requestTickets.send();

