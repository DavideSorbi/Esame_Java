package it.unibs.fp.Esame;

import java.util.*;
import it.unibs.fp.mylib.*;

/**
 * Classe principale che gestisce l'esecuzione del programma di simulazione dell'ascensore.
 */

public class Main {

    private static final int CAPIENZA = 10;

	/**
     * Metodo principale che avvia l'esecuzione del programma.
     * 
     * @param args Argomenti della riga di comando (non utilizzati).
     */
	
    public static void main(String[] args) {

        ArrayList<Persona> personeTotali = new ArrayList<>();									// Crea una lista per le persone totali
        int capienza = CAPIENZA;
        int scelta;

        int piani = InputDati.leggiInteroConMinimo(StringheUtili.NUM_PIANI, 10);				// Legge il numero di piani dell'edificio
        int posizioneIniziale = InputDati.leggiIntero(StringheUtili.POS_INIZ, 0, piani); 		// Legge la posizione iniziale dell'ascensore

        Palazzo palazzo = new Palazzo(piani);													// Crea l'oggetto Palazzo
        
        Ascensore ascensore = new Ascensore(posizioneIniziale, capienza, posizioneIniziale);	// Crea l'oggetto Ascensore

        palazzo.inizializzaDati(personeTotali, ascensore);										// Inizializza i dati dell'ascensore e delle persone nel palazzo

        String[] voci = StringheUtili.MENU_VOCI;												// Crea il menu con le opzioni disponibili
        MyMenu menu = new MyMenu(StringheUtili.SCELTA, voci);

        do {																					// Esegue un ciclo per gestire le scelte dell'utente
            
            scelta = menu.scegli();																// Mostra il menu e legge la scelta dell'utente
            switch (scelta) {
            
                case 1:
                    
                    Utilita.input(posizioneIniziale, piani, posizioneIniziale, personeTotali);	// Aggiungi una chiamata all'ascensore
                    break;

                case 2:
                   
                    ascensore.simulazione(personeTotali, palazzo); 								// Inizia la simulazione
                    break;

                case 3:
                  
                    for (Persona p : personeTotali) {   									 	// Stampa le chiamate
                    	
                        System.out.println(StringheUtili.DIREZIONE + p.getDirezione() +
                                           StringheUtili.DAL_PIANO + p.getPianoPartenza() +
                                           StringheUtili.AL_PIANO + p.getPianoArrivo());
                        
                    }
                    
                    break;
                    
            }
            
        } while (scelta != 0);																 	// Ripete il ciclo finché l'utente non sceglie di uscire

        
        ascensore.chiudiFile();	// Chiude il file di log dell'ascensore
        
    }
    
}