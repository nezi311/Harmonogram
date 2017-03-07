
import java.awt.GridLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.FileNotFoundException;
import java.io.IOException;


import java.lang.Object;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Color;




public class  Okno extends JFrame implements ActionListener 
{
	// tworzymy panele glowne
	//kolory
	private Color ColorPrzyciskow;
	//przyciski
	private JButton bWyjscie, bBazaSpektakli, bListy;
	//panele
	private JPanel Menu,Lista;
	private JScrollPane ListaPane;
	//Menu rozwijane
	private MenuOkna menu_okna = new MenuOkna();
	//przyciski
	private JButton bMiesiacWroc;
	//uzytkownik
	private Uzytkownik user;

	
		//zmienne potrzebne do tabeli baza spektakli

		private Object[][] dane;
		//zmienne potrzebne do tabeli spektakle praca

	
	private JFrame JFrOknoMiesiaca;
	
	
	
	public static boolean isNumeric(String str)  
	{  
	  try  
	  {  
	    double d = Double.parseDouble(str);  
	  }  
	  catch(NumberFormatException nfe)  
	  {  
	    return false;  
	  }  
	  return true;  
	}
	
	void przykladowe_dane()
	{
		try
		{
			user.dodaj_spektakl(3, 30,"Szalone no¿yczki","SD");
			user.dodaj_spektakl(3, 30,"Mayday","SD");
			user.dodaj_spektakl(4, 00,"Wojna polsko-ruska","SD");
			user.dodaj_spektakl(3, 00,"Klêski w dziejach miasta","SK");
			user.dodaj_spektakl(2, 30,"Niebosk³on","SD");
			user.dodaj_spektakl(2, 30,"Tadek Niejadek","SK");
			user.dodaj_spektakl(2, 30,"Pszczó³ka Majka","SK");
			user.dodaj_spektakl(2, 30,"Czarodziejskie sztuczki","SK");
			user.dodaj_spektakl(2, 30,"Diabe³ek Pawe³ek","SD");
			user.dodaj_spektakl(4, 00,"Plotka","SD");
			user.dodaj_spektakl(4, 30,"Fantazy","SD");
			user.dodaj_spektakl(2, 30,"Abonament na szczêœcie","SK");
			user.dodaj_spektakl(3, 30,"B³êkitny ptak","SD");
			user.dodaj_spektakl(3, 30,"Kupiec wenecki","SD");
			user.dodaj_spektakl(4, 00,"Prze³amuj¹c fale","SK");
			user.dodaj_spektakl(3, 30,"Rewizor","SD");
			user.dodaj_spektakl(3, 30,"Versus","SK");
			user.dodaj_spektakl(3, 00,"Miasto pustych pianin","SD");
			user.dodaj_spektakl(3, 30,"¯o³nierz Królowej Madagaskaru","SK");
			user.dodaj_spektakl(3, 30,"Œw. Joanna szlachtuzów","SD");
			user.dodaj_spektakl(2, 30,"Siostra i cieñ","SK");
			user.dodaj_spektakl(3,0,"K.albo...","SK");
			user.dodaj_spektakl(3, 30,"Tragedia Coriolanusa","SD");
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null,""+e.toString());
		}


	}


	


