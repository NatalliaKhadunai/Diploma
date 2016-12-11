(function() {
    angular.module('app').service('eventSrv', function($http) {
        let $ctrl = this;
        $ctrl.getEvents = function() {
            return $http.get('/events').then(function(response){
                return response.data;
            });
        };
        $ctrl.addEvent = function (event) {
            $http.post('/addEvent', event);
        };
    });
})();
