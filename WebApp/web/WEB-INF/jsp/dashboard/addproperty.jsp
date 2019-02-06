<jsp:include page="../inc/header.jsp" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:setLocale value="en_IE"/>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!-- Page top section -->
<section class="page-top-section set-bg" data-setbg="assets/img/page-top-bg.jpg">
        <div class="container text-white">
                <h2>Add a Property</h2>
        </div>
</section>
<!--  Page top end -->
<!-- page -->
<section class="page-section blog-page">
<div class="container">
    <div class="row">
        <div class="col-lg-12">
            <div class="contact-right">
                    <form enctype="multipart/form-data" action="AddProperty" class="contact-form" method="post">
                            <div class="row">
                                <div class="offset-md-3 col-md-6">
                                        <input type="textarea" name="street" placeholder="Street" required>
                                </div>
                            </div>
                            <div class="row">
                                <div class="offset-md-3 col-md-3">
                                        <input type="text" name="city" placeholder="City" required>
                                </div>
                                <div class="col-md-3">
                                        <input type="text" name="listing" placeholder="Listing Number" required>
                                </div>
                            </div>
                        <div class="row">
                                <div class="offset-md-3 col-md-3">
                                    Style Type: 
                                    <select name="style">
                                       <c:forEach items="${styletypes}" var="styletype">
                                                <option value="${styletype.getStyleId()}">${styletype.getPStyle()}</option>
                                            </c:forEach> 
                                    </select>
                                </div>
                                <div class="col-md-3">
                                    Property Type: 
                                        <select name="type">
                                            <c:forEach items="${propertytypes}" var="propertytype">
                                                <option value="${propertytype.getTypeId()}">${propertytype.getPType()}</option>
                                            </c:forEach> 
                                    </select>
                                </div>
                            </div>
                        <br>
                        <div class="row">
                                <div class="offset-md-3 col-md-3">
                                        <input type="number" name="bedrooms" placeholder="Number of Bedrooms" required>
                                </div>
                                <div class="col-md-3">
                                        <input type="text" name="bathrooms" placeholder="Number of Bathrooms" required>
                                </div>
                            </div>
                        <div class="row">
                                <div class="offset-md-3 col-md-3">
                                        <input type="number" name="squarefeet" placeholder="Square Feet" required>
                                </div>
                                <div class="col-md-3">
                                        BER Rating:
                                        <select name="ber">
                                            <option value="A1">A1</option>
                                            <option value="A2">A2</option>
                                            <option value="A3">A3</option>
                                            <option value="B1">B1</option>
                                            <option value="B2">B2</option>
                                            <option value="B3">B3</option>
                                            <option value="C1">C1</option>
                                            <option value="C2">C2</option>
                                            <option value="C3">C3</option>
                                            <option value="D1">D1</option>
                                            <option value="D2">D2</option>
                                            <option value="E1">E1</option>
                                            <option value="E2">E2</option>
                                            <option value="F">F</option>
                                            <option value="G">G</option>
                                    </select>
                                </div>
                            </div>
                        <div class="row">
                                <div class="offset-md-3 col-md-6">
                                        <input type="textarea" name="description" placeholder="Description" required>
                                </div>
                            </div>
                        <div class="row">
                                <div class="offset-md-3 col-md-3">
                                        <input type="text" name="garagesize" placeholder="Garage Size" required>
                                </div>
                                <div class="col-md-3">
                                        Garage Type:
                                        <select name="gtype">
                                            <c:forEach items="${garagetypes}" var="garagetype">
                                                <option value="${garagetype.getGarageId()}">${garagetype.getGType()}</option>
                                            </c:forEach> 
                                        </select>
                                </div>
                            </div>
                        <div class="row">
                                <div class="offset-md-3 col-md-3">
                                        <input type="number" name="price" placeholder="Price" required>
                                </div>
                            <div class="col-md-3">
                                        Vendor:
                                        <select name="vendor">
                                             <c:forEach items="${vendors}" var="vendor">
                                                <option value="${vendor.getId()}">${vendor.getId()} | ${vendor.getName()}</option>
                                            </c:forEach> 
                                    </select>
                                </div>
                            </div>
                        <div class="row">
                            <div class="offset-md-3 col-md-3">
                                <input type="text" name="lotsize" placeholder="Lot Size" required>
                                </div>
                                <div class="col-md-3">
                                    <input type="file" name="file" type="file" multiple required>
                                </div>
                            </div>
                        <div class="row">
                            <div class="offset-md-3 col-md-6">
                                <input type="hidden" name="action" value="add"/>
                                <input class="site-btn" value="Add Property" type="submit"/>
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