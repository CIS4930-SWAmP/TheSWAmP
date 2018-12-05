function verifyLogin() {
    var loginRequest = new XMLHttpRequest();
    var username = document.getElementById("username").value;
    var password = document.getElementById("password").value;
    console.log('username ', username.toString());
    var url = 'http://localhost:8080/TheSWAmP-2.0.3.RELEASE/store/login?username='+username.toString()+'&password='+password.toString();
    console.log(url);
    loginRequest.open('POST', url, true);
    loginRequest.send();
    loginRequest.onreadystatechange = function() {
        if(loginRequest.readyState === 4) {
            console.log(loginRequest.status);
            if (loginRequest.status === 200) {
                const temp = loginRequest.responseText;
                console.log('temp', temp);
                const split = temp.split("\ ");
                localStorage['session'] = split[2].toString();
                console.log('session here', localStorage['session']);
                // document.cookie="session="+split[2].toString();
                // document.cookie="path=/";
                userInfo.userId = split[7];
                window.location = "./html/eventTile.html";
            }
            else {
                alert('Unable to log in at this time. Try again later');
            }
        }
    }
}

function createUser() {
    var createRequest = new XMLHttpRequest();
    var firstname = document.getElementById("firstname").value;
    var lastname = document.getElementById("lastname").value;
    var username = document.getElementById("createUsername").value;
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
                alert('Unable to create user at this time. Try again later');
            }
        }
    }
}

function logout() {
    // console.log(document.cookie);
    var logoutRequest = new XMLHttpRequest();
    // const session = readCookie("session");
    const session = localStorage['session'];
    console.log('session', session);
    var apiUrl = 'http://localhost:8080/TheSWAmP-2.0.3.RELEASE/store/login/' + session;
    logoutRequest.open('DELETE', apiUrl, true);
    logoutRequest.send();
    logoutRequest.onreadystatechange = function () {
        if (logoutRequest.readyState === 4) {
            if (logoutRequest.status === 200) {
                localStorage['session'] = null;
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