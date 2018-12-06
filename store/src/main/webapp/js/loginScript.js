function verifyLogin() {
    var loginRequest = new XMLHttpRequest();
    var username = document.getElementById("username").value;
    var password = document.getElementById("password").value;
    var url = 'https://the-swamp.herokuapp.com/store/login?username='+username.toString()+'&password='+password.toString();
    loginRequest.open('POST', url, true);
    loginRequest.send();
    loginRequest.onreadystatechange = function() {
        if(loginRequest.readyState === 4) {
            if (loginRequest.status === 200) {
                const temp = loginRequest.responseText;
                const split = temp.split("\ ");
                localStorage['session'] = split[2].toString();
                userInfo.userId = split[7];
                localStorage['id'] = userInfo.userId;
                window.location = "./html/eventTile.html";
            }
            else {
                alert('Unable to log in at this time. Try again later');
            }
        }
    }
}

function createUser() {
    var isValid = false;
    var createRequest = new XMLHttpRequest();
    var firstname = document.getElementById("firstname").value;
    var lastname = document.getElementById("lastname").value;
    var username = document.getElementById("createUsername").value;
    var password = document.getElementById("createPass").value;
    var phone = document.getElementById("phone").value;
    var email = document.getElementById("email").value;

    var apiUrl = 'https://the-swamp.herokuapp.com/store/users?fname='+firstname+'&lname='+lastname+ '&username='+username+'&password='+password+'&phone='+phone+'&email='+email;
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
                alert('Unable to create user at this time. Try again later');
            }
        }
    }
}

function logout() {
    var logoutRequest = new XMLHttpRequest();
    const session = localStorage['session'];
    var apiUrl = 'https://the-swamp.herokuapp.com/store/login/' + session;
    logoutRequest.open('DELETE', apiUrl, true);
    logoutRequest.send();
    logoutRequest.onreadystatechange = function () {
        if (logoutRequest.readyState === 4) {
            if (logoutRequest.status === 200) {
                localStorage['session'] = null;
                localStorage['id'] = null;
                window.location = "../";
            }
            else {
                alert('Unable to log out at this time. Try again later');
            }
        }
    }
}

function clearCookie() {
    localStorage['session'] = null;
}