<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
	<head>
		<title>Login Page</title>
	</head>
	
	<style>
		.error-text {
		color: red;
		}
	</style>
	
	<body>
		<h2>Login Page</h2>
		<hr>
		
		<form action="${pageContext.request.contextPath}/authenticateTheUser" method="POST">
			
			<c:if test="${param.error!= null }">
				<p class="error-text"> 
					Invalid username or password.
				</p>
			</c:if>
			
			<c:if test="${param.logout != null}">
				<p class="error-text"> 
					You have just logged out.
				</p>
			</c:if>
			Username: 
			<input type="text" name="username" placeholder="Username"> <br/>
			
			Password: 
			<input type="password" name="password" placeholder="Password" > <br/>
			
			<button type="submit">Login</button>
			
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			
		
		</form>
		
		<hr>
		
		<a href="${pageContext.request.contextPath}/register/showRegistrationForm">Register</a>
		
	</body>

</html>