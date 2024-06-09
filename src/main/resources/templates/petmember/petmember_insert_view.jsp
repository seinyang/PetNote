<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Insert Pet Member</title>
</head>
<body>
<h1>Pet Member Inserted Successfully</h1>
<p>Name: ${pet.name}</p>
<p>Breed: ${pet.breed}</p>
<p>Gender: ${pet.gender}</p>
<p>Age: ${pet.age}</p>
<p>Size: ${pet.psize}</p>
<a href="${pageContext.request.contextPath}/PetList">Back to List</a>
</body>
</html>
