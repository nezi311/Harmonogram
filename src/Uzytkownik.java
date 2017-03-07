import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
public class Uzytkownik implements Serializable {
	private String imie,nazwisko;
	private ArrayList<Spektakl> baza;
	private ArrayList<Podsumowanie_Miesiaca> lista;
	public Uzytkownik(String imie, String nazwisko){
		this.imie=imie;
		this.nazwisko=nazwisko;
		baza=new ArrayList<Spektakl>();
		lista=new ArrayList<Podsumowanie_Miesiaca>();
	}
	public Uzytkownik(Uzytkownik u){
		this.imie=u.dajImie();
		this.nazwisko=u.dajNazwisko();
		baza=u.dajBaze();
		lista=u.dajPodsumowanie();
	}
	ArrayList<Podsumowanie_Miesiaca> dajPodsumowanie(){
		return lista;
	}
	ArrayList<Spektakl> dajBaze(){
		return baza;
	}
	
	String dajNazwisko(){
		return nazwisko;
	}
	String dajImie(){
		return imie;
	}

	int MscToInt(String m){
		switch(m){
		case "Styczen":{return 1;}
		case "Luty":{return 2;}
		case "Marzec":{return 3;}
		case "Kwiecien":{return 4;}
		case "Maj":{return 5;}
		case "Czerwiec":{return 6;}
		case "Lipiec":{return 7;}
		case "Sierpien":{return 8;}
		case "Wrzesien":{return 9;}
		case "Pazdziernik":{return 10;}
		case "Listopad":{return 11;}
		case "Grudzien":{return 12;}
		}
		return 0;
	}
	String IntToMsc(int m){
		switch(m){
		case 1:{return "Styczen";}
		case 2:{return "Luty";}
		case 3:{return "Marzec";}
		case 4:{return "Kwiecien";}
		case 5:{return "Maj";}
		case 6:{return "Czerwiec";}
		case 7:{return "Lipiec";}
		case 8:{return "Sierpien";}
		case 9:{return "Wrzesien";}
		case 10:{return "Pazdziernik";}
		case 11:{return "Listopad";}
		case 12:{return "Grudzien";}
		}
		return null;
	}
	boolean dodaj_podsumowanie(int m, int r){
		
		boolean temp = lista.add(new Podsumowanie_Miesiaca(m, r));
		return temp;
	}
	boolean dodaj_spektakl(int h, int m, String n, String s){
		boolean temp =baza.add(new Spektakl(h, m, n, s));
		
		// sortowanie spektakli
		Collections.sort(dajBaze());
		
		return temp;
	}
	
	boolean spektakl_praca(int miesiac, int rok,int dzien,int odh, int odm, int doh, int dom, int hroz, int mroz, String scena,String nazwa, boolean dyzur)
	{
		for(Podsumowanie_Miesiaca x:lista)
		{
			if(x.daj_rok()==rok && x.daj_miesiac()==miesiac){
				boolean temp = x.dodaj(dzien, odh, odm, doh, dom, hroz, mroz, scena, nazwa, dyzur);	
				
				return temp;
			}
			
		}
		return false;
	}
	
	int get_spektakl_praca(int miesiac, int rok)
	{
		for(int i=0;i<lista.size();i++)
		{
			Podsumowanie_Miesiaca x=lista.get(i);
			if(x.daj_rok()==rok && x.daj_miesiac()==miesiac)
			{
				return i;
			}
			
		}
		return 0;
	}
	
	public String toString(){
		return ""+imie+" "+nazwisko+", "+dajPodsumowanie()+", "+dajBaze();
	}

}
