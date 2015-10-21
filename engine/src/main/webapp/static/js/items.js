var api = new API();
var app = angular.module('app', ['layout', 'ngRoute']);


app.config(function($routeProvider) {
    $routeProvider
        .when('/:page/:amount', {
            reloadOnSearch: false
        })
        .when('/', {
            reloadOnSearch: false
        });
});


app.controller('ItemsCtrl', function ($scope, $routeParams, $rootScope, $location) {
    $scope.page = 'items';
    $scope.itemListSearch = {
        amount: 30,
        page: 1
    };

    //if ($routeParams.page == undefined || $routeParams.amount == undefined) {
    //    $location.path('/' + $scope.itemListSearch.page + '/' + $scope.itemListSearch.amount);
    //}

    $rootScope.$on("$routeChangeSuccess", function(event, current) {
        if ($routeParams.page == undefined || $routeParams.amount == undefined) {
            $location.path('/' + $scope.itemListSearch.page + '/' + $scope.itemListSearch.amount);
            return
        }

        $scope.loadItems($routeParams.page, $routeParams.amount);
    });

    $scope.$watch('itemListSearch', function (newValue, oldValue) {
        // prevent to work with null params
        if (newValue.amount == '' || newValue.page == '') {
            return;
        }

        $location.path('/' + newValue.page + '/' + newValue.amount);
    }, false);

    $scope.loadItems = function (page, amount) {
        api.getItemList({page: page, amount: amount}).done(function (data) {
            $scope.items = data;
            $scope.$apply()
        });
    };

    $scope.prevPage = function () {
        if ($scope.itemListSearch.page == 1) {
            return;
        }

        $scope.itemListSearch.page--
    };

    $scope.nextPage = function () {
        $scope.itemListSearch.page++
    }
});


