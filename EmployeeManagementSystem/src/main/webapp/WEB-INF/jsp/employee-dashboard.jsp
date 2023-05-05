<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
    <head>
        <title>Employee Management System</title>
        <link href="<c:url value="/css/home.css"/>?version=51" rel="stylesheet" type="text/css">
    </head>
    <body>
    	
    	<h1><c:out value="${employee.getLastName()}"/>'s Dashboard</h1>
    	<div class="logout">
    			<a style="padding: 5px;color: black;font-family: cursive;" href="http://localhost:8080/employee/logout">Logout</a>
    	</div>
    	
    	<div class="dashboard-box">
    		<div class = "dash-one">
    			<div class = "dash-info">
    				<div class="dash-heading">
    					My Info
    				</div>
    				<table class = "table1">
    					<tr>
    						<td>Employee ID:</td>
    						<td class="pad-td"><c:out value="${employee.getEmployeeId()}"/></td>
    					</tr>
    					<tr>
    						<td>Department:</td>
    						<td class="pad-td"><c:out value="${employee.getDepartment()}"/></td>
    					</tr>
    					<tr>
    						<td>Branch:</td>
    						<td class="pad-td"><c:out value="${employee.getBranch()}"/></td>
    					</tr>
    					<tr>
    						<td>Email ID:</td>
    						<td class="pad-td"><c:out value="${employee.getEmailId()}"/></td>
    					</tr>
    				</table>
    			</div>
    			<div class = "dash-info gap-1">
    				<div class="dash-heading">
    					My Personal Info
    				</div>
    				<table class = "table1">
    					<tr>
    						<td>First Name:</td>
    						<td class="pad-td"><c:out value="${employee.getFirstName()}"/></td>
    					</tr>
    					<tr>
    						<td>Last Name:</td>
    						<td class="pad-td"><c:out value="${employee.getLastName()}"/></td>
    					</tr>
    					<tr>
    						<td>City:</td>
    						<td class="pad-td"><c:out value="${employee.getCity()}"/></td>
    					</tr>
    					<tr>
    						<td>Address:</td>
    						<td class="pad-td"><c:out value="${employee.getAddress()}"/></td>
    					</tr>
    				</table>
    			</div>
    			<div class = "dash-info gap-1">
    				<div class="dash-heading">
    					Apply Leave
    				</div>
    				<table class = "table2">
    					<tr>
    						<td>Available Leaves:</td>
    						<td class="pad-td"><c:out value="${employee.getLeavesAvailable()}"/></td>
    					</tr>
    				</table>
    				<form style="border:none" action="http://localhost:8080/employee/apply-leave" method="POST">
    					<label style="margin-left: 115px" for="leavesReq">Enter no.of leaves:</label>
    					<input style="display: inline;" type="number" required name="leavesReq" />
    					<button class="btn-apply" >Apply</button>
    					
    				</form>
    				<div style="color: #cd1e1e; text-align:center;">
    						<c:if test="${error != null}">
    							<c:out value="${error}"/>
    						</c:if>
    				</div>
    			</div>
    		</div>
    		<div class = "dash-two">
    			<div class = "dash-info-rev">
    				<div class="dash-heading">
    					My Notifications
    				</div>
    				<c:if test="${notification == null}">
    					No Notifications
    				</c:if>
    				<div style="color: #2c71da;">
    					<c:if test="${notification != null}">
    					<c:out value="${notification}"/>
    				</c:if>
    				</div>
    			</div>
    			<div class = "dash-info-rev">
    				<div class="dash-heading">
    					My Manager Info
    				</div>
    				<table>
    					<tr>
    						<td>Manager ID:</td>
    						<td class="pad-td"><c:out value="${employee.getManager().getManagerId()}"/></td>
    					</tr>
    					<tr>
    						<td>First Name:</td>
    						<td class="pad-td"><c:out value="${employee.getManager().getFirstName()}"/></td>
    					</tr>
    					<tr>
    						<td>Last Name:</td>
    						<td class="pad-td"><c:out value="${employee.getManager().getLastName()}"/></td>
    					</tr>
    					<tr>
    						<td>Department:</td>
    						<td class="pad-td"><c:out value="${employee.getManager().getDepartment()}"/></td>
    					</tr>
    					<tr>
    						<td>Branch:</td>
    						<td class="pad-td"><c:out value="${employee.getManager().getBranch()}"/></td>
    					</tr>
    					<tr>
    						<td>Email ID:</td>
    						<td class="pad-td"><c:out value="${employee.getManager().getEmailId()}"/></td>
    					</tr>
    				</table>
    			</div>
    			<div class = "dash-info-rev gap-2">
    				<div class="dash-heading">
    					My Project Info
    				</div>
    				<table>
    					<tr>
    						<td>Project ID:</td>
    						<td class="pad-td"><c:out value="${employee.getProject().getProjectId()}"/></td>
    					</tr>
    					<tr>
    						<td>Project Name:</td>
    						<td class="pad-td"><c:out value="${employee.getProject().getProjectName()}"/></td>
    					</tr>
    					<tr>
    						<td>Client:</td>
    						<td class="pad-td"><c:out value="${employee.getProject().getClient()}"/></td>
    					</tr>
    				</table>
    			</div>
    		</div>
    	</div>
    </body>
</html>