void lista()
	{
		try
		{
			Lista = new JPanel();
			Lista.setVisible(false);
			//Lista.setBorder(BorderFactory.createLineBorder(Color.RED));
			
			Lista.setLayout(new GridLayout(0,1));
			ListaPane = new JScrollPane(Lista);
			
			setLayout(new BorderLayout());
			add(ListaPane, BorderLayout.CENTER);
			setSize(200,400);
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null,""+e.toString());
		}

	
 
		 
	}
	
	
	
	
	void lista_menu()
	{
		try
		{
	        setJMenuBar(menu_okna);
	        //menu_okna.zakoncz.addActionListener(this);
	        menu_okna.Statystyki.addActionListener(this);
	        menu_okna.Wypelnij_Baze.addActionListener(this);
	        menu_okna.autor.addActionListener(this);
	        menu_okna.Zmien_kolor_przyciskow.addActionListener(this);
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null,""+e.toString());
		}

        
	}
	
	
	void menu()
	{
		try
		{
			//Dodanie panelu
	        Menu = new JPanel();
	        
	       // getContentPane().setLayout(new GridLayout(0, 1));
	       // Menu.setBounds(0, 0, 200, 400);
	       // Menu.setOpaque(true);
	        add(Menu);

	        // Osadzenie przyciskowD
	        bListy = new JButton("Listy");
	        bListy.setBackground(ColorPrzyciskow);
	        bListy.addActionListener(this);
	       // bListy.setBackground(Color.pink);
	       
	        Menu.add(bListy);
	        
	        
	        bBazaSpektakli = new JButton("<html><center>Baza Spektakli</center></html>");
	        bBazaSpektakli.setBackground(ColorPrzyciskow);
	        bBazaSpektakli.addActionListener(this);
	        Menu.add(bBazaSpektakli);
	        //bBazaSpektakli.setBackground(Color.pink);
	        
	        bWyjscie = new JButton("Wyjscie");
	        bWyjscie.setBackground(ColorPrzyciskow);
	        bWyjscie.addActionListener(this);
	        Menu.setLayout(new GridLayout(0, 1));
	        Menu.add(bWyjscie);
	        //bWyjscie.setBackground(Color.pink);
	        
	        Menu.setVisible(false);	
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null,""+e.toString());
		}
		 
	}
	
	
	public Okno() throws FileNotFoundException, ClassNotFoundException, IOException
	{
		super("Teatr");
		try
		{
			//wywolanie konstruktora klasy nadrzednej (JFrame)
			
			Odczyt_obiektu odczyt = new Odczyt_obiektu(); 
			this.user=odczyt.Odczytaj("user"); // wczytanie pliku z danymi uzytkownika
			//ustawienie standardowej akcji po nacisnieciu przycisku zamkniecia
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        //blokada zmiany rozmiaru okna
	        setResizable (false);
	        //setLayout(new FlowLayout(FlowLayout.CENTER, 2, 2));
	        //wyswietlenie naszej ramki
	        setVisible(true);
	        setSize(200, 380);
	        setLayout(new GridLayout(0,1));
	        //wysrodkowanie ramki
	        setLocationRelativeTo(null);  
	        

	    	//ColorPrzyciskow=new Color(255,0,0);
	        
	        // Dodawanie elementow i paneli
	        	menu();
	        	lista();
	        	lista_menu();
	        	
	        	Menu.setVisible(true);
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null,""+e.toString());
		}
	
	}

	

	
	
	
	
	@Override
	public void actionPerformed(ActionEvent element) 
	{
		Object zrodlo = element.getSource();
		
		
		if(zrodlo==bWyjscie)
		{
			System.exit(0);
		}


		if(zrodlo==bListy)
		{
			try
			{
				Okno_Listy oknoListy = new Okno_Listy(dane, user);
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(null,""+e.toString());
			}
			
		}



		if(zrodlo==bBazaSpektakli)
		{
			try
			{
				Okno_Bazy oknobazy = new Okno_Bazy(dane, user);
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(null,""+e.toString());
			}
		}

		if(zrodlo == bMiesiacWroc)
		{
			JFrOknoMiesiaca.dispose();
		}

		

		if(zrodlo == menu_okna.autor)
		{
			try{
				Autor xyx = new Autor();
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(null,""+e.toString());
			}
		
		}
		
		if(zrodlo == menu_okna.Wypelnij_Baze)
		{
			try
			{
				int yes = JOptionPane.showConfirmDialog(null,"Czy na pewno chcesz wype\u0142ni\u0107 baze?");
				if(yes == JOptionPane.YES_OPTION)
				{
					przykladowe_dane();
					ZapisDoPliku zapis = new ZapisDoPliku();
					zapis.zapisz(user, "user", "dat");
					JOptionPane.showMessageDialog(null,"Wype\u0142niono baze spektakli");
				}
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(null,""+e.toString());
			}

		}
		
		if(zrodlo == menu_okna.Statystyki)
		{
			try
			{
				Statystyki staty = new Statystyki(user);
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(null,""+e.toString());
			}
			
		}
		
		
		
	}
}
