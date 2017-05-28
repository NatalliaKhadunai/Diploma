(function () {
    'use strict';
    angular.module('app')
        .component('articleList', {
            templateUrl: 'res/article/article-list/article-list.html',
            controller: function ($state, $stateParams, $http) {
                let $ctrl = this;
                $ctrl.showOnlyInteresting =
                    typeof $stateParams['interesting'] != 'undefined' ?
                        $stateParams['interesting'] : false;
                $ctrl.getArticles = function () {
                    let params = $ctrl.defineParams();
                    $http.get('/articles', {params: params}).then(function (response) {
                        $ctrl.articles = response.data;
                    });
                };
                $ctrl.openArticlePage = function (article) {
                    $state.go('articlePage', {'articleId': article.id});
                };
                $ctrl.initializePopularTags = function () {
                    $http.get('/tags/popular')
                        .then(function (response) {
                            $ctrl.popularTags = response.data;
                        });
                };
                $ctrl.loadArticlesByTag = function (tag) {
                    $state.go('articles', {'tag': tag.name});
                };
                $ctrl.loadArticlesByPage = function (page) {
                    $state.go('articles', {page : page});
                };
                $ctrl.loadInterestingArticles = function () {
                    $stateParams['author'] = null;
                    $stateParams['tag'] = null;
                    $stateParams['page'] = null;
                    $state.go('articles', {'interesting': $ctrl.showOnlyInteresting});
                };
                $ctrl.getPageCount = function () {
                    let params = $ctrl.defineParams();
                    $http.get('/articles/count/', {params: params})
                        .then(function (response) {
                            $ctrl.pageCount = response.data;
                        });
                };
                $ctrl.getPageNumber = function () {
                    return new Array($ctrl.pageCount);
                };
                $ctrl.defineParams = function () {
                    let params = {};
                    if (typeof $stateParams['author'] != 'undefined')
                        params.author = $stateParams['author'];
                    if (typeof $stateParams['tag'] != 'undefined')
                        params.tag = $stateParams['tag'];
                    if (typeof $stateParams['page'] != 'undefined')
                        params.page = $stateParams['page'];
                    else params.page = 1;
                    params.interesting = $ctrl.showOnlyInteresting;
                    return params;
                };
                $ctrl.getArticles(1);
                $ctrl.initializePopularTags();
                $ctrl.getPageCount();
            }
        });
})();