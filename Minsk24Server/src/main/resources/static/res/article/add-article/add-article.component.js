(function () {
    'use strict';
    angular.module('app')
        .component('addArticle', {
            templateUrl: 'res/article/add-article/add-article.html',
            controller: function ($state, $stateParams, $http) {
                let $ctrl = this;
                $ctrl.article = $stateParams['article'];
                $ctrl.required = typeof $stateParams['article'].id != 'undefined' ?
                    false : true;
                $ctrl.tags = [];
                $ctrl.initializeTagSelect = function () {
                    $http.get('/tags')
                        .then(function (response) {
                            $ctrl.tags = response.data;
                        }, function (response) {
                            alert('Status code : ' + response.data.httpStatusCode + '\n'
                                + 'Message : ' + response.data.developerMessage);
                        });
                };
                $ctrl.initializeTagSelect();
            }
        });
})();