import java.io.IOException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {
		System.out.println("--- VokabelTrainer Project von Sebastian Rentsch ---");
		
		
		Scanner in; // Scanner zum einlesen der Benutzereingaben.
		in = new Scanner(System.in);
		
		Liste vokabelListe = new Liste(); // erzeugen einer neuen Liste für die Vokabeln
		
		printMenue(); //ausgabe des Hauptmenüs
		_41_beispielVokabeln(vokabelListe); // TODO Delete this just for debug
		boolean schleife = true;// "main schleife" für die benutzereingabe
		while(schleife) {
			System.out.println("=>Bitte Menüeintrag wählen: (0 zum anzeigen des Menüs)");
			switch(liesKorrektenInteger(in,"Menü Auswahl falsch, bitte neu"))
			{		
					case 0: printMenue(); break;
					case 11:  _11_Egnlisch(in,vokabelListe); break;
//					case 12:  _12_EndeEinfuegen(in,L1); break;
//					case 13:  _13_EinfuegenVorElement(in,L1); break;
//					case 14:  _14_EinfuegenNachElement(in,L1); break;
//					case 15:  _15_Einsortieren(in,L1); break;
//					case 25:  _25_Loeschen(in,L1); break;
//					case 29:  _29_allesLoeschen(in,L1); break;
//					case 31: _31_AnzahlElement(in,L1); break;
//					case 32: _32_GetText(in,L1); break;
					case 41: _41_beispielVokabeln(vokabelListe); break;
					case 42: _42_alleVokabelnAusgeben(vokabelListe); break;
					case 43: _43_sucheVokabel(in,vokabelListe); break;
					case 99: schleife = false; break;
					default: System.out.println("Ungültiger Menüeintrag!\n1");break;
			}// end switch
		}//end while
		
		System.out.println("-----Programm Ende-----");
	}
	
	static void _11_Egnlisch(Scanner in, Liste vokabelListe)
	{
		System.out.println("\n------");
		System.out.println("Sie haben ");
		
		ListElement tmp = vokabelListe.getVokabel(2);
		Session s1 =  new Session(); ;
		
		
		System.out.println(s1.startSession(,));
		
		System.out.println("------\n");
	}
				
	static void _41_beispielVokabeln(Liste vokabelListe)
	{
		System.out.println("\n------");
		System.out.println("Sie haben '41' gewählt, Beispiel Vokabeln werden in die Vokabelliste eingetragen");
		
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
	
	static void _43_sucheVokabel(Scanner in, Liste vokabelListe)
	{
		System.out.println("\n------");
		System.out.println("Sie haben '43' gewählt, Suche Vokabel nach ID");
		System.out.println("Nach welcher ID soll gesucht werden?");
		int value = liesKorrektenInteger(in,"ungültiger ID eingabe, nochmal eingeben");
		System.out.println("Vokabel von Liste '"+ vokabelListe.getVokabel(value) + "'");
		
		System.out.println("------\n");
	}
	
	static void printMenue ()
	{
		System.out.println("______________Menü_________________");
		System.out.println("| =>Vokabeln lernen               	|");
		System.out.println("| 11: Start Lauf Englisch->Deutsch	|");
		System.out.println("| 12: Start Lauf Deutsch->Englisch  |");
		System.out.println("| 13: vor Element                 	|");
		System.out.println("|__________________________________");
		System.out.println("| =>Statistik");
		System.out.println("| 29: Statistik anzeigen	       	|");
		System.out.println("|__________________________________");
		System.out.println("|=> 'Amdin befehle'	              	|");
		System.out.println("| 41: 8 Beispiel Werte eintragen  	|");
		System.out.println("| 42: Liste Ausgeben              	|");
		System.out.println("| 43: Suche Vokabel nach ID        	|");
		System.out.println("|__________________________________");
		System.out.println("| 99: Exit                        	|");
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
		return zahl;
	}
	// liest string ein
	static String liesKorrektenString (Scanner in, String prompt)
	{
		String text;
		in.nextLine();
		while(!in.hasNextLine())
		{    
			System.out.println(prompt);
			in.nextLine();
		}
		text = in.nextLine();
		return text;
	}
	
}
