<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="text" var="text"/>

<fmt:message bundle="${text}" key="header.button.signOut" var="sign_out"/>
<fmt:message bundle="${text}" key="header.button.profile" var="profile"/>
<fmt:message bundle="${text}" key="header.button.offerALot" var="offer_a_lot"/>

<fmt:message bundle="${text}" key="footer.helpInfo" var="contact_us"/>

<fmt:message bundle="${text}" key="main.table.header.photo" var="photo"/>
<fmt:message bundle="${text}" key="main.table.header.currentPrice" var="current_price"/>
<fmt:message bundle="${text}" key="main.table.header.lot" var="lot"/>
<fmt:message bundle="${text}" key="main.table.header.model" var="model"/>
<fmt:message bundle="${text}" key="main.table.header.photo" var="photo"/>
<fmt:message bundle="${text}" key="main.table.lotInfo" var="lot_info"/>
<fmt:message bundle="${text}" key="main.table.bidButton" var="bid_button"/>
<fmt:message bundle="${text}" key="main.finder.header.vehicleFinder" var="vehicle_finder"/>
<fmt:message bundle="${text}" key="main.finder.parameter.auctionType" var="auction_type"/>
<fmt:message bundle="${text}" key="main.finder.parameter.auctionType.option.all" var="all"/>
<fmt:message bundle="${text}" key="main.finder.parameter.auctionType.option.direct" var="direct"/>
<fmt:message bundle="${text}" key="main.finder.parameter.auctionType.option.reverse" var="reverse"/>
<fmt:message bundle="${text}" key="main.finder.parameter.brand" var="brand"/>
<fmt:message bundle="${text}" key="main.finder.parameter.brand.option.all" var="all"/>
<fmt:message bundle="${text}" key="main.finder.parameter.year" var="year"/>
<fmt:message bundle="${text}" key="main.finder.parameter.year.option.All" var="all"/>
<fmt:message bundle="${text}" key="main.finder.parameter.year.to" var="to"/>
<fmt:message bundle="${text}" key="main.finder.parameter.damaged" var="damaged"/>
<fmt:message bundle="${text}" key="main.finder.parameter.damaged.option.all" var="all"/>
<fmt:message bundle="${text}" key="main.finder.parameter.damaged.option.yes" var="yes"/>
<fmt:message bundle="${text}" key="main.finder.parameter.damaged.option.no" var="no"/>
<fmt:message bundle="${text}" key="main.finder.button.find" var="find"/>



<html lang="${sessionScope.language}">
<head>
    <link rel="stylesheet" type="text/css" href="../style/mainStyle.css">
    <meta charset="UTF-8">
    <title>ENAUCT</title>
</head>
<body>

<header>
    <a href="controller?command=main" style="float:left">ENAUCT</a>
    <a href="controller?command=language&language=${sessionScope.nextLanguage}" style="float:right">${sessionScope.nextLanguage}</a>
    <a href="controller?command=signOut">${sign_out}</a>
    <a href="controller?command=profile">${profile}</a>
    <a href="controller?command=offerALotPage">${offer_a_lot}</a>
</header>

