<%@ page import="org.freekode.wowauction.controllers.data.BidData" %>
<%@ page import="org.freekode.wowauction.controllers.data.ItemData" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%
    ItemData itemData = (ItemData) request.getAttribute("item");

    int quantity = 0;
    for (BidData bidData : itemData.getOpenBids()) {
        quantity += bidData.getQuantity();
    }

    request.setAttribute("quantity", quantity);
%>

<t:layout>
    <jsp:attribute name="head">
        <spring:url value="/static/js/lib/highcharts.js" var="highchartsJs"/>
        <spring:url value="/static/js/lib/dark-unica.js" var="darkunicaJs"/>
        <spring:url value="/static/js/item.js" var="indexJs"/>

        <script src="${highchartsJs}"></script>
        <script src="${darkunicaJs}"></script>
        <script src="${indexJs}"></script>

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
        <div ng-controller="ItemCtrl">
            <div class="row">
                <div class="col-xs-12">
                    <a href="http://www.wowhead.com/item=${item.identifier}" class="item__a" target="_blank">
                        <div class="inline">
                            <div class="icon__medium"
                                 ng-style="{'background' : 'no-repeat url(http://wow.zamimg.com/images/wow/icons/large/${item.itemInfo.icon})'}"></div>
                            <div class="icon__hover__medium"></div>
                            <div class="icon__border__medium"></div>
                        </div>
                    </a>

                    <div class="inline" ng-class="qualityClass(${item.itemInfo.quality.codeInteger})">
                            ${item.itemInfo.name}
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-xs-4">
                    <table class="table table-striped">
                        <tbody>
                        <tr>
                            <td><b>Name</b></td>
                            <td>${item.itemInfo.name}</td>
                        </tr>
                        <tr>
                            <td><b>Identifier</b></td>
                            <td>${item.identifier}</td>
                        </tr>
                        <tr>
                            <td><b>Quantity</b></td>
                            <td>${quantity}</td>
                        </tr>
                        <tr>
                            <td><b>Quantity</b></td>
                            <td>${quantity}</td>
                        </tr>
                        <tr>
                            <td><b>Sell vendor</b></td>
                            <td>${item.itemInfo.sellPrice}</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="row">
                <div class="col-xs-6">
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th>rate</th>
                            <th>buyout</th>
                            <th>quantity</th>
                            <th>time left</th>
                            <th>created</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${item.openBids}" var="bid">
                            <tr>
                                <td>${bid.rate}</td>
                                <td>${bid.buyout}</td>
                                <td>${bid.quantity}</td>
                                <td>${bid.timeLeft}</td>
                                <td>${bid.createdAt}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </jsp:body>
</t:layout>
