<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@ page isELIgnored="false" %>

<fmt:setBundle basename="messages"/>
<fmt:setLocale value="${param.lang}"/>


<!DOCTYPE html>
<html lang="${param.lang}">
<head>
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>
    <title><fmt:message key="main.page" /></title>
    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" />
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <style>
        @media only screen and (min-width: 768px) {
            .dropdown:hover .dropdown-menu {
                display: block;
                margin-top: 0;
            }
        }
    </style>

</head>
<body>

    <nav id="myNAV" class="navbar navbar-expand-lg navbar-light bg-light fixed-top">
        <a class="navbar-brand" href="${contextPath}/"><fmt:message key="main" /></a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target=".navbar-collapse" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item dropdown">
                    <div class="btn-group">
                        <button type="button" class="btn"><fmt:message key="locale" /></button>
                        <button type="button" class="btn dropdown-toggle dropdown-toggle-split" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <span class="sr-only">Toggle Dropdown</span>
                        </button>
                        <div class="dropdown-menu">
                            <a class="dropdown-item" href="${contextPath}/?lang=en"><fmt:message key="english" /></a>
                            <a class="dropdown-item" href="${contextPath}/?lang=ru"><fmt:message key="russian" /></a>
                        </div>
                    </div>
                </li>
                <li class="nav-item active">
                    <a class="nav-link" href="${contextPath}/login"><fmt:message key="login.title" /> <span class="sr-only"></span></a>
                </li>
                <li class="nav-item active">
                    <a class="nav-link" href="${contextPath}/registration"><fmt:message key="login.create.account" /> <span class="sr-only"></span></a>
                </li>
            </ul>
        </div>
    </nav>

<div class="container" style="margin-top: 70px">
    <h1><fmt:message key="main.message" /></h1>
</div>

</body>
</html>