<div class="card">
    <div class="row">
        <div class="column side">
            <table id="lots">
                <tr>
                    <th>${photo}</th>
                    <th>${lot}#</th>
                    <th>${brand}</th>
                    <th>${model}</th>
                    <th>${current_price}$</th>
                </tr>
                <c:forEach items="${lotDtoList}" var="lotDto">
                    <tr>
                        <td>
                            <div>
                                <c:if test="${lotDto.photos.size() != 0}">
                                    <img src="../${lotDto.photos.get(0).url}" alt="lot photo" width="200px">
                                </c:if>
                            </div>

                            <div>
                                <a href="controller?command=lotInfo&lotId=${lotDto.lot.idLot}">${lot_info}</a>
                            </div>
                        </td>
                        <td><c:out value="${ lotDto.lot.idLot }"/></td>
                        <td><c:out value="${ lotDto.lot.brand }"/></td>
                        <td><c:out value="${ lotDto.lot.model }"/></td>
                        <td>
                            <div><c:out value="${ lotDto.lot.price }$"/></div>
                            <div>
                                <div class="bidButton">
                                    <a href="controller?command=lotInfo&lotId=${lotDto.lot.idLot}">${bid_button}</a>
                                </div>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <div class="column middle">
            <div class="vehicleFinderHeader">${vehicle_finder}</div>
            <form action="" class="vehicleFinder" method="get">
                <input type="hidden" name="command" value="findLots">
                <div class="row">
                    <div class="col-25">
                        <label for="auction_type">${auction_type}</label>
                    </div>
                    <div class="col-75">
                        <select id="auction_type" name="auction_type">
                            <option value="All">${all}</option>
                            <option value="direct">${direct}</option>
                            <option value="reverse">${reverse}</option>
                        </select>
                    </div>
                </div>

                <div class="row">
                    <div class="col-25">
                        <label for="brand">${brand}</label>
                    </div>
                    <div class="col-75">
                        <select id="brand" name="brand">
                            <option value="All">${all}</option>
                            <option value="Audi">Audi</option>
                            <option value="Bentley">Bentley</option>
                            <option value="BMW">BMW</option>
                            <option value="Mercedes">Mercedes</option>
                            <option value="Rolls Royce">Rolls Royce</option>
                            <option value="Jaguar">Jaguar</option>
                        </select>
                    </div>
                </div>

                <div class="row">
                    <div class="col-25">
                        <label for="year_of_issue_from">${year}</label>
                    </div>
                    <div class="col-30">
                        <select id="year_of_issue_from" name="year_of_issue_from">
                            <option value="All">${all}</option>
                            <option value="2018">2018</option>
                            <option value="2017">2017</option>
                            <option value="2016">2016</option>
                            <option value="2015">2015</option>
                            <option value="2014">2014</option>
                            <option value="2013">2013</option>
                            <option value="2012">2012</option>
                            <option value="2011">2011</option>
                            <option value="2010">2010</option>
                            <option value="2009">2009</option>
                            <option value="2008">2008</option>
                            <option value="2007">2007</option>
                            <option value="2006">2006</option>
                            <option value="2005">2005</option>
                            <option value="2004">2004</option>
                            <option value="2003">2003</option>
                            <option value="2002">2002</option>
                            <option value="2001">2001</option>
                            <option value="2000">2000</option>
                            <option value="1999">1999</option>
                            <option value="1998">1998</option>
                            <option value="1997">1997</option>
                            <option value="1996">1996</option>
                            <option value="1995">1995</option>
                            <option value="1994">1994</option>
                            <option value="1993">1993</option>
                            <option value="1992">1992</option>
                            <option value="1991">1991</option>
                            <option value="1990">1990</option>
                            <option value="1980">1980</option>
                            <option value="1970">1970</option>
                            <option value="1960">1960</option>
                            <option value="1950">1950</option>
                            <option value="1940">1940</option>
                            <option value="1930">1930</option>
                            <option value="1920">1920</option>
                        </select>
                    </div>
                    <div class="col-15">
                        <label for="year_of_issue_to">${to}</label>
                    </div>
                    <div class="col-30">
                        <select id="year_of_issue_to" name="year_of_issue_to">
                            <option value="All">${all}</option>
                            <option value="2018">2018</option>
                            <option value="2017">2017</option>
                            <option value="2016">2016</option>
                            <option value="2015">2015</option>
                            <option value="2014">2014</option>
                            <option value="2013">2013</option>
                            <option value="2012">2012</option>
                            <option value="2011">2011</option>
                            <option value="2010">2010</option>
                            <option value="2009">2009</option>
                            <option value="2008">2008</option>
                            <option value="2007">2007</option>
                            <option value="2006">2006</option>
                            <option value="2005">2005</option>
                            <option value="2004">2004</option>
                            <option value="2003">2003</option>
                            <option value="2002">2002</option>
                            <option value="2001">2001</option>
                            <option value="2000">2000</option>
                            <option value="1999">1999</option>
                            <option value="1998">1998</option>
                            <option value="1997">1997</option>
                            <option value="1996">1996</option>
                            <option value="1995">1995</option>
                            <option value="1994">1994</option>
                            <option value="1993">1993</option>
                            <option value="1992">1992</option>
                            <option value="1991">1991</option>
                            <option value="1990">1990</option>
                            <option value="1980">1980</option>
                            <option value="1970">1970</option>
                            <option value="1960">1960</option>
                            <option value="1950">1950</option>
                            <option value="1940">1940</option>
                            <option value="1930">1930</option>
                            <option value="1920">1920</option>
                        </select>
                    </div>
                </div>


                <div class="row">
                    <div class="col-25">
                        <label for="is_damaged">${damaged}</label>
                    </div>
                    <div class="col-75">
                        <select id="is_damaged" name="is_damaged">
                            <option value="All">${all}</option>
                            <option value="1">${yes}</option>
                            <option value="0">${no}</option>
                        </select>
                    </div>
                </div>

                <div class="findButtonDiv">
                    <input type="submit" value="${find}" id="findButton">
                </div>
            </form>
        </div>
    </div>
</div>

<footer>
    <h4>${contact_us}: enauct@gmail.com</h4>
</footer>

</body>
</html>