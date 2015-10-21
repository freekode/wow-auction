var api = new API();
var app = angular.module('app', ['layout', 'ngRoute']);


app.config(function($routeProvider, $locationProvider) {
    $routeProvider.when('/:page/:amount', {
        reloadOnSearch: false
    });

    //$locationProvider.html5Mode(true);
});


app.controller('ItemsCtrl', function ($scope, $routeParams, $rootScope, $location) {
    $scope.page = 'items';
    $scope.currentPage = 0;
    $scope.amount = 30;


    $location.path('0/' + $scope.amount);

    $scope.loadItems = function (page, amount) {
        api.getItemList({page: page, amount: amount}).done(function (data) {
            $scope.items = data;
            $scope.$apply()
        });
    };

    $rootScope.$on("$routeChangeSuccess", function(event, current) {
        $scope.loadItems($routeParams.page, $routeParams.amount);
    });
});


