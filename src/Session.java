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
	
	private int einzelRunde(String tmpEnglisch, String tmpDeutsch, Scanner in)
	{
			
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
			if (eingabe.equals(tmpDeutsch))
			{
				sessionStatistik.addRichtig();
				return 1;
			}
			else
			{
				sessionStatistik.addFalsch();
				System.out.println("-'"+tmpDeutsch+"'- Das wäre die richtige eingabe");
				return 0;
			}
		}else {
			if (eingabe.equals(tmpEnglisch))
			{
				sessionStatistik.addRichtig();
				return 1;
			}
			else
			{
				sessionStatistik.addFalsch();
				System.out.println("-'"+tmpEnglisch+"'- Das wäre die richtige eingabe");
				return 0;
			}
		}
	}
	
	public void startSession (Liste vokabelListe)
	{
		System.out.println(">>>>>>>>>> Start der Vokabel Session <<<<<<<<<<<<<<");
		
		int countDurchlauf = 0;
		
		
		while (countDurchlauf <= wiederholungen )	
		{
			
			
			
			countDurchlauf++;
		}
		
		
		System.out.println(">>>>>>>>>> Ende der Vokabel Session <<<<<<<<<<<<<<");
	}
	
	private static String liesKorrektenString (Scanner in, String prompt)
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
