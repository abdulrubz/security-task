<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<html>
	<head>
		<title>
			Homepage
		</title>
	</head>
	
	<body>
		<h2>Homepage</h2>
		<hr>
		
		<p> 
			Welcome to the Security App Homepage
		</p>
		
		<p>
			User: <security:authentication property="principal.username" />
			<br><br>
			Role(s): <security:authentication property="principal.authorities" />
		</p>
		
		<security:authorize access="hasRole('MANAGER')">
			<p>
				<a href="${pageContext.request.contextPath}/manager">Manager Dashboard</a>
				(Only for Manager peeps)
			</p>
		</security:authorize>
		
		<security:authorize access="hasRole('ADMIN')">  		
			<p>
				<a href="${pageContext.request.contextPath}/admin">Admin Dashboard</a>
				
			</p>
		</security:authorize>
		
		<form:form action="${pageContext.request.contextPath}/logout" method="POST">
			<input type="submit" value="Logout" />
		</form:form>
		
		
	</body>
</html>