<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="text" var="text"/>

<fmt:message bundle="${text}" key="header.button.signOut" var="sign_out"/>
<fmt:message bundle="${text}" key="header.button.profile" var="profile"/>
<fmt:message bundle="${text}" key="header.button.offerALot" var="offer_a_lot"/>
<fmt:message bundle="${text}" key="header.button.home" var="home"/>

<fmt:message bundle="${text}" key="footer.helpInfo" var="contact_us"/>

<fmt:message bundle="${text}" key="offerALot.label.auctionType" var="auction_type"/>
<fmt:message bundle="${text}" key="offerALot.label.auctionType.choose" var="choose_auction_type"/>
<fmt:message bundle="${text}" key="offerALot.label.brand" var="brand"/>
<fmt:message bundle="${text}" key="offerALot.label.brand.choose" var="choose_brand"/>
<fmt:message bundle="${text}" key="offerALot.label.model" var="model"/>
<fmt:message bundle="${text}" key="offerALot.label.model.placeholder" var="placeholder_model"/>
<fmt:message bundle="${text}" key="offerALot.label.classOfLot" var="class_of_lot"/>
<fmt:message bundle="${text}" key="offerALot.label.classOfLot.choose" var="choose_class_of_lot"/>
<fmt:message bundle="${text}" key="offerALot.label.color" var="color"/>
<fmt:message bundle="${text}" key="offerALot.label.color.choose" var="choose_color"/>
<fmt:message bundle="${text}" key="offerALot.label.dateOfStart" var="date_of_start"/>
<fmt:message bundle="${text}" key="offerALot.label.damaged" var="damaged"/>
<fmt:message bundle="${text}" key="offerALot.label.damaged.choose" var="choose_damaged"/>
<fmt:message bundle="${text}" key="offerALot.label.engineVolume" var="engine_volume"/>
<fmt:message bundle="${text}" key="offerALot.label.engineVolume.choose" var="choose_engine_volume"/>
<fmt:message bundle="${text}" key="offerALot.label.year" var="year"/>
<fmt:message bundle="${text}" key="offerALot.label.year.choose" var="choose_year"/>
<fmt:message bundle="${text}" key="offerALot.label.price" var="price"/>
<fmt:message bundle="${text}" key="offerALot.label.price.placeholder" var="placeholder_price"/>
<fmt:message bundle="${text}" key="offerALot.parameter.auctionType.option.all" var="all"/>
<fmt:message bundle="${text}" key="offerALot.parameter.auctionType.option.direct" var="direct"/>
<fmt:message bundle="${text}" key="offerALot.parameter.auctionType.option.reverse" var="reverse"/>
<fmt:message bundle="${text}" key="offerALot.parameter.damaged.option.yes" var="yes"/>
<fmt:message bundle="${text}" key="offerALot.parameter.damaged.option.no" var="no"/>
<fmt:message bundle="${text}" key="offerALot.label.photos" var="photos"/>
<fmt:message bundle="${text}" key="offerALot.button.offer" var="offer_button"/>
<fmt:message bundle="${text}" key="offerALot.model.error.pattern.message" var="model_error_pattern_message"/>
<fmt:message bundle="${text}" key="offerALot.price.error.pattern.message" var="price_error_pattern_message"/>

<html lang="${sessionScope.language}">
<head>
    <link rel="stylesheet" type="text/css" href="../style/offerALotStyle.css">
    <meta charset="UTF-8">
    <title>ENAUCT</title>
</head>
<body>

<header>
    <a href="controller?command=main" style="float:left">${home}</a>
    <a href="controller?command=language&currentPage=offerALotPage&language=${sessionScope.nextLanguage}" style="float:right">${sessionScope.nextLanguage}</a>
    <a href="controller?command=signOut">${sign_out}</a>
    <a href="controller?command=profile">${profile}</a>
    <a href="controller?command=offerALotPage">${offer_a_lot}</a>
</header>


