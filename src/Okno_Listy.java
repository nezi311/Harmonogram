import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class Okno_Listy implements ActionListener  {
	// Dane Uzytkownika
	private Uzytkownik user;
	// Dane do tabel
	private Object[][] dane;
	// Okno listy miesiecy
	private JFrame JFrOknoListy;
	private JPanel Lista;
	private JScrollPane ListaPane;
	private JButton bDodaj_Miesiac,bWroc,bPlus,bMinus,bDodaj_Miesiac_Okno,bMiesiacUsunMiesiac;
	private JComboBox<String> combMiesiace;
	private JButton []tablica_przyciskow;
	private boolean zmiana=false;
	private int zmienna_indexowa_i;
	private JLabel txtRok;
	
	@SuppressWarnings("deprecation")
	private int rok=1900+new Date().getYear();
	
	public Okno_Listy(Object[][] dane, Uzytkownik user) 
	{
		this.user = user;
		this.dane = dane;
		
		wczytaj_elementy_okna();
	}
	
	void Dodawanie_Miesiaca_Do_Listy()
	{
		try
		{
			JFrOknoListy = new JFrame("Dodawanie Miesi\u0105ca");
	        //blokada zmiany rozmiaru okna
			JFrOknoListy.setResizable (false);
	        //setLayout(new FlowLayout(FlowLayout.CENTER, 2, 2));
	        //wyswietlenie naszej ramki
			JFrOknoListy.setVisible(true);
			JFrOknoListy.setSize(405, 120);
			JFrOknoListy.setLayout(null);
	        //wysrodkowanie ramki
			JFrOknoListy.setLocationRelativeTo(null);  
			//dodawanie miesiecy 
			combMiesiace = new JComboBox<>();
			combMiesiace.addItem("Stycze\u0144");
			combMiesiace.addItem("Luty");
			combMiesiace.addItem("Marzec");
			combMiesiace.addItem("Kwiecie\u0144");
			combMiesiace.addItem("Maj");
			combMiesiace.addItem("Czerwiec");
			combMiesiace.addItem("Lipiec");
			combMiesiace.addItem("Sierpie\u0144");
			combMiesiace.addItem("Wrzesie\u0144");
			combMiesiace.addItem("Pa\u017Adziernik");
			combMiesiace.addItem("Listopad");
			combMiesiace.addItem("Grudzie\u0144");
			combMiesiace.addActionListener(this);
			combMiesiace.setBounds(5, 25, 200, 25);
			((JLabel)combMiesiace.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
			JFrOknoListy.add(combMiesiace);
			
			
			txtRok = new JLabel("",SwingConstants.CENTER);
			txtRok.setBounds(205, 25, 90, 25);
			txtRok.setText(""+rok);
			txtRok.setBorder(BorderFactory.createLineBorder(Color.black, 1));
			JFrOknoListy.add(txtRok);
			
			bPlus = new JButton("+");
			bPlus.setBounds(295, 25, 50, 25);
			bPlus.addActionListener(this);
			JFrOknoListy.add(bPlus);
			
			bMinus = new JButton("-");
			bMinus.setBounds(345, 25, 50, 25);
			bMinus.addActionListener(this);
			JFrOknoListy.add(bMinus);
			
			
			
			bDodaj_Miesiac_Okno = new JButton("Dodaj");
			bDodaj_Miesiac_Okno.setBounds(5,50,390,25);
			bDodaj_Miesiac_Okno.addActionListener(this);
			zmiana=true;
			
			JFrOknoListy.add(bDodaj_Miesiac_Okno);
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null,""+e.toString());
		}
		
		
	}
	
	void dodanie_buttonow_miesiecy()
	{
		try
		{
			int a=100; // obecna wysokosc calej listy z buttonami
			tablica_przyciskow=new JButton[user.dajPodsumowanie().size()];
			ArrayList<Podsumowanie_Miesiaca> temp =user.dajPodsumowanie();
			
			for(int i=user.dajPodsumowanie().size()-1;i>=0;i--){
				
				tablica_przyciskow[i]=new JButton(""+temp.get(i).toString()+"");
				tablica_przyciskow[i].setBounds(0, 50, 200, 50);
				tablica_przyciskow[i].addActionListener(this);
				Lista.add(tablica_przyciskow[i]);
				a+=50; // zwiekszamy wysokosc listy o 50 co button
			}
			Lista.setPreferredSize(new Dimension(150,a)); // nadaje nowa wysokosc dla listy
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null,""+e.toString());
		}
	}
	
	void wczytaj_elementy_okna() 
	{
		try
		{
			JFrOknoListy = new JFrame("Okno listy");
			JFrOknoListy.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	        //blokada zmiany rozmiaru okna
			JFrOknoListy.setResizable (false);
	        //setLayout(new FlowLayout(FlowLayout.CENTER, 2, 2));
	        //wyswietlenie naszej ramki
			JFrOknoListy.setVisible(true);
			JFrOknoListy.setSize(200, 380);
			JFrOknoListy.setLayout(new GridLayout(0,1));
	        //wysrodkowanie ramki
			JFrOknoListy.setLocationRelativeTo(null);  
			
			Lista = new JPanel();
			//Lista.setBorder(BorderFactory.createLineBorder(Color.RED));
			
			Lista.setLayout(new GridLayout(0,1));
			ListaPane = new JScrollPane(Lista);
			
			JFrOknoListy.setLayout(new BorderLayout());
			JFrOknoListy.add(ListaPane, BorderLayout.CENTER);
			JFrOknoListy.setSize(400,600);
			//setVisible(false);
			
			
			bDodaj_Miesiac = new JButton("Dodaj Miesi\u0105c");
			//bDodaj_Miesiac.setBounds(0, 0, 200, 50);
			bDodaj_Miesiac.addActionListener(this);
			Lista.add(bDodaj_Miesiac);
			
			bMiesiacUsunMiesiac = new JButton("Usu\u0144 Miesi\u0105c");
			bMiesiacUsunMiesiac.setBounds(0, 150, 150, 50);
			bMiesiacUsunMiesiac.addActionListener(this);
			Lista.add(bMiesiacUsunMiesiac);
			
			bWroc = new JButton("Wr\u00F3\u0107");
			//bWroc.setBounds(0,50,200,50);
			bWroc.addActionListener(this);
			Lista.add(bWroc);
			
			dodanie_buttonow_miesiecy();
			
			
			Lista.setVisible(true);
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null,""+e.toString());
		}
	}

	void wyczysc_buttony_miesiecy()
	{
			try
			{
				for(int i=0;i<user.dajPodsumowanie().size();i++)
				{
					Lista.remove(tablica_przyciskow[i]);
				}
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(null,""+e.toString());
			}
	}
	
	void buttony_miesiace()
	{
		try
		{
			if(zmiana)
			{
				wyczysc_buttony_miesiecy(); // czyszczenie tablicy przed wyswietleniem nowej tablicy buttonow
			}
			
			dodanie_buttonow_miesiecy();	// wyswietlenie tablicy buttonow
			zmiana = true;
			//pierwsze_logowanie_lub_zmiana=false;
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null,""+e.toString());
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public void actionPerformed(ActionEvent element) {
		// TODO Auto-generated method stub
		
		Object zrodlo = element.getSource();
		
		if(zrodlo==bDodaj_Miesiac)
		{
			try
			{
				Dodawanie_Miesiaca_Do_Listy();
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(null,""+e.toString());
			}
			
				
		}
		
		if(zrodlo==bDodaj_Miesiac_Okno)
		{
			try
			{
				// procedura przed wprowadzeniem zmian do listy wyswietlanych miesiecy
				wyczysc_buttony_miesiecy(); // czyszczenie tablicy buttonow przed dodaniem nowego buttona
				zmiana=false;
				//JFrOknoListy.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				
			//	System.out.println(user.toString());
				user.dodaj_podsumowanie(combMiesiace.getSelectedIndex()+1, rok);
				//---- Dodawanie buttonow z listy
				//dodanie_buttonow_miesiecy();
				//zmiana = true;
				//-- koniec dodawania
					ZapisDoPliku zapis = new ZapisDoPliku();
					zapis.zapisz(user, "user", "dat");
					//System.out.println(user.toString());
					//zmiana=true;
					JOptionPane.showMessageDialog(null,"Dodano: "+user.IntToMsc((combMiesiace.getSelectedIndex()+1))+" "+rok);
					buttony_miesiace();
					Lista.setVisible(false);
					Lista.setVisible(true);
					JFrOknoListy.dispose();
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(null,""+e.toString());
			}

			
		}
		
		if(zrodlo==bWroc)
		{
			JFrOknoListy.dispose();
		}
		
		if(zrodlo==bMinus)
		{
			try
			{
				rok-=1;
				txtRok.setText(""+rok);
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(null,""+e.toString());
			}

		}
		
		if(zrodlo==bPlus)
		{
			try
			{
				rok+=1;
				txtRok.setText(""+rok);
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(null,""+e.toString());
			}
		}
		

		
		for(int i=0;i<tablica_przyciskow.length;i++)
		{
			try
			{
				if(tablica_przyciskow[i]==zrodlo)
				{
					// utworzenie obiektu
					Okno_Miesiecy oknoMiesiecy = new Okno_Miesiecy(user, i);
				}
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(null,""+e.toString());
			}

		}
		
		
		if(zrodlo == bMiesiacUsunMiesiac)
		{
			try
			{
				final JFrame UsunMiesiac = new JFrame("Usu\u0144 miesi\u0105c");
				UsunMiesiac.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		        //blokada zmiany rozmiaru okna
				UsunMiesiac.setResizable (false);
		        //setLayout(new FlowLayout(FlowLayout.CENTER, 2, 2));
		        //wyswietlenie naszej ramki
				UsunMiesiac.setVisible(true);
				UsunMiesiac.setSize(300, 200);
				UsunMiesiac.setLayout(null);
		        //wysrodkowanie ramki
				UsunMiesiac.setLocationRelativeTo(null);  
				
				final JComboBox<String> comboUsunMiesiac = new JComboBox<String>();
				comboUsunMiesiac.setBounds(0,0,295,80);
				((JLabel)comboUsunMiesiac.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
				UsunMiesiac.getContentPane().add(comboUsunMiesiac);
				
				for(Podsumowanie_Miesiaca x : user.dajPodsumowanie())
				{
					comboUsunMiesiac.addItem(x.toString());
				}
				
				JButton usunMiesiac = new JButton("Usu\u0144 miesi\u0105c");
				usunMiesiac.setBounds(0,80,295,95);
				UsunMiesiac.getContentPane().add(usunMiesiac);
				
				usunMiesiac.addActionListener( new ActionListener()
				{
				    public void actionPerformed(ActionEvent e)
				    {
				    	int temp =JOptionPane.showConfirmDialog(null,"Na pewno chcesz usun\u0105\u0107	miesi\u0105c?");
						if(temp == JOptionPane.YES_OPTION){
							wyczysc_buttony_miesiecy();
							zmiana=true;
							for(int i=0;i<user.dajPodsumowanie().size();i++)
							{
								Podsumowanie_Miesiaca x = user.dajPodsumowanie().get(i);
								if(x.toString().equals((String)comboUsunMiesiac.getSelectedItem()))
								{
									user.dajPodsumowanie().remove(i);
								}
							}
							ZapisDoPliku zapis = new ZapisDoPliku();
							zapis.zapisz(user, "user", "dat");
							Lista.setVisible(false);
							Lista.setVisible(true);
							dodanie_buttonow_miesiecy();//buttony_miesiace();
							JFrOknoListy.dispose();
							UsunMiesiac.dispose();
						}
				    }
				});
	
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(null,""+e.toString());
			}
		}

		
		
	}
}
