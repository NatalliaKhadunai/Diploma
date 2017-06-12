(function () {
    'use strict';
    angular.module('app')
        .component('eventPage', {
            templateUrl: 'res/event/event-page/event-page.html',
            controller: function ($state, $stateParams, $http) {
                let $ctrl = this;
                $ctrl.event = {};
                $ctrl.now = new Date();
                $ctrl.user = {};
                $ctrl.getUser = function () {
                    $http.get('/currentUser').then(function (response) {
                        $ctrl.user = response.data;
                    });
                };
                $ctrl.getEvent = function () {
                    var eventId = $stateParams['eventId'];
                    $http.get('/events/' + eventId)
                        .then(function (response) {
                            $ctrl.event = response.data;
                        })
                };
                $ctrl.editEvent = function () {
                    $state.go('addEvent', {'id': $ctrl.event.id});
                };
                $ctrl.removeEvent = function () {
                    $http({
                        url: '/v3/events/' + $ctrl.event.id,
                        method: 'DELETE'
                    }).then(function () {
                    }, function (response) {
                        alert('Status code : ' + response.data.httpStatusCode + '\n'
                            + 'Message : ' + response.data.developerMessage);
                    });
                };
                $ctrl.getEventsOfTime = function (time) {
                    $state.go('events', {'time': time});
                };
                $ctrl.getEventsOfLocation = function (location) {
                    $state.go('events', {'location': location});
                };
                $ctrl.addComment = function () {
                    $http({
                        url: '/v1/events/' + $ctrl.event.id + '/comments',
                        method: 'POST',
                        data: $ctrl.comment
                    }).then(function (response) {
                        $ctrl.event = response.data;
                        $ctrl.comment.content = "";
                    }, function (response) {
                        alert('Status code : ' + response.data.httpStatusCode + '\n'
                            + 'Message : ' + response.data.developerMessage);
                    });
                };
                $ctrl.sendUserBeforeEventRate = function () {
                    $http({
                        url: '/v1/events/' + $ctrl.event.id + '/beforeEventRate',
                        method: 'POST',
                        data: $ctrl.beforeEventRate
                    }).then(function () {
                        $("#success-alert").alert();
                        $("#success-alert").fadeTo(2000, 500).slideUp(500, function(){
                            $("#success-alert").slideUp(500);
                        });
                    }, function (response) {
                        $("#danger-alert").alert();
                        $("#danger-alert").fadeTo(2000, 500).slideUp(500, function(){
                            $("#danger-alert").slideUp(500);
                        });
                    });
                };
                $ctrl.sendUserAfterEventRate = function () {
                    $http({
                        url: '/v1/events/' + $ctrl.event.id + '/afterEventRate',
                        method: 'POST',
                        data: $ctrl.afterEventRate
                    }).then(function () {
                        $("#success-alert").alert();
                        $("#success-alert").fadeTo(2000, 500).slideUp(500, function(){
                            $("#success-alert").slideUp(500);
                        });
                    }, function () {
                        $("#danger-alert").alert();
                        $("#danger-alert").fadeTo(2000, 500).slideUp(500, function(){
                            $("#danger-alert").slideUp(500);
                        });
                    });
                };
                $ctrl.getEvent();
                $ctrl.getUser();
            }
        });
})();