app = angular.module("app", ["layout"]);

app.controller("IndexCtrl", function ($scope, $http) {
	$http.get("/models/snapshots/today").then(function (resp) {
		data = [];

		resp.data.map(function (elem) {
			data.push({
				lastModified: new Date(elem.lastModified),
				size: elem.size
			})
		});

		graph(data);
	})
});

function graph(data) {
	var margin = {top: 60, right: 60, bottom: 60, left: 70},
		outerWidth = 1000,
		outerHeight = 500;

	var innerWidth = outerWidth - margin.left - margin.right,
		innerHeight = outerHeight - margin.top - margin.bottom;

	var svg = d3.select("#graph").append("svg")
		.attr("width", outerWidth)
		.attr("height", outerHeight)
		.append("g")
		.attr("transform", "translate(" + margin.left + "," + margin.top + ")");

	var minTotal = d3.min(
		data, function (d) {
			return d.size;
		});

	var maxTotal = d3.max(
		data, function (d) {
			return d.size;
		});

	var minDate = d3.min(
		data, function (d) {
			return d.lastModified;
		});

	var maxDate = d3.max(
		data, function (d) {
			return d.lastModified;
		});

	var x = d3.time.scale()
		.domain([minDate, maxDate])
		.range([0, innerWidth]);

	var y = d3.scale.linear()
		.domain([30000, 40000])
		.range([innerHeight, 0]);

	var xAxis = d3.svg.axis()
		.scale(x)
		.orient("bottom")
		.ticks(d3.time.hour, 1)
		.innerTickSize(-innerHeight)
		.outerTickSize(0)
		.tickPadding(10)
		.tickFormat(d3.time.format("%H:%M"));

	svg.append("g")
		.attr("class", "x axis")
		.attr("transform", "translate(0," + innerHeight + ")")
		.call(xAxis);

	svg.append("text")
		.attr("x", innerWidth / 2)
		.attr("y", innerHeight + (margin.bottom / 2) + 10)
		.style("text-anchor", "middle")
		.text("Date");

	var yAxis = d3.svg.axis()
		.scale(y)
		.orient("left")
		.ticks(5)
		.innerTickSize(-innerWidth)
		.outerTickSize(0)
		.tickPadding(10);

	svg.append("g")
		.attr("class", "y axis")
		.call(yAxis);

	svg.append("text")
		.attr("transform", "rotate(-90)")
		.attr("x", -innerHeight / 2)
		.attr("y", -margin.left / 2 - 5)
		.attr("dy", "-1em")
		.style("text-anchor", "middle")
		.text("Snapshot size");

	var yGrid = d3.svg.axis()
		.scale(y)
		.orient("left")
		.ticks(20)
		.tickSize(-innerWidth, 0, 0)
		.tickFormat("");

	svg.append("g")
		.attr("class", "grid")
		.call(yGrid);

	var xGrid = d3.svg.axis()
		.scale(x)
		.orient("bottom")
		.ticks(d3.time.minute, 15)
		.tickSize(-innerHeight, 0, 0)
		.tickFormat("");

	svg.append("g")
		.attr("class", "grid")
		.attr("transform", "translate(0," + innerHeight + ")")
		.call(xGrid);

	var line = d3.svg.line()
		.x(function (d) {
			return x(d.lastModified);
		})
		.y(function (d) {
			return y(d.size);
		});

	svg.append("svg:path")
		.datum(data)
		.attr("class", "line")
		.attr("d", line);

	var points = svg.selectAll(".point")
		.data(data)
		.enter().append("svg:circle")
		.attr("class", "circle")
		.attr("cx", function (d, i) {
			return x(d.lastModified)
		})
		.attr("cy", function (d, i) {
			return y(d.size)
		})
		.attr("r", function (d, i) {
			return 4
		})
		.append("svg:title")
		.text(function (d) {
			return d.lastModified.getHours() + ":" + d.lastModified.getMinutes() + " - " + d.size;
		});
}