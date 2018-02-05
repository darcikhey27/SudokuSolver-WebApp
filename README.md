# Sudoko board solved using recursion and backtracking 
* Spring Boot, MVC
* Thymeleaf template engine
* jQuery,html/css
* AWS Elastic Beanstalk . 

Algorithm will solve a sudoku puzzle using Java in the backend with Spring boot framework

a JSON object will be sent to the backend using AJAX .  
0 represents an emtpy cell in the Sudoky board . 

### input
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
### output
App will return another JSON object containing a solved Sudoku board
(ex) . 
```JavaScript
{"0":[3,1,6,5,7,8,4,9,2],  
"1":[5,2,9,1,3,4,7,6,8],  
"2":[4,8,7,6,2,9,5,3,1],  
"3":[2,6,3,4,1,5,9,8,7],  
"4":[9,7,4,8,6,3,1,2,5],  
"5":[8,5,1,7,9,2,6,4,3],  
"6":[1,3,8,9,4,7,2,5,6],  
"7":[6,9,2,3,5,1,8,7,4],   
"8":[7,4,5,2,8,6,3,1,9]} . 
```

## take it for a run . 
http://sudoku.us-west-2.elasticbeanstalk.com/
