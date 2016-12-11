(function () {
    'use strict';
    angular.module('app')
        .component('articlePage', {
            templateUrl: 'article/article-page/article-page.html',
            controller: function($state, $stateParams) {
                let $ctrl = this;
                $ctrl.article = $stateParams['article'];
            }
        });
})();