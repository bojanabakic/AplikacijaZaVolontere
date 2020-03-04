/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import domen.OpstiDomenskiObjekat;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import konstante.Operacija;
import logika.Kontroler;
import transfer.KlijentskiTransferObjekat;
import transfer.ServerskiTransferObjekat;

/**
 *
 * @author CJ931WT
 */
public class ObradaKlijentskogZahtjeva extends Thread {

    Socket soket;
    boolean kraj = false;

    public ObradaKlijentskogZahtjeva(Socket soket) {
        this.soket = soket;
    }

    @Override
    public void run() {
        while (!kraj) {
            try {
                KlijentskiTransferObjekat kz = primiZahtjev();
                ServerskiTransferObjekat so = new ServerskiTransferObjekat();
                OpstiDomenskiObjekat odo = (OpstiDomenskiObjekat) kz.getParametar();

                switch (kz.getOperacija()) {
                    case Operacija.LOGOVANJE:
                        so = Kontroler.getInstance().ulogujKorisnika(odo);
                        break;
                    case Operacija.VRATI_RASE:
                        so = Kontroler.getInstance().vratiListuRasa(odo);
                        break;
                    case Operacija.SACUVAJ_MACKU:
                        so = Kontroler.getInstance().zapamtiMacku(odo);
                        break;
                    case Operacija.VRATI_SVE_MACKE:
                        so = Kontroler.getInstance().vratiListuMacaka(odo);
                        break;
                    case Operacija.IZMJENA_MACKE:
                        so = Kontroler.getInstance().izmjeniMacku(odo);
                        break;
                    case Operacija.BRISANJE_MACKE:
                        so = Kontroler.getInstance().obrisiMacku(odo);
                        break;
                    case Operacija.VRATI_GRADOVE:
                        so = Kontroler.getInstance().vratiListuGradova(odo);
                        break;
                    case Operacija.SACUVAJ_VOLONTERA:
                        so = Kontroler.getInstance().zapamtiVolontera(odo);
                        break;
                    case Operacija.VRATI_SVE_VOLONTERE:
                        so = Kontroler.getInstance().vratiListuVolontera(odo);
                        break;
                    case Operacija.IZMJENA_VOLONTERA:
                        so = Kontroler.getInstance().izmjeniVolontera(odo);
                        break;
                    case Operacija.BRISANJE_VOLONTERA:
                        so = Kontroler.getInstance().obrisiVolontera(odo);
                        break;
                    case Operacija.VRATI_SVA_ZADUZENJA:
                        so = Kontroler.getInstance().vratiListuZaduzenja(odo);
                        break;
                    case Operacija.SACUVAJ_ZADUZENJE:
                        so = Kontroler.getInstance().zapamtiZaduzenje(odo);
                        break;
                    case Operacija.IZMJENI_ZADUZENJE:
                        so = Kontroler.getInstance().izmjeniZaduzenje(odo);
                        break;
                    case Operacija.OBRISI_ZADUZENJE:
                        so = Kontroler.getInstance().obrisiZaduzenje(odo);
                        break;
                    case Operacija.ZAVRSI:
                        kraj = true;
                        so.setPoruka("Sistem je završio sa radom.");
                        break;
                }
                posaljiOdgovor(so);
            } catch (Exception ex) {
                Logger.getLogger(ObradaKlijentskogZahtjeva.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
            }
        }
    }
    
    public void posaljiOdgovor(ServerskiTransferObjekat so){
        try {
            ObjectOutputStream oos = new ObjectOutputStream(soket.getOutputStream());
            oos.writeObject(so);
        } catch (IOException ex) {
            Logger.getLogger(ObradaKlijentskogZahtjeva.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public KlijentskiTransferObjekat primiZahtjev(){
        KlijentskiTransferObjekat kz = new KlijentskiTransferObjekat();
        try {
            ObjectInputStream ois = new ObjectInputStream(soket.getInputStream());
            kz = (KlijentskiTransferObjekat) ois.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ObradaKlijentskogZahtjeva.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return kz;
    }
}
