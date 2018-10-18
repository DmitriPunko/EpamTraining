<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="/WEB-INF/tld/fullFilePath.tld" %>,

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="text" var="text"/>

<fmt:message bundle="${text}" key="header.button.signOut" var="sign_out"/>
<fmt:message bundle="${text}" key="header.button.profile" var="profile"/>
<fmt:message bundle="${text}" key="header.button.offerALot" var="offer_a_lot"/>
<fmt:message bundle="${text}" key="header.button.home" var="home"/>

<fmt:message bundle="${text}" key="footer.helpInfo" var="contact_us"/>

<fmt:message bundle="${text}" key="lotInfo.label.auctionType" var="auction_type"/>
<fmt:message bundle="${text}" key="lotInfo.label.participants" var="participants"/>
<fmt:message bundle="${text}" key="lotInfo.label.brand" var="brand"/>
<fmt:message bundle="${text}" key="lotInfo.label.model" var="model"/>
<fmt:message bundle="${text}" key="lotInfo.label.classOfLot" var="class_of_lot"/>
<fmt:message bundle="${text}" key="lotInfo.label.color" var="color"/>
<fmt:message bundle="${text}" key="lotInfo.label.dateOfStart" var="date_of_start"/>
<fmt:message bundle="${text}" key="lotInfo.label.dateOfEnd" var="date_of_end"/>
<fmt:message bundle="${text}" key="lotInfo.label.damaged" var="damaged"/>
<fmt:message bundle="${text}" key="lotInfo.label.engineVolume" var="engine_volume"/>
<fmt:message bundle="${text}" key="lotInfo.label.year" var="year"/>
<fmt:message bundle="${text}" key="lotInfo.label.price" var="price"/>
<fmt:message bundle="${text}" key="lotInfo.lotHeader" var="bargaining"/>
<fmt:message bundle="${text}" key="lotInfo.label.lotInfo" var="lot_info"/>
<fmt:message bundle="${text}" key="lotInfo.label.currentBid" var="current_bid"/>
<fmt:message bundle="${text}" key="lotInfo.button.bid" var="bid"/>
<fmt:message bundle="${text}" key="lotInfo.damaged.yes" var="yes"/>
<fmt:message bundle="${text}" key="lotInfo.damaged.no" var="no"/>

<html lang="${sessionScope.language}">
<head>
    <link rel="stylesheet" type="text/css" href="../style/lotInfoStyle.css">
    <meta charset="UTF-8">
    <title>ENACUT</title>
</head>
<body>

<jsp:useBean id="lotDto" scope="request" type="com.epam.auction.model.dto.LotDto"/>

<header>
    <a href="controller?command=main" style="float:left">${home}</a>
    <a href="controller?command=language&currentPage=lotInfo${lotDto.lot.idLot}&language=${sessionScope.nextLanguage}"
       style="float:right">${sessionScope.nextLanguage}</a>
    <a href="controller?command=signOut">${sign_out}</a>
    <a href="controller?command=profile">${profile}</a>
    <a href="controller?command=offerALotPage">${offer_a_lot}</a>
</header>

