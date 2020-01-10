<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--
  Created by IntelliJ IDEA.
  User: jingw
  Date: 1/8/20
  Time: 11:24 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Resource Page</title>
</head>
<body>
<h1>Resource List</h1>
<table>
    <tr>
        <th>Resource ID</th>
        <th>Resource Code</th>
        <th>Resource Name</th>
    </tr>
    <c:forEach items ="${resources}" var="reso">
        <tr>
            <td>${reso.resourceId}</td>
            <td>${reso.resourceCode}</td>
            <td>${reso.resourceName}</td>
            <td><a href = "${pageContext.request.getContextPath()}/resource/update/${reso.resourceId}">edit</a></td>
<%--            <td><a href = "${pageContext.request.getContextPath()}/resource/delete/${reso.resourceId}">delete</a></td>--%>
            <td>
                <form action = "${pageContext.request.getContextPath()}/resource/delete/${reso.resourceId}" method = "post">
                    <input type="hidden" name="_method" value="delete" />
                    <button type="submit">Delete</button>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>



</body>
</html>
