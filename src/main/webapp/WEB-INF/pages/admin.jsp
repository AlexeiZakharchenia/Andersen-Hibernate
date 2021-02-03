<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
Hello ${sessionScope.name} it's admin page
<br>
<jsp:include page="util.jsp"/>
</body>
</html>
