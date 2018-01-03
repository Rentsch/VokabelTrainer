
public class ListElement {

	String englisch;	
	String deutsch;
	int id;
	int aussortierCounter;
	ListElement next;

	//-----Konstruktoren -----
	public ListElement(String tmpEnglisch, String tmpDeutsch, int tmpID)
	{
		this.englisch = tmpEnglisch;
		this.deutsch = tmpDeutsch;
		this.id = tmpID;
		this.aussortierCounter = 0;
		this.next = null; 
	}
	public ListElement()
	{		
		this.englisch = null;
		this.deutsch = null;
		this.id = 0;
		this.aussortierCounter = 0;
		this.next = null; 	
	}		
	public String getEnglisch() {
		return englisch;
	}

	public void setEnglisch(String englisch) {
		this.englisch = englisch;
	}

	public String getDeutsch() {
		return deutsch;
	}

	public void setDeutsch(String deutsch) {
		this.deutsch = deutsch;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAussortierCounter() {
		return aussortierCounter;
	}

	public void plusAussortierCounter() {
		this.aussortierCounter++;
	}
	public void nullifieAussortierCounter() {
		this.aussortierCounter = 0;
	}

	public ListElement getNext() {
		return next;
	}
	public void setNext(ListElement next) {
		this.next = next;
	}
	
	
	
}
