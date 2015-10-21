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

        <base href="${pageContext.request.contextPath}/items/">

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
        <style>
            .inline {
                display: inline-block;
                vertical-align: middle;
            }

            .icon {
                width: 56px;
                height: 56px;
            }

            .icon__frame {
                width: 68px;
                height: 68px;
                margin-top: -65px;
                margin-left: -6px;
            }

            .icon__hover {
                width: 62px;
                height: 62px;
                margin-top: -59px;
                margin-left: -3px;
            }

            .icon__frame__large {
                background: no-repeat url('${pageContext.request.contextPath}/static/img/item_large_border.png')
            }

            .icon__hover__large {
                background: no-repeat url('${pageContext.request.contextPath}/static/img/item_large_hover.png')
            }
        </style>
    </jsp:attribute>

    <jsp:body>
        <div ng-controller="ItemsCtrl">
            <div ng-view></div>

            <div class="row">
                <div class="col-xs-12">
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th>id</th>
                            <th>name</th>
                            <th>level</th>
                            <th>class</th>
                            <th>sub class</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr ng-repeat="item in items">
                            <td>{{ item.identifier }}</td>
                            <td>
                                <a href="{{ item.itemInfo.url }}" class="q4" target="_blank">
                                    <div class="inline">
                                        <div class="icon"
                                             ng-style="{'background' : 'no-repeat url({{item.itemInfo.icon}})'}"></div>
                                        <div class="icon__hover icon__hover__large"></div>
                                        <div class="icon__frame icon__frame__large"></div>
                                    </div>
                                    <div class="inline">{{ item.itemInfo.name }}</div>
                                </a>
                            </td>
                            <td>{{ item.itemInfo.level }}</td>
                            <td>null</td>
                            <td>null</td>
                        </tr>
                        </tbody>
                    </table>
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
