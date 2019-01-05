/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Sinponzakra
 */
@Embeddable
public class AppartementHabitantPK implements Serializable {
    @Temporal(TemporalType.DATE)
    private Date dateDebut;
    private int appartement_id;
    private int habitant_id;

    public AppartementHabitantPK() {
    }

    public AppartementHabitantPK(Date dateDebut, int appartement_id, int habitant_id) {
        this.dateDebut = dateDebut;
        this.appartement_id = appartement_id;
        this.habitant_id = habitant_id;
    }
    
    

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public int getAppartement_id() {
        return appartement_id;
    }

    public void setAppartement_id(int appartement_id) {
        this.appartement_id = appartement_id;
    }

    public int getHabitant_id() {
        return habitant_id;
    }

    public void setHabitant_id(int habitant_id) {
        this.habitant_id = habitant_id;
    }
    
    
}
