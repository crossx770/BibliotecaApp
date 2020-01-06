(function () {

    'use strict';

    angular.module("todoApp")
        .controller("TodoUpdtCtrl", TodoUpdtCtrl);

    TodoUpdtCtrl.$inject = ['$scope', '$routeParams', 'TodoService'];

    function TodoUpdtCtrl($scope,$routeParams, TodoService) {
        TodoService.get($routeParams.cartiId)
            .then(function (res) {
                $scope.carti = res.data;
            }, function () {
                $scope.carti = [];
            });

        $scope.updateCartiFunction = function () {
            TodoService.put($routeParams.cartiId,$scope.carti)
            window.location.href="/";
        }

        }

    }

})();