(function () {
    'use strict';
    angular.module('app')
        .component('history', {
            templateUrl: 'res/history/history.html',
            controller: function ($state, $stateParams, $http) {
                let $ctrl = this;
                $ctrl.currentUser = {};
                $ctrl.userCanEdit = false;
                $ctrl.getCurrentUser = function () {
                    $http.get('/currentUser').then(function(response){
                        let user = response.data;
                        if (user.role == 'ADMIN' || user.role == 'AUTHOR')
                            $ctrl.userCanEdit = true;
                    });
                };
                $ctrl.initialization = function () {
                    $http.get('/history')
                        .then(function (response) {
                            $ctrl.history = response.data;
                            $ctrl.initializeTimelineElements();
                        }, function () {

                        });
                };
                $ctrl.initializeTimelineElements = function () {
                    let timelineElem = document.getElementById('example');
                    for (let i = 0; i < $ctrl.history.length; i++) {
                        let dotElem = document.createElement('div');
                        dotElem.setAttribute('data-year', i);
                        dotElem.textContent = ($ctrl.history[i].startYear < 0 ?
                            $ctrl.history[i].startYear.toString().replace('-', '') + ' г. до н.э.' :
                            $ctrl.history[i].startYear) + ' г.' +
                             ' - ' + ($ctrl.history[i].endYear < 0 ?
                                $ctrl.history[i].endYear.toString().replace('-', '') + ' г. до н.э.' :
                                $ctrl.history[i].endYear) + ' г.';
                        if (i == 0) dotElem.setAttribute('class', 'active');
                        timelineElem.appendChild(dotElem);

                        let contentElem = document.createElement('div');
                        contentElem.setAttribute('id', i);
                        contentElem.setAttribute('class', 'article description');
                        contentElem.innerHTML = $ctrl.history[i].content;
                        document.getElementById('content-container').appendChild(contentElem);
                    }
                    $ctrl.initializeTimeline();
                };
                $ctrl.initializeTimeline = function () {
                    jQuery(".article").hide();
                    jQuery("#1").fadeIn(200);
                    jQuery('#example').timeliny({
                        onLeave: function (currYear, nextYear) {
                            $("#" + currYear).fadeOut(0);
                            $("#" + nextYear).fadeIn(200);
                        }
                    });
                };
                $ctrl.editHistory = function () {
                    let num = document.getElementsByClassName('active')[0].getAttribute('data-year');
                    $state.go('addHistory', {id : $ctrl.history[num].id});
                };
                $ctrl.initialization();
                $ctrl.getCurrentUser();
            }
        });
})();