(function () {
    'use strict';
    angular.module('app')
        .component('advertisementList', {
            templateUrl: 'res/advertisement/advertisement-list/advertisement-list.html',
            controller: function ($state, $stateParams, $http) {
                let $ctrl = this;
                $ctrl.showToolbar = true;
                $ctrl.openAdvertisementPage = function (advertisement) {
                    $state.go('advertisementPage', {'advertisementId': advertisement.id});
                };
                $ctrl.getAdvertisements = function () {
                    let params = $ctrl.defineParams();
                    $http.get('/advertisements', {params: params})
                        .then(function (response) {
                            $ctrl.advertisements = response.data;
                            $ctrl.getPageCount();
                        }, function (response) {
                            alert('Status code : ' + response.data.httpStatusCode + '\n'
                                + 'Message : ' + response.data.developerMessage);
                        });
                };
                $ctrl.getPageCount = function () {
                    let params = $ctrl.defineParams();
                    $http.get('/advertisements/count/', {params: params})
                        .then(function (response) {
                            $ctrl.pageCount = response.data;
                        }, function (response) {
                            alert('Status code : ' + response.data.httpStatusCode + '\n'
                                + 'Message : ' + response.data.developerMessage);
                        });
                };
                $ctrl.changeSort = function (sort) {
                    $state.go('advertisements', {page: 1, sort: sort});
                };
                $ctrl.getPageNumber = function () {
                    return new Array($ctrl.pageCount);
                };
                $ctrl.loadAdvertisementsByPage = function (page) {
                    $state.go('advertisements', {page: page});
                };
                $ctrl.search = function () {
                    $state.go('search', {scope : 'advertisement',
                        keyword : $ctrl.searchKeyword});
                };
                $ctrl.isExpired = function (advertisement) {
                    let now = new Date();
                    let expirationDate = new Date(advertisement.expirationDate);
                    if (expirationDate < now) return 'expired';
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
            }
        });
})();