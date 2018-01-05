import java.util.Random;
import java.util.Scanner;
/**
 * Diese Klasse dient dazu eine Vokabel Session zu Starten.
 * 
 * 
 * @author 	Sebastian Rentsch
 * @Version 1.0
 *
 */
public class Session {

	private String sprache; 			// Die Sprache die waehrend der gesamten Session gepruefte werden soll.
	private int wiederholungen; 		// Anzahl an Vokabeln die waherend der Session gepruefte werden soll.
	private Statistik sessionStatistik; // Session Statsistik wird in diesem Objetk gespreichert.
	
	
	/**
	 * Konstruktor fuer eine Sesison.
	 * 
	 * @param tmpSprache: welche Sprache soll geprueft werden. Nuur "englisch" und "deutsch" sind moeglich. 
	 * 					  Auf eine pruefung wird verzichtet.
	 * @param tmpWiederholungen: Anzahl an Vokabeln die waherend der Session gepruefte werden soll.
	 */
	public Session(String tmpSprache, int tmpWiederholungen)
	{
		this.sprache = tmpSprache;
		this.wiederholungen = tmpWiederholungen;
		sessionStatistik = new Statistik();
	}
	
	/**
	 * Diese Funktiont ist dafuer da, um eine einzelne Vokabel abzufragen.
	 * Das Wort in der entsprechenden Sprache ( die Sprache ist pro Session gespeichert)
	 * aus dem ListenElement wird mit dem Eingegebn String der Benutzereingabe geprueft.
	 * 
	 * Zusaetzlich wird Session Statistik gezaehlt.
	 * 
	 * @param vokabelElement: Das ListElement was abgefragt werden soll.
	 * @param in: Scanner zum einlesen von Benutzereingaben.
	 * @return 1 = Vokabel richtig beantwortet, 0 = Vokabel Falsch beantworten, -1 = session abbrechen.
	 */
	private int einzelRunde(ListElement vokabelElement, Scanner in)
	{
		
		String tmpEnglisch = vokabelElement.getEnglisch();
		String tmpDeutsch  = vokabelElement.getDeutsch();
		
		System.out.println("Wie lautet die uebersetzung von:");
		
		// Ausgabe der Vokabel die beantwortet werden soll.
		if (sprache == "englisch")
		{
			System.out.println("-'"+tmpEnglisch+"'-");
		}else {
			System.out.println("-'"+tmpDeutsch+"'-");
		}
		//Benutzereingabe der uebersetzung.
		String eingabe =liesKorrektenString(in,"ungueltige eingabe");
		
		//Ausgabe der Benutzereingabe.
		//Dient der besseren vergleichbarkeit bei falscher uebersetzung.
		System.out.println("Ihre Eingabe:");
		System.out.println("-'"+eingabe+"'-");
		
		// wil lder Benutzer die Session abbrechen wird -1 return
		if (eingabe.equals("99"))
		{
			return -1;
		}

		if (sprache == "englisch") // wenn englisch abgefragt wird.
		{
			if (eingabe.equalsIgnoreCase(tmpDeutsch)) // vergleich der Benutzereingabe mit der richtigen uebersetzung
			{
				sessionStatistik.addRichtig();
				System.out.println("Eingabe Korrekt.");
				vokabelElement.plusAussortierCounter();
				return 1;
			}
			else
			{
				sessionStatistik.addFalsch();
				System.out.println("-'"+tmpDeutsch+"'- < Das waere die richtige eingabe");
				vokabelElement.nullifieAussortierCounter();
				return 0;
			}
		}else { // wenn Deutsch abgefragt wird.
			if (eingabe.equalsIgnoreCase(tmpEnglisch)) // vergleich der Benutzereingabe mit der richtigen uebersetzung
			{
				sessionStatistik.addRichtig(); 
				System.out.println("Eingabe Korrekt.");
				vokabelElement.plusAussortierCounter();
				return 1;
			}
			else
			{
				sessionStatistik.addFalsch();
				System.out.println("-'"+tmpEnglisch+"'- < Das waere die richtige eingabe");
				vokabelElement.nullifieAussortierCounter(); 
				return 0;
			}
		}
	}
	/**
	 * Diese Funtion ist die "Hauptrutine" der eigentlichen Abfrage Session.
	 * Hier befindet sich die Schleife die je nach gewaehlter wiederholungs anzahl die Vokabeln heraussucht.
	 * Die Vokabeln die geprueft werden sollen wird anhand einer Funktion ermittelt die eine Zufallszahl zurueck gibt.
	 * 
	 * Besonderheit:
	 * In der Schleife wird geprueft ob die ermittelte Vokabel ueberhaupt noch geprueft werden soll oder
	 * ob diese schon 3x hintereinander richtig beantwortet wurde. Ausserdem wird die Seesion
	 * gegegbenfalls frueher abgebrochen falls alle Vokabeln bereits richtig 3x hintereinander richtig
	 * beantworetet wurdne.
	 * 
	 * @param in: Scanner zum einlesen von Benutzereingaben.
	 * @param vokabelListe: Komplette Vokabelliste die zur verfuegung steht.
	 * @return Diese Funktion gibt dioe SessionStatistik zurueck damit sie in die Globale und Programm Statistik
	 * 		   Eingefuegt werden kann.
	 */
	public Statistik runSession (Scanner in, Liste vokabelListe)
	{
		System.out.println(">>>>>>>>>> Start der Vokabel Session <<<<<<<<<<<<<<");
		
		int countDurchlauf = 1;
		
		// Hauptschleife wodurch die gewuenschte anzahl an Vokabeln abgefraagt wird.
		while (countDurchlauf <= wiederholungen )	
		{
			System.out.println("-------Vokabel "+countDurchlauf +"/"+wiederholungen+"---'99' um Session abzubrechen.----");
			
			// diese Schleife dient dazu um Vokabeln die schon aussortiert sind zu ignoriereen.
			boolean vokabelGetestet = false; 
			while (!vokabelGetestet) 
			{
				// ermittelt zufaellig eine ID git das ListElement zurueck
				ListElement vokabelElement;
				vokabelElement = vokabelListe.getVokabel(gibZufallszahl(vokabelListe)); 
				
				if (vokabelElement.getAussortierCounter() < 3)
				{
					// Startet einzelrunde
					int ergebnis =  einzelRunde(vokabelElement,in);
					countDurchlauf++;
					vokabelGetestet = true;
					// session abbrechen.
					if (ergebnis == -1) {
						System.out.println("!!! Sie wollen die Session abbrechen.!!! ");
						countDurchlauf = wiederholungen+1;
						vokabelGetestet = true;
					}
				}
	
				// Prueft ob noch Vokabeln vorhanden sind die geprueft werden koennen.
				if (!vokabelListe.vokabelnNochVorhanden())
				{
					countDurchlauf = wiederholungen+1;
					System.out.println("-------------------------");
					System.out.println("Alle Vokabeln wurde bereits erfolgreich abgefragt.");
					vokabelGetestet = true; // true damit schleife beendet wird.
				}	
			}
			
			System.out.println("-------------------------");
		}
		// Die Statistik der aktuellen Session wird ausgegeben.
		sessionStatistik.statistikAusgeben();
		
		System.out.println(">>>>>>>>>> Ende der Vokabel Session <<<<<<<<<<<<<<");
		
		return this.sessionStatistik;
	}
	
	
	/**
	 * Diese Funktion gibt eine Zufallszahl zurueck die zwischen 1 und der maximalen anzahl 
	 * an Vokabeln in der Vokabelliste liegt. 
	 * Als Grundlage fuer die Zufallsberechnung dient die vorhanden Klasse "Random" 
	 * die von Java bereit gestesllt wird. 
	 * 
	 * 
	 * @param vokabelListe:Komplette Vokabelliste die zur verfuegung steht.
	 * @return Eine zufaelligen Int-Wert
	 */
	
	// gibt eine Zufallszahl zurueck die zwischen 1 und der maixmalen anzahl Vokabeln liegt.
	private static int gibZufallszahl(Liste vokabelListe){
		Random wuerfel = new Random();
		// 1 + damit 0 nicht gewuerfelt wird.
		int augenzahl = 1 + wuerfel.nextInt(vokabelListe.zaehlen()); 
		//System.out.println(augenzahl); // just for #debuging
		return augenzahl;
	}
	/**
	 * Diese Funktion prueft die Benutzereingabe ob ein korrekter String eingelesen wurden.
	 * Wenn nicht wird der Promt ausgegeben. und der Benutzer muss ernerut eingebne.
	 * 
	 * @param in:Scanner zum einlesen von Benutzereingaben.
	 * @param prompt: Text der bei falscher eingabe errscheinen soll.
	 * @return eingegeben String.
	 */
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
