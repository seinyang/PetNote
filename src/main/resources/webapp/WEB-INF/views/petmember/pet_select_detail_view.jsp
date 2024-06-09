<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Pet Detail</title>
</head>
<body>
<h1>Pet Detail</h1>
<p>Name: ${list.name}</p>
<p>Breed: ${list.breed}</p>
<p>Gender: ${list.gender}</p>
<p>Age: ${list.age}</p>
<p>Size: ${list.psize}</p>
<a href="${pageContext.request.contextPath}/PetUpdate?name=${list.name}">Update</a>
<a href="${pageContext.request.contextPath}/PetDelete?name=${list.name}">Delete</a>
<a href="${pageContext.request.contextPath}/PetList">Back to List</a>
</body>
</html>
