(function () {
    'use strict';
    angular.module('app')
        .component('advertisementPage', {
            templateUrl: 'advertisement/advertisement-page/advertisement-page.html',
            controller: function($state, $stateParams) {
                let $ctrl = this;
                $ctrl.advertisement = $stateParams['advertisement'];
            }
        });
})();