<div class="card">

    <div class="col-25 container">
        <div class="lotHeader">${bargaining}</div>
        <c:if test="${lotDto.lot.status == 'CONFIRMED'}">
            <div class="participantsLabel">${participants}:</div>
            <div>
                <ol>
                    <c:forEach items="${biddersList}" var="bidder">
                        <li>${bidder.firstName} ${bidder.lastName}</li>
                    </c:forEach>
                </ol>
            </div>
            <div class="participantsLabel" style="margin-bottom: 10px">${current_bid}: ${lotDto.lot.currentBid}$</div>

            <div class="bidButtonDiv">
                <a href="controller?command=bid&lotId=${lotDto.lot.id}" class="bidButton">${bid}</a>
            </div>
        </c:if>
    </div>


    <div class="col-75 container">
        <div class="lotHeader">${lot_info}</div>
        <div class="leftcolumn">
            <div class="row">
                <div class="col-25">
                    <label for="auctionType">${auction_type}</label>
                </div>
                <div class="col-75">
                    <input type="text" id="auctionType" name="auctionType" value="${lotDto.lot.auctionType.value}"
                           readonly>
                </div>
            </div>

            <div class="row">
                <div class="col-25">
                    <label for="brand">${brand}</label>
                </div>
                <div class="col-75">
                    <input type="text" id="brand" name="brand" value="${lotDto.lot.brand}" readonly>
                </div>
            </div>

            <div class="row">
                <div class="col-25">
                    <label for="model">${model}</label>
                </div>
                <div class="col-75">
                    <input type="text" id="model" name="model" value="${lotDto.lot.model}" readonly>
                </div>
            </div>

            <div class="row">
                <div class="col-25">
                    <label for="class">${class_of_lot}</label>
                </div>
                <div class="col-75">
                    <input type="text" id="class" name="class" value="${lotDto.lot.classOfLot}" readonly>
                </div>
            </div>

            <div class="row">
                <div class="col-25">
                    <label for="color">${color}</label>
                </div>
                <div class="col-75">
                    <input type="text" id="color" name="color" value="${lotDto.lot.colorEnum.value}" readonly>
                </div>
            </div>

            <div class="row">
                <div class="col-25">
                    <label for="engineVolume">${engine_volume}</label>
                </div>
                <div class="col-75">
                    <input type="text" id="engineVolume" name="engineVolume" value="${lotDto.lot.engineVolume}"
                           readonly>
                </div>
            </div>

            <div class="row">
                <div class="col-25">
                    <label for="yearOfIssue">${year}</label>
                </div>
                <div class="col-75">
                    <input type="text" id="yearOfIssue" name="yearOfIssue" value="${lotDto.lot.yearOfIssue}" readonly>
                </div>
            </div>

            <div class="row">
                <div class="col-25">
                    <label for="isDamaged">${damaged}</label>
                </div>
                <div class="col-75">
                    <c:choose>
                        <c:when test="${lotDto.lot.damaged}">
                            <input type="text" id="isDamaged" name="isDamaged" value="${yes}" readonly>
                        </c:when>
                        <c:otherwise>
                            <input type="text" id="isDamaged" name="isDamaged" value="${no}" readonly>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>


            <div class="row">
                <div class="col-25">
                    <label for="price">${price}</label>
                </div>
                <div class="col-75">
                    <input type="text" id="price" name="price" value="${lotDto.lot.price}$" readonly>
                </div>
            </div>

            <div class="row">
                <div class="col-25">
                    <label for="dateOfStart">${date_of_start}</label>
                </div>
                <div class="col-75">
                    <input type="text" id="dateOfStart" name="dateOfStart" value="${lotDto.lot.dateOfStart}" readonly>
                </div>
            </div>

            <div class="row">
                <div class="col-25">
                    <label for="dateOfEnd">${date_of_end}</label>
                </div>
                <div class="col-75">
                    <input type="text" id="dateOfEnd" name="dateOfEnd" value="${lotDto.lot.dateOfEnd}" readonly>
                </div>
            </div>
        </div>


        <div class="rightcolumn">
            <div class="row">
                <c:forEach items="${lotDto.photos}" var="photo">
                    <c:set var="photoName" value="${photo.url}" scope="page"/>
                    <a rel="nofollow" target="_blank" href="${ctg:filePath(photoName)}">
                        <img src="${ctg:filePath(photoName)}" width="210px" alt="photo">
                    </a>
                </c:forEach>
                <%--<c:forEach items="${lotDto.photos}" var="photo">--%>
                <%--<a rel="nofollow" target="_blank" href="../${photo.url}">--%>
                <%--<img src="..${photo.url}" width="210px" alt="photo">--%>
                <%--</a>--%>
                <%--</c:forEach>--%>
            </div>
        </div>

    </div>

</div>

<footer>
    <h4>${contact_us}: enauct@gmail.com</h4>
</footer>

</body>
</html>