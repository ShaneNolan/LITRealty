<jsp:include page="inc/header.jsp" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:setLocale value="en_IE"/>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!-- Page top section -->
<section class="page-top-section set-bg" data-setbg="assets/img/page-top-bg.jpg">
        <div class="container text-white">
                <h2>We found ${searched_properties.size()}
                <c:out default="None" escapeXml="true" value="${searched_properties.size() == 1 ? 'property' : 'properties'}" />
                </h2>
        </div>
</section>
<!--  Page top end -->

<!-- Breadcrumb -->
<div class="site-breadcrumb">
        <div class="container">
                <a href="Home"><i class="fa fa-home"></i>Home</a>
                <span><i class="fa fa-angle-right"></i>Search Result</span>
        </div>
</div>


<!-- page -->
<section class="page-section categories-page">
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <div style="padding-bottom: 20px"><i class="fa fa-search"></i> Quick Search</div>
            <table id="propertyTable" class="table display">
                <thead>
                    <tr>
                        <th>View</th>
                        <th>City</th>
                        <th>Street</th>
                        <th>Bedrooms</th>
                        <th>Bathrooms</th>
                        <th>Price</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${searched_properties}" var="property">
                        <tr>
                            <td><a href="#${property.id}"><i class="fa fa-eye"></i></a></td>
                            <td>${property.city}</td>
                            <td>${property.street}</td>
                            <td>${property.bedrooms}</td>
                            <td>${property.bathrooms}</td>
                            <td><fmt:formatNumber value="${property.price}" type="currency"/></td>
                        </tr>
                    </c:forEach> 
                </tbody>
            </table>
            </div>
        </div>
    </div>
        <div style="padding-top: 25px" class="container">
            <div class="row">
            <c:forEach items="${searched_properties}" var="property">
                <div class="col-lg-4 col-md-6">
                                <!-- feature -->
                                <div id="${property.id}" class="feature-item">
                                        <div onclick="location.href='Property?id=${property.id}';" style="cursor:pointer;" class="feature-pic set-bg" data-setbg="assets/img/properties/large/${property.listingNum}/${property.photo}">
                                            
                                        </div>
                                        
                                        <div class="feature-text">
                                                <div class="text-center feature-title">
                                                        <h5>${property.street}</h5>
                                                        <p><i class="fa fa-map-marker"></i> ${property.city}</p>
                                                </div>
                                                <div class="room-info-warp">
                                                        <div class="room-info">
                                                                <div class="rf-left">
                                                                        <p><i class="fa fa-th-large"></i> ${property.squarefeet} Square Foot</p>
                                                                        <p><i class="fa fa-bed"></i> ${property.bedrooms} Bedrooms</p>
                                                                </div>
                                                                <div class="rf-right">
                                                                        <p><i class="fa fa-car"></i> ${property.garagesize} Garages</p>
                                                                        <p><i class="fa fa-bath"></i> ${property.bathrooms} Bathrooms</p>
                                                                </div>	
                                                        </div>
                                                        <div class="room-info">
                                                                <shiro:user>
                                                                <div class="rf-left">
                                                                    
                                                                        <p><i class="fa fa-user"></i>
                                                                            
                                                                            <c:forEach items="${vendors}" var="vendor">
                                                                                <c:if test="${vendor.getId() == property.vendorId}">
                                                                                    ${vendor.getName()}
                                                                                </c:if>
                                                                            </c:forEach>
                                                                        </p>
                                                                </div>
                                                            </shiro:user>
                                                                <div class="rf-right">
                                                                        <p><i class="fa fa-clock-o"></i> <fmt:formatDate type = "date" value = "${property.dateAdded}" /></p>
                                                                </div>	
                                                        </div>
                                                </div>
                                                <a href="Favourite?action=add&id=${property.id}" class="fav"><i class="fa fa-heart"></i></a>
                                                <a href="Property?id=${property.id}" class="room-price"><fmt:formatNumber value="${property.price}" type="currency"/></a>
                                                </div>
                                </div>
                        </div>
            </c:forEach> 
                </div>
            <!--
                <div class="site-pagination">
                        <span>1</span>
                        <a href="#">2</a>
                        <a href="#">3</a>
                        <a href="#"><i class="fa fa-angle-right"></i></a>
                </div>
            -->
        </div>
</section>
<!-- page end -->
<jsp:include page="inc/footer.jsp" />