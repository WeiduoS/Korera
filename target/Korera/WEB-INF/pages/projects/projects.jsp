<%--
  Created by IntelliJ IDEA.
  User: Stoic_Colony
  Date: 2020/1/7
  Time: 2:28 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    pageContext.setAttribute("cpt", request.getContextPath());
%>
<html>
<head>
    <title>Project page</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
</head>
<body>
<h1>Project list</h1>
    <table>
        <tr>
            <th>Project ID</th>
            <th>Project Name</th>
            <th>User ID</th>
            <th>edit</th>
            <th>delete</th>
        </tr>
        <c:forEach items="${projects}" var="project">
            <tr>
                <td>${project.project_id}</td>
                <td>${project.project_name}</td>
                <td>${project.user_id}</td>
                <td><a href="${cpt}/project/findById/${project.project_id}">edit</a></td>
                <td>
                    <form action="${cpt}/project/delete/${project.project_id}" method="post">
                        <input type="hidden" name="_method" value="delete"/>
                        <input type="submit" value="delete"/>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
<a href="/project/add">add project</a>
<script>
    $(function () {
        alert("test");
    });
</script>
<%--pageContext: ${pageScope.projects}--%>
<%--requestScope: ${requestScope.projects}--%>
<%--sessionScope: ${sessionScope.projects}--%>
<%--applicationScope: ${applicationScope.projects}--%>
</body>
</html>
