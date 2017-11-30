import java.io.*;

public class Dateilesen  {

	private FileReader FileReader = null;
	//private FileWriter FileWriter = null;


    public Dateilesen (String filename) throws IOException
    {
    	FileReader = new FileReader(filename);
	
    }
    
    public String readline() throws IOException
    {
    	BufferedReader read = new BufferedReader(FileReader);
    	
    	String zeile = read.readLine();
    	
    	//read.close();
    	
    	return zeile;
    }
    
}
