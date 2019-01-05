/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Sinponzakra
 */
@Entity
@Table(name = "quartier")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Quartier.findAll", query = "SELECT q FROM Quartier q")
    , @NamedQuery(name = "Quartier.findById", query = "SELECT q FROM Quartier q WHERE q.id = :id")
    , @NamedQuery(name = "Quartier.findByNom", query = "SELECT q FROM Quartier q WHERE q.nom = :nom")})
public class Quartier implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "nom")
    private String nom;
    @JoinColumn(name = "ville_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Ville villeId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "quarierId", fetch = FetchType.EAGER)
    private transient  List<Immeuble> immeubleList;

    public Quartier() {
    }

    public Quartier(Integer id) {
        this.id = id;
    }

    public Quartier(Integer id, String nom, Ville ville) {
        this.id = id;
        this.nom = nom;
        this.villeId = ville;
    }

    public Quartier(String nom, Ville villeId) {
        this.nom = nom;
        this.villeId = villeId;
    }
    
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Ville getVilleId() {
        return villeId;
    }

    public void setVilleId(Ville villeId) {
        this.villeId = villeId;
    }

    @XmlTransient
    public List<Immeuble> getImmeubleList() {
        return immeubleList;
    }

    public void setImmeubleList(List<Immeuble> immeubleList) {
        this.immeubleList = immeubleList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Quartier)) {
            return false;
        }
        Quartier other = (Quartier) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Quartier[ id=" + id + " ]";
    }
    
}
