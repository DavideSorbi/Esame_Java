package it.unibs.fp.Esame;

import java.io.*;
import java.util.*;

/**
 * Classe che rappresenta un ascensore all'interno di un palazzo.
 * Gestisce il movimento tra i piani e il carico di persone.
 */

public class Ascensore {

	private int pianoIniziale; 							// Il piano iniziale dell'ascensore
	private int pianoCorrente; 							// Il piano corrente dell'ascensore
	private int capienza; 								// La capacità massima dell'ascensore
	private List<Persona> personePresenti; 				// La lista delle persone presenti nell'ascensore
	private BufferedWriter writer; 						// Oggetto per scrivere su file

	/**
	 * Costruttore della classe Ascensore.
	 * 
	 * @param pianoIniziale        	
	 * @param capienza  			
	 * @param pianoCorrente 		
	 */
	
	public Ascensore(int pianoIniziale, int capienza, int pianoCorrente) {
		
		this.pianoIniziale = pianoIniziale; 			// Imposta il piano iniziale
		this.capienza = capienza; 						// Imposta la capienza massima
		this.personePresenti = new ArrayList<>(); 		// Inizializza la lista delle persone presenti
		this.pianoCorrente = pianoCorrente; 			// Imposta il piano corrente
		
		try {
																					// Prova ad aprire il file di log
			writer = new BufferedWriter(new FileWriter("simulazione.txt"));
			
		} catch (IOException e) {													// Gestione dell'eccezione in caso di errore nell'apertura del file
																								
			e.printStackTrace();
			
		}
		
	}
	
	
	// Da qui in poi ci sono i metodi utili per la simulazione 
	
	
	/**
	 * Avvia la simulazione del movimento dell'ascensore e della gestione delle persone.
	 * 
	 * @param personeTotali
	 * @param palazzo 
	 */
	
	public void simulazione(ArrayList<Persona> personeTotali, Palazzo palazzo) {
		
		String direzioneCorrente = "salire";												 	// La direzione corrente dell'ascensore
		if (!personeTotali.isEmpty()) {															// Se ci sono persone da trasportare
																								
			int destinazioneIniziale = personeTotali.get(0).getPianoPartenza(); 				// Ottiene il piano di partenza della prima persona
			muoviAscensore(destinazioneIniziale);												// Muove l'ascensore al piano di partenza
			
		}
			
		while (!personeTotali.isEmpty() || !isEmpty()) {										// Continua la simulazione finché ci sono persone da trasportare o l'ascensore non è vuoto
																								
			for (int i = 0; i < personeTotali.size(); i++) {
				
				Persona persona = personeTotali.get(i); 										// Ottiene la persona corrente
				if (persona.getPianoPartenza() == getPianoCorrente() && !isFull()) {			// Se la persona si trova al piano corrente e l'ascensore non è pieno
																								
					aggiungiPersona(persona); 													// Aggiunge la persona all'ascensore
					personeTotali.remove(i); 													// Rimuove la persona dalla lista delle persone totali
					i--; 																		// Decrementa l'indice per gestire correttamente la rimozione
					
				}	
				
			}
			
			List<Persona> personeDaScaricare = new ArrayList<>(); 								// Lista delle persone da scaricare
			for (Persona persona : getPersonePresenti()) {									 	// Controlla se ci sono persone da scaricare al piano corrente
																								
				if (persona.getPianoArrivo() == getPianoCorrente()) {
					
					personeDaScaricare.add(persona); 											// Aggiunge la persona alla lista delle persone da scaricare
												
				}
				
			}
			
			for (Persona persona : personeDaScaricare) {
				
				rimuoviPersona(persona);														// Rimuove le persone che devono scendere al piano corrente
																
			}

			Integer prossimoPiano = null; 														// Il prossimo piano a cui muovere l'ascensore
			if (direzioneCorrente.equals("salire")) {											// Se l'ascensore sta salendo
															
				prossimoPiano = trovaProssimoPianoSalire(personeTotali, palazzo); 				// Trova il prossimo piano verso l'alto
				if (prossimoPiano == null) {													// Se non ci sono piani verso l'alto, cambia direzione
					
					direzioneCorrente = "scendere";
					prossimoPiano = trovaProssimoPianoScendere(personeTotali);					// Trova il prossimo piano verso il basso
					
				}
				
			} else {																			// Se l'ascensore sta scendendo
				
				prossimoPiano = trovaProssimoPianoScendere(personeTotali); 						// Trova il prossimo piano verso il basso
				if (prossimoPiano == null) {													// Se non ci sono piani verso il basso, cambia direzione
					
					direzioneCorrente = "salire";		
					prossimoPiano = trovaProssimoPianoSalire(personeTotali, palazzo);			// Trova il prossimo piano verso l'alto
					
				}
				
			}

			if (prossimoPiano != null) {						// Se c'è un prossimo piano, muove l'ascensore
				
				muoviAscensore(prossimoPiano);
				
			} else {
				
				break;											// Se non ci sono più piani, termina la simulazione
				
			}
			
		}

		System.out.println(StringheUtili.SIMULAZIONE_TERMINATA);
		scriviLog(StringheUtili.SIMULAZIONE_TERMINATA);
		
	}

