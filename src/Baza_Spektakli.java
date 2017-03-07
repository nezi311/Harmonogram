import java.io.Serializable;
import java.util.*;
public class Baza_Spektakli implements Serializable {
	private ArrayList<Spektakl> Baza;
	public Baza_Spektakli(){
		Baza = new ArrayList<Spektakl>();
	}
	boolean dodaj_spektakl(int godzina, int minuta, String nazwa, String scena){
		return Baza.add(new Spektakl(godzina,minuta,nazwa,scena));
	}
	
	ArrayList<Spektakl> daj_baze(){
		return Baza;
	}
}
