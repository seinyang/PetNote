<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Update Pet</title>
</head>
<body>
<h1>Update Pet</h1>
<form action="${pageContext.request.contextPath}/PetUpdate" method="post">
    <input type="hidden" name="name" value="${pet.name}" />
    Breed: <input type="text" name="breed" value="${pet.breed}" /><br/>
    Gender: <input type="text" name="gender" value="${pet.gender}" /><br/>
    Age: <input type="number" name="age" value="${pet.age}" /><br/>
    Size: <input type="text" name="psize" value="${pet.psize}" /><br/>
    <input type="submit" value="Update"/>
</form>
</body>
</html>
