<%@ page import="com.vh.activitymanage.model.dto.ActivityUserDTO" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<c:set var="categories" value="${pageContext.request.getAttribute('categories')}"/>

<fmt:setBundle basename="messages"/>
<fmt:setLocale value="${param.lang}"/>

<!DOCTYPE html>
<html lang="${param.lang}">
<head>
    <meta charset="utf-8">
    <title><fmt:message key="activity.page"/></title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/my.css" rel="stylesheet">
</head>

<body>

<div class="container">

    <form:form method="POST" action="${contextPath}/activity/update" modelAttribute="activity" class="form-signin">
        <h2 class="form-signin-heading">
            <%ActivityUserDTO activityUserDTO = (ActivityUserDTO) request.getAttribute("activity");%>
            <%if(activityUserDTO.getId() != null){ %>
            <fmt:message key="edit"/>
            <% } else {%>
            <fmt:message key="create"/>
            <%}%>
            <fmt:message key="activity.activity"/></h2>
        <form:hidden path="id" />

        <%--NAME--%>
        <spring:bind path="name">
            <div class="form-group ${status.error ? 'has-error' : ''}">  <fmt:message key="activity.name"/>
                <form:input type="text" path="name" class="form-control"
                            autofocus="true"/>
                <form:errors path="name"/>
            </div>
        </spring:bind>


        <%--Category--%>
        <spring:bind path="category.id">
            <div class="form-group ${status.error ? 'has-error' : ''}"> <label for="categoryId">   <fmt:message key="category.name"/> </label>
                <form:select path="category.id" id = "categoryId" class="selectpicker" data-show-subtext="true" data-live-search="true">
                    <c:forEach items="${categories}" var="category">
                        <option ${category.id == activity.category.id ? 'selected="selected"' : ''}
                                value="${category.id}">${category.name}</option>
                    </c:forEach>
                </form:select>
                <form:errors path="category.id"/>
            </div>
        </spring:bind>

        <%--time--%>
        <spring:bind path="time">
            <div class="form-group ${status.error ? 'has-error' : ''}"> <label for="timeSet">   <fmt:message key="activity.time"/> </label>
                <form:select path="time" id = "timeSet" class="selectpicker" data-show-subtext="true" data-live-search="true">
                    <option ${time == activity.time ? 'selected="selected"' : ''}
                            value="1">1 <fmt:message key="activity.time.hour"/></option>
                    <option ${time == activity.time ? 'selected="selected"' : ''}
                            value="2">2 <fmt:message key="activity.time.2-4hours"/></option>
                    <option ${time == activity.time ? 'selected="selected"' : ''}
                            value="3">3 <fmt:message key="activity.time.2-4hours"/></option>
                    <option ${time == activity.time ? 'selected="selected"' : ''}
                            value="4">4 <fmt:message key="activity.time.2-4hours"/></option>
                    <option ${time == activity.time ? 'selected="selected"' : ''}
                            value="5">5 <fmt:message key="activity.time.5-6hours"/></option>
                    <option ${time == activity.time ? 'selected="selected"' : ''}
                            value="6">6 <fmt:message key="activity.time.5-6hours"/></option>
                </form:select>
                <form:errors path="time"/>
            </div>
        </spring:bind>

        <spring:bind path="usersId">
            <div class="form-group ${status.error ? 'has-error' : ''}"> <label for="usersSet">   <fmt:message key="activity.users"/> </label>
                <form:select multiple="true" path="usersId" id = "usersSet" class="selectpicker" data-show-subtext="true" data-live-search="true">
                    <c:forEach items="${users}" var="user">
                        <option value="${user.id}">${user.username}</option>
                    </c:forEach>
                </form:select>
            </div>
        </spring:bind>


        <button class="btn btn-lg btn-primary btn-block" type="submit"><fmt:message key="submit"/></button>
    </form:form>
</div>

<script src="${contextPath}/resources/js/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>

