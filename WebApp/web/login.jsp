<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<shiro:guest>
<jsp:include page="WEB-INF/jsp/inc/header.jsp" />
<!-- Page top section -->
<section class="page-top-section set-bg" data-setbg="assets/img/page-top-bg.jpg">
        <div class="container text-white">
                <h2>Login</h2>
        </div>
</section>
<!--  Page top end -->
<!-- page -->
<section class="page-section blog-page">
<div class="container">
    <div class="row">
        <div class="col-lg-12">
            <div class="contact-right">
                    <form name="loginform" class="contact-form" method="post">
                        <div class="row">
                                    <div class="offset-md-3 col-md-6 ">
                                        <label class="offset-md-3">
                                            <c:if test="${shiroLoginFailure != null}">
                                                Oops!, invalid email or password provided.
                                            </c:if>
                                        </label>
                                    </div>
                            </div>
                            <div class="row">
                                    <div class="offset-md-3 col-md-6 ">
                                            <input type="email" placeholder="Email" name="userEmail" autofocus="True">
                                    </div>
                            </div>
                        <div class="row">
                                    <div class="offset-md-3 col-md-6">
                                            <input type="password" name="userPassword" placeholder="Password">
                                    </div>
                            </div>
                        <div class="row">
                            <div class="offset-md-4 col-md-4">
                                <input class="site-btn" value="Login" type="submit"/>
                            </div>
                        </div>
                    </form>
            </div>
        </div>
    </div>
</div>
</section>
<!-- page end --> 
        
<jsp:include page="WEB-INF/jsp/inc/footer.jsp" />
</shiro:guest>