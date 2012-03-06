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
public class Asociacion  {
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Version
    private Integer version;
    @NotBlank
    @Column(unique=true, nullable = false, length = 64)
    private String nombre;
    
    
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

    public Asociacion( String nombre, String status) {
        
        this.nombre = nombre;
        this.status = status;
        
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    

    public String getNombre() {
        return nombre;
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
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
        final Asociacion other = (Asociacion) obj;
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
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.nombre);
        hash = 29 * hash + Objects.hashCode(this.status);
        return hash;
    }

    @Override
    public String toString() {
        return "Asociacion{" + "nombre=" + nombre + ", status=" + status + '}';
    }

    

    

   

    

  

   

    

    
    
    
}
