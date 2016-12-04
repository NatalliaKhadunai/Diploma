(function () {
    'use strict';

    angular
        .module('app')
        .controller('eventListCtrl', function (eventSrv) {
            let $ctrl = this;
            $ctrl.eventToAdd = {};
            $ctrl.addEvent = function () {
                eventSrv.addEvent($ctrl.eventToAdd);
            }
        });
})();