/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.um.mateo.general.dao;

import java.util.HashMap;
import java.util.Map;
import mx.edu.um.mateo.general.model.Cliente;
import mx.edu.um.mateo.general.model.Cliente;
import mx.edu.um.mateo.general.model.Usuario;
import mx.edu.um.mateo.general.utils.UltimoException;
import org.hibernate.Criteria;
import org.hibernate.NonUniqueObjectException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author lobo4
 */
@Repository
@Transactional
public class ClienteDao {

    private static final Logger log = LoggerFactory.getLogger(ClienteDao.class);
    @Autowired
    private SessionFactory sessionFactory;
    private Object cliente;

    public ClienteDao() {
        log.info("Nueva instancia de ClienteDao");
    }

    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    public Map<String, Object> lista(Map<String, Object> params) {
        log.debug("Buscando lista de clientes con params {}", params);
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
        Criteria criteria = currentSession().createCriteria(Cliente.class);
        Criteria countCriteria = currentSession().createCriteria(Cliente.class);

        if (params.containsKey("union")) {
            criteria.createCriteria("union").add(Restrictions.idEq(params.get("union")));
            countCriteria.createCriteria("union").add(Restrictions.idEq(params.get("union")));
        }

        if (params.containsKey("filtro")) {
            String filtro = (String) params.get("filtro");
            filtro = "%" + filtro + "%";
            Disjunction propiedades = Restrictions.disjunction();
            propiedades.add(Restrictions.ilike("nombre", filtro));
            propiedades.add(Restrictions.ilike("nombreCompleto", filtro));
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
        params.put("asociacion", criteria.list());

        countCriteria.setProjection(Projections.rowCount());
        params.put("cantidad", (Long) countCriteria.list().get(0));

        return params;
    }

    public Cliente obtiene(Long id) {
        log.debug("Obtiene cliente con id = {}", id);
        Cliente cliente = (Cliente) currentSession().get(Cliente.class, id);
        return cliente;
    }

    public Cliente crea(Cliente cliente) {
        log.debug("Creando cliente : {}", cliente);
        currentSession().save(cliente);
        currentSession().flush();
        return cliente;
    }

    public Cliente actualiza(Cliente cliente) {
        log.debug("Actualizando cliente {}", cliente);
        
        //trae el objeto de la DB 
        Cliente nuevo = (Cliente)currentSession().get(Cliente.class, cliente.getid());
        //actualiza el objeto
        BeanUtils.copyProperties(cliente, nuevo);
        //lo guarda en la BD
        
        currentSession().update(nuevo);
        currentSession().flush();
        return nuevo;
    }

    public String elimina(Long id) throws UltimoException {
        log.debug("Eliminando cliente con id {}", id);
        Cliente cliente = obtiene(id);
        currentSession().delete(cliente);
        currentSession().flush();
        String nombre = cliente.getNombre();
        return nombre;
    }
}


