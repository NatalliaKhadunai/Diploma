(function () {
    'use strict';
    angular.module('app')
        .component('eventList', {
            templateUrl: 'res/event/event-list/event-list.html',
            controller: function($state, $stateParams, userSrv, $http) {
                let $ctrl = this;
                $ctrl.currentUser = userSrv.getCurrentUser();
                $ctrl.openEventPage = function(event) {
                    $state.go('eventPage', { 'eventId' : event.id });
                };
                $ctrl.initializeEvents = function () {
                    if (typeof $stateParams['time'] == 'number') {
                        $http.get('/events/time/' + $stateParams['time'],
                            {params: {pageNum: 1}})
                            .then(function (response) {
                            $ctrl.events = response.data;
                        });
                    }
                    else if (typeof $stateParams['location'] == 'string') {
                        $http.get('/events/location/' + $stateParams['location'],
                            {params: {pageNum: 1}})
                            .then(function (response) {
                                $ctrl.events = response.data;
                        });
                    }
                    else {
                        $http.get('/events', {params: {pageNum: 1}}).then(function (response) {
                            $ctrl.events = response.data;
                        });
                    }
                };
                $ctrl.getEvents = function (pageNum) {
                    if (typeof $stateParams['time'] == 'number') {
                        $http.get('/events/time/' + $stateParams['time'],
                            {params: {pageNum: pageNum}})
                            .then(function (response) {
                                $ctrl.events = response.data;
                            });
                    }
                    else if (typeof $stateParams['location'] == 'string') {
                        $http.get('/events/location/' + $stateParams['location'],
                            {params: {pageNum: pageNum}})
                            .then(function (response) {
                                $ctrl.events = response.data;
                            });
                    }
                    else {
                        $http.get('/events', {params: {pageNum: pageNum}})
                            .then(function (response) {
                                $ctrl.events = response.data;
                        });
                    }
                };
                $ctrl.getPageCount = function () {
                    if (typeof $stateParams['time'] == 'number') {
                        $http.get('/events/time/' + $stateParams['time'] + '/count')
                            .then(function (response) {
                                $ctrl.pageCount = response.data;
                            });
                    }
                    else if (typeof $stateParams['location'] == 'string') {
                        $http.get('/events/location/' + $stateParams['location'] + '/count')
                            .then(function (response) {
                                $ctrl.pageCount = response.data;
                            });
                    }
                    else {
                        $http.get('/events/count/')
                            .then(function (response) {
                                $ctrl.pageCount = response.data;
                            });
                    }
                };
                $ctrl.getPageNumber = function() {
                    return new Array($ctrl.pageCount);
                };
                $ctrl.initializeEvents();
                $ctrl.getPageCount();
            }
        });
})();