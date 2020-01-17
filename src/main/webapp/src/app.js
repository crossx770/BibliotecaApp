(function () {
    'use strict';

    angular.module('todoApp', ['ngRoute'])
        .config(Config);

    Config.$inject = ['$routeProvider', '$locationProvider'];

    function Config($routeProvider, $locationProvider) {
        $locationProvider.hashPrefix('');

        $routeProvider
            .when('/homeAdmin',
            {
                templateUrl: 'src/pages/homeAdmin.html'
            })
            .when('/login',
            {
                templateUrl: 'src/pages/login.html'
            })

            .when('/error',
            {
                templateUrl: 'src/pages/error.html'
            })
            .when('/userList',
            {
                templateUrl: 'src/pages/user/user.html',
                controller: 'OrderUserCtrl'
            })
            .when('/cartiList',
            {
                templateUrl: 'src/pages/cartiOperation/list/listCarti.html',
                controller: 'CartiListCtrl'

            })
            .when('/cartiDetail/:cartiId',
                {
                    templateUrl: 'src/pages/cartiOperation/details/detail.html',
                    controller: 'CartiDetailCtrl'
                })
            .when('/cartiDelete/:cartiId',
                {
                    templateUrl: 'src/pages/cartiOperation/delete/delete.html',
                    controller: 'CartiDeleteCtrl'
                })
            .when('/cartiPut',
                {
                    templateUrl: 'src/pages/cartiOperation/insert/insert.html',
                    controller: 'CartiPutCtrl'
                })
            .when('/cartiUpdate/:cartiId',
                {
                    templateUrl: 'src/pages/cartiOperation/update/update.html',
                    controller: 'CartiUpdtCtrl'
                })
             .when('/orderList',
                 {
                     templateUrl: 'src/pages/orderOperation/list/list.html',
                     controller: 'OrderListCtrl'
                 })
             .when('/orderDetail/:orderId',
                 {
                     templateUrl: 'src/pages/orderOperation/detail/detail.html',
                     controller: 'OrderDetailCtrl'
                 })
              .when('/orderDelete/:orderId',
                {
                    templateUrl: 'src/pages/orderOperation/delete/delete.html',
                    controller: 'OrderDeleteCtrl'
                })
              .when('/orderPut',
                {
                    templateUrl: 'src/pages/orderOperation/insert/insert.html',
                    controller: 'OrderPutCtrl'
                })
              .when('/orderUpdate/:orderId',
                {
                    templateUrl: 'src/pages/orderOperation/update/update.html',
                    controller: 'OrderUpdtCtrl'
                })
           .otherwise({redirectTo: '/login'});
    }

})();
