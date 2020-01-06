(function () {

    'use strict';

    angular.module("todoApp")
        .controller("TodoDeleteCtrl", TodoDeleteCtrl);

    TodoDeleteCtrl.$inject = ['$scope', '$routeParams', 'TodoService'];

    function TodoDeleteCtrl($scope, $routeParams, TodoService,$window) {
        TodoService.del($routeParams.cartiId)
            .then(function (res) {
            $scope.carti = res.data;
                //window.location.href="/";
                window.setTimeout(function() {
                    window.location.href = "/";
                }, 500);
        }, function () {
            $scope.carti = [];
        });
    }



})();