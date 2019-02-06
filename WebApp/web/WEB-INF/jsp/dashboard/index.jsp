<jsp:include page="../inc/header.jsp" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:setLocale value="en_IE"/>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!-- Page top section -->
<section class="page-top-section set-bg" data-setbg="assets/img/page-top-bg.jpg">
        <div class="container text-white">
                <h2>Dashboard</h2>
                <h5>${message}</h5>
        </div>
</section>
<section class="page-section blog-page">
<div class="container">
    <div class="row">
        <div class="col-lg-12 mt-4">
            <h2>Vendors</h2>
        </div>
        
        <c:forEach items="${vendors}" var="vendor">
                <div class="col-lg-4 col-md-6 mt-4">
                                <!-- feature -->
                                <div id="${property.id}" class="feature-item" style="border-style: solid; border-color: #ebebeb; border-width: 1px 0 0 0">
                                        <div class="feature-text">
                                                <div class="text-center feature-title">
                                                        <h5>${vendor.name}</h5>
                                                        <p><i class="fa fa-map-marker"></i> ${vendor.address}</p>
                                                </div>
                                                <div class="room-info-warp">
                                                        <div class="room-info">
                                                                <div class="rf-left">
                                                                        <p><i class="fa fa-envelope"></i> ${vendor.email}</p>
                                                                </div>
                                                                <div class="rf-right">
                                                                        <p><i class="fa fa-phone"></i> ${vendor.phone}</p>
                                                                </div>	
                                                        </div>
                                                </div>
                                                <a href="EditVendor?id=${vendor.id}" class="room-price"><i class="fa fa-edit"></i></a>
                                                </div>
                                </div>
                        </div>
            </c:forEach> 
    </div>
    <hr>
    <div class="row">
        <div class="col-lg-12 mt-4">
            <div class="feature-item" style="border-style: solid; border-color: #ebebeb; border-width: 1px 0 1px 0">
                                        
                                        <div class="feature-text">
                                                <div class="text-center feature-title">
                                                        <h2>Properties</h2>
                                                </div>
                                            <div class="col-lg-6 mt-4 mb-4" style="margin:auto">
                                                <a href="AddProperty" class="room-price"><i class="fa fa-plus-circle"></i>   Add Property</a>
                                            </div>  

                                            </div>
                                </div>
        </div>
        
        <c:forEach items="${properties}" var="property">
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
                                                <a href="DeleteProperty?id=${property.id}" class="fav"><i class="fa fa-trash"></i></a>
                                                <a href="EditProperty?id=${property.id}" class="room-price"><i class="fa fa-edit"></i></a>
                                                </div>
                                </div>
                        </div>
            </c:forEach> 
    </div>
</div>
</section>
<!-- page end --> 


<jsp:include page="../inc/footer.jsp" />