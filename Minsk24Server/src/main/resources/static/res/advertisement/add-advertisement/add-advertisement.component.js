(function () {
    'use strict';
    angular.module('app')
        .component('addAdvertisement', {
            templateUrl: 'res/advertisement/add-advertisement/add-advertisement.html',
            controller: function($state, $stateParams, $http) {
                let $ctrl = this;
                $ctrl.advertisement = {};
                $ctrl.dateNow = new Date();
                $ctrl.dateStr = new Date();
                $ctrl.required = typeof $stateParams['id'] != 'undefined' &&
                $stateParams['id'].toString().length != 0 ?
                    false : true;
                $ctrl.initializeAdvertisement = function () {
                    $http.get('/advertisements/' + $stateParams['id'])
                        .then(function (response) {
                            $ctrl.advertisement = response.data;
                            $ctrl.dateStr = new Date($ctrl.advertisement.expirationDate)
                        }, function (response) {
                            alert('Status code : ' + response.data.httpStatusCode + '\n'
                                + 'Message : ' + response.data.developerMessage);
                        });
                };
                if ($stateParams['id']) $ctrl.initializeAdvertisement();
            }
        });
})();