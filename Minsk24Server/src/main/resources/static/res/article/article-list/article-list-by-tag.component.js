(function () {
    'use strict';
    angular.module('app')
        .component('articleListByTag', {
            templateUrl: 'res/article/article-list/article-list.html',
            controller: function($state, $stateParams, $http, userSrv) {
                let $ctrl = this;
                $ctrl.activeTag = '';
                $ctrl.initializeArticles = function () {
                	let tagName = $stateParams['tagName'];
                        $http.get('/articles/tags/' + tagName, {params: {pageNum: 1}})
                        .then(function(response){
                            $ctrl.articles = response.data;
                        });
                };
                $ctrl.getArticles = function (pageNum) {
                	let tagName = $stateParams['tagName'];
                        $http.get('/articles/tags/' + tagName, {params: {pageNum: pageNum}})
                            .then(function(response){
                                $ctrl.articles = response.data;
                            });
                };
                $ctrl.openArticlePage = function(article) {
                    $state.go('articlePage', { 'articleId' : article.id });
                };
                $ctrl.initializePopularTags = function () {
                    $http.get('/tags/popular')
                        .then(function (response) {
                            $ctrl.popularTags = response.data;
                        });
                };
                $ctrl.loadArticlesByTag = function (tag) {
                	$state.go('articlesByTag', { 'tagName' : tag.name });
                };
                $ctrl.getPageCount = function () {
                	let tagName = $stateParams['tagName'];
                        $http.get('/articles/tags/' + tagName + '/count')
                            .then(function(response){
                                $ctrl.pageCount = response.data;
                            });
                };
                $ctrl.getPageNumber = function() {
                    return new Array($ctrl.pageCount);
                };
                $ctrl.initializeArticles();
                $ctrl.initializePopularTags();
                $ctrl.getPageCount();
            }
        });
})();