(function () {
    'use strict';
    angular.module('app')
        .component('eventList', {
            templateUrl: 'events/event-list.html',
            controller: 'eventListCtrl',
            bindings: {
                events: '<'
            }
        });
})();