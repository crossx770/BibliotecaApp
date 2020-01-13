(function () {

    'use strict';

    angular.module("todoApp")
        .controller("OrderDeleteCtrl", OrderDeleteCtrl);

    OrderDeleteCtrl.$inject = ['$scope', '$routeParams', 'OrderService'];

    function OrderDeleteCtrl($scope, $routeParams, OrderService,$window) {
        OrderService.del($routeParams.orderId)
            .then(function (res) {
            $scope.order = res.data;
                //window.location.href="/";
                window.setTimeout(function() {
                    window.location.href = "#/orderList";
                }, 500);
        }, function () {
            $scope.order = [];
        });
    }



})();