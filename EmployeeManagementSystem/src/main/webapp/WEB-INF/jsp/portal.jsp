<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
    <head>
        <title>Employee Management System</title>
        <link href="<c:url value="/css/home.css"/>?version=51" rel="stylesheet" type="text/css">
    </head>
    <body>
    	<h1>Manager Portal</h1>
    	<div class="logout">
    			<a style="padding: 5px;color: black;font-family: cursive;" href="http://localhost:8080/manager/logout">Logout</a>
    	</div>
    	<div class="portal-layout">
    		<div class="dash-heading">
    					Leave Requests
    		</div>
    		<div class="portal-leave">
    			<c:forEach items="${employees}" var="e">
    					<div class = "dash-info-rev ta-1">
    				
    				<table>
    					<tr>
    						<td>Employee ID:</td>
    						<td class="pad-td"><c:out value="${e.getEmployeeId()}"/></td>
    					</tr>
    					<tr>
    						<td>First Name:</td>
    						<td class="pad-td"><c:out value="${e.getFirstName()}"/></td>
    					</tr>
    					<tr>
    						<td>Last Name:</td>
    						<td class="pad-td"><c:out value="${e.getLastName()}"/></td>
    					</tr>
    					<tr>
    						<td>Department:</td>
    						<td class="pad-td"><c:out value="${e.getDepartment()}"/></td>
    					</tr>
    					<tr>
    						<td>Branch:</td>
    						<td class="pad-td"><c:out value="${e.getBranch()}"/></td>
    					</tr>
    					<tr>
    						<td>Email ID:</td>
    						<td class="pad-td"><c:out value="${e.getEmailId()}"/></td>
    					</tr>
    					<tr>
    						<td>Leaves Applied:</td>
    						<td class="pad-td"><c:out value="${e.getLeavesApplied()}"/></td>
    					</tr>
    				</table>
    				<c:if test="${e.getLeavesApplied() != 0}">
    					<form action="http://localhost:8080/manager/approve-leave" method="post">
    					<input type="hidden" name="approveStatus"  value="<c:out value="${e.getEmployeeId()}"/>"/>
    					<input style="margin-left: 140px; cursor: pointer;" type="submit" value="approve">
    				</form>
    				</c:if>
    				<c:if test="${e.getLeavesApplied() == 0 && e.getLeavesAvailable() < 15}">
    					Approved!
    				</c:if>
    			</div>
    			</c:forEach>
    		</div>
    		<div class="dash-heading mt-1">
    					Transfer Employee
    		</div>
    		<div class="portal-leave transfer">
    			<div class = "dash-info-rev ta-1">
    				<form action="http://localhost:8080/manager/transfer-employee" method="post">
    				<div style="padding: 10px;">
    					<label for="transferEmp">Select Employee to transfer</label>
    				<select name="transferEmp">
    					<c:forEach var="e" items="${employees}">
        					<option value="${e.getEmployeeId()}">${e.getEmployeeId()}</option>
    					</c:forEach>
					</select>
    				</div>
    				<div style="padding: 10px;">
    					<label for="transferProj">Select Project to transfer</label>
    				<select name="transferProj">
    					<c:forEach var="e" items="${projects}">
        					<option value="${e.getProjectName()}">${e.getProjectName()}</option>
    					</c:forEach>
					</select>
    				</div>
					<input class="btn-submit" type="submit" value="transfer">
    				</form>
    				<c:if test="${success == true}">
    					Employee Transferred to new project successfully!
    				</c:if>
    			</div>
    		</div>
    	</div>
    </body>
</html>