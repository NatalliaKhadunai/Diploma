(function () {
    'use strict';
    angular.module('app')
        .component('userPageAdvertisements', {
            templateUrl: 'res/advertisement/advertisement-list/advertisement-list.html',
            controller: function ($state, $stateParams, $http) {
                let $ctrl = this;
                $ctrl.user = {};
                $ctrl.getUser = function () {
                    if (typeof $stateParams['user'].login != 'undefined') {
                        $ctrl.user = $stateParams['user'];
                        $ctrl.getAdvertisements();
                        $ctrl.getPageCount();
                    }
                    else {
                        $http.get('/currentUser').then(function (response) {
                            $ctrl.user = response.data;
                            $ctrl.getAdvertisements();
                            $ctrl.getPageCount();
                        });
                    }
                };
                $ctrl.openAdvertisementPage = function (advertisement) {
                    $state.go('advertisementPage', {'advertisementId': advertisement.id});
                };
                $ctrl.getAdvertisements = function () {
                    let params = $ctrl.defineParams();
                    $http.get('/advertisements', {params: params}).then(function (response) {
                        $ctrl.advertisements = response.data;
                    });
                };
                $ctrl.loadAdvertisementsByPage = function (page) {
                    $state.go('userPage.advertisements', {page : page});
                };
                $ctrl.getPageCount = function () {
                    let params = $ctrl.defineParams();
                    $http.get('/advertisements/count/', {params: params})
                        .then(function (response) {
                            $ctrl.pageCount = response.data;
                        });
                };
                $ctrl.getPageNumber = function () {
                    return new Array($ctrl.pageCount);
                };
                $ctrl.defineParams = function () {
                    let params = {};
                    if (typeof $stateParams['page'] != 'undefined')
                        params.page = $stateParams['page'];
                    else params.page = 1;
                    params.holder = $ctrl.user.login;
                    return params;
                };
                $ctrl.getUser();
            }
        });
})();