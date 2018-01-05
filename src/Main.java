import java.io.IOException;
import java.util.Scanner;
/**
 * Mainrutine des Vokabeltrainers. 
 * Ausserdem sind in dieser Klasse die wichtigsten Funktionen fuer 
 * die Bedienung des Vokabeltrainsere enthalten.
 * 
 * @author 	Sebastian Rentsch
 * @Version 1.0
 *
 */
public class Main {

	public static void main(String[] args) throws IOException {
		System.out.println("--- VokabelTrainer Projekt von Sebastian Rentsch ---");
		
		Scanner in; // Scanner zum einlesen der Benutzereingaben.
		in = new Scanner(System.in);
		
		Liste vokabelListe = new Liste(); // erzeugen einer neuen Liste fuer die Vokabeln
		
		Statistik globaleStatistik 	= new Statistik(true);  // neues Statistik Objekt fuer Glaoble Statistik
		Statistik programmStatistik = new Statistik();		// neues Statistik Objekt fuer Programm Statistik
		
		// Einlesen der Vokabelliste.txt
		// Exception falls Einelesen fehlschlaegt.
		try {
			vokabelListe.readFile("Vokabelliste.txt");
		}catch(IOException e)
		{
			System.out.println("---");
			System.out.println("Die Vokabelliste konnte nicht gefunden werden oder ist fehlerhaft.");
			System.out.println("Kopieren Sie die korrekte 'Vokabelliste.txt' in den Projektroder und starten sie das Programm erneut!");
			System.out.println("Das Programm wird mit 5 Beispiel vokabeln fortgesetzt.");
			_41_beispielVokabeln(vokabelListe);
		}
		
		printMainMenue(); //ausgabe des Hauptmenues
		
		boolean schleife = true;// "main schleife" fuer die benutzereingabe
		while(schleife) {
			System.out.println("=>Bitte Menueeintrag waehlen: (0 zum anzeigen des Menues)");
			switch(liesKorrektenInteger(in,"Menue Auswahl falsch, bitte neu"))
			{		
				case 0: printMainMenue(); break;
				case 11: _11_Egnlisch(in,vokabelListe,globaleStatistik,programmStatistik); break;
				case 12: _12_Deutsch(in,vokabelListe,globaleStatistik,programmStatistik); break;
				case 21: _21_showProgrammStatistik(programmStatistik); break;
				case 22: _22_showGlobalStatistik(globaleStatistik); break;
				case 41: _41_beispielVokabeln(vokabelListe); break;
				case 42: _42_alleVokabelnAusgeben(vokabelListe); break;
				case 97: _97_AboutText(); break;
				case 98: _98_helpText(); break;
				case 99: schleife = false; break;
				default: System.out.println("Ungueltiger Menueeintrag!\n");break;
			}// end switch
		}//end while
		
		//Schreibe die GloblaeStatistik in externe Datei.
		try {
			globaleStatistik.writeGlobaleStatistik("GlobaleStatistik.txt");
		}catch (IOException e) 
		{
			System.out.println("Die GlobaleStatistik konnte leider nicht gespeichert werden. Die Statistik der Programmsession geht verloren.");
		}
		
		System.out.println("-----Programm Ende-----");
	}
	/**
	 * Diese Funktion dient nur der Ausgabe des Mainmenues in der Console
	 */
	static void printMainMenue ()
	{
		System.out.println("______________Menue_____________________________");
		System.out.println("| Die Zahl des gewuenschten Menue Punkt eingeben.|");
		System.out.println("|________________________________________________|");
		System.out.println("| =>Vokabeln lernen                              |");
		System.out.println("| 11: Start Lauf Englisch->Deutsch               |");
		System.out.println("| 12: Start Lauf Deutsch->Englisch               |");
		System.out.println("|________________________________________________|");
		System.out.println("| =>Statistik                                    |");
		System.out.println("| 21: Programm Statistik Anzegien                |");
		System.out.println("| 22: Gloable Statistik Anzegien                 |");
		System.out.println("|________________________________________________|");
		System.out.println("|=> 'Amdin befehle'                              |");
		System.out.println("| 41: 8 Beispiel Werte eintragen                 |");
		System.out.println("| 42: Liste Ausgeben                             |");
		System.out.println("|________________________________________________|");
		System.out.println("| 97: About                                      |");
		System.out.println("| 98: Help                                       |");
		System.out.println("| 99: Exit                                       |");
		System.out.println("|________________________________________________|");
	}
	/**
	 * Diese Funktion erzeugt eine Session fuer das Abfragen von Englischen Vokabeln.
	 * Die SessionStatsitik wird am Ende der Session mit der Globalen und der ProgrammStatistik gemergt.
	 * 
	 * @param in:  					Scanner zum einlesen von Benutzereingaben.
	 * @param vokabelListe: 		Komplette Vokabelliste die zur verfuegung steht.
	 * @param globalStatistik		Das Objekt fuer die Globale Statistik.
	 * @param programmStatistik		Das Objekt fuer die Programm Statistik.
	 */
	static void _11_Egnlisch(Scanner in, Liste vokabelListe, Statistik globalStatistik, Statistik programmStatistik )
	{
		System.out.println("\n------");
		System.out.println("Sie haben sich fuer eine Vokabel Session Englisch->Deutsch entschieden");
		
		// Anzahl an wiederholungen ermitteln
		int wiederholungen = auswahlWiederholungen(in);
		
		// tmp element fuer Statistik-merge 
		Statistik sessionStatistik; 
		
		//Session anlegen und starten
		Session s1 =  new Session("englisch", wiederholungen);
		sessionStatistik = s1.runSession(in, vokabelListe);
		
		// fuegt die Ergebnise der Session der Globalen und Programm statistik hinzu.
		globalStatistik.mergeStatistik(sessionStatistik);
		programmStatistik.mergeStatistik(sessionStatistik);
		
		
		System.out.println("------\n");
	}
	/**
	 * Diese Funktion erzeugt eine Session fuer das Abfragen von deutschen Vokabeln.
	 * Die SessionStatsitik wird am Ende der Session mit der Globalen und der ProgrammStatistik gemergt.
	 * 
	 * @param in:  					Scanner zum einlesen von Benutzereingaben.
	 * @param vokabelListe: 		Komplette Vokabelliste die zur verfuegung steht.
	 * @param globalStatistik		Das Objekt fuer die Globale Statistik.
	 * @param programmStatistik		Das Objekt fuer die Programm Statistik.
	 */
	static void _12_Deutsch(Scanner in, Liste vokabelListe, Statistik globalStatistik, Statistik programmStatistik)
	{
		System.out.println("\n------");
		System.out.println("Sie haben sich fuer eine Vokabel Session Deutsch->Englisch entschieden");
		
		// Anzahl an wiederholungen ermitteln
		int wiederholungen = auswahlWiederholungen(in);
		
		// tmp element fuer Statistik-merge 
		Statistik sessionStatistik; 
		
		//Session anlegen und starten.
		Session s1 =  new Session("deutsch", wiederholungen);
		sessionStatistik = s1.runSession(in, vokabelListe);
		
		// fuegt die Ergebnise der Session der Globalen und Programm statistik hinzu.
		globalStatistik.mergeStatistik(sessionStatistik);
		programmStatistik.mergeStatistik(sessionStatistik);
		
		System.out.println("------\n");
	}
	/**
	 * Diese Funktion fragt den Bediener wie viele Vokabeln in der Sesssion abgefragt werden soll.
	 * 
	 * @param in: Scanner zum einlesen von Benutzereingaben.
	 * @return: Gibt die Anzahl der Session durchlaufe zurueck.
	 */
	static int auswahlWiederholungen (Scanner in) {
		
		printWiederholungMenue();
		boolean schleife = true;// "main schleife" fuer die benutzereingabe
		while(schleife) 
		{
			System.out.println("=>Bitte Menueeintrag waehlen: (0 zum anzeigen des Menues)");
			switch(liesKorrektenInteger(in,"Menue Auswahl falsch, bitte neu"))
			{		
					case 0: printWiederholungMenue(); break;
					case 1:  return 5; 	
					case 2:  return 10; 
					case 3:  return 20;
					case 4:  {System.out.println("Bitte Eingeben.");
							 return liesKorrektenInteger(in,"Falsche Freie Eintabe");
					}
					default: System.out.println("Ungueltiger Menueeintrag!\n");	break;
			}// end switch
		}
		return 0;
	}
	/**
	 * Diese Funktion dient nur der Ausgabe des Menues, fuer die eingabe der WInderholungen, in der Console
	 */
	static void printWiederholungMenue ()
	{
		System.out.println("______Auswahl Wiederholgugen_______");
		System.out.println("| 1:   5 Vokabeln                   |");
		System.out.println("| 2:  10 Vokabeln                   |");
		System.out.println("| 3:  20 Vokabeln                   |");
		System.out.println("| 4:  Freie Eingabe                 |");
		System.out.println("|__________________________________");
		
	}
	/**e
	 * Diese Funktion zeigt die Aktuelle ProgrammStatistik an.
	 * 
	 * @param s1: StatistikElement was angezeigt werden soll.
	 */
	static void _21_showProgrammStatistik(Statistik s1)
	{
		System.out.println("\n------");
		System.out.println("Die ProgrammStatistik sieht wie folgt aus:\n");
		
		System.out.println("Anzahl beantwortete Vokabeln    : "+ s1.getAnzahlAntworten());
		System.out.println("Davon wurden richtig beantwortet: "+ s1.getRichtige());
		System.out.println("Davon wurden falsch beantwortet : "+ s1.getFalsche());
		
		System.out.println("------\n");
	}
	/**
	 * Diese Funktion zeigt die GlobaleStatistik an.
	 * 
	 * @param s1: StatistikElement was angezeigt werden soll.
	 */
	static void _22_showGlobalStatistik(Statistik s1)
	{
		System.out.println("\n------");
		System.out.println("Die GlobaleStatistik sieht wie folgt aus:\n");
		
		System.out.println("Anzahl beantwortete Vokabeln    : "+ s1.getAnzahlAntworten());
		System.out.println("Davon wurden richtig beantwortet: "+ s1.getRichtige());
		System.out.println("Davon wurden falsch beantwortet : "+ s1.getFalsche());
		
		System.out.println("------\n");
	}
	
