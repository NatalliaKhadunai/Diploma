(function () {
    'use strict';
    angular.module('app')
        .component('userPageArticles', {
            templateUrl: 'res/article/article-list/article-list.html',
            controller: function ($state, $stateParams, $http) {
                let $ctrl = this;
                $ctrl.user = {};
                $ctrl.getUser = function () {
                    if (typeof $stateParams['user'].login != 'undefined') {
                        $ctrl.user = $stateParams['user'];
                        $ctrl.getArticles();
                        $ctrl.getPageCount();
                    }
                    else {
                        $http.get('/currentUser')
                            .then(function (response) {
                                $ctrl.user = response.data;
                                $ctrl.getArticles();
                                $ctrl.getPageCount();
                            }, function (response) {
                                alert('Status code : ' + response.data.httpStatusCode + '\n'
                                    + 'Message : ' + response.data.developerMessage);
                            });
                    }
                };
                $ctrl.openArticlePage = function (article) {
                    $state.go('articlePage', {'articleId': article.id});
                };
                $ctrl.getArticles = function () {
                    let params = $ctrl.defineParams();
                    $http.get('/articles', {params: params})
                        .then(function (response) {
                            $ctrl.articles = response.data;
                        }, function (response) {
                            alert('Status code : ' + response.data.httpStatusCode + '\n'
                                + 'Message : ' + response.data.developerMessage);
                        });
                };
                $ctrl.loadArticlesByPage = function (page) {
                    $state.go('userPage.articles', {page : page});
                };
                $ctrl.getPageCount = function () {
                    let params = $ctrl.defineParams();
                    $http.get('/articles/count/', {params: params})
                        .then(function (response) {
                            $ctrl.pageCount = response.data;
                        }, function (response) {
                            alert('Status code : ' + response.data.httpStatusCode + '\n'
                                + 'Message : ' + response.data.developerMessage);
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
                    params.author = $ctrl.user.login;
                    return params;
                };
                $ctrl.getUser();
            }
        });
})();