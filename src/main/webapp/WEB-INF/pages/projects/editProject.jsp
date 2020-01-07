<%--
  Created by IntelliJ IDEA.
  User: Stoic_Colony
  Date: 2020/1/7
  Time: 2:57 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Project Add</title>
</head>
<body>
<%pageContext.setAttribute("ctp", request.getContextPath());%>


<form:form action="${cpt}/project/update/${project.project_id}" modelAttribute="project" method="post">
    <input type="hidden" name="_method" value="put">
    <input type="hidden" name="project_id" value="${project.project_id}">
    <label>project name: </label><form:input path="project_name"/>
    <label>user id: </label><form:input path="user_id"/>
    <input type="submit" value="submit">
</form:form>

</body>
</html>
