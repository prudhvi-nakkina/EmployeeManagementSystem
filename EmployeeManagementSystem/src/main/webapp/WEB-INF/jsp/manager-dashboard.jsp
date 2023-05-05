<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
    <head>
        <title>Employee Management System</title>
        <link href="<c:url value="/css/home.css"/>?version=51" rel="stylesheet" type="text/css">
    </head>
    <body>
    	
    	<h1><c:out value="${manager.getLastName()}"/>'s Dashboard</h1>
    	<div class="logout">
    			<a style="padding: 5px;color: black;font-family: cursive;" href="http://localhost:8080/manager/logout">Logout</a>
    	</div>
    	<div class="dashboard-box">
    		<div class = "dash-one">
    			<div class = "dash-info w-1">
    				<div class="dash-heading">
    					My Info
    				</div>
    				<table class = "table1 ml-1">
    					<tr>
    						<td>Manager ID:</td>
    						<td class="pad-td"><c:out value="${manager.getManagerId()}"/></td>
    					</tr>
    					<tr>
    						<td>Department:</td>
    						<td class="pad-td"><c:out value="${manager.getDepartment()}"/></td>
    					</tr>
    					<tr>
    						<td>Branch:</td>
    						<td class="pad-td"><c:out value="${manager.getBranch()}"/></td>
    					</tr>
    					<tr>
    						<td>Email ID:</td>
    						<td class="pad-td"><c:out value="${manager.getEmailId()}"/></td>
    					</tr>
    				</table>
    			</div>
    		</div>
    		<div class = "dash-two">
    			<div class="dash-info w-1">
    				<div class="dash-heading">
    					My Personal Info
    				</div>
    				<table class = "table1 ml-1">
    					<tr>
    						<td>First Name:</td>
    						<td class="pad-td"><c:out value="${manager.getFirstName()}"/></td>
    					</tr>
    					<tr>
    						<td>Last Name:</td>
    						<td class="pad-td"><c:out value="${manager.getLastName()}"/></td>
    					</tr>
    					<tr>
    						<td>City:</td>
    						<td class="pad-td"><c:out value="${manager.getCity()}"/></td>
    					</tr>
    					<tr>
    						<td>Address:</td>
    						<td class="pad-td"><c:out value="${manager.getAddress()}"/></td>
    					</tr>
    				</table>
    			</div>
    		</div>
    	</div>
    	<div class="portal-layout">
    		<div style="margin-top:30px"></div>
    		
    		<div class="dash-heading">
    					My Team
    		</div>
    		<div class="portal-leave">
    			<c:forEach items="${employees}" var="e">
    					<div class = "dash-info-rev">
    				
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
    				</table>
    			</div>
    			</c:forEach>
    		</div>
    		<div style="margin-top:30px"></div>
    		<div class="dash-heading">
    					My Projects
    			</div>
    		<div class="portal-leave">
    			<c:forEach items="${projects}" var="p">
    					<div class = "dash-info-rev">
    				
            			<table>
    						<tr>
    							<td>Project ID:</td>
    							<td class="pad-td"><c:out value="${p.getProjectId()}"/></td>
    						</tr>
    						<tr>
    							<td>Project Name:</td>
    							<td class="pad-td"><c:out value="${p.getProjectName()}"/></td>
    						</tr>
    						<tr>
    							<td>Client:</td>
    							<td class="pad-td"><c:out value="${p.getClient()}"/></td>
    						</tr>
    					</table>
    				
    					</div>
    			</c:forEach>
    		</div>
    		<div class="detail mt-1 ta-1">
    			<div class="detail-heading">My Portal</div>
    			<a style="padding: 5px;color: black;font-family: cursive;text-align" href="http://localhost:8080/manager/portal">Click here to launch portal</a>
    		</div>
    	</div>
    </body>
</html>