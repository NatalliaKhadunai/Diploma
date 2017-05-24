(function () {
    'use strict';
    angular.module('app')
        .component('eventList', {
            templateUrl: 'res/event/event-list/event-list.html',
            controller: function ($state, $stateParams, $http) {
                let $ctrl = this;
                $ctrl.openEventPage = function (event) {
                    $state.go('eventPage', {'eventId': event.id});
                };
                $ctrl.initializeEvents = function () {
                    $http.get('/events', {params: {pageNum: 1}}).then(function (response) {
                        $ctrl.events = response.data;
                    });
                };
                $ctrl.getEvents = function (pageNum) {
                    $http.get('/events', {params: {pageNum: pageNum}})
                        .then(function (response) {
                            $ctrl.events = response.data;
                        });
                };
                $ctrl.getPageCount = function () {
                    $http.get('/events/count/')
                        .then(function (response) {
                            $ctrl.pageCount = response.data;
                        });
                };
                $ctrl.getPageNumber = function () {
                    return new Array($ctrl.pageCount);
                };
                $ctrl.initializeEvents();
                $ctrl.getPageCount();
            }
        });
})();