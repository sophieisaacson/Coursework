function displayUserInfo(){
    var username = Cookies.get("Username");
    var email = Cookies.get("Email");
    document.getElementById("emaillbl").innerHTML = email
    document.getElementById("usernamelbl").innerHTML = username
    var span = document.getElementById("usernamedisplay");
    span.innerHTML=username;
}
