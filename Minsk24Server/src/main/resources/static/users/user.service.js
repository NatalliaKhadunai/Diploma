(function() {
    angular.module('app').service('userSrv', function($http) {
        let $ctrl = this;
        $ctrl.getUsers = function() {
            return $http.get('/users').then(function(response){
                return response.data;
            });
        };
    });
})();