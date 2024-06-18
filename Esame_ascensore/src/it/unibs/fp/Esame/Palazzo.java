package it.unibs.fp.Esame;

import java.util.*;

/**
 * Classe che rappresenta un palazzo con un certo numero di piani.
 * Contiene metodi per gestire le informazioni dei piani e delle persone.
 */

public class Palazzo {
	
	private int numeroPiani;                      	// Il numero di piani del palazzo

	/**
	 * Costruttore della classe Palazzo.
	 * 
	 * @param numeroPiani 
	 */
	
	public Palazzo(int numeroPiani) {
		this.numeroPiani = numeroPiani;           	// Imposta il numero di piani
	}

	
	// Getters e setters
	
	
	/**
	 * Restituisce il numero di piani del palazzo.
	 * 
	 * @return Il numero di piani del palazzo.
	 */
	
	public int getNumeroPiani() {
		
		return numeroPiani; 						// Ritorna il numero di piani
		
	}

	/**
	 * Imposta il numero di piani del palazzo.
	 * 
	 * @param numeroPiani 
	 */
	
	public void setNumeroPiani(int numeroPiani) {
		
		this.numeroPiani = numeroPiani; 			// Imposta il nuovo numero di piani
		
	}

	
	//Definizione del set di dati che viene caricato in fase di avvio 
	
	
	/**
	 * Inizializza i dati delle persone e dell'ascensore con informazioni predefinite.
	 * 
	 * @param personeTotali 
	 * @param ascensore 
	 */
	
	public void inizializzaDati(ArrayList<Persona> personeTotali, Ascensore ascensore) {
		
	    // Aggiunge persone alla lista totale delle persone nel palazzo
		
	    personeTotali.add(new Persona(1, 8, "salire")); 										// Persona che parte dal piano 1 e va al piano 8
	    personeTotali.add(new Persona(6, 1, "scendere")); 										// Persona che parte dal piano 6 e va al piano 1
	    personeTotali.add(new Persona(2, 10, "salire")); 										// Persona che parte dal piano 2 e va al piano 10
	    personeTotali.add(new Persona(1, 7, "salire")); 										// Persona che parte dal piano 1 e va al piano 7
	    personeTotali.add(new Persona(2, 9, "salire")); 										// Persona che parte dal piano 2 e va al piano 9

	    // Crea una nuova lista di persone presenti nell'ascensore
	    
	    ArrayList<Persona> personePresenti = new ArrayList<>();
	    personePresenti.add(new Persona(1, 8, "salire")); 										// Persona che parte dal piano 1 e va al piano 8
	    personePresenti.add(new Persona(1, 7, "salire")); 										// Persona che parte dal piano 1 e va al piano 7
	    personePresenti.add(new Persona(2, 9, "salire")); 										// Persona che parte dal piano 2 e va al piano 9

	    // Imposta la lista delle persone presenti nell'ascensore
	    
	    ascensore.setPersonePresenti(personePresenti);
	    
	}
	
}