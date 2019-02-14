<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>

<div class="jumbotron py-0">
    <div class="container">
        <sec:authorize access="isAnonymous()">
            <div class="pt-4">
                <a href="oauth/vk/authorize" role="button" class="btn btn-block btn-social btn-vk">
                    <span class="fa fa-vk"></span>Sign in with VK
                </a>
            </div>
        </sec:authorize>
    </div>
</div>
<div class="container lead">
    &nbsp;&nbsp;&nbsp;Необходимо сделать веб приложение, на выбранном вами языке, при открытии должно показать кнопку
    «авторизоваться» по нажатию делает oauth авторизацию ВКонтакте, и показывает имя авторизованного пользователя и
    5 любых друзей пользователя. При последующих запусках/заходах на страницу сразу показывает всю информацию т.к. уже
    понимает, что авторизовано и авторизация запоминается. Бекенд если потребуется, на любой технологии на ваш выбор.
</div>
</body>
</html>