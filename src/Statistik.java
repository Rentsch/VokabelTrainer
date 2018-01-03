import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Statistik {

	private int falscheAntwort;
	private int richtigeAntwort;
	
	public Statistik()
	{
		this.falscheAntwort 	= 0;
		this.richtigeAntwort 	= 0;		
	}
	
	// extra behandlung wenn objekt für Globale Statistik genutzt werdne soll.
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
					System.out.println("Erstellen der Datei für Gloable Statistik nicht möglich.");
				}
	
			}
			
		}else // zweig eigentlich nicht notwendig, aber zur Fehlerrobustheit hinzugefügt.
		{
			this.falscheAntwort 	= 0;
			this.richtigeAntwort 	= 0;		
		}
			
	}

	public void addRichtig ()
	{
		richtigeAntwort++;	
	}
	
	public void addFalsch ()
	{
		
		falscheAntwort++;
	}
	
	private void setFalsche(int tmpZahl)
	{
		this.falscheAntwort = tmpZahl;
	}
	
	public int getRichtige()
	{
		return richtigeAntwort;
	}
	
	public int getFalsche()
	{
		return falscheAntwort;
	}
	
	private void setRichtige(int tmpZahl)
	{
		this.richtigeAntwort = tmpZahl;
	}
	
	public int Falsche()
	{
		return richtigeAntwort;
	}
	
	public int getAnzahlAntworten()
	{
		return falscheAntwort+richtigeAntwort;
	}
	
	// Funktion für die Ausgabe nach eine einzelrunde.
	public void statistikAusgeben()
	{
		System.out.println("Richtige Anzahl an Antworten: "+ this.getRichtige());
		System.out.println("Falsche Anzahl an Antworten : "+ this.getFalsche());
	}
	
	// Funktion addiert das überegenbe Statistik Objekt zu dem this. Statistkelement
	public void mergeStatistik(Statistik tmpElement)
	{
			this.setRichtige(this.getRichtige()+tmpElement.getRichtige());
			this.setFalsche(this.getFalsche()+tmpElement.getFalsche());
	}
	
	// scheibt die GlobaleStatistik in Datei
	public void writeGlobaleStatistik(String fileName)throws IOException
	{
		// Objekt zum schreiben in Datei
		PrintWriter writeFile = new PrintWriter(fileName, "UTF-8");
				
		writeFile.println("[GloableStatistik]");
		writeFile.println("richtig:"+this.getRichtige());
		writeFile.println("falsch:"	+this.getFalsche());
				
		// schließen des Write objektes.
		writeFile.close();
	}
	
	
	// liest die GlobaleStatistik ein.
	private void readGlobaleStatistik(String fileName)throws IOException
	{
		if (fileName.isEmpty())
		{
			fileName = "GlobaleStatistik.txt";
		}
		
		BufferedReader brFileReader = new BufferedReader(new FileReader(fileName));
		String tmpZeile;
		
		String firstLine = brFileReader.readLine();
		if (firstLine.equalsIgnoreCase("[GloableStatistik]"))
		{
			// geht die datei bis zur letzten zeile durch und bricht dann ab.
			while ((tmpZeile = brFileReader.readLine()) != null) 
			{
				//System.out.println(tmpZeile); // just for Debug
				String[] tmpSplitLine = tmpZeile.split(":");
							
				// wenn die Zeile Nicht nach dem vorgegeben Standart "wort1;wort2" ist, dann fügt er diese nicht hinzu.
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
		}
		// schließt den Bufferreader wieder.
		brFileReader.close();
	
	}
	
	// sollte es keine Datei für die GlobaleStatistik geben, wird eine erstellt.
	private void createEmptyFile() throws IOException
	{
		
		// Objekt zum erstellen einer Datei
		File newFile = new File ("GlobaleStatistik.txt");
		newFile.createNewFile(); // Datei erstellen.
		
		// Objekt zum schreiben in Datei
		PrintWriter writeFile = new PrintWriter("GlobaleStatistik.txt", "UTF-8");
		
		writeFile.println("[GloableStatistik]");
		writeFile.println("richtig:0");
		writeFile.println("falsch:0");
		
		// schließen des Write objektes.
		writeFile.close();
	}
}
