<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Show Names</title>
</head>
<body>
        <c:forEach var="s" items="${applicationScope.names}">
            <ul>
                <li>${s}</li>
            </ul>
        </c:forEach>
</body>
</html>