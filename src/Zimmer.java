package zimmerbelegung;

import java.util.ArrayList;

/**
 *
 * @author Janik
 */
public class Zimmer {
    private ArrayList<Schuelerin> schuelerinnen = new ArrayList<>();
    private boolean alleGluecklich = true;
    
    public Zimmer(Schuelerin schuelerin) {
        fuegeHinzu(schuelerin);
    }
    
    public boolean fuegeHinzu(Schuelerin schuelerin) {
        /*
         * Wenn eine Nicht-Freundin der Schülerin bereits im Zimer ist, wird 
         * false zurückgegeben 
         */
        for (int i = 0; i < schuelerin.getNichtFreunde().size(); i++) {
            if (schuelerin.getNichtFreunde().get(i).getZimmer() == this) {
                System.out.println(schuelerin.getName() + " möchte nicht in "
                        + "ein Zimmer mit " 
                        + schuelerin.getNichtFreunde().get(i).getName() + ".");
                alleGluecklich = false;
                return false;
            }
        }
        
        /*
         * Wenn eine Schülerin aus dem Zimmer nicht mit der Schülerin die 
         * hinzugefügt werden soll in ein Zimmer möchte, wird false zurückgegeben 
         */
        for (int i = 0; i < schuelerinnen.size(); i++) {
            for (int j = 0; j < schuelerinnen.get(i).getNichtFreunde().size(); j++) {
                if (schuelerinnen.get(i).getNichtFreunde().get(j) == schuelerin) {
                    System.out.println(schuelerin.getName() + " möchte nicht in "
                            + "ein Zimmer mit " + schuelerinnen.get(i).getName()
                            + ".");
                    alleGluecklich = false;
                    return false;
                }
            }            
        }
        
        // Die Schülerin wird dem Zimmer hinzugefügt.
        schuelerinnen.add(schuelerin);
        schuelerin.setZimmer(this);
        
        /* 
         * Alle Freunde der Schülerin und deren Freunde werden dem Zimmer 
         * hinzugefügt, wenn niemand etwas dagegen hat.
         */
        for (int i = 0; i < schuelerin.getFreunde().size(); i++) {
            if(!fuegeHinzu(schuelerin.getFreunde().get(i))) {
                alleGluecklich = false;
                return false;
            }
        }
        return true;
    }
    
    public ArrayList<Schuelerin> getSchuelerinnen() {
        return schuelerinnen;
    }
    
    public int getGroesse() {
        return schuelerinnen.size();
    }
    
    public boolean getAlleGluecklich() {
        return alleGluecklich;
    }
}