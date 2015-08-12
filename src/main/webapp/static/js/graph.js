var margin = {top: 60, right: 60, bottom: 60, left: 60},
	outerWidth = 750,
	outerHeight = 350;

var innerWidth = outerWidth - margin.left - margin.right,
	innerHeight = outerHeight - margin.top - margin.bottom;

// example data
var data = [
	{lastModified:"2012-03-20", size:"43"},
	{lastModified:"2012-03-21", size:"89"},
	{lastModified:"2012-03-22", size:"82"},
	{lastModified:"2012-03-23", size:"70"},
	{lastModified:"2012-03-24", size:"36"},
	{lastModified:"2012-03-25", size:"20"},
	{lastModified:"2012-03-26", size:"42"}
];

var minTotal = d3.min(
	data, function(d) {
		return d.size;
	});

var maxTotal = d3.max(
	data, function(d) {
		return d.size;
	});

var minDate = d3.min(
	data, function(d) {
		return d.lastModified;
	});

var maxDate = d3.max(
	data, function(d) {
		return d.lastModified;
	});

var x = d3.time.scale()
	.domain([
		new Date(minDate),
		new Date(maxDate)
	])
	.range([0, innerWidth]);

var y = d3.scale.linear()
	.domain([0, maxTotal])
	.range([innerHeight, 0]);

var xAxis = d3.svg.axis()
	.scale(x)
	.orient("bottom")
	.ticks(d3.time.days)
	.innerTickSize(-innerHeight)
	.outerTickSize(0)
	.tickPadding(10)
	.tickFormat(d3.time.format("%d %b"));

var yAxis = d3.svg.axis()
	.scale(y)
	.orient("left")
	.tickValues([0, maxTotal / 4, maxTotal / 2, maxTotal])
	.innerTickSize(-innerWidth)
	.outerTickSize(0)
	.tickPadding(10);

var svg = d3.select("#graph").append("svg")
	.attr("width", outerWidth)
	.attr("height", outerHeight)
	.append("g")
	.attr("transform", "translate(" + margin.left + "," + margin.top + ")");

svg.append("g")
	.attr("class", "x axis")
	.attr("transform", "translate(0," + innerHeight + ")")
	.call(xAxis);

svg.append("g")
	.attr("class", "y axis")
	.call(yAxis);

var yGrid = d3.svg.axis()
	.scale(y)
	.orient("left")
	.ticks(20)
	.tickSize(-innerWidth, 0, 0)
	.tickFormat("");

svg.append("g")
	.attr("class", "grid")
	.call(yGrid);

// uncomment to put y-lines on the grid
//
// var xGrid = d3.svg.axis()
//     .scale(x)
//     .orient("bottom")
//     .ticks(20)
//     .tickSize(-innerHeight, 0, 0)
//     .tickFormat("");
//
// svg.append("g")
//     .attr("class", "grid")
//     .attr("transform", "translate(0," + innerHeight + ")")
//     .call(xGrid);

var line = d3.svg.line()
	.x(function(d) {
		return x(new Date(d.lastModified));
	})
	.y(function(d) {
		return y(d.size);
	})
	.interpolate("basis");

svg.append("svg:path")
	.datum(data)
	.attr("class", "line")
	.attr("d", line);