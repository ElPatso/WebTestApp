<%--
  Created by IntelliJ IDEA.
  User: Ostap
  Date: 27.05.2017
  Time: 2:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Title</title>

</head>
<body>

<c:url var="firstUrl" value="${contextPath}/list/" />
<c:url var="lastUrl" value="${contextPath}/list/${deploymentLog.totalPages}" />
<c:url var="prevUrl" value="${contextPath}/list/${currentIndex - 1}" />
<c:url var="nextUrl" value="${contextPath}/list/${currentIndex + 1}" />
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
<div class="pagination">
    <ul>
        <c:choose>
            <c:when test="${currentIndex == 1}">
                <li class="disabled"><a href="#">&lt;&lt;</a></li>
                <li class="disabled"><a href="#">&lt;</a></li>
            </c:when>
            <c:otherwise>
                <li><a href="${firstUrl}">&lt;&lt;</a></li>
                <li><a href="${prevUrl}">&lt;</a></li>
            </c:otherwise>
        </c:choose>
        <c:forEach var="i" begin="${beginIndex}" end="${endIndex}">
            <c:url var="pageUrl" value="${contextPath}/list/${i}" />
            <c:choose>
                <c:when test="${i == currentIndex}">
                    <li class="active"><a href="${pageUrl}"><c:out value="${i}" /></a></li>
                </c:when>
                <c:otherwise>
                    <li><a href="${pageUrl}"><c:out value="${i}" /></a></li>
                </c:otherwise>
            </c:choose>
        </c:forEach>
        <c:choose>
            <c:when test="${currentIndex == deploymentLog.totalPages}">
                <li class="disabled"><a href="#">&gt;</a></li>
                <li class="disabled"><a href="#">&gt;&gt;</a></li>
            </c:when>
            <c:otherwise>
                <li><a href="${nextUrl}">&gt;</a></li>
                <li><a href="${lastUrl}">&gt;&gt;</a></li>
            </c:otherwise>
        </c:choose>
    </ul>
</div>
</body>
</html>
