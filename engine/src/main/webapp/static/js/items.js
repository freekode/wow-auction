var api = new API();
var app = angular.module('app', ['layout', 'ngRoute']);


app.config(function($routeProvider) {
    $routeProvider.when('/:page/:amount', {
        reloadOnSearch: false
    });
});


app.controller('ItemsCtrl', function ($scope, $routeParams, $rootScope, $location) {
    $scope.page = 'items';
    $scope.itemListSearch = {
        amount: 30,
        page: 0
    };

    if ($routeParams.page == undefined || $routeParams.amount == undefined) {
        $location.path('/' + $scope.itemListSearch.page + '/' + $scope.itemListSearch.amount);
    }
    
    $scope.$watch('itemListSearch', function (newValue, oldValue) {
        if (newValue.amount == '' || newValue.page == '' ) {
            return;
        }

        $scope.loadItems(newValue.page, newValue.amount)
    }, true);

    $scope.loadItems = function (page, amount) {
        api.getItemList({page: page, amount: amount}).done(function (data) {
            $scope.items = data;
            $scope.$apply()
        });
    };

    $rootScope.$on("$routeChangeSuccess", function(event, current) {
        $scope.loadItems($routeParams.page, $routeParams.amount);
    });

    $scope.prevPage = function () {
        if ($scope.itemListSearch.page == 0) {
            return;
        }

        $scope.itemListSearch.page--
    };

    $scope.nextPage = function () {
        $scope.itemListSearch.page++
    }
});


