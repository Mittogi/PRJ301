<%-- 
    Document   : search
    Created on : Feb 26, 2024, 2:06:12 PM
    Author     : vopha
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search</title>
    </head>
    <body>
        <font color="red">
        Welcome, ${sessionScope.USERINFO.fullName} <a href="LogoutServlet">Logout</a>
        </font>

        <h1>Search Page</h1>

        <form action="DispatchServlet">
            Search <input type="text" name="txtSearchValue" value="${param.txtSearchValue}" /> <br/>
            <input type="submit" value="Search" name="btAction" />
        </form>
        <br/>

        <c:set var="searchValue" value="${param.txtSearchValue}"/>
        
        <c:if test="${not empty searchValue}">
            <c:set var="result" value="${requestScope.SEARCH_RESULT}"/>
            
            <c:if test="${not empty result}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Username</th>
                            <th>Password</th>
                            <th>Full name</th>
                            <th>Role</th>
                            <th>Delete</th>
                            <th>Update</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="dto" items="${result}" varStatus="counter">
                        <form action="DispatchServlet">
                            <tr>
                                <td>
                                    ${counter.count}
                                .</td>
                                <td>
                                    ${dto.username}
                                    <input type="hidden" name="txtUsername" value="${dto.username}" />
                                </td>
                                <td>
                                    <input type="text" name="txtPassword" value="${dto.password}" />
                                </td>
                                <td>
                                    ${dto.fullName}
                                </td>
                                <td>
                                    <input type="checkbox" name="checkAdmin" value="ADMIN" 
                                           <c:if test="${dto.role}">checked="checked"</c:if>
                                    />    
                                </td>
                                <td>
                                    <c:url var="deleteLink" value="DispatchServlet">
                                        <c:param name="btAction" value="Delete"/>
                                        <c:param name="pk" value="${dto.username}"/>
                                        <c:param name="lastSearchValue" value="${param.txtSearchValue}"/>
                                    </c:url>
                                    <a href="${deleteLink}">Delete</a>
                                </td>
                                <td>
                                    <input type="submit" value="Update" name="btAction" />
                                    <input type="hidden" name="lastSearchValue" value="${param.txtSearchValue}" />
                                </td>
                            </tr>
                        </form>
                        </c:forEach>
                    </tbody>
                </table>

            </c:if>
            
            <c:if test="${empty result}">
                <h2>No record is matched!!!</h2>
            </c:if>
        </c:if>
    </body>
</html>
