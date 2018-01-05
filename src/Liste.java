import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Diese Klasse dient als Liste.
 * An ihr haengt der das erste ListElement sowie einige Funktionen um die 
 * Liste zu befuellen und auszulesen.
 * 
 * 
 * @author 	Sebastian Rentsch
 * @Version 1.0
 *
 */
public class Liste {

	private ListElement myList = null; 	//Anfang der Liste.
	private int idCounter = 1;   		// Anfand der liste

	
	/**
	 * Diese Funktion fuegt ein neues Vokabelpaar in ein neues ListeElement ein.
	 * 
	 * 
	 * @param tmpEnglisch: Die englische uebersetzung des Vokabelpaar
	 * @param tmpDeutsch: die deutsche uebersetzung des vokbelpaar
	 */
	public void vokabelAdd (String tmpEnglisch, String tmpDeutsch)
	{
		ListElement neu = new ListElement(); 
		ListElement tmp = myList;
		
		// wenn Liste nicht leer
		if (tmp != null)
		{
			// suche bis letztes Element (next = null)
			while(tmp.next != null)
			{
				tmp = tmp.next;
			}
		
			tmp.next = neu;
			neu.next = null;		

			neu.englisch = tmpEnglisch;
			neu.deutsch = tmpDeutsch;
			neu.id = this.idCounter;
			
		}else{ // wenn Liste leer ist.
			neu.next = null;
			this.myList = neu;
			
			neu.englisch = tmpEnglisch;
			neu.deutsch = tmpDeutsch;
			neu.id = this.idCounter;
		}
		
		this.idCounter++;
	}
	
	/**
	 * Diese Funktion durchsucht die ganze Vokabelliste und prueft ob es noch eine Vokabel gibt die noch
	 * nicht 3x hinterinander richtig beantwortet wurde.
	 * 
	 * @return true = noch Vokabeln vorhanden die geprueft werden koennen. false = keine Vokabeln vorhadne.
	 */
	public boolean vokabelnNochVorhanden()
	{
		ListElement tmp = myList;
		while(tmp != null)
		{
			// wenn es ein Element mit dem Counter kleiner 3 gibt return true
			if (tmp.getAussortierCounter() < 3)
			{
				return true;
			}
			tmp = tmp.next;
		}
		// kein Element kleiner 3
		return false;
	}
	

    /**
     * Die Funktion sucht alle ListElement nach der uebergegen ID und gibt das entprechende Element zurueck.
     * 
     * @param suchID: ID nach der gesucht werden soll.
     * @return ListElement mit der ID.
     */
	public ListElement getVokabel(int suchID)
	{
		ListElement tmp = myList;
		while(tmp != null)
		{
			if (tmp.id == suchID )
				//return "ID: "+tmp.id +"=>  " + tmp.getEnglisch() + "-" + tmp.getDeutsch();
				return tmp;
			tmp = tmp.next;
		}
		return tmp;  // return null wenn ID nicht  gefunden wurde.	
	}
	
	/**
	 * Die Funktion zaehlt alle Vokabeln die in der Liste sind und gibt diese wert zurueck.
	 * 
	 * @return Anzahl an ListElementen die zur Liste gehoeren.
	 */
	public int zaehlen()
	{
		ListElement tmp = myList;
		int counter = 0;
		while(tmp != null)
		{
			counter++;
			tmp = tmp.next;
		}
		return counter;
	}
	
	/**
	 * Funktion zum Ausgeben der Kompletten Vokabelliste dient.
	 * Diese Funktion ruft die Rekurisve Funktion toStringR auf, mit der Rekursiv durch die Liste geangen wird.
	 * Zum schluss wird ein langer Sting return
	 * 
	 * @return String mit der kompletten Vokabelliste
	 */
	public String toString() 
	{
		return "Anfang der Liste:\n" + toStringR(myList);
	}
	/**
	 *  Siehe beschreibung toString
	 */
	private String toStringR(ListElement vokabel)
	{
		if (vokabel == null)
		{
			return ("Ende der Liste");
		}else{
			return vokabel.getEnglisch() + "-" + vokabel.getDeutsch()+ "\n" + toStringR(vokabel.getNext());
		}
	}
	/**
	 * Diese Funktion dient zum einlesen der Vokabelliste aus einer Externen Datei		
	 * 				
	 * 
	 * @param fileName: aus welcher Datei soll eingelesen werden.
	 * @throws IOException: wirft exception wenn einlesen der Datei nicht moeglich ist oder das File die falsche syntax hat.
	 */
	public void readFile (String fileName) throws IOException
	{
		// Wenn der uebergebene String leer ist dann wird der Default name benutzt.
		if (fileName.isEmpty())
		{
			fileName = "vokabelliste.txt";
		}
		
		//Erstellt den Reader
		BufferedReader brFileReader = new BufferedReader(new FileReader(fileName));
		String tmpZeile; // Variable fuer die einzelnen Zeilen
		String firstLine = brFileReader.readLine(); // erste zeile der Datei
		
		if (firstLine.equalsIgnoreCase("[Deutsch;Englisch]"))
		{			
			// geht die datei bis zur letzten zeile durch und bricht dann ab.
			while ((tmpZeile = brFileReader.readLine()) != null) 
			{
				// splitet die Zeile nach ; in ein Array
				String[] tmpSplitLine = tmpZeile.split(";");
				
				// wenn die Zeile Nicht nach dem vorgegeben Standart "wort1;wort2" ist, dann fuegt er diese nicht hinzu.
				if (tmpSplitLine.length == 2)
				{
					this.vokabelAdd(tmpSplitLine[1],tmpSplitLine[0]);
				}
			}//End while
		}
		else
		{
			// schliesst den Bufferreader wieder.
			brFileReader.close();
			throw new IOException("File mit falscher Syntax"); 
		}
		// schliesst den Bufferreader wieder.
		brFileReader.close();
	}
}
