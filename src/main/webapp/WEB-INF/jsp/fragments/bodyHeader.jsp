<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<nav class="navbar navbar-dark bg-dark py-0">
    <div class="container">
        <sec:authorize access="isAuthenticated()">
            <form class="form-inline my-2">
                <a class="btn btn-primary" href="logout">
                    <span class="fa fa-sign-out"></span>
                </a>
            </form>
        </sec:authorize>
        <sec:authorize access="isAnonymous()">
            <form class="form-inline my-2" id="login_form" action="spring_security_check" method="post">
                <input class="form-control mr-1" type="text" placeholder="Email" name="username">
                <input class="form-control mr-1" type="password" placeholder="Password" name="password">
                <button class="btn btn-success" type="submit">
                    <span class="fa fa-sign-in"></span>
                </button>
            </form>
        </sec:authorize>
    </div>
</nav>
