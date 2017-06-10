(function () {
    'use strict';
    angular.module('app')
        .component('search', {
            templateUrl: 'res/search/search.html',
            controller: function ($http, $state, $stateParams) {
                let $ctrl = this;
                $ctrl.getData = function () {
                    let params = $ctrl.defineParams();
                    $ctrl.searchKeyword = $stateParams['keyword'];
                    $ctrl.scope = $stateParams['scope'];
                    $http.get('/search', {params : params})
                        .then(function (response) {
                           $ctrl.searchData =  response.data;
                        }, function () {
                            alert('Status code : ' + response.data.httpStatusCode + '\n'
                                + 'Message : ' + response.data.developerMessage);
                        });
                };
                $ctrl.openResultPage = function (resultObj) {
                    if ($ctrl.scope == 'article')
                        $state.go('articlePage', {articleId: resultObj.id});
                    if ($ctrl.scope == 'advertisement')
                        $state.go('advertisementPage', {advertisementId: resultObj.id});
                    if ($ctrl.scope == 'event')
                        $state.go('eventPage', {eventId: resultObj.id});
                };
                $ctrl.getPageCount = function () {
                    let params = $ctrl.defineParams();
                    $http.get('/search/count/', {params: params})
                        .then(function (response) {
                            $ctrl.pageCount = response.data;
                        }, function (response) {
                            alert('Status code : ' + response.data.httpStatusCode + '\n'
                                + 'Message : ' + response.data.developerMessage);
                        });
                };
                $ctrl.loadByPage = function (page) {
                    $state.go('search', {page: page});
                };
                $ctrl.getPageNumber = function () {
                    return new Array($ctrl.pageCount);
                };
                $ctrl.defineParams = function () {
                    let params = {};
                    params.scope = $stateParams['scope'];
                    params.keyword = $stateParams['keyword'];
                    if (typeof $stateParams['page'] != 'undefined')
                        params.page = $stateParams['page'];
                    else params.page = 1;
                    params.interesting = $ctrl.showOnlyInteresting;
                    return params;
                };
                $ctrl.getData();
                $ctrl.getPageCount();
            }
        });
})();