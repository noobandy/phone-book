<%@ page contentType="text/html" charset="utf8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<title>Contacts</title>
</head>
<body>
<ul>
	<c:forEach items="${contacts}" var="contact">
		<li>
			<p>
				<c:out value="${contact.name}"></c:out> : <c:out value="${contact.number}"></c:out>
			</p>
		</li>

	</c:forEach>
</ul>
</body>
</html>