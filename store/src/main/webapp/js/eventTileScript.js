var request = new XMLHttpRequest();
var requestKey = new XMLHttpRequest();

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
                card.setAttribute('onClick',`window.location = "/TheSWAmP-2.0.3.RELEASE/html/productDetails.html#"+${event.id}`);

                const h1 = document.createElement('h1');
                h1.textContent = event.title;
                h1.setAttribute('style', 'background-color:orange');

                const date = document.createElement('p');
                var dateFormat = event.date.substr(6) + '-' + event.date.substr(0,4);
                date.textContent = `Date: ${dateFormat}`;
                date.setAttribute("style", 'color:#343a40');

                const desc = document.createElement('p');
                desc.textContent = event.desc;
                desc.setAttribute("style", 'color:#343a40');

                container.appendChild(card);
                card.appendChild(h1);
                card.appendChild(date);
                card.appendChild(desc);
            });
}

request.send();