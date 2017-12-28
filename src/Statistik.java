
public class Statistik {

	private int falscheAntwort;
	private int richtigeAntwort;
	
	public Statistik()
	{
		this.falscheAntwort 	= 0;
		this.richtigeAntwort 	= 0;		
	}
	
	
	public void addRichtig ()
	{
		richtigeAntwort++;	
	}
	public void addFalsch ()
	{
		
		falscheAntwort++;
	}
	
	public int getFalsche()
	{
		return falscheAntwort;
	}
	
	public int getRichtige()
	{
		return richtigeAntwort;
	}
	
	public int getAnzahlAntworten()
	{
		return falscheAntwort+richtigeAntwort;
	}
	
}
