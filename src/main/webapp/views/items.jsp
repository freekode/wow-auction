<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<t:layout>
    <jsp:attribute name="head">
        <spring:url value="/static/js/items.js" var="itemsJs"/>
        <script src="${itemsJs}"></script>

        <base href="${pageContext.request.contextPath}/items/">


        <script type="text/javascript" src="http://static.wowhead.com/widgets/power.js"/>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/async/1.4.2/async.min.js"/>
        <script>
            var wowhead_tooltips = {
                colorlinks: true,
                iconizelinks: true,
                renamelinks: true
            }
        </script>

        <style>
            a {
                text-decoration: none;
            }

            .inline {
                display: inline-block;
                vertical-align: middle;
            }

            .icon__large {
                width: 56px;
                height: 56px;
            }

            .icon__border__large {
                width: 68px;
                height: 68px;
                margin-top: -65px;
                margin-left: -6px;
                background: no-repeat url('${pageContext.request.contextPath}/static/img/item_large_border.png')
            }

            .icon__hover__large {
                width: 62px;
                height: 62px;
                margin-top: -59px;
                margin-left: -3px;
            }

            .icon__hover__large:hover {
                background: no-repeat url('${pageContext.request.contextPath}/static/img/item_large_hover.png')
            }

            .icon__medium {
                width: 36px;
                height: 36px;
            }

            .icon__border__medium {
                width: 44px;
                height: 44px;
                margin-top: -41px;
                margin-left: -4px;
                background: no-repeat url('${pageContext.request.contextPath}/static/img/item_medium_border.png')
            }

            .icon__hover__medium {
                width: 38px;
                height: 38px;
                margin-top: -37px;
                margin-left: -1px;
            }

            .icon__hover__medium:hover {
                background: no-repeat url('${pageContext.request.contextPath}/static/img/item_medium_hover.png')
            }

            .item__poor {
                color: #9d9d9d !important;
            }

            .item__common {
                color: #fff !important;
            }

            .item__uncommon {
                color: #1eff00 !important;
            }

            .item__rare {
                color: #0070dd !important;
            }

            .item__epic {
                color: #a335ee !important;
            }

            .item__legendary {
                color: #ff8000 !important;
            }

            .item__artifact,
            .item__heirloom {
                color: #e5cc80 !important
            }

            .item__wow_token {
                color: #0cf !important;
            }
        </style>
    </jsp:attribute>

    <jsp:body>
        <div ng-controller="ItemsCtrl">
            <div ng-view></div>

            <div class="row">
                <div class="col-xs-1">
                    <input type="text" class="form-control" style="width:60px;" placeholder="Amount"
                           ng-model="itemListSearch.amount">
                </div>
                <div class="col-xs-3">
                    <div class="form-inline">
                        <button type="button" class="btn btn-info" ng-click="prevPage()">&laquo;</button>
                        <input type="text" class="form-control" style="width:60px;text-align:center;" placeholder="Page"
                               ng-model="itemListSearch.page"/>
                        <button type="button" class="btn btn-info" ng-click="nextPage()">&raquo;</button>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-xs-12">
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th>id</th>
                            <th>name</th>
                            <th>itemLevel</th>
                            <th>quality</th>
                            <th>class</th>
                            <th>sub class</th>
                            <th>inventory slot</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr ng-repeat="item in items">
                            <td>{{ item.identifier }}</td>
                            <td>
                                <a href="http://www.wowhead.com/item={{ item.identifier }}" class="item__a" target="_blank">
                                    <div class="inline">
                                        <div class="icon__medium"
                                             ng-style="{'background' : 'no-repeat url(http://wow.zamimg.com/images/wow/icons/medium/{{item.itemInfo.icon}})'}"></div>
                                        <div class="icon__hover__medium"></div>
                                        <div class="icon__border__medium"></div>
                                    </div>
                                </a>

                                <a href="${pageContext.request.contextPath}/item/{{ item.identifier }}"
                                   class="inline" ng-class="qualityClass(item.itemInfo)">
                                    {{ item.itemInfo.name }}
                                </a>
                            </td>
                            <td>{{ item.itemInfo.itemLevel }}</td>
                            <td ng-class="qualityClass(item.itemInfo)">
                                {{ item.itemInfo.quality.value }}
                            </td>
                            <td>{{ item.itemInfo.itemClass.value }}</td>
                            <td>{{ item.itemInfo.itemSubclass.value }}</td>
                            <td>{{ item.itemInfo.inventorySlot.value }}</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </jsp:body>
</t:layout>
