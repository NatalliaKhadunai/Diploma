(function () {
    'use strict';
    angular.module('app')
        .component('addRemoveTag', {
            templateUrl: 'res/tag/add-remove-tag/add-remove-tag.html',
            controller: function($state, $stateParams, $http) {
                let $ctrl = this;
                $ctrl.chosenTags = [];
                $ctrl.tags = [];
                $ctrl.initializeTagSelect = function () {
                    $http.get('/tags')
                        .then(function (response) {
                            $ctrl.tags = response.data;
                        });
                };
                $ctrl.addTag = function () {
                    $http.post('/tags', $ctrl.tagName)
                        .then(function(response) {
                            $ctrl.tags.push(response.data);
                        });
                };
                $ctrl.deleteTags = function () {
                    $http({
                        url: '/tags',
                        method: 'DELETE',
                        data: $ctrl.chosenTags,
                        headers: {
                            "Content-Type": "application/json;charset=utf-8"
                        }
                    }).then(function (response) {
                        for (var i=0;i<$ctrl.chosenTags.length;i++) {
                            var index = $ctrl.tags.indexOf($ctrl.chosenTags[i]);
                            $ctrl.tags.splice(index, 1);
                        }
                    });
                };
                $ctrl.initializeTagSelect();
            }
        });
})();