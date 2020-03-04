/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transfer;

import java.io.Serializable;

/**
 *
 * @author CJ931WT
 */
public class ServerskiTransferObjekat implements Serializable{
    private String poruka;
    private Object odgovor;
    private boolean uspjesno;

    public ServerskiTransferObjekat() {
    }

    public ServerskiTransferObjekat(String poruka, Object odgovor, boolean uspjesno) {
        this.poruka = poruka;
        this.odgovor = odgovor;
        this.uspjesno = uspjesno;
    }


    public Object getOdgovor() {
        return odgovor;
    }

    public void setOdgovor(Object odgovor) {
        this.odgovor = odgovor;
    }

    public String getPoruka() {
        return poruka;
    }

    public void setPoruka(String poruka) {
        this.poruka = poruka;
    }

    public boolean isUspjesno() {
        return uspjesno;
    }

    public void setUspjesno(boolean uspesno) {
        this.uspjesno = uspjesno;
    }
}
