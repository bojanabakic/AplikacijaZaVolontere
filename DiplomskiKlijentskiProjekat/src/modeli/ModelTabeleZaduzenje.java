/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeli;

import domen.Zaduzenje;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author CJ931WT
 */
public class ModelTabeleZaduzenje extends AbstractTableModel{
    ArrayList<Zaduzenje> lista;

    public ModelTabeleZaduzenje() {
        lista = new ArrayList<>();
    }

    public ModelTabeleZaduzenje(ArrayList<Zaduzenje> lista) {
        this.lista = lista;
    }
    
    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Zaduzenje z = lista.get(rowIndex);
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        
        switch(columnIndex){
            case 0: return z.getZaduzenjeID();
            case 1: return z.getVolonter().getIme() + " " + z.getVolonter().getPrezime();
            case 2: return z.getKorisnik().getKorisnickoIme();
            case 3: 
                boolean uradjeno = z.isStatusZaduzenja();
                if(uradjeno)
                    return "odrađeno";
                else return "nije odrađeno";
            case 4: return sdf.format(z.getDatum());
            default: return "avav";
        }
    }

    @Override
    public String getColumnName(int column) {
        switch(column){
            case 0: return "ID"; 
            case 1: return "Volonter";
            case 2: return "Unio/la";
            case 3: return "Status";
            case 4: return "Datum";
            default: return "avav";
        }
    }
    
    public void sortiraj(){
        Collections.sort(lista);
    }

    public Zaduzenje dajZaduzenje(int red) {
        return lista.get(red);
    }

    public boolean izmjeni(Zaduzenje z) {
        boolean ima = false;
        if (z != null) {
            for (Zaduzenje zad : lista) {
                if (zad.getZaduzenjeID()== z.getZaduzenjeID()) {
                    zad.setKorisnik(z.getKorisnik());
                    zad.setVolonter(z.getVolonter());
                    zad.setStatusZaduzenja(z.isStatusZaduzenja());
                    zad.setDatum(z.getDatum());
                    zad.setListaZadataka(z.getListaZadataka());
                    ima = true;
                }
            }
            
            if(!ima){
                lista.add(z);
                //ako je id 0 onda radimo unos, ako nije onda radimo update
            }
            
            fireTableDataChanged();
            return true;
        } else {
            return false;
        }
    }

    public Zaduzenje obrisiZaduzenje(int red) {
        Zaduzenje z = lista.get(red);
        lista.remove(red);
        fireTableDataChanged();
        return z;
    }
}
