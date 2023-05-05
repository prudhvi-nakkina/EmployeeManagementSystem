<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
    <head>
        <title>Employee Management System</title>
        <link href="<c:url value="/css/home.css"/>?version=51" rel="stylesheet" type="text/css">
    </head>
    <body>
    	<h1>Admin Dashboard</h1>
    	<div class="logout">
    			<a style="padding: 5px;color: black;font-family: cursive;" href="http://localhost:8080/admin/logout">Logout</a>
    	</div>
    	<div class="detail-list">
    		<div class="detail">
    			<div class="detail-heading">Manage Employees</div>
    			<div style="text-align: center;">
    				<a style="padding: 5px;color: black;font-family: cursive;" href="http://localhost:8080/admin/manage-employee">manage</a>
    			</div>
    		</div>
    		<div class="detail">
    			<div class="detail-heading">Manage Managers</div>
    			<div style="text-align: center;">
    				<a style="padding: 5px;color: black;font-family: cursive;" href="http://localhost:8080/admin/manage-manager">manage</a>
    			</div>
    		</div>
    		<div class="detail">
    			<div class="detail-heading">Manage Projects</div>
    			<div style="text-align: center;">
    				<a style="padding: 5px;color: black;font-family: cursive;" href="http://localhost:8080/admin/manage-project">manage</a>
    			</div>
    		</div>
    		<div class="detail">
    			<div class="detail-heading">Analytics Hub</div>
    			<div style="text-align: center;">
    				<a style="padding: 5px;color: black;font-family: cursive;" href="http://localhost:8080/admin/perform-analytics">launch</a>
    			</div>
    		</div>
    	</div>
    </body>
</html>