<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
Hello ${sessionScope.name} it's subscriber page
<br>
<jsp:include page="util.jsp"/>
</body>
</html>
