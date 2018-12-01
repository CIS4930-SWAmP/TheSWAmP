var request = new XMLHttpRequest();
var requestUpdate = new XMLHttpRequest();
var requestHistory = new XMLHttpRequest();

const username = document.cookie.substr(document.cookie.indexOf('=')+1);

request.open('GET', 'http://localhost:8080/store-2.0.3.RELEASE/store/customers/'+username, true);
request.onload = function () {
    if(request.readyState === 4) {
        if (request.status === 200) {
            var data = JSON.parse(this.response);
            var userInfo = document.getElementById("userInfo");

            var username = document.createElement('h4');
            username.innerText = 'Username: ' + data.username;

            var email = document.createElement('h4');
            email.innerText = 'Email: ' + data.email;

            var name = document.createElement('h4');
            name.innerText = 'Name: ' + data.fname+ ' ' +data.lname;

            userInfo.appendChild(username);
            userInfo.appendChild(email);
            userInfo.appendChild(name);

        } else {
           window.location = 'http://localhost:8080/store-2.0.3.RELEASE/html/login.html'    ;
        }
    }
};

function submit() {
    var fname = document.getElementById('fname').value;
    var lname = document.getElementById('lname').value;
    var email = document.getElementById('email').value;

    var apiUrl = "http://localhost:8080/store-2.0.3.RELEASE/store/customers?fname="+fname+"&lname="+lname+
    "&username="+username+"&email="+email;

    requestUpdate.open('PUT', apiUrl, true );
    requestUpdate.send();
    requestUpdate.onreadystatechange = function (){
        if(requestUpdate.readyState === 4){
            if(requestUpdate.status === 200){
                window.location = "http://localhost:8080/store-2.0.3.RELEASE/html/profile.html";
            }
            else{
                const errorMessage = document.createElement('marquee');
                errorMessage.textContent = `Error`;
            }
        }
    }
}

requestHistory.open('GET',"http://localhost:8080/store-2.0.3.RELEASE/store/carts/history/"+username, true);
requestHistory.send();
requestHistory.onreadystatechange = function (){
    if(requestHistory.readyState === 4){
        if(requestHistory.status === 200){
            var data = JSON.parse(this.response);
            data.forEach(product => {
                const history =document.getElementById('history');
                const div = document.createElement('div');
                div.setAttribute('class','border border-dark');
                div.setAttribute('style','padding:5px;max-width:500px;background:#e9ecef');

                const name = document.createElement('p');
                name.textContent = product.name;

                const desc = document.createElement('p');
                product.description = product.msrp;
                desc.textContent = `MSRP: ${product.msrp}`;

                const price = document.createElement('p');
                product.description += product.salePrice;
                price.textContent = `Sale Price: $${product.salePrice}`;

                div.appendChild(name);
                div.appendChild(desc);
                div.appendChild(price);

                history.appendChild(div);
            });
        }
        else{
            const errorMessage = document.createElement('marquee');
            errorMessage.textContent = `Error`;
        }
    }
};

request.send();