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
      <title><fmt:message key="login.title"/></title>

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
                          <a class="dropdown-item" href="${contextPath}/login/?lang=en"><fmt:message key="english" /></a>
                          <a class="dropdown-item" href="${contextPath}/login/?lang=ru"><fmt:message key="russian" /></a>
                      </div>
                  </div>
              </li>
          </ul>
      </div>
  </nav>

    <div class="container">
      <form method="POST" action="${contextPath}/login" class="form-signin">
        <h2 class="form-heading"><fmt:message key="login.title"/></h2>

        <div class="form-group ${error != null ? 'has-error' : ''}">
            <% if(null != request.getAttribute("message")){ %>
            <span><fmt:message key="${message}"/></span>
            <%} %>
            <div><fmt:message key="username"/>
                <input name="username" type="text" class="form-control" autofocus="true"/>
            </div>

            <div><fmt:message key="password"/>
                <input name="password" type="password" class="form-control"/>
                <% if(null != request.getAttribute("error")){ %>
                <span><fmt:message key="${error}"/></span>
                <%} %>
            </div>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>


            <button class="btn btn-lg btn-primary btn-block" type="submit"><fmt:message key="login.title"/></button>
            <h4 class="text-center"><a href="${contextPath}/registration"><fmt:message key="login.create.account"/></a></h4>
        </div>
      </form>
    </div>

    <script src="${contextPath}/resources/js/jquery.min.js"></script>
    <script src="${contextPath}/resources/js/bootstrap.min.js"></script>
  </body>
</html>
