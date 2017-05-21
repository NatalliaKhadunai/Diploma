(function() {
    angular.module('app', ['ui.router']).config(function($urlRouterProvider, $stateProvider) {
        $urlRouterProvider.otherwise('/');
        $stateProvider
            .state('home', {
                url: '/',
                template: '<home-component></home-component>'
            })
            .state('articles', {
                url: '/articles',
                template: '<article-list></article-list>',
                params: {
                    'author': {}
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
                template: '<advertisement-list></advertisement-list>',
                params: {
                    'holder': {}
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
                template: '<event-list></event-list>',
                params: {
                    'time': {},
                    'location': {}
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
            .state('addRemoveTag', {
                url: '/addRemoveTag',
                template: '<add-remove-tag></add-remove-tag>'
            })
            .state('users', {
                url: '/users',
                template: '<user-list users="$resolve.users"></user-list>',
                resolve : {
                    users: function(userSrv) {
                        return userSrv.getUsers();
                    }
                }
            })
            .state('userPage', {
                url: '/userPage',
                template: '<user-page></user-page>',
                params: {
                    'user': {}
                }
            });
    });
})();