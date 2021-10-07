<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<fmt:setBundle basename="messages"/>
<fmt:setLocale value="${param.lang}"/>

<!DOCTYPE html>
<html lang="${param.lang}">
<head>
    <meta charset="utf-8">
    <title><fmt:message key="category.page"/></title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">
</head>

<body>

<div class="container">

    <div class="row">
        <div class="col-10">

            <form:form method="POST" action="${contextPath}/category/edit" modelAttribute="category"
                       class="form-signin">
                <h2 class="form-signin-heading"><fmt:message key="category.view"/></h2>

                <form:hidden path="id"/>

                <%--NAME--%>
                <spring:bind path="name">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <fmt:message key="category.name"/><br>
                        <form:input type="text" path="name" class="form-control"
                                    autofocus="true"/>
                        <form:errors path="name"/>
                    </div>
                </spring:bind>

                <button class="btn btn-lg btn-primary btn-block" type="submit"><fmt:message key="edit"/></button>
            </form:form>
        </div>
        <div class="col-2 margin-view">
            <form:form method="GET" action="${contextPath}/category/all" class="form-signin">
                <button class="btn btn btn-info btn-block" type="submit"><fmt:message key="category.view.all"/></button>
            </form:form>
        </div>
    </div>
</div>

<script src="${contextPath}/resources/js/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.js"></script>
</body>
</html>