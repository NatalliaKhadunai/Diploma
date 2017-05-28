(function () {
    'use strict';
    angular.module('app')
        .component('userPage', {
            templateUrl: 'res/users/user-page/user-page.html',
            controller: function($state, $stateParams, $http) {
                let $ctrl = this;
                $ctrl.user = {};
                $ctrl.isCurrent = false;
                $ctrl.getUser = function() {
                    if (typeof $stateParams['user'].login != 'undefined') {
                        $ctrl.user = $stateParams['user'];
                        $http.get('/currentUser').then(function(response){
                            let currentUser = response.data;
                            if (currentUser.login == $ctrl.user.login)
                                $ctrl.isCurrent = true;
                        });
                    }
                    else {
                        $http.get('/currentUser').then(function(response){
                            $ctrl.user = response.data;
                            $ctrl.isCurrent = true;
                        });
                    }
                };
                $ctrl.getUser()
            }
        });
})();