<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="zh-TW">
<head>
    <meta charset="UTF-8">
    <title>商品列表</title>
    <style>
        body { font-family: "Microsoft JhengHei", Arial, sans-serif; margin: 20px; }
        table { border-collapse: collapse; width: 100%; }
        th, td { border: 1px solid #ccc; padding: 8px 12px; text-align: left; }
        th { background-color: #f2f2f2; }
        .btn { padding: 4px 10px; text-decoration: none; border-radius: 4px; }
        .btn-edit { background-color: #4CAF50; color: white; }
        .btn-delete { background-color: #f44336; color: white; }
        .btn-add { background-color: #2196F3; color: white; padding: 8px 16px; }
        .search-form { margin-bottom: 15px; }
        .search-form input { padding: 4px; margin-right: 5px; }
        .stock-low { color: red; font-weight: bold; }
    </style>
</head>
<body>

<h2>商品列表</h2>

<%-- 新增按鈕 --%>
<a class="btn btn-add" href="${pageContext.request.contextPath}/products/add">新增商品</a>
<br/><br/>
<%-- 搜尋表單 --%>
<form class="search-form" action="${pageContext.request.contextPath}/products/search" method="get">
    名稱: <input type="text" name="name" placeholder="輸入商品名稱" />
    類別: <input type="text" name="category" placeholder="輸入類別" />
    <button type="submit">搜尋</button>
    <a href="${pageContext.request.contextPath}/products/list">清除搜尋</a>
</form>
<%-- 商品表格 --%>
<c:choose>
    <c:when test="${not empty products}">
        <table>
            <thead>
                <tr>
                    <th>編號</th>
                    <th>商品名稱</th>
                    <th>價格</th>
                    <th>庫存</th>
                    <th>類別</th>
                    <th>操作</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="p" items="${products}">
                    <tr>
                        <td><c:out value="${p.id}" /></td>
                        <td><c:out value="${p.name}" /></td>
                        <td><fmt:formatNumber value="${p.price}" type="currency" currencySymbol="NT$" /></td>
                        <td>
                            <c:choose>
                                <c:when test="${p.stock < 10}">
                                    <span class="stock-low"><c:out value="${p.stock}" /></span>
                                </c:when>
                                <c:otherwise>
                                    <c:out value="${p.stock}" />
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td><c:out value="${p.category}" /></td>
                        <td>
                            <a class="btn btn-edit"
                               href="${pageContext.request.contextPath}/products/edit?id=${p.id}">編輯</a>
                            <a class="btn btn-delete"
                               href="${pageContext.request.contextPath}/products/delete?id=${p.id}"
                               onclick="return confirm('確定要刪除此商品嗎？');">刪除</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:when>
    <c:otherwise>
        <p>目前沒有商品資料。</p>
    </c:otherwise>
</c:choose>

</body>
</html>