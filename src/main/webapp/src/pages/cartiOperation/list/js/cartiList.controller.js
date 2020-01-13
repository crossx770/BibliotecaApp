(function () {

    'use strict';

    angular.module("todoApp")
        .controller("CartiListCtrl", CartiListCtrl);

    CartiListCtrl.$inject = ['$scope', 'CartiService'];

    function CartiListCtrl($scope, CartiService) {
        CartiService.list()
            .then(function (res) {
                $scope.carti = res.data;
            }, function () {
                $scope.carti = [];
            });
    }



})();