(function () {
    'use strict';
    angular.module('app')
        .component('advertisementList', {
            templateUrl: 'res/advertisement/advertisement-list/advertisement-list.html',
            controller: function ($state, $stateParams, userSrv, $http) {
                let $ctrl = this;
                $ctrl.currentUser = userSrv.getCurrentUser();
                $ctrl.openAdvertisementPage = function(advertisement) {
                    $state.go('advertisementPage', { 'advertisementId' : advertisement.id });
                };
                $ctrl.initializeAdvertisements = function () {
                    if (typeof $stateParams['holder'].id != 'undefined') {
                        $http.get('/advertisements/holder/' + $stateParams['holder'].id,
                            {params: {pageNum: 1}})
                            .then(function (response) {
                                $ctrl.advertisements = response.data;
                        });
                    }
                    else {
                        $http.get('/advertisements', {params: {pageNum: 1}}).then(function (response) {
                            $ctrl.advertisements = response.data;
                        });
                    }
                };
                $ctrl.getAdvertisements = function (pageNum) {
                    if (typeof $stateParams['holder'].id != 'undefined') {
                        $http.get('/advertisements/holder/' + $stateParams['holder'].id,
                            {params: {pageNum: pageNum}})
                            .then(function (response) {
                                $ctrl.advertisements = response.data;
                            });
                    }
                    else {
                        $http.get('/advertisements', {params: {pageNum: pageNum}}).then(function (response) {
                            $ctrl.advertisements = response.data;
                        });
                    }
                };
                $ctrl.getPageCount = function () {
                    if (typeof $stateParams['holder'].id != 'undefined') {
                        $http.get('/advertisements/holder/' + $stateParams['holder'].id +'/count')
                            .then(function (response) {
                                $ctrl.pageCount = response.data;
                            });
                    }
                    else {
                        $http.get('/advertisements/count/')
                            .then(function (response) {
                                $ctrl.pageCount = response.data;
                            });
                    }
                };
                $ctrl.getPageNumber = function() {
                    return new Array($ctrl.pageCount);
                };
                $ctrl.initializeAdvertisements();
                $ctrl.getPageCount();
            }
        });
})();