(function() {
    angular.module('app').service('userSrv', function($http) {
        let $ctrl = this;
        $ctrl.getUsers = function() {
            return $http.get('/admin/users').then(function(response){
                return response.data;
            });
        };
        $ctrl.getCurrentUser = function() {
            return $http.get('/currentUser').then(function(response){
                return response.data;
            });
        };
        $ctrl.getUser = function(username) {
            return $http.get('admin/users/' + username)
                .then(function (response) {
                    return response.data;
                });
        };
    });
})();