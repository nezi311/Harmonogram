import java.math.*;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
public class Spektakl_praca  implements Serializable, Comparable<Spektakl_praca>{
	private int od_godzina, od_minuta,do_godzina,do_minuta,godzina_rozpoczecia,minuta_rozpoczecia,dzien; // deklarujemy zmienne
	private double ilosc_godzin;
	private String scena,nazwa;
	private boolean dyzur;

	public Spektakl_praca(int dzien,int odh, int odm, int doh, int dom, int hroz, int mroz, String scena,String nazwa, boolean dyzur){
		this.dzien=dzien;
		
		
		// sprawdzenie czy godzina przyjscia jest prawidlowa
		if(odh>24){
			this.od_godzina=odh-24;
		}else{
			this.od_godzina=odh;
		}
		
		// sprawdzenie czy minuta przyjscia jest prawidlowa
		if((odm<0) && (odm>59)){
			
		}else{
			this.od_minuta=odm;
		}
		
		// sprawdzenie czy godzina wyjscia jest prawidlowa
		if(doh>24){
			this.do_godzina=doh-24;
		}
		else{
			this.do_godzina=doh;
		}
		
		// sprawdzenie czy minuta wyjscia jest prawidlowa
		if(dom<0)
		{
			
		}
		if(dom>59)
		{
			this.do_godzina++;
			this.do_minuta=60-dom;
		}else{
			this.do_minuta=dom;
			
		}
		
		this.godzina_rozpoczecia=hroz;
		this.minuta_rozpoczecia=mroz;
		this.scena=scena;
		this.dyzur=dyzur;
		nadaj_czas_pracy();
		this.nazwa=nazwa;
	}
	void nadaj_czas_pracy(){
		this.ilosc_godzin=oblicz_czas_pracy(zamiana_na_minuty(od_godzina, od_minuta),zamiana_na_minuty(do_godzina, do_minuta));
	}
	int zamiana_na_minuty(int h1, int m1){
		return 60*h1+m1;
	}

	double oblicz_czas_pracy(int a, int b){
		if(a>b){
			return (1440-(a-b))/60.0;
		}
		else{return (b-a)/60.0;}
		
	}
	String zamien_format_godziny(int a, int b){
		Time date = new Time(a,b,00);
		DateFormat format = new SimpleDateFormat("HH:mm");
		
		return ""+format.format(date);
	}
	
	int daj_do_h(){
		return do_godzina;
	}
	int daj_do_m(){
		return do_minuta;
	}
	int daj_h_rozpoczecia(){
		return godzina_rozpoczecia;
	}
	int daj_m_rozpoczecia(){
		return minuta_rozpoczecia;
	}
	String daj_godzine_spektaklu(){

		return zamien_format_godziny(godzina_rozpoczecia, minuta_rozpoczecia);
	}
	String daj_godzine_przyjscia(){
		 return zamien_format_godziny(od_godzina,od_minuta);
	}
	String daj_godzine_wyjscia(){
		 return zamien_format_godziny(do_godzina,do_minuta);
	}
	double daj_czas_pracy(){
		return ilosc_godzin;
	}
	String daj_nazwe_spektaklu(){
		return nazwa;
	}
	String daj_scene(){
		return scena;
	}
	String daj_dyzur(){
		if(dyzur){
			return "D";
		}
		return "";
	}
	int daj_dzien(){
		return dzien;
	}
	
	
	boolean zmien_godzine_przyjscia(int h, int m){
		if(h>=0 && m>=0 && m<60){
			this.od_godzina=h;
			this.od_minuta=m;
			nadaj_czas_pracy();
			return true;
		}
		return false;
	}
	boolean zmien_godzine_wyjscia(int h, int m){
		if(h>=0 && m>=0 && m<60){
			this.do_godzina=h;
			this.do_minuta=m;
			nadaj_czas_pracy();
			return true;
		}
		return false;
	}
	boolean zmien_godzine_rozpoczecia(int h, int m){
		if(h>=0 && m>=0 && m<60){
			this.godzina_rozpoczecia=h;
			this.minuta_rozpoczecia=m;
			nadaj_czas_pracy();
			return true;
		}
		return false;
	}
	boolean zmien_dzien(int d){
		if(d>0&&d<32){
			this.dzien=d;
			return true;
		}
		return false;
	}
	boolean zmien_scene(String s){
		this.scena=s;
		return true;
	}
	boolean zmien_nazwe_spektaklu(String s){
		this.nazwa=s;
		return true;
	}
	boolean zmien_dyzur(boolean flaga){
		this.dyzur=flaga;
		return true;
	}
	
	
	public String toString(){
		return nazwa+" "+daj_godzine_spektaklu()+" "+daj_godzine_przyjscia()+" "+daj_godzine_wyjscia()+" "+daj_czas_pracy()+" "+daj_dyzur()+" "+daj_scene();
	}
	
	@Override
	public int compareTo(Spektakl_praca o) {
		
		if(daj_dzien()==o.daj_dzien())
		{
			if(daj_h_rozpoczecia()==o.daj_h_rozpoczecia())
			{
				if(daj_m_rozpoczecia()==o.daj_m_rozpoczecia())
					return 0;
				
				if(daj_m_rozpoczecia()>o.daj_m_rozpoczecia())
					return 1;
				
				else
					return -1;
			}
			if(daj_h_rozpoczecia()>o.daj_h_rozpoczecia())
				return 1;
			else
				return -1;
		}
		if(daj_dzien()>o.daj_dzien())
			return 1;
		else
			return -1;
	}
	
}
