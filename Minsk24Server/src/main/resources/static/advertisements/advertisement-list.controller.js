(function () {
    'use strict';

    angular
        .module('app')
        .controller('advertisementListCtrl', function (advertisementSrv) {
            let $ctrl = this;
            $ctrl.advertisementToAdd = {};
            $ctrl.addAdvertisement = function () {
                advertisementSrv.addAdvertisement($ctrl.advertisementToAdd);
            }
        });
})();