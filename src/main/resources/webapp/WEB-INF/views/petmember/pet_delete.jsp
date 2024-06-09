<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Delete Pet</title>
</head>
<body>
<h1>Delete Pet</h1>
<p>Are you sure you want to delete the following pet?</p>
<p>Name: ${list.name}</p>
<p>Breed: ${list.breed}</p>
<p>Gender: ${list.gender}</p>
<p>Age: ${list.age}</p>
<p>Size: ${list.psize}</p>
<form action="${pageContext.request.contextPath}/PetDelete" method="post">
    <input type="hidden" name="name" value="${list.name}" />
    <input type="submit" value="Delete"/>
</form>
<a href="${pageContext.request.contextPath}/PetList">Cancel</a>
</body>
</html>
