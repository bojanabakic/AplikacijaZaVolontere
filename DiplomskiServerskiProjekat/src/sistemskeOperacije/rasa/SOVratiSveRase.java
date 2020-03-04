/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemskeOperacije.rasa;

import domen.OpstiDomenskiObjekat;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import sistemskeOperacije.OpstaSistemskaOperacija;
import transfer.ServerskiTransferObjekat;

/**
 *
 * @author CJ931WT
 */
public class SOVratiSveRase extends OpstaSistemskaOperacija{

    @Override
    public ServerskiTransferObjekat IzvrsiOperaciju(OpstiDomenskiObjekat odo) throws Exception {
        ServerskiTransferObjekat so = new ServerskiTransferObjekat();
        try {
            ArrayList<OpstiDomenskiObjekat> lista = odo.ucitajListu((ResultSet) db.DBBroker.getInstance().select(odo));
            so.setOdgovor(lista);
            so.setUspjesno(true);
            so.setPoruka("Sistem je pronašao rase po zadatim kriterijumima pretrage");
        } catch (Exception ex) {
            Logger.getLogger(SOVratiSveRase.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception("Sistem nije pronašao rase po zadatim kriterijumima pretrage");
        }
        return so;
    }
    
}
