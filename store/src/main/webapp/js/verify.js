function verify() {
    var verifyRequest = new XMLHttpRequest();
    var session = localStorage['session'];
    if(session === null ) {
        localStorage['session'] = null;
        window.location = "../";
    }
    const apiUrl = 'https://the-swamp.herokuapp.com/store/login?sessionId=' + session.toString();
    verifyRequest.open('GET', apiUrl, true);
    verifyRequest.send();
    verifyRequest.onload = function () {
        if (verifyRequest.readyState === 4) {
            if (verifyRequest.status === 200) {
                var res = JSON.parse(verifyRequest.responseText);
                userInfo.userId = res.userId;
                userInfo.isAdmin = res.isAdmin;
                userInfo.username = res.username;
                const url = window.location.href;
                const endpoint = url.split('/');
                console.log('endpoint', endpoint);
                if(endpoint[4] === 'profile.html' ) {
                    getProfileInfo(res.username);
                }
                document.getElementById("username").innerText = res.username;
            }
            else {
                localStorage['session'] = null;
                window.location = '../';
            }
        }
    }
}