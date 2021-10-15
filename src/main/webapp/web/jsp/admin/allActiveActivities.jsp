<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<fmt:setBundle basename="messages"/>
<fmt:setLocale value="${param.lang}"/>

<!DOCTYPE html>
<html lang="${param.lang}">
<head>
    <meta charset="utf-8">
    <title><fmt:message key="dashboard.page"/></title>

    <link href="${contextPath}/resources/css/bootstrap.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/bootstrap-grid.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/my.css" rel="stylesheet">
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
            <li class="nav-item dropdown">
                <div class="btn-group">
                    <button type="button" class="btn"><fmt:message key="locale" /></button>
                    <button type="button" class="btn dropdown-toggle dropdown-toggle-split" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <span class="sr-only">Toggle Dropdown</span>
                    </button>
                    <div class="dropdown-menu">
                        <a class="dropdown-item" href="${contextPath}/admin/activity/all/?lang=en"><fmt:message key="english" /></a>
                        <a class="dropdown-item" href="${contextPath}/admin/activity/all/?lang=ru"><fmt:message key="russian" /></a>
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

<div class="container" style="margin-top: 55px;">
    <div class="row">
        <div class="col-10">
            <table class="table table-bordered table-hover">
                <thead class="thead-dark">
                <tr>
                    <th><a href="${contextPath}/admin/activity/all?sort=name"> <fmt:message key="activity.name"/><span class="sr-only"></span></th>
                    <th><a href="${contextPath}/admin/activity/all?sort=time"><fmt:message key="activity.time"/><span class="sr-only"></span></th>
                    <th><a href="${contextPath}/admin/activity/all?sort=status"><fmt:message key="activity.status"/><span class="sr-only"></span></th>
                    <th><a href="${contextPath}/admin/activity/all?sort=creator"><fmt:message key="username"/><span class="sr-only"></span></th>
                    <th><a href="${contextPath}/admin/activity/all?sort=userCounter"><fmt:message key="counter"/><span class="sr-only"></span></th>
                    <th><fmt:message key="actions"/></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${activityWaitToActive}" var="activityWaitToActive">
                    <tr>
                        <td><a href="${contextPath}/activity/${activityWaitToActive.id}">${activityWaitToActive.name}<span class="sr-only"></span></td>
                        <td>${activityWaitToActive.time}</td>
                        <td>${activityWaitToActive.status}</td>
                        <td>${activityWaitToActive.creator}</td>
                        <td>${activityWaitToActive.userCounter}</td>
                        <td>
                            <form:form method="DELETE" action="${contextPath}/admin/activity/confirm/${activityWaitToActive.id}/delete"
                                       class="form-signin">
                                <button class="btn btn btn-danger btn-block" type="submit"><fmt:message key="delete"/></button>
                            </form:form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

<script src="${contextPath}/resources/js/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.js"></script>
</body>
</html>