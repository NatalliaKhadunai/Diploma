(function () {
    'use strict';
    angular.module('app')
        .component('advertisementList', {
            templateUrl: 'advertisements/advertisement-list.html',
            controller: 'advertisementListCtrl',
            bindings: {
                advertisements: '<'
            }
        });
})();