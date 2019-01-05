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
@Table(name = "immeuble")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Immeuble.findAll", query = "SELECT i FROM Immeuble i")
    , @NamedQuery(name = "Immeuble.findById", query = "SELECT i FROM Immeuble i WHERE i.id = :id")
    , @NamedQuery(name = "Immeuble.findByNumero", query = "SELECT i FROM Immeuble i WHERE i.numero = :numero")
    , @NamedQuery(name = "Immeuble.findByNom", query = "SELECT i FROM Immeuble i WHERE i.nom = :nom")
    , @NamedQuery(name = "Immeuble.findByCodePostal", query = "SELECT i FROM Immeuble i WHERE i.codePostal = :codePostal")})
public class Immeuble implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "numero")
    private int numero;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "nom")
    private String nom;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "codePostal")
    private String codePostal;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "immeubleId", fetch = FetchType.EAGER)
    private transient List<Appartement> appartementList;
    @JoinColumn(name = "quarier_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Quartier quarierId;
    @ManyToOne
    private Habitant syndic;

    public Immeuble() {
    }

    public Immeuble(Integer id) {
        this.id = id;
    }

    public Immeuble(Integer id, int numero, String nom, String codePostal, Habitant syndic) {
        this.id = id;
        this.numero = numero;
        this.nom = nom;
        this.codePostal = codePostal;
        this.syndic = syndic;
    }

    public Immeuble(int numero, String nom, String codePostal, Quartier quarierId, Habitant syndic) {
        this.numero = numero;
        this.nom = nom;
        this.codePostal = codePostal;
        this.quarierId = quarierId;
        this.syndic = syndic;
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

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    @XmlTransient
    public List<Appartement> getAppartementList() {
        return appartementList;
    }

    public void setAppartementList(List<Appartement> appartementList) {
        this.appartementList = appartementList;
    }

    public Quartier getQuarierId() {
        return quarierId;
    }

    public void setQuarierId(Quartier quarierId) {
        this.quarierId = quarierId;
    }

    public Habitant getSyndic() {
        return syndic;
    }

    public void setSyndic(Habitant syndic) {
        this.syndic = syndic;
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
        if (!(object instanceof Immeuble)) {
            return false;
        }
        Immeuble other = (Immeuble) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Immeuble[ id=" + id + " ]";
    }
    
}
