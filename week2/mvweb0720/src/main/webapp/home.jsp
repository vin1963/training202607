<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>首頁</title>
    <style>
        body { font-family: Arial; max-width: 600px; margin: 50px auto; }
        .info { background: #f5f5f5; padding: 20px; border-radius: 5px; }
        .logout { background: #dc3545; color: white; padding: 8px 15px; text-decoration: none; }
    </style>
</head>
<body>
    <h2>歡迎，${user.name}！</h2>
    
    <div class="info">
        <p><strong>帳號：</strong>${user.username}</p>
        <p><strong>姓名：</strong>${user.name}</p>
        <p><strong>角色：</strong>${user.role}</p>
        <p><strong>登入時間：</strong>${sessionScope.loginTime}</p>
        <p><strong>Session ID：</strong>${pageContext.session.id}</p>
        <p><strong>Session ID：</strong>${sessionScope.id}</p>
        
    </div>
    
    <br>
    <a href="${pageContext.request.contextPath}/logout" class="logout">登出</a>
</body>
</html>