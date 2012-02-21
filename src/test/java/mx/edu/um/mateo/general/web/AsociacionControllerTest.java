/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.um.mateo.general.web;

import mx.edu.um.mateo.general.test.BaseTest;
import mx.edu.um.mateo.general.test.GenericWebXmlContextLoader;
import mx.edu.um.mateo.general.dao.AsociacionDao;
import mx.edu.um.mateo.general.model.Asociacion;
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
    public void debieraMostrarListaDeCtaMayor() throws Exception {
        this.mockMvc.perform(get("/web/asociacones")).
                andExpect(status().isOk()).
                andExpect(forwardedUrl("/WEB-INF/jsp/web/asociaciones/lista.jsp")).
                andExpect(model().attributeExists("asociaciones")).
                andExpect(model().attributeExists("paginacion")).
                andExpect(model().attributeExists("paginas")).
                andExpect(model().attributeExists("pagina"));
    }

    @Test
    public void debieraMostrarCtaMayor() throws Exception {
        Asociacion asociacion = new Asociacion("test", "test");
        asociacion = asociacionDao.crea(asociacion);

        this.mockMvc.perform(get("/web/asociacion/ver/" + asociacion.getId())).
                andExpect(status().isOk()).
                andExpect(forwardedUrl("/WEB-INF/jsp/web/asociacion/ver.jsp")).
                andExpect(model().attributeExists("asociacion"));
    }
    
    @Test
    public void debieraCrearCtaMayor() throws Exception {
        log.debug("Debiera crear asociaciones");
        Asociacion asociacion = new Asociacion("test", "test");
        asociacion = asociacionDao.crea(asociacion);

        this.mockMvc.perform(post("/web/asociacion/crea").
                param("nombre", "test").
                param("direccion", "test")).
                andExpect(status().isOk()).
                andExpect(flash().attributeExists("message")).
                andExpect(flash().attribute("message", "asociaciones.creada.message"));
    }
}
