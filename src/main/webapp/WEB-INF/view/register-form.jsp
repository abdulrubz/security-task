<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  

<html>
	<head>
		<title>Registration Form</title>
	</head>
	
	<style>
		.error-text {
			color: red;
		}
	</style>
	
	<body>
		<h2>Register Form</h2>
		<hr>
		<p>
			Please provide with username and password
		</p>
		<form:form action="${pageContext.request.contextPath}/register/processRegistrationForm" modelAttribute="userModel">
			
			<c:if test="${registrationError != null}">
				<p class="error-text">
					${registrationError}
				</p>
			</c:if>
				
			Username: 
			<form:input path="userName" placeholder="Username"/> <br/>
				
			Password:
			<form:password path="password" placeholder="Password" /> <br/>
				
			<button type="submit">Register</button>
			
		</form:form>
	</body>

</html>