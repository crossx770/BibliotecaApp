(function () {

    'use strict';

    angular.module("todoApp")
        .controller("CartiPutCtrl", CartiPutCtrl);

    CartiPutCtrl.$inject = ['$scope', 'CartiService'];


    function CartiPutCtrl($scope,CartiService,$window) {

        $scope.myFunction= function()
        {
            CartiService.post12($scope.carti);
            window.setTimeout(function() {
                window.location.href = "#/cartiList";
            }, 500);
        }
    }
})();