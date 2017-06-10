(function () {
    'use strict';
    angular.module('app')
        .component('advertisementPage', {
            templateUrl: 'res/advertisement/advertisement-page/advertisement-page.html',
            controller: function ($state, $stateParams, $http) {
                let $ctrl = this;
                $ctrl.advertisement = {};
                $ctrl.user = {};
                $ctrl.getUser = function () {
                    $http.get('/currentUser').then(function (response) {
                        $ctrl.user = response.data;
                        $ctrl.isCurrent = true;
                    });
                };
                $ctrl.getAdvertisement = function () {
                    let advertisementId = $stateParams['advertisementId'];
                    $http.get('/advertisements/' + advertisementId)
                        .then(function (response) {
                            $ctrl.advertisement = response.data;
                        }, function (response) {
                            alert('Status code : ' + response.data.httpStatusCode + '\n'
                                + 'Message : ' + response.data.developerMessage);
                        });
                };
                $ctrl.getAdvertisementsOfHolder = function (holder) {
                    $state.go('advertisements', {'holder': holder.login});
                };
                $ctrl.editAdvertisement = function () {
                    $state.go('addAdvertisement', {'advertisement': $ctrl.advertisement});
                };
                $ctrl.removeAdvertisement = function () {
                    $http({
                        url: '/advertisements/' + $ctrl.advertisement.id,
                        method: 'DELETE'
                    }).then(function () {
                    }, function (response) {
                        alert('Status code : ' + response.data.httpStatusCode + '\n'
                            + 'Message : ' + response.data.developerMessage);
                    });
                };
                $ctrl.addComment = function () {
                    $http({
                        url: '/advertisements/' + $ctrl.advertisement.id + '/comments',
                        method: 'POST',
                        data: $ctrl.comment
                    }).then(function (response) {
                        $ctrl.advertisement = response.data;
                        $ctrl.comment.content = "";
                    }, function (response) {
                        alert('Status code : ' + response.data.httpStatusCode + '\n'
                            + 'Message : ' + response.data.developerMessage);
                    });
                };
                $ctrl.getAdvertisement();
                $ctrl.getUser();
            }
        });
})();