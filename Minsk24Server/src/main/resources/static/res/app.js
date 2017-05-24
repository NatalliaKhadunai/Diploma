(function() {
    angular.module('app', ['ui.router']).config(function($urlRouterProvider, $stateProvider) {
        $urlRouterProvider.otherwise('/');
        $stateProvider
            .state('home', {
                url: '/',
                template: '<home></home>'
            })
            .state('articles', {
                url: '/articles',
                template: '<article-list></article-list>',
                params: {
                    'author': {}
                }
            })
            .state('articlesByAuthor', {
                url: '/articles/authors/{authorLogin}',
                template: '<article-list-by-author></article-list-by-author>'
            })
            .state('articlesByTag', {
                url: '/articles/tags/{tagName}',
                template: '<article-list-by-tag></article-list-by-tag>'
            })
            .state('articlesByAuthorAndTag', {
                url: '/articles/authors/{authorLogin}/tags/{tagName}',
                template: '<article-list-by-author-and-tag></article-list-by-author-and-tag>'
            })
            .state('articlePage', {
                url: '/articles/{articleId}',
                template: '<article-page></article-page>'
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
                template: '<advertisement-list></advertisement-list>'
            })
            .state('advertisementsByHolder', {
                url: '/advertisements/holder/{holderLogin}',
                template: '<advertisement-list-by-holder></advertisement-list-by-holder>'
            })
            .state('advertisementPage', {
                url: '/advertisements/{advertisementId}',
                template: '<advertisement-page></advertisement-page>'
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
                template: '<event-list></event-list>'
            })
            .state('eventsByTime', {
                url: '/events/time/{time}',
                template: '<event-list-by-time></event-list-by-time>'
            })
            .state('eventsByLocation', {
                url: '/events/location/{location}',
                template: '<event-list-by-location></event-list-by-location>'
            })
            .state('eventPage', {
                url: '/events/{eventId}',
                template: '<event-page></event-page>'
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
                	user: {}
                }
            });
    });
})();