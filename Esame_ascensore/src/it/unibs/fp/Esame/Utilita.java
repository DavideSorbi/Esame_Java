package it.unibs.fp.Esame;

import java.util.ArrayList;
import it.unibs.fp.mylib.InputDati;

/**
 * Classe di utilità per la gestione dell'input relativo alle persone che utilizzano l'ascensore.
 */

public class Utilita{

    /**
     * Gestisce l'input delle informazioni relative a una persona che desidera utilizzare l'ascensore.
     * 
     * @param posizioneIniziale 
     * @param piani 
     * @param pianoIniziale 
     * @param personeTotali
     */
	
    public static void input(int posizioneIniziale, int piani, int pianoIniziale, ArrayList<Persona> personeTotali) {
    	
        String direzione = ""; 																					// La direzione desiderata dalla persona (salire o scendere)    																							
        while (!direzione.equalsIgnoreCase("salire") && !direzione.equalsIgnoreCase("scendere")) {				// Richiede l'input della direzione finché non viene inserito un valore valido
        	
            direzione = InputDati.leggiStringaNonVuota("Una persona desidera (salire/scendere): ");
            if (!direzione.equalsIgnoreCase("salire") && !direzione.equalsIgnoreCase("scendere")) {				// Controlla se l'input è valido
            	
                System.out.println("Inserisci solo 'salire' o 'scendere'.");
                
            }
            
            if (direzione.equalsIgnoreCase("salire") && posizioneIniziale == piani) {							// Controlla se è possibile salire oltre l'ultimo piano
            	
                System.out.println("Non è possibile salire oltre il piano " + piani);
                
            }
            
            if (direzione.equalsIgnoreCase("scendere") && posizioneIniziale == 0) {       						// Controlla se è possibile scendere oltre il piano terra
                System.out.println("Non è possibile scendere oltre il piano 0");
                
            }
            
        }
        
        int piano_arrivo; 																						// Il piano di arrivo della persona
        
        // Gestisce l'input dei piani di partenza e di arrivo a seconda della direzione
        
        if (direzione.equalsIgnoreCase("salire")) {
        	
            pianoIniziale = InputDati.leggiIntero("partendo dal piano: ", 0, piani - 1);
            piano_arrivo = InputDati.leggiIntero("fino al piano: ", pianoIniziale + 1, piani);
            
        } else {
        	
            pianoIniziale = InputDati.leggiIntero("partendo dal piano: ", 1, piani);
            piano_arrivo = InputDati.leggiIntero("fino al piano: ", 0, pianoIniziale - 1);
            
        }
        
        // Crea un nuovo oggetto Persona con i dati inseriti e lo aggiunge alla lista delle persone totali
        
        Persona persona = new Persona(pianoIniziale, piano_arrivo, direzione);
        personeTotali.add(persona);
        
    }
    
}