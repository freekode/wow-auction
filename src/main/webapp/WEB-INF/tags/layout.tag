<%@tag description="Overall Page template" pageEncoding="UTF-8" %>
<%@attribute name="head" fragment="true" %>
<%@attribute name="header" fragment="true" %>
<%@attribute name="footer" fragment="true" %>
<!DOCTYPE html>
<html ng-app="app">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <%--<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/lib/bootstrap.min.css">--%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/lib/bootstrap-slate.min.css">

    <script src="${pageContext.request.contextPath}/static/js/lib/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/lib/bootstrap.min.js"></script>

    <script src="${pageContext.request.contextPath}/static/js/api.js"></script>

    <script src="${pageContext.request.contextPath}/static/js/lib/angular.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/lib/angular-route.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/layout.js"></script>


    <jsp:invoke fragment="head"/>

</head>
<body ng-controller="MainCtrl">


<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="${pageContext.request.contextPath}/">WoW Auction</a>
        </div>

        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li ng-class="{active : page == 'items'}"><a href="${pageContext.request.contextPath}/items/">Items</a></li>
                <%--<li ng-class="{active : page == 'auctions'}"><a href="#">Auctions</a></li>--%>
            </ul>

            <%--<ul class="nav navbar-nav navbar-right">--%>
                <%--<li><a href="#">Link</a></li>--%>
                <%--<li class="dropdown">--%>
                    <%--<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Dropdown <span class="caret"></span></a>--%>
                    <%--<ul class="dropdown-menu">--%>
                        <%--<li><a href="#">Action</a></li>--%>
                        <%--<li><a href="#">Another action</a></li>--%>
                        <%--<li><a href="#">Something else here</a></li>--%>
                        <%--<li role="separator" class="divider"></li>--%>
                        <%--<li><a href="#">Separated link</a></li>--%>
                    <%--</ul>--%>
                <%--</li>--%>
            <%--</ul>--%>
        </div>
    </div>
</nav>


<div class="container-fluid">
    <jsp:doBody/>
</div>


</body>
</html>
