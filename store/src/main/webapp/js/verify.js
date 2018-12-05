function verify() {
    var verifyRequest = new XMLHttpRequest();
    var session = localStorage['session'];
    if(session === null ) {
        localStorage['session'] = null;
        window.location = "../";
    }
    const apiUrl = 'http://localhost:8080/TheSWAmP-2.0.3.RELEASE/store/login?sessionId=' + session.toString();
    verifyRequest.open('GET', apiUrl, true);
    verifyRequest.send();
    verifyRequest.onload = function () {
        if (verifyRequest.readyState === 4) {
            if (verifyRequest.status === 200) {
                var res = JSON.parse(verifyRequest.responseText);
                userInfo.userId = res.userId;
                userInfo.isAdmin = res.isAdmin;
                document.getElementById("username").innerText = res.username;
            }
            else {
                localStorage['session'] = null;
                window.location = '../';
            }
        }
    }
}