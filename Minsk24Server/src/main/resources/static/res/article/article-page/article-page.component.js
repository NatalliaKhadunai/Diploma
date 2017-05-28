(function () {
    'use strict';
    angular.module('app')
        .component('articlePage', {
            templateUrl: 'res/article/article-page/article-page.html',
            controller: function($state, $stateParams, $http) {
                let $ctrl = this;
                $ctrl.article = {};
                $ctrl.comment = {};
                $ctrl.getArticle = function() {
                    var articleId = $stateParams['articleId'];
                    $http.get('/articles/' + articleId)
                        .then(function (response) {
                            $ctrl.article = response.data;
                        });
                };
                $ctrl.editArticle = function() {
                    $state.go('addArticle', { 'article' : $ctrl.article });
                };
                $ctrl.getArticlesOfAuthor = function (author) {
                    $state.go('articles', {'author': author.login});
                };
                $ctrl.addComment = function () {
                    $http({
                        url: '/articles/' + $ctrl.article.id + '/comments',
                        method: 'POST',
                        data: $ctrl.comment
                    }).then(function (response) {
                        $ctrl.article = response.data;
                        $ctrl.comment.content = "";
                    }, function (response) {
                        alert('Status code : ' + response.data.httpStatusCode + '\n' + 'Message : ' + response.data.developerMessage);
                    });
                };
                $ctrl.getArticle();
            }
        });
})();