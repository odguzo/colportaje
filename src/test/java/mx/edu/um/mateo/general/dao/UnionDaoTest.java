/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.um.mateo.general.dao;
import java.util.*;
import mx.edu.um.mateo.Constantes;
import mx.edu.um.mateo.general.model.Asociacion;
import mx.edu.um.mateo.general.model.Rol;
import mx.edu.um.mateo.general.model.Union;
import mx.edu.um.mateo.general.model.Usuario;
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
 * @author wilbert
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:mateo.xml", "classpath:security.xml"})
@Transactional
public class UnionDaoTest extends BaseTest {

    private static final Logger log = LoggerFactory.getLogger(UnionDaoTest.class);
    @Autowired
    private UnionDao instance;
    @Autowired
    private SessionFactory sessionFactory;

    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Test
    public void debieraMostrarListaDeUniones() {
        log.debug("Debiera mostrar lista de uniones");
        for (int i = 0; i < 20; i++) {
            Union union = new Union("tst-" + i);
            union.setStatus(Constantes.STATUS_ACTIVO);
            currentSession().save(union);
        }
        Map<String, Object> params = null;
        Map result = instance.lista(params);
        assertNotNull(result.get(Constantes.CONTAINSKEY_UNIONES));
        assertNotNull(result.get(Constantes.CONTAINSKEY_CANTIDAD));
        
        assertEquals(10, ((List<Union>) result.get(Constantes.CONTAINSKEY_UNIONES)).size());
        assertEquals(20, ((Long) result.get(Constantes.CONTAINSKEY_CANTIDAD)).intValue());
    }

    @Test
    public void debieraObtenerUnion() {
        log.debug("Debiera obtener union");
        Union union = new Union("tst-01");
        union.setStatus(Constantes.STATUS_ACTIVO);
        currentSession().save(union);
        assertNotNull(union.getId());
        Long id = union.getId();

        Union result = instance.obtiene(id);
        assertNotNull(result);
        assertEquals("tst-01", result.getNombre());
    }

    @Test
    public void debieraCrearUnion() {
        log.debug("Debiera crear union");
        Union union = new Union("TEST01");
        union.setStatus(Constantes.STATUS_ACTIVO);
        currentSession().save(union);
        Rol rol = new Rol("ROLE_TEST");
        currentSession().save(rol);
        Set<Rol> roles = new HashSet<>();
        roles.add(rol);
        Asociacion asociacion = new Asociacion("test", Constantes.STATUS_ACTIVO, union);
        currentSession().save(asociacion);
        Usuario usuario = new Usuario("test-01@test.com", "test-01", "TEST1", "TEST");
        usuario.setAsociacion(asociacion);
        usuario.setRoles(roles);
        currentSession().save(usuario);
        currentSession().flush();
        Long id = usuario.getId();
        assertNotNull(id);

        authenticate(usuario, usuario.getPassword(), new ArrayList<GrantedAuthority>(usuario.getRoles()));
        union = new Union("tst-01");
        union = instance.crea(union, usuario);
        assertNotNull(union.getId());
    }

    @Test
    public void debieraActualizarUnion() {
        log.debug("Debiera actualizar union");
        Union union = new Union("test");
        union.setStatus(Constantes.STATUS_ACTIVO);
        currentSession().save(union);
        Rol rol = new Rol("ROLE_TEST");
        currentSession().save(rol);
        Set<Rol> roles = new HashSet<>();
        roles.add(rol);
        Asociacion asociacion = new Asociacion("TEST01", Constantes.STATUS_ACTIVO, union);
        currentSession().save(asociacion);
        Usuario usuario = new Usuario("test-01@test.com", "test-01", "TEST1", "TEST");
        usuario.setAsociacion(asociacion);
        usuario.setRoles(roles);
        currentSession().save(usuario);
        currentSession().flush();
        Long id = usuario.getId();
        assertNotNull(id);
        id = union.getId();

        authenticate(usuario, usuario.getPassword(), new ArrayList<GrantedAuthority>(usuario.getRoles()));

        Union result = instance.obtiene(union.getId());
        assertNotNull(result);
        result.setNombre("PRUEBA");
        instance.actualiza(result);

        Union prueba = instance.obtiene(union.getId());
        assertNotNull(prueba);
        assertEquals("PRUEBA", prueba.getNombre());
    }

    @Test
    public void debieraEliminarUnion() throws UltimoException {
        log.debug("Debiera eliminar union");

        Union union = new Union("TEST01");
        union.setStatus(Constantes.STATUS_ACTIVO);
        currentSession().save(union);
        Rol rol = new Rol("ROLE_TEST");
        currentSession().save(rol);
        Set<Rol> roles = new HashSet<>();
        roles.add(rol);
        Asociacion asociacion = new Asociacion("TEST01", Constantes.STATUS_ACTIVO, union);
        currentSession().save(asociacion);
        Usuario usuario = new Usuario("test-01@test.com", "test-01", "TEST1", "TEST");
        usuario.setAsociacion(asociacion);
        usuario.setRoles(roles);
        currentSession().save(usuario);
        Long id = usuario.getId();
        assertNotNull(id);
        id = union.getId();
        currentSession().refresh(asociacion);
        currentSession().refresh(union);

        union = new Union("TEST02");
        union.setStatus(Constantes.STATUS_ACTIVO);
        currentSession().save(union);

        authenticate(usuario, usuario.getPassword(), new ArrayList<GrantedAuthority>(usuario.getRoles()));

        String nombre = instance.elimina(id);
        assertEquals("TEST01", nombre);

        Union prueba = instance.obtiene(id);
        assertEquals(Constantes.STATUS_INACTIVO, prueba.getStatus());
    }
}