var app = angular.module("app", ["layout"]);
var api = new API();


app.controller("ItemCtrl", function ($scope) {
    $scope.page = 'item';
    var qualityClasses = [
        'item__poor',
        'item__common',
        'item__uncommon',
        'item__rare',
        'item__epic',
        'item__legendary',
        'item__artifact',
        'item__heirloom',
        'item__wow_token'
    ];

    $scope.qualityClass = function (codeInteger) {
        return qualityClasses[codeInteger];
    }
});


