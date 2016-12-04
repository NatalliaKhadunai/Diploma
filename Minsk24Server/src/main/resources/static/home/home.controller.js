(function () {
    'use strict';

    angular
        .module('app')
        .controller('homeCtrl', function ($http) {
            let $ctrl = this;
            $ctrl.file = {};
            $ctrl.uploadFile = function () {
                var uploadUrl= '/uploadFile';
                var formData=new FormData();
                formData.append("file", $ctrl.file);
                $http({
                    method: 'POST',
                    url: uploadUrl,
                    headers: {'Content-Type': undefined},
                    data: formData,
                    transformRequest: function(data, headersGetterFunction) {
                        return data;
                    }
                })
                    .success(function(data, status) {
                        alert("success");
                    })

            }
        });
})();