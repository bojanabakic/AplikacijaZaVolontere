/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klijentKontroler;

import domen.Grad;
import domen.Korisnik;
import domen.Macka;
import domen.Rasa;
import domen.Volonter;
import domen.Zaduzenje;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import komunikacijaSaServerom.Komunikacija;
import konstante.Operacija;
import transfer.KlijentskiTransferObjekat;
import transfer.ServerskiTransferObjekat;

/**
 *
 * @author CJ931WT
 */
public class KlijentKontroler {

    private static KlijentKontroler instance;
    Korisnik trenutnoUlogovanKorisnik;
    ArrayList<Rasa> listaRasa;
    ArrayList<Macka> listaMacaka;
    ArrayList<Grad> listaGradova;
    ArrayList<Volonter> listaVolontera;
    ArrayList<Zaduzenje> listaZaduzenja;

    private KlijentKontroler() {
    }

    public static KlijentKontroler getInstance() {
        if (instance == null) {
            instance = new KlijentKontroler();
        }
        return instance;
    }

    public boolean ulogujKorisnika(Korisnik k) {
        KlijentskiTransferObjekat kto = new KlijentskiTransferObjekat();
        kto.setOperacija(Operacija.LOGOVANJE);
        kto.setParametar(k);
        Komunikacija.getInstanca().posaljiZahtjev(kto);
        ServerskiTransferObjekat sto = Komunikacija.getInstanca().primiOdgovor();
        ArrayList<Korisnik> lista = (ArrayList<Korisnik>) sto.getOdgovor();
        JOptionPane.showMessageDialog(null, sto.getPoruka(), "Uspješno prijavljivanje", JOptionPane.INFORMATION_MESSAGE);
        if (lista == null || lista.isEmpty()) {
            return false;
        } else {
            trenutnoUlogovanKorisnik = lista.get(0);
            return true;
        }
    }

    public String zavrsiSaRadom() {
        KlijentskiTransferObjekat kto = new KlijentskiTransferObjekat();
        kto.setOperacija(Operacija.ZAVRSI);
        Komunikacija.getInstanca().posaljiZahtjev(kto);
        ServerskiTransferObjekat sto = Komunikacija.getInstanca().primiOdgovor();
        return sto.getPoruka();
    }

    public ArrayList<Rasa> vratiRase() {
        KlijentskiTransferObjekat kz = new KlijentskiTransferObjekat();
        kz.setOperacija(Operacija.VRATI_RASE);
        kz.setParametar(new Rasa());
        Komunikacija.getInstanca().posaljiZahtjev(kz);
        ServerskiTransferObjekat sto = Komunikacija.getInstanca().primiOdgovor();

        listaRasa = (ArrayList<Rasa>) sto.getOdgovor();

        if (listaRasa == null || listaRasa.isEmpty()) {
            JOptionPane.showMessageDialog(null, sto.getPoruka(), "Greska", JOptionPane.ERROR_MESSAGE);
            return new ArrayList<>();
        } else {
            return listaRasa;
        }
    }

    public ServerskiTransferObjekat sacuvajMacka(Macka m) {
        KlijentskiTransferObjekat kto = new KlijentskiTransferObjekat();
        kto.setOperacija(Operacija.SACUVAJ_MACKU);
        kto.setParametar(m);
        Komunikacija.getInstanca().posaljiZahtjev(kto);
        ServerskiTransferObjekat sto = Komunikacija.getInstanca().primiOdgovor();
        return sto;
    }

    public ArrayList<Macka> vratiSveMacke() {
        KlijentskiTransferObjekat kto = new KlijentskiTransferObjekat();
        kto.setOperacija(Operacija.VRATI_SVE_MACKE);
        kto.setParametar(new Macka());
        Komunikacija.getInstanca().posaljiZahtjev(kto);
        ServerskiTransferObjekat sto = Komunikacija.getInstanca().primiOdgovor();

        listaMacaka = (ArrayList<Macka>) sto.getOdgovor();

        if (listaMacaka == null || listaMacaka.isEmpty()) {
            JOptionPane.showMessageDialog(null, sto.getPoruka(), "Greska", JOptionPane.ERROR_MESSAGE);
            return new ArrayList<>();
        } else {
            return listaMacaka;
        }
    }

