/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Sinponzakra
 */
@Entity
public class Appartement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "numero")
    private int numero;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dimension")
    private int dimension;
    @Basic(optional = false)
    @NotNull
    @Column(name = "etage")
    private int etage;
    @JoinColumn(name = "immeuble_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Immeuble immeubleId;

    public Appartement() {
    }

    public Appartement(Integer id) {
        this.id = id;
    }

    public Appartement(Integer id, int numero, int dimension) {
        this.id = id;
        this.numero = numero;
        this.dimension = dimension;
    }

    public Appartement(int numero, int dimension, int etage, Immeuble immeubleId) {
        this.numero = numero;
        this.dimension = dimension;
        this.etage = etage;
        this.immeubleId = immeubleId;
    }
    
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getDimension() {
        return dimension;
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }

    public Immeuble getImmeubleId() {
        return immeubleId;
    }

    public void setImmeubleId(Immeuble immeubleId) {
        this.immeubleId = immeubleId;
    }

    public int getEtage() {
        return etage;
    }

    public void setEtage(int etage) {
        this.etage = etage;
    }
    
    
}
