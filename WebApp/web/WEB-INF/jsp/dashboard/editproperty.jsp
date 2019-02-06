<jsp:include page="../inc/header.jsp" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:setLocale value="en_IE"/>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!-- Page top section -->
<section class="page-top-section set-bg" data-setbg="assets/img/page-top-bg.jpg">
        <div class="container text-white">
                <h2>Edit A Property</h2>
        </div>
</section>
<!--  Page top end -->
<!-- page -->
<section class="page-section blog-page">
<div class="container">
    <div class="row">
        <div class="col-lg-12">
            <div class="contact-right">
                    <form enctype="multipart/form-data" action="EditProperty" class="contact-form" method="POST">
                            <div class="row">
                                <div class="offset-md-3 col-md-6">
                                        <input type="textarea" name="street" placeholder="Street" value="${property.street}" required>
                                </div>
                            </div>
                            <div class="row">
                                <div class="offset-md-3 col-md-3">
                                        <input type="text" name="city" placeholder="City" value="${property.city}" required>
                                </div>
                                <div class="col-md-3">
                                        <input type="text" name="listing" placeholder="Listing Number" value="${property.listingNum}" readonly>
                                </div>
                            </div>
                        <div class="row">
                                <div class="offset-md-3 col-md-3">
                                    Style Type: 
                                    <select name="style">
                                       <c:forEach items="${styletypes}" var="styletype">
                                                <option <c:if test="${styletype.getStyleId() == property.styleId}">selected</c:if> value="${styletype.getStyleId()}">${styletype.getPStyle()}</option>
                                            </c:forEach> 
                                    </select>
                                </div>
                                <div class="col-md-3">
                                    Property Type: 
                                        <select name="type">
                                            <c:forEach items="${propertytypes}" var="propertytype">
                                                <option <c:if test="${propertytype.getTypeId() == property.typeId}">selected</c:if> value="${propertytype.getTypeId()}">${propertytype.getPType()}</option>
                                            </c:forEach> 
                                    </select>
                                </div>
                            </div>
                        <br>
                        <div class="row">
                                <div class="offset-md-3 col-md-3">
                                        <input type="number" name="bedrooms" value="${property.bedrooms}" placeholder="Number of Bedrooms" required>
                                </div>
                                <div class="col-md-3">
                                        <input type="text" name="bathrooms" value="${property.bathrooms}" placeholder="Number of Bathrooms" required>
                                </div>
                            </div>
                        <div class="row">
                                <div class="offset-md-3 col-md-3">
                                        <input type="number" name="squarefeet" value="${property.squarefeet}" placeholder="Square Feet" required>
                                </div>
                                <div class="col-md-3">
                                        BER Rating:
                                        <select name="ber">
                                            <%-- Can be done better with a for and list --%>
                                            <option <c:if test="${property.berRating == 'A1'}">selected</c:if> value="A1">A1</option>
                                            <option <c:if test="${property.berRating == 'A2'}">selected</c:if> value="A2">A2</option>
                                            <option <c:if test="${property.berRating == 'A3'}">selected</c:if> value="A3">A3</option>
                                            <option <c:if test="${property.berRating == 'B1'}">selected</c:if> value="B1">B1</option>
                                            <option <c:if test="${property.berRating == 'B2'}">selected</c:if> value="B2">B2</option>
                                            <option <c:if test="${property.berRating == 'B3'}">selected</c:if> value="B3">B3</option>
                                            <option <c:if test="${property.berRating == 'C1'}">selected</c:if> value="C1">C1</option>
                                            <option <c:if test="${property.berRating == 'C2'}">selected</c:if> value="C2">C2</option>
                                            <option <c:if test="${property.berRating == 'C3'}">selected</c:if> value="C3">C3</option>
                                            <option <c:if test="${property.berRating == 'D1'}">selected</c:if> value="D1">D1</option>
                                            <option <c:if test="${property.berRating == 'D2'}">selected</c:if> value="D2">D2</option>
                                            <option <c:if test="${property.berRating == 'E1'}">selected</c:if> value="E1">E1</option>
                                            <option <c:if test="${property.berRating == 'E2'}">selected</c:if> value="E2">E2</option>
                                            <option <c:if test="${property.berRating == 'F'}">selected</c:if> value="F">F</option>
                                            <option <c:if test="${property.berRating == 'G'}">selected</c:if> value="G">G</option>
                                    </select>
                                </div>
                            </div>
                        <div class="row">
                                <div class="offset-md-3 col-md-6">
                                        <input type="textarea" name="description" value="${property.description}" placeholder="Description" required>
                                </div>
                            </div>
                        <div class="row">
                                <div class="offset-md-3 col-md-3">
                                        <input type="text" name="garagesize" value="${property.garagesize}" placeholder="Garage Size" required>
                                </div>
                                <div class="col-md-3">
                                        Garage Type:
                                        <select name="gtype">
                                            <c:forEach items="${garagetypes}" var="garagetype">
                                                <option <c:if test="${garagetype.getGarageId() == property.garageId}">selected</c:if> value="${garagetype.getGarageId()}">${garagetype.getGType()}</option>
                                            </c:forEach> 
                                        </select>
                                </div>
                            </div>
                        <div class="row">
                                <div class="offset-md-3 col-md-3">
                                        <input type="number" name="price" placeholder="Price" value="${property.price}" required>
                                </div>
                            <div class="col-md-3">
                                        Vendor:
                                        <select name="vendor">
                                             <c:forEach items="${vendors}" var="vendor">
                                                <option <c:if test="${vendor.getId() == property.vendorId}">selected</c:if> value="${vendor.getId()}">${vendor.getId()} | ${vendor.getName()}</option>
                                            </c:forEach> 
                                    </select>
                                </div>
                            </div>
                                <div class="row">
                                <div class="offset-md-3 col-md-6">
                                    <p><strong>Note</strong>: If new images are provided, the current images are deleted.</p>
                                </div>
                            </div>
                        <div class="row">
                            <div class="offset-md-3 col-md-3">
                                <input type="text" name="lotsize" placeholder="Lot Size" value="${property.lotsize}" required>
                                </div>
                                <div class="col-md-3">
                                    <input type="file" name="file" type="file" multiple>
                                </div>
                            </div>
                                
                        <div class="row">
                            <div class="offset-md-3 col-md-6">
                                <input type="hidden" name="id" value="${property.id}"/>
                                <input type="hidden" name="action" value="edit"/>
                                <input class="site-btn" value="Edit Property" type="submit"/>
                            </div>
                        </div>
                                
                    </form>
            </div>
        </div>
    </div>
</div>
</section>
<!-- page end --> 
        
<jsp:include page="../inc/footer.jsp" />