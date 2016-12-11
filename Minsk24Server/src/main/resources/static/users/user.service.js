(function() {
    angular.module('app').service('userSrv', function($http) {
        let $ctrl = this;
        $ctrl.getUsers = function() {
            return $http.get('/users').then(function(response){
                return response.data;
            });
        };
        $ctrl.getCurrentUser = function() {
            return $http.get('/currentUser').then(function(response){
                return response.data;
            });
        };
        $ctrl.getUser = function(username) {
            return $http.get('/getUser', {
                    params: {
                        'username': username
                    }
                })
                .then(function (response) {
                    return response.data;
                });
        };
    });
})();