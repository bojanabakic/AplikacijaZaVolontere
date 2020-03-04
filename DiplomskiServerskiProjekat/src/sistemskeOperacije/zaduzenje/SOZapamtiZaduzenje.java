/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemskeOperacije.zaduzenje;

import domen.OpstiDomenskiObjekat;
import domen.Zadatak;
import domen.Zaduzenje;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import sistemskeOperacije.OpstaSistemskaOperacija;
import transfer.ServerskiTransferObjekat;

/**
 *
 * @author CJ931WT
 */
public class SOZapamtiZaduzenje extends OpstaSistemskaOperacija{

    @Override
    public ServerskiTransferObjekat IzvrsiOperaciju(OpstiDomenskiObjekat odo) throws Exception {
        ServerskiTransferObjekat so = new ServerskiTransferObjekat();
        try {
            db.DBBroker.getInstance().insert(odo);
            Zaduzenje z = (Zaduzenje) odo;
            z.setZaduzenjeID(db.DBBroker.getInstance().vratiID(odo)-1);
            for (Zadatak zad : z.getListaZadataka()) {
                zad.setZaduzenje(z);
                db.DBBroker.getInstance().insert(zad);
            }
            so.setOdgovor(null);
            so.setUspjesno(true);
            so.setPoruka("Sistem je sačuvao zaduženje");
        } catch (SQLException ex) {
            Logger.getLogger(SOZapamtiZaduzenje.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception("Sistem nije sačuvao zaduženje");
        }
        return so;
    }
    
}
