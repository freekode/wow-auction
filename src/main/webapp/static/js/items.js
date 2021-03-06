var api = new API();
var app = angular.module('app', ['layout', 'ngRoute']);


app.config(function ($routeProvider) {
    $routeProvider
        .when('/', {
            reloadOnSearch: false
        })
        .when('/:page/:amount', {
            reloadOnSearch: false
        });
});


app.controller('ItemsCtrl', function ($scope, $routeParams, $rootScope, $location) {
    $scope.page = 'items';
    $scope.itemListSearch = {};
    var defaultListConfig = {
        amount: 30,
        page: 1
    };
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


    $rootScope.$on("$routeChangeSuccess", function (event, current) {
        if ($routeParams.page == undefined || $routeParams.amount == undefined) {
            $scope.itemListSearch = defaultListConfig;
            return;
        }

        $scope.itemListSearch = $routeParams;
        $scope.loadItems($routeParams.page, $routeParams.amount);
    });

    $scope.$watch('itemListSearch', function (newValue, oldValue) {
        if (newValue == undefined ||
            newValue.page == undefined || newValue.amount == undefined ||
            newValue.page == '' || newValue.amount == '') {
            return;
        }

        $location.path('/' + newValue.page + '/' + newValue.amount);
    }, true);

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
    };

    $scope.qualityClass = function (itemInfo) {
        return qualityClasses[itemInfo.quality.codeInteger];
    }
});

