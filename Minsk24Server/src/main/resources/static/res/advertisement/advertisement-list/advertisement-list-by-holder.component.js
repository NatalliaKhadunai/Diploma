(function () {
    'use strict';
    angular.module('app')
        .component('advertisementListByHolder', {
            templateUrl: 'res/advertisement/advertisement-list/advertisement-list.html',
            controller: function ($state, $stateParams, $http) {
                let $ctrl = this;
                $ctrl.openAdvertisementPage = function (advertisement) {
                    $state.go('advertisementPage', {'advertisementId': advertisement.id});
                };
                $ctrl.initializeAdvertisements = function () {
                    $http.get('/advertisements/holder/' + $stateParams['holderLogin'],
                        {params: {pageNum: 1}})
                        .then(function (response) {
                            $ctrl.advertisements = response.data;
                        });
                };
                $ctrl.getAdvertisements = function (pageNum) {
                    $http.get('/advertisements/holder/' + $stateParams['holderLogin'],
                        {params: {pageNum: pageNum}})
                        .then(function (response) {
                            $ctrl.advertisements = response.data;
                        });
                };
                $ctrl.getPageCount = function () {
                    $http.get('/advertisements/holder/' + $stateParams['holderLogin'] + '/count')
                        .then(function (response) {
                            $ctrl.pageCount = response.data;
                        });
                };
                $ctrl.getPageNumber = function () {
                    return new Array($ctrl.pageCount);
                };
                $ctrl.initializeAdvertisements();
                $ctrl.getPageCount();
            }
        });
})();