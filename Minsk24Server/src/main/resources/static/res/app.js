(function() {
    angular.module('app', ['ui.router']).config(function($urlRouterProvider, $stateProvider) {
        $urlRouterProvider.otherwise('/');
        $stateProvider
            .state('home', {
                url: '/',
                template: '<home></home>'
            })
            .state('articles', {
                url: '/articles?{page}&{author}&{tag}&{interesting}',
                template: '<article-list></article-list>'
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
                url: '/advertisements?{page}&{sort}',
                template: '<advertisement-list></advertisement-list>'
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
                url: '/events?{page}&{sort}&{time}&{location}',
                template: '<event-list></event-list>'
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
            })
            .state('userPage.recentActions', {
                url: '/recent-actions',
                template: '<user-page-recent-actions></user-page-recent-actions>'
            })
            .state('userPage.settings', {
                url: '/settings',
                template: '<user-page-settings></user-page-settings>'
            })
            .state('userPage.advertisements', {
                url: '/advertisements?{page}',
                template: '<user-page-advertisements></user-page-advertisements>'
            });
    });
})();