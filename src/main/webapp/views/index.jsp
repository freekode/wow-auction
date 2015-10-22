<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<t:layout>
    <jsp:attribute name="head">
        <spring:url value="/static/css/graph.css" var="graphCss"/>
        <spring:url value="/static/js/lib/d3.min.js" var="d3Js"/>
        <spring:url value="/static/js/graph.js" var="graphJs"/>
        <spring:url value="/static/js/index.js" var="indexJs"/>

        <link href="${graphCss}" rel="stylesheet"/>
        <script src="${d3Js}"></script>
        <script src="${graphJs}"></script>
        <script src="${indexJs}"></script>
        <script>

        </script>
    </jsp:attribute>

    <jsp:body>
        ${snapshots}
        <div ng-controller="IndexCtrl">
            <select class="form-control" ng-model="realmId" ng-change="changeRealm()">
                <c:forEach items="${realms}" var="realm">
                    <option value="${realm.id}">${realm.name}</option>
                </c:forEach>
            </select>
            <div id="graph"></div>
        </div>
    </jsp:body>
</t:layout>
