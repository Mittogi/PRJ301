<%-- 
    Document   : product
    Created on : Mar 13, 2024, 7:44:29 PM
    Author     : vopha
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Product</title>
    </head>
    <body>
        <h1>Book Store</h1>
        <form action="DispatchServlet">
            choose <select name="chooseProduct">
                <c:forEach var="product" items="${requestScope.PRODUCTS}">
                    <option>${product.productName}</option>
                </c:forEach>
            </select>
            <br/>
            <input type="submit" value="Add book to your card" name="btAction" />
            <input type="submit" value="View cart" name="btAction" />
        </form>
    </body>
</html>
