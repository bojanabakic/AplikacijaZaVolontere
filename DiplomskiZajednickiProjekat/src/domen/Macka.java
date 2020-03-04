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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author CJ931WT
 */
public class Macka implements OpstiDomenskiObjekat{
    private int mackaID;
    private String imeMacke;
    private int starost;
    private boolean pol;
    private Rasa rasa;
    private String kriterijumPretrage;

    public Macka(int mackaID, String imeMacke, int starost, boolean pol, Rasa rasa, String kriterijumPretrage) {
        this.mackaID = mackaID;
        this.imeMacke = imeMacke;
        this.starost = starost;
        this.pol = pol;
        this.rasa = rasa;
        this.kriterijumPretrage = kriterijumPretrage;
    }

    public Macka() {
    }

    public Rasa getRasa() {
        return rasa;
    }

    public void setRasa(Rasa rasa) {
        this.rasa = rasa;
    }

    public int getMackaID() {
        return mackaID;
    }

    public void setMackaID(int mackaID) {
        this.mackaID = mackaID;
    }

    public String getImeMacke() {
        return imeMacke;
    }

    public void setImeMacke(String imeMacke) {
        this.imeMacke = imeMacke;
    }

    public int getStarost() {
        return starost;
    }

    public void setStarost(int starost) {
        this.starost = starost;
    }

    public boolean isPol() {
        return pol;
    }

    public void setPol(boolean pol) {
        this.pol = pol;
    }

    public String getKriterijumPretrage() {
        return kriterijumPretrage;
    }

    public void setKriterijumPretrage(String kriterijumPretrage) {
        this.kriterijumPretrage = kriterijumPretrage;
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
        final Macka other = (Macka) obj;
        if (this.mackaID != other.mackaID) {
            return false;
        }
        if (this.starost != other.starost) {
            return false;
        }
        if (this.pol != other.pol) {
            return false;
        }
        if (!Objects.equals(this.imeMacke, other.imeMacke)) {
            return false;
        }
        if (!Objects.equals(this.rasa, other.rasa)) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return imeMacke;
    }

    @Override
    public String vratiNazivTabele() {
        return " macka ";
    }

    @Override
    public String vratiNaziveKolona() {
        return "mackaID, imeMacke, starost, pol, rasaID";
    }

    @Override
    public String vratiVrijednostiZaUnos() {
        return "'" + imeMacke + "','" + starost + "'," + pol + "," + rasa.getRasaID();
    }

    @Override
    public String vratiVrijednostiZaUpdate() {
        return " imeMacke = '" + imeMacke + "', starost = '" + starost + "', pol = " + pol + ", rasaID = '" + rasa.getRasaID() + "'";
    }

    @Override
    public String vratiWhereUslov() {
        return " mackaID=" + mackaID;
    }

    @Override
    public String vratiKoloneZaSelektovanje() {
        return "*";
    }

    @Override
    public String vratiAlijas() {
        return " m ";
    }

    @Override
    public String vratiUslovZaJoin() {
        return " join Rasa r on m.rasaID = r.rasaID ";
    }

    @Override
    public String vratiWhereUslovSelect() {
        if (kriterijumPretrage != null) {
            return "where m.imeMacke LIKE '%" + kriterijumPretrage + "%'";
        }

        return "";
    }

    @Override
    public ArrayList<OpstiDomenskiObjekat> ucitajListu(ResultSet rs) throws SQLException {
        ArrayList<OpstiDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            Rasa r = new Rasa(rs.getInt("rasaID"), rs.getString("naziv"));
            lista.add(new Macka(rs.getInt("mackaID"), rs.getString("imeMacke"), rs.getInt("starost"), rs.getBoolean("pol"), r, new String()));
        }
        return lista;
    }

    @Override
    public String vratiMaxPrimarni() {
        return "mackaID";
    }

    @Override
    public int vratiSlobodanID(ResultSet rs) {
        int slobodanID = 0;
        ArrayList<Integer> listaID = new ArrayList<>();

        try {
            while (rs.next()) {
                int id = rs.getInt("mackaID");
                listaID.add(id);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Macka.class.getName()).log(Level.SEVERE, null, ex);
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
    
    
}
