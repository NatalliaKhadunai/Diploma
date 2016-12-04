(function () {
    'use strict';
    angular.module('app')
        .component('userList', {
            templateUrl: 'users/user-list.html',
            controller: 'userListCtrl',
            bindings: {
                users: '<'
            }
        });
})();