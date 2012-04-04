/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.um.mateo.general.model;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 *
 * @author wilbert
 */
@Entity
@Table(name="documentos")
public class Documento implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Version
    private Integer version;
    @NotNull
    @Column(nullable = false, length = 15)
    private String tipoDeDocumento;
    @NotNull
    @Column(nullable = false, length = 30)
    private String folio;
    @Column( length = 10)
    private String fecha;
    @Column(length = 20)
    private String importe;
    @Column(length = 100)
    private String observaciones;
   
   
  
    
    public Documento(){
    }
    public Documento(String tipoDeDocumento,String folio,String fecha,String importe,String observaciones){
        this.tipoDeDocumento=tipoDeDocumento;
        this.folio=folio;
        this.fecha=fecha;
        this.importe=importe;
        this.observaciones=observaciones;
          
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

  
   public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImporte() {
        return importe;
    }

    public void setImporte(String importe) {
        this.importe = importe;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getTipoDeDocumento() {
        return tipoDeDocumento;
    }

    public void setTipoDeDocumento(String tipoDeDocumento) {
        this.tipoDeDocumento = tipoDeDocumento;
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
        final Documento other = (Documento) obj;
        if (!Objects.equals(this.tipoDeDocumento, other.tipoDeDocumento)) {
            return false;
        }
        if (!Objects.equals(this.folio, other.folio)) {
            return false;
        }
        if (!Objects.equals(this.fecha, other.fecha)) {
            return false;
        }
        if (!Objects.equals(this.importe, other.importe)) {
            return false;
        }
        if (!Objects.equals(this.observaciones, other.observaciones)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + Objects.hashCode(this.tipoDeDocumento);
        hash = 19 * hash + Objects.hashCode(this.folio);
        hash = 19 * hash + Objects.hashCode(this.fecha);
        hash = 19 * hash + Objects.hashCode(this.importe);
        hash = 19 * hash + Objects.hashCode(this.observaciones);
        return hash;
    }

    @Override
    public String toString() {
        return "Documento{" + "tipoDeDocumento=" + tipoDeDocumento + ", folio=" + folio + ", fecha=" + fecha + ", importe=" + importe + ", observaciones=" + observaciones + '}';
    }
  
}
