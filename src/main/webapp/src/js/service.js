(function () {

    'use strict';

    angular.module("todoApp")
        .service("TodoService", TodoService);

    TodoService.$inject = ['$http'];

    function TodoService($http) {
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