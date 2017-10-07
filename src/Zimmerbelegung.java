package zimmerbelegung;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Janik
 */
public class Zimmerbelegung {
    public static ArrayList<Schuelerin> schuelerinnen = new ArrayList<>();
    public static ArrayList<Zimmer> zimmer = new ArrayList<>();
    public static boolean zimmerbelegung;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // Einlesen der Schülerinnen aus der Datei
        dateiLesen();
        
        // Freunde und Nicht-Freunde zuweisen
        for (int i = 0; i < schuelerinnen.size(); i++) {
            schuelerinnen.get(i).setFreundeUndNichtFreunde(schuelerinnen);
        }
        
        // Schülerinnen auf die Zimmer verteilen
        zimmerbelegung = verteileSchuelerinnenAufZimmer();
        
        //Zimmer zusammenlegen
        zimmerZusammenlegen();
        
        // Zimmerbelegung ausgeben
        zimmerbelegungAusgeben();
    }
    
    public static void dateiLesen() {
        FileReader reader = null;
        try {
            reader = new FileReader("data/zimmerbelegung2.txt");
            BufferedReader br = new BufferedReader(reader);
            String[] daten = new String[3];
            do {
                for (int i = 0; i < 3; i++) {
                    daten[i] = br.readLine();
                }
                br.readLine(); // Leere Zeile nach jeder Schülerin
                if (daten[0] != null) schuelerinnen.add(new Schuelerin(daten));
            } while (daten[0] != null);
        } catch (IOException e) {
            System.err.println( "Fehler beim Lesen der Datei." );
        }
    }
    
    public static boolean verteileSchuelerinnenAufZimmer() {
        for (int i = 0; i < schuelerinnen.size(); i++) {
            Schuelerin s = schuelerinnen.get(i);
            
            // Wenn die Schülerin bereits in einem Zimmer ist, passiert nichts.
            if (s.getZimmer() == null) {
                
                /* 
                 * Wenn eine Freundin schon in einem Zimmer ist, wird die 
                 * Schülerin diesem hinzugefügt.
                 */
                for (int j = 0; j < s.getFreunde().size(); j++) {
                    Schuelerin freundin = s.getFreunde().get(j);
                    if (freundin.getZimmer() != null) {
                        if (!freundin.getZimmer().fuegeHinzu(s)) return false;
                        else break;
                    }
                }
                
                // Die Schülerin kommt in ein neues Zimmer.
                if (s.getZimmer() == null) {
                    Zimmer z = new Zimmer();
                    if (z.fuegeHinzu(s)) zimmer.add(z);
                    else return false;
                }
            }
        }
        
        // Leere Zimmer löschen
        for (int i = 0; i < zimmer.size(); i++) {
            if (zimmer.get(i).getSchuelerinnen().isEmpty()) {
                zimmer.remove(i);
                i--;
            }
        }
        return true;
    }
    
    public static void zimmerbelegungAusgeben() {
        if (zimmerbelegung) {
            for (int i = 0; i < zimmer.size(); i++) {
                Zimmer z = zimmer.get(i);
                System.out.print("Zimmer " + (i + 1) + ": " 
                        + z.getSchuelerinnen().get(0).getName());
                for (int j = 1; j < z.getSchuelerinnen().size(); j++) {
                    System.out.print(", " + z.getSchuelerinnen().get(j).getName());
                }
                System.out.println("");
            }
        } else System.out.println("Keine Zimmerbelegung möglich.");
    }
    
    public static void zimmerZusammenlegen() {

    }
}
