/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemskeOperacije.grad;

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
public class SOVratiSveGradove extends OpstaSistemskaOperacija{

    @Override
    public ServerskiTransferObjekat IzvrsiOperaciju(OpstiDomenskiObjekat odo) throws Exception {
        ServerskiTransferObjekat so = new ServerskiTransferObjekat();
        try {
            ArrayList<OpstiDomenskiObjekat> lista = odo.ucitajListu((ResultSet) db.DBBroker.getInstance().select(odo));
            so.setOdgovor(lista);
            so.setUspjesno(true);
            so.setPoruka("Sistem je pronašao gradove po zadatim kriterijumima pretrage");
        } catch (Exception ex) {
            Logger.getLogger(SOVratiSveGradove.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception("Sistem nije pronašao gradove po zadatim kriterijumima pretrage");
        }
        return so;
    }
    
}
