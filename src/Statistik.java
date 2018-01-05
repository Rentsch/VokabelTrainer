import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
/**
 * Diese Klasse dient dazu die Statistik der Vokabel-Ergebenisse zu erfassen.
 * 
 * 
 * @author 	Sebastian Rentsch
 * @Version 1.0
 *
 */
public class Statistik {

	private int falscheAntwort;
	private int richtigeAntwort;	
	
	public Statistik()
	{
		this.falscheAntwort 	= 0;
		this.richtigeAntwort 	= 0;		
	}
	
	/**
	 * Wenn dieser Konstruktor aufgerufen wird dann soll das Objekt als GlboaleStatistik benutzt werden.
	 * Dafuer muss dem konstruktor ein "true" uebergeben werden.
	 * Fuer die GLoable Statistik wird die Statistik aus einer externen Datei gelesen.
	 * 
	 * Ist keine Datei verfuegbar so wird ein Leeres File erzeugt und mit 0 initalisiert.
	 * 
	 * wird dem konstrukor ein "false" uebergeben so werden jediglich die varibalben mit 0 initalisiert.
	 * 
	 * @param globaleStatiklesen
	 */
	public Statistik(boolean globaleStatiklesen)
	{
		if (globaleStatiklesen)
		{
			try
			{
				this.readGlobaleStatistik("GlobaleStatistik.txt");
			}catch(IOException e1)
			{
				try
				{ 
					this.createEmptyFile();
					this.falscheAntwort 	= 0;
					this.richtigeAntwort 	= 0;	
				}catch(IOException e2)
				{
					System.out.println("Erstellen der Datei fuer Gloable Statistik nicht möglich.");
				}
	
			}
			
		}else // zweig eigentlich nicht notwendig, aber zur Fehlerrobustheit hinzugefuegt.
		{
			this.falscheAntwort 	= 0;
			this.richtigeAntwort 	= 0;		
		}
			
	}
	/**
	 * Diese Funktion addiert der richtigen Antworten 1 hinzu.
	 */
	public void addRichtig ()
	{
		richtigeAntwort++;	
	}
	
	/**
	 * Diese Funktion addiert der Flacshen Antworten 1 hinzu.
	 */
	public void addFalsch ()
	{
		
		falscheAntwort++;
	}
	/**
	 * Diese Funktion addiert die anzahl an falschen antworten und positiven antworten.
	 * Das Ergebniss ist die maximale anzahl an beantworten Vokabeln.	
	 * 
	 * 
	 * @return Anzahl an Antworten insgesamt
	 */
	public int getAnzahlAntworten()
	{
		return falscheAntwort+richtigeAntwort;
	}
	
	// Funktion fuer die Ausgabe nach eine einzelrunde.
	public void statistikAusgeben()
	{
		System.out.println("Richtige Anzahl an Antworten: "+ this.getRichtige());
		System.out.println("Falsche Anzahl an Antworten : "+ this.getFalsche());
	}
	
	/**
	 * Diese Funktion nimmt die Statistik des uebergebenen Objekts und fuegt sie 
	 * dem eigenen Objekt hinzu.
	 * 
	 * @param tmpElement: Statistik objekt was gemergt werden soll
	 */
	
	// Funktion addiert das ueberegenbe Statistik Objekt zu dem this. Statistkelement
	public void mergeStatistik(Statistik tmpElement)
	{
			this.setRichtige(this.getRichtige()+tmpElement.getRichtige());
			this.setFalsche(this.getFalsche()+tmpElement.getFalsche());
	}
	
