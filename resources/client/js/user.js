"use strict";
function DisplayUsername(){
    var username = Cookies.get("Username");
    var span = document.getElementById("usernamedisplay");
    span.innerHTML=username;
}

var modal = document.getElementById('signup');
window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
}
function UsersLogin() {
    debugger;
    console.log("Invoked UsersLogin() ");
    let url = "/users/login";
    let formData = new FormData(document.getElementById('LoginForm'));
    fetch(url, {
        method: "POST",
        body: formData,
    }).then(response => {
        return response.json();
    }).then(response => {
        if (response.hasOwnProperty("Error")) {
            alert(JSON.stringify(response));
        } else {
            Cookies.set("Token", response.Token);
            Cookies.set("Username", response.Username);
            Cookies.set("Email", response.Email);
            window.open("home.html", "_self");
        }
    });
}
function UsersLogout() {
    debugger;
    console.log("Invoked logout");
    let url = "/users/logout";
    fetch(url, {
        method: "POST"
    }).then(response => {
        return response.json();                 //now return that promise to JSON
    }).then(response => {
        if (response.hasOwnProperty("Error")) {
            alert(JSON.stringify(response));        // if it does, convert JSON object to string and alert
        } else {
            Cookies.remove("Token", response.Token);    //UserName and Token are removed
            Cookies.remove("Username", response.Username);
            window.open("login.html", "_self");       //open index.html in same tab
        }
    });
}



/*function getUsersList() {
    debugger;
    console.log("Invoked getUsersList()");     //console.log your BFF for debugging client side - also use debugger statement
    const url = "/users/list/";    		// API method on web server will be in Users class, method list
    fetch(url, {
        method: "GET",				//Get method
    }).then(response => {
        return response.json();                 //return response as JSON
    }).then(response => {
        if (response.hasOwnProperty("Error")) { //checks if response from the web server has an "Error"
            alert(JSON.stringify(response));    // if it does, convert JSON object to string and alert (pop up window)
        } else {
            formatUsersList(response);          //this function will create an HTML table of the data (as per previous lesson)
        }
    });
}*/

function formatUsersList(myJSONArray){
    let dataHTML = "";
    for (let item of myJSONArray) {
        dataHTML += "<tr><td>" + item.UserID + "<td><td>" + item.Username + "<tr><td>";
    }
    document.getElementById("Users").innerHTML = dataHTML;
}

function NewAccount() {
    console.log("Invoked AddUser()");
    const formData = new FormData(document.getElementById('InputUserDetails'));
    let url = "/users/add";
    fetch(url, {
        method: "POST",
        body: formData,
    }).then(response => {
        return response.json()
    }).then(response => {
        if (response.hasOwnProperty("Error")) {
            alert(JSON.stringify(response));
        } else {
            window.open("/client/home.html", "_self");
        }
    });
}

