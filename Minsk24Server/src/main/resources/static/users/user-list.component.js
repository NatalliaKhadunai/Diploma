(function () {
    'use strict';
    angular.module('app')
        .component('userList', {
            templateUrl: 'users/user-list.html',
            bindings: {
                users: '<'
            },
            controller: function($http) {
                let $ctrl = this;
                $ctrl.addAuthorPermissions = function(user) {
                    $http.post('/addAuthorPermissions', user.username)
                        .success(function() {
                            user.role = 'AUTHOR';
                        });
                };
                $ctrl.removeAuthorPermissions = function(user) {
                    $http.post('/removeAuthorPermissions', user.username)
                        .success(function() {
                            user.role = 'VISITOR';
                        });
                };
                $ctrl.removeUser = function(user) {
                    $http.post('/removeUser', user.username)
                        .success(function() {
                            $ctrl.users.pop(user);
                        });
                }
            }
        });
})();