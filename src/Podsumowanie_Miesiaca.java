import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class Podsumowanie_Miesiaca implements Serializable{
	private int miesiac, rok;
	private double ilosc_scena_duza,ilosc_scena_kameralna;
	private int tablica_z_powtorkami[]=new int[33];
	private String miesiac_slownie;
	private String oprowadzania_lub_ulotki="";
	private ArrayList<Spektakl_praca> lista;
	public Podsumowanie_Miesiaca(int miesiac, int rok){
		this.miesiac=miesiac;
		this.rok=rok;
		this.miesiac_slownie=miesiac(miesiac);
		lista=new ArrayList<Spektakl_praca>();
		this.ilosc_scena_duza=0;
		this.ilosc_scena_kameralna=0;
		
	}
	String miesiac(int a){
		switch(a){
			case 1:{return "Styczen" ;}
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
			default: {return "Blad";}
		}
	}
	int daj_miesiac(){
		return miesiac;
	}
	String daj_oprowadzania_lub_ulotki()
	{
		return oprowadzania_lub_ulotki;
	}
	boolean zamien_oprowadzania_lub_ulotki(String x)
	{
		oprowadzania_lub_ulotki=x;
		return true;
	}
	String daj_miesiac_slownie(){
		return miesiac_slownie;
	}
	int daj_rok(){
		return rok;
	}
	boolean dodaj(int dzien,int odh, int odm, int doh, int dom, int hroz, int mroz, String scena,String nazwa, boolean dyzur){
		boolean temp = lista.add(new Spektakl_praca(dzien,odh,odm,doh,dom,hroz,mroz,scena,nazwa,dyzur));
		Collections.sort(lista);
		return temp;		
	}
	void rowspan_html(){
		for(Spektakl_praca x:lista){
			tablica_z_powtorkami[x.daj_dzien()]++;
		}
	}
	void zerowanie_tablicy_pomocniczej(){
		for(int i=0;i<tablica_z_powtorkami.length;i++){
			tablica_z_powtorkami[i]=1;
		}
	}
	
	ArrayList<Spektakl_praca> daj_liste(){
		return lista;
	}
	Double daj_spedzony_czas()
	{
		double czas=0;
		for(Spektakl_praca x:lista)
		{
			czas+=x.daj_czas_pracy();
		}
		return czas;
	}
	Double daj_spedzony_czas_SD()
	{
		double czas=0;
		for(Spektakl_praca x:lista)
		{
			if(x.daj_scene().equals("SD"))
			{
				czas+=x.daj_czas_pracy();
			}
		}
		return czas;
	}
	Double daj_spedzony_czas_SK()
	{
		double czas=0;
		for(Spektakl_praca x:lista)
		{
			if(x.daj_scene().equals("SK"))
			{
				czas+=x.daj_czas_pracy();
			}
		}
		return czas;
	}
	String daj_html(String imie, String nazwisko){
		String temp="";
		zerowanie_tablicy_pomocniczej();
		rowspan_html();
		ilosc_scena_duza=0;
		ilosc_scena_kameralna=0;
		int i=0;
		temp+="<!DOCTYPE HTML>\n";
		temp+="<html>\n";
		temp+="<head>\n";
		temp+="\t <style>\n";
			temp+="\t \t table{text-align: center;border: 1px solid black;background-color: #000000;}\n";
			temp+="\t \t td{padding: 3px; border:1px solid black;}\n";
			temp+="\t \t thead{background-color: #ffffff;}\n";
			temp+="\t \t tfoot{background-color: #ffffff;}\n";
			temp+="\t \t tbody{background-color:#ffffff;}\n";
			temp+="\t \t body{background-color:#ffffff;}\n";
		temp+="\t </style>\n";
		temp+="</head>\n";
		temp+="<body>\n";
		temp+="<table align=center>\n";
			temp+="\t <thead>\n";
					temp+="\t \t \t <td colspan=8>";
					temp+=imie+" "+nazwisko+"    ";
					temp+=daj_miesiac_slownie();
					temp+=" "+daj_rok()+" \t";
					temp+="</td>\n";
				temp+="\t \t <tr>\n";
					temp+="\t \t \t <td>Dzie&#324;</td>\n";
					temp+="\t \t \t <td>Dy&#380;ur</td>\n";
					temp+="\t \t \t <td>Spektakl</td>\n";
					temp+="\t \t \t <td>Godzina spektaklu</td>\n";
					temp+="\t \t \t <td>W pracy od</td>\n";
					temp+="\t \t \t <td>W pracy do</td>\n";
					temp+="\t \t \t <td>Godzinowo</td>\n";
					temp+="\t \t \t <td>Scena</td>\n";
				temp+="\t \t </tr>\n";
			temp+="\t </thead>\n";
			temp+="\t <tbody>\n";				
			temp+="\t \t <tr>\n";
			temp+="\t \t </tr>\n";
		for(Spektakl_praca x:lista){
					if(tablica_z_powtorkami[x.daj_dzien()]>1){
						if(i==0){
						temp+="\t \t <tr>\n";
							i=tablica_z_powtorkami[x.daj_dzien()];
							temp+="\t \t \t <td rowspan="+tablica_z_powtorkami[x.daj_dzien()]+">";
							temp+=x.daj_dzien();
							temp+="\t \t \t </td>\n";
							--i;
						}
						if(i>0){
							temp+="\t \t <tr>\n";
							
							temp+="\t \t \t </td>\n";
							--i;
						}
				}else{
				temp+=" \t \t <tr>\n";	
				}
				

				temp+="\t \t \t <td>";
				temp+=x.daj_dyzur();
				temp+="\t \t \t </td>\n";
				
				temp+="\t \t \t <td>";
				temp+=x.daj_nazwe_spektaklu();
				temp+="</td>\n";
		
				temp+="\t \t \t <td>";
				temp+=x.daj_godzine_spektaklu();
				temp+="\t \t \t </td>\n";
		
				temp+="\t \t \t <td>";
				temp+=x.daj_godzine_przyjscia();
				temp+="\t \t \t </td>\n";
		
				temp+="\t \t \t <td>";
				temp+=x.daj_godzine_wyjscia();
				temp+="\t \t \t </td>\n";
		
				temp+="\t \t \t <td>";
				temp+=x.daj_czas_pracy();
				temp+="\t \t \t </td>\n";
					
				temp+="\t \t \t <td>";
				temp+=x.daj_scene();
				temp+="\t \t \t </td>\n";
					if(x.daj_scene().equals("SK")){
						ilosc_scena_kameralna+=x.daj_czas_pracy();
					}
					if(x.daj_scene().equals("SD")){
						ilosc_scena_duza+=x.daj_czas_pracy();
					}
			temp+="\t \t </tr>\n";

		}
			temp+="\t </tbody>\n";	
			temp+="\t <tfoot>\n";
				temp+="\t \t <tr>\n";
					temp+="\t \t \t <td colspan=3>";
						temp+="Scena Kameralna";
					temp+="\t \t \t </td>";
					
					temp+="\t \t \t <td colspan=2>";
						temp+="Razem";
					temp+="\t \t \t </td>";
					
					temp+="\t \t \t <td colspan=3>";
						temp+="Scena Du\u017Ca";
					temp+="\t \t \t </td>";
				temp+="\t \t </tr>\n";
				temp+="\t \t <tr>\n";
					temp+="\t \t \t <td colspan=3>";
						temp+=ilosc_scena_kameralna;
					temp+="\t \t \t </td>";
					
					temp+="\t \t \t <td colspan=2>";
						temp+=ilosc_scena_kameralna+ilosc_scena_duza;
					temp+="\t \t \t </td>";
					
					temp+="\t \t \t <td colspan=3>";
						temp+=ilosc_scena_duza;
					temp+="\t \t \t </td>";
				temp+="\t \t </tr>\n";
				
				temp+="\t \t <tr>\n";
					temp+="\t \t \t <td colspan=8>";
					
					String temp2="";
					char tab[] = oprowadzania_lub_ulotki.toCharArray();
					for(int xx=0;xx<tab.length;xx++)
					{
						if((int)tab[xx]==59 && xx!=tab.length-1)
						{
							//System.out.println("jest enter");
							temp2+="\t \t \t </td>\n \t \t </tr>\n";
							temp2+="\t \t <tr>\n \t \t \t <td colspan=8>";
						}
						else
						{
							temp2+=tab[xx];
						}
					}
					temp+=temp2;
					
					temp+="\t \t \t </td>\n";
				temp+="\t \t </tr>\n";
			temp+="\t </tfoot>\n";		
			temp+="</table>\n";
			temp+="</body>\n";
			temp+="</html>";			
		return temp;
				
	}
	public String toString(){
		/*
		ilosc_scena_duza=0;
		ilosc_scena_kameralna=0;
		String temp ="";
		temp+=miesiac_slownie+"\n";
		for(Spektakl_praca x:lista){
					temp+=x.daj_dzien()+" \t"+x.daj_dyzur()+"\t"+x.daj_nazwe_spektaklu()+"\t"+x.daj_godzine_spektaklu()+"\t"+x.daj_godzine_przyjscia()+"\t"+x.daj_godzine_wyjscia()+"\t"+x.daj_czas_pracy()+"\t"+x.daj_scene()+"\n";
					if(x.daj_scene().equals("SK")){
						ilosc_scena_kameralna+=x.daj_czas_pracy();
					}
					if(x.daj_scene().equals("SD")){
						ilosc_scena_duza+=x.daj_czas_pracy();
					}
		}
		temp+="Scena kameralna : "+ilosc_scena_kameralna+" Scena duza :"+ilosc_scena_duza+" Razem :"+(ilosc_scena_duza+ilosc_scena_kameralna);
		
		return temp;
		*/
		return ""+miesiac_slownie+" "+rok;
	}
	
}	
