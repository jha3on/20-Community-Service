<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<head>
    <title>홈</title>
    <link href="/resources/css/home.css" rel="stylesheet">
</head>

<spring:url var="boardUri" value="/board/post/list"/>
<spring:url var="chatUri" value="/chat/room/list"/>
<spring:url var="joinUri" value="/user/consent"/>
<spring:url var="loginUri" value="/user/login"/>
<spring:url var="logoutUri" value="/user/logout"/>
<spring:url var="profileUri" value="/user/profile"/>

<div id="c_home" class="container">
    <div class="row">
        <div class="col-md-4 col-xl-3">
            <a class="btn btn-primary btn-block" href="${boardUri}">
                <strong>게시판</strong>
            </a>
        </div>
        <div class="col-md-4 col-xl-3">
            <a class="btn btn-primary btn-block" href="${chatUri}">
                <strong>채팅</strong>
            </a>
        </div>

        <sec:authorize access="isAnonymous()">
        <div class="col-md-4 col-xl-3">
            <a class="btn btn-primary btn-block" href="${joinUri}">
                <strong>계정 등록</strong>
            </a>
        </div>
        <div class="col-md-4 col-xl-3">
            <a class="btn btn-primary btn-block" href="${loginUri}">
                <strong>계정 로그인</strong>
            </a>
        </div>
        </sec:authorize>

        <sec:authorize access="isAuthenticated()">
        <div class="col-md-4 col-xl-3">
            <a class="btn btn-primary btn-block" href="${logoutUri}">
                <strong>계정 로그아웃</strong>
            </a>
        </div>
        <div class="col-md-4 col-xl-3">
            <a class="btn btn-primary btn-block" href="${profileUri}">
                <strong>내 프로필</strong>
            </a>
        </div>
        </sec:authorize>
        </div>
    </div>
</div>