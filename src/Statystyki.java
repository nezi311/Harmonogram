import java.awt.EventQueue;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Statystyki extends JFrame {


	Uzytkownik user;
	public Statystyki(Uzytkownik user) {
		super("Statystyki ");
		setResizable(false);
		//ustawienie standardowej akcji po nacisnieciu przycisku zamkniecia
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //setLayout(new FlowLayout(FlowLayout.CENTER, 2, 2));
        //wyswietlenie naszej ramki
        
        setSize(300,222);
        getContentPane().setLayout(null);
        
        JLabel lblImie = new JLabel("Imie :");
        lblImie.setBounds(10, 11, 78, 14);
        getContentPane().add(lblImie);
        
        JLabel lblNazwisko = new JLabel("Nazwisko :");
        lblNazwisko.setBounds(10, 36, 78, 14);
        getContentPane().add(lblNazwisko);
        
        JLabel lblNewLabel = new JLabel("New label");
        lblNewLabel.setBounds(105, 11, 119, 14);
        getContentPane().add(lblNewLabel);
        lblNewLabel.setText(user.dajImie());
        
        JLabel lblNewLabel_1 = new JLabel("New label");
        lblNewLabel_1.setBounds(105, 36, 119, 14);
        getContentPane().add(lblNewLabel_1);
        lblNewLabel_1.setText(user.dajNazwisko());
        
        JLabel lblCzasSpedzonyW = new JLabel("Czas spedzony w teatrze :");
        lblCzasSpedzonyW.setBounds(10, 82, 162, 14);
        getContentPane().add(lblCzasSpedzonyW);
        
        JLabel lblNewLabel_2 = new JLabel("New label");
        lblNewLabel_2.setBounds(182, 82, 56, 14);
        getContentPane().add(lblNewLabel_2);
        
        
        setLocationRelativeTo(null);  
        setVisible(true);
        
        this.user=user;
        
        lblNewLabel_2.setText(""+oblicz_spedzony_czas());
        
        JLabel lblScenaDuaRazem = new JLabel("Scena Du\u017Ca razem :");
        lblScenaDuaRazem.setBounds(10, 107, 129, 14);
        getContentPane().add(lblScenaDuaRazem);
        
        JLabel lblNewLabel_3 = new JLabel("0.0");
        lblNewLabel_3.setBounds(182, 107, 46, 14);
        getContentPane().add(lblNewLabel_3);
        lblNewLabel_3.setText(""+oblicz_spedzony_czas_SD());
        
        JLabel lblScenaMaaRazem = new JLabel("Scena Ma\u0142a razem :");
        lblScenaMaaRazem.setBounds(10, 132, 129, 14);
        getContentPane().add(lblScenaMaaRazem);
        
        
        JLabel label = new JLabel("0.0");
        label.setBounds(182, 132, 46, 14);
        getContentPane().add(label);
        label.setText(""+oblicz_spedzony_czas_SK());
        
        JLabel lblRazemDyurw = new JLabel("Razem Dy\u017Cur\u00F3w :");
        lblRazemDyurw.setBounds(10, 157, 129, 14);
        getContentPane().add(lblRazemDyurw);
        
        JLabel lblNewLabel_4 = new JLabel("0.0");
        lblNewLabel_4.setBounds(182, 157, 46, 14);
        getContentPane().add(lblNewLabel_4);
        lblNewLabel_4.setText(""+oblicz_dyzury());
      
       
	}
	

	
	int[][] bubblesort(int[][] a)
	{
		int[][] tab=a;
		
		for(int i=0;i<tab.length;i++)
		{
			for(int j=0;j<tab[i].length-1;j++)
			{
				if(tab[1][j]>tab[1][j+1])
				{
					int temp = tab[1][j+1];
					int temp2 = tab[0][j+1];
					
					tab[1][j+1]=tab[1][j];
					tab[0][j+1]=tab[0][j];
					
					tab[1][j]=temp;
					tab[0][j]=temp2;

				}
			}
		}
		return tab;
	}
	
	int oblicz_dyzury()
	{
		int suma =0;
		for(Podsumowanie_Miesiaca x :user.dajPodsumowanie())
		{
			for(Spektakl_praca y :x.daj_liste())
			{
				if(y.daj_dyzur().equals("D"))
				{
					suma++;
				}
			}
		}
		return suma;
	}
	double oblicz_spedzony_czas()
	{
		double suma =0.0;
		for(Podsumowanie_Miesiaca x :user.dajPodsumowanie())
		{
			suma+=x.daj_spedzony_czas();
		}
		return suma;
	}
	double oblicz_spedzony_czas_SD()
	{
		double suma =0.0;
		for(Podsumowanie_Miesiaca x :user.dajPodsumowanie())
		{
			suma+=x.daj_spedzony_czas_SD();
		}
		return suma;
	}
	double oblicz_spedzony_czas_SK()
	{
		double suma =0.0;
		for(Podsumowanie_Miesiaca x :user.dajPodsumowanie())
		{
			suma+=x.daj_spedzony_czas_SK();
		}
		return suma;
	}
}
