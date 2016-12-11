(function () {
    'use strict';
    angular.module('app')
        .component('addEvent', {
            templateUrl: 'event/add-event/add-event.html',
            controller: function($state, $stateParams) {
                let $ctrl = this;
                $ctrl.event = $stateParams['event'];
            }
        });
})();