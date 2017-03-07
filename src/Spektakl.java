import java.io.Serializable;;

public class Spektakl implements Serializable,Comparable<Spektakl> {
	private int godzina_Trwania, minuta_Trwania;
	private String nazwa, scena;
	public Spektakl(int godzina, int minuta, String nazwa, String scena){
		this.godzina_Trwania=godzina;
			if(minuta<0 && minuta >59)
			{
				//error
			}
			else
			{	
				this.minuta_Trwania=minuta;
				}
		this.nazwa=nazwa;
		this.scena=scena;
	}
	
	int daj_godzine_trwania_spektaklu(){
		return godzina_Trwania;
	}
	
	int daj_minute_trwania_spektaklu(){
		return minuta_Trwania;
	}
	String daj_nazwe_spektaklu(){
		return nazwa;
	}
	String daj_scene(){
		return scena;
	}
	boolean ustaw_godzine_trwania(int h){
		if(h>0){
			godzina_Trwania = h;
			return true;
		}else{
			return false;
		}
	}
	boolean ustaw_minute_trwania(int m){
		if(m>=0 && m<=59){
			minuta_Trwania = m;
			return true;
		}
		else{
			return false;
		}
	}
	boolean ustaw_nazwe_spektaklu(String s){
		nazwa=s;
		return true;
	}
	boolean ustaw_scene(String s){
		scena=s;
		return true;
	}
	
	public String toString(){
		return daj_nazwe_spektaklu();
		//return daj_nazwe_spektaklu()+" "+daj_godzine_trwania_spektaklu()+":h "+daj_minute_trwania_spektaklu()+"min "+daj_scene();
	}

	@Override
	public int compareTo(Spektakl o) {
		return daj_nazwe_spektaklu().compareTo(o.daj_nazwe_spektaklu());
	}

}
