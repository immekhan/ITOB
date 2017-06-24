angular.module('loginApp', []).controller('loginController', function($scope, $http , $window ) {

    $scope.idOrgUnit='01';
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
    $scope.signUpErrorVisible =false;
    $scope.firstName='';
    $scope.lastName='';
    $scope.emailId='';
    $scope.mobileNo='';
    $scope.userName='';
    $scope.password='';
    $scope.rpassword='';

    $scope.signUp=function($event) {
        $event.preventDefault();//this is to prevent default page refresh
        $scope.signUpError='';
        $scope.signUpErrorVisible =false;

        if($scope.password == $scope.rpassword) {

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
                    'password': $scope.password,
                    'rpassword': $scope.rpassword,
                    'idOrgUnit': $scope.idOrgUnit
                }
            }).then(function mySuccess(response) {
                $scope.singUpResponse = response.data;

                if ($scope.singUpResponse.status.code == '00') {

                    $scope.registerFormVisible = false;
                    $scope.signUpSuccessMsgVisible = true;

                    //empty all fields
                    $scope.firstName = '';
                    $scope.lastName = '';
                    $scope.emailId = '';
                    $scope.mobileNo = '';
                    $scope.userName = '';
                    $scope.password = '';
                    $scope.rpassword = '';
                } else {
                    $scope.signUpError = $scope.singUpResponse.status.msg;
                    $scope.signUpErrorVisible = true;
                }
            }, function myError(response) {
                $scope.signUpError = response.statusText;
                $scope.signUpErrorVisible = true;
            });
        }

    }

    $scope.showRegisterForm=function(){
        // $scope.loginFormVisible=false;
        $scope.registerFormVisible=true;
    }

    $scope.showLoginForm=function($event){
        $event.preventDefault();//this is to prevent default page refresh
        $scope.signUpSuccessMsgVisible = false;
        // $scope.loginFormVisible=true;

        //empty all fields
        $scope.signUpErrorVisible =false;
        $scope.signUpError='';
        $scope.firstName='';
        $scope.lastName='';
        $scope.emailId='';
        $scope.mobileNo='';
        $scope.userName='';
        $scope.password='';
        $scope.rpassword='';
    }

});