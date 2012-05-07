/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.um.mateo;

import java.math.BigDecimal;

/**
 *
 * @author gibrandemetrioo
 */
public class Constantes {

    public static final String ADMIN = "ADMIN";
    public static final String UNI = "UNI";
    public static final String ASO = "ASO";
    public static final String NOMBRE = "test";
    public static final String STATUS_ACTIVO = "A";
    public static final String CLAVE = "000";
    public static final String DIRECCION = "testd";
    public static final String CORREO = "test@tes.tst";
    public static final String TELEFONO = "12345";
    public static final String TIPO_DOCUMENTO = "A";
    public static final String TIPO_COLPORTOR = "test";
    public static final String FOLIO = "test";
    public static final String FECHA = "dd/MM/yyyy";
    public static final BigDecimal IMPORTE = new BigDecimal("0.0");
    public static final String OBSERVACIONES = "test teste";
    public static final String CALLE = "test";
    public static final String COLONIA = "test";
    public static final String MUNICIPIO = "testmu";
    public static final String MATRICULA = "1070";
    public static final String DEPOSITO_CAJA = "Deposito_Caja";
    public static final String DEPOSITO_BANCO = "Deposito_Banco";
    public static final String DIEZMO = "Diezmo";
    public static final String NOTA_DE_COMPRA = "Nota_De_Compra";
    public static final String BOLETIN = "Boletín";
    public static final String INFORME = "Informe";
    public static final String TOTALBOLETIN = "Total_Boletin";
    public static final String TOTALDIEZMOS = "Total_Diezmos";
    public static final String TOTALDEPOSITOS = "Total_Depositos";
    public static final String ALCANZADO = "Alcanzado";
    public static final String OBJETIVO = "Objetivo";
    public static final String FIDELIDAD = "Fidelidad";
    public static final String TIEMPO_COMPLETO = "Tiempo_Completo";
    public static final String TIEMPO_PARCIAL = "Tiempo_Parcial";
    public static final String ESTUDIANTE = "Estudiante";
    /**
     * Valores para el constructor de Locale
     */
    public static final String LOCALE_LANGUAGE = "es";
    public static final String LOCALE_COUNTRY = "MX";
    public static final String LOCALE_VARIANT = "Traditional_WIN";
    /**
     * Formato (yyyy-MM-dd) de la fecha en el cual el mes se representa
     * numericamente.
     */
    public static final String DATE_SHORT_SYSTEM_PATTERN = "yyyy-MM-dd";
    /**
     * Formato (dd/MM/yyyy) de la fecha en el cual el mes se representa
     * numericamente
     */
    public static final String DATE_SHORT_HUMAN_PATTERN = "dd/MM/yyyy";
    /**
     * Formato (dd/MM/yyyy hh:mm) de la fecha en el cual el mes se representa
     * numericamente incluyendo la hora:minutos:segundos am/pm
     */
    public static final String DATE_SHORT_HHMM_HUMAN_PATTERN = "dd/MM/yyyy HH:mm";
    /**
     * Se utiliza para informar al usuario el formato de fecha esperado
     */
    public static final String DATE_SHORT_HUMAN_PATTERN_MSG = "dd/mm/yyyy";
    /**
     * Formato (dd/MMM/yyyy) de la fecha en el cual el mes se representa en
     * palabra
     */
    public static final String DATE_LONG_HUMAN_PATTERN = "dd/MMM/yyyy";
    /**
     * Formato (dd de MMMMM de yyyy) de la fecha en la cual el mes es completo
     */
    public static final String DATE_XLONG_HUMAN_PATTERN = "dd 'de' MMMM 'de' yyyy";
    //public static final String STATUS_ACTIVO = "A";
    public static final String STATUS_INACTIVO = "I";
    public static final String DECIMAL_PATTERN = "###,###,###,##0.00";
    public static final String CURRENCY_PATTERN = "$###,###,##0.00";
    public static final String PERCENTAGE_PATTERN = "#.00%";
    /**
     * Valores para el los containsKey
     */
    public static final String CONTAINSKEY_MAX = "max";
    public static final String CONTAINSKEY_PAGINA = "pagina";
    public static final String CONTAINSKEY_PAGINAS = "paginas";
    public static final String CONTAINSKEY_PAGINACION = "paginacion";
    public static final String CONTAINSKEY_OFFSET = "offset";
    public static final String CONTAINSKEY_FILTRO = "filtro";
    public static final String CONTAINSKEY_ORDER = "order";
    public static final String CONTAINSKEY_SORT = "sort";
    public static final String CONTAINSKEY_DESC = "desc";
    public static final String CONTAINSKEY_REPORTE = "reporte";
    public static final String CONTAINSKEY_CANTIDAD = "cantidad";
    public static final String CONTAINSKEY_MESSAGE = "message";
    public static final String CONTAINSKEY_MESSAGE_ATTRS = "messageAttrs";
    /**
     * Valores para el los containsKey para las clases
     */
    public static final String CONTAINSKEY_CLIENTES = "clientes";
    public static final String CONTAINSKEY_UNIONES = "uniones";
    public static final String CONTAINSKEY_COLPORTORES = "colportores";
    public static final String CONTAINSKEY_DOCUMENTOS = "documentos";
    public static final String CONTAINSKEY_COLEGIOS = "colegios";
    public static final String CONTAINSKEY_ASOCIACIONES = "asociaciones";
    public static final String CONTAINSKEY_ASOCIADOS = "asociados";
    public static final String CONTAINSKEY_TEMPORADAS = "temporadas";
    public static final String CONTAINSKEY_TEMPORADACOLPORTORES = "temporadaColportores";
    public static final String CONTAINSKEY_ALMACENES = "almacenes";
    public static final String CONTAINSKEY_PAISES = "paises";
    public static final String CONTAINSKEY_ESTADOS = "estados";
    public static final String CONTAINSKEY_CIUDADES = "ciudades";
    /**
     * Valores para el los addAttribute para las clases
     */
    public static final String ADDATTRIBUTE_USUARIO = "union";
    public static final String ADDATTRIBUTE_UNION = "union";
    public static final String ADDATTRIBUTE_COLPORTOR = "colportor";
    public static final String ADDATTRIBUTE_DOCUMENTO = "documento";
    public static final String ADDATTRIBUTE_COLEGIO = "colegio";
    public static final String ADDATTRIBUTE_CLIENTE = "cliente";
    public static final String ADDATTRIBUTE_ASOCIACION = "asociacion";
    public static final String ADDATTRIBUTE_ASOCIADO = "asociado";
    public static final String ADDATTRIBUTE_TEMPORADA = "temporada";
    public static final String ADDATTRIBUTE_TEMPORADACOLPORTOR = "temporadaColportor";
    public static final String ADDATTRIBUTE_ALMACEN = "almacen";
    public static final String ADDATTRIBUTE_PAIS = "pais";
    public static final String ADDATTRIBUTE_ESTADO = "estado";
    public static final String ADDATTRIBUTE_CIUDAD = "ciudad";
    /**
     * Valores para el los path's para las clases
     */
    public static final String PATH_CLIENTE = "/admin/cliente";
    public static final String PATH_CLIENTE_LISTA = "admin/cliente/lista";
    public static final String PATH_CLIENTE_VER = "/admin/cliente/ver";
    public static final String PATH_CLIENTE_EDITA = "admin/cliente/edita";
    public static final String PATH_CLIENTE_CREA = "/admin/cliente/crea";
    public static final String PATH_CLIENTE_ACTUALIZA = "/admin/cliente/actualiza";
    public static final String PATH_CLIENTE_ELIMINA = "/admin/cliente/elimina";
    public static final String PATH_UNION = "/admin/union";
    public static final String PATH_UNION_LISTA = "admin/union/lista";
    public static final String PATH_UNION_VER = "/admin/union/ver";
    public static final String PATH_UNION_NUEVA = "admin/union/nueva";
    public static final String PATH_UNION_EDITA = "admin/union/edita";
    public static final String PATH_UNION_CREA = "/admin/union/crea";
    public static final String PATH_UNION_ACTUALIZA = "/admin/union/actualiza";
    public static final String PATH_UNION_ELIMINA = "/admin/union/elimina";
    public static final String PATH_ASOCIACION = "/admin/asociacion";
    public static final String PATH_ASOCIACION_LISTA = "/admin/asociacion/lista";
    public static final String PATH_ASOCIACION_VER = "/admin/asociacion/ver";
    public static final String PATH_ASOCIACION_NUEVA = "/admin/asociacion/nueva";
    public static final String PATH_ASOCIACION_EDITA = "/admin/asociacion/edita";
    public static final String PATH_ASOCIACION_CREA = "/admin/asociacion/crea";
    public static final String PATH_ASOCIACION_ACTUALIZA = "/admin/asociacion/actualiza";
    public static final String PATH_ASOCIACION_ELIMINA = "/admin/asociacion/elimina";
    public static final String PATH_COLPORTOR = "/colportor";
    public static final String PATH_COLPORTOR_LISTA = "colportor/lista";
    public static final String PATH_COLPORTOR_VER = "/colportor/ver";
    public static final String PATH_COLPORTOR_NUEVO = "colportor/nuevo";
    public static final String PATH_COLPORTOR_EDITA = "colportor/edita";
    public static final String PATH_COLPORTOR_CREA = "/colportor/crea";
    public static final String PATH_COLPORTOR_ACTUALIZA = "/colportor/actualiza";
    public static final String PATH_COLPORTOR_ELIMINA = "/colportor/elimina";
    public static final String PATH_DOCUMENTO = "/documento";
    public static final String PATH_DOCUMENTO_LISTA = "documento/lista";
    public static final String PATH_DOCUMENTO_VER = "/documento/ver";
    public static final String PATH_DOCUMENTO_NUEVO = "documento/nuevo";
    public static final String PATH_DOCUMENTO_EDITA = "documento/edita";
    public static final String PATH_DOCUMENTO_CREA = "/documento/crea";
    public static final String PATH_DOCUMENTO_ACTUALIZA = "/documento/actualiza";
    public static final String PATH_DOCUMENTO_ELIMINA = "/documento/elimina";
    public static final String PATH_COLEGIO = "/colegio";
    public static final String PATH_COLEGIO_LISTA = "colegio/lista";
    public static final String PATH_COLEGIO_VER = "/colegio/ver";
    public static final String PATH_COLEGIO_NUEVO = "colegio/nuevo";
    public static final String PATH_COLEGIO_EDITA = "colegio/edita";
    public static final String PATH_COLEGIO_CREA = "/colegio/crea";
    public static final String PATH_COLEGIO_ACTUALIZA = "/colegio/actualiza";
    public static final String PATH_COLEGIO_ELIMINA = "/colegio/elimina";
    public static final String PATH_CLIENTE_NUEVO = "cliente/nuevo";
    public static final String PATH_ASOCIADO = "/asociado";
    public static final String PATH_ASOCIADO_LISTA = "/asociado/lista";
    public static final String PATH_ASOCIADO_VER = "/asociado/ver";
    public static final String PATH_ASOCIADO_NUEVO = "/asociado/nuevo";
    public static final String PATH_ASOCIADO_EDITA = "/asociado/edita";
    public static final String PATH_ASOCIADO_CREA = "/asociado/crea";
    public static final String PATH_ASOCIADO_ACTUALIZA = "/asociado/actualiza";
    public static final String PATH_ASOCIADO_ELIMINA = "/asociado/elimina";
    public static final String PATH_TEMPORADA = "/temporada";
    public static final String PATH_TEMPORADA_LISTA = "/temporada/lista";
    public static final String PATH_TEMPORADA_VER = "/temporada/ver";
    public static final String PATH_TEMPORADA_NUEVA = "/temporada/nueva";
    public static final String PATH_TEMPORADA_EDITA = "/temporada/edita";
    public static final String PATH_TEMPORADA_CREA = "/temporada/crea";
    public static final String PATH_TEMPORADA_ACTUALIZA = "/temporada/actualiza";
    public static final String PATH_TEMPORADA_ELIMINA = "/temporada/elimina";
    public static final String PATH_TEMPORADACOLPORTOR = "/temporadaColportor";
    public static final String PATH_TEMPORADACOLPORTOR_LISTA = "/temporadaColportor/lista";
    public static final String PATH_TEMPORADACOLPORTOR_VER = "/temporadaColportor/ver";
    public static final String PATH_TEMPORADACOLPORTOR_NUEVA = "/temporadaColportor/nueva";
    public static final String PATH_TEMPORADACOLPORTOR_EDITA = "/temporadaColportor/edita";
    public static final String PATH_TEMPORADACOLPORTOR_CREA = "/temporadaColportor/crea";
    public static final String PATH_TEMPORADACOLPORTOR_ACTUALIZA = "/temporadaColportor/actualiza";
    public static final String PATH_TEMPORADACOLPORTOR_ELIMINA = "/temporadaColportor/elimina";
    public static final String PATH_ALMACEN = "/almacen";
    public static final String PATH_ALMACEN_LISTA = "/almacen/lista";
    public static final String PATH_ALMACEN_VER = "/almacen/ver";
    public static final String PATH_ALMACEN_NUEVO = "/almacen/nuevo";
    public static final String PATH_ALMACEN_EDITA = "/almacen/edita";
    public static final String PATH_ALMACEN_CREA = "/almacen/crea";
    public static final String PATH_ALMACEN_ACTUALIZA = "/almacen/actualiza";
    public static final String PATH_ALMACEN_ELIMINA = "/almacen/elimina";
    public static final String PATH_PAIS = "/pais";
    public static final String PATH_PAIS_LISTA = "pais/lista";
    public static final String PATH_PAIS_VER = "/pais/ver";
    public static final String PATH_PAIS_NUEVA = "/pais/nueva";
    public static final String PATH_PAIS_EDITA = "/pais/edita";
    public static final String PATH_PAIS_CREA = "/pais/crea";
    public static final String PATH_PAIS_ACTUALIZA = "/pais/actualiza";
    public static final String PATH_PAIS_ELIMINA = "/pais/elimina";
    public static final String PATH_ESTADO = "/estado";
    public static final String PATH_ESTADO_LISTA = "estado/lista";
    public static final String PATH_ESTADO_VER = "/estado/ver";
    public static final String PATH_ESTADO_NUEVA = "/estado/nueva";
    public static final String PATH_ESTADO_EDITA = "/estado/edita";
    public static final String PATH_ESTADO_CREA = "/estado/crea";
    public static final String PATH_ESTADO_ACTUALIZA = "/estado/actualiza";
    public static final String PATH_ESTADO_ELIMINA = "/estado/elimina";
    public static final String PATH_CIUDAD = "/ciudad";
    public static final String PATH_CIUDAD_LISTA = "ciudad/lista";
    public static final String PATH_CIUDAD_VER = "/ciudad/ver";
    public static final String PATH_CIUDAD_NUEVA = "/ciudad/nueva";
    public static final String PATH_CIUDAD_EDITA = "/ciudad/edita";
    public static final String PATH_CIUDAD_CREA = "/ciudad/crea";
    public static final String PATH_CIUDAD_ACTUALIZA = "/ciudad/actualiza";
    public static final String PATH_CIUDAD_ELIMINA = "/ciudad/elimina";
}
