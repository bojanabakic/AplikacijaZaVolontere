/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeli;

import domen.Volonter;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author CJ931WT
 */
public class ModelTabeleVolonter extends AbstractTableModel{
    ArrayList<Volonter> lista;

    public ModelTabeleVolonter() {
    }

    public ModelTabeleVolonter(ArrayList<Volonter> lista) {
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
        Volonter v = lista.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return v.getIme();
            case 1:
                return v.getPrezime();
            case 2:
                return v.getKontakt();
            case 3:
                return v.getGrad().getNaziv();
            default:
                return "avav";
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Ime";
            case 1:
                return "Prezime";
            case 2:
                return "Kontakt";
            case 3:
                return "Grad";
            default:
                return "avav";
        }
    }

    public void setLista(ArrayList<Volonter> lista) {
        this.lista = lista;
        fireTableDataChanged();
    }

    public Volonter dajVolontera(int red) {
        return lista.get(red);
    }

    public boolean izmjeni(Volonter v) {
        if (v != null) {
            for (Volonter volonter : lista) {
                if (volonter.getVolonterID() == v.getVolonterID()) {
                    volonter.setIme(v.getIme());
                    volonter.setPrezime(v.getPrezime());
                    volonter.setKontakt(v.getKontakt());
                    volonter.setGrad(v.getGrad());
                }
            }
            fireTableDataChanged();
            return true;
        } else {
            return false;
        }
    }

    public Volonter obrisiVolontera(int red) {
        Volonter v = lista.get(red);
        lista.remove(red);
        fireTableDataChanged();
        return v;
    }
}
