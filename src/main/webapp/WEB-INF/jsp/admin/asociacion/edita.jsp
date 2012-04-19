<%-- 
    Document   : edita
    Created on : 27-feb-2012, 15:43:48
    Author     : gibrandemetrioo
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><s:message code="asociacion.edita.label" /></title>
    </head>
    <body>
        <jsp:include page="../menu.jsp" >
            <jsp:param name="menu" value="asociacion" />
        </jsp:include>

        <div id="edita-asociacion" class="content scaffold-list" role="main">
            <h1><s:message code="asociacion.edita.label" /></h1>
            <p class="well">
                <a class="btn btn-primary" href="<s:url value='../'/>"><i class="icon-list icon-white"></i> <s:message code='asociacion.lista.label' /></a>
            </p>
            <c:url var="actualizaUrl" value="../actualiza" />
            <form:form commandName="asociacion" method="post" action="${actualizaUrl}">
                <form:errors path="*">
                    <div class="alert alert-block alert-error fade in" role="status">
                        <a class="close" data-dismiss="alert">×</a>
                        <c:forEach items="${messages}" var="message">
                            <p>${message}</p>
                        </c:forEach>
                    </div>
                </form:errors>
                <form:hidden path="id" />
                <form:hidden path="version" />

                <fieldset>
                    <s:bind path="asociacion.nombre">
                        <div class="control-group <c:if test='${not empty status.errorMessages}'>error</c:if>">
                            <label for="nombre">
                                <s:message code="nombre.label" />
                                <span class="required-indicator">*</span>
                            </label>
                            <form:input path="nombre" maxlength="128" required="true" />
                            <form:errors path="nombre" cssClass="alert alert-error" />
                        </div>
                    </s:bind>
                    <c:if test='${asociacion.status == "I"}'>
                        <s:bind path="asociacion.status">
                            <div class="control-group <c:if test='${not empty status.errorMessages}'>error</c:if>">
                                <label for="status">
                                    <s:message code="status.label" />
                                    <span class="required-indicator">*</span>
                                </label>
                                <form:select path="status">
                                    <form:option value="0" label="Inactivo" />
                                    <form:option value="1" label="Activo" />
                                </form:select>
                                <form:errors path="status" cssClass="alert alert-error" />
                            </div>
                        </s:bind>
                    </c:if>
                    <c:if test='${asociacion.status == "A"}'>
                        <form:hidden path="status" />
                    </c:if>
                </fieldset>

                <p class="well" style="margin-top: 10px;">
                    <input type="submit" name="actualiza" value="<s:message code='actualizar.button' />" class="btn btn-large btn-primary" />
                </p>
            </form:form>
        </div>
    <content>
        <script>
            $(document).ready(function() {
                $('input#nombre').focus();
            });
        </script> 
    </content>
</body>
</html>