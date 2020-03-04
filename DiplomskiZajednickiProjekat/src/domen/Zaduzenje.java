/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author CJ931WT
 */
public class Zaduzenje implements OpstiDomenskiObjekat, Comparable{
    private int zaduzenjeID;
    private boolean statusZaduzenja;
    private Date datum;
    private Volonter volonter;
    private Korisnik korisnik;
    private ArrayList<Zadatak> listaZadataka;
    private int kriterijumPretrage;

    public Zaduzenje() {
        listaZadataka = new ArrayList<>();
    }

    public Zaduzenje(int zaduzenjeID, boolean statusZaduzenja, Date datum, Volonter volonter, Korisnik korisnik, ArrayList<Zadatak> listaZadataka, int kriterijumPretrage) {
        this.zaduzenjeID = zaduzenjeID;
        this.statusZaduzenja = statusZaduzenja;
        this.datum = datum;
        this.volonter = volonter;
        this.korisnik = korisnik;
        this.listaZadataka = listaZadataka;
        this.kriterijumPretrage = kriterijumPretrage;
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }

    public int getZaduzenjeID() {
        return zaduzenjeID;
    }

    public void setZaduzenjeID(int zaduzenjeID) {
        this.zaduzenjeID = zaduzenjeID;
    }

    public boolean isStatusZaduzenja() {
        return statusZaduzenja;
    }

    public void setStatusZaduzenja(boolean statusZaduzenja) {
        this.statusZaduzenja = statusZaduzenja;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public Volonter getVolonter() {
        return volonter;
    }

    public void setVolonter(Volonter volonter) {
        this.volonter = volonter;
    }
    
    public int getKriterijumPretrage() {
        return kriterijumPretrage;
    }

    public void setKriterijumPretrage(int kriterijumPretrage) {
        this.kriterijumPretrage = kriterijumPretrage;
    }

    @Override
    public String vratiNazivTabele() {
        return " zaduzenje ";
    }

    @Override
    public String vratiNaziveKolona() {
        return "zaduzenjeID, statusZaduzenja, datum, volonterID, korisnikID";
    }

    @Override
    public String vratiVrijednostiZaUnos() {
        return 0 + ", '" + new java.sql.Date(datum.getTime()) + "', '" + volonter.getVolonterID() + "', '" + korisnik.getKorisnikID() + "'";
    }

    @Override
    public String vratiVrijednostiZaUpdate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return "statusZaduzenja = " + statusZaduzenja + ", datum = '" + sdf.format(datum)
                + "', volonterID = '" + volonter.getVolonterID() + "', korisnikID = '" + korisnik.getKorisnikID() + "'";
    }

    @Override
    public String vratiWhereUslov() {
        return " zaduzenjeID=" + zaduzenjeID;
    }

    @Override
    public String vratiKoloneZaSelektovanje() {
        return " * ";
    }

    @Override
    public String vratiAlijas() {
        return " z ";
    }

    @Override
    public String vratiUslovZaJoin() {
        return " join volonter v on z.volonterID = v.volonterID join korisnik k on k.korisnikID = z.korisnikID join grad g on g.gradID = v.gradID ";
    }

    @Override
    public String vratiWhereUslovSelect() {
        int[] mjeseci = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        for (int m : mjeseci) {
            if (m == kriterijumPretrage) {
                return "where MONTH(z.datum) LIKE " + kriterijumPretrage;
            }
        }
        return "";
    }

    @Override
    public ArrayList<OpstiDomenskiObjekat> ucitajListu(ResultSet rs) throws SQLException {
        ArrayList<OpstiDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            Grad g = new Grad(rs.getInt("gradID"), rs.getString("naziv"));
            Volonter v = new Volonter(rs.getInt("volonterID"), rs.getString("ime"), rs.getString("prezime"), rs.getString("kontakt"), g, null);
            Korisnik k = new Korisnik(rs.getInt("korisnikID"), rs.getString("korisnickoIme"), rs.getString("lozinka"));
            lista.add(new Zaduzenje(rs.getInt("zaduzenjeID"), rs.getBoolean("statusZaduzenja"), new java.sql.Date(rs.getDate("datum").getTime()), v, k, null, 0));
        }
        return lista;
    }

    @Override
    public String vratiMaxPrimarni() {
        return "zaduzenjeID";
    }
    
    @Override
    public int compareTo(Object o) {
        Date dat = ((Zaduzenje) o).getDatum();
        return this.datum.compareTo(dat);
    }


    @Override
    public int vratiSlobodanID(ResultSet rs) {
        int slobodanID = 0;
        ArrayList<Integer> listaID = new ArrayList<>();

        try {
            while (rs.next()) {
                int id = rs.getInt("zaduzenjeID");
                listaID.add(id);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Zaduzenje.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (listaID.size() == 0) {
            return 1;
        }
        for (int i = 0; i < listaID.size(); i++) {
            if ((i + 1) == listaID.size()) {
                return listaID.get(i) + 1;
            } else if ((listaID.get(i) + 1) < listaID.get(i + 1)) {
                return listaID.get(i) + 1;
            }
        }
        return slobodanID;
    }

    public ArrayList<Zadatak> getListaZadataka() {
        return listaZadataka;
    }

    public void setListaZadataka(ArrayList<Zadatak> listaZadataka) {
        this.listaZadataka = listaZadataka;
    }

}
