var verifyRequest = new XMLHttpRequest();
function verify() {
    var session = readCookie("session");
    if(session === "" || session === null ) {session = "1";}
    console.log('session', session);
    const apiUrl = 'http://localhost:8080/TheSWAmP-2.0.3.RELEASE/store/login?sessionId=' + session.toString();
    console.log(apiUrl);
    verifyRequest.open('GET', apiUrl, true);
    verifyRequest.send();
    verifyRequest.onload = function () {
        // console.log('status', request.status);
        if (verifyRequest.readyState === 4) {
            if (verifyRequest.status === 200) {
                console.log('verify', verifyRequest.responseText);
            }
            else {
                document.cookie="session="+"";
                window.location = '../';
            }
        }
    }
}