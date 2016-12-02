(function () {
    'use strict';

    angular
        .module('app')
        .controller('articleListCtrl', function (articleSrv) {
            let $ctrl = this;
            $ctrl.articleToAdd = {
                tags: []
            };
            $ctrl.tags = [];
            $ctrl.addArticle= function () {
                for (var i=0; i< $ctrl.tags.length;i++) {
                    $ctrl.articleToAdd.tags[i] = {name: $ctrl.tags[i]};
                }
                articleSrv.addArticle($ctrl.articleToAdd);
            }
        });
})();