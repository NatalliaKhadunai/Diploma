(function () {
    'use strict';
    angular.module('app')
        .component('addArticle', {
            templateUrl: 'article/add-article/add-article.html',
            controller: function($state, $stateParams, $http) {
                let $ctrl = this;
                $ctrl.article = {};
                /*$ctrl.getArticle = function() {
                    var articleTmp = $stateParams['article'];
                    return $http.get('/article/' + articleTmp.id).then(function(response){
                        $ctrl.article = response.data;
                        console.log($ctrl.article);
                    });
                };*/
            }
        });
})();