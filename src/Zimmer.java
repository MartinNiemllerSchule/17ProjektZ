package zimmerbelegung;

import java.util.ArrayList;

/**
 *
 * @author Janik
 */
public class Zimmer {
    private ArrayList<Schuelerin> schuelerinnen = new ArrayList<>();
    
    public boolean fuegeHinzu(Schuelerin s) {
        
        // Die Schülerin ist bereits in diesem Zimmer.
        if (s.getZimmer() == this) return true;
        
        /*
         * Wenn eine Nicht-Freundin der Schülerin bereits im Zimemr ist, wird 
         * false zurückgegeben.
         */
        for (int i = 0; i < s.getNichtFreunde().size(); i++) {
            if (s.getNichtFreunde().get(i).getZimmer() == this) {
                System.out.println(s.getName() + " möchte nicht in ein Zimmer mit " 
                        + s.getNichtFreunde().get(i).getName() + ".");
                return false;
            }
        }
        
        /*
         * Wenn eine Schülerin aus dem Zimmer nicht mit der Schülerin die 
         * hinzugefügt werden soll in ein Zimmer möchte, wird false zurückgegeben.
         */
        for (int i = 0; i < schuelerinnen.size(); i++) {
            for (int j = 0; j < schuelerinnen.get(i).getNichtFreunde().size(); j++) {
                if (schuelerinnen.get(i).getNichtFreunde().get(j) == s) {
                    System.out.println(s.getName() + " möchte nicht in ein Zimmer "
                            + "mit " + schuelerinnen.get(i).getName() + ".");
                    return false;
                }
            }            
        }
        
        // Die Schülerin wird dem Zimmer hinzugefügt.
        schuelerinnen.add(s);
        s.setZimmer(this);
        
        /* 
         * Alle Freunde der Schülerin und deren Freunde werden dem Zimmer 
         * hinzugefügt. 
         * Wenn die Freundin bereits in einem anderen Zimmer ist, werden die 
         * beiden Zimmer zusammengelegt.
         */
        for (int i = 0; i < s.getFreunde().size(); i++) {
            Schuelerin freundin = s.getFreunde().get(i);
            if (freundin.getZimmer() != this) {
                if (freundin.getZimmer() != null) {
                    if (!zusammenlegen(freundin.getZimmer())) return false;
                } 
                if(!fuegeHinzu(freundin)) return false;
            } 
        }
        return true;
    }
    
    public ArrayList<Schuelerin> getSchuelerinnen() {
        return schuelerinnen;
    }

    public boolean zusammenlegen(Zimmer z) {
        
        /*
         * Alle Nicht-Freunde jeder Schülerin aus Zimmer 1 werden mit den
         * Schülerinnen aus Zimmer 2 verglichen.
         */
        for (int i = 0; i < schuelerinnen.size(); i++) {
            Schuelerin s = schuelerinnen.get(i);
            for (int j = 0; j < s.getNichtFreunde().size(); j++) {
                for (int k = 0; k < z.getSchuelerinnen().size(); k++) {
                    if (s.getNichtFreunde().get(j) == z.getSchuelerinnen().get(k))
                        return false;
                }
            }
        }
        
        /*
         * Alle Nicht-Freunde jeder Schülerin aus Zimmer 2 werden mit den
         * Schülerinnen aus Zimmer 1 verglichen.
         */
        for (int i = 0; i < z.getSchuelerinnen().size(); i++) {
            Schuelerin schuelerin = z.getSchuelerinnen().get(i);
            for (int j = 0; j < schuelerin.getNichtFreunde().size(); j++) {
                for (int k = 0; k < schuelerinnen.size(); k++) {
                    if (schuelerin.getNichtFreunde().get(j) == schuelerinnen.get(k))
                        return false;
                }
            }
        }
        
        // Alle Schülerinnen aus Zimmer 2 werden Zimmer 1 hinzugefügt.
        for (int i = 0; i < z.getSchuelerinnen().size();) {
            schuelerinnen.add(z.getSchuelerinnen().get(i));
            z.getSchuelerinnen().get(i).setZimmer(this);
            z.getSchuelerinnen().remove(i);
        }
        return true;
    }
}
