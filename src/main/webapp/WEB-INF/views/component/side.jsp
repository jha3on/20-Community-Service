<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<head>
    <link href="/resources/css/component/side.css" rel="stylesheet">
    <script src="/resources/js/component/side.js" type="text/javascript"></script>
</head>

<spring:url var="homeUri" value="/"/>
<spring:url var="joinUri" value="/user/join"/>
<spring:url var="loginUri" value="/user/login"/>
<spring:url var="editUri" value="/user/edit"/>
<spring:url var="profileUri" value="/user/profile"/>
<spring:url var="logoutUri" value="/user/logout"/>
<spring:url var="boardUri" value="/board/post/list"/>
<spring:url var="chatUri" value="/chat/room/list"/>

<div id="s_menu" class="control-center">
    <ul class="s_menuBottomSidebar">
        <sec:authorize access="isAnonymous()">
            <li class="btn-sm btn-round" data-toggle="tooltip" data-placement="left" title="홈 가기" onclick="window.location.href='${homeUri}'"><i class="fas fa-angle-double-down"></i></li>
            <li class="btn-sm btn-round" data-toggle="tooltip" data-placement="left" title="게시판 가기" onclick="window.location.href='${boardUri}'"><i class="fas fa-angle-down"></i></li>
        </sec:authorize>
        <sec:authorize access="isAuthenticated()">
            <li class="btn-sm btn-round" data-toggle="tooltip" data-placement="left" title="게시판 가기" onclick="window.location.href='${boardUri}'"><i class="fas fa-angle-double-down"></i></li>
            <li class="btn-sm btn-round" data-toggle="tooltip" data-placement="left" title="채팅방 가기" onclick="window.location.href='${chatUri}'"><i class="fas fa-angle-down"></i></li>
        </sec:authorize>
    </ul>
    <div id="s_menuButton" class="btn btn-neutral btn-icon btn-round option-btn" data-toggle="tooltip" data-placement="left" title="퀵 메뉴" onclick="quickMenu()"></div>
    <ul class="s_menuTopSidebar">
        <sec:authorize access="isAnonymous()">
            <li class="btn-sm btn-round" data-toggle="tooltip" data-placement="left" title="계정 로그인" onclick="window.location.href='${loginUri}'"><i class="fas fa-angle-up"></i></li>
            <li class="btn-sm btn-round" data-toggle="tooltip" data-placement="left" title="계정 등록" onclick="window.location.href='${joinUri}'"><i class="fas fa-angle-double-up"></i></li>
        </sec:authorize>
        <sec:authorize access="isAuthenticated()">
            <li class="btn-sm btn-round" data-toggle="tooltip" data-placement="left" title="계정 로그아웃" onclick="window.location.href='${logoutUri}'"><i class="fas fa-angle-up"></i></li>
            <li class="btn-sm btn-round" data-toggle="tooltip" data-placement="left" title="계정 프로필" onclick="window.location.href='${profileUri}'"><i class="fas fa-angle-double-up"></i></li>
        </sec:authorize>
    </ul>
</div>

<%-- 퀵 이동 버튼 --%>
<div id="s_menuQuick">
    <button id="s_menuTopButton" class="btn btn-neutral btn-icon btn-round" onclick="goTop()"><i class="fas fa-chevron-up"></i></button>
    <button id="s_menuBottomButton" class="btn btn-neutral btn-icon btn-round" onclick="goBottom()"><i class="fas fa-chevron-down"></i></button>
</div>