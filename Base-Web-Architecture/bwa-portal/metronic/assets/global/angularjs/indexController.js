var indexApp=angular.module('indexApp', []);
indexApp.controller('indexController', function($scope,$http) {

    $scope.name = 'hello world';

    $http({
        method: 'POST',
        url: 'http://localhost:8080/base/getNavMenu',
        headers: {'Content-Type': 'application/json'},
        params: {
            'idRole': 'ROLE_TEST',
            'customerId': 9,
            'idOrgUnit': '01'
        }
    }).then(function mySuccess(response) {
        $scope.navMenuResponse = response.data;

        if ($scope.navMenuResponse.status.code == '00') {
            $scope.navBarMenu = response.data.menuBean;
        } else {
           //todo logout etc on service not available
        }
    }, function myError(response) {
        $scope.navMenuError = response.statusText;
    });

});