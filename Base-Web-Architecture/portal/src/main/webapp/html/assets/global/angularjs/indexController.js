var indexApp=angular.module('indexApp', []);
indexApp.controller('indexController', function($scope,$http) {

    $scope.name = 'hello world';

    $http({
        method: 'POST',
        url: 'http://localhost:8080/base/fetchNavBar',
        headers: {'Content-Type': 'application/json'},
        params: {
        }
    }).then(function mySuccess(response) {
        $scope.navMenuResponse = response.data;

        if ($scope.navMenuResponse.status.code == '00') {
            $scope.navBarMenu = response.data.data.menuBean;
        } else {
           //todo logout etc on service not available
        }
    }, function myError(response) {
        $scope.navMenuError = response.statusText;
    });

});