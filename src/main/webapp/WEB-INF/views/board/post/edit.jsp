<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<head>
    <title>게시글 수정</title>
    <link href="/resources/css/board/post/newAndEdit.css" rel="stylesheet">
    <script src="/resources/js/board/post/newAndEdit.js" type="text/javascript"></script>
</head>

<spring:url var="postEditUri" value="/board/post/${id}/edit" />
<spring:url var="postUri" value="/board/post/${id}" />

<div id="c_postEditContainer" class="container c_container-xl">
    <div class="container c_headerLine">
    </div>
    <div class="container c_header">
        <h5>게시글 수정</h5>
        <p>제목과 내용을 작성해주세요.</p>
    </div>

    <div id="c_postEdit" class="container-lg">
        <form:form method="post" modelAttribute="post" class="post" action="${postEditUri}" novalidate="true">
            <div class="materialGroup">

                <div class="form-group">
                    <form:input id="title" name="title" path="title" type="text" value="${post.title}" required="required" maxlength="100" onKeyup="titleCheck()"/>
                    <span class="highlight"></span>
                    <span class="bar"></span>
                    <label for="title">* 게시글 제목 <span id="titleCounter"> (0 / 100자)</span></label>
                    <span id="titleCheck"></span>
                </div>

                <div class="form-group">
                    <span id="typeLabel">* 게시글 카테고리</span>
                    <form:select id="type" class="form-control" path="postType">
                        <form:option value="NOTICE" label="공지사항"/>
                        <form:option value="EVENT" label="이벤트"/>
                        <form:option value="TALK" label="잡담"/>
                    </form:select>
                </div>

                <div class="form-group">
                    <span id="contentLabel">* 게시글 내용</span>
                    <form:textarea id="froala-editor" name="content" class="form-control" path="content" value="${post.content}"/>
                </div>
            </div>

            <jsp:include page="/WEB-INF/views/component/bootstrapAlertMessage.jsp">
                <jsp:param name="title" value="수정"/>
            </jsp:include>

            <button id="c_postEditCompleteButton" type="submit" class="btn btn-neutral"><i class="fas fa-angle-right"></i> 수정</button>
            <button id="c_postEditCancelButton" type="button" class="btn btn-neutral btn-heart" onclick="location.href='${postUri}'"><i class="fas fa-angle-right"></i> 취소</button>
        </form:form>
    </div>

    <div id="c_postEditFormFooter" class="container">
        <%-- 태그 목록--%>
    </div>
</div>