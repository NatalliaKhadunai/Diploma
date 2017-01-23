(function () {
    'use strict';
    angular.module('app')
        .component('addAdvertisement', {
            templateUrl: 'advertisement/add-advertisement/add-advertisement.html',
            controller: function($state, $stateParams) {
                let $ctrl = this;
                $ctrl.advertisement = $stateParams['advertisement'];
                $ctrl.currentDate = new Date();
            }
        });
})();