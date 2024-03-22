<%-- 
    Document   : createAccount
    Created on : Mar 10, 2024, 8:43:58 PM
    Author     : vopha
--%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Account</title>
    </head>    
    <body>
        <h1>Registration</h1>        
        <form action="DispatchServlet" method="POST">
            <c:set var="errors" value="${requestScope.CREATE_ERRORS}"/>
            Username* <input type="text" name="txtUsername" value="${param.txtUsername}" />(6 - 20 chars)<br/>
            <c:if test="${not empty errors.usernameLengthError}">
                <font color="red">
                ${errors.usernameLengthError}
                </font><br/>
            </c:if>
            Password* <input type="password" name="txtPassword" value="" />(6 - 30 chars)<br/>
            <c:if test="${not empty errors.passwordLengthError}">
                <font color="red">
                ${errors.passwordLengthError}
                </font><br/>
            </c:if>
            Confirm* <input type="password" name="txtConfirm" value="" /><br/>
            <c:if test="${not empty errors.confirmNotMatch}">
                <font color="red">
                ${errors.confirmNotMatch}
                </font><br/>
            </c:if><br/>
            Full name* <input type="text" name="txtFullName" value="${param.txtFullname}" />(6 - 50 chars)<br/>
            <c:if test="${not empty errors.fullNameLengthError}">
                <font color="red">
                ${errors.fullNameLengthError}
                </font><br/>
            </c:if><br/>
            <c:if test="${not empty errors.usernameIsExisted}">
                <font color="red">
                ${errors.usernameIsExisted}
                <font/><br/>
            </c:if>
            <input type="submit" value="Create new account" name="btAction" />
            <input type="reset" value="Reset" />
        </form>
    </body>
</html>
