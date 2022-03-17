<%@ page import="model.User" %>
<%@ page import="model.Category" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Item" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    User user = (User) session.getAttribute("user");
    List<Category> categories = (List<Category>) request.getAttribute("categories");
    List<Item> items = (List<Item>) request.getAttribute("items");
%>
<div style="margin: 0 auto; width: 1000px;height: 1000px; border: 1px solid blue">
    <% if (user == null) { %>
    <div style="float: right"><a href="/login">Login</a> | <a href="/register">Register</a></div>
    <% } else { %>
    <div> Welcome <%=user.getName()%> | <a href="/myItems">Իմ հայտարարությունները</a> | <a href="/addItem">Ավելացնել</a><a href="/logout">Logout</a></div>
    <% } %>
    <div>
        <ul style="overflow: hidden">
            <li style="display: inline;margin-left: 40px"><a href="/home">Գլխավոր</a></li>
            <% for (Category category : categories) { %>
            <li style="display: inline;margin-left: 40px">
                <a href="/home?cat_id=<%=category.getId()%>"><%=category.getName()%>
            </a></li>
            <% } %>
        </ul>
    </div>

    <div>
        <% for (Item item : items) { %>
        <a href="/singleItem?id=<%=item.getId()%>"></a>
        <div style="width: 105px; float: left">
            <div>
                <% if (item.getPicUrl() != null  && !item.getPicUrl().equals("")) {%>
                <img src="/getImage?pic_url=<%=item.getPicUrl()%>" width="100px" height="100"/>
                <% } else { %>
                <img src="/img/img.png" width="100" height="100"/>
                <% } %>
            </div>
            <div>
                <span><%=item.getTitle()%> | <%=item.getPrice()%></span>
            </div>
        </div>
        <a/>
        <% } %>
    </div>
</div>
</body>
</html>