	/**
	 * Muove l'ascensore al piano target specificato.
	 * 
	 * @param pianoTarget
	 */
	
	public void muoviAscensore(int pianoTarget) {
		
		while (pianoCorrente != pianoTarget) {					// Continua a muovere l'ascensore fino a raggiungere il piano target
			
			if (pianoCorrente < pianoTarget) {
				
				pianoCorrente++;								// Sale di un piano	
				
			} else {
				
				pianoCorrente--; 								// Scende di un piano
				
			}
			
			System.out.println(StringheUtili.ASCENSORE_AL_PIANO + pianoCorrente);
			scriviLog(StringheUtili.ASCENSORE_AL_PIANO + pianoCorrente);
			
		}
		
	}

	/**
	 * Trova il prossimo piano verso cui muovere l'ascensore salendo.
	 * 
	 * @param personeTotali 
	 * @param palazzo 
	 * @return Il prossimo piano verso cui muovere l'ascensore, o null se non ci sono piani verso l'alto.
	 */
	
	public Integer trovaProssimoPianoSalire(ArrayList<Persona> personeTotali, Palazzo palazzo) {
		
		for (int piano = getPianoCorrente() + 1; piano <= palazzo.getNumeroPiani(); piano++) {			// Cicla attraverso i piani sopra il piano corrente
			
			for (Persona persona : personeTotali) {
				
				if (persona.getPianoPartenza() == piano || persona.getPianoArrivo() == piano) {			// Se una persona deve salire o scendere a questo piano
					
					return piano;																		// Ritorna il piano
				
				}
				
			}
			
			for (Persona persona : getPersonePresenti()) {
				
				if (persona.getPianoArrivo() == piano) {												// Se una persona presente nell'ascensore deve scendere a questo piano
					
					return piano; 																		// Ritorna il piano
											
				}
				
			}
			
		}
		
		return null;																					// Non ci sono piani verso l'alto
		
	}

	/**
	 * Trova il prossimo piano verso cui muovere l'ascensore scendendo.
	 * 
	 * @param personeTotali 
	 * @return Il prossimo piano verso cui muovere l'ascensore, o null se non ci sono piani verso il basso.
	 */
	
	public Integer trovaProssimoPianoScendere(ArrayList<Persona> personeTotali) {
		
		for (int piano = getPianoCorrente() - 1; piano >= 0; piano--) {									// Cicla attraverso i piani sotto il piano corrente
			
			for (Persona persona : personeTotali) {
				
				if (persona.getPianoPartenza() == piano || persona.getPianoArrivo() == piano) {			// Se una persona deve salire o scendere a questo pia
					
					return piano;																		// Ritorna il piano
					
				}
				
			}
			
			for (Persona persona : getPersonePresenti()) {
				
				if (persona.getPianoArrivo() == piano) {												// Se una persona presente nell'ascensore deve scendere a questo piano
					
					return piano; 																		// Ritorna il piano
					
				}
				
			}
			
		}
		
		return null; 																					// Non ci sono piani verso il basso
		
	}
	
	/**
	 * Aggiunge una persona all'ascensore.
	 * 
	 * @param persona  
	 */
	
	public void aggiungiPersona(Persona persona) {
		
		int count = 0;
		if (personePresenti.size() < capienza) {
			
			count++;
			personePresenti.add(persona); 																				// Aggiunge la persona alla lista delle persone presenti
			System.out.println(StringheUtili.SONO_SALITE + count + StringheUtili.PERSONE);
			System.out.println(StringheUtili.ATTUALMENTE_CI_SONO + personePresenti.size() + StringheUtili.PERSONE);
			scriviLog(StringheUtili.SONO_SALITE + count + StringheUtili.PERSONE);
			scriviLog(StringheUtili.ATTUALMENTE_CI_SONO + personePresenti.size() + StringheUtili.PERSONE);
			
		} else {																										
			
			
			System.out.println(StringheUtili.L_ASCENSORE_PIENO + persona.getPianoPartenza() + StringheUtili.DEVE_ATTENDERE);
			
		}
		
	}

	/**
	 * Rimuove una persona dall'ascensore.
	 * 
	 * @param persona
	 */
	
