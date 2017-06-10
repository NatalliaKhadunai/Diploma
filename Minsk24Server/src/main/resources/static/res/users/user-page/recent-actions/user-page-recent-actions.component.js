(function () {
    'use strict';
    angular.module('app')
        .component('userPageRecentActions', {
            templateUrl: 'res/users/user-page/recent-actions/user-page-recent-actions.html',
            controller: function ($state, $stateParams, $http) {
                let $ctrl = this;
                $ctrl.user = {};
                $ctrl.getUser = function () {
                    if (typeof $stateParams['user'].login != 'undefined') {
                        $ctrl.user = $stateParams['user'];
                        $ctrl.getRecentActions();
                    }
                    else {
                        $http.get('/currentUser').then(function (response) {
                            $ctrl.user = response.data;
                            $ctrl.getRecentActions();
                        }, function (response) {
                            alert('Status code : ' + response.data.httpStatusCode + '\n'
                                + 'Message : ' + response.data.developerMessage);
                        });
                    }
                };
                $ctrl.getRecentActions = function () {
                    $http.get('/users/' + $ctrl.user.login + '/comments')
                        .then(function (response) {
                            $ctrl.recentComments = response.data;
                        }, function (response) {
                            alert('Status code : ' + response.data.httpStatusCode + '\n'
                                + 'Message : ' + response.data.developerMessage);
                        });
                    $http.get('/users/' + $ctrl.user.login + '/beforeEventRates')
                        .then(function (response) {
                            $ctrl.recentBeforeEventRates = response.data;
                        }, function (response) {
                            alert('Status code : ' + response.data.httpStatusCode + '\n'
                                + 'Message : ' + response.data.developerMessage);
                        });
                    $http.get('/users/' + $ctrl.user.login + '/afterEventRates')
                        .then(function (response) {
                            $ctrl.recentAfterEventRates = response.data;
                        }, function (response) {
                            alert('Status code : ' + response.data.httpStatusCode + '\n'
                                + 'Message : ' + response.data.developerMessage);
                        });
                };
                $ctrl.openEventPage = function (event) {
                    $state.go('eventPage', {'eventId': event.id});
                };
                $ctrl.getUser();
            }
        });
})();