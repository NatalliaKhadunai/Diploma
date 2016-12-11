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
            .state('articlePage', {
                url: '/articlePage',
                template: '<article-page></article-page>',
                params: {
                    'article': {}
                }
            })
            .state('addArticle', {
                url: '/addArticle',
                template: '<add-article></add-article>',
                params: {
                    'article': {}
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
            .state('advertisementPage', {
                url: '/advertisementPage',
                template: '<advertisement-page></advertisement-page>',
                params: {
                    'advertisement': {}
                }
            })
            .state('addAdvertisement', {
                url: '/addAdvertisement',
                template: '<add-advertisement></add-advertisement>',
                params: {
                    'advertisement': {}
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
            .state('eventPage', {
                url: '/eventPage',
                template: '<event-page></event-page>',
                params: {
                    'event': {}
                }
            })
            .state('addEvent', {
                url: '/addEvent',
                template: '<add-event></add-event>',
                params: {
                    'event': {}
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