<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
    <jsp:attribute name="head">
            <script src="${pageContext.request.contextPath}/static/js/lib/d3.min.js"></script>
    </jsp:attribute>

    <jsp:body>
        <p>${realms}</p>
    </jsp:body>
</t:layout>
