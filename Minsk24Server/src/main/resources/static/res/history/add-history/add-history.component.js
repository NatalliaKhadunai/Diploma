(function () {
    'use strict';
    angular.module('app')
        .component('addHistory', {
            templateUrl: 'res/history/add-history/add-history.html',
            controller: function ($state, $stateParams, $http) {
                let $ctrl = this;
                $ctrl.headingNum = 6;
                $ctrl.history = {};
                $ctrl.startYearEra = 'до н.э.';
                $ctrl.endYearEra = 'до н.э.';
                (function init() {
                    if (typeof $stateParams['id'] == 'string') {
                        $http.get('/v2/history/' + $stateParams['id'])
                            .then(function (response) {
                                $ctrl.history = response.data;
                                if ($ctrl.history.startYear < 0) {
                                    $ctrl.startYearEra = 'до н.э.';
                                    $ctrl.history.startYear = (-1) * $ctrl.startYear;
                                }
                                else $ctrl.startYearEra = 'н.э.';
                                if ($ctrl.history.endYear < 0) {
                                    $ctrl.endYearEra = 'до н.э.';
                                    $ctrl.history.endYear = (-1) * $ctrl.endYear;
                                }
                                else $ctrl.endYearEra = 'н.э.';
                                jQuery('#historyText').append($ctrl.history.content);
                            });
                    }
                    else {
                        $ctrl.history.content = '< Введите текст >';
                    }
                })();
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
                    let data = document.getElementById('historyText');
                    let images = data.getElementsByTagName('img');
                    for (let i = 0; i < images.length; i++) {
                        images[i].setAttribute('src', 'INSERT_IMAGE_SRC');
                    }
                    $ctrl.history.content = data.innerHTML;
                    if ($ctrl.startYearEra == 'до н.э.') history.startYear = (-1) * $ctrl.startYear;
                    if ($ctrl.endYearEra == 'до н.э.') history.endYear = (-1) * $ctrl.endYear;
                    else {
                        history.startYear = $ctrl.startYear;
                        history.endYear = $ctrl.endYear;
                    }
                    $http.post('/v2/history/add', history).then(function () {

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
                    document.execCommand('insertHTML', false, '<br>');
                };


            }
        });
})();