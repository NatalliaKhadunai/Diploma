(function () {
    'use strict';
    angular.module('app')
        .component('userPageSettings', {
            templateUrl: 'res/users/user-page/settings/user-page-settings.html',
            controller: function($state, $stateParams, $http) {
                let $ctrl = this;
                $ctrl.user = {};
                $ctrl.getUser = function() {
                    if (typeof $stateParams['user'].login != 'undefined') {
                        $ctrl.user = $stateParams['user'];
                    }
                    else {
                        $http.get('/currentUser').then(function(response){
                            $ctrl.user = response.data;
                        });
                    }

                };
                $ctrl.initializeTagSelect = function () {
                    $http.get('/tags')
                        .then(function (response) {
                            $ctrl.tags = response.data;
                        });
                };
                $ctrl.initializeInterestingTags = function () {
                    $http.get('/account/tags/interesting')
                        .then(function (response) {
                            $ctrl.interestingTags = response.data;
                        });
                };
                $ctrl.isExcluded = function (tag) {
                    for (var i=0;i<$ctrl.interestingTags.length;i++) {
                        if ($ctrl.interestingTags[i].id == tag.id) return false;
                    }
                    return true;
                };
                $ctrl.addOrExcludeTag = function (tag) {
                    let element = angular.element(document.querySelector('#' + tag.name));
                    element.attr('disabled','disabled');
                    if ($ctrl.isExcluded(tag)) {
                        $http.post('/account/tags/' + tag.id + '/add')
                            .then(function (response) {
                                $ctrl.interestingTags.push(response.data);
                                element.removeAttr('disabled');
                            })
                    }
                    else {
                        $http.post('/account/tags/' + tag.id + '/exclude')
                            .then(function () {
                                var index = -1;
                                for (var i=0;i<$ctrl.interestingTags.length;i++) {
                                    if ($ctrl.interestingTags[i].id == tag.id) index = i;
                                }
                                $ctrl.interestingTags.splice(index, 1);
                                element.removeAttr('disabled');
                            })
                    }
                };
                $ctrl.getUser();
                $ctrl.initializeTagSelect();
                $ctrl.initializeInterestingTags();
            }
        });
})();