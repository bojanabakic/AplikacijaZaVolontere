/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package properties;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author CJ931WT
 */
public class Properties {
    private static Properties instance;
    java.util.Properties prop;
    
    public Properties() {
        try {
            prop = new java.util.Properties();
            InputStream inputStream = new FileInputStream("db.properties");
            prop.load(inputStream);
        } catch (IOException ex) {
            Logger.getLogger(Properties.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Properties getInstance() {
        if(instance == null)
            instance = new Properties();
        return instance;
    }
    
    public String vratiVrijednost(String kljuc){
        return prop.getProperty(kljuc);
    }

    public void setUrl(String url) {
        prop.setProperty("url", url);
    }

    public void setUser(String user) {
        prop.setProperty("user", user);
    }

    public void setPassword(String password) {
        prop.setProperty("pass", password);
    }
    
    public void store(FileOutputStream out, String str) throws IOException {
        prop.store(out, str);
    }
}
