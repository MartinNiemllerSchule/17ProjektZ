package zimmerbelegung;

import java.util.ArrayList;

/**
 *
 * @author Janik
 */
public class Zimmer {
    private ArrayList<Schuelerin> schuelerinnen = new ArrayList<>();
    
    public Zimmer() {
        
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
                    return false;
                }
            }            
        }
        
        // Die Schülerin wird dem Zimmer hinzugefügt.
        schuelerinnen.add(schuelerin);
        schuelerin.setZimmer(this);
        
        /* 
         * Alle Freunde der Schülerin und deren Freunde werden dem Zimmer 
         * hinzugefügt, wenn sie nicht bereits in einen anderem Zimmer sind und 
         * niemand etwas dagegen hat.
         */
        for (int i = 0; i < schuelerin.getFreunde().size(); i++) {
            if (schuelerin.getFreunde().get(i).getZimmer() != null) continue; 
            if(!fuegeHinzu(schuelerin.getFreunde().get(i))) return false;
        }
        return true;
    }
    
    public ArrayList<Schuelerin> getSchuelerinnen() {
        return schuelerinnen;
    }
    
    public boolean zusammenlegen(Zimmer zimmer) {
        
        /*
         * Alle Nicht-Freunde jeder Schülerin aus Zimmer 1 werden mit den
         * Schülerinnen aus Zimmer 2 verglichen.
         */
        for (int i = 0; i < schuelerinnen.size(); i++) {
            Schuelerin schuelerin = schuelerinnen.get(i);
            for (int j = 0; j < schuelerin.getNichtFreunde().size(); j++) {
                for (int k = 0; k < zimmer.getSchuelerinnen().size(); k++) {
                    if (schuelerin.getNichtFreunde().get(j)
                            == zimmer.getSchuelerinnen().get(k)) return false;
                }
            }
        }
        
        /*
         * Alle Nicht-Freunde jeder Schülerin aus Zimmer 2 werden mit den
         * Schülerinnen aus Zimmer 1 verglichen.
         */
        for (int i = 0; i < zimmer.getSchuelerinnen().size(); i++) {
            Schuelerin schuelerin = zimmer.getSchuelerinnen().get(i);
            for (int j = 0; j < schuelerin.getNichtFreunde().size(); j++) {
                for (int k = 0; k < schuelerinnen.size(); k++) {
                    if (schuelerin.getNichtFreunde().get(j) 
                            == schuelerinnen.get(k)) return false;
                }
            }
        }
        
        for (int i = 0; i < zimmer.getSchuelerinnen().size();) {
            schuelerinnen.add(zimmer.getSchuelerinnen().get(i));
            zimmer.getSchuelerinnen().get(i).setZimmer(this);
            zimmer.getSchuelerinnen().remove(i);
        }
        
        
        return true;
    }
    
}
