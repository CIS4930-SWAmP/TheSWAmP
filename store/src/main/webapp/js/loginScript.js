var request = new XMLHttpRequest();
var createRequest = new XMLHttpRequest();

function verifyLogin() {
    var username = document.getElementById("username").value;
    var password = document.getElementById("password").value;
    request.open('POST', 'http://localhost:8080/TheSWAmP-2.0.3.RELEASE/store/login?username='+username+'&password='+password, true);
    request.send();
    request.onreadystatechange = function() {
        if(request.readyState === 4) {
            if (request.status === 200) {
                const temp = request.responseText;
                const split = temp.split("\ ");
                document.cookie="session="+split[2];
                window.location = "./eventList.html";
            }
            else {
                document.getElementById("warning").style.display = "block";
            }
        }
    }
}

function createUser(){
    var firstname = document.getElementById("firstname").value;
    var lastname = document.getElementById("lastname").value;
    var username = document.getElementById("createUser").value;
    var password = document.getElementById("createPass").value;
    var phone = document.getElementById("phone").value;
    var email = document.getElementById("email").value;

    var apiUrl = 'http://localhost:8080/TheSWAmP-2.0.3.RELEASE/store/users?fname='+firstname+'&lname='+lastname+ '&username='+username+'&password='+password+'&phone='+phone+'&email='+email;
    createRequest.open('POST', apiUrl, true);
    createRequest.send();
    createRequest.onreadystatechange = function () {
        if (createRequest.readyState === 4) {
            if (createRequest.status === 200) {
                document.getElementById("username").value = username;
                document.getElementById("password").value = password;
                verifyLogin();
            }
            else {
                document.getElementById("warning2").style.display = "block";
            }
        }
    }
}

function logout() {
    console.log(document.cookie);
    var apiUrl = 'http://localhost:8080/TheSWAmP-2.0.3.RELEASE/store/login/
}