/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.um.mateo.general.dao;

import java.util.List;
import java.util.Map;
import mx.edu.um.mateo.general.model.Asociado;
import mx.edu.um.mateo.general.dao.AsociadoDao;
import mx.edu.um.mateo.general.model.TipoAsociado;
import mx.edu.um.mateo.general.utils.UltimoException;
import mx.um.edu.mateo.Constantes;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.Assert.*;
/**
 *
 * @author gibrandemetrioo
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:mateo.xml", "classpath:security.xml"})
@Transactional
public class AsociadoDaoTest {
   
    private static final Logger log = LoggerFactory.getLogger(AsociadoDao.class);
    @Autowired
    private AsociadoDao instance;
    @Autowired
    private SessionFactory sessionFactory;
    
   private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }
    
    /**
     * Test of lista method, of class AsociadoDao.
     */
    @Test
    public void debieraMostrarListaDeAsociado() {
        log.debug("Debiera mostrar lista de Asociados");
        for (int i = 0; i < 20; i++) {
           Asociado asociado = new Asociado("test-00"+i,"test-00"+i,"test-00"+i,"test-00"+i, Constantes.STATUS_ACTIVO);
            currentSession().save(asociado);
            assertNotNull(asociado);
            log.debug("asociado>>" + asociado);
        }
        Map<String, Object> params = null;
        Map result = instance.lista(params);
        assertNotNull(result.get(Constantes.CONTAINSKEY_ASOCIADOS));
        assertNotNull(result.get(Constantes.CONTAINSKEY_CANTIDAD));
        assertEquals(10, ((List<Asociado>) result.get(Constantes.CONTAINSKEY_ASOCIADOS)).size());
        assertEquals(20, ((Long) result.get(Constantes.CONTAINSKEY_CANTIDAD)).intValue());
    }
    
    
}


