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
                const ticketDiv = document.getElementById('tickets');

                const div = document.createElement('div');
                div.setAttribute('class','border');

                const h1 = document.createElement('h5');
                h1.textContent = '1 Ticket';

                const price = document.createElement('h5');
                price.textContent = `$${ticket.price}`;

                // const contact = document.createElement('p');
                // desc.textContent = ${ticket.desc};

                const avail = document.createElement('h5');
                avail.textContent = ticket.availability;

                div.appendChild(h1);
                div.appendChild(price);
                div.appendChild(avail);

                ticketDiv.appendChild(div);
            })
        } else {
            console.log('Error');
        }
    }
};


request.send();
requestTickets.send();