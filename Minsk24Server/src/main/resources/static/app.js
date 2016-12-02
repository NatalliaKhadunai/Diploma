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
                template: '<h1>Advertisements</h1>'
            })
            .state('events', {
                url: '/events',
                template: '<h1>Events</h1>'
            });
    });
})();