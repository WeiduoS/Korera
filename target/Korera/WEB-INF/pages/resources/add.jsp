
<%--
  Created by IntelliJ IDEA.
  User: jingw
  Date: 1/8/20
  Time: 2:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Add Resource Form</title>
</head>
<body>
<h1>Add Resource</h1>
<form:form method ="POST" action = "${pageContext.request.contextPath}/resource/add" modelAttribute ="r_add">
    <label>resource code: </label><form:input path="resourceCode"/>
    <label>resource name: </label><form:input path="resourceName"/>
    <input type="submit" value="submit">
</form:form>


</body>
</html>
