(function () {

    'use strict';

    angular.module("todoApp")
        .controller("CartiDeleteCtrl", CartiDeleteCtrl);

    CartiDeleteCtrl.$inject = ['$scope', '$routeParams', 'CartiService'];

    function CartiDeleteCtrl($scope, $routeParams, CartiService,$window) {
        CartiService.del($routeParams.cartiId)
            .then(function (res) {
            $scope.carti = res.data;
                //window.location.href="/";
                window.setTimeout(function() {
                    window.location.href = "#/cartiList";
                }, 500);
        }, function () {
            $scope.carti = [];
        });
    }



})();