    public ArrayList<Macka> pretraziMacku(Macka m) {
        KlijentskiTransferObjekat kto = new KlijentskiTransferObjekat();
        kto.setOperacija(Operacija.VRATI_SVE_MACKE);
        kto.setParametar(m);
        Komunikacija.getInstanca().posaljiZahtjev(kto);
        ServerskiTransferObjekat sto = Komunikacija.getInstanca().primiOdgovor();

        listaMacaka = (ArrayList<Macka>) sto.getOdgovor();

        if (listaMacaka == null || listaMacaka.isEmpty()) {
            JOptionPane.showMessageDialog(null, sto.getPoruka(), "Greska", JOptionPane.ERROR_MESSAGE);
            return new ArrayList<>();
        } else {
            return listaMacaka;
        }
    }

    public ServerskiTransferObjekat izmjeniMacke(Macka m) {
        KlijentskiTransferObjekat kto = new KlijentskiTransferObjekat();
        kto.setOperacija(Operacija.IZMJENA_MACKE);
        kto.setParametar(m);
        Komunikacija.getInstanca().posaljiZahtjev(kto);
        ServerskiTransferObjekat sto = Komunikacija.getInstanca().primiOdgovor();
        return sto;
    }

    public ServerskiTransferObjekat obrisiMacku(Macka macka) {
        KlijentskiTransferObjekat kto = new KlijentskiTransferObjekat();
        kto.setOperacija(Operacija.BRISANJE_MACKE);
        kto.setParametar(macka);
        Komunikacija.getInstanca().posaljiZahtjev(kto);
        ServerskiTransferObjekat sto = Komunikacija.getInstanca().primiOdgovor();
        return sto;
    }

    public ArrayList<Grad> vratiGradove() {
        KlijentskiTransferObjekat kz = new KlijentskiTransferObjekat();
        kz.setOperacija(Operacija.VRATI_GRADOVE);
        kz.setParametar(new Grad());
        Komunikacija.getInstanca().posaljiZahtjev(kz);
        ServerskiTransferObjekat sto = Komunikacija.getInstanca().primiOdgovor();

        listaGradova = (ArrayList<Grad>) sto.getOdgovor();

        if (listaGradova == null || listaGradova.isEmpty()) {
            JOptionPane.showMessageDialog(null, sto.getPoruka(), "Greska", JOptionPane.ERROR_MESSAGE);
            return new ArrayList<>();
        } else {
            return listaGradova;
        }
    }

    public ArrayList<Volonter> vratiSveVolontere() {
        KlijentskiTransferObjekat kto = new KlijentskiTransferObjekat();
        kto.setOperacija(Operacija.VRATI_SVE_VOLONTERE);
        kto.setParametar(new Volonter());
        Komunikacija.getInstanca().posaljiZahtjev(kto);
        ServerskiTransferObjekat sto = Komunikacija.getInstanca().primiOdgovor();

        listaVolontera = (ArrayList<Volonter>) sto.getOdgovor();

        if (listaVolontera == null || listaVolontera.isEmpty()) {
            JOptionPane.showMessageDialog(null, sto.getPoruka(), "Greska", JOptionPane.ERROR_MESSAGE);
            return new ArrayList<>();
        } else {
            return listaVolontera;
        }
    }

    public ServerskiTransferObjekat sacuvajVolontera(Volonter vol) {
        KlijentskiTransferObjekat kto = new KlijentskiTransferObjekat();
        kto.setOperacija(Operacija.SACUVAJ_VOLONTERA);
        kto.setParametar(vol);
        Komunikacija.getInstanca().posaljiZahtjev(kto);
        ServerskiTransferObjekat sto = Komunikacija.getInstanca().primiOdgovor();
        return sto;
    }

    public ServerskiTransferObjekat izmjeniVolontera(Volonter vol) {
        KlijentskiTransferObjekat kto = new KlijentskiTransferObjekat();
        kto.setOperacija(Operacija.IZMJENA_VOLONTERA);
        kto.setParametar(vol);
        Komunikacija.getInstanca().posaljiZahtjev(kto);
        ServerskiTransferObjekat sto = Komunikacija.getInstanca().primiOdgovor();
        return sto;
    }

