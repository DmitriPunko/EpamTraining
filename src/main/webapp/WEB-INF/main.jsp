<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false" %>


<html lang="en">
<head>
    <link rel="stylesheet" type="text/css" href="../style/mainStyle.css">
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

<div class="card">
    <div class="row">
        <div class="column side">
            <table id="lots">
                <tr>
                    <th>Photo</th>
                    <th>Lot#</th>
                    <th>Brand</th>
                    <th>Model</th>
                    <th>Current Price</th>
                </tr>
                <c:forEach items="${lotDTOList}" var="lotDTO">
                    <tr>
                        <td>
                            <div>
                                <c:if test="${lotDTO.photos.size() != 0}">
                                    <img src="../${lotDTO.photos.get(0).url}" alt="lot photo" width="200px">
                                </c:if>
                            </div>

                            <div>
                                <a href="controller?command=lotInfo&lotId=${lotDTO.lot.idLot}">Lot info</a>
                            </div>
                        </td>
                        <td><c:out value="${ lotDTO.lot.idLot }"/></td>
                        <td><c:out value="${ lotDTO.lot.brand }"/></td>
                        <td><c:out value="${ lotDTO.lot.model }"/></td>
                        <td>
                            <div><c:out value="${ lotDTO.lot.price }"/></div>
                            <div>
                                <div class="bidButton">
                                    <a href="/controller?command=lotInfo&lotId=${lotDTO.lot.idLot}">BID NOW</a>
                                </div>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <div class="column middle">
            <div class="vehicleFinderHeader">Vehicle Finder</div>
            <form action="" class="vehicleFinder" method="get">
                <input type="hidden" name="command" value="findLots">
                <div class="row">
                    <div class="col-25">
                        <label for="auction_type">Auction type</label>
                    </div>
                    <div class="col-75">
                        <select id="auction_type" name="auction_type">
                            <option value="All">All</option>
                            <option value="direct">Direct (classical)</option>
                            <option value="reverse">Reverse</option>
                        </select>
                    </div>
                </div>

                <div class="row">
                    <div class="col-25">
                        <label for="brand">Brand</label>
                    </div>
                    <div class="col-75">
                        <select id="brand" name="brand">
                            <option value="All">All</option>
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
                        <label for="year_of_issue_from">Year</label>
                    </div>
                    <div class="col-30">
                        <select id="year_of_issue_from" name="year_of_issue_from">
                            <option value="All">All</option>
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
                        <label for="year_of_issue_to">To</label>
                    </div>
                    <div class="col-30">
                        <select id="year_of_issue_to" name="year_of_issue_to">
                            <option value="All">All</option>
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
                        <label for="is_damaged">Damaged</label>
                    </div>
                    <div class="col-75">
                        <select id="is_damaged" name="is_damaged">
                            <option value="All">All</option>
                            <option value="1">Yes</option>
                            <option value="0">No</option>
                        </select>
                    </div>
                </div>

                <div class="findButtonDiv">
                    <input type="submit" value="FIND" id="findButton">
                </div>
            </form>
        </div>
    </div>
</div>

<footer>
    <h4>CONTACT US: enauct@gmail.com</h4>
</footer>

</body>
</html>