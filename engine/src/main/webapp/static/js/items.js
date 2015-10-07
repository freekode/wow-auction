var app = angular.module("app", ["layout"]);
var api = new API();


app.controller("ItemsCtrl", function ($scope) {
    $scope.page = 'items';
    var currentPage = 0;
    var amount = 10;


    api.getItemList({page: currentPage, amount: amount}).done(function (data) {
        $scope.items = data;
        $scope.$apply()
    });
});


