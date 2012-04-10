<%-- 
    Document   : lista
    Created on : 4/04/2012, 09:49:49 AM
    Author     : wilbert
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
    <head>
       <title><s:message code="documento.lista.label" /></title>
    </head>
    <body>
        <nav class="navbar navbar-fixed-top" role="navigation">
            <ul class="nav">
                <li><a href="<c:url value='/inicio' />"><s:message code="inicio.label" /></a></li>
                <li class="active"><a href="<s:url value='/web/documento'/>" ><s:message code="documento.label" /></a></li>
               </ul>
        </nav>

        <h1><s:message code="documento.lista.label" /></h1>
        <hr/>

        <form name="filtraLista" class="form-search" method="post" action="<c:url value='/web/documento' />">
            <input type="hidden" name="pagina" id="pagina" value="${pagina}" />
            <input type="hidden" name="tipo" id="tipo" value="" />
            <input type="hidden" name="observaciones" id="observaciones" value="" />
            <input type="hidden" name="order" id="order" value="${param.order}" />
            <input type="hidden" name="sort" id="sort" value="${param.sort}" />
            <p class="well">
                <a class="btn btn-primary" href="<s:url value='/web/documento/nuevo'/>"><i class="icon-user icon-white"></i> <s:message code='documento.nuevo.label' /></a>
                <input name="filtro" type="text" class="input-medium search-query" value="${param.filtro}">
                <button type="submit" class="btn"><s:message code="buscar.label" /></button>
            </p>
            <c:if test="${not empty message}">
                <div class="alert alert-block alert-success fade in" role="status">
                    <a class="close" data-dismiss="alert">×</a>
                    <s:message code="${message}" arguments="${messageAttrs}" />
                </div>
            </c:if>
            <c:if test="${documento != null}">
                <s:bind path="documento.*">
                    <c:if test="${not empty status.errorMessages}">
                    <div class="alert alert-block alert-error fade in" role="status">
                        <a class="close" data-dismiss="alert">×</a>
                        <c:forEach var="error" items="${status.errorMessages}">
                            <c:out value="${error}" escapeXml="false"/><br />
                        </c:forEach>
                    </div>
                    </c:if>
                </s:bind>
            </c:if>
            
            <table id="lista" class="table">
                <thead>
                    <tr>
                        <th>
                            <a href="javascript:ordena('tipoDeDocumento');">
                                <s:message code="tipoDeDocumento.label" />
                                <c:choose>
                                    <c:when test="${param.order == 'tipoDeDocumento' && param.sort == 'asc'}">
                                        <i class="icon-chevron-up"></i>
                                    </c:when>
                                    <c:when test="${param.order == 'tipoDeDocumento' && param.sort == 'desc'}">
                                        <i class="icon-chevron-down"></i>
                                    </c:when>
                                </c:choose>
                            </a>
                        </th>
                 <th>
                            <a href="javascript:ordena('folio');">
                                <s:message code="folio.label" />
                                <c:choose>
                                    <c:when test="${param.order == 'folio' && param.sort == 'asc'}">
                                        <i class="icon-chevron-up"></i>
                                    </c:when>
                                    <c:when test="${param.order == 'folio' && param.sort == 'desc'}">
                                        <i class="icon-chevron-down"></i>
                                    </c:when>
                                </c:choose>
                            </a>
                        </th>
                        
                          <th>
                            <a href="javascript:ordena('fecha');">
                                <s:message code="fecha.label" />
                                <c:choose>
                                    <c:when test="${param.order == 'fecha' && param.sort == 'asc'}">
                                        <i class="icon-chevron-up"></i>
                                    </c:when>
                                    <c:when test="${param.order == 'fecha' && param.sort == 'desc'}">
                                        <i class="icon-chevron-down"></i>
                                    </c:when>
                                </c:choose>
                            </a>
                        </th>
                          <th>
                            <a href="javascript:ordena('importe');">
                                <s:message code="importe.label" />
                                <c:choose>
                                    <c:when test="${param.order == 'importe' && param.sort == 'asc'}">
                                        <i class="icon-chevron-up"></i>
                                    </c:when>
                                    <c:when test="${param.order == 'importe' && param.sort == 'desc'}">
                                        <i class="icon-chevron-down"></i>
                                    </c:when>
                                </c:choose>
                            </a>
                        </th>
                   
                      <th>
                            <a href="javascript:ordena('observaciones');">
                                <s:message code="observaciones.label" />
                                <c:choose>
                                    <c:when test="${param.order == 'observaciones' && param.sort == 'asc'}">
                                        <i class="icon-chevron-up"></i>
                                    </c:when>
                                    <c:when test="${param.order == 'observaciones' && param.sort == 'desc'}">
                                        <i class="icon-chevron-down"></i>
                                    </c:when>
                                </c:choose>
                            </a>
                        </th>                  
                     </tr>
                </thead>
                <tbody>
                    <c:forEach items="${documentos}" var="documento" varStatus="status">
                        <tr class="${status.index % 2 == 0 ? 'even' : 'odd'}">
                            <td><a href="<c:url value='documento/ver/${documento.id}' />">${documento.tipoDeDocumento}</a></td>
                            <td>${documento.folio}</td>
                            <td>${documento.fecha}</td>
                            <td>${documento.importe}</td>
                            <td>${documento.observaciones}</td>
                            </tr>
                    </c:forEach>
                </tbody>
            </table>
            <div class="row-fluid">
                <div class="span8">
                    <div class="pagination">
                        <ul>
                            <li class="disabled"><a href="#"><s:message code="mensaje.paginacion" arguments="${paginacion}" /></a></li>
                            <c:forEach items="${paginas}" var="paginaId">
                                <li <c:if test="${pagina == paginaId}" >class="active"</c:if>>
                                    <a href="javascript:buscaPagina(${paginaId});" >${paginaId}</a>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                </div>
                <div class="span4">
                    <div class="btn-group pull-right" style="margin-top: 22px;margin-left: 10px;">
                        <button id="enviaCorreoBtn" class="btn" data-loading-text="<s:message code='enviando.label'/>" onclick="javascript:enviaCorreo('XLS');" ><s:message code="envia.correo.label" /></button>
                        <a class="btn dropdown-toggle" data-toggle="dropdown" href="#"><span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="javascript:enviaCorreo('PDF');"><img src="<c:url value='/images/pdf.gif' />" /></a></li>
                            <li><a href="javascript:enviaCorreo('CSV');"><img src="<c:url value='/images/csv.gif' />" /></a></li>
                            <li><a href="javascript:enviaCorreo('XLS');"><img src="<c:url value='/images/xls.gif' />" /></a></li>
                        </ul>
                    </div>
                    <p class="pull-right" style="margin-top: 20px;">
                        <a href="javascript:imprime('PDF');"><img src="<c:url value='/images/pdf.gif' />" /></a>
                        <a href="javascript:imprime('CSV');"><img src="<c:url value='/images/csv.gif' />" /></a>
                        <a href="javascript:imprime('XLS');"><img src="<c:url value='/images/xls.gif' />" /></a>
                    </p>
                </div>
            </div>
        </form>        
        <content>
            <script>
                $(document).ready(function() {
                    highlightTableRows("lista");

                });

                function buscaPagina(paginaId) {
                    $('input#pagina').val(paginaId);
                    document.forms["filtraLista"].submit();
                }
                
                function imprime(tipo) {
                    $('input#tipo').val(tipo);
                    document.forms["filtraLista"].submit();
                }
                
                function enviaCorreo(tipo) {
                    $('#enviaCorreoBtn').button('loading');
                    $('input#observaciones').val(tipo);
                    document.forms["filtraLista"].submit();
                }
                
                function ordena(campo) {
                    if ($('input#order').val() == campo && $('input#sort').val() == 'asc') {
                        $('input#sort').val('desc');
                    } else {
                        $('input#sort').val('asc');
                    }
                    $('input#order').val(campo);
                    document.forms["filtraLista"].submit();
                }
            </script>
        </content>
    </body>
</html>
