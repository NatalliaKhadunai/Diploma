(function () {
    'use strict';
    angular.module('app')
        .component('articleListByAuthor', {
            templateUrl: 'res/article/article-list/article-list.html',
            controller: function($state, $stateParams, $http, userSrv) {
                let $ctrl = this;
                $ctrl.initializeArticles = function () {
                    let authorLogin = $stateParams['authorlogin'];
                        $http.get('/articles/authors/' + $stateParams['authorLogin'],
                            {params: {pageNum: 1}})
                            .then(function (response) {
                                $ctrl.articles = response.data;
                            });
                };
                $ctrl.getArticles = function (pageNum) {
                    let authorLogin = $stateParams['authorLogin'];
                        $http.get('/articles/authors/' + $stateParams['authorLogin'],
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
                    $http.get('/articles/tags/' + tag.id, {params: {pageNum: 1}})
                        .then(function(response){
                            $ctrl.articles = response.data;
                            $ctrl.activeTag = tag;
                            $ctrl.getPageCount();
                    });
                };
                $ctrl.getPageCount = function () {
                    let authorLogin = $stateParams['authorLogin'];
                    $http.get('/articles/author/' + $stateParams['authorLogin'] + '/count')
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