function updateProfile() {
    var fname = document.getElementById("fname").value;
    var lname = document.getElementById("lname").value;
    var username = document.getElementById("username").innerHTML;
    var password = document.getElementById("password").value;
    var phone = document.getElementById("phone").value;
    var email = document.getElementById("email").value;
    var updateRequest = new XMLHttpRequest();
    url = 'http://localhost:8080/TheSWAmP-2.0.3.RELEASE/store/users?fname='+fname+'&lname='+lname+'&username='+username+'&password='+password+'&phone='+phone+'&email=' + email;
    updateRequest.open('PUT', url, true);
    updateRequest.send();
    updateRequest.onreadystatechange = function() {
        if(updateRequest.readyState === 4) {
            if(updateRequest.status === 200) {
                alert('Updated information successfully');
            }
            else {
                alert('Unable to update information. Please try again later');
            }
        }
    }
}

function getProfileInfo(username) {
    var getProfileRequest = new XMLHttpRequest();
    url = 'http://localhost:8080/TheSWAmP-2.0.3.RELEASE/store/users/'+username;
    getProfileRequest.open('GET', url, true);
    getProfileRequest.send();
    getProfileRequest.onload = function() {
        if(getProfileRequest.readyState === 4) {
            if(getProfileRequest.status === 200) {
                var res = JSON.parse(getProfileRequest.responseText);
                document.getElementById("fname").value = res.fname;
                document.getElementById("lname").value = res.lname;
                document.getElementById("phone").value = res.phone;
                document.getElementById("email").value = res.email;
                document.getElementById("password").value = res.password;
            }
            else {
                alert('Unable to obtain information. Please try again later');
            }
        }
    }
}

var purchaseRequest = new XMLHttpRequest();
var sellerId = localStorage['id'];
sellerId = sellerId.replace(/\D/g,'');
var url = 'http://localhost:8080/TheSWAmP-2.0.3.RELEASE/store/tickets/purchased/'+sellerId;
purchaseRequest.open('GET', url, true);
purchaseRequest.send();
purchaseRequest.onload = function() {
    if(purchaseRequest.readyState === 4) {
        if(purchaseRequest.status === 200) {
            var res = JSON.parse(purchaseRequest.responseText);
            res.forEach(ticket => {
                //Table Data
                const table = document.getElementById('pTickets');
                const list = document.createElement('tr');

                //columns
                const name = document.createElement('td');
                const date = document.createElement('td');

                var eventDet = new XMLHttpRequest();
                eventDet.open('GET', 'http://localhost:8080/TheSWAmP-2.0.3.RELEASE/store/events/' + ticket.eventId);
                eventDet.send();
                eventDet.onreadystatechange = function () {
                    if(eventDet.readyState === 4){
                        if(eventDet.status >= 200 && eventDet.status <= 400){
                            var data = JSON.parse(eventDet.responseText);

                            name.textContent = data.title;

                            var dateFormat = data.date.substr(5) + '-' + data.date.substr(0,4);
                            date.textContent = dateFormat;
                        }
                        else{
                            console.log('Error');
                        }
                    }
                };

                const price = document.createElement('td');
                price.textContent = '$'+ticket.price;

                //Append Columns to list
                list.appendChild(name);
                list.appendChild(date);
                list.appendChild(price);

                //Append list to table
                table.appendChild(list);
            })
        }
        else {
           console.log('Error');
        }
    }
};

