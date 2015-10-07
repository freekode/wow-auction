var app = angular.module("app", ["layout"]);
var api = new API();


app.controller("IndexCtrl", function ($scope, $http) {
    $scope.page = 'index';


    $scope.changeRealm = function () {
        api.getSnapshots24h({realmId: $scope.realmId}).done(function(data) {

        });
        $http.get("/models/snapshots/24h").then(function (resp) {
            var data = [];

            resp.data.map(function (elem) {
                data.push({
                    date: new Date(elem.lastModified),
                    existing: elem.existing,
                    overall: elem.closed + elem.existing + elem.newAmount,

                    closed: elem.closed,
                    newAmount: elem.newAmount
                });
            });

            indexGraph(data, 'graph');
        })
    };
});