	public void rimuoviPersona(Persona persona) {
		
		int count = 0;
		for (int i = 0; i < personePresenti.size(); i++) {
			
			if (personePresenti.get(i).getPianoArrivo() == pianoCorrente) {
				
				count++;
				personePresenti.remove(persona);											// Rimuove la persona dalla lista delle persone presenti
				
			}
			
		}
		
		System.out.println(StringheUtili.SONO_SCESE + count + StringheUtili.PERSONE);
		System.out.println(StringheUtili.ATTUALMENTE_CI_SONO + personePresenti.size() + StringheUtili.PERSONE);
		scriviLog(StringheUtili.SONO_SCESE + count + StringheUtili.PERSONE);
		scriviLog(StringheUtili.ATTUALMENTE_CI_SONO + personePresenti.size() + StringheUtili.PERSONE);
		
	}

	/**
	 * Controlla se ci sono persone al piano corrente che vogliono salire o scendere.
	 * 
	 * @param direzione 
	 * @return true se ci sono persone al piano corrente che vogliono salire o scendere, false altrimenti.
	 */
	
	public boolean controllaPiano(String direzione) {
		
		for (int i = 0; i < personePresenti.size(); i++) {
			
			if (personePresenti.get(i).getPianoPartenza() == pianoCorrente) {
				
				if (direzione.equals("salire") && personePresenti.get(i).getDirezione().equals("salire")) {
					
					return true;																							// C'è una persona che vuole salire
					
				} else if (direzione.equals("scendere") && personePresenti.get(i).getDirezione().equals("scendere")) {
					
					return true;																							// C'è una persona che vuole scendere
					
				}
				
			}
			
		}
		
		return false; 																										// Non ci sono persone al piano corrente che vogliono salire o scendere
		
	}
	
	/**
	 * Verifica se l'ascensore è vuoto.
	 * 
	 * @return true se l'ascensore è vuoto, false altrimenti.
	 */
	
	public boolean isEmpty() {
		return personePresenti.isEmpty();
	}

	/**
	 * Verifica se l'ascensore è pieno.
	 * 
	 * @return true se l'ascensore è pieno, false altrimenti.
	 */
	
	public boolean isFull() {
		return personePresenti.size() >= capienza;
	}

	
	// Da qua in poi sono presenti i getters e i setters dei vari attributi e liste

	
	/**
	 * Ritorna la lista delle persone presenti nell'ascensore.
	 * 
	 * @return La lista delle persone presenti nell'ascensore.
	 */
	
	public List<Persona> getPersonePresenti() {
		return personePresenti;
	}

	/**
	 * Imposta la lista delle persone presenti nell'ascensore.
	 * 
	 * @param personePresenti 
	 */
	
	public void setPersonePresenti(List<Persona> personePresenti) {
		this.personePresenti = personePresenti;
	}

	/**
	 * Ritorna il piano corrente dell'ascensore.
	 * 
	 * @return Il piano corrente dell'ascensore.
	 */
	
	public int getPianoCorrente() {
		return pianoCorrente;
	}

	/**
	 * Imposta il piano corrente dell'ascensore.
	 * 
	 * @param pianoCorrente 
	 */
	
	public void setPianoCorrente(int pianoCorrente) {
		this.pianoCorrente = pianoCorrente;
	}

	/**
	 * Ritorna il piano iniziale dell'ascensore.
	 * 
	 * @return Il piano iniziale dell'ascensore.
	 */
	
	public int getPianoIniziale() {
		return pianoIniziale;
	}
	
	/**
	 * Imposta il piano iniziale dell'ascensore.
	 * 
	 * @param pianoIniziale 
	 */
	
	public void setPianoIniziale(int pianoIniziale) {
		this.pianoIniziale = pianoIniziale;
	}

	/**
	 * Ritorna la capienza massima dell'ascensore.
	 * 
	 * @return La capienza massima dell'ascensore.
	 */
	
	public int getCapienza() {
		return capienza;
	}

	/**
	 * Imposta la capienza massima dell'ascensore.
	 * 
	 * @param capienza
	 */
	
	public void setCapienza(int capienza) {
		this.capienza = capienza;
	}

	
	// Da qua i metodi per chiudere il file e per la scrittura nel file della simulazione

	
	/**
	 * Scrive un messaggio nel file di log.
	 * 
	 * @param messaggio 
	 */
	
	private void scriviLog(String messaggio) {
		
		try {
			
			if (writer != null) {
				
				writer.write(messaggio); 				// Scrive il messaggio nel file
				writer.newLine();						// Aggiunge una nuova riga
				writer.flush(); 						// Forza lo svuotamento del buffer
			}
			
		} catch (IOException e) {
			
			e.printStackTrace(); 						// Gestione dell'eccezione in caso di errore nella scrittura del file
																			
		}
		
	}
	
	/**
	 * Chiude il file di log.
	 */
	
	public void chiudiFile() {
		
		try {
			
			if (writer != null) {
				
				writer.close(); 						// Chiude il BufferedWriter
			}
			
		} catch (IOException e) {
			
			e.printStackTrace();						// Gestione dell'eccezione in caso di errore nella chiusura del file
			
		}
		
	}
}