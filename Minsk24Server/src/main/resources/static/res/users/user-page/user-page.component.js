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
                $ctrl.initializeTagSelect = function () {
                    $http.get('/tags')
                        .then(function (response) {
                            $ctrl.tags = response.data;
                        });
                };
                $ctrl.initializeInterestingTags = function () {
                    $http.get('/account/tags/interesting')
                        .then(function (response) {
                            $ctrl.interestingTags = response.data;
                        });
                };
                $ctrl.isExcluded = function (tag) {
                      for (var i=0;i<$ctrl.interestingTags.length;i++) {
                          if ($ctrl.interestingTags[i].id == tag.id) return false;
                      }
                      return true;
                };
                $ctrl.addOrExcludeTag = function (tag) {
                     if ($ctrl.isExcluded(tag)) {
                         $http.post('/account/tags/' + tag.id + '/add')
                             .then(function (response) {
                                 $ctrl.interestingTags.push(response.data);
                             })
                     }
                     else {
                         $http.post('/account/tags/' + tag.id + '/exclude')
                             .then(function () {
                                 var index = -1;
                                 for (var i=0;i<$ctrl.interestingTags.length;i++) {
                                     if ($ctrl.interestingTags[i].id == tag.id) index = i;
                                 }
                                 $ctrl.interestingTags.splice(index, 1);
                             })
                     }
                };
                $ctrl.getUser();
                $ctrl.initializeTagSelect();
                $ctrl.initializeInterestingTags();
            }
        });
})();