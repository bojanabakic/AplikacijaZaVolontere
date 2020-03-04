/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package niti;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;

/**
 *
 * @author CJ931WT
 */
public class NitSat extends Thread{
    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
    JLabel labelaSat;

    public NitSat(JLabel labelaSat) {
        this.labelaSat = labelaSat;
    }    
    
    @Override
    public void run() {
        while (true) {            
            labelaSat.setText(sdf.format(new Date()));
            try {
                sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(NitSat.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
