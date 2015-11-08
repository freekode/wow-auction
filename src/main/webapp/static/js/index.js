var app = angular.module("app", ["layout"]);
var api = new API();


app.controller("IndexCtrl", function ($scope) {
    $scope.page = 'index';

    buildGraph();

    $scope.changeRealm = function () {
        api.findAllSnapshots({realmId: $scope.realmId}).done(function (data) {
            var graphData = {
                dataSets: [{
                    data: [],
                    name: "Overall"
                }, {
                    data: [],
                    name: "New"
                }, {
                    data: [],
                    name: "Existing"
                }, {
                    data: [],
                    name: "Closed"
                }]
            };

            data.map(function (elem) {
                graphData.dataSets[0].data.push([elem.lastModified, elem.closed + elem.existing + elem.newAmount]);
                graphData.dataSets[1].data.push([elem.lastModified, elem.newAmount]);
                graphData.dataSets[2].data.push([elem.lastModified, elem.existing]);
                graphData.dataSets[3].data.push([elem.lastModified, elem.closed]);
            });

            graphData.dataSets.map(function (dataSet, i) {
                $('<div class="chart">').appendTo('#graph').highcharts({
                    rangeSelector: {
                        selected: 1
                    },
                    chart: {
                        marginLeft: 40, // Keep all charts left aligned
                        spacingTop: 20,
                        spacingBottom: 20,
                        zoomType: 'x',
                        height: 250
                    },
                    title: {
                        text: dataSet.name,
                        align: 'left',
                        margin: 0,
                        x: 30
                    },
                    credits: {
                        enabled: false
                    },
                    legend: {
                        enabled: false
                    },
                    xAxis: {
                        crosshair: true,
                        events: {
                            setExtremes: syncExtremes
                        },
                        type: 'datetime'
                    },
                    yAxis: {
                        title: {
                            text: null
                        }
                    },
                    //tooltip: {
                    //    positioner: function () {
                    //        return {
                    //            x: this.chart.chartWidth - this.label.width, // right aligned
                    //            y: -1 // align to title
                    //        };
                    //    },
                    //    borderWidth: 0,
                    //    backgroundColor: 'none',
                    //    pointFormat: '{point.y}',
                    //    headerFormat: '',
                    //    shadow: false,
                    //    style: {
                    //        fontSize: '18px'
                    //    }
                    //},
                    series: [{
                        data: dataSet.data,
                        name: dataSet.name,
                        //type: dataSet.type,
                        color: Highcharts.getOptions().colors[i],
                        fillOpacity: 0.3
                    }]
                })
            });
        });
    };
});


var buildGraph = function () {
    /**
     * In order to synchronize tooltips and crosshairs, override the
     * built-in events with handlers defined on the parent element.
     */
    $('#graph').bind('mousemove touchmove', function (e) {
        var chart,
            point,
            i;

        for (i = 0; i < Highcharts.charts.length; i = i + 1) {
            chart = Highcharts.charts[i];
            e = chart.pointer.normalize(e); // Find coordinates within the chart
            point = chart.series[0].searchPoint(e, true); // Get the hovered point

            if (point) {
                point.onMouseOver(); // Show the hover marker
                chart.tooltip.refresh(point); // Show the tooltip
                chart.xAxis[0].drawCrosshair(e, point); // Show the crosshair
            }
        }
    });
    /**
     * Override the reset function, we don't need to hide the tooltips and crosshairs.
     */
    Highcharts.Pointer.prototype.reset = function () {
        return undefined;
    };
};

/**
 * Synchronize zooming through the setExtremes event handler.
 */
var syncExtremes = function (e) {
    var thisChart = this.chart;

    Highcharts.each(Highcharts.charts, function (chart) {
        if (chart !== thisChart) {
            if (chart.xAxis[0].setExtremes) { // It is null while updating
                chart.xAxis[0].setExtremes(e.min, e.max);
            }
        }
    });
};

