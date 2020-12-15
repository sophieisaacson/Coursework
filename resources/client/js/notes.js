function FastPlanner() {
    console.log("Invoked FastPlanner()");
    const formData = new FormData(document.getElementById('InputDetails'));
    let url = "/usersnotes/add";
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