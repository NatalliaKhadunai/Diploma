(function () {
    'use strict';
    angular.module('app')
        .component('home', {
            templateUrl: 'res/home/home.html',
            controller: function ($state, $stateParams, $http) {
                let $ctrl = this;
                $ctrl.getArticles = function () {
                    $http.get('/articles',
                        {params: {page: 1, interesting: true}}).then(function (response) {
                        $ctrl.articles = response.data;
                    });
                };
                $ctrl.openArticlePage = function (article) {
                    $state.go('articlePage', {'articleId': article.id});
                };
                $ctrl.getEvents = function () {
                    $http.get('/events', {params: {page: 1, sort: 'rating'}})
                        .then(function (response) {
                            $ctrl.events = response.data;
                        });
                };
                $ctrl.getAdvertisements = function () {
                    $http.get('/advertisements', {params: {page: 1}}).then(function (response) {
                        $ctrl.newAdvertisements = response.data;
                    });
                    $http.get('/advertisements', {params: {page: 1, sort: 'expirationDate'}}).then(function (response) {
                        $ctrl.expiringAdvertisements = response.data;
                    });
                };
                $ctrl.openEventPage = function (event) {
                    $state.go('eventPage', {'eventId': event.id});
                };
                $ctrl.getArticles();
                $ctrl.getEvents();
                $ctrl.getAdvertisements();
            }
        });
})();