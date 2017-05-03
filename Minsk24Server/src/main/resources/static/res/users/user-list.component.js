(function () {
    'use strict';
    angular.module('app')
        .component('userList', {
            templateUrl: 'res/users/user-list.html',
            bindings: {
                users: '<'
            },
            controller: function($http) {
                let $ctrl = this;
                $ctrl.addAuthorPermissions = function(user) {
                    $http.post('admin/users/ ' + user.login + '/addAuthorPermissions')
                        .then(function() {
                            user.role = 'AUTHOR';
                        });
                };
                $ctrl.removeAuthorPermissions = function(user) {
                    $http.post('admin/users/' + user.login + '/removeAuthorPermissions')
                        .then(function() {
                            user.role = 'GUEST';
                        });
                };
                $ctrl.removeUser = function(user) {
                    $http({
                        url: '/admin/users/' + user.login,
                        method: 'DELETE'
                    }).then(function () {
                        user = null;
                    }, function (response) {
                        alert('Status code : ' + response.data.httpStatusCode + '\n' + 'Message : ' + response.data.developerMessage);
                    });
                }
            }
        });
})();