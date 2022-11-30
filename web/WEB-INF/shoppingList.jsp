<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping List</title>
    </head>
    <body>
        <h1>Shopping List</h1>
        <p>Hello, ${username} <a href="?action=logout">Logout</a></p>
        <h2>List</h2>
        <form method="post">
            <label>Add Item: </label>
            <input type="text" name="item">
            <input type="submit" value="Add">
            <input type="hidden" name="action" value="add">
        </form>
        <form method="post">
            <ul>
                <c:forEach items="${items}" var="item">
                    <li><input type="radio" name="item" value="${item}">${item}</li>
                </c:forEach>
            </ul>
            <c:if test="${list.size()>0}">
                <input type="submit" value="Delete">
                <input type="hidden" name="action" value="delete">
            </c:if>
            
        </form>
    </body>
</html>
