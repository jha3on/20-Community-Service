<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<head>
</head>

<spring:url var="gitUri" value="https://github.com/sonnjh"/>

<footer id="e_footer" class="footer footer-big footer-white fixed-bottom">
    <div class="container" style="text-align: center">
        Copyright &copy; 2020
        <a href="${gitUri}" target="_blank">GitHub(sonn.jh)</a> All Rights Reserved.
    </div>
</footer>