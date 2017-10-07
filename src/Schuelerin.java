package zimmerbelegung;

import java.util.ArrayList;

/**
 *
 * @author Janik
 */
public class Schuelerin {
    private String name;
    private ArrayList<Schuelerin> freunde = new ArrayList<>();
    private ArrayList<Schuelerin> nichtFreunde = new ArrayList<>();
    private Zimmer zimmer;
    private String[] daten = new String[2];                                                                                                                                                  
    
    public Schuelerin(String[] daten) {
        name = daten[0];
        this.daten[0] = daten[1];
        this.daten[1] = daten[2];
    }
    
    public String getName() {
        return name;
    }
    
    public void setFreundeUndNichtFreunde(ArrayList<Schuelerin> schuelerinnen) {
        String[] freunde = daten[0].split(" ");
        for (int i = 0; i < freunde.length; i++) {
            for (int j = 0; j < schuelerinnen.size(); j++) {
                if (freunde[i].equals(schuelerinnen.get(j).getName())) {
                    this.freunde.add(schuelerinnen.get(j));
                }
            }
        }
        String[] nichtFreunde = daten[1].split(" ");
        for (int i = 0; i < nichtFreunde.length; i++) {
            for (int j = 0; j < schuelerinnen.size(); j++) {
                if (nichtFreunde[i].equals(schuelerinnen.get(j).getName())) {
                    this.nichtFreunde.add(schuelerinnen.get(j));
                }
            }
        }
    }

    public ArrayList<Schuelerin> getFreunde() {
        return freunde;
    }

    public ArrayList<Schuelerin> getNichtFreunde() {
        return nichtFreunde;
    }
    
    public void setZimmer(Zimmer zimmer) {
        this.zimmer = zimmer;
    }
    
    public Zimmer getZimmer() {
        return zimmer;
    }  
}
