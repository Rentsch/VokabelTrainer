import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Liste {

	private ListElement myList = null;
	private int idCounter = 1;
	//-----Konstruktoren -----
	//----- End of  Konstruktoren -----
	
	
	//----- Funktionen -----
	
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
	
	// Diese Funktion prüft ob noch Vokabeln vorhanden sind die noch nicht 3 mal richtig beantwortet wurden.
	public boolean vokabelnNochVorhanden()
	{
		ListElement tmp = myList;
		while(tmp != null)
		{
			if (tmp.getAussortierCounter() < 3)
			{
				return true;
			}
			tmp = tmp.next;
		}
		return false;
	}
	

//	
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
			return tmp;
	// "ID nicht gefunden"; 
	}
	
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
	
	public String toString() {
		return "Anfang der Liste:\n" + toStringR(myList);
	}
	
	public String toStringR(ListElement vokabel) {
		if (vokabel == null)
		{
			return ("Ende der Liste");
		}else{
			return vokabel.getEnglisch() + "-" + vokabel.getDeutsch()+ "\n" + toStringR(vokabel.getNext());
		}
	}
	

	public void readFile (String fileName) throws IOException
	{
		if (fileName.isEmpty())
		{
			fileName = "vokabelliste.txt";
		}
		BufferedReader brFileReader = new BufferedReader(new FileReader(fileName));
		String tmpZeile;
		
		String firstLine = brFileReader.readLine();
		if (firstLine.equalsIgnoreCase("[Deutsch;Englisch]"))
		{
			//System.out.println("Header gefunden."); // just for Debug
			
			// geht die datei bis zur letzten zeile durch und bricht dann ab.
			while ((tmpZeile = brFileReader.readLine()) != null) 
			{
				//System.out.println(tmpZeile); // just for Debug
				String[] tmpSplitLine = tmpZeile.split(";");
				
				// wenn die Zeile Nicht nach dem vorgegeben Standart "wort1;wort2" ist, dann fügt er diese nicht hinzu.
				if (tmpSplitLine.length == 2)
				{
					this.vokabelAdd(tmpSplitLine[1],tmpSplitLine[0]);
				}
				
			}
			
		}
		
		// schließt den Bufferreader wieder.
		brFileReader.close();
		
		
	}
}
