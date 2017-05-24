(function () {
    'use strict';
    angular.module('app')
        .component('advertisementPage', {
            templateUrl: 'res/advertisement/advertisement-page/advertisement-page.html',
            controller: function($state, $stateParams, $http) {
                let $ctrl = this;
                $ctrl.advertisement = {};
                $ctrl.getAdvertisement = function() {
                    var advertisementId = $stateParams['advertisementId'];
                    $http.get('/advertisements/' + advertisementId)
                        .then(function (response) {
                            $ctrl.advertisement = response.data;
                        });
                };
                $ctrl.getAdvertisementsOfHolder = function (holder) {
                    $state.go('advertisementsByHolder', { 'holderLogin' : holder.login });
                };
                $ctrl.editAdvertisement = function() {
                    $state.go('addAdvertisement', { 'advertisement' : $ctrl.advertisement });
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
                        alert('Status code : ' + response.data.httpStatusCode + '\n' + 'Message : ' + response.data.developerMessage);
                    });
                };
                $ctrl.getAdvertisement();
            }
        });
})();