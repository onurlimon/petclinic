<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Training</title>
</head>
<body>
	<table>
		<thead>
			<tr style="font-weight: bold;" bgcolor="lightblue">
				<td>ID</td>
				<td>FirstName</td>
				<td>LastName</td>
			</tr>
		</thead>
		<c:forEach items="${owners}" var="owner" varStatus="status">
			<tr bgcolor="${status.index % 2 == 0 ? 'white' : 'lightgray' }">
				<td>${owner.id}</td>
				<td>${owner.firstName}</td>
				<td>${owner.lastName}</td>
			</tr>
		</c:forEach>
	</table>
	<c:if test="${not empty message}">
		<div style="color:blue">
			${message}
		</div>
	</c:if>
</body>
</html>