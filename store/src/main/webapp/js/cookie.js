var userInfo = {
    userId: null,
    isAdmin: false
};

function readCookie(name){
    var decodedCookie = decodeURIComponent(document.cookie);
    var first = name + "=";
    const split = decodedCookie.split(";");
    for(var i = 0; i <split.length; i++) {
        var c = split[i];
        while (c.charAt(0) == ' ') {
            c = c.substring(1);
        }
        if (c.indexOf(first) == 0) {
            return c.substring(first.length, c.length);
        }
    }
    return "";
}