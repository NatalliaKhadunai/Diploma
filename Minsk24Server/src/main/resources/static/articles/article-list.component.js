(function () {
    'use strict';
    angular.module('app')
        .component('articleList', {
            templateUrl: 'articles/article-list.html',
            controller: 'articleListCtrl',
            bindings: {
                articles: '<'
            }
        });
})();