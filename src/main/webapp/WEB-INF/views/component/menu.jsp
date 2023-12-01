<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<head>
    <link href="/resources/css/component/menu.css" rel="stylesheet">
    <script src="/resources/js/component/menu.js" type="text/javascript"></script>
</head>

<spring:url var="postListUri" value="/board/post/list"/>
<spring:url var="roomListUri" value="/chat/room/list"/>
<spring:url var="errorUri" value="/error/404"/>
<spring:url var="adminUri" value="/admin/home"/>

<div id="m_menu" class="container">
    <ul class="nav justify-content-center">
        <li class="p-2">
            <button type="button" class="btn btn-neutral" data-toggle="tooltip" data-placement="bottom" title="게시판"><a href="${postListUri}">게시판 기능</a></button>
        </li>
        <li class="p-2">
            <button type="button" class="btn btn-neutral" data-toggle="tooltip" data-placement="bottom" title="채팅"><a href="${roomListUri}">채팅 기능</a></button>
        </li>
    </ul>
</div>