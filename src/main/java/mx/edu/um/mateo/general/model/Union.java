/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.um.mateo.general.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 *
 * @author wilbert
 */
@Entity
@Table(name = "uniones")
public class Union implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Version
    private Integer version;
    @NotNull
    @Column(unique = true, nullable = false, length = 64)
    private String nombre;
    @NotNull
    @Column(nullable = false, length = 2, name = "status")
    private String status;
    @OneToMany(mappedBy = "union", cascade = CascadeType.ALL)
    private List<Asociacion> asociaciones;
    @ManyToMany
    private List<Reporte> reportes = new ArrayList<>();

    public Union() {
    }

    public Union(String nombre) {
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Asociacion> getAsociaciones() {
        return asociaciones;
    }

    public void setAsociaciones(List<Asociacion> asociaciones) {
        this.asociaciones = asociaciones;
    }

    public List<Reporte> getReportes() {
        return reportes;
    }

    public void setReportes(List<Reporte> reportes) {
        this.reportes = reportes;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Union other = (Union) obj;
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.status, other.status)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.nombre);
        hash = 97 * hash + Objects.hashCode(this.status);
        return hash;
    }

    @Override
    public String toString() {
        return "Union{" + "nombre=" + nombre + ", status=" + status + '}';
    }
}
