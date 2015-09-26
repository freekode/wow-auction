<%--@elvariable id="snapshots" type="java.util.List<org.freekode.wowauction.persistence.models.Snapshot>"--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Snapshot</title>
</head>
<body>
<table>
    <thead>
    <tr>
        <th>Created</th>
        <th>Last Modified</th>
        <th>Realm</th>
        <th>Closed</th>
        <th>Existing</th>
        <th>New</th>
    </tr>
    </thead>
    <tbody>
        <c:forEach items="${snapshots}" var="snapshot">
            <tr>
                <td>${snapshot.createdAt}</td>
                <td>${snapshot.lastModified}</td>
                <td>${snapshot.realm.name}</td>
                <td>${snapshot.closed}</td>
                <td>${snapshot.existing}</td>
                <td>${snapshot.newAmount}</td>
            </tr>
        </c:forEach>
    </tbody>
</table>
</body>
</html>
