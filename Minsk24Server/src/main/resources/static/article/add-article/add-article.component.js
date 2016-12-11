(function () {
    'use strict';
    angular.module('app')
        .component('addArticle', {
            templateUrl: 'article/add-article/add-article.html',
            controller: function($state, $stateParams) {
                let $ctrl = this;
                $ctrl.article = $stateParams['article'];
            }
        });
})();