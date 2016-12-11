(function () {
    'use strict';
    angular.module('app')
        .component('eventPage', {
            templateUrl: 'event/event-page/event-page.html',
            controller: function($state, $stateParams) {
                let $ctrl = this;
                $ctrl.event = $stateParams['event'];
            }
        });
})();