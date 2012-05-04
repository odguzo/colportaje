/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.um.mateo.general.web;
import mx.edu.um.mateo.Constantes;
import mx.edu.um.mateo.general.dao.AsociacionDao;
import mx.edu.um.mateo.general.dao.ClienteDao;
import mx.edu.um.mateo.general.dao.UnionDao;
import mx.edu.um.mateo.general.model.Asociacion;
import mx.edu.um.mateo.general.model.Cliente;
import mx.edu.um.mateo.general.model.Union;
import mx.edu.um.mateo.general.test.BaseTest;
import mx.edu.um.mateo.general.test.GenericWebXmlContextLoader;
import static org.junit.Assert.assertNotNull;
import org.junit.*;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.server.MockMvc;
import static org.springframework.test.web.server.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.server.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.*;
import org.springframework.test.web.server.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

/**
 *
 * @author lobo4
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = GenericWebXmlContextLoader.class, locations = {
    "classpath:mateo.xml",
    "classpath:security.xml",
    "classpath:dispatcher-servlet.xml"
})
@Transactional
public class ClienteControllerTest extends BaseTest {

    private static final Logger log = LoggerFactory.getLogger(ClienteControllerTest.class);
    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;
    @Autowired
    private ClienteDao clienteDao;
    @Autowired
    private UnionDao unionDao;
    @Autowired
    private AsociacionDao asociacionDao;

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webApplicationContextSetup(wac).build();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void debieraMostrarListaDeCliente() throws Exception {
        log.debug("Debiera monstrar lista de clientees");
        
        Union union = new Union("test");
        union.setStatus(Constantes.STATUS_ACTIVO);
        unionDao.crea(union);
        Asociacion asociacion = new Asociacion("test-01", Constantes.STATUS_ACTIVO, union);
        asociacionDao.crea(asociacion);

        for (int i = 0; i < 20; i++) {
            Cliente cliente = new Cliente("test", "test", "010101010101", asociacion);
            clienteDao.crea(cliente);
            assertNotNull(cliente);
        }

        this.mockMvc.perform(get(Constantes.PATH_COLEGIO))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("/WEB-INF/jsp/" + Constantes.PATH_COLEGIO_LISTA + ".jsp"))
                .andExpect(model().attributeExists(Constantes.CONTAINSKEY_COLEGIOS))
                .andExpect(model().attributeExists(Constantes.CONTAINSKEY_PAGINACION))
                .andExpect(model().attributeExists(Constantes.CONTAINSKEY_PAGINAS))
                .andExpect(model().attributeExists(Constantes.CONTAINSKEY_PAGINA));
    }

   @Test
    public void debieraMostrarCliente() throws Exception {
        log.debug("Debiera mostrar cliente");
        
        Union union = new Union("test");
        union.setStatus(Constantes.STATUS_ACTIVO);
        unionDao.crea(union);
        Asociacion asociacion = new Asociacion("test-01", Constantes.STATUS_ACTIVO, union);
        asociacionDao.crea(asociacion);
        
        Cliente cliente = new Cliente("test", "test", "010101010101", asociacion);
        clienteDao.crea(cliente);
        assertNotNull(cliente);

        this.mockMvc.perform(get(Constantes.PATH_CLIENTE_VER +"/"+ cliente.getId()))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("/WEB-INF/jsp/" + Constantes.PATH_CLIENTE_VER + ".jsp"))
                .andExpect(model()
                .attributeExists(Constantes.ADDATTRIBUTE_CLIENTE));
    }

    @Test
    public void debieraCrearCliente() throws Exception {
        log.debug("Debiera crear cliente");

        Union union = new Union("test");
        union.setStatus(Constantes.STATUS_ACTIVO);
        unionDao.crea(union);
        Asociacion asociacion = new Asociacion("test-01", Constantes.STATUS_ACTIVO, union);
        asociacionDao.crea(asociacion);
        
        this.mockMvc.perform(post(Constantes.PATH_CLIENTE_CREA)
                .param("nombre", "test")
                .param("apellidoP", "test")
                .param("telefono", "010101010101")
                .param("asociacion", asociacion.getId().toString()))
                .andExpect(status().isOk());
                //.andExpect(flash().attributeExists(Constantes.CONTAINSKEY_MESSAGE))
                //.andExpect(flash().attribute(Constantes.CONTAINSKEY_MESSAGE, "cliente.creado.message"));
    }

    @Test
    public void debieraActualizarCliente() throws Exception {
        log.debug("Debiera actualizar cliente");
       Union union = new Union("test");
        union.setStatus(Constantes.STATUS_ACTIVO);
        unionDao.crea(union);
        Asociacion asociacion = new Asociacion("test-01", Constantes.STATUS_ACTIVO, union);
        asociacionDao.crea(asociacion);
        
        Cliente cliente = new Cliente("test", "test", "010101010101", asociacion);
        clienteDao.crea(cliente);
        assertNotNull(cliente);


        this.mockMvc.perform(post(Constantes.PATH_CLIENTE_ACTUALIZA)
                .param("id",cliente.getId().toString())
                .param("version", cliente.getVersion().toString())
                .param("nombre", "test1")
                .param("apellidoP", "test1")
                .param("telefono", "010101010101")
                .param("asociacion", asociacion.getId().toString()))
                .andExpect(status().isOk());
                //.andExpect(flash().attributeExists(Constantes.CONTAINSKEY_MESSAGE))
                //.andExpect(flash().attribute(Constantes.CONTAINSKEY_MESSAGE, "cliente.actualizado.message"));
    }

    @Test
    public void debieraEliminarCliente() throws Exception {
        log.debug("Debiera eliminar cliente");
        
        Union union = new Union("test");
        union.setStatus(Constantes.STATUS_ACTIVO);
        unionDao.crea(union);
        Asociacion asociacion = new Asociacion("test-01", Constantes.STATUS_ACTIVO, union);
        asociacionDao.crea(asociacion);
        
        Cliente cliente = new Cliente("test", "test", "010101010101", asociacion);
        clienteDao.crea(cliente);
        assertNotNull(cliente);


        this.mockMvc.perform(post(Constantes.PATH_CLIENTE_ELIMINA)
                .param("id", cliente.getId().toString()))
                .andExpect(status().isOk())
                .andExpect(flash().attributeExists(Constantes.CONTAINSKEY_MESSAGE))
                    .andExpect(flash().attribute(Constantes.CONTAINSKEY_MESSAGE, "cliente.eliminado.message"));
    }
}