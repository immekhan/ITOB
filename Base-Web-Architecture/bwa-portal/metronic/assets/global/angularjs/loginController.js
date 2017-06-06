angular.module('loginApp', []).controller('loginController', function($scope, $http , $window ) {

    /*$scope.names = [
        {name:'Jani',country:'Norway'},
        {name:'Hege',country:'Sweden'},
        {name:'Kai',country:'Denmark'}
    ];*/

    // $scope.loginFormVisible=true;
    $scope.registerFormVisible=false;
    $scope.signUpSuccessMsgVisible = false;

    /*login models*/
    $scope.loginUserName='';
    $scope.loginPassword='';

    $scope.login=function($event){
        $event.preventDefault(); //this is to prevent default refresh
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

            $scope.loginResponse = response.data;

            if($scope.loginResponse == 'Login Success'){

                $scope.loginUserName='';
                $scope.loginPassword='';

                var url = "http://" + $window.location.host + "/base.web.architecture/bwa-portal/metronic/modules/admin/index.html";
                // $log.log(url);
                $window.location.href = url;
            }
        }, function myError(response) {
            $scope.loginResponseError = response.statusText;
        });
    }

    /*signUp models*/
    $scope.firstName='';
    $scope.lastName='';
    $scope.emailId='';
    $scope.mobileNo='';
    $scope.userName='';
    $scope.password='';

    $scope.signUp=function($event) {
        $event.preventDefault();//this is to prevent default page refresh

        $http({
            method: 'POST',
            url: 'http://localhost:8080/base/signUp',
            headers: {'Content-Type': 'application/json'},
            params: {
                'firstName': $scope.firstName,
                'lastName': $scope.lastName,
                'emailId': $scope.emailId,
                'mobileNo': $scope.mobileNo,
                'userName': $scope.userName,
                'password': $scope.password
            }
        }).then(function mySuccess(response) {
            $scope.singUpResponse = response.data;

            if ($scope.singUpResponse == 'Success') {

                $scope.registerFormVisible = false;
                $scope.signUpSuccessMsgVisible = true;

                //empty all fields
                $scope.firstName='';
                $scope.lastName='';
                $scope.emailId='';
                $scope.mobileNo='0324';
                $scope.userName='';
                $scope.password='';
            }
        }, function myError(response) {
            $scope.signUpResponseError = response.statusText;
        });

    }

    $scope.showRegisterForm=function(){
        // $scope.loginFormVisible=false;
        $scope.registerFormVisible=true;
    }

    $scope.showLoginForm=function($event){
        $event.preventDefault();//this is to prevent default page refresh
        $scope.signUpSuccessMsgVisible = false;
        // $scope.loginFormVisible=true;
    }

});