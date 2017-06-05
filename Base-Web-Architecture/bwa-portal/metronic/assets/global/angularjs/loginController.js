angular.module('loginApp', []).controller('loginController', function($scope, $http , $window) {

    $scope.names = [
        {name:'Jani',country:'Norway'},
        {name:'Hege',country:'Sweden'},
        {name:'Kai',country:'Denmark'}
    ];

    /*login models*/
    $scope.loginUserName='';
    $scope.loginPassword='';

    $scope.login=function(){
        $http({
            method : 'POST',
            url : 'http://localhost:8080/base/login',
            headers: {'Content-Type': 'application/json'},
            params :
                {
                'userName':$scope.loginUserName,
                'password':$scope.loginPassword
            }
        }).then(function mySuccess(response) {
            $scope.mySuccess = response.data;
            var url = "http://" + $window.location.host + "/base.web.architecture/bwa-portal/metronic/modules/admin/index.html";
            // $log.log(url);
            $window.location.href = url;
        }, function myError(response) {
            $scope.myError = response.statusText;
        });
    }

    /*signUp models*/
    $scope.firstName='';
    $scope.lastName='lastName';
    $scope.emailId='';
    $scope.mobileNo='0324';
    $scope.userName='';
    $scope.password='';

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
            $scope.mySuccess = response.data;
        }, function myError(response) {
            $scope.myError = response.statusText;
        });
    }

});