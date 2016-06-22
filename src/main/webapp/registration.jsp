<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration page</title>
</head>
<body>
<div align="center" style="background-color:yellow; color:black; ">
  <form action="Register" method="POST">
    <p>Please fill all fields correctly and accurate.<br>
      </p>
    <input type="text" name="fullname" value=""/><p>Please type here only letters.</p><br>
    <input type="text" name="login" value=""/><p>Please type here only letters and/or numbers.</p><br>
    <input type="text" name="password" value=""/><p>Please type here only letters and/or numbers.</p><br>
    <input type="submit" name="ok" value="Register"/><br>
  </form>
</div>
</body>
</html>
