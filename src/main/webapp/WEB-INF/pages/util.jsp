<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<c:forEach var="r" items="${roles}">
    <a href="/${r}"> ${r} </a>
    <br>
</c:forEach>
<a href="hello">HOME</a>
</body>
</html>
