<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" type="text/css" href="../style/lotInfoStyle.css">
    <meta charset="UTF-8">
    <title>ENACUT</title>
</head>
<body>

<header>
    <a href="controller?command=main" style="float:left">ENAUCT</a>
    <a href="#">RU</a>
    <a href="controller?command=signOut">SIGN OUT</a>
    <a href="controller?command=profile">PROFILE</a>
    <a href="controller?command=offerALotPage">OFFER A LOT</a>
</header>

<div class="card">
    <jsp:useBean id="lotDTO" scope="request" type="com.epam.auction.model.dto.LotDTO"/>

    <div class="col-25 container">
        <div class="lotHeader">Bargaining</div>
        <c:if test="${lotDTO.lot.status == 'CONFIRMED'}">
            <div class="record">Participants:</div>
            <div>
                <ol>
                    <c:forEach items="${biddersList}" var="bidder">
                    <li>${bidder.firstName} ${bidder.lastName}</li>
                    </c:forEach>
                </ol>
            </div>
            <div class="record" style="margin-bottom: 10px">Current Bid: 150$</div>

            <div class="bidButtonDiv">
                <a href="" class="bidButton">BID NOW</a>
            </div>
        </c:if>
    </div>


    <div class="col-75 container">
        <div class="lotHeader">Lot info</div>
        <div class="leftcolumn">
            <div class="row">
                <div class="col-25">
                    <label for="auctionType">Auction type</label>
                </div>
                <div class="col-75">
                    <input type="text" id="auctionType" name="auctionType" value="${lotDTO.lot.auctionType.value}"
                           readonly>
                </div>
            </div>

            <div class="row">
                <div class="col-25">
                    <label for="brand">Brand</label>
                </div>
                <div class="col-75">
                    <input type="text" id="brand" name="brand" value="${lotDTO.lot.brand}" readonly>
                </div>
            </div>

            <div class="row">
                <div class="col-25">
                    <label for="model">Model</label>
                </div>
                <div class="col-75">
                    <input type="text" id="model" name="model" value="${lotDTO.lot.model}" readonly>
                </div>
            </div>

            <div class="row">
                <div class="col-25">
                    <label for="class">Class</label>
                </div>
                <div class="col-75">
                    <input type="text" id="class" name="class" value="${lotDTO.lot.classOfLot}" readonly>
                </div>
            </div>

            <div class="row">
                <div class="col-25">
                    <label for="color">Color</label>
                </div>
                <div class="col-75">
                    <input type="text" id="color" name="color" value="${lotDTO.lot.colorEnum.value}" readonly>
                </div>
            </div>

            <div class="row">
                <div class="col-25">
                    <label for="engineVolume">Engine Volume</label>
                </div>
                <div class="col-75">
                    <input type="text" id="engineVolume" name="engineVolume" value="${lotDTO.lot.engineVolume}"
                           readonly>
                </div>
            </div>

            <div class="row">
                <div class="col-25">
                    <label for="yearOfIssue">Year</label>
                </div>
                <div class="col-75">
                    <input type="text" id="yearOfIssue" name="yearOfIssue" value="${lotDTO.lot.yearOfIssue}" readonly>
                </div>
            </div>

            <div class="row">
                <div class="col-25">
                    <label for="isDamaged">Damaged</label>
                </div>
                <div class="col-75">
                    <input type="text" id="isDamaged" name="isDamaged" value="${lotDTO.lot.damaged}" readonly>
                </div>
            </div>


            <div class="row">
                <div class="col-25">
                    <label for="price">Price</label>
                </div>
                <div class="col-75">
                    <input type="text" id="price" name="price" value="${lotDTO.lot.price}" readonly>
                </div>
            </div>

            <div class="row">
                <div class="col-25">
                    <label for="dateOfStart">Date of start</label>
                </div>
                <div class="col-75">
                    <input type="text" id="dateOfStart" name="dateOfStart" value="${lotDTO.lot.dateOfStart}" readonly>
                </div>
            </div>

            <div class="row">
                <div class="col-25">
                    <label for="dateOfEnd">Date of end</label>
                </div>
                <div class="col-75">
                    <input type="text" id="dateOfEnd" name="dateOfEnd" value="${lotDTO.lot.dateOfEnd}" readonly>
                </div>
            </div>
        </div>


        <div class="rightcolumn">
            <div class="row">
                <c:forEach items="${lotDTO.photos}" var="photo">
                    <a rel="nofollow" target="_blank" href="../${photo.url}">
                        <img src="../${photo.url}" width="210px" alt="photo">
                    </a>
                </c:forEach>
            </div>
        </div>

    </div>

</div>
</div>

<footer>
    <h4>CONTACT US: enauct@gmail.com</h4>
</footer>

</body>
</html>