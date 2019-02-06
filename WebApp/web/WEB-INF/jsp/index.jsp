<jsp:include page="inc/header.jsp" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:setLocale value="en_IE"/>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!-- Hero section -->
<section class="hero-section set-bg" data-setbg="assets/img/bg.jpg">
        <div class="container hero-text text-white">
                <h2>helping you find your new home</h2>
                <p>Search real estate property records, houses, condos, land and more on litrealty.ie®.<br>Find property info from all over Ireland.</p>
        </div>
</section>
<!-- Hero section end -->


<!-- Filter form section -->
<div class="filter-search">
    <div class="container" 
         <c:if test="${recommended_properties.size() == 0 || recommended_properties == null}"> style="margin-bottom: -50px"</c:if>
         >
            <div class="offset-md-2 col-lg-8">
                <form class="filter-form" method="GET" action="Search">
                        <!--<input type="text" placeholder="Enter a street name, address number or keyword">-->
                        <select name="place">
                                <option value="Antrim">Antrim</option>
                                <option value="Armagh">Armagh</option>
                                <option value="Carlow">Carlow</option>
                                <option value="Cavan">Cavan</option>
                                <option value="Clare">Clare</option>
                                <option value="Cork">Cork</option>
                                <option value="Derry">Derry</option>
                                <option value="Donegal">Donegal</option>
                                <option value="Down">Down</option>
                                <option value="Dublin">Dublin</option>
                                <option value="Fermanagh">Fermanagh</option>
                                <option value="Galway">Galway</option>
                                <option value="Kerry">Kerry</option>
                                <option value="Kildare">Kildare</option>
                                <option value="Kilkenny">Kilkenny</option>
                                <option value="Laois">Laois</option>
                                <option value="Leitrim">Leitrim</option>
                                <option value="Limerick">Limerick</option>
                                <option value="Longford">Longford</option>
                                <option value="Louth">Louth</option>
                                <option value="Mayo">Mayo</option>
                                <option value="Meath">Meath</option>
                                <option value="Monaghan">Monaghan</option>
                                <option value="Offaly">Offaly</option>
                                <option value="Roscommon">Roscommon</option>
                                <option value="Sligo">Sligo</option>
                                <option value="Tipperary">Tipperary</option>
                                <option value="Tyrone">Tyrone</option>
                                <option value="Waterford">Waterford</option>
                                <option value="Westmeath">Westmeath</option>
                                <option value="Wexford">Wexford</option>
                                <option value="Wicklow">Wicklow</option>
                        </select>
                        <select name="price">
                                <option value="0to50000">€0 - €50,000</option>
                                <option value="50000to100000">€50,000 - €100,000</option>
                                <option value="100000to200000">€100,000 - €200,000</option>
                                <option value="200000to300000">€200,000 - €300,000</option>
                                <option value="300000to400000">€300,000 - €400,000</option>
                                <option value="400000to500000">€400,000 - €500,000</option>
                                <option value="500000to750000">€500,000 - €750,000</option>
                                <option value="750000">€1,000,000 +</option>
                                
                        </select>
                        <button class="site-btn fs-submit">SEARCH</button>
                </form>
            </div>
        </div>
</div>
<!-- Filter form section end -->

<c:if test="${recommended_properties.size() > 0}">
<!-- Properties section -->
<section class="properties-section spad">
        <div class="container">
                <div class="section-title text-center">
                        <h3>RECOMMENDED PROPERTIES</h3>
                        <p>We think you may be interested in the properties below</p>
                </div>
                <div class="row">
                    <c:forEach items="${recommended_properties}" var="property">
                        <div class="col-md-6">
                                <div onclick="location.href='Property?id=${property.id}';" style="cursor:pointer;" class="propertie-item set-bg" data-setbg="assets/img/properties/large/${property.listingNum}/${property.photo}">
                                        <div class="propertie-info text-white">
                                                <div class="info-warp">
                                                        <h5>${property.street}</h5>
                                                        <p><i class="fa fa-map-marker"></i>${property.city}</p>
                                                </div>
                                                <div class="price"><fmt:formatNumber value="${property.price}" type="currency"/></div>
                                        </div>
                                </div>
                        </div>
                    </c:forEach>
                </div>
        </div>
</section>
<!-- Properties section end -->
</c:if>

<!-- Services section -->
<section class="services-section spad set-bg" data-setbg="assets/img/service-bg.jpg">
        <div class="container">
                <div class="row">
                        <div class="col-lg-6">
                                <img src="assets/img/service.jpg" alt="">
                        </div>
                        <div class="col-lg-5 offset-lg-1 pl-lg-0">
                                <div class="section-title text-white">
                                        <h3>OUR SERVICES</h3>
                                        <p>We provide the perfect service for real estate.</p>
                                </div>
                                <div class="services">
                                        <div class="service-item">
                                                <i class="fa fa-comments"></i>
                                                <div class="service-text">
                                                        <h5>Consultant Service</h5>
                                                        <p>In Aenean purus, pretium sito amet sapien denim consectet sed urna placerat sodales magna leo.</p>
                                                </div>
                                        </div>
                                        <div class="service-item">
                                                <i class="fa fa-home"></i>
                                                <div class="service-text">
                                                        <h5>Properties Management</h5>
                                                        <p>In Aenean purus, pretium sito amet sapien denim consectet sed urna placerat sodales magna leo.</p>
                                                </div>
                                        </div>
                                        <div class="service-item">
                                                <i class="fa fa-briefcase"></i>
                                                <div class="service-text">
                                                        <h5>Renting and Selling</h5>
                                                        <p>In Aenean purus, pretium sito amet sapien denim consectet sed urna placerat sodales magna leo.</p>
                                                </div>
                                        </div>
                                </div>
                        </div>
                </div>
        </div>
</section>
<!-- Services section end -->

<jsp:include page="inc/footer.jsp" />