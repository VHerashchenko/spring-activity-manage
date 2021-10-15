<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ page import="com.vh.activitymanage.model.enums.Permission" %>
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
                        <a class="dropdown-item" href="${contextPath}/admin/activity/confirm/?lang=en"><fmt:message key="english" /></a>
                        <a class="dropdown-item" href="${contextPath}/admin/activity/confirm/?lang=ru"><fmt:message key="russian" /></a>
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
                <caption><fmt:message key="admin.wait.active.table"/></caption>
                <thead class="thead-dark">
                <tr>
                    <th><fmt:message key="activity.name"/></th>
                    <th><fmt:message key="activity.status"/></th>
                    <th><fmt:message key="username"/></th>
                    <th><fmt:message key="actions"/></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${activityWaitToActive}" var="activityWaitToActive">
                    <tr>
                        <td><a href="${contextPath}/activity/${activityWaitToActive.id}">${activityWaitToActive.name}<span class="sr-only"></span></td>
                        <td>${activityWaitToActive.status}</td>
                        <td>${activityWaitToActive.creator}</td>
                        <td>
                            <form:form method="POST" action="${contextPath}/admin/activity/confirm/${activityWaitToActive.id}/active"
                                       class="form-signin">
                                <button class="btn btn btn-danger btn-block" type="submit"><fmt:message key="admin.confirm"/></button>
                            </form:form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

<div class="container" style="margin-top: 55px;">
    <div class="row">
        <div class="col-10">
            <table class="table table-bordered table-hover">
                <caption><fmt:message key="admin.wait.delete.table"/></caption>
                <thead class="thead-dark">
                <tr>
                    <th><fmt:message key="activity.name"/></th>
                    <th><fmt:message key="activity.status"/></th>
                    <th><fmt:message key="username"/></th>
                    <th><fmt:message key="actions"/></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${activityWaitToDelete}" var="activityWaitToDelete">
                    <tr>
                        <td><a href="${contextPath}/activity/${activityWaitToDelete.id}">${activityWaitToDelete.name}<span class="sr-only"></span></td>
                        <td>${activityWaitToDelete.status}</td>
                        <td>${activityWaitToDelete.creator}</td>
                        <td>
                            <form:form method="DELETE" action="${contextPath}/admin/activity/confirm/${activityWaitToDelete.id}/delete"
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