/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemskeOperacije.korisnik;

import db.DBBroker;
import domen.OpstiDomenskiObjekat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import sistemskeOperacije.OpstaSistemskaOperacija;
import transfer.ServerskiTransferObjekat;


public class SOUloguj extends OpstaSistemskaOperacija {

    @Override
    public ServerskiTransferObjekat IzvrsiOperaciju(OpstiDomenskiObjekat odo) throws Exception {
        ServerskiTransferObjekat so = new ServerskiTransferObjekat();
        try {
            ArrayList<OpstiDomenskiObjekat> lista = odo.ucitajListu(DBBroker.getInstance().select(odo));
            if (lista.isEmpty()) {
                throw new Exception("Neuspješno prijavljivanje na sistem");
            }
            so.setOdgovor(lista);
            so.setUspjesno(true);
            so.setPoruka("Uspješno ste se prijavili na sistem");
        } catch (Exception ex) {
            Logger.getLogger(SOUloguj.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception("Neuspješno prijavljivanje na sistem");
        }
        return so;
    }
    
}
