package confrontoliste;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.FileNotFoundException;
import java.io.IOException;
import confrontoliste.Liste;

/**
 *
 * @author claudio
 */

public class ConfrontoListe 
{

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */	
	
    public static void main(String[] args) throws FileNotFoundException, IOException {
        
        // richiamo la prima operazione sulle liste che mi porterà poi, 
        // richiamando anche altre funzioni della classe Liste, al confronto
        // delle liste stesse
        long  start = System.currentTimeMillis();
        Liste liste = new Liste();
        liste.inserttype();
        System.out.println("secondi impiegati " + (System.currentTimeMillis()-start)/1000 + "s");
        
    }
    
}
