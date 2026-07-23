<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="zh-TW">
<head>
    <meta charset="UTF-8">
    <title>
        <c:choose>
            <c:when test="${not empty product}">編輯商品</c:when>
            <c:otherwise>新增商品</c:otherwise>
        </c:choose>
    </title>
    <style>
        body { font-family: "Microsoft JhengHei", Arial, sans-serif; margin: 20px; }
        .form-group { margin-bottom: 15px; }
        .form-group label { display: inline-block; width: 80px; font-weight: bold; }
        .form-group input { padding: 5px; width: 250px; }
        .btn { padding: 8px 20px; border: none; border-radius: 4px; cursor: pointer; }
        .btn-submit { background-color: #4CAF50; color: white; }
        .btn-cancel { background-color: #888; color: white; text-decoration: none; padding: 8px 20px; }
        .error { color: red; font-weight: bold; margin-bottom: 10px; }
    </style>
</head>
<body>

<%-- 標題：新增 vs 編輯 --%>
<h2>
    <c:choose>
        <c:when test="${not empty product}">編輯商品</c:when>
        <c:otherwise>新增商品</c:otherwise>
    </c:choose>
</h2>

<%-- 錯誤訊息 --%>
<c:if test="${not empty error}">
    <p class="error"><c:out value="${error}" /></p>
</c:if>

<%-- 表單 --%>
<form action="${pageContext.request.contextPath}/products/update" method="post">

    <%-- 編輯模式：隱藏欄位帶入 id --%>
    <c:if test="${not empty product}">
        <input type="hidden" name="id" value="<c:out value='${product.id}' />" />
    </c:if>

    <div class="form-group">
        <label for="name">名稱：</label>
        <input type="text" id="name" name="name" required maxlength="100"
               value="<c:out value='${product.name}' />" />
    </div>

    <div class="form-group">
        <label for="price">價格：</label>
        <input type="number" id="price" name="price" step="0.01" min="0.01" required
               value="<c:out value='${product.price}' />" />
    </div>

    <div class="form-group">
        <label for="stock">庫存：</label>
        <input type="number" id="stock" name="stock" min="0" required
               value="<c:out value='${product.stock}' />" />
    </div>

    <div class="form-group">
        <label for="category">類別：</label>
        <input type="text" id="category" name="category" maxlength="50"
               value="<c:out value='${product.category}' />" />
    </div>

    <div class="form-group">
        <button type="submit" class="btn btn-submit">
            <c:choose>
                <c:when test="${not empty product}">更新</c:when>
                <c:otherwise>新增</c:otherwise>
            </c:choose>
        </button>
        <a class="btn-cancel" href="${pageContext.request.contextPath}/product/list">取消</a>
    </div>

</form>

</body>
</html>