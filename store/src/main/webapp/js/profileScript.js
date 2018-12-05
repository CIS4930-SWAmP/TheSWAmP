function updateProfile() {
    var fname = document.getElementById("fname").value;
    var lname = document.getElementById("lname").value;
    var username = document.getElementById("username").value;
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
    // var username = document.getElementById("username");
    // var username = userInfo.username;
    console.log('got username', username);
    var getProfileRequest = new XMLHttpRequest();
    url = 'http://localhost:8080/TheSWAmP-2.0.3.RELEASE/store/users/'+username;
    getProfileRequest.open('GET', url, true);
    getProfileRequest.send();
    getProfileRequest.onload = function() {
        if(getProfileRequest.readyState === 4) {
            if(getProfileRequest.status === 200) {
                var res = JSON.parse(getProfileRequest.responseText);
                console.log('get info', JSON.parse(getProfileRequest.responseText));
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

// const username = document.cookie.substr(document.cookie.indexOf('=')+1);
//
// request.open('GET', 'http://localhost:8080/TheSWAmP-2.0.3.RELEASE/store/users/'+username, true);
// request.onload = function () {
//     if(request.readyState === 4) {
//         if (request.status === 200) {
//             var data = JSON.parse(this.response);
//             var userInfo = document.getElementById("userInfo");
//
//             var username = document.createElement('h4');
//             username.innerText = 'Username: ' + data.username;
//
//             var email = document.createElement('h4');
//             email.innerText = 'Email: ' + data.email;
//
//             var name = document.createElement('h4');
//             name.innerText = 'Name: ' + data.fname+ ' ' +data.lname;
//
//             var phone = document.createElement('h4');
//             name.innerText = 'Phone: ' + data.phone;
//
//             userInfo.appendChild(username);
//             userInfo.appendChild(email);
//             userInfo.appendChild(name);
//             userInfo.appendChild(phone);
//
//         } else {
//            console.log('Error');
//         }
//     }
// };
//
// function submit() {
//     var fname = document.getElementById('fname').value;
//     var lname = document.getElementById('lname').value;
//     var email = document.getElementById('email').value;
//     var phone = document.getElementById('phone').value;
//
//     var apiUrl = "http://localhost:8080/TheSWAmP-2.0.3.RELEASE/store/users?fname="+fname+"&lname="+lname+
//     "&username="+username+"&phone="+phone+"&email="+email;
//
//     requestUpdate.open('PUT', apiUrl, true );
//     requestUpdate.send();
//     requestUpdate.onreadystatechange = function (){
//         if(requestUpdate.readyState === 4){
//             if(requestUpdate.status === 200){
//                 window.location = "http://localhost:8080/TheSWAmP-2.0.3.RELEASE/html/profile.html";
//             }
//             else{
//                 console.log('Error');
//             }
//         }
//     }
// }
//
// requestHistory.open('GET',"http://localhost:8080/TheSWAmP-2.0.3.RELEASE/store/tickets/purchased/"+username, true);
// requestHistory.send();
// requestHistory.onreadystatechange = function (){
//     if(requestHistory.readyState === 4){
//         if(requestHistory.status === 200){
//             var data = JSON.parse(this.response);
//             data.forEach(product => {
//                 const history =document.getElementById('history');
//                 const div = document.createElement('div');
//                 div.setAttribute('class','border border-dark');
//                 div.setAttribute('style','padding:5px;max-width:500px;background:#e9ecef');
//
//                 const name = document.createElement('p');
//                 name.textContent = product.eventId;
//
//
//                 const price = document.createElement('p');
//                 product.description += product.price;
//                 price.textContent = `Sale Price: $${product.price}`;
//
//                 div.appendChild(name);
//                 div.appendChild(price);
//
//                 history.appendChild(div);
//             });
//         }
//         else{
//             console.log('Error');
//         }
//     }
// };
//
// request.send();
