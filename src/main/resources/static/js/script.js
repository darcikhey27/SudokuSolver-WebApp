$(document).ready(init);

function init() {
    $.ajaxSetup({
        cache: false,
        contentType:"application/json; charset=utf-8",
        dataType:"json"
    });
    $("#POST").on("click", add);
    $("#GET").on("click", get);
    $("#UPDATE").on("click", update);
    $("#DELETE").on("click", deletee);
    $("#show-all").on("click", showAll);


}
function showAll() {
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8090/get-all',
        success: function (data) {
            $("#output-all").html(JSON.stringify(data));

            //TODO: show all movies in the front-end


        }
    });

    console.log("add function finished");
}
function add() {
    console.log("clicked add button");
    var name = $("#city-name").val();
    var nameReg = /^[a-zA-Z\s+]+/;
    if(!name.match(nameReg)) {
        alert("bad city name");
        return;
    }
        console.log("the name of the city is "+ name);
    var formdata = {city_name : name};
    $.ajax({
        type: 'POST',
        url: 'http://localhost:8090/add-city',
        data: JSON.stringify(formdata),
        success: function (data) {
            // console.log("success");
            // $("p").html("city was added");
        }
    });
    $("#outputPOST").html("city "+name + " was added");
    console.log("add function finished");

}


function get() {
    // get info for one city by cityname
    var name = $("#city-name").val();
    var nameReg = /^[a-zA-Z]+/;
    if(!name.match(nameReg)) {
        alert("bad city name");
        return;
    }
    console.log(name);
    var formdata = {city_name : name};
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8090/get-city/'+name,
        success: function (data) {
            $("#outputGET").html(JSON.stringify(data));
            var name = data.name;
            var cityID = data.id;
            var temp = data.main.temp;
            var weatherMain = data.weather[0].description;
            var weatherMain2 = data.weather[0].main;
            var icon = data.weather[0].icon;
            updateFields(cityID, name, cityID, weatherMain, weatherMain2, icon, temp);

        }
    });


}

function update() {
    console.log("clicked update button");
    var name = $("#city-name").val();
    var nameReg = /^[a-zA-Z]+/;
    if(!name.match(nameReg)) {
        alert("bad city name");
        return;
    }
    console.log("UPDATE, no yet implemented");
}

function deletee() {
    console.log("clicked delete button");
    console.log("btn click");
    var name = $("#city-name").val();
    var formdata = {city_name : name};
    console.log("city to delte is "+ name);
    $.ajax({
        type: 'DELETE',
        url: 'http://localhost:8090/delete-city/'+name,
        data: JSON.stringify(formdata),
        success: function (data) {
            console.log(data);
            $("#outputDELETE").html(name + " was deleted");
        }
    });
}


/* add a city when the user types in the city name */
function btnAddCity() {
    console.log("btn click");
    // TODO: -> validate name makesure they type a good string
    var name = $("#city-name").val();
    var formdata = {city_name : name};
    $.ajax({
        type: 'POST',
        url: 'http://localhost:8090/add-city',
        data: JSON.stringify(formdata),
        success: function (data) {
            console.log(data);
            $("#outputDELETE").html("city was added");
        }
    });
}

/* delete a city from the db */
function btnDelete() {
    var name = $("#city-name-delete").val();
    $.ajax({
        type: 'POST',
        url: 'http://darcikhey.com/api/v1/weather/city/delete/',
        data: { city_name: name },
        success: function (data) {
            $("#code-delete").append(JSON.stringify(data));
        }
    });
}

function btnUpdate() {
    var name = $("#city-name-update").val();
    $.ajax({
        type: 'POST',
        url: 'http://darcikhey.com/api/v1/weather/city/update/',
        data: { city_name: name },
        success: function (data) {
            $("#code-update").append(JSON.stringify(data));
        }
    });
}
function btnGetCityName() {
    var name = $("#city-name-get").val();
    var formdata = {city_name : name};
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/get/'+name,
        success: function (data) {
            //$("#code-get").append(JSON.stringify(data));
            console.log(data);
            var name = data.name;
            var cityID = data.id;
            var temp = data.main.temp;
            var weatherMain = data.weather[0].description;
            var weatherMain2 = data.weather[0].main;
            var icon = data.weather[0].icon;
            updateFields(cityID, name, cityID, weatherMain, weatherMain2, icon, temp);
        }
    });
}
/* update the fields for each temp widget via a json object */
function updateFields(id, name, cityID, description, main, icon, temp) {
    console.log("this data should be updated");
    console.log(id);
    console.log(name);
    console.log(cityID);
    console.log(description);
    console.log(icon);
    console.log(temp);
    //TODO: get a hold of all field

    $("#my-table").append("<td id='tr" + cityID + "'></td>");
    $("#my-table td:last").append(
        "<div class='widget'><h3 id='city-name'>City: " + name + "</h3><p id='temp'>temp: " + temp + "</p><p id='icon-url'></p><p id='description'>Description: "+main +", "+ description + " </p><p id='city-id'>CityID: " + cityID + "</p>"+
        "</div>"





    // $("#my-table").append("<tr id='tr" + cityID + "'></tr>");
    // $("#my-table tr:last").append(
    //     "<td><div class='widget'><h3 id='city-name'>City: " + name + "</h3><p id='temp'>temp: " + temp + "</p><p id='icon-url'></p><p id='description'>Description: "+main +", "+ description + " </p><p id='city-id'>CityID: " + cityID + "</p>"+
    //     "</div></td>"

    );

    // $("my-table tr:last").append("<td><button class='btn btn-primary btn-edit'>edit</button><button class='btn btn-danger btn-delete'>delete</button></td>");
    // $("#city-name").append(name);
    // $("#temp").append(temp);
    var img = $('<img>'); //Equivalent: $(document.createElement('img'))
    img.attr('src', "http://openweathermap.org/img/w/" + icon + ".png");
    img.appendTo('#tr' + cityID + ' #icon-url');
    // $("#description").append(description);
    // $("#city-id").append(cityID);

    //TODO: make an img tag, href to http://openweathermap.org/img/w/10d.png to populate icon image

}

function btnRefreshCity() {

}
function btnDeleteCity() {

}
