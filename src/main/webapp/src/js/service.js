(function () {

    'use strict';

    angular.module("todoApp")
        .service("OrderService", OrderService);

    angular.module("todoApp")
            .service("CartiService", CartiService);

    OrderService.$inject = ['$http'];
    CartiService.$inject = ['$http'];




    function OrderService($http) {
      return {
          list: function () {
            return $http.get('order');
          },
          listUser: function () {
            return $http.get('order');
          },
          del:function(id){
              var requestConfig = {
                  params: {id:id}
              };
              return $http.delete('order',requestConfig);
          },
          post12:function(order){
              return $http.post('order',order,{});
          },
          put:function(id,order){
              var requestConfig={
                  params:{id:id}
              };
              return $http.put('order',order,requestConfig);
          },
          get: function (id) {
              var requestConfig = {
                  params: {id: id}
              };
              return $http.get('order', requestConfig);
          }
      };
    }

    function CartiService($http) {
        return {
            list: function () {
                return $http.get('carti');
            },

            get: function (id) {
                var requestConfig = {
                    params: {id: id}
                };
                return $http.get('carti', requestConfig);
            },

            del:function(id){
                var requestConfig = {
                    params: {id:id}
                };
                return $http.delete('carti',requestConfig);
            },

            post12:function(carti){
                return $http.post('carti',carti,{});
            },

            put:function(id,carti){
                var requestConfig={
                    params:{id:id}
                };
                return $http.put('carti',carti,requestConfig);
            }


        };
    }



})();