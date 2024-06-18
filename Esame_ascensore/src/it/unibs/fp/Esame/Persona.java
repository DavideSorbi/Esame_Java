package it.unibs.fp.Esame;

/**
 * Classe che rappresenta una persona che utilizza l'ascensore.
 * Contiene informazioni sul piano di partenza, il piano di arrivo e la direzione del movimento.
 */

public class Persona {
	
	private int pianoPartenza; 								// Il piano di partenza della persona
	private int pianoArrivo; 								// Il piano di arrivo della persona
	private String direzione; 								// La direzione del movimento (salire o scendere)

	/**
	 * Costruttore della classe Persona.
	 * 
	 * @param pianoPartenza 
	 * @param pianoArrivo
	 * @param direzione 
	 */
	
	public Persona(int pianoPartenza, int pianoArrivo, String direzione) {
		
		this.pianoPartenza = pianoPartenza; 				// Imposta il piano di partenza
		this.pianoArrivo = pianoArrivo; 					// Imposta il piano di arrivo
		this.direzione = direzione; 						// Imposta la direzione del movimento
		
	}

	
	// Da in poi getters e i setters
	
	
	/**
	 * Restituisce la direzione del movimento della persona.
	 * 
	 * @return La direzione del movimento (salire o scendere).
	 */
	
	public String getDirezione() {
		
		return direzione;									// Ritorna la direzione del movimento
		
	}

	/**
	 * Imposta la direzione del movimento della persona.
	 * 
	 * @param direzione 
	 */
	
	public void setDirezione(String direzione) {
		
		this.direzione = direzione; 						// Imposta la nuova direzione del movimento
		
	}

	/**
	 * Restituisce il piano di partenza della persona.
	 * 
	 * @return Il piano di partenza della persona.
	 */
	
	public int getPianoPartenza() {
		
		return pianoPartenza; 								// Ritorna il piano di partenza
		
	}

	/**
	 * Imposta il piano di partenza della persona.
	 * 
	 * @param pianoPartenza 
	 */
	
	public void setPianoPartenza(int pianoPartenza) {
		
		this.pianoPartenza = pianoPartenza; 				// Imposta il nuovo piano di partenza
		
	}

	/**
	 * Restituisce il piano di arrivo della persona.
	 * 
	 * @return Il piano di arrivo della persona.
	 */
	
	public int getPianoArrivo() {
		
		return pianoArrivo; 								// Ritorna il piano di arrivo
		
	}

	/**
	 * Imposta il piano di arrivo della persona.
	 * 
	 * @param pianoArrivo 
	 */
	
	public void setPianoArrivo(int pianoArrivo) {
		
		this.pianoArrivo = pianoArrivo; 					// Imposta il nuovo piano di arrivo

	}
	
}