var app = angular.module("app", ["layout"]);
var api = new API();


app.controller("IndexCtrl", function ($scope) {
    $scope.page = 'index';


    $scope.changeRealm = function () {
        api.findAllSnapshots({realmId: $scope.realmId}).done(function (data) {
            var graphData = [{
                name: 'Existing',
                data: []
            }, {
                name: 'Overall',
                data: []
            }, {
                name: 'Closed',
                data: []
            }, {
                name: 'New',
                data: []
            }];

            data.map(function (elem) {
                graphData[0].data.push([elem.lastModified, elem.existing]);
                graphData[1].data.push([elem.lastModified, elem.closed + elem.existing + elem.newAmount]);
                graphData[2].data.push([elem.lastModified, elem.closed]);
                graphData[3].data.push([elem.lastModified, elem.newAmount]);
            });

            $('#graph').highcharts({
                chart: {
                    zoomType: 'x'
                },
                title: {
                    text: 'All items'
                },
                xAxis: {
                    type: 'datetime'
                },
                series: graphData
            });
        });
    };
});


