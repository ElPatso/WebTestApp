<%--
  Created by IntelliJ IDEA.
  User: Ostap
  Date: 18.05.2017
  Time: 20:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title></title>
</head>
<body>
    <h1>Your information</h1>

<form:form modelAttribute="UserInformation" method="post">
<form:input path="name"/>
    <form:input path="surname"/>
    <form:input path="city"/>
    <form:input path="age"/>
    <button type="submit" value="Bla"/>
</form:form>
</body>
</html>
