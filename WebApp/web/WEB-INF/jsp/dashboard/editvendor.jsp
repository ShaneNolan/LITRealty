<jsp:include page="../inc/header.jsp" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:setLocale value="en_IE"/>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:if test="${empty vendor}">
    <c:redirect url = "Dashboard"/>
</c:if>
<!-- Page top section -->
<section class="page-top-section set-bg" data-setbg="assets/img/page-top-bg.jpg">
        <div class="container text-white">
                <h2>Edit ${vendor.name}'s Details</h2>
        </div>
</section>
<!--  Page top end -->
<!-- page -->
<section class="page-section blog-page">
<div class="container">
    <div class="row">
        <div class="col-lg-12">
            <div class="contact-right">
                    <form action="EditVendor" class="contact-form" method="post">
                        <div class="row">
                            </div>
                        <div class="row">
                                    <div class="offset-md-3 col-md-6 ">
                                            <input type="text" value="${vendor.name}" placeholder="Name" name="vendorname" autofocus="True">
                                    </div>
                            </div>
                            <div class="row">
                                    <div class="offset-md-3 col-md-6 ">
                                            <input type="email" value="${vendor.email}" placeholder="Email" name="vendoremail">
                                    </div>
                            </div>
                        <div class="row">
                                    <div class="offset-md-3 col-md-6">
                                            <input type="text" value="${vendor.phone}" name="vendorphone" placeholder="Vendor Phone">
                                    </div>
                            </div>
                                    <div class="row">
                                    <div class="offset-md-3 col-md-6">
                                            <input type="textarea" value="${vendor.address}" name="vendoraddress" placeholder="Vendor Address">
                                    </div>
                            </div>
                        <div class="row">
                            <div class="offset-md-4 col-md-4">
                                <input type="hidden" name="action" value="edit"/>
                                <input type="hidden" name="id" value="${vendor.id}"/>
                                <input class="site-btn" value="Edit Vendor" type="submit"/>
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