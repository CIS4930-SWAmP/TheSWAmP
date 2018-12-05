function updateProfile() {
    var fname = document.getElementById("fname");
    var lname = document.getElementById("lname");
    var username = document.getElementById("username");
    var password = document.getElementById("password");
    var phone = document.getElementById("phone");
    var email = document.getElementById("email");
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

function getProfileInfo() {
    var username = document.getElementById("username");
    url = 'http://localhost:8080/TheSWAmP-2.0.3.RELEASE/store/users?username='+username;
    updateRequest.open('GET', url, true);
    updateRequest.send();
    updateRequest.onreadystatechange = function() {
        if(updateRequest.readyState === 4) {
            if(updateRequest.status === 200) {
                var res = JSON.parse(verifyRequest.responseText);
                document.getElementById("fname").value = res.fname;
                document.getElementById("lname").value = res.lname;
                document.getElementById("phone").value = res.phone;
                document.getElementById("email").value = res.email;
            }
            else {
                alert('Unable to obtain information. Please try again later');
            }
        }
    }
}