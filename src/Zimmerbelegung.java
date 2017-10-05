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
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        boolean zimmerbelegung;
        
        // Einlesen der Schülerinnen aus der Datei
        FileReader reader = null;
        try {
            reader = new FileReader("data/test.txt");
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
        
        // Freunde und Nicht-Freunde zuweisen
        for (int i = 0; i < schuelerinnen.size(); i++) {
            schuelerinnen.get(i).setFreundeUndNichtFreunde(schuelerinnen);
        }
        
        // Die erste Schülerin wird einem Zimmer hinzugefügt.
        zimmer.add(new Zimmer());
        zimmerbelegung = zimmer.get(0).fuegeHinzu(schuelerinnen.get(0));
        
        // zweites Zimmer
        zimmer.add(new Zimmer());
        zimmerbelegung = zimmer.get(1).fuegeHinzu(schuelerinnen.get(2));
        
        // drittes Zimmer 
        zimmer.add(new Zimmer());
        zimmerbelegung = zimmer.get(2).fuegeHinzu(schuelerinnen.get(7));
        
        System.out.println(zimmer.get(0).zusammenlegen(zimmer.get(1)));
        System.out.println(zimmer.get(0).zusammenlegen(zimmer.get(2)));
        System.out.println(zimmer.get(1).zusammenlegen(zimmer.get(2)));
        
    } 
}
