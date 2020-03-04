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
public class Volonter implements OpstiDomenskiObjekat{

    private int volonterID;
    private String ime;
    private String prezime;
    private String kontakt;
    private Grad grad;
    private ArrayList<String> kriterijumPretrage;

    public Volonter() {
    }

    public Volonter(int volonterID, String ime, String prezime, String kontakt, Grad grad, ArrayList<String> kriterijumPretrage) {
        this.volonterID = volonterID;
        this.ime = ime;
        this.prezime = prezime;
        this.kontakt = kontakt;
        this.grad = grad;
        this.kriterijumPretrage = kriterijumPretrage;
    }

    public Grad getGrad() {
        return grad;
    }

    public void setGrad(Grad grad) {
        this.grad = grad;
    }

    public int getVolonterID() {
        return volonterID;
    }

    public void setVolonterID(int volonterID) {
        this.volonterID = volonterID;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getKontakt() {
        return kontakt;
    }

    public void setKontakt(String kontakt) {
        this.kontakt = kontakt;
    }

    public ArrayList<String> getKriterijumPretrage() {
        return kriterijumPretrage;
    }

    public void setKriterijumPretrage(ArrayList<String> kriterijumPretrage) {
        this.kriterijumPretrage = kriterijumPretrage;
    }

    @Override
    public String toString() {
        return ime + " " + prezime;
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
        final Volonter other = (Volonter) obj;
        if (this.volonterID != other.volonterID) {
            return false;
        }
        if (!Objects.equals(this.ime, other.ime)) {
            return false;
        }
        if (!Objects.equals(this.prezime, other.prezime)) {
            return false;
        }
        if (!Objects.equals(this.kontakt, other.kontakt)) {
            return false;
        }
        if (!Objects.equals(this.kriterijumPretrage, other.kriterijumPretrage)) {
            return false;
        }
        if (!Objects.equals(this.grad, other.grad)) {
            return false;
        }
        return true;
    }

    @Override
    public String vratiNazivTabele() {
        return " volonter ";
    }

    @Override
    public String vratiNaziveKolona() {
        return "volonterID, ime, prezime, kontakt, gradID";
    }

    @Override
    public String vratiVrijednostiZaUnos() {
        return "'" + ime + "','" + prezime + "', '" + kontakt + "', '" + grad.getGradID() + "'";
    }

    @Override
    public String vratiVrijednostiZaUpdate() {
        return " ime = '" + ime + "', prezime = '" + prezime + "', kontakt = " + kontakt + ", gradID = '" + grad.getGradID() + "'";
    }

    @Override
    public String vratiWhereUslov() {
        return " volonterID =" + volonterID;
    }

    @Override
    public String vratiKoloneZaSelektovanje() {
        return "*";
    }

    @Override
    public String vratiAlijas() {
        return " v ";
    }

    @Override
    public String vratiUslovZaJoin() {
        return " join Grad g on v.gradID = g.gradID ";
    }

    @Override
    public String vratiWhereUslovSelect() {
        if(kriterijumPretrage != null){
            if(kriterijumPretrage.get(0) != null && kriterijumPretrage.get(1) == null){
                return "where v.ime LIKE '%" + kriterijumPretrage.get(0) + "%'";
            }else if(kriterijumPretrage.get(0) == null && kriterijumPretrage.get(1) != null){
                return "where v.prezime LIKE '%" + kriterijumPretrage.get(1) + "%'";
            }else if(kriterijumPretrage.get(0) != null && kriterijumPretrage.get(1) != null){
                String imee = kriterijumPretrage.get(0);
                String prezimee = kriterijumPretrage.get(1);
                return "where v.ime LIKE '%" + imee + "%' AND v.prezime LIKE '%" + prezimee + "%'";
            }
        }
        return "";
    }

    @Override
    public ArrayList<OpstiDomenskiObjekat> ucitajListu(ResultSet rs) throws SQLException {
        ArrayList<OpstiDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            Grad g = new Grad(rs.getInt("gradID"), rs.getString("naziv"));
            lista.add(new Volonter(rs.getInt("volonterID"), rs.getString("ime"), rs.getString("prezime"), rs.getString("kontakt"), g, null));
        }
        return lista;
    }

    @Override
    public String vratiMaxPrimarni() {
        return "volonterID";
    }

    @Override
    public int vratiSlobodanID(ResultSet rs) {
        int slobodanID = 0;
        ArrayList<Integer> listaID = new ArrayList<>();

        try {
            while (rs.next()) {
                int id = rs.getInt("volonterID");
                listaID.add(id);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Volonter.class.getName()).log(Level.SEVERE, null, ex);
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
