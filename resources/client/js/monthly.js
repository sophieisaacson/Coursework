document.addEventListener('DOMContentLoaded', function() {
    var calendarEl = document.getElementById('calendar');
    const todaysDate = new Date().toISOString().slice(0, 10);  //get todays's date as yyyy-mm-dd

    //Create JSON array, just like the data you could return from web server using fetch()...
    let myJSONArray = [
        {"title": "Dinner at Zoe's", "start": '2020-10-14'},
        {"title": "Christmas planning meeting","start": '2020-10-15'},
        {"title": "Dentist", "start": '2020-10-14'}
    ];


    var calendar = new FullCalendar.Calendar(calendarEl, {
        initialView: 'dayGridMonth',
        initialDate: todaysDate,
        headerToolbar: {
            left: 'prev,next today',
            center: 'title',
            right: 'dayGridMonth,timeGridWeek,timeGridDay'
        },

        events: myJSONArray

    });

    calendar.render();
});
