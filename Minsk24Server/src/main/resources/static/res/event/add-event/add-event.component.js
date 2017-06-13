(function () {
    'use strict';
    angular.module('app')
        .component('addEvent', {
            templateUrl: 'res/event/add-event/add-event.html',
            controller: function($state, $stateParams, $http) {
                let $ctrl = this;
                $ctrl.event = {};
                $ctrl.dateNow = new Date();
                $ctrl.required = typeof $stateParams['id'] != 'undefined' &&
                $stateParams['id'].toString().length != 0 ?
                    false : true;
                $ctrl.initializeEvent = function () {
                    $http.get('/events/' + $stateParams['id'])
                        .then(function (response) {
                            $ctrl.event = response.data;
                            $ctrl.timeStr = new Date($ctrl.event.time)
                        }, function (response) {
                            alert('Status code : ' + response.data.httpStatusCode + '\n'
                                + 'Message : ' + response.data.developerMessage);
                        });
                };
                if ($stateParams['id']) $ctrl.initializeEvent();
            }
        });
})();