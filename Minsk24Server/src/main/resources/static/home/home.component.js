(function () {
    'use strict';
    angular.module('app')
        .component('homeComponent', {
            templateUrl: 'home/home.html',
            controller: 'homeCtrl',
            bindings: {
                articles: '<'
            }
        });
})();