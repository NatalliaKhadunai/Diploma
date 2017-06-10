(function () {
    'use strict';
    angular.module('app')
        .component('home', {
            templateUrl: 'res/home/home.html',
            controller: function ($state, $stateParams, $http) {
                let $ctrl = this;
                $ctrl.getArticles = function () {
                    $http.get('/articles',
                        {params: {page: 1, interesting: true}})
                        .then(function (response) {
                            $ctrl.articles = response.data;
                        }, function (response) {
                            alert('Status code : ' + response.data.httpStatusCode + '\n'
                                + 'Message : ' + response.data.developerMessage);
                        });
                };
                $ctrl.openArticlePage = function (article) {
                    $state.go('articlePage', {'articleId': article.id});
                };
                $ctrl.getEvents = function () {
                    $http.get('/events', {params: {page: 1, sort: 'upcoming-rating'}})
                        .then(function (response) {
                            $ctrl.upcomingEvents = response.data;
                        }, function (response) {
                            alert('Status code : ' + response.data.httpStatusCode + '\n'
                                + 'Message : ' + response.data.developerMessage);
                        });
                    $http.get('/events', {params: {page: 1, sort: 'past-rating'}})
                        .then(function (response) {
                            $ctrl.pastEvents = response.data;
                        }, function (response) {
                            alert('Status code : ' + response.data.httpStatusCode + '\n'
                                + 'Message : ' + response.data.developerMessage);
                        });
                };
                $ctrl.getAdvertisements = function () {
                    $http.get('/advertisements',
                        {params: {page: 1, sort: 'expirationDate'}})
                        .then(function (response) {
                            $ctrl.expiringAdvertisements = response.data;
                        }, function (response) {
                            alert('Status code : ' + response.data.httpStatusCode + '\n'
                                + 'Message : ' + response.data.developerMessage);
                        });
                    $http.get('/advertisements',
                        {params: {page: 1, sort: 'placementDate'}})
                        .then(function (response) {
                            $ctrl.newAdvertisements = response.data;
                        }, function (response) {
                            alert('Status code : ' + response.data.httpStatusCode + '\n'
                                + 'Message : ' + response.data.developerMessage);
                        });
                };
                $ctrl.openEventPage = function (event) {
                    $state.go('eventPage', {'eventId': event.id});
                };
                $ctrl.loadArticlesByTag = function (tag) {
                    $state.go('articles', {'tag': tag.name});
                };
                $ctrl.getArticles();
                $ctrl.getEvents();
                $ctrl.getAdvertisements();
            }
        });
})();