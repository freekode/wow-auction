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
        <spring:url value="/static/js/items.js" var="itemsJs"/>

        <link href="${graphCss}" rel="stylesheet"/>
        <script src="${d3Js}"></script>
        <script src="${graphJs}"></script>
        <script src="${itemsJs}"></script>

        <script type="text/javascript" src="http://static.wowhead.com/widgets/power.js"/>
        <script>
            var wowhead_tooltips = {
                colorlinks: true,
                iconizelinks: true,
                renamelinks: true
            }
        </script>
    </jsp:attribute>

    <jsp:body>
        <div ng-controller="ItemsCtrl">
            <div class="row">
                <div class="col-xs-1" ng-repeat="item in items">
                    <a href="http://www.wowhead.com/item={{ item.identifier }}" class="q4 thumbnail">
                        hm...
                    </a>
                </div>
            </div>

            <div class="row">
                <nav>
                    <ul class="pagination">
                        <li>
                            <a href="#" aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span>
                            </a>
                        </li>
                        <li><a href="#">0</a></li>
                        <li><a href="#">1</a></li>
                        <li><a href="#">2</a></li>
                        <li><a href="#">3</a></li>
                        <li><a href="#">4</a></li>
                        <li>
                            <a href="#" aria-label="Next">
                                <span aria-hidden="true">&raquo;</span>
                            </a>
                        </li>
                    </ul>
                </nav>
            </div>
        </div>
    </jsp:body>
</t:layout>
