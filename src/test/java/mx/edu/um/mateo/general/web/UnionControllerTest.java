/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.um.mateo.general.web;

import java.util.ArrayList;
import mx.edu.um.mateo.general.dao.UnionDao;
import mx.edu.um.mateo.general.dao.RolDao;
import mx.edu.um.mateo.general.dao.UsuarioDao;
import mx.edu.um.mateo.general.model.Empresa;
import mx.edu.um.mateo.general.model.Union;
import mx.edu.um.mateo.general.model.Rol;
import mx.edu.um.mateo.general.model.Usuario;
import mx.edu.um.mateo.general.test.BaseTest;
import mx.edu.um.mateo.general.test.GenericWebXmlContextLoader;
import mx.edu.um.mateo.inventario.model.Almacen;
import mx.um.edu.mateo.Constantes;
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
 * @author wilbert
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = GenericWebXmlContextLoader.class, locations = {
    "classpath:mateo.xml",
    "classpath:security.xml",
    "classpath:dispatcher-servlet.xml"
})
public class UnionControllerTest {
     private static final Logger log = LoggerFactory.getLogger(UnionControllerTest.class);
    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;
    @Autowired
    private UnionDao unionDao;

    public UnionControllerTest() {
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
    public void debieraMostrarListaDeUnion() throws Exception {
        log.debug("Debiera monstrar lista de union");
        
        this.mockMvc.perform(get("/web/union")).
                andExpect(status().isOk()).
                andExpect(forwardedUrl("/WEB-INF/jsp/web/union/lista.jsp")).
                andExpect(model().attributeExists("uniones")).
                andExpect(model().attributeExists("paginacion")).
                andExpect(model().attributeExists("paginas")).
                andExpect(model().attributeExists("pagina"));
    }

    @Test
    public void debieraMostrarUnion() throws Exception {
        log.debug("Debiera mostrar union");
        Union union = new Union("test", "t"); 
        union = unionDao.crea(union);

        this.mockMvc.perform(get("/web/union/ver/" + union.getId())).
                andExpect(status().isOk()).
                andExpect(forwardedUrl("/WEB-INF/jsp/web/union/ver.jsp")).
                andExpect(model().attributeExists("union"));
    }

    @Test
    public void debieraCrearUnion() throws Exception {
        log.debug("Debiera crear union");
        
        this.mockMvc.perform(post("/web/union/crea").param("nombre", "test0").param("status", Constantes.STATUS_ACTIVO)).
                andExpect(status().isOk()).andExpect(flash().attributeExists("message")).andExpect(flash().attribute("message", "union.creada.message"));
    }

    @Test
    public void debieraActualizarUnion() throws Exception {
        log.debug("Debiera actualizar union");

        this.mockMvc.perform(post("/web/union/actualiza").param("nombre", "test1").param("status", Constantes.STATUS_ACTIVO)).
                andExpect(status().isOk()).andExpect(flash().attributeExists("message")).andExpect(flash().attribute("message", "union.actualizada.message"));
    }

  @Test
    public void debieraEliminarUnion() throws Exception {
        log.debug("Debiera eliminar union");
        Union union = new Union("test6", Constantes.STATUS_ACTIVO);
        unionDao.crea(union);

        this.mockMvc.perform(post("/web/union/elimina").param("id", union.getId().toString())).
                andExpect(status().isOk()).
                andExpect(flash().attributeExists("message")).
                andExpect(flash().attribute("message", "union.eliminada.message"));
    }
  
}
