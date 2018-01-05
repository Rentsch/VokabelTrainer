/**
 * Diese Klasse ist fuer ein ListElement der Vokabellsite.
 * Ein ListElement ist ein Vokabelpaar. 
 * 
 * 
 * @author 	Sebastian Rentsch
 * @Version 1.0
 *
 */
public class ListElement {

	String englisch; 		// die englische uebersetzun der Vokabel
	String deutsch; 		//die deutsche uebersetzun der Vokabel 
	int id; 				// eindeutige ID fuer das Vokbaelpaar
	int aussortierCounter;  // Counter fuer das aussortieren von Vokabeln die 3x hintereinander richtig beantwortet wurden.
	ListElement next;		// nachstes Element der 

	// !!! Hinweis !!!
	// Aufgabenstellung inhaltlich unklar: 
	// 
	// "Eine Vokabel, die dreimal hintereinander richti uebersetzt wurde, wird in der aktuellen Sesion nicht mehr geprueft."
	//
	// Da Session von mir in dem Programm anders verwendet wird, interpretiere ich die "sesion" in der Aufgabenstellung
	// ALs Programmlauf. Erst nach dem neustart des Proramms werden die aussortierCounter wieder auf 0 gesetzt.
	
	
	/**
	 * Konstruktor fuer ein ListElement. 
	 * 
	 * @param tmpEnglisch: die englische uebersetzun der Vokabele
	 * @param tmpDeutsch: die deutsche uebersetzun der Vokabel 
	 * @param tmpID: die Id die die vokabel bekommt um spaeter anhand der zufallszahl gefunden zu werden.
	 */
	public ListElement(String tmpEnglisch, String tmpDeutsch, int tmpID)
	{
		this.englisch = tmpEnglisch;
		this.deutsch = tmpDeutsch;
		this.id = tmpID;
		this.aussortierCounter = 0;
		this.next = null; 
	}/**
	 * Konstruktor fuer ein ListeELement ohne uebergabe Paramter.
	 * Dieser Kontruktor wird eigentlich ausschliesslich fuer die 
	 * erstellung des ListElement in der Funktion vokabelAdd vom List Objekt benoetigt und sollte nicht 
	 * weiter aufgerufen werden.
	 */
	public ListElement()
	{		
		this.englisch = null;
		this.deutsch = null;
		this.id = 0;
		this.aussortierCounter = 0;
		this.next = null; 	
	}		
	
	/**
	 * Diese Funktion erhoeht den Counter um 1.
	 */
	public void plusAussortierCounter() {
		this.aussortierCounter++;
	}
	
	/**
	 * Diese Funktion setzt den Counter auf 0
	 */
	public void nullifieAussortierCounter() {
		this.aussortierCounter = 0;
	}
	// --------- Setter und Getter -------
	// Setter und getter werden nicht weiter kommentiert, da diese selbsterklaerend sind.
	public String getEnglisch() {
		return englisch;
	}

	public void setEnglisch(String englisch) {
		this.englisch = englisch;
	}

	public String getDeutsch() {
		return deutsch;
	}

	public void setDeutsch(String deutsch) {
		this.deutsch = deutsch;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAussortierCounter() {
		return aussortierCounter;
	}

	public ListElement getNext() {
		return next;
	}
	
	public void setNext(ListElement next) {
		this.next = next;
	}
	// END OF  --------- Setter und Getter -------
}
