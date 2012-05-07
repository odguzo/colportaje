/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.um.mateo.general.dao;

import java.util.*;
import mx.edu.um.mateo.Constantes;
import mx.edu.um.mateo.general.model.*;
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
import org.springframework.security.core.GrantedAuthority;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author lobo4
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
    public void debieraMostrarListaDeClientees() {
        log.debug("Debiera mostrar lista de clientees");
        Union union = new Union("tst-01");
        union.setStatus(Constantes.STATUS_ACTIVO);
        currentSession().save(union);
        Asociacion asociacion =new Asociacion("test", "test", union);
        asociacion.setStatus(Constantes.STATUS_ACTIVO);
        currentSession().save(asociacion);
        for (int i = 0; i < 20; i++) {
            Cliente cliente = new Cliente("test", "test", "test", "test", 000000, 000000, 000000000000, 000000000000, "test", asociacion);
            currentSession().save(cliente);
        }

        Map<String, Object> params = new HashMap<>();
        params.put("union", union.getId());
        Map result = instance.lista(params);
        assertNotNull(result.get("clientes"));
        assertNotNull(result.get("cantidad"));
        assertEquals(10, ((List<Cliente>) result.get("clientees")).size());
        assertEquals(20, ((Long) result.get("cantidad")).intValue());
    }

    /**
     * Test of obtiene method, of class ClienteDao.
     */
    @Test
    public void debieraObtenerCliente() {
        log.debug("Debiera obtener cliente");
        Union union = new Union("test");
        union.setStatus(Constantes.STATUS_ACTIVO);
        currentSession().save(union);
        Asociacion asociacion =new Asociacion("test", "test", union);
        asociacion.setStatus(Constantes.STATUS_ACTIVO);
        currentSession().save(asociacion);
        Cliente cliente = new Cliente("test", "test", "test", "test", 000000, 000000, 000000000000, 000000000000, "test", asociacion);
        currentSession().save(cliente);
        String id = cliente.getNombre();
        assertNotNull(id);
        Cliente result = instance.obtiene(Long.MIN_VALUE);
        assertEquals("test-01", result.getNombre());
    }

    /**
     * Test of crea method, of class ClienteDao.
     */
    @Test
    public void debieraCrearCliente() {
        log.debug("Debiera crear cliente");
        Union union = new Union("TEST01");
        union.setStatus(Constantes.STATUS_ACTIVO);
        currentSession().save(union);
        Asociacion asociacion =new Asociacion("test", "test", union);
        asociacion.setStatus(Constantes.STATUS_ACTIVO);
        currentSession().save(asociacion);
        Rol rol = new Rol("ROLE_TEST");
        currentSession().save(rol);
        Set<Rol> roles = new HashSet<>();
        roles.add(rol);
        Cliente cliente = new Cliente("test", "test", "test", "test", 000000, 000000, 000000000000, 000000000000, "test", asociacion);
        currentSession().save(cliente);
        Usuario usuario = new Usuario("test-01@test.com", "test-01", "TEST1", "TEST");
        usuario.setCliente(cliente);
        usuario.setRoles(roles);
        currentSession().save(usuario);
        Long id = usuario.getId();
        assertNotNull(id);

        cliente = new Cliente("test", "test", "test", "test", 000000, 000000, 000000000000, 000000000000, "test", asociacion);
        cliente = instance.crea(cliente);
        assertNotNull(cliente);
        assertNotNull(cliente.getNombre());
        assertEquals("test", cliente.getNombre());
    }

    /**
     * Test of actualiza method, of class ClienteDao.
     */
    @Test
    public void debieraActualizarCliente() {
        log.debug("Debiera actualizar cliente");
        Union union = new Union("TEST01");
        union.setStatus(Constantes.STATUS_ACTIVO);
        currentSession().save(union);
        Asociacion asociacion =new Asociacion("test", "test", union);
        asociacion.setStatus(Constantes.STATUS_ACTIVO);
        currentSession().save(asociacion);
        assertNotNull(union);
        Rol rol = new Rol("ROLE_TEST");
        currentSession().save(rol);
        assertNotNull(rol);
        Set<Rol> roles = new HashSet<>();
        roles.add(rol);
        Cliente cliente = new Cliente("test", "test", "test", "test", 000000, 000000, 000000000000, 000000000000, "test", asociacion);
        currentSession().save(cliente);
        assertNotNull(cliente);
        Usuario usuario = new Usuario("test-01@test.com", "test-01", "TEST1", "TEST");
        usuario.setCliente(cliente);
        usuario.setRoles(roles);
        currentSession().save(usuario);
        Long id = usuario.getId();
        assertNotNull(id);

        authenticate(usuario, usuario.getPassword(), new ArrayList<GrantedAuthority>(usuario.getRoles()));

        log.debug("Obteniendo cliente");
        Cliente result = instance.obtiene(cliente.getid());
        assertNotNull(result);
        log.debug("Modificando nombre");
        result.setNombre("PRUEBA");
        log.debug("Enviando a actualizar cliente");
        instance.actualiza(result);

        log.debug("Obteniendo cliente nuevamente");
        Cliente prueba = instance.obtiene(cliente.getid());
        log.debug("Haciendo asserts");
        assertNotNull(prueba);
        assertEquals("PRUEBA", prueba.getNombre());
    }

    /**
     * Test of elimina method, of class ClienteDao.
     */
    @Test(expected = UltimoException.class)
    public void noDebieraEliminarCliente() throws Exception {
        log.debug("Debiera actualizar cliente");
        Union union = new Union("TEST01");
        union.setStatus(Constantes.STATUS_ACTIVO);
        currentSession().save(union);
        Asociacion asociacion =new Asociacion("test", "test", union);
        asociacion.setStatus(Constantes.STATUS_ACTIVO);
        currentSession().save(asociacion);
        Rol rol = new Rol("ROLE_TEST");
        currentSession().save(rol);
        Set<Rol> roles = new HashSet<>();
        roles.add(rol);
        Cliente cliente = new Cliente("test", "test", "test", "test", 000000, 000000, 000000000000, 000000000000, "test", asociacion);
        currentSession().save(cliente);
        Usuario usuario = new Usuario("test-01@test.com", "test-01", "TEST1", "TEST");
        usuario.setCliente(cliente);
        usuario.setRoles(roles);
        currentSession().save(usuario);
        Long id = usuario.getId();
        assertNotNull(id);
        assertNotNull(id);

        String nombre = instance.elimina(id);
        assertNotNull(nombre);
        assertEquals("TEST01", nombre);

        Cliente prueba2 = instance.obtiene(id);
        assertNull(prueba2);
    }

    @Test
    public void debieraEliminarCliente() throws UltimoException {
        log.debug("Debiera actualizar cliente");

        Union union = new Union("TEST01");
        union.setStatus(Constantes.STATUS_ACTIVO);
        currentSession().save(union);
        Asociacion asociacion =new Asociacion("test", "test", union);
        asociacion.setStatus(Constantes.STATUS_ACTIVO);
        currentSession().save(asociacion);
        Rol rol = new Rol("ROLE_TEST");
        currentSession().save(rol);
        Set<Rol> roles = new HashSet<>();
        roles.add(rol);
        Cliente cliente = new Cliente("test", "test", "test", "test", 000000, 000000, 000000000000, 000000000000, "test", asociacion);
        currentSession().save(cliente);
        Usuario usuario = new Usuario("test-01@test.com", "test-01", "TEST1", "TEST");
        usuario.setCliente(cliente);
        usuario.setRoles(roles);
        currentSession().save(usuario);
        Long id = usuario.getId();
        assertNotNull(id);
        id = cliente.getid();
        currentSession().refresh(cliente);
        currentSession().refresh(union);

        cliente = new Cliente("test", "test", "test", "test", 000000, 000000, 000000000000, 000000000000, "test", asociacion);
        currentSession().save(cliente);

        authenticate(usuario, usuario.getPassword(), new ArrayList<GrantedAuthority>(usuario.getRoles()));

        String nombre = instance.elimina(id);
        assertNotNull(nombre);
        assertEquals("tst-01", nombre);

        cliente = instance.obtiene(id);
        assertNull(cliente);
    }
}