<div class="card">
    <div class="container">
        <div class="offerALotHeader">${offer_a_lot}</div>

        <form action="controller?command=offerALot" class="offerALotForm" method="post" enctype="multipart/form-data">

            <div class="row">
                <div class="col-25">
                    <label for="auction_type">${auction_type}</label>
                </div>
                <div class="col-75">
                    <select id="auction_type" name="auction_type" required>
                        <option disabled>${choose_auction_type}</option>
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
                    <select id="brand" name="brand" required>
                        <option disabled>${choose_brand}</option>
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
                    <label for="model">${model}</label>
                </div>
                <div class="col-75">
                    <input type="text" id="model" name="model" required placeholder="${model}"
                           pattern="^[a-zA-Z0-9-_\.\s]{1,20}$" title="${model_error_pattern_message}">
                </div>
            </div>

            <div class="row">
                <div class="col-25">
                    <label for="year_of_issue">${year}</label>
                </div>
                <div class="col-75">
                    <select id="year_of_issue" name="year_of_issue" required>
                        <option disabled>${choose_year}</option>
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
                        <option value="1980">1980th</option>
                        <option value="1970">1970th</option>
                        <option value="1960">1960th</option>
                        <option value="1950">1950th</option>
                        <option value="1940">1940th</option>
                        <option value="1930">1930th</option>
                        <option value="1920">1920th</option>
                    </select>
                </div>
            </div>

            <div class="row">
                <div class="col-25">
                    <label for="class">${class_of_lot}</label>
                </div>
                <div class="col-75">
                    <select id="class" name="class" required>
                        <option disabled>${choose_class_of_lot}</option>
                        <option value="Mini">Mini</option>
                        <option value="Small">Small</option>
                        <option value="Larger">Larger</option>
                        <option value="Executive">Executive</option>
                        <option value="Luxury">Luxury</option>
                        <option value="Sports utility">Sports utility</option>
                        <option value="Multi purpose">Multi purpose</option>
                        <option value="Sport coupés">Sport coupes</option>
                        <option value="Sport coupés">Retro</option>
                    </select>
                </div>
            </div>

            <div class="row">
                <div class="col-25">
                    <label for="color">${color}</label>
                </div>
                <div class="col-75">
                    <select id="color" name="color" required>
                        <option disabled>${choose_color}</option>
                        <option value="Red">Red</option>
                        <option value="Orange">Orange</option>
                        <option value="Yellow">Yellow</option>
                        <option value="Green">Green</option>
                        <option value="Blue">Blue</option>
                        <option value="Purple">Purple</option>
                        <option value="White">White</option>
                        <option value="Black">Black</option>
                    </select>
                </div>
            </div>

            <div class="row">
                <div class="col-25">
                    <label for="engine_volume">${engine_volume}</label>
                </div>
                <div class="col-75">
                    <select id="engine_volume" name="engine_volume" required>
                        <option disabled>${choose_engine_volume}</option>
                        <option value="1.0">1.0</option>
                        <option value="1.1">1.1</option>
                        <option value="1.2">1.2</option>
                        <option value="1.3">1.3</option>
                        <option value="1.4">1.4</option>
                        <option value="1.5">1.5</option>
                        <option value="1.6">1.6</option>
                        <option value="1.7">1.7</option>
                        <option value="1.8">1.8</option>
                        <option value="1.9">1.9</option>
                        <option value="2.0">2.0</option>
                        <option value="2.2">2.2</option>
                        <option value="2.4">2.4</option>
                        <option value="2.6">2.6</option>
                        <option value="2.8">2.8</option>
                        <option value="3.0">3.0</option>
                        <option value="3.2">3.2</option>
                        <option value="3.4">3.4</option>
                        <option value="3.6">3.6</option>
                        <option value="3.8">3.8</option>
                        <option value="4.0">4.0</option>
                        <option value="4.2">4.2</option>
                        <option value="4.4">4.4</option>
                        <option value="4.6">4.6</option>
                        <option value="4.8">4.8</option>
                        <option value="5.0">5.0</option>
                    </select>
                </div>
            </div>

            <div class="row">
                <div class="col-25">
                    <label for="is_damaged">${damaged}</label>
                </div>
                <div class="col-75">
                    <select id="is_damaged" name="is_damaged" required>
                        <option disabled>${damaged}?</option>
                        <option value="true">${yes}</option>
                        <option value="false">${no}</option>
                    </select>
                </div>
            </div>


            <div class="row">
                <div class="col-25">
                    <label for="price">${price}</label>
                </div>
                <div class="col-75">
                    <input type="text" id="price" name="price" required placeholder="${placeholder_price}"
                           pattern="^\d+(\.\d{2})?$" title="${price_error_pattern_message}">
                </div>
            </div>

            <div class="row">
                <div class="col-25">
                    <label for="date_of_start">${date_of_start}</label>
                </div>
                <div class="col-75">
                    <input type="datetime-local" id="date_of_start" name="date_of_start" required>
                </div>
            </div>

            <div class="row">
                <p>${photos}:</p>
                <input type="file" name="photo" multiple accept="image/*,image/jpeg" class="photoForm" required lang="${sessionScope.language}">
            </div>

            <input type="submit" value="${offer_button}">
        </form>
    </div>
</div>

<footer>
    <h4>${contact_us}: enauct@gmail.com</h4>
</footer>


</body>
</html>