app = angular.module("app", ["layout"]);

app.controller("IndexCtrl", function ($scope, $http) {
    $http.get("/models/snapshots/24h").then(function (resp) {
        var data = [];

        resp.data.map(function (elem) {
            data.push({
                date: new Date(elem.lastModified),
                closed: elem.closed,
                existing: elem.existing
                //newAmount: elem.newAmount
                //overall: elem.closed + elem.existing + elem.newAmount
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
    var yScale0 = d3.scale.linear().range([innerHeight, 0]);
    var yScale1 = d3.scale.linear().range([innerHeight, 0]);


    var xAxis = d3.svg.axis().scale(xScale)
        .orient("bottom")
        .ticks(d3.time.hour, 30)
        .innerTickSize(-innerHeight)
        .outerTickSize(0)
        .tickPadding(10)
        .tickFormat(d3.time.format("%H:%M"));

    var yAxisLeft = d3.svg.axis().scale(yScale0)
        .orient("left")
        .ticks(10)
        .tickFormat(d3.format("s"))
        .innerTickSize(-innerWidth)
        .outerTickSize(0)
        .tickPadding(10);

    var yAxisRight = d3.svg.axis().scale(yScale1)
        .orient("right")
        .ticks(10)
        .tickFormat(d3.format("s"))
        .tickPadding(10)
        .innerTickSize(-innerWidth)
        .outerTickSize(0);


    var line0 = d3.svg.line()
        .x(function (d) {
            return xScale(d.date);
        })
        .y(function (d) {
            return yScale0(d.value);
        });

    var line1 = d3.svg.line()
        .x(function (d) {
            return xScale(d.date);
        })
        .y(function (d) {
            return yScale1(d.newAmount);
        });


    var svg = d3.select("#graph").append("svg")
        .attr("width", outerWidth)
        .attr("height", outerHeight)
        .append("g")
        .attr("transform", "translate(" + margin.left + "," + margin.top + ")");


    color.domain(d3.keys(data[0]).filter(function (key) {
        return key !== "date";
    }));

    var lineValues = color.domain().map(function (name) {
        return {
            name: name,
            values: data.map(function (d) {
                return {date: d.date, value: +d[name]};
            })
        };
    });


    xScale.domain(d3.extent(data, function (d) {
        return d.date;
    }));
    yScale0.domain([0, d3.max(lineValues, function (d) {
        return d3.max(d.values, function (v) {
            return v.value;
        });
    })]);
    yScale1.domain([0, d3.max(lineValues, function (d) {
        return d3.max(d.values, function (v) {
            return v.value;
        });
    })]);


    var domain = svg.selectAll(".line")
        .data(lineValues)
        .enter().append("g")
        .attr("class", "line");

    // lines
    domain.append("path")
        .attr("d", function (d) {
            return line0(d.values);
        })
        .attr("stroke", function (d) {
            return color(d.name);
        });


    // axis
    svg.append("g")
        .attr("class", "x axis")
        .attr("transform", "translate(0," + innerHeight + ")")
        .call(xAxis);

    svg.append("g")
        .attr("class", "y axis")
        .style("fill", "steelblue")
        .call(yAxisLeft);

    svg.append("g")
        .attr("class", "y axis")
        .attr("transform", "translate(" + innerWidth + " ,0)")
        .style("fill", "red")
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
}


function graphZz(data) {
    var margin = {top: 60, right: 120, bottom: 60, left: 70},
        outerWidth = 1300,
        outerHeight = 700;

    var innerWidth = outerWidth - margin.left - margin.right,
        innerHeight = outerHeight - margin.top - margin.bottom;

    var svg = d3.select("#graph").append("svg")
        .attr("width", outerWidth)
        .attr("height", outerHeight)
        .append("g")
        .attr("transform", "translate(" + margin.left + "," + margin.top + ")");


    var color = d3.scale.category20b();

    // min max values
    var minTotal = d3.min(dataFirst, function (d) {
        return d.value;
    });

    var maxTotal = d3.max(
        dataFirst, function (d) {
            return d.value;
        });

    var minDate = d3.min(
        dataFirst, function (d) {
            return d.date;
        });

    var maxDate = d3.max(
        dataFirst, function (d) {
            return d.date;
        });

    // axis scaling
    var xScale = d3.time.scale()
        .domain([minDate, maxDate])
        .range([0, innerWidth]);

    var yScale = d3.scale.linear()
        .domain([0, 15000])
        .range([innerHeight, 0]);

    var yArea = d3.scale.linear()
        .domain([100, 60000])
        .range([innerHeight, 0]);

    // axis
    var xAxis = d3.svg.axis()
        .scale(xScale)
        .orient("bottom")
        .ticks(d3.time.hour, 30)
        .innerTickSize(-innerHeight)
        .outerTickSize(0)
        .tickPadding(10)
        .tickFormat(d3.time.format("%H:%M"));

    // axis
    var yAxis = d3.svg.axis()
        .scale(yScale)
        .orient("left")
        .ticks(10)
        .tickFormat(d3.format("s"))
        .innerTickSize(-innerWidth)
        .outerTickSize(0)
        .tickPadding(10);

    // grid
    var yGrid = d3.svg.axis()
        .scale(yScale)
        .orient("left")
        .ticks(15)
        .tickSize(-innerWidth, 0, 0)
        .tickFormat("");

    var xGrid = d3.svg.axis()
        .scale(xScale)
        .orient("bottom")
        .ticks(d3.time.minute, 15)
        .tickSize(-innerHeight, 0, 0)
        .tickFormat("");

    // rendering line and area
    var line = d3.svg.line()
        .x(function (d) {
            return xScale(d.date);
        })
        .y(function (d) {
            return yScale(d.value);
        });

    var area = d3.svg.area()
        .x(function (d) {
            return xScale(d.date);
        })
        .y0(innerHeight)
        .y1(function (d) {
            return yScale(d.value);
        });

    color.domain(d3.keys(dataFirst[0]).filter(function (key) {
        return key !== "date";
    }));

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


    // axis
    svg.append("g")
        .attr("class", "x axis")
        .attr("transform", "translate(0," + innerHeight + ")")
        .call(xAxis);

    svg.append("g")
        .attr("class", "y axis")
        .call(yAxis);


    // grid
    svg.append("g")
        .attr("class", "grid")
        .call(yGrid);

    svg.append("g")
        .attr("class", "grid")
        .attr("transform", "translate(0," + innerHeight + ")")
        .call(xGrid);


    // lines
    //svg.append("svg:path")
    //    .datum(dataFourthArea)
    //    .attr("class", "area-overall")
    //    .attr("d", area);
    //
    //svg.append("svg:path")
    //    .datum(dataSecond)
    //    .attr("class", "line-existing")
    //    .attr("d", line);

    svg.append("svg:path")
        .datum(dataFirst)
        //.attr("class", "line-closed")
        .attr("d", line);

    svg.append("svg:path")
        .datum(dataThird)
        //.attr("class", "line-newAmount")
        .attr("d", line)
        .attr('fill', function (d, i) {
            return color(d.data.label);
        });


    var points = svg.selectAll(".point")
        .data(dataFirst)
        .enter().append("svg:circle")
        .attr("class", "circle")
        .attr("cx", function (d, i) {
            return xScale(d.date)
        })
        .attr("cy", function (d, i) {
            return yScale(d.value)
        })
        .attr("r", 2)
        .append("svg:title")
        .text(function (d) {
            return d.date.getHours() + ":" + d.date.getMinutes() + " - " + d.value;
        });


    var legendRectSize = 18;
    var legendSpacing = 4;

    var legend = svg.selectAll('.legend')
        .data(dataFourthArea)
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
        .style('stroke', color);

    legend.append('text')
        .attr('x', legendRectSize + legendSpacing)
        .attr('y', legendRectSize - legendSpacing)
        .text(function (d) {
            return d;
        });
}
