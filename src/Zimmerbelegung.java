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

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ArrayList<Schuelerin> schuelerinnen = new ArrayList<>();
        
        // Einlesen der Schülerinnen aus der Datei
        FileReader reader = null;
        try {
            reader = new FileReader("data/zimmerbelegung1.txt");
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
        
        // Freunde und nicht Freunde zuweisen
        for (int i = 0; i < schuelerinnen.size(); i++) {
            schuelerinnen.get(i).setFreundeUndNichtFreunde(schuelerinnen);
        }
        
        // TODO code application logic here
    }
}
