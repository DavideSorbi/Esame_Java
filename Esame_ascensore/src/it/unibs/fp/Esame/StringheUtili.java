package it.unibs.fp.Esame;

/**
 * Classe che contiene le costanti stringa utilizzate nel progetto ascensore.
 */

public class StringheUtili {
 
    public static final String SCELTA = "scegli opzione";			// Messaggio per scegliere un'opzione nel menu
    
    // Le voci del menu
    
    public static final String[] MENU_VOCI = {
    		
        "Aggiungi chiamata ascensore", 								// Opzione per aggiungere una chiamata all'ascensore
        "Inizia simulazione", 										// Opzione per iniziare la simulazione
        "Stampa chiamate" 											// Opzione per stampare le chiamate
        
    };
    
    public static final String NUM_PIANI = "Inserisci il numero di piani dell'edificio: ";						// Messaggio per l'inserimento del numero di piani dell'edificio
    
    public static final String POS_INIZ = "Inserisci la posizione iniziale dell'ascensore: ";   				// Messaggio per l'inserimento della posizione iniziale dell'ascensore
    
    public static final String ASCENSORE_AL_PIANO = "L'ascensore e' al piano "; 									// Messaggio che indica il piano corrente dell'ascensore
   
    public static final String SIMULAZIONE_TERMINATA = "Simulazione terminata. L'ascensore e' vuoto e non ci sono comandi in coda."; 					// Messaggio che indica la fine della simulazione
    
    public static final String SONO_SALITE = " Sono salite ";													// Messaggio che indica il numero di persone salite
    
    public static final String ATTUALMENTE_CI_SONO = "Attualmente ci sono ";									// Messaggio che indica il numero attuale di persone nell'ascensore
    
    public static final String L_ASCENSORE_PIENO = "L'ascensore e' pieno, la persona al piano ";					// Messaggio che indica che l'ascensore è pieno
    
    public static final String DEVE_ATTENDERE = " deve attendere";												// Messaggio che indica che una persona deve attendere
    
    public static final String SONO_SCESE = " Sono scese ";														// Messaggio che indica il numero di persone scese
    
    public static final String PERSONE = " persone ";															// Parte del messaggio che indica il numero di persone
    
    public static final String AL_PIANO = " al piano "; 														// Parte del messaggio che indica il piano
    
    public static final String DAL_PIANO = " dal piano ";														// Parte del messaggio che indica il piano di partenza
    
    public static final String DIREZIONE = "direzione: "; 														// Parte del messaggio che indica la direzione
    
}