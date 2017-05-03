(function () {
    'use strict';
    angular.module('app')
        .component('advertisementPage', {
            templateUrl: 'res/advertisement/advertisement-page/advertisement-page.html',
            controller: function($state, $stateParams, $http) {
                let $ctrl = this;
                $ctrl.advertisement = {};
                $ctrl.getAdvertisement = function() {
                    var advertisement = $stateParams['advertisement'];
                    $http.get('/advertisements/' + advertisement.id)
                        .then(function (response) {
                            $ctrl.advertisement = response.data;
                        });
                };
                $ctrl.editAdvertisement = function() {
                    $state.go('addAdvertisement', { 'advertisement' : $ctrl.advertisement });
                };
                $ctrl.getAdvertisement();
            }
        });
})();