(function () {
    'use strict';
    angular.module('app')
        .component('addAdvertisement', {
            templateUrl: 'res/advertisement/add-advertisement/add-advertisement.html',
            controller: function($state, $stateParams) {
                let $ctrl = this;
                $ctrl.advertisement = $stateParams['advertisement'];
                $ctrl.dateNow = new Date();
                $ctrl.dateStr = new Date($ctrl.advertisement.expirationDate);
                $ctrl.required = typeof $stateParams['advertisement'].id != 'undefined'?
                    false : true;
            }
        });
})();