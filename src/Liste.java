
public class Liste {

	private ListElement myList = null;
	private int idCounter = 1;
	//-----Konstruktoren -----
	//----- End of  Konstruktoren -----
	
	
	//----- Funktionen -----
	
	public void vokabelAdd (String tmpEnglisch, String tmpDeutsch)
	{
		ListElement neu = new ListElement(); 
		ListElement tmp = myList;
		
		// wenn Liste nicht leer
		if (tmp != null)
		{
			// suche bis letztes Element (next = null)
			while(tmp.next != null)
			{
				tmp = tmp.next;
			}
		
			tmp.next = neu;
			neu.next = null;		

			neu.englisch = tmpEnglisch;
			neu.deutsch = tmpDeutsch;
			neu.id = this.idCounter;
			
		}else{ // wenn Liste leer ist.
			neu.next = null;
			this.myList = neu;
			
			neu.englisch = tmpEnglisch;
			neu.deutsch = tmpDeutsch;
			neu.id = this.idCounter;
		}
		
		this.idCounter++;
	}
//	public void einfuegenAmAnfang (String tmpEnglisch, String tmpDeutsch)
//	{
//		ListElement neu = new ListElement(); 
//		neu.next = this.myList;
//		this.myList = neu;
//		
//		neu.englisch = tmpEnglisch;
//		neu.deutsch = tmpDeutsch;
//	}
//	
//	public void einfuegenAmEnde (int value, String text)
//	{
//		ListElement neu = new ListElement(); 
//		ListElement tmp = myList;
//		
//		// wenn Liste nicht leer
//		if (tmp != null)
//		{
//			// suche bis letztes Element (next = null)
//			while(tmp.next != null)
//			{
//				tmp = tmp.next;
//			}
//		
//			tmp.next = neu;
//			neu.next = null;		
//
//			neu.value = value;
//			neu.text = text;
//		}else{
//			einfuegenAmAnfang(value, text);
//		}
//	}
//	
//	public void einfuegenVorElement(int suchID, int value, String text)
//	{
//		ListElement tmp = myList;
//		ListElement vortmp = null;
//		
//		// wenn Liste nicht leer
//		if (tmp != null)
//		{
//			if (tmp.value == suchID)
//			{
//				ListElement neu = new ListElement(value, text); 
//				neu.setNext(tmp); 
//				this.myList = neu;
//				return; // ELement angelegt spring aus FUnktion.
//			}
//			vortmp = tmp;
//			tmp = tmp.next;
//			// durchsucht alle Element in liste 
//			while(tmp != null)
//			{
//				if (tmp.value == suchID ) // wenn ID gefunden.
//				{
//					ListElement neu = new ListElement(value, text); 
//					neu.setNext(tmp); 
//					vortmp.setNext(neu);
//					return; // ELement angelegt spring aus FUnktion.
//				}
//				vortmp = tmp;
//				tmp = tmp.next; // next  in Liste
//			}
//		}
//		// wird aufgerufen wenn kein Element in Liste  oder ID nicht gefunden wird. 
//		einfuegenAmEnde(value, text);
//	}
//	
//	
//	public void loeschen(int value)
//	{
//		ListElement tmp = myList;
//		if (tmp == null) // abfrage ob Liste leer
//		{
//			return;
//		}
//		
//		if (tmp.getValue() == value) // abfrage ob erstes Element das Gesuchte ist
//		{
//			myList = tmp.getNext();
//			return;
//		}
//		
//		// so lange wie das nächste element nicht null
//		while (tmp.getNext() != null){
//			if (tmp.getNext().getValue() == value)
//			{
//				tmp.setNext(tmp.getNext().getNext());
//				break;
//			}
//			tmp = tmp.getNext();
//		}
//	}
//	public void allesLoeschen()
//	{
//		this.myList = null;
//	}
//	
	public ListElement getVokabel(int suchID)
	{
		ListElement tmp = myList;
			while(tmp != null)
			{
				if (tmp.id == suchID )
					//return "ID: "+tmp.id +"=>  " + tmp.getEnglisch() + "-" + tmp.getDeutsch();
					return tmp;
				tmp = tmp.next;
			}
			return tmp;
	// "ID nicht gefunden"; 
	}
	
	public int zaehlen()
	{
		ListElement tmp = myList;
		int counter = 0;
			while(tmp != null)
			{
				counter++;
				tmp = tmp.next;
			}
		return counter;
	}
	
	public String toString() {
		return "Anfang der Liste:\n" + toStringR(myList);
	}
	
	public String toStringR(ListElement vokabel) {
		if (vokabel == null)
		{
			return ("Ende der Liste");
		}else{
			return vokabel.getEnglisch() + "-" + vokabel.getDeutsch()+ "\n" + toStringR(vokabel.getNext());
		}
	}
	
	//----- End of Funktionen -----
	
}
