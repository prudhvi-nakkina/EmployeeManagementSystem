<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
    <head>
        <title>Employee Management System</title>
        <link href="<c:url value="/css/home.css"/>" rel="stylesheet" type="text/css">
    </head>
    <body>
    <h1>Manager Registration Form</h1>
		<div class="layout">
            <form action="http://localhost:8080/manager/register" method="POST">
            	<div class="input-layout">
                <div class="form-group">
                    <label for="firstName">First Name</label>
                    <input type='text' name="firstName" required/>
                </div>
                <div class="form-group">
                    <label for="lastName">Last Name</label>
                    <input type='text' name="lastName" required/>
                </div>
                <div class="form-group">
                    <label for="city">City</label>
                    <input type='text' required name="city" />
                </div>
                <div class="form-group">
                    <label for="email">Email ID</label>
                    <input type='email' required name="email" />
                </div>
                <div class="form-group">
                    <label for="address">Address</label>
                    <input type='text' required name="address" />
                </div>
                <div class="form-group">
                    <label for="emppassword">Set Password</label>
                    <input type='password' required name="emppassword" />
                </div>
                </div>
                <div class="form-group">
                    <button class="btn-submit" >Register</button>
                </div>
            </form>
		</div>
	</body>
</html>