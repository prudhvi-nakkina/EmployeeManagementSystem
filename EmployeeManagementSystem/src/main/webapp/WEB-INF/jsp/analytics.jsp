<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
    <head>
        <title>Employee Management System</title>
        <link href="<c:url value="/css/home.css"/>?version=51" rel="stylesheet" type="text/css">
    </head>
    <body>
    	
    	<h1>Analytics</h1>
    	<div class="logout">
    			<a style="padding: 5px;color: black;font-family: cursive;" href="http://localhost:8080/manager/logout">Logout</a>
    	</div>
    	<div class="portal-layout">
    		
    		<div class="dash-heading">
    					Projects On Track
    		</div>
    		<div class="portal-leave">
    			<c:forEach items="${pprojects}" var="e">
    			<div class = "dash-info-rev">
    				
    				<table>
    					<tr>
    						<td>Project ID:</td>
    						<td class="pad-td"><c:out value="${e.getProjectId()}"/></td>
    					</tr>
    					<tr>
    						<td>Project Name:</td>
    						<td class="pad-td"><c:out value="${e.getProjectName()}"/></td>
    					</tr>
    					<tr>
    						<td>Client:</td>
    						<td class="pad-td"><c:out value="${e.getClient()}"/></td>
    					</tr>
    					<tr>
    						<td>Department:</td>
    						<td class="pad-td"><c:out value="${e.getDepartment()}"/></td>
    					</tr>
    					<tr>
    						<td>Started On:</td>
    						<td class="pad-td"><c:out value="${e.getStartDate()}"/></td>
    					</tr>
    					<tr>
    						<td>Budget:</td>
    						<td class="pad-td"><c:out value="${e.getBudget()}"/> $</td>
    					</tr>
    					<tr>
    						<td>Expenditure:</td>
    						<td class="pad-td"><c:out value="${e.getExpenditure()}"/> $</td>
    					</tr>
    					<tr>
    						<td>Project Manager:</td>
    						<c:if test="${e.getProjectManager() != null}">
    							<td class="pad-td"><c:out value="${e.getProjectManager().getLastName()}"/></td>
    						</c:if>
    						
    					</tr>
    					<tr>
    						<td>Employees in project:</td>
    						<td class="pad-td">
    						<c:if test="${e.getEmployees() != null}">
    							<select name="employees">
    								<c:forEach var="f" items="${e.getEmployees()}">
        								<option value="${f.getFirstName()}">${f.getLastName()} - ${f.getEmployeeId()}</option>
    								</c:forEach>
								</select>
    						</c:if>
    						</td>
    					</tr>
    				</table>
    			</div>
    			</c:forEach>
    		</div>
    	</div>
    	<div class="portal-layout mt-1">
    		
    		<div class="dash-heading">
    					Projects Over Budget
    		</div>
    		<div class="portal-leave">
    			<c:forEach items="${nprojects}" var="e">
    			<div class = "dash-info-rev">
    				
    				<table>
    					<tr>
    						<td>Project ID:</td>
    						<td class="pad-td"><c:out value="${e.getProjectId()}"/></td>
    					</tr>
    					<tr>
    						<td>Project Name:</td>
    						<td class="pad-td"><c:out value="${e.getProjectName()}"/></td>
    					</tr>
    					<tr>
    						<td>Client:</td>
    						<td class="pad-td"><c:out value="${e.getClient()}"/></td>
    					</tr>
    					<tr>
    						<td>Department:</td>
    						<td class="pad-td"><c:out value="${e.getDepartment()}"/></td>
    					</tr>
    					<tr>
    						<td>Started On:</td>
    						<td class="pad-td"><c:out value="${e.getStartDate()}"/></td>
    					</tr>
    					<tr>
    						<td>Budget:</td>
    						<td class="pad-td"><c:out value="${e.getBudget()}"/> $</td>
    					</tr>
    					<tr>
    						<td>Expenditure:</td>
    						<td class="pad-td" style="color:red;"><c:out value="${e.getExpenditure()}"/> $</td>
    					</tr>
    					<tr>
    						<td>Project Manager:</td>
    						<c:if test="${e.getProjectManager() != null}">
    							<td class="pad-td"><c:out value="${e.getProjectManager().getLastName()}"/></td>
    						</c:if>
    						
    					</tr>
    					<tr>
    						<td>Employees in project:</td>
    						<td class="pad-td">
    						<c:if test="${e.getEmployees() != null}">
    							<select name="employees">
    								<c:forEach var="f" items="${e.getEmployees()}">
        								<option value="${f.getFirstName()}">${f.getLastName()} - ${f.getEmployeeId()}</option>
    								</c:forEach>
								</select>
    						</c:if>
    						</td>
    					</tr>
    				</table>
    			</div>
    			</c:forEach>
    		</div>
    	</div>
    	<div class="portal-layout mt-1">
    		
    		<div class="dash-heading">
    					Top Earning Employees
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
    						<td>Salary Per Hour:</td>
    						<td class="pad-td"><c:out value="${e.getSalaryPerHour()}"/>$</td>
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
    						<td>Manager LastName:</td>
    						<c:if test="${e.getManager() != null}">
    							<td class="pad-td"><c:out value="${e.getManager().getLastName()}"/></td>
    						</c:if>
    					</tr>
    					<tr>
    						<td>Project Name:</td>
    						<c:if test="${e.getProject() != null}">
    							<td class="pad-td"><c:out value="${e.getProject().getProjectName()}"/></td>
    						</c:if>
    					</tr>
    				</table>
    			</div>
    			</c:forEach>
    		</div>
    	</div>
    	<div class="portal-layout mt-1">
    		
    		<div class="dash-heading">
    					Top Earning Managers
    		</div>
    		<div class="portal-leave">
    			<c:forEach items="${managers}" var="e">
    					<div class = "dash-info-rev">
    				
    				<table>
    					<tr>
    						<td>Manager ID:</td>
    						<td class="pad-td"><c:out value="${e.getManagerId()}"/></td>
    					</tr>
    					<tr>
    						<td>Salary Per Hour:</td>
    						<td class="pad-td"><c:out value="${e.getSalaryPerHour()}"/>$</td>
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
    						<td>Managed Employees:</td>
    						<td class="pad-td">
    							<c:if test="${e.getEmployees() != null}">
    								<select name="employees">
    								<c:forEach var="f" items="${e.getEmployees()}">
    								<c:if test="${f != null && f.getEmployeeId() != null}">
    									<option value="${f.getEmployeeId()}">${f.getLastName()} - ${f.getDepartment()} - ${f.getProject().getProjectId()}</option>
    								</c:if>
    								</c:forEach>
								</select>
    							</c:if>
    							
    						</td>
    					</tr>
    					<tr>
    						<td>Managed Projects:</td>
    						<td class="pad-td">
    							<c:if test="${e.getProjects() != null}">
    								<select name="projects">
    								<c:forEach var="f" items="${e.getProjects()}">
    								<c:if test="${f != null && f.getProjectId() != null}">
    									<option value="${f.getProjectId()}">${f.getProjectName()} - ${f.getDepartment()} - ${f.getProjectId()}</option>
    								</c:if>
    								</c:forEach>
								</select>
    							</c:if>
    						</td>
    					</tr>
    				</table>
    			</div>
    			</c:forEach>
    		</div>
    	</div>
    </body>
</html>