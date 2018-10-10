<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" type="text/css" href="../style/offerALotStyle.css">
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
    <div class="container">
        <div class="offerALotHeader">Offer a lot</div>

        <form action="controller?command=offerALot" class="offerALotForm" method="post" enctype="multipart/form-data">

            <div class="row">
                <div class="col-25">
                    <label for="auction_type">Auction type</label>
                </div>
                <div class="col-75">
                    <select id="auction_type" name="auction_type" required>
                        <%--<option value=""/>--%>
                        <option disabled>Choose auction type</option>
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
                    <select id="brand" name="brand" required>
                        <%--<option value=""/>--%>
                        <option disabled>Choose brand</option>
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
                    <label for="model">Model</label>
                </div>
                <div class="col-75">
                    <input type="text" id="model" name="model" required placeholder="Model..">
                </div>
            </div>

            <div class="row">
                <div class="col-25">
                    <label for="year_of_issue">Year</label>
                </div>
                <div class="col-75">
                    <select id="year_of_issue" name="year_of_issue" required>
                        <%--<option value=""/>--%>
                        <option disabled>Choose year</option>
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
                    <label for="class">Class</label>
                </div>
                <div class="col-75">
                    <select id="class" name="class" required>
                        <%--<option value=""/>--%>
                        <option disabled>Choose class</option>
                        <option value="Mini">Mini</option>
                        <option value="Small">Small</option>
                        <option value="Larger">Larger</option>
                        <option value="Executive">Executive</option>
                        <option value="Luxury">Luxury</option>
                        <option value="Sports utility">Sports utility</option>
                        <option value="Multi purpose">Multi purpose</option>
                        <option value="Sport coupés">Sport coupés</option>
                    </select>
                </div>
            </div>

            <div class="row">
                <div class="col-25">
                    <label for="color">Color</label>
                </div>
                <div class="col-75">
                    <select id="color" name="color" required>
                        <%--<option value=""/>--%>
                        <option disabled>Choose color</option>
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
                    <label for="engine_volume">Engine Volume</label>
                </div>
                <div class="col-75">
                    <select id="engine_volume" name="engine_volume" required>
                        <%--<option value=""/>--%>
                        <option disabled>Choose engine volume</option>
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
                    <label for="is_damaged">Damaged</label>
                </div>
                <div class="col-75">
                    <select id="is_damaged" name="is_damaged" required>
                        <%--<option value=""/>--%>
                        <option disabled>Damaged?</option>
                        <option value="true">Yes</option>
                        <option value="false">No</option>
                    </select>
                </div>
            </div>


            <div class="row">
                <div class="col-25">
                    <label for="price">Price</label>
                </div>
                <div class="col-75">
                    <input type="text" id="price" name="price" required placeholder="Price($)..">
                </div>
            </div>

            <div class="row">
                <div class="col-25">
                    <label for="date_of_start">Date of start</label>
                </div>
                <div class="col-75">
                    <input type="datetime-local" id="date_of_start" name="date_of_start" required>
                </div>
            </div>

            <div class="row">
                <%--<form action="controller" method="post">--%>
                <p>Photos:</p>
                <input type="file" name="photo" multiple accept="image/*,image/jpeg" class="photoForm" required>
                <%--<input type="submit" value="UPLOAD">--%>
                <%--</form>--%>
            </div>

            <input type="submit" value="OFFER">
        </form>
    </div>
</div>

<footer>
    <h4>CONTACT US: enauct@gmail.com</h4>
</footer>


</body>
</html>