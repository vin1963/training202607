<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Stop Session</title>
</head>
<body>
<h1>
   <% 
     session.invalidate();
     response.sendRedirect("stat.jsp");
   %>
   Session Stopped
</h1>
</body>
</html>