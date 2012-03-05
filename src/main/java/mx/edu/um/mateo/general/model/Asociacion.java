/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.um.mateo.general.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author gibrandemetrioo
 */
@Entity 
@Table (name = "asociaciones")
public class Asociacion implements Serializable {
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Version
    private Integer version;
    @NotBlank
    @Column(unique=true, nullable = false, length = 64)
    private String nombre;
    @Column(unique=true, nullable = false, length = 500)
    private String direccion;
    
    @NotNull
    @Column (nullable = false, length = 2, name = "status")
    private String status;
//    @Column(length = 25)
//    private String telefono;
//    @Column(length = 25)
//    private String fax;
//    @Email
//    @Column(length = 128)
//    private String correo;
    
    public Asociacion (){
    }
    
//    public Asociacion (String nombre, String direccion, String telefono, String fax, String correo){
//        this.nombre = nombre ;
//        this.direccion = direccion;
//        this.telefono = telefono;
//        this.fax = fax;
//        this.correo = correo;
//    }

    public Asociacion( String nombre, String direccion, String status) {
        
        this.nombre = nombre;
        this.direccion = direccion;
        this.status = status;
        
    }
    
    public Long getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getDireccion() {
        return direccion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
        if (!Objects.equals(this.direccion, other.direccion)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 43 * hash + Objects.hashCode(this.nombre);
        hash = 43 * hash + Objects.hashCode(this.direccion);
        return hash;
    }

    @Override
    public String toString() {
        return "Asociacion{" + "id=" + id + ", nombre=" + nombre + ", direccion=" + direccion + ", status=" + status + '}';
    }

    

    
    
    
}