	/**
	 * Diese Funktion schreibt die neue Globale Statistik in eine Externe Datei.
	 * Dabei wird die Datei eigentlich einfach komplett ueberschrieben.
	 * 
	 * @param fileName: aus welcher Datei soll eingelesen werden.
	 * @throws IOException: wirft exception wenn schreiben der Datei nicht möglich ist 
	 */
	// scheibt die GlobaleStatistik in Datei
	public void writeGlobaleStatistik(String fileName)throws IOException
	{
		// Objekt zum schreiben in Datei
		PrintWriter writeFile = new PrintWriter(fileName, "UTF-8");
				
		writeFile.println("[GloableStatistik]");
		writeFile.println("richtig:"+this.getRichtige());
		writeFile.println("falsch:"	+this.getFalsche());
				
		// schliessen des Write objektes.
		writeFile.close();
	}
	
	/**
	 * Diese Funktion liest die Datei fuer die GloableStatistik ein.
	 * 
	 * @param fileName: aus welcher Datei soll eingelesen werden.
	 * @throws IOException: wirft exception wenn einlesen der Datei nicht möglich ist oder das File die falsche syntax hat.
	 */
	private void readGlobaleStatistik(String fileName)throws IOException
	{
		// Wenn der uebergebene String leer ist dann wird der Default name benutzt.
		if (fileName.isEmpty())
		{
			fileName = "GlobaleStatistik.txt";
		}
		//Erstellt den Reader
		BufferedReader brFileReader = new BufferedReader(new FileReader(fileName));
		String tmpZeile;// Variable fuer die einzelnen Zeilen
		String firstLine = brFileReader.readLine();// erste zeile der Datei
		
		if (firstLine.equalsIgnoreCase("[GloableStatistik]"))
		{
			// geht die datei bis zur letzten zeile durch und bricht dann ab.
			while ((tmpZeile = brFileReader.readLine()) != null) 
			{
				//System.out.println(tmpZeile); // just for Debug
				String[] tmpSplitLine = tmpZeile.split(":");
							
				// wenn die Zeile Nicht nach dem vorgegeben Standart "wort1;wort2" ist, dann fuegt er diese nicht hinzu.
				if (tmpSplitLine.length == 2)
				{
					int zahl = Integer.parseInt(tmpSplitLine[1]);
					if (tmpSplitLine[0].equalsIgnoreCase("richtig"))
					{
						this.setRichtige(zahl);
					}
					if (tmpSplitLine[0].equalsIgnoreCase("falsch"))
					{
						this.setFalsche(zahl);
					}
				}
			}
		}else
		{
			// schliesst den Bufferreader wieder.
			brFileReader.close();
			throw new IOException("File mit falscher Syntax"); 
		}
		// schliesst den Bufferreader wieder.
		brFileReader.close();
	}
	
	/**
	 *  Diese Funktion erstellt die GlobaleStatistik.txt falls Sie bei Programm start noch nicht existiert.
	 * 
	 * @throws IOException
	 */
	
	// sollte es keine Datei fuer die GlobaleStatistik geben, wird eine erstellt.
	private void createEmptyFile() throws IOException
	{	
		// Objekt zum erstellen einer Datei
		File newFile = new File ("GlobaleStatistik.txt");
		newFile.createNewFile(); // Datei erstellen.
		
		// Objekt zum schreiben in Datei
		PrintWriter writeFile = new PrintWriter("GlobaleStatistik.txt", "UTF-8");
		
		// schreibt txt in datei
		writeFile.println("[GloableStatistik]");
		writeFile.println("richtig:0");
		writeFile.println("falsch:0");
		
		// schliessen des Write objektes.
		writeFile.close();
	}
	
	// --------- Setter und Getter -------
		// Setter und getter werden nicht weiter kommentiert, da diese selbsterklaerend sind.
	private void setFalsche(int tmpZahl)
	{
		this.falscheAntwort = tmpZahl;
	}
	public int getFalsche()
	{
		return falscheAntwort;
	}
	private void setRichtige(int tmpZahl)
	{
		this.richtigeAntwort = tmpZahl;
	}
	public int getRichtige()
	{
		return richtigeAntwort;
	}
	// END OF  --------- Setter und Getter -------
	
}
