
var app = angular.module("myApp", []);
app.controller('appController', function ($scope, $http) {
   
    $scope.students = [];
    $scope.student = {};
    let successDelete = response => {
        $scope.students.splice($scope.students.indexOf($scope.students.filter(student => student.id == response.data)[0]), 1);
    }
    let failureDelete = error => $scope.postResultMessage = "Error with status: " + error.statusText


    var url = "http://localhost:8080/api/v1/student";
    $http.get(url).then(function (response) {
        $scope.students = response.data
        console.log($scope.students)
    }, function error(response) {
        $scope.postResultMessage = "Error with status: " + response.statusText;
    });


    $scope.delete = function (studentId) {
        $http.delete("http://localhost:8080/api/v1/student/" + studentId).then(successDelete, failureDelete)
    }


    $scope.saveRecord = function () {
        var url = "http://localhost:8080/api/v1/student/post";
        var config = {
            headers: {
                'Content-Type': 'application/json;charset=utf-8;',
                'Accept': 'application/json',
                'Access-Control-Allow-Origin': '*'
            }
        }
        let success = response => {
            if ($scope.student.id == response.data) {
            } else {
                $scope.student.id = response.data;
                $scope.students.push($scope.student);
            }
            $scope.student = {};
        }
        let failure = error => $scope.postResultMessage = "Error with status: " + error.statusText
        $http.post(url, $scope.student, config).then(success, failure);
    }

    $scope.edit = function (student) {
        $scope.student = student;
    }

    $scope.sortColumn = function (col) {
        $scope.column = col;
        if ($scope.reverse) {
            $scope.reverse = false;
        } else {
            $scope.reverse = true;
        }
    };
});








