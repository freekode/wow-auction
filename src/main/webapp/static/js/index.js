app = angular.module("app", ["layout"]);

app.controller("IndexCtrl", function ($scope, $http) {
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

        //var gr = new IndexGraph({
        //    id: '#graph',
        //    yTitle: 'Snapshot size',
        //    xTitle: 'Data'
        //});
        //gr.addXAxis(dataClosed, 'date');
        //gr.addYAxis(dataClosed, 'value');
        //gr.addGrid();
        //gr.addArea(dataOverall, 'date', 'value');
        //gr.addLine(dataClosed, 'date', 'value');
        //gr.addPoints(dataClosed, 'date', 'value');
        graph(data);
    })
});


function graph(data) {
    var margin = {top: 60, right: 120, bottom: 60, left: 70},
        outerWidth = 1300,
        outerHeight = 700;

    var innerWidth = outerWidth - margin.left - margin.right,
        innerHeight = outerHeight - margin.top - margin.bottom;


    var color = d3.scale.category10();


    var xScale = d3.time.scale().range([0, innerWidth]);
    var yScaleLeft = d3.scale.linear().range([innerHeight, 0]);
    var yScaleRight = d3.scale.linear().range([innerHeight, 0]);


    var xAxis = d3.svg.axis().scale(xScale)
        .orient("bottom")
        .ticks(d3.time.hours, 1)
        .innerTickSize(-innerHeight)
        .outerTickSize(0)
        .tickPadding(10)
        .tickFormat(d3.time.format("%H:%M"));

    var yAxisLeft = d3.svg.axis().scale(yScaleLeft)
        .orient("left")
        .ticks(10)
        .tickFormat(d3.format("s"))
        .innerTickSize(10)
        .outerTickSize(0)
        .tickPadding(10);

    var yAxisRight = d3.svg.axis().scale(yScaleRight)
        .orient("right")
        .ticks(10)
        .tickFormat(d3.format("s"))
        .tickPadding(10)
        .innerTickSize(10)
        .outerTickSize(0);


    // left
    var lineClosed = d3.svg.line()
        .x(function (d) {
            return xScale(d.date);
        })
        .y(function (d) {
            return yScaleLeft(d.closed);
        });

    var lineNewAmount = d3.svg.line()
        .x(function (d) {
            return xScale(d.date);
        })
        .y(function (d) {
            return yScaleLeft(d.newAmount);
        });

    // right
    var lineExisting = d3.svg.line()
        .x(function (d) {
            return xScale(d.date);
        })
        .y(function (d) {
            return yScaleRight(d.existing);
        });

    var lineOverall = d3.svg.line()
        .x(function (d) {
            return xScale(d.date);
        })
        .y(function (d) {
            return yScaleRight(d.overall);
        });


    var svg = d3.select("#graph").append("svg")
        .attr("width", outerWidth)
        .attr("height", outerHeight)
        .attr("style", "border: 1px solid;")
        .append("g")
        .attr("transform", "translate(" + margin.left + "," + margin.top + ")");


    color.domain(d3.keys(data[0]).filter(function (key) {
        return key !== "date";
    }));

    xScale.domain(d3.extent(data, function (d) {
        return d.date;
    }));

    // small
    yScaleLeft.domain([d3.min(data, function (d) {
        return d.closed < d.newAmount ? d.closed : d.newAmount
    }), d3.max(data, function (d) {
        return d.closed > d.newAmount ? d.closed : d.newAmount
    })]);
    // big
    yScaleRight.domain([d3.min(data, function (d) {
        return d.existing < d.overall ? d.existing : d.overall
    }), d3.max(data, function (d) {
        return d.existing > d.overall ? d.existing : d.overall
    })]);


    // lines
    svg.append("path")
        .attr("d", lineClosed(data))
        .attr("class", "line")
        .attr("stroke", color('closed'));

    svg.append("path")
        .attr("d", lineNewAmount(data))
        .attr("class", "line")
        .attr("stroke", color('newAmount'));

    svg.append("path")
        .attr("d", lineExisting(data))
        .attr("class", "line")
        .attr("stroke", color('existing'));

    svg.append("path")
        .attr("d", lineOverall(data))
        .attr("class", "line")
        .attr("stroke", color('overall'));


    // axis
    svg.append("g")
        .attr("class", "x axis")
        .attr("transform", "translate(0," + innerHeight + ")")
        .call(xAxis);

    svg.append("g")
        .attr("class", "y axis")
        .style("fill", color('closed'))
        .call(yAxisLeft);

    svg.append("g")
        .attr("class", "y axis")
        .attr("transform", "translate(" + innerWidth + " ,0)")
        .style("fill", color('existing'))
        .call(yAxisRight);


    // text
    svg.append("text")
        .attr("x", innerWidth / 2)
        .attr("y", innerHeight + (margin.bottom / 2) + 10)
        .style("text-anchor", "middle")
        .text("Date");

    svg.append("text")
        .attr("transform", "rotate(-90)")
        .attr("x", -innerHeight / 2)
        .attr("y", -margin.left / 2 - 5)
        .attr("dy", "-1em")
        .style("text-anchor", "middle")
        .text("Snapshot size");


    var legendRectSize = 18;
    var legendSpacing = 4;


    var legend = svg.selectAll('.legend')
        .append('div')
        .data(color.domain())
        .enter()
        .append('g')
        .attr('class', 'legend')
        .attr('transform', function (d, i) {
            var height = legendRectSize + legendSpacing;
            var offset = height * color.domain().length / 2;
            var horz = -2 * legendRectSize;
            var vert = i * height - offset;
            return 'translate(' + horz + ',' + vert + ')';
        });

    legend.append('rect')
        .attr('width', legendRectSize)
        .attr('height', legendRectSize)
        .style('fill', color)
        .style('stroke', color)

    legend.append('text')
        .attr('x', legendRectSize + legendSpacing)
        .attr('y', legendRectSize - legendSpacing)
        .text(function (d) {
            return d;
        });
}
