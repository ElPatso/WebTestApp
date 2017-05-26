<%--
  Created by IntelliJ IDEA.
  User: Ostap
  Date: 23.05.2017
  Time: 17:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:if test="${!empty userslist}">
    <table class="tg">
        <tr>
            <th width="80">ID</th>
            <th width="120">Name</th>
            <th width="120">Email</th>
        </tr>
        <c:forEach items="${userslist}" var="user">
            <tr>
                <td>${user.id}</td>

                <td>${user.username}</td>
                <td>${user.email}</td>
                <td><a href="<c:url value='/remove/${user.username}'/>">Delete</a></td>
                <td></td>

            </tr>
        </c:forEach>
    </table>
</c:if>

</body>
</html>
