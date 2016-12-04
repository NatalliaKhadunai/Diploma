(function() {
    angular.module('app', ['ui.router']).config(function($urlRouterProvider, $stateProvider) {
        $urlRouterProvider.otherwise('/');
        $stateProvider
            .state('home', {
                url: '/',
                template: '<home-component articles="$resolve.articles"></home-component>',
                resolve : {
                    articles: function(articleSrv) {
                        return articleSrv.getArticles();
                    }
                }
            })
            .state('articles', {
                url: '/articles',
                template: '<article-list articles="$resolve.articles"></article-list>',
                resolve : {
                    articles: function(articleSrv) {
                        return articleSrv.getArticles();
                    }
                }
            })
            .state('advertisements', {
                url: '/advertisements',
                template: '<advertisement-list advertisements="$resolve.advertisements"></advertisement-list>',
                resolve : {
                    advertisements: function(advertisementSrv) {
                        return advertisementSrv.getAdvertisements();
                    }
                }
            })
            .state('events', {
                url: '/events',
                template: '<event-list events="$resolve.events"></event-list>',
                resolve : {
                    events: function(eventSrv) {
                        return eventSrv.getEvents();
                    }
                }
            })
            .state('users', {
                url: '/users',
                template: '<user-list users="$resolve.users"></user-list>',
                resolve : {
                    users: function(userSrv) {
                        return userSrv.getUsers();
                    }
                }
            });
    });
})();