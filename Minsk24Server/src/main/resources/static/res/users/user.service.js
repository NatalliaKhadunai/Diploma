(function() {
    angular.module('app').service('userSrv', function($http) {
        let $ctrl = this;
        $ctrl.getUsers = function() {
            return $http.get('/v3/users').then(function(response){
                return response.data;
            }, function (response) {
                alert('Status code : ' + response.data.httpStatusCode + '\n'
                    + 'Message : ' + response.data.developerMessage);
            });
        };
        $ctrl.getCurrentUser = function() {
            return $http.get('/currentUser').then(function(response){
                return response.data;
            }, function (response) {
                alert('Status code : ' + response.data.httpStatusCode + '\n'
                    + 'Message : ' + response.data.developerMessage);
            });
        };
        $ctrl.getUser = function(username) {
            return $http.get('/v3/users/' + username)
                .then(function (response) {
                    return response.data;
                }, function (response) {
                    alert('Status code : ' + response.data.httpStatusCode + '\n'
                        + 'Message : ' + response.data.developerMessage);
                });
        };
    });
})();