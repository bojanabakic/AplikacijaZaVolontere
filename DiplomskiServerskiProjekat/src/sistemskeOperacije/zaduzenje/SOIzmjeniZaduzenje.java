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
import tipoviAkcija.Statusi;
import transfer.ServerskiTransferObjekat;

/**
 *
 * @author CJ931WT
 */
public class SOIzmjeniZaduzenje extends OpstaSistemskaOperacija{

    @Override
    public ServerskiTransferObjekat IzvrsiOperaciju(OpstiDomenskiObjekat odo) throws Exception {
        ServerskiTransferObjekat so = new ServerskiTransferObjekat();
        try {
            db.DBBroker.getInstance().update(odo);
            Zaduzenje z = (Zaduzenje) odo;
            for (Zadatak zad : z.getListaZadataka()) {
                if(zad.getStatus().equals(Statusi.SACUVAJ))
                    db.DBBroker.getInstance().insert(zad);
                
                if(zad.getStatus().equals(Statusi.IZMJENI))
                    db.DBBroker.getInstance().update(zad);
                
                if(zad.getStatus().equals(Statusi.OBRISI))
                    db.DBBroker.getInstance().delete(zad);
            }
            so.setOdgovor(null);
            so.setUspjesno(true);
            so.setPoruka("Sistem je izmjenio zaduženje");
        } catch (SQLException ex) {
            Logger.getLogger(SOIzmjeniZaduzenje.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception("Sistem nije izmjenio zaduženje");
        }
        return so;
    }
    
}
