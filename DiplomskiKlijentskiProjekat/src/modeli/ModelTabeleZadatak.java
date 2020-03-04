/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeli;

import domen.Zadatak;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import tipoviAkcija.Statusi;

/**
 *
 * @author CJ931WT
 */
public class ModelTabeleZadatak extends AbstractTableModel {
    ArrayList<Zadatak> listaZaBrisanje;
    ArrayList<Zadatak> lista;

    public ModelTabeleZadatak() {
        lista = new ArrayList<>();
    }

    public ModelTabeleZadatak(ArrayList<Zadatak> lista) {
        this.lista = lista;
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Zadatak z = lista.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return z.getRedniBroj();
            case 1:
                return z.getOpisZadatka();
            case 2:
                return z.getPotrebnoVrijeme();
            case 3:
                return z.getProcjena();
            default:
                return "avav";
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Rbr";
            case 1:
                return "Opis Zadatka";
            case 2:
                return "Potrebno Vrijeme";
            case 3:
                return "Procjena";
            default:
                return "avav";
        }
    }

    public ArrayList<Zadatak> getLista() {
        return lista;
    }

    public void setLista(ArrayList<Zadatak> lista) {
        this.lista = lista;
    }

    public Zadatak vratiZadatak(int red) {
        return lista.get(red);
    }

    public void dodajZadatak(Zadatak z) {
        lista.add(z);
        fireTableDataChanged();
    }

    public void izmjeni(Zadatak z) {
        if (z != null) {
            for (Zadatak zad : lista) {
                if (zad.getRedniBroj() == z.getRedniBroj()) {
                    zad.setOpisZadatka(z.getOpisZadatka());
                    zad.setProcjena(z.getProcjena());
                    zad.setPotrebnoVrijeme(z.getPotrebnoVrijeme());
                    zad.setStatus(z.getStatus());
                }
            }
            fireTableDataChanged();
        }
    }

    public Zadatak obrisi(int red) {
        Zadatak z = lista.get(red);
        lista.remove(red);
        fireTableDataChanged();
        return z;
    }

    public int dajID() {
        int indeks = 0;
        for (Zadatak s : lista) {
            if (s.getRedniBroj() > indeks) {
                indeks = s.getRedniBroj();
            }
        }
        return indeks + 1;
    }

    public void srediRB() {
        for (int i = 0; i < lista.size(); i++) {
            lista.get(i).setRedniBroj(i + 1);
        }
    }

    public void izbrisiZadatak(int red) {
        Zadatak z = lista.remove(red);
       
       if(!z.getStatus().equals(Statusi.SACUVAJ)){
           z.setStatus(Statusi.OBRISI);
           listaZaBrisanje.add(z);
       }
       fireTableDataChanged();
    }
}
