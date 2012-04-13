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
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author gibrandemetrioo
 */
@Entity
@Table(name = "asociaciones")
public class Asociacion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Version
    private Integer version;
    @NotBlank
    @Column(nullable = false, length = 64)
    private String nombre;
    @NotNull
    @Column(nullable = false, length = 2, name = "status")
    private String status;
    @ManyToOne(optional = false)
    private Union union;
    @OneToMany(mappedBy = "asociacion", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Usuario> usuarios = new ArrayList<>();
    @ManyToMany
    private List<Reporte> reportes = new ArrayList<>();
//    @Column(length = 25)
//    private String telefono;
//    @Column(length = 25)
//    private String fax;
//    @Email
//    @Column(length = 128)
//    private String correo;

    public Asociacion() {
    }

    public Asociacion(String nombre, String status, Union union) {
        this.nombre = nombre;
        this.status = status;
        this.union = union;
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

    public Union getUnion() {
        return union;
    }

    public void setUnion(Union union) {
        this.union = union;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.setUsuarios(usuarios);
    }

    public List<Reporte> getReportes() {
        return reportes;
    }

    public void setReportes(List<Reporte> reportes) {
        this.reportes = reportes;
    }
    
    public String getNombreCompleto() {
        StringBuilder sb = new StringBuilder();
        sb.append(getUnion().getNombre());
        sb.append(" | ");
        sb.append(getNombre());
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.nombre);
        hash = 37 * hash + Objects.hashCode(this.union);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Asociacion other = (Asociacion) obj;
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.union, other.union)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Asociacion{" + "nombre=" + nombre + ", status=" + status + ", union=" + union + '}';
    }
}
