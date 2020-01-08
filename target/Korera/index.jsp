<%--
  Created by IntelliJ IDEA.
  User: Stoic_Colony
  Date: 2019/12/28
  Time: 7:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isErrorPage="true" %>
<jsp:forward page="/project/findAll"></jsp:forward>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>Project</h1>
    <a href="project/findById/150"> find project with id 150</a>
    <br/>
    <a href="project/findByName/hello"> find project with name hello</a>
    <br/>
    <a href="project/findAll"> project find all</a>
    <br/>
    <form action="project/add" method="post">
        <label>project id: </label><input type="text" name="project_id" value=""/>
        <label>project name: </label><input type="text" name="project_name" value=""/>
        <label>user id: </label><input type="text" name="user_id" value=""/>
        <input type="submit" value="add project with id=10000"/>
    </form>
    <br/>
    <form action="project/delete/101" method="post">
        <input type="hidden" name="_method" value="delete"/>
        <input type="submit" value="delete project with id=10000"/>
    </form>
    <br/>
    <form action="project/update/101" method="post">
        <input type="hidden" name="_method" value="put"/>
        <input type="submit" value="update project with id=10000"/>
    </form>
    <br/>
</body>
</html>
