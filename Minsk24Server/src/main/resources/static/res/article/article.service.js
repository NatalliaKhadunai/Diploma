(function() {
    angular.module('app').service('articleSrv', function($http) {
        let $ctrl = this;
        $ctrl.getArticles = function() {
            return $http.get('/articles').then(function(response){
                return response.data;
            });
        };
    });
})();
