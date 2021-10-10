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
    <title><fmt:message key="activity.page"/></title>

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
            <li class="nav-item active">
                <a class="nav-link" href="${contextPath}/category/all"><fmt:message key="category.page" /> <span class="sr-only"></span></a>
            </li>
            <li class="nav-item active">
                <a class="nav-link" href="${contextPath}/admin/dashboard"><fmt:message key="dashboard.page" /> <span class="sr-only"></span></a>
            </li>
            <% } %>
            <li class="nav-item dropdown">
                <div class="btn-group">
                    <button type="button" class="btn"><fmt:message key="locale" /></button>
                    <button type="button" class="btn dropdown-toggle dropdown-toggle-split" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <span class="sr-only">Toggle Dropdown</span>
                    </button>
                    <div class="dropdown-menu">
                        <a class="dropdown-item" href="${contextPath}/activity/all/?lang=en"><fmt:message key="english" /></a>
                        <a class="dropdown-item" href="${contextPath}/activity/all/?lang=ru"><fmt:message key="russian" /></a>
                    </div>
                </div>
            </li>
            <li class="nav-item active">
                <a class="nav-link" href="${contextPath}/welcome"><fmt:message key="welcome" /> <span class="sr-only"></span></a>
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
                    <th><fmt:message key="activity.name"/></th>
                    <th><fmt:message key="activity.time"/></th>
                    <th><fmt:message key="activity.status"/></th>
                    <th><fmt:message key="category.name"/></th>
                    <th><fmt:message key="actions"/></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${activities}" var="activities">
                    <tr>
                        <td>${activities.name}</td>
                        <td>${activities.time}</td>
                        <td>${activities.status}</td>
                        <td>${activities.category.name}</td>
                        <td>
                            <form:form method="GET" action="${contextPath}/activity/${activities.id}/edit"
                                       class="form-signin">
                                <button class="btn btn btn-primary btn-block" type="submit"><fmt:message key="edit"/></button>
                            </form:form>
                            <form:form method="POST" action="${contextPath}/activity/${activities.id}"
                                   class="form-signin">
                            <button class="btn btn btn-danger btn-block" type="submit"><fmt:message key="delete"/></button>
                            </form:form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <form:form cssClass="col-2 margin-table-view" method="GET" action="${contextPath}/activity/create" class="form-signin">
            <button class="btn btn btn-success" type="submit"><fmt:message key="create"/></button>
        </form:form>
    </div>
</div>

<script src="${contextPath}/resources/js/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.js"></script>
</body>
</html>
