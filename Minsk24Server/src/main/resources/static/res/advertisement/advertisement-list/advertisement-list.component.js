(function () {
    'use strict';
    angular.module('app')
        .component('advertisementList', {
            templateUrl: 'res/advertisement/advertisement-list/advertisement-list.html',
            controller: function ($state, $stateParams, $http) {
                let $ctrl = this;
                console.log($state.current);
                $ctrl.openAdvertisementPage = function (advertisement) {
                    $state.go('advertisementPage', {'advertisementId': advertisement.id});
                };
                $ctrl.getAdvertisements = function () {
                    let params = $ctrl.defineParams();
                    $http.get('/advertisements', {params: params}).then(function (response) {
                        $ctrl.advertisements = response.data;
                    });
                };
                $ctrl.getPageCount = function () {
                    let params = $ctrl.defineParams();
                    $http.get('/advertisements/count/', {params: params})
                        .then(function (response) {
                            $ctrl.pageCount = response.data;
                        });
                };
                $ctrl.changeSort = function (sort) {
                    $state.go('advertisements', {page : 1, sort: sort});
                };
                $ctrl.getPageNumber = function () {
                    return new Array($ctrl.pageCount);
                };
                $ctrl.loadAdvertisementsByPage = function (page) {
                    $state.go('advertisements', {page : page});
                };
                $ctrl.defineParams = function () {
                    let params = {};
                    if (typeof $stateParams['sort'] != 'undefined')
                        params.sort = $stateParams['sort'];
                    if (typeof $stateParams['page'] != 'undefined')
                        params.page = $stateParams['page'];
                    else params.page = 1;
                    return params;
                };
                $ctrl.getAdvertisements();
                $ctrl.getPageCount();
            }
        });
})();