var today = new Date();
var soldRequest = new XMLHttpRequest();
var buyerId = localStorage['id'];
buyerId = sellerId.replace(/\D/g,'');
var url = 'http://localhost:8080/TheSWAmP-2.0.3.RELEASE/store/tickets/sold/'+buyerId;
soldRequest.open('GET', url, true);
soldRequest.send();
soldRequest.onload = function() {
    if(soldRequest.readyState === 4) {
        if(soldRequest.status === 200) {
            var res = JSON.parse(soldRequest.responseText);
            res.forEach(ticket => {
                //Table Data
                const table = document.getElementById('sTickets');
                const list = document.createElement('tr');

                //columns
                const name = document.createElement('td');
                const date = document.createElement('td');
                const update = document.createElement('td');
                const btn = document.createElement('button');

                var eventDet1 = new XMLHttpRequest();
                eventDet1.open('GET', 'http://localhost:8080/TheSWAmP-2.0.3.RELEASE/store/events/' + ticket.eventId);
                eventDet1.send();
                eventDet1.onreadystatechange = function () {
                    if(eventDet1.readyState === 4){
                        if(eventDet1.status >= 200 && eventDet1.status <= 400){
                            var data = JSON.parse(eventDet1.responseText);

                            name.textContent = data.title;

                            var dateFormat = data.date.substr(5) + '-' + data.date.substr(0,4);
                            date.textContent = dateFormat;

                            btn.setAttribute('onclick', `updateModal(${ticket.id}, '${data.title}')`);
                        }
                        else{
                            console.log('Error');
                        }
                    }
                };


                btn.setAttribute('type', 'button');
                btn.setAttribute('class', 'btn btn-dark');
                btn.setAttribute('style', 'float:right');
                btn.innerText = 'Update';

                update.appendChild(btn);


                const price = document.createElement('td');
                price.textContent = ticket.price;

                //Append Columns to list
                list.appendChild(name);
                list.appendChild(date);
                list.appendChild(price);
                list.appendChild(update);

                //Append list to table
                table.appendChild(list);
            })
        }
        else {
            console.log('Error');
        }
    }
};

function updateModal(id, title){
    document.getElementById('delete').setAttribute('onclick' , 'deleteTicket('+id+')');
    document.getElementById('eventNames').innerHTML = title;
    var ticketInfo = new XMLHttpRequest();
    ticketInfo.open('GET', 'http://localhost:8080/TheSWAmP-2.0.3.RELEASE/store/tickets/'+id );
    ticketInfo.send();
    ticketInfo.onreadystatechange = function() {
        if(ticketInfo.readyState === 4){
            if(ticketInfo.status >= 200 && ticketInfo.status < 400){
                var res = JSON.parse(ticketInfo.responseText);
                document.getElementById('price').value = res.price;
                document.getElementById('quantity').value = res.quantity;
                document.getElementById('availability').value = res.availability;
                document.getElementById('purchase').checked = res.purchased;
            }
            else{
                console.log('Error');
            }
        }
    };
    document.getElementById('submit').setAttribute('onclick', 'updateTicket('+id+')');
    document.getElementById('update').click();


}

function deleteTicket(id){
   var deleteTicket = new XMLHttpRequest();
   deleteTicket.open('DELETE', 'http://localhost:8080/TheSWAmP-2.0.3.RELEASE/store/tickets/delete/'+id);
   deleteTicket.send();
   deleteTicket.onreadystatechange = function() {
       if(deleteTicket.readyState === 4){
           if(deleteTicket.status >= 200 && deleteTicket.status < 400){
               document.getElementById('close').click();
               location.reload();
           }
           else{
               console.log('Error');
           }
       }
   }
}

function updateTicket(id) {
    var price = document.getElementById('price').value;
    var quantity = document.getElementById('quantity').value;
    var availability = document.getElementById('availability').value;
    var purchased = document.getElementById('purchase').checked;
    var purchaser = document.getElementById('purchaser').value;
    console.log(purchaser);
    var updateTicket = new XMLHttpRequest();
    var url = 'http://localhost:8080/TheSWAmP-2.0.3.RELEASE/store/tickets?price='+price+'&avil=' + availability + '&ticketId='+id+ '&purch=' + purchased+ '&username=' + purchaser + '&quantity=' + quantity;
    updateTicket.open('PUT', url, true);
    updateTicket.send();
    updateTicket.onreadystatechange = function() {
        if(updateTicket.readyState === 4){
            if(updateTicket.status >= 200 && updateTicket.status < 400){
                document.getElementById('close').click();
                location.reload();
            }
            else{
                console.log('Error');
            }
        }
    }
}
