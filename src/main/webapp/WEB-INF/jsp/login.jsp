<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>

<div class="container">
    <sec:authorize access="isAnonymous()">
        <div align="right">
            <a href="oauth/vk/authorize" role="button" class="btn btn-block btn-social btn-vk m-2" style="max-width: 250px">
                <span class="fa fa-vk"></span> Sign in with VK
            </a>
        </div>
    </sec:authorize>
</div>

<div class="container lead">
    <h2>Задача: </h2>
    <p class="text-justify pl-5">Необходимо сделать веб приложение, на выбранном вами языке, при открытии должно
        показать кнопку
        «авторизоваться» по нажатию делает oauth авторизацию ВКонтакте, и показывает имя авторизованного пользователя и
        5 любых друзей пользователя. При последующих запусках/заходах на страницу сразу показывает всю информацию т.к.
        уже
        понимает, что авторизовано и авторизация запоминается. Бекенд если потребуется, на любой технологии на ваш
        выбор.</p>
    <h2>Для решения данной задачи были применены следующие технологии:</h2>
    <ul class="text-justify pl-5">
        <li>Spring Security</li>
        <li>Spring MVC</li>
        <li>Hibernate ORM</li>
        <li>JSP</li>
        <li>Apache Tomcat</li>
        <li>WebJars</li>
        <li>PostgreSQL</li>
        <li>jQuery</li>
        <li>Bootstrap</li>
    </ul>
</div>
<footer class="container">
    <div class="row">
        <div class="col-lg justify-content-lg-center">
            <div class="align-content-lg-center" align="center">
                <p class="lead">
                    Воронин Петр | <a href="https://gatchina.hh.ru/applicant/resumes/view?resume=01542232ff041823d20039ed1f376a414a4177">Резюме</a>
                </p>
            </div>
        </div>
    </div>
</footer>
</body>
</html>