<%@ page import="com.vh.activitymanage.model.enums.Role" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ page import="com.vh.activitymanage.model.enums.Permission" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@ page isELIgnored="false" %>

<fmt:setBundle basename="messages"/>
<fmt:setLocale value="${param.lang}" />


<!DOCTYPE html>
<html lang="${param.lang}">
<head>
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>
    <title><fmt:message key="welcome.page" /></title>
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
    <a class="navbar-brand" href="${contextPath}/welcome"><fmt:message key="main" /></a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target=".navbar-collapse" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="${contextPath}/activity/all"><fmt:message key="activity.page" /> <span class="sr-only"></span></a>
            </li>
            <%boolean hasUserRole = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                    .anyMatch(r -> r.getAuthority().equalsIgnoreCase(Permission.WRITE.getPermission()));
            if(hasUserRole) { %>
            <li class="nav-item dropdown">
                <div class="btn-group">
                    <button type="button" class="btn"><fmt:message key="dashboard.page"/></button>
                    <button type="button" class="btn dropdown-toggle dropdown-toggle-split" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <span class="sr-only">Toggle Dropdown</span>
                    </button>
                    <div class="dropdown-menu">
                        <a class="dropdown-item" href="${contextPath}/admin/category/all"><fmt:message key="category.page"/></a>
                        <a class="dropdown-item" href="${contextPath}/admin/user/all"><fmt:message key="user.page"/></a>
                        <a class="dropdown-item" href="${contextPath}/admin/activity/confirm"><fmt:message key="confirm.activity"/></a>
                        <a class="dropdown-item" href="${contextPath}/admin/activity/all"><fmt:message key="activity.page"/></a>
                    </div>
                </div>
            </li>
            <% } %>
            <li class="nav-item dropdown">
                <div class="btn-group">
                    <button type="button" class="btn"><fmt:message key="locale" /></button>
                    <button type="button" class="btn dropdown-toggle dropdown-toggle-split" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <span class="sr-only">Toggle Dropdown</span>
                    </button>
                    <div class="dropdown-menu">
                        <a class="dropdown-item" href="${contextPath}/welcome/?lang=en"><fmt:message key="english" /></a>
                        <a class="dropdown-item" href="${contextPath}/welcome/?lang=ru"><fmt:message key="russian" /></a>
                    </div>
                </div>
            </li>
        </ul>
        <form id="logoutForm" method="POST" action="${contextPath}/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <a onclick="document.forms['logoutForm'].submit()"><fmt:message key="logout"/></a>
        </form>
    </div>
</nav>

<div class="container" style="margin-top: 70px">
    <h1><fmt:message key="welcome.message" /> ${pageContext.request.userPrincipal.name}</h1>
</div>

</body>
</html>