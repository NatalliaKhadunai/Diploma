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
                $ctrl.getEvents = function () {
                    let params = $ctrl.defineParams();
                    $http.get('/events', {params: params})
                        .then(function (response) {
                            $ctrl.events = response.data;
                        });
                };
                $ctrl.loadEventsByPage = function (page) {
                    $state.go('events', {page : page});
                };
                $ctrl.getPageCount = function () {
                    let params = $ctrl.defineParams();
                    $http.get('/events/count/', {params: params})
                        .then(function (response) {
                            $ctrl.pageCount = response.data;
                        });
                };
                $ctrl.getPageNumber = function () {
                    return new Array($ctrl.pageCount);
                };
                $ctrl.changeSort = function (sort) {
                    $state.go('events', {page : 1, sort: sort});
                };
                $ctrl.defineParams = function () {
                    let params = {};
                    if (typeof $stateParams['time'] != 'undefined')
                        params.time = $stateParams['time'];
                    if (typeof $stateParams['location'] != 'undefined')
                        params.location = $stateParams['location'];
                    if (typeof $stateParams['sort'] != 'undefined')
                        params.sort = $stateParams['sort'];
                    if (typeof $stateParams['page'] != 'undefined')
                        params.page = $stateParams['page'];
                    else params.page = 1;
                    return params;
                };
                $ctrl.getEvents();
                $ctrl.getPageCount();
            }
        });
})();