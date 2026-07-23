<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>網站統計</title>
    <style>
        body { font-family: Arial, sans-serif; text-align: center; margin-top: 50px; }
        .stat-box { 
            display: inline-block; 
            padding: 20px 40px; 
            margin: 10px;
            background: #f0f0f0; 
            border-radius: 10px;
        }
        .number { font-size: 36px; color: #007bff; font-weight: bold; }
        .label { font-size: 14px; color: #666; }
    </style>
</head>
<body>
    <h1>網站訪客統計</h1>
    
    <div class="stat-box">
        <div class="number">${applicationScope.onlineUsers}</div>
        <div class="label">目前線上人數</div>
    </div>
    
    <div class="stat-box">
        <div class="number">${applicationScope.totalVisitors}</div>
        <div class="label">總訪客數</div>
    </div>
    
    <div class="stat-box">
        <div class="number">${pageContext.session.id}</div>
        <div class="label">您的 Session ID</div>
    </div><br/>
    <a href="stopSession.jsp">Close Session</a>
</body>
</html>