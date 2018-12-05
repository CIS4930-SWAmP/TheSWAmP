var request = new XMLHttpRequest();
var requestKey = new XMLHttpRequest();
var isAdmin = false;

//Do login stuff
if(isAdmin){
    document.getElementById('sell').style.display = "none";
    document.getElementById('newEvent').style.display = "block";
}

function searchByKeyword(keyword) {
    requestKey.open('GET', 'http://localhost:8080/TheSWAmP-2.0.3.RELEASE/store/events/search/' + keyword, true);
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
request.open('GET', 'http://localhost:8080/TheSWAmP-2.0.3.RELEASE/store/events', true);
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
                card.setAttribute('onClick',`window.location = "/TheSWAmP-2.0.3.RELEASE/html/ticket.html#"+${event.eventId}`);

                const h1 = document.createElement('h1');
                h1.textContent = event.title;
                h1.setAttribute('style', 'background-color:orange');

                const date = document.createElement('p');
                var dateFormat = event.date.substr(6) + '-' + event.date.substr(0,4);
                date.textContent = `Date: ${dateFormat}`;
                date.setAttribute("style", 'color:#343a40');

                const desc = document.createElement('p');
                desc.textContent = event.desc;
                desc.setAttribute("style", 'color:#343a40;margin-bottom:2rem');

                container.appendChild(card);
                card.appendChild(h1);
                card.appendChild(date);
                card.appendChild(desc);

                if(isAdmin){
                    var updateBtn = document.createElement('button');
                    updateBtn.setAttribute('type', 'button');
                    updateBtn.setAttribute('class', 'btn btn-dark');
                    updateBtn.setAttribute('style', 'margin:0 0 0 14rem;position:absolute;bottom:1rem;');
                    updateBtn.setAttribute('class', 'btn btn-dark');
                    updateBtn.setAttribute('onclick', `updateEvent(event,${event.eventId})`);
                    updateBtn.innerText = 'Update';
                    card.appendChild(updateBtn);
                }
            });
}

function updateEvent(event, id){
    event.stopPropagation();
    window.location = "/TheSWAmP-2.0.3.RELEASE/html/updateEvent.html#"+id;
}

request.send();