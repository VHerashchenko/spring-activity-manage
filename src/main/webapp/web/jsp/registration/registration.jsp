<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@ page isELIgnored="false" %>

<fmt:setBundle basename="messages"/>
<fmt:setLocale value="${param.lang}" />


<!DOCTYPE html>
<html lang="${param.lang}">
  <head>
      <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>
      <title>Create an account</title>

      <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
      <link href="${contextPath}/resources/css/common.css" rel="stylesheet">
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
                          <a class="dropdown-item" href="${contextPath}/registration/?lang=en"><fmt:message key="english" /></a>
                          <a class="dropdown-item" href="${contextPath}/registration/?lang=ru"><fmt:message key="russian" /></a>
                      </div>
                  </div>
              </li>
          </ul>
      </div>
  </nav>



    <div class="container">

        <form:form method="POST" modelAttribute="noteForm" class="form-signin">
            <h2 class="form-signin-heading">Create your account</h2>
            <spring:bind path="username">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <fmt:message key="username"/>
                    <form:input type="text" path="username" class="form-control"
                                autofocus="true"/>
                    <form:errors path="username"/>
                </div>
            </spring:bind>

            <spring:bind path="password">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <fmt:message key="password"/>
                    <form:input type="password" path="password" class="form-control"/>
                    <form:errors path="password"/>
                </div>
            </spring:bind>

            <spring:bind path="passwordConfirm">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <fmt:message key="confirm"/>
                    <form:input type="password" path="passwordConfirm" class="form-control"/>
                    <form:errors path="passwordConfirm"/>
                </div>
            </spring:bind>

            <button class="btn btn-lg btn-primary btn-block" type="submit"><fmt:message key="submit"/></button>
        </form:form>

    </div>

    <script src="${contextPath}/resources/js/jquery.min.js"></script>
    <script src="${contextPath}/resources/js/bootstrap.min.js"></script>
  </body>
</html>
