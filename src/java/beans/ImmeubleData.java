/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.List;

/**
 *
 * @author Sinponzakra
 */
public class ImmeubleData {
    private int etage;
    private List<Object> appart;

    public ImmeubleData() {
    }

    public ImmeubleData(int etage, List<Object> appart) {
        this.etage = etage;
        this.appart = appart;
    }

    public int getEtage() {
        return etage;
    }

    public void setEtage(int etage) {
        this.etage = etage;
    }

    public List<Object> getAppart() {
        return appart;
    }

    public void setAppart(List<Object> appart) {
        this.appart = appart;
    }
    
    
}
