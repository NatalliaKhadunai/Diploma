(function () {
    'use strict';
    angular.module('app')
        .component('addHistory', {
            templateUrl: 'res/history/add-history/add-history.html',
            controller: function ($state, $stateParams, $http) {
                let $ctrl = this;
                $ctrl.text = '';
                document.designMode = 'on';
                $ctrl.headingNum = 6;
                $ctrl.startYearEra = 'до н.э.';
                $ctrl.endYearEra = 'до н.э.';
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
                $ctrl.addHistory = function () {
                    document.getElementById('imageUpload').click();
                    let history = {};
                    let data = document.getElementById('historyText');
                    let images = data.getElementsByTagName('img');
                    for (let i = 0; i < images.length; i++) {
                        images[i].setAttribute('src', 'INSERT_IMAGE_SRC');
                    }
                    history.content = data.outerHTML;
                    if ($ctrl.startYearEra == 'до н.э.') $ctrl.startYear = (-1) * $ctrl.startYear;
                    if ($ctrl.endYearEra == 'до н.э.') $ctrl.endYear = (-1) * $ctrl.endYear;
                    history.startYear = $ctrl.startYear;
                    history.endYear = $ctrl.endYear;
                    $http.post('/history/add', history).then(function () {

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
                $ctrl.insertBr = function () {
                    document.execCommand('insertBrOnReturn', false, null);
                };
            }
        });
})();