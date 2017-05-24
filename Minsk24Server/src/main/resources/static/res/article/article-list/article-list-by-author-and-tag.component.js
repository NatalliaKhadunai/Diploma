(function () {
    'use strict';
    angular.module('app')
        .component('articleListByAuthorAndTag', {
            templateUrl: 'res/article/article-list/article-list.html',
            controller: function($state, $stateParams, $http) {
                let $ctrl = this;
                $ctrl.initializeArticles = function () {
                    let authorLogin = $stateParams['authorLogin'];
                    let tagName = $stateParams['tagName'];
                    $http.get('/articles/authors/' + $stateParams['authorLogin']
                        + '/tags/' + tagName,
                        {params: {pageNum: 1}})
                        .then(function (response) {
                            $ctrl.articles = response.data;
                        });
                };
                $ctrl.getArticles = function (pageNum) {
                    let authorLogin = $stateParams['authorLogin'];
                    let tagName = $stateParams['tagName'];
                    $http.get('/articles/authors/' + $stateParams['authorLogin']
                        + '/tags/' + tagName,
                        {params: {pageNum: pageNum}})
                        .then(function (response) {
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
                    $state.go('articlesByAuthorAndTag',
                        { 'authorLogin' : $stateParams['authorLogin'],
                        'tagName' : tag.name});
                };
                $ctrl.getPageCount = function () {
                    let authorLogin = $stateParams['authorLogin'];
                    let tagName = $stateParams['tagName'];
                    $http.get('/articles/author/' + authorLogin + '/tags/' + tagName + '/count')
                        .then(function (response) {
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