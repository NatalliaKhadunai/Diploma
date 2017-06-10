(function () {
    'use strict';
    angular.module('app')
        .component('addHistory', {
            templateUrl: 'res/history/add-history/add-history.html',
            controller: function($state, $stateParams, $http) {
                let $ctrl = this;
                $ctrl.text = '';
                document.designMode = 'on';
                $ctrl.headingNum = 6;
                $ctrl.chosenImage = '';
                $ctrl.images = [];
                $ctrl.getSelectionText = function() {
                    let text = "";
                    if (window.getSelection) {
                        text = window.getSelection().toString();
                    } else if (document.selection && document.selection.type != "Control") {
                        text = document.selection.createRange().text;
                    }
                    return text;
                };
                $ctrl.preventDefault = function () {
                    event.preventDefault();
                };
                $ctrl.makeSelectedBold = function () {
                    document.execCommand('bold', false, false);
                };
                $ctrl.makeSelectedItalic = function () {
                    document.execCommand('italic', false, false);
                };
                $ctrl.makeSelectedLarger = function () {
                    if ($ctrl.headingNum > 1) {
                        $ctrl.headingNum -= 1;
                        let heading = '<H' + $ctrl.headingNum + '>';
                        document.execCommand('formatBlock', false, heading);
                    }
                };
                $ctrl.makeSelectedSmaller = function () {
                    if ($ctrl.headingNum < 6) {
                        $ctrl.headingNum += 1;
                        let heading = '<H' + $ctrl.headingNum + '>';
                        document.execCommand('formatBlock', false, heading);
                    }
                };
                $ctrl.insertImage = function () {
                    document.execCommand('insertHTML', false,  $ctrl.chosenImage);
                };
                $ctrl.addHistory = function () {
                    let data = document.getElementById('historyText').innerHTML;
                    $http.post('/history/add', data).then(function () {

                    });
                };
                $ctrl.setChosenImage = function (img) {
                    $ctrl.chosenImage = document.getElementById(img).getAttribute('src');
                };
                $ctrl.makeSelectedUnderline = function () {
                    document.execCommand('underline', false, null);
                };
                $ctrl.insertLink = function () {
                    document.execCommand('createLink', false, $ctrl.link);
                };
            }
        });
})();