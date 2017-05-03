(function () {
    'use strict';
    angular.module('app')
        .component('addAdvertisement', {
            templateUrl: 'res/advertisement/add-advertisement/add-advertisement.html',
            controller: function($state, $stateParams) {
                let $ctrl = this;
                $ctrl.advertisement = $stateParams['advertisement'];
            }
        });
})();