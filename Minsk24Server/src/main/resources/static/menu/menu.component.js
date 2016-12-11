(function () {
    'use strict';
    angular.module('app')
        .component('menuComponent', {
            templateUrl: 'menu/menu.html',
            controller : function(userSrv) {
                let $ctrl = this;
                $ctrl.currentUser = userSrv.getCurrentUser();
            }
        });
})();