/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author CJ931WT
 */
public class Zadatak implements OpstiDomenskiObjekat{
    private int redniBroj;
    private Zaduzenje zaduzenje;
    private String opisZadatka;
    private int potrebnoVrijeme;
    private String procjena;
    private String status;

    public Zadatak() {
    }

    public Zadatak(int redniBroj, Zaduzenje zaduzenje, String opisZadatka, int potrebnoVrijeme, String procjena, String status) {
        this.redniBroj = redniBroj;
        this.zaduzenje = zaduzenje;
        this.opisZadatka = opisZadatka;
        this.potrebnoVrijeme = potrebnoVrijeme;
        this.procjena = procjena;
        this.status = status;
    }

    public Zaduzenje getZaduzenje() {
        return zaduzenje;
    }

    public void setZaduzenje(Zaduzenje zaduzenje) {
        this.zaduzenje = zaduzenje;
    }

    public int getPotrebnoVrijeme() {
        return potrebnoVrijeme;
    }

    public void setPotrebnoVrijeme(int potrebnoVrijeme) {
        this.potrebnoVrijeme = potrebnoVrijeme;
    }

    public String getProcjena() {
        return procjena;
    }

    public void setProcjena(String procjena) {
        this.procjena = procjena;
    }

    public int getRedniBroj() {
        return redniBroj;
    }

    public void setRedniBroj(int redniBroj) {
        this.redniBroj = redniBroj;
    }

    public String getOpisZadatka() {
        return opisZadatka;
    }

    public void setOpisZadatka(String opisZadatka) {
        this.opisZadatka = opisZadatka;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return opisZadatka;
    }

    @Override
    public String vratiNazivTabele() {
        return " zadatak ";
    }

    @Override
    public String vratiNaziveKolona() {
        return "redniBroj, zaduzenjeID, opisZadatka, potrebnoVrijeme, procjena";
    }

    @Override
    public String vratiVrijednostiZaUnos() {
        return "'" + zaduzenje.getZaduzenjeID() + "', '" + opisZadatka + "', '" + potrebnoVrijeme + "', '" + procjena + "'";
    }

    @Override
    public String vratiVrijednostiZaUpdate() {
        return "opisZadatka = '" + opisZadatka + "', potrebnoVrijeme = " + potrebnoVrijeme + ", procjena = '" + procjena + "'";
    }

    @Override
    public String vratiWhereUslov() {
        return " redniBroj = " + redniBroj  + " and zaduzenjeID = " + zaduzenje.getZaduzenjeID();
    }

    @Override
    public String vratiKoloneZaSelektovanje() {
        return " * ";
    }

    @Override
    public String vratiAlijas() {
        return " zad ";
    }

    @Override
    public String vratiUslovZaJoin() {
        return " join zaduzenje z on z.zaduzenjeID = zad.zaduzenjeID ";
    }

    @Override
    public String vratiWhereUslovSelect() {
        if(zaduzenje != null)
            return " where zad.zaduzenjeID = "+ zaduzenje.getZaduzenjeID();
        
        return "";
    }

    @Override
    public ArrayList<OpstiDomenskiObjekat> ucitajListu(ResultSet rs) throws SQLException {
        ArrayList<OpstiDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            lista.add(new Zadatak(rs.getInt("redniBroj"), null, rs.getString("opisZadatka"), rs.getInt("potrebnoVrijeme"), rs.getString("procjena"), new String()));
        }
        return lista;
    }

    @Override
    public String vratiMaxPrimarni() {
        return "redniBroj";
    }

    @Override
    public int vratiSlobodanID(ResultSet rs) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
