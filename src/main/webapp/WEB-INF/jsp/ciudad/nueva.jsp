<%-- 
    Document   : nueva
    Created on : 14-mar-2012, 11:27:43
    Author     : gibrandemetrioo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
    <head>
        <title><s:message code="ciudad.nueva.label" /></title>
    </head>
    <body>
        <jsp:include page="../menu.jsp" >
            <jsp:param name="menu" value="ciudad" />
        </jsp:include>

        <div id="nueva-ciudad" class="content scaffold-list" role="main">
            <h1><s:message code="ciudad.nueva.label" /></h1>
            <p class="well">
                <a class="btn btn-primary" href="<s:url value='/ciudad'/>"><i class="icon-list icon-white"></i> <s:message code='ciudad.lista.label' /></a>
            </p>
            <form:form commandName="ciudad" action="crea" method="post">
                <form:errors path="*">
                    <div class="alert alert-block alert-error fade in" role="status">
                        <a class="close" data-dismiss="alert">×</a>
                        <c:forEach items="${messages}" var="message">
                            <p>${message}</p>
                        </c:forEach>
                    </div>
                </form:errors>
                <fieldset>
                    <s:bind path="ciudad.estado">
                        <div class="control-group <c:if test='${not empty status.errorMessages}'>error</c:if>">
                                <label for="estado">
                                <s:message code="estado.label" />
                                <span class="required-indicator">*</span>
                                <form:select id="estadoId" path="estado.id" items="${estados}" itemLabel="nombre" itemValue="id" />
                                <form:errors path="estado" cssClass="alert alert-error" />
                        </div>
                    </s:bind>
                    <s:bind path="ciudad.nombre">
                        <div class="control-group <c:if test='${not empty status.errorMessages}'>error</c:if>">
                                <label for="nombre">
                                <s:message code="ciudad.label" />
                                <span class="required-indicator">*</span>
                            </label>
                            <form:input path="nombre" maxlength="128" required="true" />
                            <form:errors path="nombre" cssClass="alert alert-error" />
                        </div>
                    </s:bind>
                </fieldset>
                <p class="well" style="margin-top: 10px;">
                    <input type="submit" name="_action_crea" class="btn btn-primary btn-large" value="<s:message code='crear.button'/>" id="crea" />
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