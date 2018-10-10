<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" type="text/css" href="../style/lotManagementStyle.css">
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
            <a href="controller?command=userManagement" class="userManagement link">USER MANAGEMENT</a>
        </div>
    </div>

    <div class="rightcolumn">
        <div class="card">
            <h4>Lots:</h4>
            <hr>
            <c:forEach items="${lotDTOList}" var="lotDTO">
                <div class="col-75">
                    <a href="controller?command=lotInfo&lotId=${lotDTO.lot.idLot}">${lotDTO.lot.brand} ${lotDTO.lot.model} ${lotDTO.lot.yearOfIssue} ${lotDTO.lot.price}$</a>
                </div>
                <div class="col-25">
                    <div class="col-50">
                        <a href="controller?command=confirmLot&lotId=${lotDTO.lot.idLot}" class="confirmLink">CONFIRM</a>
                    </div>
                    <div class="col-50">
                        <a href="controller?command=refuseLot&lotId=${lotDTO.lot.idLot}" class="refuseLink">REFUSE</a>
                    </div>
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