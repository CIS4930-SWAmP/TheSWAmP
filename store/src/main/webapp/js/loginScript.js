var request = new XMLHttpRequest();
var createRequest = new XMLHttpRequest();

function verifyLogin() {
    var username = document.getElementById("username").value;
    request.open('GET', 'http://localhost:8080/store-2.0.3.RELEASE/store/customers/'+username, true);
    request.send();
    request.onreadystatechange = function() {
        if(request.readyState === 4) {
            if (request.status === 200) {
                document.cookie = "username="+username + "; path=/";
                window.location = "../html/eventTile.html";
            }
            else {
                document.getElementById("warning").style.display = "block";
            }
        }
    }
}

function createUser(){
    var username = document.getElementById("pass1").value;
    var email = document.getElementById("email1").value;
    var fName = document.getElementById("pass2").value;
    var lName = document.getElementById("pass3").value;

    var apiUrl = 'http://localhost:8080/store-2.0.3.RELEASE/store/customers?fname='+fName+'&lname='+lName+ '&username='+username+'&email='+email;
    createRequest.open('POST', apiUrl, true);
    createRequest.send();
    createRequest.onreadystatechange = function () {
        if (createRequest.readyState === 4) {
            if (createRequest.status === 200) {
                document.cookie = "username="+username + "; path=/";
                window.location = "../html/eventTile.html";
            }
            else {
                document.getElementById("warning2").style.display = "block";
            }
        }
    }
}