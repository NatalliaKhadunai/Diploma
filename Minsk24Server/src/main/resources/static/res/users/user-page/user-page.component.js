(function () {
    'use strict';
    angular.module('app')
        .component('userPage', {
            templateUrl: 'res/users/user-page/user-page.html',
            controller: function($state, $stateParams, $http) {
                let $ctrl = this;
                $ctrl.user = {};
                $ctrl.getUser = function() {
                    if (typeof $stateParams['user'].login != 'undefined') {
                        $ctrl.user = $stateParams['user'];
                        $ctrl.getRecentActions();
                    }
                    else {
                        $http.get('/currentUser').then(function(response){
                            $ctrl.user = response.data;
                            $ctrl.getRecentActions();
                        });
                    }

                };
                $ctrl.getRecentActions = function () {
                    $http.get('/users/' + $ctrl.user.login + '/comments')
                        .then(function(response){
                            $ctrl.recentComments = response.data;
                        });
                    $http.get('/users/' + $ctrl.user.login + '/beforeEventRates')
                        .then(function(response){
                            $ctrl.recentBeforeEventRates = response.data;
                        });
                    $http.get('/users/' + $ctrl.user.login + '/afterEventRates')
                        .then(function(response){
                            $ctrl.recentAfterEventRates = response.data;
                        });
                };
                $ctrl.openEventPage = function (event) {
                    $state.go('eventPage', { 'event' : event });
                };
                $ctrl.getUser();
            }
        });
})();