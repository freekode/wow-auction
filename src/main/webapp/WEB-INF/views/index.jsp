<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<t:layout>
    <jsp:attribute name="head">
        <spring:url value="/static/css/graph.css" var="graphCss"/>
        <spring:url value="/static/js/lib/d3.min.js" var="d3Js"/>
        <spring:url value="/static/js/graph.js" var="graphJs"/>
        <spring:url value="/static/js/index.js" var="indexJs"/>

        <link href="${graphCss}" rel="stylesheet"/>
        <script src="${d3Js}"></script>
        <script src="${indexJs}"></script>
    </jsp:attribute>

    <jsp:body>
        <div ng-controller="IndexCtrl">
            <div id="graph"></div>
        </div>
    </jsp:body>
</t:layout>
