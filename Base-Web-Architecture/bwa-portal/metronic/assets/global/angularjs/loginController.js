angular.module('loginApp', []).controller('loginController', function($scope, $http) {

    $scope.names = [
        {name:'Jani',country:'Norway'},
        {name:'Hege',country:'Sweden'},
        {name:'Kai',country:'Denmark'}
    ];

    $scope.firstName='firstName';
    $scope.lastName='lastName';
    $scope.emailId='abac@abc.com';
    $scope.mobileNo='0324';
    $scope.userName='userName';
    $scope.password='password';

    $scope.signUp=function() {
        $http({
            method : 'POST',
            url : 'http://localhost:8080/base/signUp',
            headers: {'Content-Type': 'application/json'},
            params : { 'firstName':$scope.firstName,
                'lastName':$scope.lastName,
                'emailId':$scope.emailId,
                'mobileNo':$scope.mobileNo,
                'userName':$scope.userName,
                'password':$scope.password
            }
        }).then(function mySuccess(response) {
            $scope.response = response.data;
        }, function myError(response) {
            $scope.response = response.statusText;
        });
    }

});