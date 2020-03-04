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
public class Grad implements OpstiDomenskiObjekat {

    private int gradID;
    private String naziv;

    public Grad() {
    }

    public Grad(int gradID, String naziv) {
        this.gradID = gradID;
        this.naziv = naziv;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getGradID() {
        return gradID;
    }

    public void setGradID(int gradID) {
        this.gradID = gradID;
    }

    @Override
    public String vratiNazivTabele() {
        return " grad ";
    }

    @Override
    public String toString() {
        return naziv;
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
        return  "*";
    }

    @Override
    public String vratiAlijas() {
        return " g ";
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
            lista.add(new Grad(rs.getInt("gradID"), rs.getString("naziv")));
        }
        return lista;
    }

    @Override
    public String vratiMaxPrimarni() {
        return "";
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
        final Grad other = (Grad) obj;
        if (this.gradID != other.gradID) {
            return false;
        }
        return true;
    }

    @Override
    public int vratiSlobodanID(ResultSet rs) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
