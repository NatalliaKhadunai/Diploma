(function () {
    'use strict';
    angular.module('app')
        .component('home', {
            templateUrl: 'res/home/home.html',
            controller: function($state, $stateParams, $http) {
                let $ctrl = this;
                $ctrl.initializeArticles = function () {
                    $http.get('/articles/interesting', {params: {pageNum: 1}}).then(function (response) {
                        $ctrl.articles = response.data;
                    });
                };
                $ctrl.getArticles = function (pageNum) {
                    $http.get('/articles/interesting', {params: {pageNum: pageNum}}).then(function (response) {
                        $ctrl.articles = response.data;
                    });
                };
                $ctrl.openArticlePage = function(article) {
                    $state.go('articlePage', { 'articleId' : article.id });
                };
                /*$ctrl.getPageCount = function () {
                    $http.get('/articles/interesting/count/')
                        .then(function (response) {
                            $ctrl.pageCount = response.data;
                        });
                };
                $ctrl.getPageNumber = function() {
                    return new Array($ctrl.pageCount);
                };*/
                $ctrl.initializeArticles();
                //$ctrl.getPageCount();
            }
        });
})();