(function () {
    'use strict';
    angular.module('app')
        .component('articlePage', {
            templateUrl: 'res/article/article-page/article-page.html',
            controller: function($state, $stateParams, $http) {
                let $ctrl = this;
                $ctrl.article = {};
                $ctrl.getArticle = function() {
                    var article = $stateParams['article'];
                    $http.get('/articles/' + article.id)
                        .then(function (response) {
                            $ctrl.article = response.data;
                        });
                };
                $ctrl.editArticle = function() {
                    $state.go('addArticle', { 'article' : $ctrl.article });
                };
                $ctrl.getArticle();
            }
        });
})();