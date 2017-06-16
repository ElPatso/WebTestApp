<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Welcome</title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">

    <style>
        .layer1 {
            background-color: #fc0; /* Цвет фона слоя */
            padding: 5px; /* Поля вокруг текста */
            float: left; /* Обтекание по правому краю */
            width: auto; /* Ширина слоя */
        }
        .layer2 {
            background-color: #c0c0c0; /* Цвет фона слоя */
            padding: 5px; /* Поля вокруг текста */
            width: auto; /* Ширина слоя */
            float: left; /* Обтекание по правому краю */
        }
        .clear {
            clear: left; /* Отмена обтекания */
        }
    </style>
</head>
<body>

<div class="layer1">
    <h1>Welcome</h1>
    <br>
    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <form id="logoutForm" method="POST" action="${contextPath}/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>

       
        <h2>Welcome ${pageContext.request.userPrincipal.name} </h2> <br>  <a onclick="document.forms['logoutForm'].submit()">Logout</a>
        <security:authorize access="hasRole('ROLE_ADMIN')">
            <a href="${contextPath}/admin">Admin</a>
        </security:authorize>
        <h1>${user.password}</h1>
        <p><a href="${contextPath}/addInformation">Add information</a> </p>
        <br>
    </c:if>



</div>
<div class="layer2">
    <c:if test="${empty user}">
        <a href="${contextPath}/login">Login</a>
        <br>
        <a href="${contextPath}/registration">Registration</a>
    </c:if>
</div>

</body>
</html>