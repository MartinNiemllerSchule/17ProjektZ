package zimmerbelegung;

import java.util.ArrayList;

/**
 *
 * @author Janik
 */
public class Schuelerin {
    private String name;
    private ArrayList<String> freunde = new ArrayList<>();
    private ArrayList<String> nichtFreunde = new ArrayList<>();
    
    public Schuelerin(String name, ArrayList<String> freunde, 
            ArrayList<String> nichtFreunde) {
        
        this.name = name;
        this.freunde = freunde;
        this.nichtFreunde = nichtFreunde;
    }
    
    public Schuelerin(String[] daten) {
        name = daten[0];
        String[] freunde = daten[1].split(" ");
        for (int i = 1; i < freunde.length; i++) {
            this.freunde.add(freunde[i]);
        }
        String[] nichtFreunde = daten[2].split(" ");
        for (int i = 1; i < nichtFreunde.length; i++) {
            this.nichtFreunde.add(nichtFreunde[i]);
        }
    }
}
