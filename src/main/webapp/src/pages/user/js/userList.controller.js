(function () {

    'use strict';

    angular.module("todoApp")
        .controller("OrderUserCtrl", OrderUserCtrl);

    OrderUserCtrl.$inject = ['$scope', 'OrderService'];

    function OrderUserCtrl($scope, OrderService) {
        OrderService.listUser()
            .then(function (res) {
                $scope.order = res.data;
            }, function () {
                $scope.order = [];
            });
    }



})();