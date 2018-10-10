<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" type="text/css" href="../style/userProfileStyle.css">
    <meta charset="UTF-8">
    <title>ENAUCT</title>
</head>
<body>

<header>
    <a href="/controller?command=main" style="float:left">ENAUCT</a>
    <a href="#">RU</a>
    <a href="/controller?command=signOut">SIGN OUT</a>
    <a href="/controller?command=profile">PROFILE</a>
    <a href="/controller?command=offerALotPage">OFFER A LOT</a>
</header>


<div class = "container">

    <div class="leftcolumn">
        <div class="card">
            <a href="/controller?command=userLots" class="lotsLink">LOTS</a>
        </div>
    </div>

    <div class="rightcolumn">
        <div class="card">
            <h4>Personal data:</h4>
            <jsp:useBean id="user" type="com.epam.auction.model.User" scope="request"/>
            <div class="col-25">
                <label for="firstName">First name</label>
            </div>
            <div class="col-75">
                <input type="text" id="firstName" name="firstName" value="${user.firstName}" readonly>
            </div>


            <div class="col-25">
                <label for="lastName">Last name</label>
            </div>
            <div class="col-75">
                <input type="text" id="lastName" name="lastName" value="${user.lastName}" readonly>
            </div>

            <div class="col-25">
                <label for="username">Username</label>
            </div>
            <div class="col-75">
                <input type="text" id="username" name="username" value="${user.userName}" readonly>
            </div>

            <div class="col-25">
                <label for="email">Email</label>
            </div>
            <div class="col-75">
                <input type="text" id="email" name="email"  value="${user.email}" readonly>
            </div>

        </div>
    </div>
</div>

<footer>
    <h4>CONTACT US: enauct@gmail.com</h4>
</footer>

</body>
</html>