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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import sistemskeOperacije.OpstaSistemskaOperacija;
import transfer.ServerskiTransferObjekat;

/**
 *
 * @author CJ931WT
 */
public class SOVratiSvaZaduzenja extends OpstaSistemskaOperacija {

    @Override
    public ServerskiTransferObjekat IzvrsiOperaciju(OpstiDomenskiObjekat odo) throws Exception {
        ServerskiTransferObjekat so = new ServerskiTransferObjekat();
        try {
            ArrayList<OpstiDomenskiObjekat> lista = odo.ucitajListu(db.DBBroker.getInstance().select(odo));
            ArrayList<Zaduzenje> listaZaduzenja = new ArrayList<>();

            for (OpstiDomenskiObjekat od : lista) {
                ArrayList<Zadatak> listaZadataka = new ArrayList<>();
                Zaduzenje z = (Zaduzenje) od;
                Zadatak zad = new Zadatak(0, z, "", 0, "", "");
                ArrayList<OpstiDomenskiObjekat> listaZ = zad.ucitajListu(db.DBBroker.getInstance().select(zad));

                for (OpstiDomenskiObjekat zadatak : listaZ) {
                    Zadatak zada = (Zadatak) zadatak;
                    zada.setZaduzenje(z);
                    listaZadataka.add(zada);
                }
                z.setListaZadataka(listaZadataka);
                listaZaduzenja.add(z);
            }

            so.setOdgovor(listaZaduzenja);
            so.setUspjesno(true);
            so.setPoruka("Sistem je pronašao zaduženja po zadatim kriterijumima pretrage");
        } catch (SQLException ex) {
            Logger.getLogger(SOVratiSvaZaduzenja.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception("Sistem nije pronašao zaduženja po zadatim kriterijumima pretrage");
        }
        return so;
    }

}
