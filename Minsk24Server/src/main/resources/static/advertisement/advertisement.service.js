(function() {
    angular.module('app').service('advertisementSrv', function($http) {
        let $ctrl = this;
        $ctrl.getAdvertisements = function() {
            return $http.get('/advertisements').then(function(response){
                return response.data;
            });
        };
        $ctrl.addAdvertisement = function (advertisement) {
            $http.post('/addAdvertisement', advertisement);
        };
    });
})();
