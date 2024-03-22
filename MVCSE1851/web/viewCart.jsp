<%-- 
    Document   : viewCart
    Created on : Mar 12, 2024, 8:59:40 PM
    Author     : vopha
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cart</title>
    </head>
    <body>
        <h1>Book Store</h1>
        <c:set var="cart" value="${sessionScope.CART}"/>
        <c:if test="${not empty cart}">
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Name</th>
                        <th>Quantity</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                <form action="DispatchServlet">
                    <c:forEach var="item" items="${cart.items}" varStatus="counter">                       
                        <tr>
                            <td>
                                ${counter.count}
                                .</td>
                            <td>
                                ${item.key}
                            </td>
                            <td>
                                ${item.value}
                            </td>
                            <td>
                                <input type="checkbox" name="checkItem" value="${item.key}" />
                            </td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <td colspan="3">
                            <a href="product.html">Add More Books to Your Cart</a>
                        </td>
                        <td>
                            <input type="submit" value="Remove selected items" name="btAction" />
                        </td>
                    </tr>
                </form>
            </tbody>
        </table>

    </c:if>
    <c:if test="${empty cart}">
        <h2>No cart is existed!!!</h2>
    </c:if>
</body>
</html>
