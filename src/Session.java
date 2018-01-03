import java.util.Random;
import java.util.Scanner;

public class Session {

	private String sprache;
	private int wiederholungen;
	private Statistik sessionStatistik;
	
	public Session(String tmpSprache, int tmpWiederholungen)
	{
		this.sprache = tmpSprache;
		this.wiederholungen = tmpWiederholungen;
		sessionStatistik = new Statistik();
	}
	
	private int einzelRunde(ListElement vokabelElement, Scanner in)
	{
		
		String tmpEnglisch = vokabelElement.getEnglisch();
		String tmpDeutsch  = vokabelElement.getDeutsch();
		
		System.out.println("Wie lautet die übersetzung von:");
		
		if (sprache == "englisch")
		{
			System.out.println("-'"+tmpEnglisch+"'-");
		}else {
			System.out.println("-'"+tmpDeutsch+"'-");
		}
		
		String eingabe =liesKorrektenString(in,"ungültige eingabe");
		
		System.out.println("Ihre Eingabe:");
		System.out.println("-'"+eingabe+"'-");
		
		if (sprache == "englisch")
		{
			if (eingabe.equalsIgnoreCase(tmpDeutsch))
			{
				sessionStatistik.addRichtig();
				System.out.println("Eingabe Korrekt.");
				vokabelElement.plusAussortierCounter();
				return 1;
			}
			else
			{
				sessionStatistik.addFalsch();
				System.out.println("-'"+tmpDeutsch+"'- < Das wäre die richtige eingabe");
				vokabelElement.nullifieAussortierCounter();
				return 0;
			}
		}else { // wenn Deutsch abgefragt wird.
			if (eingabe.equalsIgnoreCase(tmpEnglisch))
			{
				sessionStatistik.addRichtig();
				System.out.println("Eingabe Korrekt.");
				vokabelElement.plusAussortierCounter();
				return 1;
			}
			else
			{
				sessionStatistik.addFalsch();
				System.out.println("-'"+tmpEnglisch+"'- < Das wäre die richtige eingabe");
				vokabelElement.nullifieAussortierCounter();
				return 0;
			}
		}
	}
	
	public Statistik runSession (Scanner in, Liste vokabelListe)
	{
		System.out.println(">>>>>>>>>> Start der Vokabel Session <<<<<<<<<<<<<<");
		
		int countDurchlauf = 1;
		
		
		while (countDurchlauf <= wiederholungen )	
		{
			System.out.println("-------Vokabel "+countDurchlauf +"/"+wiederholungen+"-------");
			
			boolean vokabelNichtGetestet = true;
			while (vokabelNichtGetestet)
			{
				// ermittelt zufällig eine ID git das ListElement zurück
				ListElement vokabelElement = new ListElement(); 
				vokabelElement = vokabelListe.getVokabel(gibZufallszahl(vokabelListe)); 
				
				if (vokabelElement.getAussortierCounter() < 3)
				{
					// Startet einzelrunde
					einzelRunde(vokabelElement,in);
					countDurchlauf++;
					vokabelNichtGetestet = false;
				}
				
				// 
				if (!vokabelListe.vokabelnNochVorhanden())
				{
					countDurchlauf = wiederholungen+1;
					System.out.println("-------------------------");
					System.out.println("Alle Vokabeln wurde bereits erfolgreich abgefragt.");
					vokabelNichtGetestet = false;
				}	
			}
			
			System.out.println("-------------------------");
		}
		
		sessionStatistik.statistikAusgeben();
		
		System.out.println(">>>>>>>>>> Ende der Vokabel Session <<<<<<<<<<<<<<");
	
		return this.sessionStatistik;
	}
	
	
	
	
	// gibt eine Zufallszahl zurück die zwischen 1 und der maixmalen anzahl Vokabeln liegt.
	private static int gibZufallszahl(Liste vokabelListe){
		Random wuerfel = new Random();
		
		// 1 + damit 0 nicht gewürfelt wird.
		// Zaehlnen funktion gibt die maximale anzahl an Vokabelnin der liste an
		int augenzahl = 1 + wuerfel.nextInt(vokabelListe.zaehlen()); 
		//System.out.println(augenzahl); // just for #debuging
		
		return augenzahl;
	}
	
	private static String liesKorrektenString (Scanner in, String prompt)
	{
		String text;
		while(!in.hasNextLine())
		{    
			System.out.println(prompt);
			in.nextLine();
		}
		text = in.nextLine();
		return text;
	}
}
