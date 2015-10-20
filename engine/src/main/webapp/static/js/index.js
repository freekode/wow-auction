var app = angular.module("app", ["layout"]);
var api = new API();


app.controller("IndexCtrl", function ($scope) {
    $scope.page = 'index';


    $scope.changeRealm = function () {
        api.getSnapshots24h({realmId: $scope.realmId}).done(function(data) {
            var graphData = [];

            data.map(function (elem) {
                graphData.push({
                    date: new Date(elem.lastModified),
                    existing: elem.existing,
                    overall: elem.closed + elem.existing + elem.newAmount,

                    closed: elem.closed,
                    newAmount: elem.newAmount
                });
            });

            indexGraph(graphData, 'graph');
        });
    };
});


