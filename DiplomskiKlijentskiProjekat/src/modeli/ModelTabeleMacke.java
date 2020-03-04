/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeli;

import domen.Macka;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author CJ931WT
 */
public class ModelTabeleMacke extends AbstractTableModel{
    
    ArrayList<Macka> lista;

    public ModelTabeleMacke() {
    }

    public ModelTabeleMacke(ArrayList<Macka> lista) {
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
        Macka m = lista.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return m.getImeMacke();
            case 1:
                return m.getStarost();
            case 2:
                if (m.isPol()) {
                    return "ženski";
                } else {
                    return "muški";
                }
            case 3:
                return m.getRasa().getNaziv();
            default:
                return "avav";
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Ime mačke";
            case 1:
                return "Starost (godine)";
            case 2:
                return "Pol";
            case 3:
                return "Rasa";
            default:
                return "avav";
        }
    }

    public void setLista(ArrayList<Macka> lista) {
        this.lista = lista;
        fireTableDataChanged();
    }

    public Macka dajMacku(int red) {
        return lista.get(red);
    }

    public boolean izmjeni(Macka mackica) {
        if (mackica != null) {
            for (Macka macka : lista) {
                if (macka.getMackaID() == mackica.getMackaID()) {
                    macka.setImeMacke(mackica.getImeMacke());
                    macka.setStarost(mackica.getStarost());
                    macka.setPol(mackica.isPol());
                    macka.setRasa(mackica.getRasa());
                }
            }
            fireTableDataChanged();
            return true;
        }else return false;
    }

    public Macka obrisiMacku(int red) {
        Macka m = lista.get(red);
        lista.remove(red);
        fireTableDataChanged();
        return m;
    }
}
