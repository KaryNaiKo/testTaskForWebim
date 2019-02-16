<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<nav class="navbar navbar-dark bg-dark py-0">
    <div class="container">
        <sec:authorize access="isAuthenticated()">
            <p class="text-light bg-dark text-left">Здравствуй, ${userTo.firstName}!</p>
            <form class="form-inline my-2">
                <a class="btn btn-primary" href="logout">
                    <span class="fa fa-sign-out"></span>
                </a>
            </form>
        </sec:authorize>
    </div>
</nav>