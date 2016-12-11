(function () {
    'use strict';
    angular.module('app')
        .component('articleList', {
            templateUrl: 'article/article-list/article-list.html',
            bindings: {
                articles: '<'
            },
            controller: function($state, userSrv) {
                let $ctrl = this;
                $ctrl.currentUser = userSrv.getCurrentUser();
                $ctrl.openArticlePage = function(article) {
                    $state.go('articlePage', { 'article' : article });
                };
                $ctrl.editArticle = function(article) {
                    $state.go('addArticle', { 'article' : article });
                };
            }
        });
})();