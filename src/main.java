import java.awt.EventQueue;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

import javax.swing.JOptionPane;
public  class main{

	static Uzytkownik wczytaj_uzytkownika(){
		Odczyt_obiektu odczyt = new Odczyt_obiektu();
		Uzytkownik user=odczyt.Odczytaj("user");
		return user;
	}
	
	public static void main(String[] args) throws InterruptedException {		 
		try {
		
		

			if(wczytaj_uzytkownika()==null){
				Rejestracja rejestracja = new Rejestracja();
				while(wczytaj_uzytkownika()==null){
				//	System.out.println("x");
				
					    Thread.sleep(1000);                 //1000 milliseconds is one second.
					
				}
				
				Okno okno = new Okno();
				
			}else{
				Okno okno = new Okno();
			}
			
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,""+e.toString());
		}
		 
		
		
	}
}
