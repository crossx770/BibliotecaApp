(function () {

    'use strict';

    angular.module("todoApp")
        .controller("OrderPutCtrl", OrderPutCtrl);

    OrderPutCtrl.$inject = ['$scope', 'OrderService'];


    function OrderPutCtrl($scope,OrderService,$window) {

        $scope.myFunction= function()
        {
            OrderService.post12($scope.order);
            window.location.href="/#/orderList";
        }
    }
})();