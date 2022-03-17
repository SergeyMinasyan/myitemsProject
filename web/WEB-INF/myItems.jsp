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
    List<Item> items = (List<Item>) request.getAttribute("items");
%>
<div style="margin: 0 auto; width: 1000px;height: 1000px; border: 1px solid blue">

    <div> Welcome <%=user.getName()%> | <a href="/myItems">Իմ հայտարարությունները</a> | <a href="/addItem">Ավելացնել</a><a
            href="/logout">Logout</a></div>

</div>
<% if (items != null){ %>
<div>
    <% for (Item item : items) { %>
    <a href="/singleItem?id=<%=item.getId()%>"></a>
    <div style="width: 105px; float: left">
        <div>
            <% if (item.getPicUrl() != null && !item.getPicUrl().equals("")) {%>
            <img src="/getImage?pic_url=<%=item.getPicUrl()%>" width="100px"/>
            <% } else { %>
            <img src="/img/img.png" width="100"/>
            <% } %>
        </div>
        <div>
            <span><%=item.getTitle()%> | <%=item.getPrice()%></span>
        </div>
    </div>
    <a/>
    <% }
}%>
</div>
</div>
</body>
</html>
es errori het es?aha