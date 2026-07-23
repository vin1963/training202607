<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登入</title>
    <style>
        body { font-family: Arial; max-width: 400px; margin: 100px auto; }
        .error { color: red; margin-bottom: 10px; }
        .form-group { margin-bottom: 15px; }
        label { display: block; margin-bottom: 5px; }
        input { width: 100%; padding: 8px; box-sizing: border-box; }
        button { background: #007bff; color: white; padding: 10px 20px; border: none; cursor: pointer; }
    </style>
</head>
<body>
    <h2>會員登入</h2>
    
    <% if (request.getAttribute("error") != null) { %>
        <div class="error">${error}</div>
    <% } %>
    
    <form action="${pageContext.request.contextPath}/login" method="post">
        <div class="form-group">
            <label>帳號：</label>
            <input type="text" name="username" required>
        </div>
        <div class="form-group">
            <label>密碼：</label>
            <input type="password" name="password" required>
        </div>
        <button type="submit">登入</button>
    </form>
    
    <p>測試帳號：admin / tom / jerry<br>密碼：1234</p>
</body>
</html>