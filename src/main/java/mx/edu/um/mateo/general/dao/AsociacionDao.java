/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.um.mateo.general.dao;

import java.util.HashMap;
import java.util.Map;
import mx.edu.um.mateo.general.dao.EmpresaDao;
import mx.edu.um.mateo.general.model.*;
import mx.edu.um.mateo.general.utils.UltimoException;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author gibrandemetrioo
 */
@Repository
@Transactional
public class AsociacionDao {
    
    private static final Logger log = LoggerFactory.getLogger(AsociacionDao.class);
    @Autowired
    private SessionFactory sessionFactory;
    
    public AsociacionDao () {
        log.info("Se ha creado una nueva AsociacionDao");
    }
    
    
    
    private Session currentSession () {
        return sessionFactory.getCurrentSession();
    }
    
    
    
    public Map<String, Object> lista(Map<String, Object> params) {
        log.debug("Buscando lista de asociacion con params {}", params);
        if (params == null) {
            params = new HashMap<>();
        }

        if (!params.containsKey("max")) {
            params.put("max", 10);
        } else {
            params.put("max", Math.min((Integer) params.get("max"), 100));
        }

        if (params.containsKey("pagina")) {
            Long pagina = (Long) params.get("pagina");
            Long offset = (pagina - 1) * (Integer) params.get("max");
            params.put("offset", offset.intValue());
        }

        if (!params.containsKey("offset")) {
            params.put("offset", 0);
        }
        
        Criteria criteria = currentSession().createCriteria(Asociacion.class);
        Criteria countCriteria = currentSession().createCriteria(Asociacion.class);
        
        if (params.containsKey("filtro")) {
            String filtro = (String) params.get("filtro");
            filtro = "%" + filtro + "%";
            Disjunction propiedades = Restrictions.disjunction();
            propiedades.add(Restrictions.ilike("nombre", filtro));
            propiedades.add(Restrictions.ilike("direccion", filtro));
            propiedades.add(Restrictions.ilike("status", filtro));
           
            criteria.add(propiedades);
            countCriteria.add(propiedades);
        }

        if (params.containsKey("order")) {
            String campo = (String) params.get("order");
            if (params.get("sort").equals("desc")) {
                criteria.addOrder(Order.desc(campo));
            } else {
                criteria.addOrder(Order.asc(campo));
            }
        }

        if (!params.containsKey("reporte")) {
            criteria.setFirstResult((Integer) params.get("offset"));
            criteria.setMaxResults((Integer) params.get("max"));
        }
        params.put("asociaciones", criteria.list());
        countCriteria.setProjection(Projections.rowCount());
        params.put("cantidad", (Long) countCriteria.list().get(0));

        return params;
    }

    public Asociacion obtiene(Long id) {
        Asociacion asociacion = (Asociacion) currentSession().get(Asociacion.class, id);
        return asociacion;
    }

    public Asociacion crea(Asociacion asociacion) {
        currentSession().save(asociacion);
        return asociacion;
    }

     public Asociacion actualiza(Asociacion asociacion) {
        currentSession().saveOrUpdate(asociacion);
        return asociacion;
    }
    
    public String elimina(Long id) throws UltimoException {
        Asociacion asociacion = obtiene(id);
        currentSession().delete(asociacion);
        String nombre = asociacion.getNombre();
        return nombre;
    }
    
}
