(function () {
    'use strict';
    angular.module('app')
        .component('addRemoveTag', {
            templateUrl: 'res/tag/add-remove-tag/add-remove-tag.html',
            controller: function ($state, $stateParams, $http) {
                let $ctrl = this;
                $ctrl.chosenTags = [];
                $ctrl.tags = [];
                $ctrl.initializeTagSelect = function () {
                    $http.get('/tags')
                        .then(function (response) {
                            $ctrl.tags = response.data;
                        }, function (response) {
                            alert('Status code : ' + response.data.httpStatusCode + '\n'
                                + 'Message : ' + response.data.developerMessage);
                        });
                };
                $ctrl.addTag = function () {
                    if ($ctrl.tagName.length != 0) {
                        $http.post('/v2/tags', $ctrl.tagName)
                            .then(function (response) {
                                $ctrl.tags.push(response.data);
                                $ctrl.tagName = '';
                            }, function (response) {
                                alert('Status code : ' + response.data.httpStatusCode + '\n'
                                    + 'Message : ' + response.data.developerMessage);
                            });
                    }
                };
                $ctrl.deleteTags = function () {
                    $http({
                        url: '/v2/tags',
                        method: 'DELETE',
                        data: $ctrl.chosenTags,
                        headers: {
                            "Content-Type": "application/json;charset=utf-8"
                        }
                    }).then(function (response) {
                        for (var i = 0; i < $ctrl.chosenTags.length; i++) {
                            var index = $ctrl.tags.indexOf($ctrl.chosenTags[i]);
                            $ctrl.tags.splice(index, 1);
                        }
                    }, function (response) {
                        alert('Status code : ' + response.data.httpStatusCode + '\n'
                            + 'Message : ' + response.data.developerMessage);
                    });
                };
                $ctrl.initializeTagSelect();
            }
        });
})();