(function () {
    'use strict';
    angular.module('app')
        .component('menuComponent', {
            templateUrl: 'res/menu/menu.html',
            controller : function($http) {
                let $ctrl = this;
                $ctrl.currentUser = {};
                $ctrl.getCurrentUser = function () {
                    $http.get('/currentUser').then(function(response){
                        $ctrl.currentUser = response.data;
                    });
                };
                $ctrl.getCurrentUser();
            }
        });
})();