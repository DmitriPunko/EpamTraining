<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="text" var="text"/>

<fmt:message bundle="${text}" key="login.label.username" var="username_label"/>
<fmt:message bundle="${text}" key="login.label.password" var="password_label"/>
<fmt:message bundle="${text}" key="login.password.placeholder" var="password_placeholder"/>
<fmt:message bundle="${text}" key="login.username.placeholder" var="username_placeholder"/>
<fmt:message bundle="${text}" key="login.button.submit" var="submit_button"/>
<fmt:message bundle="${text}" key="footer.helpInfo" var="footer_info"/>
<fmt:message bundle="${text}" key="login.username.pattern.error" var="username_pattern_error"/>
<fmt:message bundle="${text}" key="login.username.pattern.error" var="username_pattern_error"/>
<fmt:message bundle="${text}" key="login.error.access.message" var="error_access"/>
<fmt:message bundle="${text}" key="login.error.access.admin.message" var="error_access_admin"/>
<fmt:message bundle="${text}" key="login.error.access.user.message" var="error_access_user"/>
<fmt:message bundle="${text}" key="login.error.login.message" var="error_login"/>
<fmt:message bundle="${text}" key="login.error.account.banned.message" var="error_account_banned"/>

<html lang="${sessionScope.language}">
<head>
    <link rel="stylesheet" href="../style/loginStyle.css">
    <meta charset="UTF-8">
    <title>ENAUCT</title>
</head>
<body>

<header>
    <a href="controller?command=main">ENAUCT</a>
    <a href="controller?command=language&language=${sessionScope.nextLanguage}" style="float:right">${sessionScope.nextLanguage}</a>
</header>


<div class="card">

    <div class="container">
        <div>
            <c:if test="${not empty errorAccessMessage}">
                <div class="error">
                    <c:choose>
                        <c:when test="${errorAccessMessage == 'You should login to view this page'}">
                            ${error_access}
                        </c:when>
                        <c:when test="${errorAccessMessage == 'You should login as admin to view this page'}">
                            ${error_access_admin}
                        </c:when>
                        <c:when test="${errorAccessMessage == 'You should login as user to view this page'}">
                            ${error_access_user}
                        </c:when>
                    </c:choose>
                </div>
            </c:if>
        </div>
        <form action="controller" class="loginForm" method="post">
            <input type="hidden" name="command" value="login">
            <div class="row">
                <div class="col-25">
                    <label for="username">${username_label}</label>
                </div>
                <div class="col-75">
                    <input type="text" id="username" name="username" placeholder="${username_placeholder}" required
                    pattern="^[a-zA-Z][a-zA-Z0-9-_\.]{1,20}$" title="${username_pattern_error}">
                </div>
            </div>
            <div class="row">
                <div class="col-25">
                    <label for="password">${password_label}</label>
                </div>
                <div class="col-75">
                    <input type="password" id="password" name="password" placeholder="${password_placeholder}" required>
                </div>
            </div>

            <c:if test="${not empty errorLoginMessage}">
                <div class="error">
                    <c:choose>
                        <c:when test="${errorLoginMessage == 'Authentication failed!'}">
                            ${error_login}
                        </c:when>
                        <c:when test="${errorLoginMessage == 'Your account has been banned!'}">
                            ${error_account_banned}
                        </c:when>
                    </c:choose>
                </div>
            </c:if>

            <div class="submitButton">
                <input type="submit" value="${submit_button}">
            </div>
        </form>
    </div>
</div>

<footer>
    <h4>${footer_info}: enauct@gmail.com</h4>
</footer>


</body>
</html>