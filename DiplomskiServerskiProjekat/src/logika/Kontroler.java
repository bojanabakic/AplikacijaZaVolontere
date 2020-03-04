/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logika;

import domen.OpstiDomenskiObjekat;
import sistemskeOperacije.korisnik.SOUloguj;
import sistemskeOperacije.grad.SOVratiSveGradove;
import sistemskeOperacije.macka.SOIzmjeniMacku;
import sistemskeOperacije.macka.SOObrisiMacku;
import sistemskeOperacije.macka.SOVratiSveMacke;
import sistemskeOperacije.macka.SOZapamtiMacku;
import sistemskeOperacije.rasa.SOVratiSveRase;
import sistemskeOperacije.volonter.SOIzmjeniVolontera;
import sistemskeOperacije.volonter.SOObrisiVolontera;
import sistemskeOperacije.volonter.SOVratiSveVolontere;
import sistemskeOperacije.volonter.SOZapamtiVolontera;
import sistemskeOperacije.zaduzenje.SOIzmjeniZaduzenje;
import sistemskeOperacije.zaduzenje.SOObrisiZaduzenje;
import sistemskeOperacije.zaduzenje.SOVratiSvaZaduzenja;
import sistemskeOperacije.zaduzenje.SOZapamtiZaduzenje;
import transfer.ServerskiTransferObjekat;

/**
 *
 * @author CJ931WT
 */
public class Kontroler {
    private static Kontroler instance;
    
    private Kontroler() {
    }

    public static Kontroler getInstance() {
        if(instance == null)
            instance = new Kontroler();
        return instance;
    }
    
    public ServerskiTransferObjekat ulogujKorisnika(OpstiDomenskiObjekat odo) throws Exception {
        return new SOUloguj().IzvrsiTransakciju(odo);
    }
    
    public ServerskiTransferObjekat vratiListuRasa(OpstiDomenskiObjekat odo) throws Exception {
        return new SOVratiSveRase().IzvrsiTransakciju(odo);
    }

    public ServerskiTransferObjekat zapamtiMacku(OpstiDomenskiObjekat odo) throws Exception {
        return new SOZapamtiMacku().IzvrsiTransakciju(odo);
    }

    public ServerskiTransferObjekat vratiListuMacaka(OpstiDomenskiObjekat odo) throws Exception {
        return new SOVratiSveMacke().IzvrsiTransakciju(odo);
    }

    public ServerskiTransferObjekat izmjeniMacku(OpstiDomenskiObjekat odo) throws Exception {
        return new SOIzmjeniMacku().IzvrsiTransakciju(odo);
    }

    public ServerskiTransferObjekat obrisiMacku(OpstiDomenskiObjekat odo) throws Exception {
        return new SOObrisiMacku().IzvrsiTransakciju(odo);
    }

    public ServerskiTransferObjekat vratiListuGradova(OpstiDomenskiObjekat odo) throws Exception {
        return new SOVratiSveGradove().IzvrsiTransakciju(odo);
    }

    public ServerskiTransferObjekat zapamtiVolontera(OpstiDomenskiObjekat odo) throws Exception {
        return new SOZapamtiVolontera().IzvrsiTransakciju(odo);
    }
    
    public ServerskiTransferObjekat vratiListuVolontera(OpstiDomenskiObjekat odo) throws Exception {
        return new SOVratiSveVolontere().IzvrsiTransakciju(odo);
    }

    public ServerskiTransferObjekat izmjeniVolontera(OpstiDomenskiObjekat odo) throws Exception {
        return new SOIzmjeniVolontera().IzvrsiTransakciju(odo);
    }

    public ServerskiTransferObjekat obrisiVolontera(OpstiDomenskiObjekat odo) throws Exception {        
        return new SOObrisiVolontera().IzvrsiTransakciju(odo);
    }

    public ServerskiTransferObjekat vratiListuZaduzenja(OpstiDomenskiObjekat odo) throws Exception {
        return new SOVratiSvaZaduzenja().IzvrsiTransakciju(odo);
    }

    public ServerskiTransferObjekat zapamtiZaduzenje(OpstiDomenskiObjekat odo) throws Exception {
        return new SOZapamtiZaduzenje().IzvrsiTransakciju(odo);
    }

    public ServerskiTransferObjekat izmjeniZaduzenje(OpstiDomenskiObjekat odo) throws Exception {
        return new SOIzmjeniZaduzenje().IzvrsiTransakciju(odo);
    }

    public ServerskiTransferObjekat obrisiZaduzenje(OpstiDomenskiObjekat odo) throws Exception {
        return new SOObrisiZaduzenje().IzvrsiTransakciju(odo);
    }
}
