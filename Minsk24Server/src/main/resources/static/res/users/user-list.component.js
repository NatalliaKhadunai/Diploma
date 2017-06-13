(function () {
    'use strict';
    angular.module('app')
        .component('userList', {
            templateUrl: 'res/users/user-list.html',
            bindings: {
                users: '<'
            },
            controller: function ($http) {
                let $ctrl = this;
                $ctrl.addAuthorPermissions = function (user) {
                    $http.post('/v3/users/ ' + user.id + '/addAuthorPermissions')
                        .then(function () {
                            user.role = 'AUTHOR';
                        }, function (response) {
                            alert('Status code : ' + response.data.httpStatusCode + '\n'
                                + 'Message : ' + response.data.developerMessage);
                        });
                };
                $ctrl.removeAuthorPermissions = function (user) {
                    $http.post('/v3/users/' + user.id + '/removeAuthorPermissions')
                        .then(function () {
                            user.role = 'GUEST';
                        }, function (response) {
                            alert('Status code : ' + response.data.httpStatusCode + '\n'
                                + 'Message : ' + response.data.developerMessage);
                        });
                };
                $ctrl.addAdminPermissions = function (user) {
                    $http.post('/v3/users/ ' + user.id + '/addAdminPermissions')
                        .then(function () {
                            user.role = 'ADMIN';
                        }, function (response) {
                            alert('Status code : ' + response.data.httpStatusCode + '\n'
                                + 'Message : ' + response.data.developerMessage);
                        });
                };
                $ctrl.removeAdminPermissions = function (user) {
                    $http.post('/v3/users/' + user.id + '/removeAdminPermissions')
                        .then(function () {
                            user.role = 'GUEST';
                        }, function (response) {
                            alert('Status code : ' + response.data.httpStatusCode + '\n'
                                + 'Message : ' + response.data.developerMessage);
                        });
                };
                $ctrl.removeUser = function (user) {
                    $http({
                        url: '/v3/users/' + user.id,
                        method: 'DELETE'
                    }).then(function () {
                        user = null;
                    }, function (response) {
                        alert('Status code : ' + response.data.httpStatusCode + '\n'
                            + 'Message : ' + response.data.developerMessage);
                    });
                }
            }
        });
})();