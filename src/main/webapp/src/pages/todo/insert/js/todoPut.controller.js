(function () {

    'use strict';

    angular.module("todoApp")
        .controller("TodoPutCtrl", TodoPutCtrl);

    TodoPutCtrl.$inject = ['$scope', 'TodoService'];


    function TodoPutCtrl($scope,TodoService,$window) {

        $scope.insertCartiFunction= function()
        {
            TodoService.post12($scope.carti);
            window.location.href="/";
        }
    }
})();