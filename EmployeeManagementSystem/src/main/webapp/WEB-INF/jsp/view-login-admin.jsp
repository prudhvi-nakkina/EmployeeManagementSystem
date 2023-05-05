<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
    <head>
        <title>Employee Management System</title>
        <link href="<c:url value="/css/home.css"/>" rel="stylesheet" type="text/css">
    </head>
    <body>
    	<h1>Admin Login Form</h1>
		<div class="layout">
            <form action="http://localhost:8080/admin/login" method="POST">
                <div class="input-layout">
                <div class="form-group">
                    <label for="username">Username</label>
                    <input type='text' required name="username" />
                </div>
                <div class="form-group">
                    <label for="emppassword">Password</label>
                    <input type='password' required name="emppassword" />
                </div>
                </div>
                <div class="form-group">
                    <button class="btn-submit" >Login</button>
                </div>
                <c:if test="${loginError == true}">
                	<div class="err-msg">
                		<p>
                			Invalid Credentials!
                		</p>
                		
                	</div>
                	<div class="err-msg">
                		Please check your Username/Password
                	</div>
                </c:if>
            </form>
		</div>
    </body>
</html>
