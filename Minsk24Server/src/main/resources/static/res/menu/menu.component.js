(function () {
    'use strict';
    angular.module('app')
        .component('menuComponent', {
            templateUrl: 'res/menu/menu.html',
            controller : function($http, $state, $stateParams) {
                let $ctrl = this;
                $ctrl.currentUser = {};
                $ctrl.getCurrentUser = function () {
                    $http.get('/currentUser').then(function(response){
                        $ctrl.currentUser = response.data;
                    });
                };
                $ctrl.getCurrentUser();
                $ctrl.goToArticles = function () {
                    $stateParams['author'] = null;
                    $stateParams['tag'] = null;
                    $stateParams['page'] = null;
                    $state.go('articles');
                };
                $ctrl.goToAdvertisements = function () {
                    $stateParams['page'] = null;
                    $stateParams['sort'] = null;
                    $state.go('advertisements');
                };
                $ctrl.goToEvents = function () {
                    $stateParams['sort'] = null;
                    $stateParams['time'] = null;
                    $stateParams['location'] = null;
                    $stateParams['page'] = null;
                    $state.go('events');
                };
            }
        });
})();