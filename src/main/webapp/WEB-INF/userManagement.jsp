<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" type="text/css" href="../style/userManagementStyle.css">
    <meta charset="UTF-8">
    <title>ENAUCT</title>
</head>
<body>

<header>
    <a href="controller?command=main" style="float:left">ENAUCT</a>
    <a href="#">RU</a>
    <a href="controller?command=signOut">SIGN OUT</a>
    <a href="controller?command=profile">PROFILE</a>
    <a href="controller?command=offerALotPage">OFFER A LOT</a>
</header>


<div class="container">

    <div class="leftcolumn">
        <div class="card">
            <a href="controller?command=profile" class="adminProfile link">PERSONAL DATA</a>
            <a href="controller?command=lotManagement" class="lotManagement link">LOT MANAGEMENT</a>
        </div>
    </div>

    <div class="rightcolumn">
        <div class="card">
            <h4>Users:</h4>
            <hr>
            <c:forEach items="${userList}" var="user">
                <div class="col-75">
                    <a href="controller?command=lotInfo&lotId=${user.firstName}">${user.firstName} ${user.lastName} ${user.userName} ${user.email}</a>
                </div>
                <div class="col-25">
                    <c:choose>
                        <c:when test="${user.isBanned() eq false}">
                            <a href="controller?command=banUser&userId=${user.idUser}" class="banLink">BAN</a>
                        </c:when>
                        <c:when test="${user.isBanned() eq true}">
                            <a href="controller?command=unbanUser&userId=${user.idUser}" class="unbanLink">UNBAN</a>
                        </c:when>
                    </c:choose>
                </div>
                <hr>
            </c:forEach>
        </div>
    </div>
</div>

<footer>
    <h4>CONTACT US: enauct@gmail.com</h4>
</footer>

</body>
</html>