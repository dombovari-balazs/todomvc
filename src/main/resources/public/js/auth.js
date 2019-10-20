var script = document.createElement('script');
script.src = 'https://code.jquery.com/jquery-3.4.1.min.js';
script.type = 'text/javascript';
document.getElementsByTagName('head')[0].appendChild(script);

function login(username, password) {
    var settings = {
        "async": true,
        "crossDomain": true,
        "url": "http://localhost:8080/auth/signin",
        "method": "POST",
        "headers": {
            "Content-Type": "application/json",
            "cache-control": "no-cache",
            "Postman-Token": "8367a0df-fb3b-4eeb-ae53-6f48dad2938b"
        },
        "processData": false,
        "data": "{\n\t\"username\": \""+username+"\",\n\t\"password\" : \""+password+"\"\n}"
    }


    $.ajax(settings).done(function (response) {

        console.log(response);
        console.log(response.token);
        const token = response.token;
        document.cookie = "token=" + response.token;
        //todo redirect
        //redirect(token);

    });



}

function clearCookies(){
    document.cookie.split(";").forEach(function(c) { document.cookie = c.replace(/^ +/, "").replace(/=.*/, "=;expires=" + new Date().toUTCString() + ";path=/"); });

}

document.getElementById("logout").addEventListener("click", clearCookies);


document.getElementById('sign_in_admin').addEventListener('click', () => {
    login("admin","password")});



document.getElementById('sign_in_user').addEventListener('click', () => {
    login("user","password")});