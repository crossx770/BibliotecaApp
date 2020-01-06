(function () {

    'use strict';

    angular.module("todoApp")
        .controller("TodoDetailCtrl", TodoDetailCtrl);

    TodoDetailCtrl.$inject = ['$scope', '$routeParams', 'TodoService'];

    function TodoDetailCtrl($scope, $routeParams, TodoService) {
        TodoService.get($routeParams.cartiId)
            .then(function (res) {
                $scope.carti = res.data;
            }, function () {
                $scope.carti = [];
            });
    }



})();