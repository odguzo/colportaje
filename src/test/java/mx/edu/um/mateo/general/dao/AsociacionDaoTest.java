/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.um.mateo.general.dao;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import mx.edu.um.mateo.general.model.*;
import mx.edu.um.mateo.inventario.model.Almacen;
import mx.edu.um.mateo.general.dao.AsociacionDao;
import mx.edu.um.mateo.general.model.Asociacion;
import mx.edu.um.mateo.general.test.BaseTest;
import mx.edu.um.mateo.general.utils.UltimoException;
import mx.um.edu.mateo.Constantes;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
/**
 *
 * @author gibrandemetrioo
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:mateo.xml", "classpath:security.xml"})
@Transactional
public class AsociacionDaoTest {

    private static final Logger log = LoggerFactory.getLogger(AsociacionDaoTest.class);
    @Autowired
    private AsociacionDao instance;
    @Autowired
    private SessionFactory sessionFactory;
    
   private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }
    
    /**
     * Test of lista method, of class AsociacionDao.
     */
    @Test
    public void debieraMostrarListaDeAsociacion() {
        log.debug("Debiera mostrar lista de Asociaciones");
//        Organizacion organizacion = new Organizacion("tst-01", "test-01", "test-01");
//        currentSession().save(organizacion);
//        Empresa empresa = new Empresa("tst-01", "test-01", "test-01", "000000000001", organizacion);
//        currentSession().save(empresa);
//        TipoCliente tipoCliente = new TipoCliente("TEST-01", "TEST-01", empresa);
//        currentSession().save(tipoCliente);
        for (int i = 0; i < 20; i++) {
           Asociacion asociacion = new Asociacion("test"+i, Constantes.STATUS_ACTIVO);
            currentSession().save(asociacion);
            assertNotNull(asociacion);
            log.debug("asociacion>>" + asociacion);
        }
        Map<String, Object> params = null;
        Map result = instance.lista(params);
        assertNotNull(result.get("asociaciones"));
        assertNotNull(result.get("cantidad"));
        assertEquals(10, ((List<Asociacion>) result.get("asociaciones")).size());
        assertEquals(20, ((Long) result.get("cantidad")).intValue());
    }
    
    @Test
    public void debieraObtenerAsociacion() {
        log.debug("Debiera obtener Asociacion");
        Asociacion asociacion = new Asociacion("test", Constantes.STATUS_ACTIVO);
        currentSession().save(asociacion);
        assertNotNull(asociacion.getId());
        Long id = asociacion.getId();

        Asociacion result = instance.obtiene(id);
        assertNotNull(result);
        assertEquals("test", result.getNombre());
    }
    
    @Test
    public void deberiaCrearAsociacion() {
        log.debug("Deberia crear Asociacion");
        Asociacion asociacion = new Asociacion("test", Constantes.STATUS_ACTIVO);
        assertNotNull(asociacion);
        log.debug("asociacion >> " + asociacion);
        asociacion = instance.crea(asociacion);
        assertNotNull(asociacion.getId());
    }
    
    @Test
    public void deberiaActualizarAsociacion() {
        log.debug("Deberia actualizar Asociacion");
        Asociacion asociacion = new Asociacion("test", Constantes.STATUS_ACTIVO);
        assertNotNull(asociacion);
        currentSession().save(asociacion);
        
        asociacion.setNombre("test1");

        asociacion = instance.actualiza(asociacion);
        log.debug("asociacion >>" + asociacion);
        assertEquals("test1", asociacion.getNombre());
    }
     @Test
    public void deberiaEliminarAsociacion() throws UltimoException {
        log.debug("Debiera eliminar Asociacion");

        Asociacion asociacion = new Asociacion("test", Constantes.STATUS_ACTIVO);
        currentSession().save(asociacion);
        assertNotNull(asociacion);
        String nombre = instance.elimina(asociacion.getId());
        assertEquals("test", nombre);

        Asociacion prueba = instance.obtiene(asociacion.getId());
        assertNull(prueba);
    }
    
}


