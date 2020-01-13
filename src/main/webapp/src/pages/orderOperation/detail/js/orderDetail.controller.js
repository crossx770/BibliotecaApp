(function () {

    'use strict';

    angular.module("todoApp")
        .controller("OrderDetailCtrl", OrderDetailCtrl);

    OrderDetailCtrl.$inject = ['$scope', '$routeParams', 'OrderService'];

    function OrderDetailCtrl($scope, $routeParams, OrderService) {
        OrderService.get($routeParams.orderId)
            .then(function (res) {
                $scope.order = res.data;
            }, function () {
                $scope.order = [];
            });
    }



})();