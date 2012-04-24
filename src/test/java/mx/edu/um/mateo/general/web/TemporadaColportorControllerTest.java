/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.um.mateo.general.web;
import java.text.SimpleDateFormat;
import java.util.Date;
import mx.edu.um.mateo.Constantes;
import mx.edu.um.mateo.general.dao.TemporadaColportorDao;
import mx.edu.um.mateo.general.model.*;
import mx.edu.um.mateo.general.test.GenericWebXmlContextLoader;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
 * @author gibrandemetrioo
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = GenericWebXmlContextLoader.class, locations = {
    "classpath:mateo.xml",
    "classpath:security.xml",
    "classpath:dispatcher-servlet.xml"
})
@Transactional
public class TemporadaColportorControllerTest {
    private static final Logger log = LoggerFactory.getLogger(TemporadaColportorControllerTest.class);
    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;
    @Autowired
    private TemporadaColportorDao temporadaColportorDao;
    @Autowired
    private SessionFactory sessionFactory;
    private Session currentSession() {
        return sessionFactory.getCurrentSession();
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
    public void debieraMostrarListaDeTemporadaColportor() throws Exception {
        log.debug("Debiera monstrar lista Temporada Colportor");
        Colportor test = new Colportor(Constantes.NOMBRE, Constantes.STATUS_ACTIVO, Constantes.CLAVE,Constantes.DIRECCION,Constantes.CORREO,Constantes.TELEFONO);
        currentSession().save(test);
        Union union = new Union("test");
        union.setStatus(Constantes.STATUS_ACTIVO);
        currentSession().save(union);
        Asociacion test2 = new Asociacion("test", Constantes.STATUS_ACTIVO, union);
        currentSession().save(test2);
        Asociado test3 = new Asociado("test","test","test","test", Constantes.STATUS_ACTIVO);
        currentSession().save(test3);
        Temporada test4 = new Temporada ("test");
        currentSession().save(test4);
        for (int i = 0; i < 20; i++) {
            TemporadaColportor temporadaColportor = new TemporadaColportor(Constantes.STATUS_ACTIVO+i,"TEST","TEST");
            temporadaColportor.setColportor(test);
            temporadaColportor.setAsociacion(test2);
            temporadaColportor.setAsociado(test3);
            temporadaColportor.setTemporada(test4);
            temporadaColportor.setUnion(union);
            temporadaColportorDao.crea(temporadaColportor);
            assertNotNull(temporadaColportor);
        }

        this.mockMvc.perform(get(Constantes.PATH_TEMPORADACOLPORTOR))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("/WEB-INF/jsp/" + Constantes.PATH_TEMPORADACOLPORTOR_LISTA+ ".jsp"))
                .andExpect(model().attributeExists(Constantes.CONTAINSKEY_TEMPORADACOLPORTORES))
                .andExpect(model().attributeExists(Constantes.CONTAINSKEY_PAGINACION))
                .andExpect(model().attributeExists(Constantes.CONTAINSKEY_PAGINAS))
                .andExpect(model().attributeExists(Constantes.CONTAINSKEY_PAGINA));
    }
    @Test
    public void debieraMostrarTemporadaColportor() throws Exception {
        log.debug("Debiera mostrar  temporada colpotor");
        Colportor test = new Colportor(Constantes.NOMBRE, Constantes.STATUS_ACTIVO, Constantes.CLAVE,Constantes.DIRECCION,Constantes.CORREO,Constantes.TELEFONO);
        currentSession().save(test);
        Union union = new Union("test");
        union.setStatus(Constantes.STATUS_ACTIVO);
        currentSession().save(union);
        Asociacion test2 = new Asociacion("test", Constantes.STATUS_ACTIVO, union);
        currentSession().save(test2);
        Asociado test3 = new Asociado("test", "test", "test", "test", Constantes.STATUS_ACTIVO);
        currentSession().save(test3);
        Temporada test4 = new Temporada("test");
        currentSession().save(test4);
        TemporadaColportor temporadaColportor = new TemporadaColportor(Constantes.STATUS_ACTIVO,"TEST","TEST");
        temporadaColportor.setColportor(test);
        temporadaColportor.setAsociacion(test2);
        temporadaColportor.setAsociado(test3);
        temporadaColportor.setTemporada(test4);
        temporadaColportor.setUnion(union);
        temporadaColportor = temporadaColportorDao.crea(temporadaColportor);
        assertNotNull(temporadaColportor);

        this.mockMvc.perform(get(Constantes.PATH_TEMPORADACOLPORTOR_VER +"/"+ temporadaColportor.getId()))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("/WEB-INF/jsp/" + Constantes.PATH_TEMPORADACOLPORTOR_VER + ".jsp"))
                .andExpect(model()
                .attributeExists(Constantes.ADDATTRIBUTE_TEMPORADACOLPORTOR));
    }
    @Test
    public void debieraCrearTemporadaColportor() throws Exception {
        log.debug("Debiera crear cuenta de Temporada Colportor");
        Colportor test = new Colportor(Constantes.NOMBRE, Constantes.STATUS_ACTIVO, Constantes.CLAVE, Constantes.DIRECCION, Constantes.CORREO, Constantes.TELEFONO);
        currentSession().save(test);
        Union union = new Union("test");
        union.setStatus(Constantes.STATUS_ACTIVO);
        currentSession().save(union);
        Asociacion test2 = new Asociacion("test", Constantes.STATUS_ACTIVO, union);
        currentSession().save(test2);
        Asociado test3 = new Asociado("test", "test", "test", "test", Constantes.STATUS_ACTIVO);
        currentSession().save(test3);
        Temporada test4 = new Temporada("test");
        currentSession().save(test4);
        
        SimpleDateFormat sdf = new SimpleDateFormat(Constantes.DATE_SHORT_HUMAN_PATTERN);
        this.mockMvc.perform(
                post(Constantes.PATH_TEMPORADACOLPORTOR_CREA)
                .param("fecha", sdf.format(new Date()))
                .param("status", "tt")
                .param("objetivo", "test")
                .param("observaciones", "test"))
                .andExpect(status().isOk())
                .andExpect(flash().attributeExists(Constantes.CONTAINSKEY_MESSAGE))
                .andExpect(flash().attribute(Constantes.CONTAINSKEY_MESSAGE, "temporadaColportor.creada.message"));
    }
    @Test
    public void debieraActualizarTemporadaColportor() throws Exception {
        log.debug("Debiera actualizar  temporada Colportor");
        Colportor test = new Colportor(Constantes.NOMBRE, Constantes.STATUS_ACTIVO, Constantes.CLAVE, Constantes.DIRECCION, Constantes.CORREO, Constantes.TELEFONO);
        currentSession().save(test);
        Union union = new Union("test");
        union.setStatus(Constantes.STATUS_ACTIVO);
        currentSession().save(union);
        Asociacion test2 = new Asociacion("test", Constantes.STATUS_ACTIVO, union);
        currentSession().save(test2);
        Asociado test3 = new Asociado("test", "test", "test", "test", Constantes.STATUS_ACTIVO);
        currentSession().save(test3);
        Temporada test4 = new Temporada("test");
        currentSession().save(test4);
        TemporadaColportor temporadaColportor = new TemporadaColportor(Constantes.STATUS_ACTIVO,"TEST","TEST");
        temporadaColportor.setColportor(test);
        temporadaColportor.setAsociacion(test2);
        temporadaColportor.setAsociado(test3);
        temporadaColportor.setTemporada(test4);
        temporadaColportor.setUnion(union);
        temporadaColportor = temporadaColportorDao.crea(temporadaColportor);
        assertNotNull(temporadaColportor);
        
        SimpleDateFormat sdf = new SimpleDateFormat(Constantes.DATE_SHORT_HUMAN_PATTERN);
        this.mockMvc.perform(post(Constantes.PATH_TEMPORADACOLPORTOR_ACTUALIZA)
                .param("id",temporadaColportor.getId().toString())
                .param("version", temporadaColportor.getVersion().toString())
                .param("status", "t")
                .param("fecha", sdf.format(new Date()))
                .param("objetivo", "test")
                .param("observaciones","test"))
                .andExpect(status().isOk())
                .andExpect(flash().attributeExists(Constantes.CONTAINSKEY_MESSAGE))
                .andExpect(flash().attribute(Constantes.CONTAINSKEY_MESSAGE, "temporadaColportor.actualizada.message"));
    }
    @Test
    public void debieraEliminarTemporadaColportor() throws Exception {
        log.debug("Debiera eliminar  temporada Colportor");
        Colportor test = new Colportor(Constantes.NOMBRE, Constantes.STATUS_ACTIVO, Constantes.CLAVE, Constantes.DIRECCION, Constantes.CORREO, Constantes.TELEFONO);
        currentSession().save(test);
        Union union = new Union("test");
        union.setStatus(Constantes.STATUS_ACTIVO);
        currentSession().save(union);
        Asociacion test2 = new Asociacion("test", Constantes.STATUS_ACTIVO, union);
        currentSession().save(test2);
        Asociado test3 = new Asociado("test", "test", "test", "test", Constantes.STATUS_ACTIVO);
        currentSession().save(test3);
        Temporada test4 = new Temporada("test");
        currentSession().save(test4);
        
        TemporadaColportor temporadaColportor = new TemporadaColportor(Constantes.STATUS_ACTIVO,"TEST","TEST");
        temporadaColportor.setColportor(test);
        temporadaColportor.setAsociacion(test2);
        temporadaColportor.setAsociado(test3);
        temporadaColportor.setTemporada(test4);
        temporadaColportor.setUnion(union);
        temporadaColportorDao.crea(temporadaColportor);
        assertNotNull(temporadaColportor);

        this.mockMvc.perform(post(
                Constantes.PATH_TEMPORADACOLPORTOR_ELIMINA)
                .param("id", temporadaColportor.getId().toString()))
                .andExpect(status().isOk())
                .andExpect(flash()
                .attributeExists(Constantes.CONTAINSKEY_MESSAGE))
                .andExpect(flash()
                .attribute(Constantes.CONTAINSKEY_MESSAGE, "temporadaColportor.eliminada.message"));
    }
    
    }
