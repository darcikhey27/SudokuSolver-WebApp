$(document).ready(init);

function init() {
    generateBoard();
    $.ajaxSetup({
        cache: false,
        contentType: "application/json; charset=utf-8",
        dataType: "json"
    });
    // $("#generate-board").on("click", generateBoard);
    $("#clear-board").on("click", clearBoard);
    $("#solve").on("click", solve);
}

function solve() {
    console.log("solving the puszzle");
    // build a JSON string
    var jsonValues = {};
    for (var x = 0; x < 9; x++) {
        jsonValues[x] = [];
        for (var y = 0; y < 9; y++) {
            var item = $("#cell" + x + "-" + y).val();
            if(item == "") {
                item = "0";
            }
            jsonValues[x][y] = item;
        }
    }
    // pass the json string to the controller
    $.ajax({
        type: 'POST',
        url: 'http://localhost:8080/solve',
        data: JSON.stringify(jsonValues),
        success: function (data) {
            //console.log("callback data is");
            //console.log(data);
            //language=JQuery-CSS
            $("#output").html(JSON.stringify(data));
            fillBoard(data);
            $("#solve").attr("disabled", "disabled");
        }
    });
}

// TODO::.. fill board wth json values
function fillBoard(data) {


    for (var x = 0; x < 9; x++) {
        var array = data[x];
        for (var y = 0; y < 9; y++) {
            var cellHandle = $("#cell" + x + "-" + y);
            var currentNum = cellHandle.val();
            if(currentNum == "") {
                var num = array[y];
                cellHandle.val(num);
                cellHandle.addClass("new-numbers");

            }
            //$("#cell" + x + "-" + y).val(num);
        }
    }
    var status = $("#status");
    status.append("Done!");
    status.addClass("done");

}

function addValues() {

    console.log("adding values to cells");
    var values = {
        0: [3, 0, 6, 5, 0, 8, 4, 0, 0],
        1: [5, 2, 0, 0, 0, 0, 0, 0, 0],
        2: [0, 8, 7, 0, 0, 0, 0, 3, 1],
        3: [0, 0, 3, 0, 1, 0, 0, 8, 0],
        4: [9, 0, 0, 8, 6, 3, 0, 0, 5],
        5: [0, 5, 0, 0, 9, 0, 6, 0, 0],
        6: [1, 3, 0, 0, 0, 0, 2, 5, 0],
        7: [0, 0, 0, 0, 0, 0, 0, 7, 4],
        8: [0, 0, 5, 2, 0, 6, 3, 0, 0]
    };
    //console.log(values[0][0]);
    for (var x = 0; x < 9; x++) {
        for (var y = 0; y < 9; y++) {
            var num = values[x][y];
            if (num == "0") {
                num = "";
            }
            $("#cell" + x + "-" + y).val(num);
        }
    }
}
function clearBoard() {
    $("input").val("");
    $('#generate-board').removeAttr("disabled");
}

function generateBoard() {
    // disable button so they don't keep on making grids
    $("#generate-board").attr("disabled", "disabled");

    // Generate board
    for (var i = 0; i < 9; i++) {
        $(".table").append("<tr></tr>");
        $(".table tr:last").attr("id", "tr" + i);
        for (var k = 0; k < 9; k++) {
            $(".table tr:last").append("<td>" + "<input type='text' size='3'/></td>");
            $(".table td:last").attr("id", "td" + k);
            $(".table input:last").attr("id", "cell" + i + "-" + k);
        }
    }
    addValues();
}