	/**
	 * Diese Funktion fuegt 5 Beispiel Vokabeln in die Vokabelliste ein.
	 * Diese Funktion wird nur aufgerufen wenn das einlesen der Vokabeln 
	 * aus der externen Datei fehlschlaegt
	 * 
	 * @param vokabelListe: Komplette Vokabelliste die zur verfuegung steht.
	 */
	static void _41_beispielVokabeln(Liste vokabelListe)
	{
		System.out.println("\n------");
		System.out.println("Beispiel Vokabeln werden in die Vokabelliste eingetragen!");
		
		vokabelListe.vokabelAdd("hello", "hallo");
		vokabelListe.vokabelAdd("sausage", "Wurst");
		vokabelListe.vokabelAdd("cheese", "Kaese");
		vokabelListe.vokabelAdd("cake", "Kuchen");
		vokabelListe.vokabelAdd("milk", "Milch");
	
		System.out.println("------\n");
	}
	/**
	 * Diese Funktion gibt alle in der VOkabelliste enthaltnen Vokabeln aus.
	 * 
	 * @param vokabelListe: Komplette Vokabelliste die zur verfuegung steht.
	 */
	static void _42_alleVokabelnAusgeben(Liste vokabelListe)
	{
		System.out.println("\n------");
		System.out.println("Sie haben '42' gewaehlt, folgend die Komplette Vokabelliste");
		
		System.out.println(vokabelListe.toString());
		
		System.out.println("------\n");
	}
	/**
	 * Diese Funktion gibt einen about Text aus.
	 */
	static void _97_AboutText()
	{
		System.out.println("\n------");
		System.out.println("Sie haben '97' gewaehlt, About Text wird angezeigt:");
		System.out.println("--> Vokabeltrainer <--");
		System.out.println("Erstellt von : Sebastian Rentsch");
		System.out.println("Klasse       : I14A");
		System.out.println("Datum        : 05.01.2018");
		System.out.println("Version      : 1.0");
		System.out.println("Der Vokabeltrainer ist fuer das Fach SoftwareEntwicklung der Technikakademie Braunschweig.");
		
		System.out.println("------\n");
	}
	/**
	 * Diese Funktion gibt einen Hile Text aus.
	 */
	static void _98_helpText()
	{
		System.out.println("\n------");
		System.out.println("Sie haben '98' gewaehlt, HilfeText:");
		System.out.println("--");
		System.out.println("Der Vokabeltrainer funktioniert ausschlieslich ueber die Console mit Tastatur eingabe.");
		System.out.println("Am Anfang des gezeigten menues steht eine Zahl diese muss ueber die Tastatur eingegeben werden,");
		System.out.println("um das gewuenschte menue auzurufen.");
		System.out.println("Das menue kann mit 0 erneut angezeigt werden.");
		System.out.println("Im hauptmenu kann das programm mit 99 beendet werden.");
		System.out.println("In einer Session kann diese ebenfalls mit 99 beendet werden.");

		System.out.println("------\n");
	}

