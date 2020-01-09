<%--
  Created by IntelliJ IDEA.
  User: jingw
  Date: 1/9/20
  Time: 1:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Update</title>
</head>
<body>
<h1>edit mode</h1>
<form:form method ="POST" action = "${pageContext.request.contextPath}/resource/update/${r_edit.resourceId}" modelAttribute ="r_edit">
    <input type="hidden" name="_method" value="put">
    <label>resource code: </label><form:input path="resourceCode" placeholder="${r_edit.resourceCode}" />
    <label>resource name: </label><form:input path="resourceName" placeholder = "${r_edit.resourceName}"/>
    <input type="submit" value="submit">
</form:form>


</body>
</html>
