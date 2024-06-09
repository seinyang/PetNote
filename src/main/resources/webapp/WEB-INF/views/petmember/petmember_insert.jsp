<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Insert Pet Member</title>
</head>
<body>
<h1>Insert Pet Member</h1>
<form action="${pageContext.request.contextPath}/PetMemberInsert" method="post">
    Name: <input type="text" name="name" /><br/>
    Breed: <input type="text" name="breed" /><br/>
    Gender: <input type="text" name="gender" /><br/>
    Age: <input type="number" name="age" /><br/>
    Size: <input type="text" name="psize" /><br/>
    <input type="submit" value="Insert"/>
</form>
</body>
</html>
