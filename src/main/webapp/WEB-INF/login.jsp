<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="en">
<head>
    <link rel="stylesheet" href="../style/loginStyle.css">
    <meta charset="UTF-8">
    <title>ENAUCT</title>
</head>
<body>

<header>
    <a href="/controller?command=main">ENAUCT</a>
    <a href="#" style="float:right">RU</a>
</header>


<div class="card">

    <div class="container">
        <div>
            <c:if test="${not empty errorAccessMessage}">
                <div class="error">${errorAccessMessage}</div>
            </c:if>
        </div>
        <form action="controller" class="loginForm" method="post">
            <input type = "hidden" name = "command" value = "login">
            <div class="row">
                <div class="col-25">
                    <label for="username">Username</label>
                </div>
                <div class="col-75">
                    <input type="text" id="username" name="username" placeholder="Your username.." required>
                </div>
            </div>
            <div class="row">
                <div class="col-25">
                    <label for="password">Password</label>
                </div>
                <div class="col-75">
                    <input type="password" id="password" name="password" placeholder="Your password.." required>
                </div>
            </div>

            <c:if test="${not empty errorLoginMessage}">
                <div class="error">${errorLoginMessage}</div>
            </c:if>

            <div class="submitButton">
                <input type="submit" value="LOGIN">
            </div>
        </form>
    </div>
</div>

<footer>
    <h4>CONTACT US: enauct@gmail.com</h4>
</footer>


</body>
</html>