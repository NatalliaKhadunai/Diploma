(function () {
    'use strict';
    angular.module('app')
        .component('eventPage', {
            templateUrl: 'res/event/event-page/event-page.html',
            controller: function($state, $stateParams, $http) {
                let $ctrl = this;
                $ctrl.event = {};
                $ctrl.now = new Date();
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
                $ctrl.addComment = function () {
                    $http({
                        url: '/events/' + $ctrl.event.id + '/comments',
                        method: 'POST',
                        data: $ctrl.comment
                    }).then(function (response) {
                        $ctrl.event = response.data;
                        $ctrl.comment.content = "";
                    }, function (response) {
                        alert('Status code : ' + response.data.httpStatusCode + '\n' + 'Message : ' + response.data.developerMessage);
                    });
                };
                $ctrl.sendUserBeforeEventRate = function () {
                    $http({
                        url: '/events/' + $ctrl.event.id + '/beforeEventRate',
                        method: 'POST',
                        data: $ctrl.beforeEventRate
                    }).then(function () {
                    }, function (response) {
                        alert('Status code : ' + response.data.httpStatusCode + '\n' + 'Message : ' + response.data.developerMessage);
                    });
                };
                $ctrl.sendUserAfterEventRate = function () {
                    $http({
                        url: '/events/' + $ctrl.event.id + '/afterEventRate',
                        method: 'POST',
                        data: $ctrl.afterEventRate
                    }).then(function () {
                    }, function (response) {
                        alert('Status code : ' + response.data.httpStatusCode + '\n' + 'Message : ' + response.data.developerMessage);
                    });
                };
                $ctrl.getEvent();
            }
        });
})();