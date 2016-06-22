<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome!</title>
</head>
<body>
<div align="center" style="background-color:limegreen; color:black; ">
    <p> We are happy to see you at our hotel booking page. <br>
    To get access to booking service you can <a href="registration.jsp">register</a> or<br>
    </p>
    ${message}
    <form action="Login" method="POST">
        <input type="text" name="login" value=""/><br>
        <input type="text" name="password" value=""/><br>
        <input type="submit" name="ok" value="Login"/><br>
    </form>
</div>
</body>
</html>
