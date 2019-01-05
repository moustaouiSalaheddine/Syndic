/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Sinponzakra
 */
@Entity
public class AppartementHabitant implements Serializable {
    @EmbeddedId
    private AppartementHabitantPK id;
    @JoinColumn(referencedColumnName = "id", updatable = false, insertable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private Appartement appartement;
    @JoinColumn(referencedColumnName = "id", updatable = false, insertable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private Habitant habitant;
    @Temporal(TemporalType.DATE)
    private Date dateFin;

    public AppartementHabitant(AppartementHabitantPK id, Appartement appartement, Habitant habitant, Date dateFin) {
        this.id = id;
        this.appartement = appartement;
        this.habitant = habitant;
        this.dateFin = dateFin;
    }

    public AppartementHabitant() {
    }

    public AppartementHabitantPK getId() {
        return id;
    }

    public void setId(AppartementHabitantPK id) {
        this.id = id;
    }

    public Appartement getAppartement() {
        return appartement;
    }

    public void setAppartement(Appartement appartement) {
        this.appartement = appartement;
    }

    public Habitant getHabitant() {
        return habitant;
    }

    public void setHabitant(Habitant habitant) {
        this.habitant = habitant;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }
    
    
}
