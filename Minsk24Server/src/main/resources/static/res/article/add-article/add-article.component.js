(function () {
    'use strict';
    angular.module('app')
        .component('addArticle', {
            templateUrl: 'res/article/add-article/add-article.html',
            controller: function ($state, $stateParams, $http) {
                let $ctrl = this;
                $ctrl.article = {};
                $ctrl.required = typeof $stateParams['id'] != 'undefined' &&
                $stateParams['id'].toString().length != 0 ?
                    false : true;
                $ctrl.tags = [];
                $ctrl.initializeArticle = function () {
                    $http.get('/articles/' + $stateParams['id'])
                        .then(function (response) {
                            $ctrl.article = response.data;
                        }, function (response) {
                            alert('Status code : ' + response.data.httpStatusCode + '\n'
                                + 'Message : ' + response.data.developerMessage);
                        });
                };
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
                if ($stateParams['id']) $ctrl.initializeArticle();
            }
        });
})();