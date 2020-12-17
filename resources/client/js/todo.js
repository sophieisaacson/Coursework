function getUsersToDosList() {
    debugger;
    DisplayUsername();
    console.log("Invoked getUsersToDosList()");     //console.log your BFF for debugging client side - also use debugger statement
    const url = "/todo/list/";           // API method on web server will be in Users class, method list
    fetch(url, {
        method: "GET",          //Get method
    }).then(response => {
        return response.json();                 //return response as JSON
    }).then(response => {
        if (response.hasOwnProperty("Error")) { //checks if response from the web server has an "Error"
            alert(JSON.stringify(response));    // if it does, convert JSON object to string and alert (pop up window)
        } else {
            formatUsersToDoList(response);          //this function will create an HTML table of the data (as per previous lesson)
        }
    });
}
function formatUsersToDoList(myJSONArray){
    let dataHTML = "";
    for (let item of myJSONArray) {
        dataHTML += "<tr><th>Name</th><th>Priority</th><th>Date</th><th>Complete</th></tr>"+"<tr><td>" + item.ToDo + "</td><td>" + item.ToDoPriority + "</td><td>" + item.ToDoDate + "</td><td>" + item.ToDoComplete + "</td></tr>";
    }
    document.getElementById("UsersToDosTable").innerHTML = dataHTML;
}

function addToDo(){
    console.log("Invoked addToDo()");
    let url = "/todo/add";
    fetch(url, {
        method: "POST"
    }).then(response => {
        return response.json();                 //now return that promise to JSON
    }).then(response => {
        if (response.hasOwnProperty("Error")) {
            alert(JSON.stringify(response));        // if it does, convert JSON object to string and alert
        } else {
            window.open("todo.html", "_self");       //open index.html in same tab
        }
    });
}

