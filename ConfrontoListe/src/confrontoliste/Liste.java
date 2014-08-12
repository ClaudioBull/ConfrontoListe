/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package confrontoliste;

import confrontoliste.DomainNameValidator;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashSet;

/**
 *
 * @author claudio
 */
public class Liste {

    public ArrayList<ArrayList<String>> listeURLs = new ArrayList<ArrayList<String>>();
    public int num;
    public String type;
    public boolean valid;
    ArrayList<String> list_name = new ArrayList<String>();

    public Liste() {

    }

    public ArrayList<ArrayList<String>> insert() throws FileNotFoundException, IOException {
        this.insertnumlist();
        ArrayList list;
        String line;

        for (int i = 0; i < num; i++) {
            System.out.println("What is the name of the " + i + "°" + " file you would like to open?");
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            list_name.add(input.readLine());
            BufferedReader reader = new BufferedReader(new FileReader(list_name.get(i) + ".txt"));

            list = new ArrayList();
            while ((line = reader.readLine()) != null) {

                switch (this.type) {
                    case "1":
                        Utilities utilities = new Utilities();
                        valid = utilities.isIpAddress(line.trim());
                        if (valid == true) {
                            list.add(line);
                        } else {
                            System.out.println("L'elemento " + line + " non rappresenta un elemento corretto");
                        }
                        break;
                    case "2":
                        String[] splitline = line.split("/");
                        DomainNameValidator domainnamevalidator = new DomainNameValidator();
                        for (int z = 0; z < splitline.length; z++) {
                            valid = domainnamevalidator.validate(splitline[z].trim());
                            if (valid == true) {
                                if (splitline[z].toLowerCase().contains("www.")) {
                                    splitline[z] = splitline[z].replace("www.", "");
                                }
                                list.add(splitline[z]);
                                continue;
                            } else {
                                System.out.println("L'elemento " + splitline[z] + " non rappresenta un elemento corretto");
                            }
                        }
                        break;
                    case "3":
                        HashValidator hashvalidator = new HashValidator();
                        valid = hashvalidator.validate(line.trim());
                        if (valid == true) {
                            list.add(line);
                        } else {
                            System.out.println("L'elemento " + line + " non rappresenta un elemento corretto");
                        }
                        break;
                    default:
                        break;
                }
            }
//            while((line = reader.readLine()) != null){
//                if (line.length() != 0 && line.charAt(0)!='#'){
//                    if (line.charAt(line.length()-1)=='/')
//                    {
//                        line = line.substring(0,line.length()-1);
//                    }
//                list.add(line);
//                }     
//            }
            // add elements to al, including duplicates
            HashSet hs = new HashSet();
            hs.addAll(list);
            list.clear();
            list.addAll(hs);
            listeURLs.add(list);
        }
        return listeURLs;
    }

    public void confrontoliste() throws FileNotFoundException {
        ArrayList find = new ArrayList();
        String w = "www.";
        FileOutputStream prova = new FileOutputStream("prova.txt");
        PrintStream scrivi = new PrintStream(prova);
        float percentuale_elaborazione;
        for (int i = 0; i < listeURLs.size() - 1; i++) {

            ArrayList list1 = listeURLs.get(i);
            ArrayList list2;
            boolean control;
            for (int z = i + 1; z < listeURLs.size(); z++) {
                int contatore = 0;
                list2 = listeURLs.get(z);
                for (int j = 0; j < list1.size(); j++) {
                    percentuale_elaborazione = (float) ((float)j/(float)list1.size()) *100;
                    System.out.println("La percentuale di elaborazione della lista " + i + " rispetto alla lista " + z + " su un totale di " + num + " liste" +" è: " + (int)percentuale_elaborazione + "%");
                    for (int k = 0; k < list2.size(); k++) {
                        String element1 = (String) list1.get(j);
                        String element2 = (String) list2.get(k);
                        control = element2.toLowerCase().equals(element1.toLowerCase()); // confronto tra gli elementi
                        if (control == true) {
                            contatore++;
                            find.add(element1); // se ci sono degli elementi in comune li salvo nella lista find
                            break;
                        }
                    }
                }
                float perc1, perc2;
                perc1 = (float) ((float) contatore / (float) list1.size()) * 100;
                perc2 = (float) ((float) contatore / (float) list2.size()) * 100;
                scrivi.println("sono stati trovati " + contatore + " elementi in comune tra la lista " + list_name.get(i) + " e la lista " + list_name.get(z));
                scrivi.println("La percentuale di elementi in comune trovati è pari al " + perc1 + "% su " + list1.size() + " elementi totali di " + list_name.get(i));
                scrivi.println("La percentuale di elementi in comune trovati è pari al " + perc2 + "% su " + list2.size() + " elementi totali di " + list_name.get(z) + "\n");
                //System.out.println("sono stati trovati " + contatore + " elementi in comune tra la lista " + listeURLs.get(i).listName + " e la lista " + listeURLs.get(z).listName);
                //System.out.println("La percentuale di elementi in comune trovati è pari al " + perc1 + "% su " + list1.size() + " elementi totali di " + listeURLs.get(i).listName);
                //System.out.println("La percentuale di elementi in comune trovati è pari al " + perc2 + "% su " + list2.size() + " elementi totali di " + listeURLs.get(z).listName + "\n");

            }
        }
    }

    public int insertnumlist() {
        // inserimento del numero di liste da confrontare
        System.out.println("Inserire il numero di liste che si vuole andare a confrontare");
        BufferedReader input_number = new BufferedReader(new InputStreamReader(System.in));
        try {
            int num_list = Integer.parseInt(input_number.readLine());

            num = num_list;
        } catch (Exception e) {
            System.out.println("Il carattere inserito non è valido");
            e.printStackTrace();
        }
        return num;
    }

    public void inserttype() throws IOException {
        // scelta di tipologia di liste da confrontare
        System.out.println("Inserire la tipologia di liste che si vuole andare a confrontare: ");
        System.out.println("1- ip \n" + "2- dominio \n" + "3- hash \n");
        BufferedReader input_type = new BufferedReader(new InputStreamReader(System.in));
        type = (String) input_type.readLine();
        if (!type.equals("1") && !type.equals("2") && !type.equals("3")) {
            System.out.println("Il tipo inserito non è valido");
        } else {
            this.insert();
            this.confrontoliste();
        }
    }

}
