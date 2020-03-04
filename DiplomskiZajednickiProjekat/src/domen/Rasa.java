/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author CJ931WT
 */
public class Rasa implements OpstiDomenskiObjekat{
    private int rasaID;
    private String naziv;

    public Rasa() {
    }

    public Rasa(int rasaID, String naziv) {
        this.rasaID = rasaID;
        this.naziv = naziv;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getRasaID() {
        return rasaID;
    }

    public void setRasaID(int rasaID) {
        this.rasaID = rasaID;
    }

    @Override
    public String toString() {
        return naziv;
    }
    
    @Override
    public String vratiNazivTabele() {
        return " rasa ";
    }

    @Override
    public String vratiNaziveKolona() {
        return "";
    }

    @Override
    public String vratiVrijednostiZaUnos() {
        return "";
    }

    @Override
    public String vratiVrijednostiZaUpdate() {
        return "";
    }

    @Override
    public String vratiWhereUslov() {
        return "";
    }

    @Override
    public String vratiKoloneZaSelektovanje() {
        return "*";
    }

    @Override
    public String vratiAlijas() {
        return " r ";
    }

    @Override
    public String vratiUslovZaJoin() {
        return "";
    }

    @Override
    public String vratiWhereUslovSelect() {
        return "";
    }

    @Override
    public ArrayList<OpstiDomenskiObjekat> ucitajListu(ResultSet rs) throws SQLException {
        ArrayList<OpstiDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            lista.add(new Rasa(rs.getInt("rasaID"), rs.getString("naziv")));
        }
        return lista;
    }

    @Override
    public String vratiMaxPrimarni() {
        return "";
    }

    @Override
    public int vratiSlobodanID(ResultSet rs) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Rasa other = (Rasa) obj;
        if (this.rasaID != other.rasaID) {
            return false;
        }
        if (!Objects.equals(this.naziv, other.naziv)) {
            return false;
        }
        return true;
    }

}
