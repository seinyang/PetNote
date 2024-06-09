<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Pet List</title>
</head>
<body>
<h1>Pet List</h1>
<table border="1">
    <tr>
        <th>Name</th>
        <th>Breed</th>
        <th>Gender</th>
        <th>Age</th>
        <th>Size</th>
    </tr>
    <c:forEach var="pet" items="${list}">
        <tr>
            <td>${pet.name}</td>
            <td>${pet.breed}</td>
            <td>${pet.gender}</td>
            <td>${pet.age}</td>
            <td>${pet.psize}</td>
        </tr>
    </c:forEach>
</table>
<a href="${pageContext.request.contextPath}/PetMemberInsert">Add New Pet</a>
</body>
</html>
