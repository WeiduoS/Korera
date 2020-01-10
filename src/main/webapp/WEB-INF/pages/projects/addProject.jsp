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
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Project Add</title>
</head>
<body>
<%pageContext.setAttribute("ctp", request.getContextPath());%>

<form action="${cpt}/project/add" method="post">
    <label>project name: </label><input type="text" name="project_name">
    <label>user id: </label><input type="text" name="user_id">
    <input type="submit" value="submit">
</form>

<%--<form:form action="${cpt}/project/add" modelAttribute="command">--%>
    <%--<label>project id: </label><form:input path="project_id"/>--%>
    <%--<label>project name: </label><form:input path="project_name"/>--%>
    <%--<label>user id: </label><form:input path="user_id"/>--%>
    <%--<input type="submit" value="submit">--%>
<%--</form:form>--%>

</body>
</html>
