(function () {

    'use strict';

    angular.module("todoApp")
        .controller("OrderUpdtCtrl", OrderUpdtCtrl);

    OrderUpdtCtrl.$inject = ['$scope', '$routeParams', 'OrderService'];

    function OrderUpdtCtrl($scope,$routeParams, OrderService) {
        OrderService.get($routeParams.orderId)
            .then(function (res) {
                $scope.order = res.data;
            }, function () {
                $scope.order = [];
            });

        $scope.myFunction = function () {
            OrderService.put($routeParams.orderId,$scope.order)
            .then(function (res) {
                window.location.href="#/orderList";
            });
         }

    }

})();