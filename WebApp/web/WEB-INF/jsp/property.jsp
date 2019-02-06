<jsp:include page="inc/header.jsp" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:setLocale value="en_IE"/>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!-- Page top section -->
<section class="page-top-section set-bg" data-setbg="assets/img/page-top-bg.jpg">
        <div class="container text-white">
                <h2>PROPERTY LISTING</h2>
        </div>
</section>
<!--  Page top end -->

<!-- Breadcrumb -->
<div class="site-breadcrumb">
        <div class="container">
                <a href="Home"><i class="fa fa-home"></i>Home</a>
                <span><i class="fa fa-angle-right"></i>Property</span>
        </div>
</div>

<!-- Page -->
<section class="page-section">
        <div class="container">
                <div class="row">
                        <div class="col-lg-8 single-list-page">
                                <div class="single-list-slider owl-carousel" id="sl-slider">
                                    <c:forEach items="${images}" var="image">
                                        <div class="sl-item set-bg" data-setbg="assets/img/properties/large/${property.listingNum}/${image}">
                                                <!--<div class="rent-notic">//</div>-->
                                        </div>
                                    </c:forEach>
                                </div>
                                <div class="owl-carousel sl-thumb-slider" id="sl-slider-thumb">
                                    <c:forEach items="${images}" var="image">
                                        <div class="sl-thumb set-bg" data-setbg="assets/img/properties/large/${property.listingNum}/${image}"></div>
                                    </c:forEach>
                                </div>
                                <div class="single-list-content">
                                        <div class="row">
                                                <div class="col-xl-8 sl-title">
                                                        <h2>${property.street}</h2>
                                                        <p><i class="fa fa-map-marker"></i>${property.city}</p>
                                                </div>
                                                <div class="col-xl-4">
                                                        <span class="price-btn"><fmt:formatNumber value="${property.price}" type="currency"/></span>
                                                </div>
                                        </div>
                                        <h3 class="sl-sp-title">Property Details</h3>
                                        <div class="row property-details-list">
                                                <div class="col-md-4 col-sm-6">
                                                        <p><i class="fa fa-th-large"></i> ${property.squarefeet} Square foot</p>
                                                        <p><i class="fa fa-bed"></i> ${property.bedrooms} Bedrooms</p>
                                                        <p><i class="fa fa-building"></i> ${style_types.getPStyle()}</p>
                                                </div>
                                                <div class="col-md-4 col-sm-6">
                                                        <p><i class="fa fa-car"></i> ${property.garagesize} Garages | ${garage_types.getGType()}</p>
                                                        <p><i class="fa fa-building-o"></i> ${property_type.getPType()}</p>
                                                        <p><i class="fa fa-clock-o"></i> <fmt:formatDate type = "date" value = "${property.dateAdded}" /></p>
                                                </div>
                                                <div class="col-md-4">
                                                        <p><i class="fa fa-bath"></i> ${property.bathrooms} Bathrooms</p>
                                                        <p><i class="fa fa-trophy"></i> ${property.berRating} Ber Rating</p>
                                                        <shiro:user>
                                                                        <p><i class="fa fa-user"></i>
                                                                            
                                                                            <c:forEach items="${vendors}" var="vendor">
                                                                                <c:if test="${vendor.getId() == property.vendorId}">
                                                                                    ${vendor.getName()}
                                                                                </c:if>
                                                                            </c:forEach>
                                                                        </p>
                                                            </shiro:user>
                                                </div>
                                        </div>
                                        <!--<h3 class="sl-sp-title">Description</h3>
                                        <div class="description">
                                                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus egestas fermentum ornareste. Donec index lorem. Vestibulum  aliquet odio, eget tempor libero. Cras congue cursus tincidunt. Nullam venenatis dui id orci egestas tincidunt id elit. Nullam ut vuputate justo. Integer lacnia pharetra pretium. Casan ante ipsum primis in faucibus orci luctus et ultrice.</p>
                                                </div>
                                        <h3 class="sl-sp-title">Property Details</h3>
                                        <div class="row property-details-list">
                                                <div class="col-md-4 col-sm-6">
                                                        <p><i class="fa fa-check-circle-o"></i> Air conditioning</p>
                                                        <p><i class="fa fa-check-circle-o"></i> Telephone</p>
                                                        <p><i class="fa fa-check-circle-o"></i> Laundry Room</p>
                                                </div>
                                                <div class="col-md-4 col-sm-6">
                                                        <p><i class="fa fa-check-circle-o"></i> Central Heating</p>
                                                        <p><i class="fa fa-check-circle-o"></i> Family Villa</p>
                                                        <p><i class="fa fa-check-circle-o"></i> Metro Central</p>
                                                </div>
                                                <div class="col-md-4">
                                                        <p><i class="fa fa-check-circle-o"></i> City views</p>
                                                        <p><i class="fa fa-check-circle-o"></i> Internet</p>
                                                        <p><i class="fa fa-check-circle-o"></i> Electric Range</p>
                                                </div>
                                        </div>
                                        <h3 class="sl-sp-title bd-no">Floorplans</h3>
                                        <div id="accordion" class="plan-accordion">
                                                <div class="panel">
                                                        <div class="panel-header" id="headingOne">
                                                                <button class="panel-link active" data-toggle="collapse" data-target="#collapse1" aria-expanded="false" aria-controls="collapse1">First Floor: <span>660 sq ft</span>	<i class="fa fa-angle-down"></i></button>
                                                        </div>
                                                        <div id="collapse1" class="collapse show" aria-labelledby="headingOne" data-parent="#accordion">
                                                                <div class="panel-body">
                                                                        <img src="img/plan-sketch.jpg" alt="">
                                                                </div>
                                                        </div>
                                                </div>
                                                <div class="panel">
                                                        <div class="panel-header" id="headingTwo">
                                                                <button class="panel-link" data-toggle="collapse" data-target="#collapse2" aria-expanded="true" aria-controls="collapse2">Second Floor:<span>610 sq ft.</span>	<i class="fa fa-angle-down"></i>
                                                                </button>
                                                        </div>
                                                        <div id="collapse2" class="collapse" aria-labelledby="headingTwo" data-parent="#accordion">
                                                                <div class="panel-body">
                                                                        <img src="img/plan-sketch.jpg" alt="">
                                                                </div>
                                                        </div>
                                                </div>
                                                <div class="panel">
                                                        <div class="panel-header" id="headingThree">
                                                                <button class="panel-link" data-toggle="collapse" data-target="#collapse3" aria-expanded="false" aria-controls="collapse3">Third Floor :<span>580 sq ft</span>	<i class="fa fa-angle-down"></i>
                                                                </button>
                                                        </div>
                                                        <div id="collapse3" class="collapse" aria-labelledby="headingThree" data-parent="#accordion">
                                                                <div class="panel-body">
                                                                        <img src="img/plan-sketch.jpg" alt="">
                                                                </div>
                                                        </div>
                                                </div>
                                        </div>

                                        <h3 class="sl-sp-title bd-no">Video</h3>
                                        <div class="perview-video">
                                                <img src="img/video.jpg" alt="">
                                                <a href="https://www.youtube.com/watch?v=v13nSVp6m5I" class="video-link"><img src="img/video-btn.png" alt=""></a>
                                        </div>-->
                                        <h3 class="sl-sp-title bd-no">Location</h3>
                                        <div class="pos-map" id="map-canvas">
                                            <iframe width="680" id="map-canvas" src="https://maps.google.com/maps?q=${property.street} ${property.city}&t=&z=13&ie=UTF8&iwloc=&output=embed" frameborder="0" scrolling="no" marginheight="0" marginwidth="0"></iframe>
                                        </div>
                                </div>
                        </div>
                        <!-- sidebar -->
                        <div class="col-lg-4 col-md-7 sidebar">
                                <div class="author-card">
                                        <div class="author-img set-bg" data-setbg="img/author.jpg"></div>
                                        <div class="author-info">
                                                <h5>Gina Wesley</h5>
                                                <p>Real Estate Agent</p>
                                        </div>
                                        <div class="author-contact">
                                                <p><i class="fa fa-phone"></i>(567) 666 121 2233</p>
                                                <p><i class="fa fa-envelope"></i>ginawesley26@gmail.com</p>
                                        </div>
                                </div>
                            <div class="author-card">
                                <h4>Add to your favourites?</h4>
                                <br>
                            <a href="Favourite?action=add&id=${property.id}" class="fav"><i class="fa fa-heart"></i></a>
                            </div>
                            <!--
                                <div class="contact-form-card">
                                        <h5>Do you have any question?</h5>
                                        <form>
                                                <input type="text" placeholder="Your name">
                                                <input type="text" placeholder="Your email">
                                                <textarea placeholder="Your question"></textarea>
                                                <button>SEND</button>
                                        </form>
                                </div>
                                <div class="related-properties">
                                        <h2>Related Property</h2>
                                        <div class="rp-item">
                                                <div class="rp-pic set-bg" data-setbg="img/feature/1.jpg">
                                                        <div class="sale-notic">FOR SALE</div>
                                                </div>
                                                <div class="rp-info">
                                                        <h5>1963 S Crescent Heights Blvd</h5>
                                                        <p><i class="fa fa-map-marker"></i>Los Angeles, CA 90034</p>
                                                </div>
                                                <a href="#" class="rp-price">$1,200,000</a>
                                        </div>
                                        <div class="rp-item">
                                                <div class="rp-pic set-bg" data-setbg="img/feature/2.jpg">
                                                        <div class="rent-notic">FOR Rent</div>
                                                </div>
                                                <div class="rp-info">
                                                        <h5>17 Sturges Road, Wokingham</h5>
                                                        <p><i class="fa fa-map-marker"></i> Newtown, CT 06470</p>
                                                </div>
                                                <a href="#" class="rp-price">$2,500/month</a>
                                        </div>
                                        <div class="rp-item">
                                                <div class="rp-pic set-bg" data-setbg="img/feature/4.jpg">
                                                        <div class="sale-notic">FOR SALE</div>
                                                </div>
                                                <div class="rp-info">
                                                        <h5>28 Quaker Ridge Road, Manhasset</h5>
                                                        <p><i class="fa fa-map-marker"></i>28 Quaker Ridge Road, Manhasset</p>
                                                </div>
                                                <a href="#" class="rp-price">$5,600,000</a>
                                        </div>
                                        <div class="rp-item">
                                                <div class="rp-pic set-bg" data-setbg="img/feature/5.jpg">
                                                        <div class="rent-notic">FOR Rent</div>
                                                </div>
                                                <div class="rp-info">
                                                        <h5>Sofi Berryessa 750 N King Road</h5>
                                                        <p><i class="fa fa-map-marker"></i>Sofi Berryessa 750 N King Road</p>
                                                </div>
                                                <a href="#" class="rp-price">$1,600/month</a>
                                        </div>
                                </div>-->
                        </div>
                </div>
        </div>
</section>
<!-- Page end -->

<jsp:include page="inc/footer.jsp" />