    public ArrayList<Volonter> pretraziVolontere(Volonter v) {
        KlijentskiTransferObjekat kto = new KlijentskiTransferObjekat();
        kto.setOperacija(Operacija.VRATI_SVE_VOLONTERE);
        kto.setParametar(v);
        Komunikacija.getInstanca().posaljiZahtjev(kto);
        ServerskiTransferObjekat sto = Komunikacija.getInstanca().primiOdgovor();

        listaVolontera = (ArrayList<Volonter>) sto.getOdgovor();

        if (listaVolontera == null || listaVolontera.isEmpty()) {
            JOptionPane.showMessageDialog(null, sto.getPoruka(), "Greska", JOptionPane.ERROR_MESSAGE);
            return new ArrayList<>();
        } else {
            return listaVolontera;
        }
    }

    public ServerskiTransferObjekat obrisiVolontera(Volonter volonter) {
        KlijentskiTransferObjekat kto = new KlijentskiTransferObjekat();
        kto.setOperacija(Operacija.BRISANJE_VOLONTERA);
        kto.setParametar(volonter);
        Komunikacija.getInstanca().posaljiZahtjev(kto);
        ServerskiTransferObjekat sto = Komunikacija.getInstanca().primiOdgovor();
        return sto;
    }

    public ArrayList<Zaduzenje> vratiSvaZaduzenja() {
        KlijentskiTransferObjekat kto = new KlijentskiTransferObjekat();
        kto.setOperacija(Operacija.VRATI_SVA_ZADUZENJA);
        kto.setParametar(new Zaduzenje());
        Komunikacija.getInstanca().posaljiZahtjev(kto);
        ServerskiTransferObjekat sto = Komunikacija.getInstanca().primiOdgovor();

        listaZaduzenja = (ArrayList<Zaduzenje>) sto.getOdgovor();

        if (listaZaduzenja == null || listaZaduzenja.isEmpty()) {
            return new ArrayList<>();
        } else {

            return listaZaduzenja;
        }
    }

    public Korisnik getTrenutnoUlogovanKorisnik() {
        return trenutnoUlogovanKorisnik;
    }

    public ServerskiTransferObjekat sacuvajZaduzenje(Zaduzenje z) {
        KlijentskiTransferObjekat kto = new KlijentskiTransferObjekat();
        kto.setOperacija(Operacija.SACUVAJ_ZADUZENJE);
        kto.setParametar(z);
        Komunikacija.getInstanca().posaljiZahtjev(kto);
        ServerskiTransferObjekat sto = Komunikacija.getInstanca().primiOdgovor();
        return sto;
    }

    public ServerskiTransferObjekat izmjeniZaduzenje(Zaduzenje z) {
        KlijentskiTransferObjekat kto = new KlijentskiTransferObjekat();
        kto.setOperacija(Operacija.IZMJENI_ZADUZENJE);
        kto.setParametar(z);
        Komunikacija.getInstanca().posaljiZahtjev(kto);
        ServerskiTransferObjekat sto = Komunikacija.getInstanca().primiOdgovor();
        return sto;
    }

    public ServerskiTransferObjekat obrisiZaduzenje(Zaduzenje zaduzenje) {
        KlijentskiTransferObjekat kto = new KlijentskiTransferObjekat();
        kto.setOperacija(Operacija.OBRISI_ZADUZENJE);
        kto.setParametar(zaduzenje);
        Komunikacija.getInstanca().posaljiZahtjev(kto);
        ServerskiTransferObjekat sto = Komunikacija.getInstanca().primiOdgovor();
        return sto;
    }

    public ArrayList<Zaduzenje> vratiZaduzenjaZaMjesec(Zaduzenje z) {
        KlijentskiTransferObjekat kto = new KlijentskiTransferObjekat();
        kto.setOperacija(Operacija.VRATI_SVA_ZADUZENJA);
        kto.setParametar(z);
        Komunikacija.getInstanca().posaljiZahtjev(kto);
        ServerskiTransferObjekat sto = Komunikacija.getInstanca().primiOdgovor();

        listaZaduzenja = (ArrayList<Zaduzenje>) sto.getOdgovor();

        if (listaZaduzenja == null || listaZaduzenja.isEmpty()) {
            return new ArrayList<>();
        } else {
            return listaZaduzenja;
        }
    }

}
