<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<a href="/home">Back</a>
<form action="/register" method="post">
    Name: <input type="text" name="name">
    Surname: <input type="text" name="surname">
    Phone: <input type="text" name="phone">
    Email: <input type="text" name="email">
    Password <input type="password" name="password">
    <input type="submit" value="register">
</form>
</body>
</html>
