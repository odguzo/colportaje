/*
 * The MIT License
 *
 * Copyright 2012 jdmr.
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
package mx.edu.um.mateo.general.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author wilbert
 */
@Entity
@Table(name = "colportores")
public class Colportor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Version
    private Integer version;
    @NotNull
    @Column(unique = true, nullable = false, length = 64)
    private String clave;
    @NotNull
    @Column(nullable = false, length = 2)
    private String status;
    @NotEmpty
    @Size(min = 10, max = 12)
    @Column(nullable = false, length = 12)
    private String telefono;
    @Column(nullable = true, length = 200)
    private String calle;
    @Column(nullable = true, length = 200)
    private String colonia;
    @Column(nullable = true, length = 200)
    private String municipio;
    /*
     * DE AQUI private Set<TipoColportor> tipoColportor = new
     * HashSet<TipoColportor>();      *
     * @ElementCollection @Enumerated(EnumType.STRING) public Set<TipoColportor>
     * getTipoColportors() { return tipoColportor; }      *
     * public void setTipoColportors(Set<TipoColportor> tipoColportor) {
     * this.tipoColportor = tipoColportor; }
     */

    public Colportor() {
    }

    public Colportor(String clave, String telefono) {
            this.clave = clave;
            this.telefono = telefono;
    }

    public Colportor(String clave, String status, String telefono, String calle, String colonia, String municipio) {
        this.clave = clave;
        this.status = status;
        this.telefono = telefono;
        this.calle = calle;
        this.colonia = colonia;
        this.municipio = municipio;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
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
        final Colportor other = (Colportor) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.clave, other.clave)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
        hash = 97 * hash + Objects.hashCode(this.clave);
        return hash;
    }

    @Override
    public String toString() {
        return "Colportor{" + "clave=" + clave + ", status=" + status + ", telefono=" + telefono + '}';
    }
}
