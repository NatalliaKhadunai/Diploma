(function () {
    'use strict';
    angular.module('app')
        .component('eventPage', {
            templateUrl: 'res/event/event-page/event-page.html',
            controller: function($state, $stateParams, $http) {
                let $ctrl = this;
                $ctrl.event = {};
                $ctrl.getEvent = function() {
                    var event = $stateParams['event'];
                    $http.get('/events/' + event.id)
                        .then(function (response) {
                            $ctrl.event = response.data;
                        })
                };
                $ctrl.editEvent = function() {
                    $state.go('addEvent', { 'event' : $ctrl.event });
                };
                $ctrl.getEvent();
            }
        });
})();