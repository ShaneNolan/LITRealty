<jsp:include page="../inc/header.jsp" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:setLocale value="en_IE"/>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!-- Page top section -->
<section class="page-top-section set-bg" data-setbg="assets/img/page-top-bg.jpg">
        <div class="container text-white">
                <h2>Delete Property</h2>
        </div>
</section>
<!--  Page top end -->
<!-- page -->
<section class="page-section blog-page">
<div class="container">
    <div class="row">
        <div class="col-lg-12">
            <div class="contact-right">
                    <form action="DeleteProperty" class="contact-form" method="post">
                        <div class="row">
                                    <div class="offset-md-3 col-md-6 text-center">
                                        <h5>Are you sure you want to delete property, ${property.listingNum}?</h5>
                                    </div>
                            </div>
                        <div class="row">
                            <div class="mt-4 offset-md-3 col-md-6">
                                <input type="hidden" name="id" value="${property.id}"/>
                                <input type="hidden" name="action" value="delete"/>
                                <input style="background-color: #EE300E;" class="site-btn" value="Delete Property" type="submit"/>
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