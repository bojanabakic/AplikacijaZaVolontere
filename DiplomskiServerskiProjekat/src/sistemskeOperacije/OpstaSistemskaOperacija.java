/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemskeOperacije;

import db.DBBroker;
import domen.OpstiDomenskiObjekat;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import transfer.ServerskiTransferObjekat;

/**
 *
 * @author CJ931WT
 */
public abstract class OpstaSistemskaOperacija {
    public ServerskiTransferObjekat IzvrsiTransakciju(OpstiDomenskiObjekat odo) {
        ServerskiTransferObjekat transferObjekat = new ServerskiTransferObjekat();
        try {
            DBBroker.getInstance().ucitajDrajver();
            DBBroker.getInstance().otvoriKonekciju();
            transferObjekat = IzvrsiOperaciju(odo);
            DBBroker.getInstance().commit();
        } catch (Exception ex) {
            Logger.getLogger(OpstaSistemskaOperacija.class.getName()).log(Level.SEVERE, null, ex);
            DBBroker.getInstance().rollback();
            transferObjekat.setUspjesno(false);
            transferObjekat.setPoruka(ex.getMessage());
        }finally {
            try {
                DBBroker.getInstance().zatvoriKonekciju();
            } catch (SQLException ex) {
                transferObjekat.setUspjesno(false);
                transferObjekat.setPoruka(ex.getMessage());
            }
        }
        return transferObjekat;
    }

    public abstract ServerskiTransferObjekat IzvrsiOperaciju(OpstiDomenskiObjekat odo) throws Exception;
}
