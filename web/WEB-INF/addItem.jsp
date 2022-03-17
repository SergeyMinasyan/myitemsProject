<%@ page import="model.Category" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <%
    List<Category> categoryList =(List<Category>) request.getAttribute("categories");
    %>
<a href="/home">Back</a>
<form action="/addItem" method="post" enctype="multipart/form-data">
    title: <input type="text" name="title"/><br>
    description: <textarea name="description"></textarea><br>
    price: <input type="number" name="price"/><br>
    <select name="cat_id">
<% for (Category category : categoryList) { %>
<option value="<%=category.getId()%>"><%=category.getName()%></option>
<% } %>
    </select>
    Picture: <input type="file" name="picture"><br>
    <input type="submit" value="register">
</form>
</body>
</html>
