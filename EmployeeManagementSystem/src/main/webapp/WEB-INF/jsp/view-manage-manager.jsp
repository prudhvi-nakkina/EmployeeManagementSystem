<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
    <head>
        <title>Employee Management System</title>
        <link href="<c:url value="/css/home.css"/>?version=51" rel="stylesheet" type="text/css">
    </head>
    <body>
    	
    	<h1>Manage Managers</h1>
    	<div class="logout">
    			<a style="padding: 5px;color: black;font-family: cursive;" href="http://localhost:8080/manager/logout">Logout</a>
    	</div>
    	<div class="portal-layout">
    		
    		<div class="dash-heading">
    					Current Managers
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
    						<td>Salary Per Hour:</td>
    						<td class="pad-td"><c:out value="${e.getSalaryPerHour()}"/>$</td>
    					</tr>
    					<tr>
    						<td>Managed Employees:</td>
    						<td class="pad-td">
    							<select name="employees">
    								<c:forEach var="f" items="${e.getEmployees()}">
        								<option value="${f.getEmployeeId()}">${f.getLastName()} - ${f.getDepartment()} - ${f.getProject().getProjectId()}</option>
    								</c:forEach>
								</select>
    						</td>
    					</tr>
    					<tr>
    						<td>Managed Projects:</td>
    						<td class="pad-td">
    							<select name="projects">
    								<c:forEach var="f" items="${e.getProjects()}">
        								<option value="${f.getProjectId()}">${f.getProjectName()} - ${f.getDepartment()} - ${f.getProjectId()}</option>
    								</c:forEach>
								</select>
    						</td>
    					</tr>
    				</table>
    			</div>
    			</c:forEach>
    		</div>
    	</div>
    	<div class="dashboard-box">
    		<div class = "dash-one">
    			<h3>Add/Update Manager</h3>
    				<div class="layout">
            <form action="http://localhost:8080/admin/add-update-manager" method="POST">
            	<div class="input-layout">
            	<div class="form-group">
                    <label for="managerId">Manager ID</label>
                    <input type='text' name="managerId" required/>
                </div>
                <div class="form-group">
                    <label for="firstName">FirstName</label>
                    <input type='text' name="firstName" required/>
                </div>
                <div class="form-group">
                    <label for="lastName">LastName</label>
                    <input type='text' name="lastName" required/>
                </div>
                <div class="form-group">
                    <label for="branch">Branch</label>
                    <input type='text' name="branch" required/>
                </div>
                <div class="form-group">
                    <label for="department">Department</label>
                    <input type='text' required name="department" />
                </div>
                <div class="form-group">
                    <label for="email">Email ID</label>
                    <input type='email' required name="email" />
                </div>
                <div class="form-group">
                    <label for="city">City</label>
                    <input type='text' required name="city" />
                </div>
                <div class="form-group">
                    <label for="address">Address</label>
                    <input type='text' required name="address" />
                </div>
                <div class="form-group">
                    <label for="salary">Salary Per Hour</label>
                    <input type='number' required name="salary" />
                </div>
                <div class="form-group mg-top">
                	
					<input class="dp-in" type="radio" required value="add" name="selection"><span><label for="selection">Add</label></span>
					
      				<input class="dp-in" type="radio" value="update" name="selection"><span><label for="selection">Update</label></span>
                </div>
                </div>
                <div class="form-group">
                    <button class="btn-submit" >Submit</button>
                </div>
                <c:if test="${addManagerError != null}">
                	<div class="err-msg">
                		<c:out value="${addManagerError}"/>
                 	</div>
                </c:if>
                <c:if test="${addManagerError == null}">
                	<div class="suc-msg ">
                		<c:out value="${addManagerSuccess}"/>
                	</div>
                </c:if>
            </form>
			</div>
    		</div>
    		<div class = "dash-two rg-0">
    			<h3>Delete Manager</h3>
    				<div class="layout">
            <form action="http://localhost:8080/admin/delete-manager" method="POST">
            	<div class="input-layout">
                <div class="form-group">
                    <label for="deleteid">Manager ID</label>
                    <input type='text' name="deleteid" required/>
                </div>
                </div>
                <div class="form-group l-1">
                    <button class="btn-submit" >Submit</button>
                </div>
                <c:if test="${deleteManagerError != null}">
                	<div class="err-msg">
                		<c:out value="${deleteManagerError}"/>
                	</div>
                </c:if>
                <c:if test="${deleteManagerError == null}">
                	<div class="suc-msg ">
                		<c:out value="${deleteManagerSuccess}"/>
                	</div>
                </c:if>
            </form>
		</div>
    		</div>
    	</div>
    </body>
</html>