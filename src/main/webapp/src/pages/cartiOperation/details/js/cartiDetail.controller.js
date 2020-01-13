(function () {

    'use strict';

    angular.module("todoApp")
        .controller("CartiDetailCtrl", CartiDetailCtrl);

    CartiDetailCtrl.$inject = ['$scope', '$routeParams', 'CartiService'];

    function CartiDetailCtrl($scope, $routeParams, CartiService) {
        CartiService.get($routeParams.cartiId)
            .then(function (res) {
                $scope.carti = res.data;
            }, function () {
                $scope.carti = [];
            });
    }



})();