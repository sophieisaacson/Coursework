"use strict";

var modal = document.getElementById('signup');
window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
}
function UsersLogin() {
    //debugger;
    console.log("Invoked UsersLogin() ");
    let url = "/users/login";
    let formData = new FormData(document.getElementById('LoginForm'));

    fetch(url, {
        method: "POST",
        body: formData,
    }).then(response => {
        return response.json();                 //now return that promise to JSON
    }).then(response => {
        if (response.hasOwnProperty("Error")) {
            alert(JSON.stringify(response));        // if it does, convert JSON object to string and alert
        } else {
            Cookies.set("Tokens", response.Tokens);
            Cookies.set("Username", response.UserName);
            window.open("home.html", "_self");       //open index.html in same tab
        }
    });
}
function logout() {
    debugger;
    console.log("Invoked logout");
    let url = "/users/logout";
    fetch(url, {method: "POST"
    }).then(response => {
        return response.json();                 //now return that promise to JSON
    }).then(response => {
        if (response.hasOwnProperty("Error")) {
            alert(JSON.stringify(response));        // if it does, convert JSON object to string and alert
        } else {
            Cookies.remove("Tokens", response.Tokens);    //UserName and Token are removed
            Cookies.remove("Username", response.Username);
            window.open("index.html", "_self");       //open index.html in same tab
        }
    });
}

function getUsersList() {
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
}

function formatUsersList(myJSONArray){
    let dataHTML = "";
    for (let item of myJSONArray) {
        dataHTML += "<tr><td>" + item.UserID + "<td><td>" + item.Username + "<tr><td>";
    }
    document.getElementById("Users").innerHTML = dataHTML;
}

function getUser() {
    console.log("Invoked getUser()");     //console.log your BFF for debugging client side
    const Username = document.getElementById("Username").value;  //get the UserId from the HTML element with id=UserID
    //debugger;				  //debugger statement to allow you to step through the code in console dev F12
    const url = "/users/get/";       // API method on webserver
    fetch(url + Username, {                // Username as a path parameter
        method: "GET",
    }).then(response => {
        return response.json();                         //return response to JSON
    }).then(response => {
       if (response.hasOwnProperty("Error")) {         //checks if response from server has an "Error"
            alert(JSON.stringify(response));            // if it does, convert JSON object to string and alert
       } else {
           document.getElementById("DisplayOneUser").innerHTML = response.Username + " " + response.Password;  //output data
       }
    });
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
