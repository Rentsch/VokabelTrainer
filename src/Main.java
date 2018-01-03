import java.io.IOException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {
		System.out.println("--- VokabelTrainer Projekt von Sebastian Rentsch ---");
		
		Scanner in; // Scanner zum einlesen der Benutzereingaben.
		in = new Scanner(System.in);
		
		Liste vokabelListe = new Liste(); // erzeugen einer neuen Liste für die Vokabeln
		
		Statistik globaleStatistik 	= new Statistik(true);
		Statistik programmStatistik = new Statistik();
		
		// Einlesen der Vokabelliste.txt mit exception falls einlesen fehlschlägt
		try {
			vokabelListe.readFile("Vokabelliste.txt");
		}catch(IOException e)
		{
			System.out.println("---");
			System.out.println("Die Vokabelliste konnte nicht gefunden werden.");
			System.out.println("Kopieren Sie die 'Vokabelliste.txt' in den Projektroder und starten sie das Programm erneut!");
			System.out.println("Das Programm wird mit 5 beispiel vokabeln fortgesetzt.");
			_41_beispielVokabeln(vokabelListe);
		}
		
		printMainMenue(); //ausgabe des Hauptmenüs
		
		boolean schleife = true;// "main schleife" für die benutzereingabe
		while(schleife) {
			System.out.println("=>Bitte Menüeintrag wählen: (0 zum anzeigen des Menüs)");
			switch(liesKorrektenInteger(in,"Menü Auswahl falsch, bitte neu"))
			{		
					case 0: printMainMenue(); break;
					case 11: _11_Egnlisch(in,vokabelListe,globaleStatistik,programmStatistik); break;
					case 12: _12_Deutsch(in,vokabelListe,globaleStatistik,programmStatistik); break;
					case 21: _21_showProgrammStatistik(programmStatistik); break;
					case 22: _22_showGlobalStatistik(globaleStatistik); break;
					case 41: _41_beispielVokabeln(vokabelListe); break;
					case 42: _42_alleVokabelnAusgeben(vokabelListe); break;
					case 43: _43_sucheVokabel(in,vokabelListe); break;
					case 44: _44_testsuite(in,vokabelListe); break;
					case 98: _98_helpText(); break;
					case 99: schleife = false; break;
					default: System.out.println("Ungültiger Menüeintrag!\n");break;
			}// end switch
		}//end while
		
		globaleStatistik.writeGlobaleStatistik("GlobaleStatistik.txt");
		
		System.out.println("-----Programm Ende-----");
	}
	
	static int auswahlWiederholungen (Scanner in) {
		
		printWiederholungMenue();
		boolean schleife = true;// "main schleife" für die benutzereingabe
		while(schleife) {
			System.out.println("=>Bitte Menüeintrag wählen: (0 zum anzeigen des Menüs)");
			switch(liesKorrektenInteger(in,"Menü Auswahl falsch, bitte neu"))
			{		
					case 0: printWiederholungMenue(); break;
					case 1:  return 5; 	
					case 2:  return 10; 
					case 3:  return 20;
					case 4:  {System.out.println("Bitte Eingeben.");
							 return liesKorrektenInteger(in,"Falsche Freie Eintabe");
					}
					default: System.out.println("Ungültiger Menüeintrag!\n");	break;
			}// end switch
		}
		return 0;
	}
	
	
	
	static void _11_Egnlisch(Scanner in, Liste vokabelListe, Statistik globalStatistik, Statistik programmStatistik )
	{
		System.out.println("\n------");
		System.out.println("Sie haben sich für eine Vokabel Session Englisch->Deutsch entschieden");
		
		// Anzahl an wiederholungen ermitteln
		int wiederholungen = auswahlWiederholungen(in);
		
		// tmp element für Statistik-merge 
		Statistik sessionStatistik; 
		
		//Session anlegen und starten
		Session s1 =  new Session("englisch", wiederholungen);
		sessionStatistik = s1.runSession(in, vokabelListe);
		
		// fügt die Ergebnise der Session der Globalen und Programm statistik hinzu.
		globalStatistik.mergeStatistik(sessionStatistik);
		programmStatistik.mergeStatistik(sessionStatistik);
		
		
		System.out.println("------\n");
	}
	
	static void _12_Deutsch(Scanner in, Liste vokabelListe, Statistik globalStatistik, Statistik programmStatistik)
	{
		System.out.println("\n------");
		System.out.println("Sie haben sich für eine Vokabel Session Deutsch->Englisch entschieden");
		
		// Anzahl an wiederholungen ermitteln
		int wiederholungen = auswahlWiederholungen(in);
		
		// tmp element für Statistik-merge 
		Statistik sessionStatistik; 
		
		//Session anlegen und starten.
		Session s1 =  new Session("deutsch", wiederholungen);
		sessionStatistik = s1.runSession(in, vokabelListe);
		
		// fügt die Ergebnise der Session der Globalen und Programm statistik hinzu.
		globalStatistik.mergeStatistik(sessionStatistik);
		programmStatistik.mergeStatistik(sessionStatistik);
		
		System.out.println("------\n");
	}
	static void _21_showProgrammStatistik(Statistik s1)
	{
		System.out.println("\n------");
		System.out.println("Die ProgrammStatistik sieht wie folgt aus:\n");
		
		System.out.println("Anzahl beantwortete Vokabeln    :"+ s1.getAnzahlAntworten());
		System.out.println("Davon wurden richtig beantwortet:"+ s1.getRichtige());
		System.out.println("Davon wurden falsch beantwortet :"+ s1.getFalsche());
		
		System.out.println("------\n");
	}
	
	static void _22_showGlobalStatistik(Statistik s1)
	{
		System.out.println("\n------");
		System.out.println("Die GlobaleStatistik sieht wie folgt aus:\n");
		
		System.out.println("Anzahl beantwortete Vokabeln    :"+ s1.getAnzahlAntworten());
		System.out.println("Davon wurden richtig beantwortet:"+ s1.getRichtige());
		System.out.println("Davon wurden falsch beantwortet :"+ s1.getFalsche());
		
		System.out.println("------\n");
	}
	static void _41_beispielVokabeln(Liste vokabelListe)
	{
		System.out.println("\n------");
		System.out.println("Beispiel Vokabeln werden in die Vokabelliste eingetragen!");
		
		vokabelListe.vokabelAdd("hello", "hallo");
		vokabelListe.vokabelAdd("sausage", "Wurst");
		vokabelListe.vokabelAdd("cheese", "Käse");
		vokabelListe.vokabelAdd("cake", "Kuchen");
		vokabelListe.vokabelAdd("milk", "Milch");
	
		System.out.println("------\n");
	}
	
	static void _42_alleVokabelnAusgeben(Liste vokabelListe)
	{
		System.out.println("\n------");
		System.out.println("Sie haben '42' gewählt, folgend die Komplette Vokabelliste");
		
		System.out.println(vokabelListe.toString());
		
		System.out.println("------\n");
	}
	
	// Funktion mit der man 
	static void _43_sucheVokabel(Scanner in, Liste vokabelListe)
	{
		System.out.println("\n------");
		System.out.println("Sie haben '43' gewählt, Suche Vokabel nach ID");
		System.out.println("Nach welcher ID soll gesucht werden?");
		int value = liesKorrektenInteger(in,"ungültiger ID eingabe, nochmal eingeben");
		System.out.println("Vokabel von Liste '"+ vokabelListe.getVokabel(value) + "'");
		
		System.out.println("------\n");
	}
		

	static void _98_helpText()
	{
		System.out.println("\n------");
		System.out.println("Sie haben '98' gewählt, HilfeText:");
		
		
		System.out.println("------\n");
	}
		
	static void printMainMenue ()
	{
		System.out.println("______________Menü_____________________________");
		System.out.println("| Die Zahl des gewünschten Menü Punkt eingeben.|");
		System.out.println("|______________________________________________|");
		System.out.println("| =>Vokabeln lernen                            |");
		System.out.println("| 11: Start Lauf Englisch->Deutsch             |");
		System.out.println("| 12: Start Lauf Deutsch->Englisch             |");
		System.out.println("|______________________________________________|");
		System.out.println("| =>Statistik                                  |");
		System.out.println("| 21: Programm Statistik Anzegien              |");
		System.out.println("| 22: Gloable Statistik Anzegien               |");
		System.out.println("|______________________________________________|");
		System.out.println("|=> 'Amdin befehle'                            |");
		System.out.println("| 41: 8 Beispiel Werte eintragen               |");
		System.out.println("| 42: Liste Ausgeben                           |");
		System.out.println("| 43: Suche Vokabel nach ID                    |");
		System.out.println("|______________________________________________|");
		System.out.println("| 98: Help                                     |");
		System.out.println("| 99: Exit                                     |");
		System.out.println("|______________________________________________|");
		
	}
	static void printWiederholungMenue ()
	{
		System.out.println("______Auswahl Wiederholgugen_______");
		System.out.println("| 1:   5 Vokabeln                   |");
		System.out.println("| 2:  10 Vokabeln                   |");
		System.out.println("| 3:  20 Vokabeln                   |");
		System.out.println("| 4:  Freie Eingabe                 |");
		System.out.println("|__________________________________");
		
	}
	// liest int ein
	static int liesKorrektenInteger (Scanner in, String prompt)
	{
		int zahl;
		while(!in.hasNextInt())
		{    
			System.out.println(prompt);
			in.nextLine();
		}
		zahl = in.nextInt();
		in.nextLine(); // löscht folgende leerzeichen oder whitespaces, da dass zu Probleme führte wenn man einen STring nach einem INt eingelesen hat.
		return zahl;
	}
	// liest string ein
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
