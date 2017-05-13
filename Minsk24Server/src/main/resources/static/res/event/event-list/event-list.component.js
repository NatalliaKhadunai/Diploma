(function () {
    'use strict';
    angular.module('app')
        .component('eventList', {
            templateUrl: 'res/event/event-list/event-list.html',
            bindings: {
                events: '<'
            },
            controller: function($state, userSrv) {
                let $ctrl = this;
                $ctrl.currentUser = userSrv.getCurrentUser();
                $ctrl.openEventPage = function(event) {
                    $state.go('eventPage', { 'event' : event });
                };
            }
        });
})();