var request = new XMLHttpRequest();
var requestKey = new XMLHttpRequest();
var isAdmin = userInfo.isAdmin;

if(isAdmin){
    document.getElementById('sell').style.display = "none";
}

request.open('GET', 'http://localhost:8080/TheSWAmP-2.0.3.RELEASE/store/events', true);
request.onload = function () {
    if(request.readyState === 4) {
        if (request.status >= 200 && request.status < 400) {
            var data = JSON.parse(this.response);
            createList(data);
        } else {
            console.log('Error');
        }
    }
};

function searchByKeyword(keyword) {
    requestKey.open('GET', 'http://localhost:8080/TheSWAmP-2.0.3.RELEASE/store/events/search/' + keyword, true);
    requestKey.send();
    requestKey.onreadystatechange = function () {
        if(requestKey.readyState === 4) {
            if (requestKey.status >= 200 && requestKey.status < 400) {
                var data = JSON.parse(this.response);
                const table = document.getElementById('events');
                table.innerHTML = "";
                createList(data);
            } else {
                console.log('Error');
            }
        }
    };
}

const productDiv = document.getElementById('eventList');
const container = document.createElement('div');
container.setAttribute('class', 'container');
productDiv.appendChild(container);

function createList(data){
    data.forEach(event => {
        //Table Data
        const table =document.getElementById('events');
        const list = document.createElement('tr');
        list.setAttribute('onclick',`window.location = "/TheSWAmP-2.0.3.RELEASE/html/ticket.html#"+${event.eventId}`);

        //columns
        const name = document.createElement('td');
        name.textContent = event.title;

        const date = document.createElement('td');
        var dateFormat = event.date.substr(6) + '-' + event.date.substr(0,4);
        date.textContent = dateFormat;

        const desc = document.createElement('td');
        event.description = event.desc;
        desc.textContent = `${event.desc}`;

        //Append Columns to list
        list.appendChild(name);
        list.appendChild(date);
        list.appendChild(desc);

        //Append list to table
        table.appendChild(list);

        //Add Ticket Modal
        var eventSel = document.getElementById('eventNames');
        var option = document.createElement('option');
        option.text = event.title;
        option.value = event.eventId;
        eventSel.appendChild(option);

    });
}
function addTicket() {
    var requestAddTicket = new XMLHttpRequest();

    var sellerId = userInfo.userId;

    var select = document.getElementById('events');
    var eventId = select.options[select.selectedIndex].value;

    var price = document.getElementById('price').value;

    var quantity = document.getElementById('quantity').value;

    var avail = document.getElementById('availability').value;

    var apiUrl = 'http://localhost:8080/TheSWAmP-2.0.3.RELEASE/store/tickets?sellerId=' + sellerId + '&eventId=' + eventId
        + '&price=' + price + '&avail=' + avail + '&quantity=' + quantity;

    requestAddTicket.open('POST', apiUrl, true);
    requestAddTicket.send();
    requestAddTicket.onreadystatechange = function () {
        if (requestAddTicket.readyState === 4) {
            if (requestAddTicket.status >= 200 && request.status < 400) {
                document.getElementById('close').click();
            } else {
                console.log('Error');
            }
        }
    };
}

request.send();