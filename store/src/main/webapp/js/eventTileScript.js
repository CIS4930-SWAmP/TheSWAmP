var request = new XMLHttpRequest();
var requestKey = new XMLHttpRequest();

function searchByKeyword(keyword) {
    requestKey.open('GET', 'https://the-swamp.herokuapp.com/store/events/search/' + keyword, true);
    requestKey.send();
    requestKey.onreadystatechange = function () {
        if(requestKey.readyState === 4) {
            if (requestKey.status >= 200 && requestKey.status < 400) {
                var data = JSON.parse(this.response);
                container.innerHTML = "";
                createCards(data);
            } else {
                console.log('Error');
            }
        }
    };
}

const eventDiv = document.getElementById('eventList');
const container = document.createElement('div');
container.setAttribute('class', 'container');

eventDiv.appendChild(container);
request.open('GET', 'https://the-swamp.herokuapp.com/store/events', true);
request.onload = function () {
    if(request.readyState === 4) {
        if (request.status >= 200 && request.status < 400) {
            var data = JSON.parse(this.response);
            createCards(data);
        } else {
            console.log('Error');
        }
    }
};

function createCards(data){
            data.forEach(event => {
                const card = document.createElement('div');
                card.setAttribute('class', 'card');
                card.setAttribute('onClick',`window.location = "/the-swamp.herokuapp.com/html/ticket.html#"+${event.eventId}`);

                const h1 = document.createElement('h1');
                h1.textContent = event.title;
                h1.setAttribute('style', 'background-color:orange');

                const date = document.createElement('p');
                var dateFormat = event.date.substr(5) + '-' + event.date.substr(0,4);
                date.textContent = `Date: ${dateFormat}`;
                date.setAttribute("style", 'color:#343a40');

                const desc = document.createElement('p');
                desc.textContent = event.desc;
                desc.setAttribute("style", 'color:#343a40;margin-bottom:2rem');

                container.appendChild(card);
                card.appendChild(h1);
                card.appendChild(date);
                card.appendChild(desc);

                if(document.getElementById('username').innerText === 'admin'){
                    var updateBtn = document.createElement('button');
                    updateBtn.setAttribute('type', 'button');
                    updateBtn.setAttribute('class', 'btn btn-dark');
                    updateBtn.setAttribute('style', 'margin:0 0 0 14rem;position:absolute;bottom:1rem;');
                    updateBtn.setAttribute('class', 'btn btn-dark');
                    updateBtn.setAttribute('onclick', `updateEventModal(event,${event.eventId})`);
                    updateBtn.innerText = 'Update';
                    card.appendChild(updateBtn);

                    document.getElementById('sell').style.display = "none";
                    document.getElementById('newEvent').style.display = "block";

                }

                //Add Ticket Modal
                var eventSel = document.getElementById('events');
                var option = document.createElement('option');
                option.text = event.title;
                option.value = event.eventId;
                eventSel.appendChild(option);
            });
}

function updateEventModal(event, id){
    event.stopPropagation();
    document.getElementById('submitUpdate').setAttribute('onclick', `updateEvent(${id})`);
    document.getElementById('delete').setAttribute('onclick', `deleteEvent(${id})`);
    var requestEvent = new XMLHttpRequest();
    requestEvent.open('GET', 'https://the-swamp.herokuapp.com/store/events/' + id, true);
    requestEvent.send();
    requestEvent.onreadystatechange = function () {
            if(requestEvent.readyState === 4) {
                if (requestEvent.status >= 200 && request.status < 400) {
                    var data = JSON.parse(this.response);
                    document.getElementById('newName').value = data.title;
                    document.getElementById('newDesc').value = data.desc;
                    document.getElementById('newDate').value = data.date;
                    document.getElementById('update').click();
                } else {
                    console.log('Error');
                }
            }
        }
}

function addTicket(){
    var requestAddTicket = new XMLHttpRequest();

    var sellerId = userInfo.userId;

    var select = document.getElementById('events');
    var eventId = select.options[select.selectedIndex].value;

    var price = document.getElementById('price').value;

    var quantity = document.getElementById('quantity').value;

    var avail = document.getElementById('availability').value;

    var apiUrl = 'https://the-swamp.herokuapp.com/store/tickets?sellerId=' + sellerId + '&eventId=' + eventId
    + '&price=' + price + '&avail=' + avail + '&quantity=' + quantity;

    requestAddTicket.open('POST', apiUrl, true);
    requestAddTicket.send();
    requestAddTicket.onreadystatechange = function () {
        if(requestAddTicket.readyState === 4) {
            if (requestAddTicket.status >= 200 && requestAddTicket.status < 400) {
                document.getElementById('close').click();
            } else {
                console.log('Error');
            }
        }
    };
}

function updateEvent(id){
    var description  = document.getElementById('newDesc').value;
    var title = document.getElementById('newName').value;
    var date = document.getElementById('newDate').value;

    var update = new XMLHttpRequest();
    var apiUrl = 'https://the-swamp.herokuapp.com/store/events?eventId=' + id + '&title=' + title + '&eventDate=' + date + '&description=' + description;
    update.open('PUT', apiUrl, true);
    update.send();
    update.onreadystatechange = function () {
        if (update.readyState === 4) {
            if (update.status >= 200 && update.status < 400) {
                location.reload();
            } else {
                console.log('Error');
            }
        }
    }
}

function addEvent(){
    var description  = document.getElementById('desc').value;
    var title = document.getElementById('name').value;
    var date = document.getElementById('date').value;

    var add = new XMLHttpRequest();
    var apiUrl = 'https://the-swamp.herokuapp.com/store/events?title=' + title + '&eventDate=' + date + '&description=' + description;
    add.open('POST', apiUrl, true);
    add.send();
    add.onreadystatechange = function () {
        if (add.readyState === 4) {
            if (add.status >= 200 && add.status < 400) {
                location.reload();
            } else {
                console.log('Error');
            }
        }
    }
}

function deleteEvent(id){
    var deleteEvent = new XMLHttpRequest();
    var apiUrl = 'https://the-swamp.herokuapp.com/store/events?eventId=' + id;
    deleteEvent.open('DELETE', apiUrl, true);
    deleteEvent.send();
    deleteEvent.onreadystatechange = function () {
        if (deleteEvent.readyState === 4) {
            if (deleteEvent.status >= 200 && deleteEvent.status < 400) {
                location.reload();
            } else {
                console.log('Error');
            }
        }
    }
}
request.send();