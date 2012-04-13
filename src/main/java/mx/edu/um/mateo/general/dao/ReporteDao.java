/*
 * The MIT License
 *
 * Copyright 2012 Universidad de Montemorelos A. C.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package mx.edu.um.mateo.general.dao;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import mx.edu.um.mateo.Constantes;
import mx.edu.um.mateo.general.model.Asociacion;
import mx.edu.um.mateo.general.model.Reporte;
import mx.edu.um.mateo.general.model.Union;
import mx.edu.um.mateo.general.model.Usuario;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author J. David Mendoza <jdmendoza@um.edu.mx>
 */
@Repository
@Transactional
public class ReporteDao {

    private static final Logger log = LoggerFactory.getLogger(ReporteDao.class);
    @Autowired
    private SessionFactory sessionFactory;

    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    public ReporteDao() {
    }

    private Reporte buscaReporteAdminstrativo(String nombre) {
        Query query = currentSession().createQuery("select r from Reporte r where r.nombre = :nombre");
        query.setString("nombre", nombre);
        Reporte reporte = (Reporte) query.uniqueResult();
        return reporte;
    }

    private Reporte buscaReportePorUnion(String nombre, Long unionId) {
        Query query = currentSession().createQuery("select r from Union o inner join o.reportes r where o.id = :id and r.nombre = :nombre");
        query.setLong("id", unionId);
        query.setString("nombre", nombre);
        Reporte reporte = (Reporte) query.uniqueResult();
        return reporte;
    }

    private Reporte buscaReportePorAsociacion(String nombre, Long asociacionId) {
        Query query = currentSession().createQuery("select r from Asociacion e inner join e.reportes r where e.id = :id and r.nombre = :nombre");
        query.setLong("id", asociacionId);
        query.setString("nombre", nombre);
        Reporte reporte = (Reporte) query.uniqueResult();
        return reporte;
    }

    public JasperReport obtieneReporteAdministrativo(String nombre) {
        Reporte reporte = buscaReporteAdminstrativo(nombre);
        return reporte.getReporte();
    }

    public JasperReport obtieneReportePorUnion(String nombre, Long unionId) {
        Reporte reporte = buscaReportePorUnion(nombre, unionId);
        return reporte.getReporte();
    }

    public JasperReport obtieneReportePorAsociacion(String nombre, Long asociacionId) {
        Reporte reporte = buscaReportePorAsociacion(nombre, asociacionId);

        return reporte.getReporte();
    }

    public void inicializa() {
        log.debug("Inicializando reportes administrativos");
        List<String> nombres = new ArrayList<>();
        nombres.add("uniones");
        inicializaReportes(nombres);
    }

    public List<Reporte> inicializaReportes(List<String> nombres) {
        List<Reporte> reportes = new ArrayList<>();
        for (String nombre : nombres) {
            Query query = currentSession().createQuery("select r from Reporte r where r.nombre = :nombre");
            query.setString("nombre", nombre);
            Reporte reporte = (Reporte) query.uniqueResult();
            if (reporte == null) {
                try {
                    JasperDesign jd = JRXmlLoader.load(this.getClass().getResourceAsStream("/reportes/" + nombre + ".jrxml"));
                    JasperReport jr = JasperCompileManager.compileReport(jd);
                    byte[] fuente = obtainByteData(this.getClass().getResourceAsStream("/reportes/" + nombre + ".jrxml"));
                    byte[] compilado = obtainByteData(jr);
                    reporte = new Reporte();
                    reporte.setNombre(nombre);
                    reporte.setFuente(fuente);
                    reporte.setCompilado(compilado);
                    Date fecha = new Date();
                    reporte.setFechaCreacion(fecha);
                    reporte.setFechaModificacion(fecha);
                    currentSession().save(reporte);
                } catch (IOException | JRException e) {
                    log.error("No se pudo inicializar el reporte " + nombre, e);
                }
            }
            reportes.add(reporte);
        }
        return reportes;
    }

    public void inicializaUnion(Union union) {
        log.debug("Inicializando reportes de la union {}", union);
        List<String> nombres = new ArrayList<>();
        nombres.add("asociaciones");
        nombres.add("usuarios");

        union.getReportes().clear();
        union.getReportes().addAll(inicializaReportes(nombres));
        currentSession().save(union);
        currentSession().flush();
    }

    public void inicializaAsociacion(Asociacion asociacion) {
        log.debug("Inicializando reportes de la asociacion {}", asociacion);
        List<String> nombres = new ArrayList<>();
        nombres.add("usuarios");

        asociacion.getReportes().clear();
        asociacion.getReportes().addAll(inicializaReportes(nombres));
        currentSession().save(asociacion);
        currentSession().flush();
    }

    private byte[] obtainByteData(InputStream inputStream) throws IOException {
        byte[] byteData;
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            for (int readBytes = inputStream.read(); readBytes >= 0; readBytes = inputStream.read()) {
                outputStream.write(readBytes);
            }
            byteData = outputStream.toByteArray();
            inputStream.close();
        }

        return byteData;
    }

    private byte[] obtainByteData(JasperReport jr) throws IOException {
        byte[] byteData;
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            try (ObjectOutputStream out = new ObjectOutputStream(baos)) {
                out.writeObject(jr);
            }
            byteData = baos.toByteArray();
        }
        return byteData;
    }

    public void compila(String nombre, String tipo, Usuario usuario) {
        Reporte reporte = null;
        switch (tipo) {
            case Constantes.ADMIN:
                reporte = buscaReporteAdminstrativo(nombre);
                break;
            case Constantes.UNI:
                reporte = buscaReportePorUnion(nombre, usuario.getAsociacion().getUnion().getId());
                break;
            case Constantes.ASO:
                reporte = buscaReportePorAsociacion(nombre, usuario.getAsociacion().getId());
                break;
        }
        try {
            JasperDesign jd = JRXmlLoader.load(this.getClass().getResourceAsStream("/reportes/" + nombre + ".jrxml"));
            JasperReport jr = JasperCompileManager.compileReport(jd);
            byte[] fuente = obtainByteData(this.getClass().getResourceAsStream("/reportes/" + nombre + ".jrxml"));
            byte[] compilado = obtainByteData(jr);
            boolean nuevo = false;
            if (reporte == null) {
                nuevo = true;
                reporte = new Reporte();
            }
            reporte.setNombre(nombre);
            reporte.setFuente(fuente);
            reporte.setCompilado(compilado);
            Date fecha = new Date();
            reporte.setFechaModificacion(fecha);
            if (nuevo) {
                reporte.setFechaCreacion(fecha);
                currentSession().save(reporte);
                switch (tipo) {
                    case Constantes.UNI:
                        usuario.getAsociacion().getUnion().getReportes().add(reporte);
                        currentSession().update(usuario.getAsociacion().getUnion());
                        break;
                    case Constantes.ASO:
                        usuario.getAsociacion().getReportes().add(reporte);
                        currentSession().update(usuario.getAsociacion());
                        break;
                }
            } else {
                currentSession().update(reporte);
            }
            currentSession().flush();
        } catch (JRException | IOException e) {
            log.error("No se pudo compilar el reporte", e);
        }
    }
}
