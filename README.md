# SudokuSolver-WebApp
* Spring Boot, MVC
* Thymeleaf template engine
* jQuery,html/css
* AWS Elastic Beanstalk
Algorithm will solve a sudoku puzzle using Java in the backend with Spring boot framework

a JSON object will be sent to the backend using AJAX (ex)
a 0 represents an emtpy cell in the Sudoky board
```JavaScript
var testBoard = {
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

$.ajax({
        type: 'POST',
        url: 'url',
        data: JSON.stringify(testBoard),
        success: function (data) {
            // ..
        }
    });  
    ```
    
