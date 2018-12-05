function verify() {
    var verifyRequest = new XMLHttpRequest();
    var session = localStorage['session'];
    console.log('before', session);
    if(session === null ) {
        localStorage['session'] = null;
        window.location = "../";
    }
    // if(session === "" || session === null ) {session = "1";}
    console.log('session', session);
    const apiUrl = 'http://localhost:8080/TheSWAmP-2.0.3.RELEASE/store/login?sessionId=' + session.toString();
    console.log(apiUrl);
    verifyRequest.open('GET', apiUrl, true);
    verifyRequest.send();
    verifyRequest.onload = function () {
        // console.log('status', request.status);
        if (verifyRequest.readyState === 4) {
            if (verifyRequest.status === 200) {
                var res = JSON.parse(verifyRequest.responseText);
                console.log('verify', JSON.parse(verifyRequest.responseText));
                userInfo.userId = res.userId;
                userInfo.isAdmin = res.isAdmin;
                console.log(userInfo.userId);
                document.getElementById("username").innerText = res.username;
            }
            else {
                localStorage['session'] = null;
                window.location = '../';
            }
        }
    }
}