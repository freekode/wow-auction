app = angular.module("app", ["layout"]);

app.controller("IndexCtrl", function ($scope, $http) {
    //$http.get("/models/snapshots/24h").then(function (resp) {
    //    var data = [];
    //
    //    resp.data.map(function (elem) {
    //        data.push({
    //            date: new Date(elem.lastModified),
    //            existing: elem.existing,
    //            overall: elem.closed + elem.existing + elem.newAmount,
    //
    //            closed: elem.closed,
    //            newAmount: elem.newAmount
    //        });
    //    });
    //
    //    var gr = new IndexGraph({
    //        id: '#graph',
    //        yTitle: 'Snapshot size',
    //        xTitle: 'Data'
    //    });
    //    gr.addXAxis(dataClosed, 'date');
    //    gr.addYAxis(dataClosed, 'value');
    //    gr.addGrid();
    //    gr.addArea(dataOverall, 'date', 'value');
    //    gr.addLine(dataClosed, 'date', 'value');
    //    gr.addPoints(dataClosed, 'date', 'value');
    //    graph(data, 'graph');
    //})
});


