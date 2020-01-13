(function () {

    'use strict';

    angular.module("todoApp")
        .controller("CartiUpdtCtrl", CartiUpdtCtrl);

    CartiUpdtCtrl.$inject = ['$scope', '$routeParams', 'CartiService'];

    function CartiUpdtCtrl($scope,$routeParams, CartiService) {
        CartiService.get($routeParams.cartiId)
            .then(function (res) {
                $scope.carti = res.data;
            }, function () {
                $scope.carti = [];
            });

        $scope.myFunction = function () {
            CartiService.put($routeParams.cartiId,$scope.carti)
            .then(function (res) {
                window.location.href="#/cartiList";
            });
         }

    }

})();