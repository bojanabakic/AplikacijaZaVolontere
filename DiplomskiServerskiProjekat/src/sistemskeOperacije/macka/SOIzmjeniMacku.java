/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemskeOperacije.macka;

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
public class SOIzmjeniMacku extends OpstaSistemskaOperacija {

    @Override
    public ServerskiTransferObjekat IzvrsiOperaciju(OpstiDomenskiObjekat odo) throws Exception {
        ServerskiTransferObjekat so = new ServerskiTransferObjekat();
        try {
            db.DBBroker.getInstance().update(odo);
            so.setOdgovor(null);
            so.setUspjesno(true);
            so.setPoruka("Sistem je izmjenio mačku");
        } catch (SQLException ex) {
            Logger.getLogger(SOIzmjeniMacku.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception("Sistem nije izmjenio mačku");
        }
        return so;
    }

}
