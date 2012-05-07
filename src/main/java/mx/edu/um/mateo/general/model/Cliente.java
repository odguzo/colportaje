/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.um.mateo.general.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author lobo4
 */
@Entity
@Table(name = "clientes")
public class Cliente implements Serializable {

    @Id
    @Version
    private Integer version;
    @NotBlank
    @Column(nullable = false, length = 24)
    private String nombre;
    @NotBlank
    @Column(nullable = false, length = 24)
    private String apellidoP;
    @Column(nullable = false, length = 24)
    private String apellidoM;
    @NotBlank
    @Column(nullable = false, length = 128)
    private String calle;
    @NotBlank
    @Column(nullable = false, length = 64)
    private String colonia;
    @NotBlank
    @Column(nullable = false, length = 6)
    private Integer casanum;
    @NotBlank
    @Column(nullable = false, length = 6)
    private Integer cp;
    @Column(nullable = true, length = 12)
    private Integer telefono;
    @Column(nullable = true, length = 12)
    private Integer celular;
    @Column(nullable = true, length = 24)
    private String email;
    @ManyToOne
    private Asociacion asociacion;

    public Cliente() {
    }

    public Cliente(String nombre, String apellidoP, String calle, String colonia,Integer casa, Integer cp, Integer telefono, Integer celular, String email, Asociacion asociacion) {
        this.nombre = nombre;
        this.apellidoP = apellidoP;
        this.calle = calle;
        this.colonia = colonia;
        this.casanum = casa;
        this.cp = cp;
        this.telefono = telefono;
        this.celular = celular;
        this.email = email;
        this.asociacion = asociacion;
    }

    public String getApellidoM() {
        return apellidoM;
    }

    public void setApellidoM(String apellidoM) {
        this.apellidoM = apellidoM;
    }

    public String getApellidoP() {
        return apellidoP;
    }

    public void setApellidoP(String apellidoP) {
        this.apellidoP = apellidoP;
    }

    public Asociacion getAsociacion() {
        return asociacion;
    }

    public void setAsociacion(Asociacion asociacion) {
        this.asociacion = asociacion;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public Integer getCelular() {
        return celular;
    }

    public void setCelular(Integer celular) {
        this.celular = celular;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public Integer getCp() {
        return cp;
    }
    
    public Integer getcasa() {
        return casanum;
    }

    public void setCp(Integer cp) {
        this.cp = cp;
    }
    
    public Integer setcasa() {
        return casanum;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
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
        final Cliente other = (Cliente) obj;
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.nombre);
        return hash;
    }

    @Override
    public String toString() {
        return "Cliente{" + "nombre=" + nombre + ", apellidoP=" + apellidoP + ", apellidoM=" + apellidoM + '}';
    }

    public Long getid() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

}
