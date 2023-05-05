<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
    <head>
        <title>Employee Management System</title>
        <link href="<c:url value="/css/home.css"/>" rel="stylesheet" type="text/css">
    </head>
    <body>
    	<h1>Employee Management System</h1>
    	<div class="detail-list">
    		<div class="detail">
    			<div class="detail-heading">Employee</div>
    			<div style="text-align: center;">
    				<a style="border-right: solid;padding: 5px;color: black;font-family: cursive;" href="/employee/employee-login">Login</a>
    				<a style="padding: 5px;color: black;font-family: cursive;" href="/employee/employee-register">Register</a>
    			</div>
    		</div>
    		<div class="detail">
    			<div class="detail-heading">Manager</div>
    			<div style="text-align: center;">
    				<a style="border-right: solid;padding: 5px;color: black;font-family: cursive;" href="/manager/manager-login">Login</a>
    				<a style="padding: 5px;color: black;font-family: cursive;" href="/manager/manager-register">Register</a>
    			</div>
    		</div>
    		<div class="detail">
    			<div class="detail-heading">Admin</div>
    			<div style="text-align: center;">
    				<a style="padding: 5px;color: black;font-family: cursive;" href="/admin/admin-login">Login</a>
    			</div>
    		</div>
    	</div>
    </body>
</html>