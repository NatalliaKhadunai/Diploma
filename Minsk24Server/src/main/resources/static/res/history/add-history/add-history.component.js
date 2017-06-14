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
                                    $ctrl.startYear = (-1) * $ctrl.history.startYear;
                                }
                                else {
                                    $ctrl.startYearEra = 'н.э.';
                                    $ctrl.startYear = $ctrl.history.startYear;
                                }
                                if ($ctrl.history.endYear < 0) {
                                    $ctrl.endYearEra = 'до н.э.';
                                    $ctrl.endYear = (-1) * $ctrl.history.endYear;
                                }
                                else {
                                    $ctrl.endYearEra = 'н.э.';
                                    $ctrl.endYear = $ctrl.history.endYear;
                                }
                                jQuery('#historyText').append($ctrl.history.content);
                                window.initializeHistoryWithImages();
                            });
                    }
                    else {
                        $ctrl.history.content = '< Введите текст >';
                        jQuery('#historyText').append($ctrl.history.content);
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
                        if (images[i].getAttribute('src').indexOf('resources\\images') == -1)
                            images[i].setAttribute('src', 'INSERT_IMAGE_SRC');
                    }
                    $ctrl.history.content = data.innerHTML;
                    if ($ctrl.startYearEra == 'до н.э.') $ctrl.history.startYear = (-1) * $ctrl.startYear;
                    else $ctrl.history.startYear = $ctrl.startYear;
                    if ($ctrl.endYearEra == 'до н.э.') $ctrl.history.endYear = (-1) * $ctrl.endYear;
                    else $ctrl.history.endYear = $ctrl.endYear;
                    if (typeof $stateParams['id'] == 'number' ||
                        typeof $stateParams['id'] == 'string')
                        $http.post('/v2/history/' + $stateParams['id'], $ctrl.history)
                            .then(function () {

                            });
                    else $http.post('/v2/history/', $ctrl.history)
                        .then(function () {

                        });
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
                $ctrl.imageUpload = function () {
                    window.imageUpload($stateParams['id']);
                };

            }
        });
})();