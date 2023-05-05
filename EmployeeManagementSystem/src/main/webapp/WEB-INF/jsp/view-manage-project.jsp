<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
    <head>
        <title>Employee Management System</title>
        <link href="<c:url value="/css/home.css"/>?version=51" rel="stylesheet" type="text/css">
    </head>
    <body>
    	
    	<h1>Manage Projects</h1>
    	<div class="logout">
    			<a style="padding: 5px;color: black;font-family: cursive;" href="http://localhost:8080/manager/logout">Logout</a>
    	</div>
    	<div class="portal-layout">
    		
    		<div class="dash-heading">
    					Current Projects
    		</div>
    		<div class="portal-leave">
    			<c:forEach items="${projects}" var="e">
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
    						<td class="pad-td"><c:out value="${e.getProjectManager().getLastName()}"/></td>
    					</tr>
    					<tr>
    						<td>Employees in project:</td>
    						<td class="pad-td">
    							<select name="employees">
    								<c:forEach var="f" items="${e.getEmployees()}">
        								<option value="${f.getFirstName()}">${f.getFirstName()} - ${f.getEmployeeId()}</option>
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
    			<h3>Add/Update Project</h3>
    				<div class="layout">
            <form action="http://localhost:8080/admin/add-update-project" method="POST">
            	<div class="input-layout">
            	<div class="form-group">
                    <label for="projectId">Project ID</label>
                    <input type='text' name="projectId" required/>
                </div>
                <div class="form-group">
                    <label for="projectName">Project Name</label>
                    <input type='text' name="projectName" required/>
                </div>
                <div class="form-group">
                    <label for="client">Client</label>
                    <input type='text' name="client" required/>
                </div>
                <div class="form-group">
                    <label for="department">Department</label>
                    <input type='text' required name="department" />
                </div>
                <div class="form-group">
                    <label for="budget">Budget</label>
                    <input type='number' required name="budget" />
                </div>
                <div class="form-group mg-top">
                	
					<input class="dp-in" type="radio" required value="add" name="selection"><span><label for="selection">Add</label></span>
					
      				<input class="dp-in" type="radio" value="update" name="selection"><span><label for="selection">Update</label></span>
                </div>
             	<div class="form-group">
    					<label for="setManager">Select Project Manager</label>
    				<select name="setManager">
    					<c:forEach var="e" items="${managers}">
        					<option value="${e.getManagerId()}">${e.getLastName()} - ${e.getDepartment()}</option>
    					</c:forEach>
					</select>
                </div>
                </div>
                <div class="form-group">
                    <button class="btn-submit" >Submit</button>
                </div>
                <c:if test="${addProjectError != null}">
                	<div class="err-msg">
                		<c:out value="${addProjectError}"/>
                	</div>
                </c:if>
                <c:if test="${addProjectError == null}">
                	<div class="suc-msg ">
                		<c:out value="${addProjectSuccess}"/>
                	</div>
                </c:if>
            </form>
			</div>
    		</div>
    		<div class = "dash-two rg-0">
    			<h3>Delete Project</h3>
    				<div class="layout">
            <form action="http://localhost:8080/admin/delete-project" method="POST">
            	<div class="input-layout">
                <div class="form-group">
                    <label for="deleteid">Project ID</label>
                    <input type='text' name="deleteid" required/>
                </div>
                </div>
                <div class="form-group l-1">
                    <button class="btn-submit" >Submit</button>
                </div>
                <c:if test="${deleteProjectError != null}">
                	<div class="err-msg">
                		<c:out value="${deleteProjectError}"/>
                	</div>
                </c:if>
                <c:if test="${deleteProjectError == null}">
                	<div class="suc-msg ">
                		<c:out value="${deleteProjectSuccess}"/>
                	</div>
                </c:if>
            </form>
		</div>
    		</div>
    	</div>
    </body>
</html>




