/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.um.mateo.general.model;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import mx.edu.um.mateo.general.model.TipoAsociado;
/**
 *
 * @author gibrandemetrioo
 */

@Entity

@Table (name = "asociados")
public  class Asociado  implements Serializable{
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Version
    private Integer version;
    @NotBlank
    @Column(nullable = false, length = 64)
    private String nombre;
    @NotBlank
    @Column(nullable = false, length = 65)
    private String clave;
    @Column(length = 500)
    private String direccion;
    @Email
    @Column(length = 128)
    private String correo;
    @Column(length = 25)
    private String telefono;
    @NotNull
    @Column(nullable = false, length = 23, name ="status")
    private String status;
    
    
    
    
    public Asociado() {
    }

    public Asociado(String nombre, String clave, String direccion,  String telefono, String status) {
        this.nombre = nombre;
        this.clave = clave;
        this.direccion = direccion;
        this.telefono = telefono;
        this.status = status;
    }
    
    
    
    

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Asociado other = (Asociado) obj;
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.nombre);
        return hash;
    }

    

    @Override
    public String toString() {
        return "Asociado{" + "nombre=" + nombre + ", clave=" + clave + ", direccion=" + direccion + ", correo=" + correo + ", telefono=" + telefono + ", status=" + status + '}';
    }

      
}