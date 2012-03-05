/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.um.mateo.general.web;


import mx.edu.um.mateo.general.test.BaseTest;
import mx.edu.um.mateo.general.test.GenericWebXmlContextLoader;
import mx.edu.um.mateo.general.dao.AsociacionDao;
import mx.edu.um.mateo.general.model.Asociacion;
import mx.um.edu.mateo.Constantes;
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
import org.springframework.web.context.WebApplicationContext;

/**
 *
 * @author gibrandemetrioo
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = GenericWebXmlContextLoader.class, locations = {
    "classpath:mateo.xml",
    "classpath:security.xml",
    "classpath:dispatcher-servlet.xml"
})
public class AsociacionControllerTest extends BaseTest {

    private static final Logger log = LoggerFactory.getLogger(AsociacionControllerTest.class);
    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;
    @Autowired
    private AsociacionDao asociacionDao;

    public AsociacionControllerTest() {
    }

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
    public void debieraMostrarListaDeAsociaciones() throws Exception {
        log.debug("Debiera monstrar lista de Asociaciones");
        
        this.mockMvc.perform(get("/web/asociacion")).
                andExpect(status().isOk()).
                andExpect(forwardedUrl("/WEB-INF/jsp/web/asociacion/lista.jsp")).
                andExpect(model().attributeExists("asociaciones")).
                andExpect(model().attributeExists("paginacion")).
                andExpect(model().attributeExists("paginas")).
                andExpect(model().attributeExists("pagina"));
    }

    @Test
    public void debieraMostrarAsociacion() throws Exception {
        log.debug("Debiera mostrar asociacion");
        Asociacion asociacion = new Asociacion("test", "test", "ts");
        asociacion = asociacionDao.crea(asociacion);

        this.mockMvc.perform(get("/web/asociacion/ver/" + asociacion.getId())).
                andExpect(status().isOk()).
                andExpect(forwardedUrl("/WEB-INF/jsp/web/asociacion/ver.jsp")).
                andExpect(model().attributeExists("asociaciones"));
    }

    @Test
    public void debieraCrearAsociacion() throws Exception {
        log.debug("Debiera crear asociacion");
        
        this.mockMvc.perform(post("/web/asociacion/crea").param("nombre", "test1").param("direccion", "test2").param("status", Constantes.STATUS_ACTIVO)).
                andExpect(status().isOk()).
                andExpect(flash().attributeExists("message")).
                andExpect(flash().attribute("message", "asociacion.creada.message"));
    }

    @Test
    public void debieraActualizarAsociacion() throws Exception {
        log.debug("Debiera actualizar asociacion");

        this.mockMvc.perform(post("/web/asociacion/actualiza").param("nombre", "test3").param("direccion", "test5").param("status", Constantes.STATUS_ACTIVO)).
                andExpect(status().isOk()).
                andExpect(flash().attributeExists("message")).
                andExpect(flash().attribute("message", "asociacion.actualizada.message"));
    }

    @Test
    public void debieraEliminarAsociacion() throws Exception {
        log.debug("Debiera eliminar asociacion");
        Asociacion asociacion = new Asociacion("test8", "test6", Constantes.STATUS_ACTIVO);
        asociacionDao.crea(asociacion);

        this.mockMvc.perform(post("/web/asociacion/elimina").param("id", asociacion.getId().toString())).
                andExpect(status().isOk()).
                andExpect(flash().attributeExists("message")).
                andExpect(flash().attribute("message", "asociacion.eliminada.message"));
    }
}