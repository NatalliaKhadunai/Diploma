(function () {
    'use strict';
    angular.module('app')
        .component('articleList', {
            templateUrl: 'res/article/article-list/article-list.html',
            controller: function($state, $stateParams, $http, userSrv) {
                let $ctrl = this;
                $ctrl.currentUser = userSrv.getCurrentUser();
                $ctrl.initializeArticles = function () {
                    if (typeof $stateParams['author'].login != 'undefined') {
                        $http.get('/articles/authors/' + $stateParams['author'].login,
                            {params: {pageNum: 1}})
                            .then(function (response) {
                                $ctrl.articles = response.data;
                            });
                    }
                    else {
                        $http.get('/articles', {params: {pageNum: 1}}).then(function (response) {
                            $ctrl.articles = response.data;
                        });
                    }
                };
                $ctrl.getArticles = function (pageNum) {
                    if (typeof $stateParams['author'].login != 'undefined') {
                        $http.get('/articles/authors/' + $stateParams['author'].login,
                            {params: {pageNum: pageNum}})
                            .then(function (response) {
                                $ctrl.articles = response.data;
                            });
                    }
                    else {
                        $http.get('/articles', {params: {pageNum: pageNum}}).then(function (response) {
                            $ctrl.articles = response.data;
                        });
                    }
                };
                $ctrl.openArticlePage = function(article) {
                    $state.go('articlePage', { 'article' : article });
                };
                $ctrl.initializePopularTags = function () {
                    $http.get('/tags/popular')
                        .then(function (response) {
                            $ctrl.popularTags = response.data;
                        });
                };
                $ctrl.loadArticlesByTag = function (tagName) {
                    $http.get('/articles/tags/' + tagName)
                        .then(function(response){
                            $ctrl.articles = response.data;
                    });
                };
                $ctrl.getPageCount = function () {
                    if (typeof $stateParams['author'].login != 'undefined') {
                        $http.get('/articles/author/' + $stateParams['author'].login + '/count')
                            .then(function (response) {
                                $ctrl.pageCount = response.data;
                            });
                    }
                    else {
                        $http.get('/articles/count/')
                            .then(function (response) {
                                $ctrl.pageCount = response.data;
                            });
                    }
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