/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemskeOperacije.volonter;

import domen.OpstiDomenskiObjekat;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import sistemskeOperacije.OpstaSistemskaOperacija;
import transfer.ServerskiTransferObjekat;

/**
 *
 * @author CJ931WT
 */
public class SOZapamtiVolontera extends OpstaSistemskaOperacija{

    @Override
    public ServerskiTransferObjekat IzvrsiOperaciju(OpstiDomenskiObjekat odo) throws Exception {
        ServerskiTransferObjekat so = new ServerskiTransferObjekat();
        try {
            db.DBBroker.getInstance().insert(odo);
            so.setOdgovor(null);
            so.setUspjesno(true);
            so.setPoruka("Sistem je zapamtio volontera");
        } catch (SQLException ex) {
            Logger.getLogger(SOZapamtiVolontera.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception("Sistem nije zapamtio volontera");
        }
        return so;
    }
    
}
