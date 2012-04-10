package mx.edu.um.mateo.general.dao;

import java.util.List;
import java.util.Map;
import mx.edu.um.mateo.Constantes;
import mx.edu.um.mateo.general.model.Asociacion;
import mx.edu.um.mateo.general.model.Cliente;
import mx.edu.um.mateo.general.test.BaseTest;
import mx.edu.um.mateo.general.utils.UltimoException;
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
 * @author nujev
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:mateo.xml", "classpath:security.xml"})
@Transactional
public class ClienteDaoTest extends BaseTest {

    private static final Logger log = LoggerFactory.getLogger(ClienteDaoTest.class);
    @Autowired
    private ClienteDao instance;
    @Autowired
    private SessionFactory sessionFactory;

    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    /**
     * Test of lista method, of class ClienteDao.
     */
    @Test
    public void deberiaMostrarListaDeClientes() {
        log.debug("Debiera mostrar lista de clientes");

        Asociacion test = new Asociacion("test", Constantes.STATUS_ACTIVO);
        currentSession().save(test);
        for (int i = 0; i < 20; i++) {
            Cliente cliente = new Cliente("test", "test", "test", "test", "test", new Integer(0), new Integer(0), new Integer(0), "test");
            cliente.setAsociacion(test);
            currentSession().save(cliente);
            assertNotNull(cliente);
        }

        Map<String, Object> params = null;
        Map result = instance.lista(params);
        assertNotNull(result.get(Constantes.CONTAINSKEY_CLIENTES));
        assertNotNull(result.get(Constantes.CONTAINSKEY_CANTIDAD));

        assertEquals(10, ((List<Cliente>) result.get(Constantes.CONTAINSKEY_CLIENTES)).size());
        assertEquals(20, ((Long) result.get(Constantes.CONTAINSKEY_CANTIDAD)).intValue());
    }

    @Test
    public void deberiaMostrarListaDeClientePorOrgananizacion() {
        log.debug("Debiera mostrar lista de cliente");

        Asociacion test = new Asociacion("test", Constantes.STATUS_ACTIVO);
        currentSession().save(test);
        for (int i = 0; i < 20; i++) {
            Cliente cliente = new Cliente("test", "test", "test", "test", "test", new Integer(0), new Integer(0), new Integer(0), "test");
            cliente.setAsociacion(test);
            currentSession().save(cliente);
            assertNotNull(cliente);
        }

        Map<String, Object> params = null;
        Map result = instance.lista(params);
        assertNotNull(result.get(Constantes.CONTAINSKEY_CLIENTES));
        assertNotNull(result.get(Constantes.CONTAINSKEY_CANTIDAD));

        assertEquals(10, ((List<Cliente>) result.get(Constantes.CONTAINSKEY_CLIENTES)).size());
        assertEquals(20, ((Long) result.get(Constantes.CONTAINSKEY_CANTIDAD)).intValue());
    }

    @Test
    public void debieraObtenerCliente() {
        log.debug("Debiera obtener cliente");

        String nombre = "test";
        Asociacion test = new Asociacion("test", Constantes.STATUS_ACTIVO);
        currentSession().save(test);
        Cliente cliente = new Cliente("test", "test", "test", "test", "test", new Integer(0), new Integer(0), new Integer(0), "test");
        cliente.setAsociacion(test);
        currentSession().save(cliente);
        assertNotNull(cliente.getId());
        Long id = cliente.getId();

        Cliente result = instance.obtiene(id);
        assertNotNull(result);
        assertEquals(nombre, result.getNombre());

        assertEquals(result, cliente);
    }

    @Test
    public void deberiaCrearCliente() {
        log.debug("Deberia crear Cliente");

        Asociacion test = new Asociacion("test", Constantes.STATUS_ACTIVO);
        currentSession().save(test);
        Cliente cliente = new Cliente("test", "test", "test", "test", "test", new Integer(0), new Integer(0), new Integer(0), "test");
        cliente.setAsociacion(test);
        assertNotNull(cliente);

        Cliente cliente2 = instance.crea(cliente);
        assertNotNull(cliente2);
        assertNotNull(cliente2.getId());

        assertEquals(cliente, cliente2);
    }

    @Test
    public void deberiaActualizarCliente() {
        log.debug("Deberia actualizar Cliente");

        Asociacion test = new Asociacion("test", Constantes.STATUS_ACTIVO);
        currentSession().save(test);
        Cliente cliente = new Cliente("test", "test", "test", "test", "test", new Integer(0), new Integer(0), new Integer(0), "test");
        cliente.setAsociacion(test);
        assertNotNull(cliente);
        currentSession().save(cliente);

        String nombre = "test1";
        cliente.setNombre(nombre);

        Cliente cliente2 = instance.actualiza(cliente);
        assertNotNull(cliente2);
        assertEquals(nombre, cliente.getNombre());

        assertEquals(cliente, cliente2);
    }

    @Test
    public void deberiaEliminarCliente() throws UltimoException {
        log.debug("Debiera eliminar Cliente");

        String nom = "test";
        Asociacion test = new Asociacion("test", Constantes.STATUS_ACTIVO);
        currentSession().save(test);
        Cliente cliente = new Cliente("test", "test", "test", "test", "test", new Integer(0), new Integer(0), new Integer(0), "test");
        cliente.setAsociacion(test);
        currentSession().save(cliente);
        assertNotNull(cliente);

        String nombre = instance.elimina(cliente.getId());
        assertEquals(nom, nombre);

        Cliente prueba = instance.obtiene(cliente.getId());
        assertNull(prueba);
    }
}
