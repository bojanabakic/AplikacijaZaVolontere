/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author CJ931WT
 */
public class PokretanjeServera extends Thread{
    ServerSocket ss;
    boolean zaustavi;
    
    @Override
    public void run() {
        try {
            ss = new ServerSocket(9000);
            System.out.println("Server je pokrenut");
            
            while (!zaustavi) {    
                Socket soket = ss.accept();
                System.out.println("Klijent se povezao");
                ObradaKlijentskogZahtjeva okz = new ObradaKlijentskogZahtjeva(soket);
                okz.start();
//                Kontroler.getInstance().dodajKlijenta(nk);
            }
        } catch (IOException ex) {
            System.err.println("Server zaustavljen");
        }
    }
    
    public void zaustaviServer(){
        try {
            zaustavi = true;
            ss.close();
//          Kontroler.getInstance().zaustaviSveKlijente();
        } catch (IOException ex) {
            Logger.getLogger(PokretanjeServera.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
