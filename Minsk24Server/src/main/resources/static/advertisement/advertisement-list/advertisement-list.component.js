(function () {
    'use strict';
    angular.module('app')
        .component('advertisementList', {
            templateUrl: 'advertisement/advertisement-list/advertisement-list.html',
            bindings: {
                advertisements: '<'
            },
            controller: function ($state, userSrv) {
                let $ctrl = this;
                $ctrl.currentUser = userSrv.getCurrentUser();
                $ctrl.openAdvertisementPage = function(advertisement) {
                    $state.go('advertisementPage', { 'advertisement' : advertisement });
                };
                $ctrl.editAdvertisement = function(advertisement) {
                    $state.go('addAdvertisement', { 'advertisement' : advertisement });
                };
            }
        });
})();