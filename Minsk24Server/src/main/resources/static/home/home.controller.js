(function () {
    'use strict';

    angular
        .module('app')
        .controller('homeCtrl', function ($http) {
            let $ctrl = this;
            $ctrl.obj = {};
            $ctrl.uploadFile = function () {
                var fd = new FormData();
                fd.append('file', file.files[0]);
                fd.append('name', $ctrl.obj.name);
                fd.append('id', $ctrl.obj.id);
                $http.post('/uploadFile', fd, {
                        transformRequest: angular.identity,
                        headers: {'Content-Type': undefined}
                    })
                    .success(function(){
                    })
                    .error(function(){
                    });
            };
            $ctrl.sendFile = function () {
                $ctrl.obj.file = file.files[0];
                $http.post('/uploadFile', $ctrl.obj, {
                        transformRequest: angular.identity,
                        headers: {'Content-Type': undefined}
                    })
                    .success(function(){
                    })
                    .error(function(){
                    });
            };
        });
})();