	/**
	 * Diese Funktion prueft die Benutzereingabe ob ein korrekter Integer eingelesen wurden.
	 * Wenn nicht wird der Promt ausgegeben. und der Benutzer muss ernerut eingebne.
	 * 
	 * 
	 * @param in:Scanner zum einlesen von Benutzereingaben.
	 * @param prompt: Text der bei falscher eingabe errscheinen soll.
	 * @return eingegebener Integer.
	 */
	static int liesKorrektenInteger (Scanner in, String prompt)
	{
		int zahl;
		while(!in.hasNextInt())
		{    
			System.out.println(prompt);
			in.nextLine();
		}
		zahl = in.nextInt();
		in.nextLine(); // loescht folgende leerzeichen oder whitespaces, da dass zu Probleme fuehrte wenn man einen STring nach einem INt eingelesen hat.
		return zahl;
	}
	 /**
	 * Diese Funktion prueft die Benutzereingabe ob ein korrekter String eingelesen wurden.
	 * Wenn nicht wird der Promt ausgegeben. und der Benutzer muss ernerut eingebne.
	 * 
	 * @param in:Scanner zum einlesen von Benutzereingaben.
	 * @param prompt: Text der bei falscher eingabe errscheinen soll.
	 * @return eingegeben String.
	 */
	static String liesKorrektenString (Scanner in, String prompt)
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
