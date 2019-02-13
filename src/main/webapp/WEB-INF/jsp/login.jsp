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
        <div class="lead py-4">Стек технологий: <br><a href="http://projects.spring.io/spring-security/">Spring
            Security</a>,
            <a href="https://docs.spring.io/spring/docs/current/spring-framework-reference/html/mvc.html">Spring MVC</a>,
            <a href="http://projects.spring.io/spring-data-jpa/">Spring Data JPA</a>,
            <a href="http://spring.io/blog/2014/05/07/preview-spring-security-test-method-security">Spring Security
                Test</a>,
            <a href="http://hibernate.org/orm/">Hibernate ORM</a>,
            <a href="http://hibernate.org/validator/">Hibernate Validator</a>,
            <a href="http://www.slf4j.org/">SLF4J</a>,
            <a href="https://github.com/FasterXML/jackson">Json Jackson</a>,
            <a href="http://ru.wikipedia.org/wiki/JSP">JSP</a>,
            <a href="http://en.wikipedia.org/wiki/JavaServer_Pages_Standard_Tag_Library">JSTL</a>,
            <a href="http://tomcat.apache.org/">Apache Tomcat</a>,
            <a href="http://www.webjars.org/">WebJars</a>,
            <a href="http://datatables.net/">DataTables plugin</a>,
            <a href="http://ehcache.org">EHCACHE</a>,
            <a href="http://www.postgresql.org/">PostgreSQL</a>,
            <a href="http://junit.org/">JUnit</a>,
            <a href="http://hamcrest.org/JavaHamcrest/">Hamcrest</a>,
            <a href="http://jquery.com/">jQuery</a>,
            <a href="http://ned.im/noty/">jQuery notification</a>,
            <a href="http://getbootstrap.com/">Bootstrap</a>.
        </div>
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