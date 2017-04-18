(function () {
    'use strict';
    angular.module('app')
        .component('menuComponent', {
            templateUrl: 'menu/menu.html',
            controller : function($http) {
                let $ctrl = this;
                $ctrl.currentUser = {};
                $ctrl.getCurrentUser = function () {
                    $http.get('/currentUser').then(function(response){
                        $ctrl.currentUser = response.data;
                        console.log($ctrl.currentUser);
                    });
                };
                $ctrl.getCurrentUser();
            }
        });
})();