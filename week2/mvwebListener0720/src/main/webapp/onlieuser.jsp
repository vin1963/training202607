<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>線上人數</title>
</head>
<body>
    <h2>目前線上人數：${applicationScope.onlineCount}</h2>
    <a href="stopSession.jsp">Stop Session</a>
</body>
</html>