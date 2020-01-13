(function () {

    'use strict';

    angular.module("todoApp")
        .controller("OrderListCtrl", OrderListCtrl);

    OrderListCtrl.$inject = ['$scope', 'OrderService'];

    function OrderListCtrl($scope, OrderService) {
        OrderService.list()
            .then(function (res) {
                $scope.order = res.data;
            }, function () {
                $scope.order = [];
            });
    }



})();