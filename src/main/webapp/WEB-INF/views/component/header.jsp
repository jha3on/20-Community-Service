<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<head>
    <link href="/resources/css/component/header.css" rel="stylesheet">
    <script src="/resources/js/component/header.js" type="text/javascript"></script>
</head>

<spring:url var="homeUri" value="/"/>
<spring:url var="joinConsentUri" value="/user/consent"/>
<spring:url var="loginUri" value="/user/login"/>
<spring:url var="logoutUri" value="/user/logout"/>
<spring:url var="profileUri" value="/user/profile"/>

<div id="h_headerProgress">
    <div id="h_headerProgressBar"></div>
</div>

<div class="d-flex bd-highlight mb-3">
    <a class="mr-auto p-3 bd-highlight" href="#" onclick="addFavorite()" data-toggle="tooltip" data-placement="bottom" title="북마크 등록">북마크 +</a>

    <a class="p-3 bd-highlight" href="${homeUri}">홈</a>

    <sec:authorize access="isAnonymous()">
        <%-- 비로그인 --%>
        <a class="p-3 bd-highlight" href="${loginUri}">로그인</a>
        <a class="p-3 bd-highlight" href="${joinConsentUri}">계정 등록</a>
    </sec:authorize>
    <c:if test="${isLoginFailure eq true}">
        <%-- 로그인 실패 --%>
        <a class="p-3 bd-highlight" href="${loginUri}">로그인</a>
        <a class="p-3 bd-highlight" href="${joinConsentUri}">계정 등록</a>
    </c:if>

    <sec:authorize access="isAuthenticated()">
        <%-- 로그인 --%>
        <a class="p-3 bd-highlight" href="${logoutUri}">로그아웃</a>
        <a class="p-3 bd-highlight" href="${profileUri}">계정 프로필</a>
    </sec:authorize>
</div>