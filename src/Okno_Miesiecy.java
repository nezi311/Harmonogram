import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class Okno_Miesiecy implements ActionListener
{
	
	// Dane Uzytkownika
	private Uzytkownik user;
	// Dane do tabel
	Object[][] dane2;
	// Dane na temat godzin 
	private double sd = 0,sk = 0;
	// Okno miesiaca
    private JFrame	JFrOknoMiesiaca;
    private JPanel pOknoMiesiaca_1,pOknoMiesiaca_2,pOknoMiesiaca_3;
    private int i,zmienna_indexowa,zmienna_indexowa_miesiac,zmienna_indexowa_i,zmienna_indexowa_i2;
    private Object[] naglowek2;
	private JTable tabela2;// Tabela do bazy spektakli
	private JButton bDodajMiesiacSpektakl,bMiesiacEdytuj,bMiesiacWroc,bMiesiacUsun,bMiesiacDajTabeleDoDruku,bMiesiacDodatkowe;
	private JLabel lSktlDyzur,lSktlDzien,lSktlDoMin,lSktlDoGodz,lSktlOdMin,lSktlOdGodz,lSktlMin,lSktlGodz;
	// Zapis do notatnika
	private JTextArea txtAreaNotatnik;
	private JButton btxtAreaZapisz;
	// Edycja miesiaca okno
	private JFrame JFrDodajMiesiacSpektakl; 
	private JTextField txtSktlGodz,txtSpktlMin,txtSktlOdGodz,txtSktlOdMin,txtSktlDoGodz,txtSktlDoMin,txtSktlDoGodzE,txtSktlDoMinE,txtSktlDzien;
	private JButton bMiesiacEdytuj2;
	private JComboBox<Spektakl> combSpektakle;
	private JCheckBox cboxDyzur;
	// Dodanie spektaklu do listy
	private JButton bMiesiacSpektakl_DodajSpektakl;
	

	
	
	
	public Okno_Miesiecy(Uzytkownik user,int i) 
	{
		this.user = user;
		this.i=i;
		try
		{
			wczytaj_elementy_okna();
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null,""+e.toString());
		}
		
		
	}
	
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
	
	void wczytaj_elementy_okna()
	{
		try
		{
			//przykladowe_dane();
			JFrOknoMiesiaca = new JFrame(user.dajPodsumowanie().get(i).toString());
	        //blokada zmiany rozmiaru okna
			JFrOknoMiesiaca.setResizable (false);
	        //setLayout(new FlowLayout(FlowLayout.CENTER, 2, 2));
	        //wyswietlenie naszej ramki
			JFrOknoMiesiaca.setSize(1000, 600);
			JFrOknoMiesiaca.setLayout(null);
			JFrOknoMiesiaca.setLocationRelativeTo(null);  
			//dodawanie miesiecy 
			
			pOknoMiesiaca_1 = new JPanel();
			pOknoMiesiaca_1.setBounds(0, 0, 850, 500);
			pOknoMiesiaca_1.setLayout(new GridLayout(0,1));
			JFrOknoMiesiaca.getContentPane().add(pOknoMiesiaca_1);
			//pOknoMiesiaca_1.setBorder(BorderFactory.createLineBorder(Color.black));
			pOknoMiesiaca_1.setVisible(true);
			
			pOknoMiesiaca_2 = new JPanel();
			pOknoMiesiaca_2.setBounds(850, 0, 150, 600);
			//BazaSpektakli_2.setLayout(new GridLayout(0,1));
			pOknoMiesiaca_2.setLayout(null);
			JFrOknoMiesiaca.getContentPane().add(pOknoMiesiaca_2);
			//pOknoMiesiaca_2.setBorder(BorderFactory.createLineBorder(Color.red));
			pOknoMiesiaca_2.setVisible(true);
			
			pOknoMiesiaca_3 = new JPanel();
			pOknoMiesiaca_3.setBounds(0, 500, 850, 100);
			//BazaSpektakli_2.setLayout(new GridLayout(0,1));
			pOknoMiesiaca_3.setLayout(null);
			JFrOknoMiesiaca.getContentPane().add(pOknoMiesiaca_3);
			//pOknoMiesiaca_3.setBorder(BorderFactory.createLineBorder(Color.GREEN));
			pOknoMiesiaca_3.setVisible(true);
			
			dane2 = new Object[user.dajPodsumowanie().get(i).daj_liste().size()][10];
			naglowek2 = new Object[10];
			naglowek2[0]=new String("L.P");
			naglowek2[1]=new String("Dzie\u0144");
			naglowek2[2]=new String("Nazwa Spektaklu");
			naglowek2[3]=new String("");
			naglowek2[4]=new String("Od");
			naglowek2[5]=new String("Do");
			naglowek2[6]=new String("Czas pracy");
			naglowek2[7]=new String("Scena");
			naglowek2[8]=new String("Dy\u017Cur");
			naglowek2[9]=new String("");
			for(int j=0;j<user.dajPodsumowanie().get(i).daj_liste().size();j++)
			{
				dane2[j][0]=new Integer(j+1);
				dane2[j][1]=new Integer(user.dajPodsumowanie().get(i).daj_liste().get(j).daj_dzien());
				dane2[j][2]=new String(user.dajPodsumowanie().get(i).daj_liste().get(j).daj_nazwe_spektaklu());
				dane2[j][3]=new String(user.dajPodsumowanie().get(i).daj_liste().get(j).daj_godzine_spektaklu());
				dane2[j][4]=new String(user.dajPodsumowanie().get(i).daj_liste().get(j).daj_godzine_przyjscia());
				dane2[j][5]=new String(user.dajPodsumowanie().get(i).daj_liste().get(j).daj_godzine_wyjscia());
				dane2[j][6]=new Double(user.dajPodsumowanie().get(i).daj_liste().get(j).daj_czas_pracy());
				dane2[j][7]=new String(user.dajPodsumowanie().get(i).daj_liste().get(j).daj_scene());
				dane2[j][8]=new String(user.dajPodsumowanie().get(i).daj_liste().get(j).daj_dyzur());
				dane2[j][9]=false;
				
				if((user.dajPodsumowanie().get(i).daj_liste().get(j).daj_scene()).equals("SK"))
				{
					sk+=user.dajPodsumowanie().get(i).daj_liste().get(j).daj_czas_pracy();
				}
				if((user.dajPodsumowanie().get(i).daj_liste().get(j).daj_scene()).equals("SD"))
				{
					sd+=user.dajPodsumowanie().get(i).daj_liste().get(j).daj_czas_pracy();
				}

			}
			//System.out.println(user.dajPodsumowanie().get(i).daj_liste().size());
			DefaultTableModel model = new DefaultTableModel(dane2,naglowek2);
			tabela2 = new JTable(model){

	            private static final long serialVersionUID = 1L;

	            @Override // wylaczenie mozliwosci edycji komorek
	            public boolean isCellEditable(int row, int column) {
	            	if(column==9)
	            	{
	            		return true;
	            	}
	            	else{
	            		return false;   
	            	}                 
	            }
	            @Override // potrzebne do checkboxow w tabeli
	            public Class getColumnClass(int column) {
	                switch (column) {
	                    case 0:
	                        return Integer.class;
	                    case 1:
	                        return Integer.class;
	                    case 2:
	                        return String.class;
	                    case 3:
	                    	return String.class;
	                    case 4:
	                    	return String.class;
	                    case 5:
	                    	return String.class;
	                    case 6:
	                    	return Double.class;
	                    case 7:
	                    	return String.class;
	                    case 8:
	                    	return String.class;
	                    
	                    default:
	                        return Boolean.class;
	                }
	            }
	        };
	        
			tabela2.setBounds(0, 0,840,500);
			tabela2.setPreferredScrollableViewportSize(new Dimension(840,500));
			tabela2.setFillsViewportHeight(true);
			tabela2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			//nadawanie wlugosci komorek
			tabela2.getColumnModel().getColumn(0).setPreferredWidth(30);
			tabela2.getColumnModel().getColumn(1).setPreferredWidth(40);
			tabela2.getColumnModel().getColumn(2).setPreferredWidth(420);
			tabela2.getColumnModel().getColumn(3).setPreferredWidth(50);
			tabela2.getColumnModel().getColumn(4).setPreferredWidth(50);
			tabela2.getColumnModel().getColumn(5).setPreferredWidth(50);
			tabela2.getColumnModel().getColumn(6).setPreferredWidth(70);
			tabela2.getColumnModel().getColumn(7).setPreferredWidth(50);
			tabela2.getColumnModel().getColumn(8).setPreferredWidth(50);
			tabela2.getColumnModel().getColumn(9).setPreferredWidth(20);
			
			tabela2.setRowHeight(20);
			DefaultTableCellRenderer centerRemderer = new DefaultTableCellRenderer();
			centerRemderer.setHorizontalAlignment(JLabel.CENTER);
			
			// komorki sa wysrodkowywane
			//tabela2.getColumnModel().getColumn(8).setCellRenderer(centerRemderer);
			tabela2.getColumnModel().getColumn(7).setCellRenderer(centerRemderer);
			tabela2.getColumnModel().getColumn(6).setCellRenderer(centerRemderer);
			tabela2.getColumnModel().getColumn(5).setCellRenderer(centerRemderer);
			tabela2.getColumnModel().getColumn(4).setCellRenderer(centerRemderer);
			tabela2.getColumnModel().getColumn(3).setCellRenderer(centerRemderer);
			tabela2.getColumnModel().getColumn(2).setCellRenderer(centerRemderer);
			tabela2.getColumnModel().getColumn(1).setCellRenderer(centerRemderer);
			tabela2.getColumnModel().getColumn(0).setCellRenderer(centerRemderer);

			JLabel lsd = new JLabel("Scena Du\u017Ca: ");
			lsd.setText("Scena  Du\u017Ca: "+sd);
			lsd.setBounds(10, 20, 202, 50);
			pOknoMiesiaca_3.add(lsd);
			
			JLabel lsm = new JLabel("Scena Kameralna: ");
			lsm.setText("Scena Kameralna: "+sk);
			lsm.setBounds(212, 20, 202, 50);
			pOknoMiesiaca_3.add(lsm);
			
			JLabel lrazem = new JLabel("Razem: ");
			lrazem.setText("Razem: "+(sk+sd));
			lrazem.setBounds(614, 20, 202, 50);
			pOknoMiesiaca_3.add(lrazem);
			
			bDodajMiesiacSpektakl = new JButton("Dodaj");
			bDodajMiesiacSpektakl.setBounds(0, 0, 150, 50);
			bDodajMiesiacSpektakl.addActionListener(this);
			pOknoMiesiaca_2.add(bDodajMiesiacSpektakl);
			
			bMiesiacEdytuj = new JButton("Edytuj");
			bMiesiacEdytuj.setBounds(0, 50, 150, 50);
			bMiesiacEdytuj.addActionListener(this);
			pOknoMiesiaca_2.add(bMiesiacEdytuj);
			
		
			JScrollPane scrollpane = new JScrollPane(tabela2);
			scrollpane.setVisible(true);
			pOknoMiesiaca_1.add(scrollpane);
			zmienna_indexowa=user.dajPodsumowanie().get(i).daj_rok();
			zmienna_indexowa_miesiac=user.dajPodsumowanie().get(i).daj_miesiac();
			zmienna_indexowa_i=i;
			
			bMiesiacWroc = new JButton("Wr\u00F3\u0107");
			bMiesiacWroc.setBounds(0,525,150,50);
			bMiesiacWroc.addActionListener(this);
			pOknoMiesiaca_2.add(bMiesiacWroc);
			
			bMiesiacUsun = new JButton("Usu\u0144");
			bMiesiacUsun.setBounds(0, 100, 150, 50);
			bMiesiacUsun.addActionListener(this);
			pOknoMiesiaca_2.add(bMiesiacUsun);
		
			
			bMiesiacDajTabeleDoDruku = new JButton("Daj do druku");
			bMiesiacDajTabeleDoDruku.setBounds(0, 150, 150, 50);
			bMiesiacDajTabeleDoDruku.addActionListener(this);
			pOknoMiesiaca_2.add(bMiesiacDajTabeleDoDruku);
			
			bMiesiacDodatkowe = new JButton("Inne");
			bMiesiacDodatkowe.setBounds(0, 200, 150, 50);
			bMiesiacDodatkowe.addActionListener(this);
			pOknoMiesiaca_2.add(bMiesiacDodatkowe);
			
			
			JFrOknoMiesiaca.setVisible(true);
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null,""+e.toString());
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public void actionPerformed(ActionEvent element) 
	{
		// TODO Auto-generated method stub
		Object zrodlo = element.getSource();
		
		if(zrodlo == bMiesiacDajTabeleDoDruku)
		{
			try
			{
				ZapisDoPliku zapis = new ZapisDoPliku();
				zapis.zapisz(user.dajPodsumowanie().get(zmienna_indexowa_i).daj_html(user.dajImie(),user.dajNazwisko()));
				//JOptionPane.showMessageDialog(null,"<html>Pami\u0119taj aby na ko\u0144cu lini zawsze stawia\u0107 ; </html>");
				
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(null,""+e.toString());
			}

			
		}
		
		if(zrodlo == bMiesiacDodatkowe)
		{
			try
			{
				JFrame temp = new JFrame("Oprowadzania / ulotki / inne");
				  //blokada zmiany rozmiaru okna
				temp.setResizable (false);
		        //wyswietlenie naszej ramki
				temp.setSize(480, 360);
				temp.setLayout(null);
				temp.setLocationRelativeTo(null);  
				
				txtAreaNotatnik = new JTextArea();
				txtAreaNotatnik.setFont(new Font("Serif", Font.ITALIC, 16));
				txtAreaNotatnik.setLineWrap(true);
				txtAreaNotatnik.setWrapStyleWord(true);
				
				txtAreaNotatnik.setText(user.dajPodsumowanie().get(zmienna_indexowa_i).daj_oprowadzania_lub_ulotki());
				JScrollPane pane = new JScrollPane(txtAreaNotatnik);
				pane.setBounds(5, 5, 465, 270);
				temp.getContentPane().add(pane);
				
				btxtAreaZapisz = new JButton("Zapisz");
				btxtAreaZapisz.setBounds(5,275,465,50);
				btxtAreaZapisz.addActionListener(this);
				temp.getContentPane().add(btxtAreaZapisz);
				
				
				
				temp.setVisible(true);
				JOptionPane.showMessageDialog(null,"<html>Pami\u0119taj aby na ko\u0144cu lini zawsze stawia\u0107 ; </html>");
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(null,""+e.toString());
			}
		}
		
		if(zrodlo == btxtAreaZapisz)
		{
			try
			{
				if(user.dajPodsumowanie().get(zmienna_indexowa_i).zamien_oprowadzania_lub_ulotki(txtAreaNotatnik.getText()))
				{
					ZapisDoPliku zapis = new ZapisDoPliku();
					zapis.zapisz(user,"user","dat");
					JOptionPane.showMessageDialog(null,"Sukces!");
				}
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(null,""+e.toString());
			}

		}
		
		if(zrodlo == bMiesiacUsun)
		{
			try
			{
				
				int temp=tabela2.getRowCount();
				boolean stan=false; // czy zaznaczono jakas pozycje
				for(int i=temp-1;i>=0;i--)
				{
					if(((boolean) tabela2.getValueAt(i,9)))
					{
						stan=true;
						break;
					}
				}
				
				if(stan) // jesli jest zaznaczona jakas pozycja to wykonaj
				{
					for(int i=temp-1;i>=0;i--)
					{
						
						if(((boolean) tabela2.getValueAt(i,9)))
						{	
							//System.out.println(user.dajBaze().size()+" : "+i);
		
							
							user.dajPodsumowanie().get(zmienna_indexowa_i).daj_liste().remove(i);
		
							temp=tabela2.getRowCount();
		
						}
					}
					ZapisDoPliku zapis = new ZapisDoPliku();
					zapis.zapisz(user,"user","dat");
					JFrOknoMiesiaca.dispose();
					JOptionPane.showMessageDialog(null,"<html>Usuni\u0119to wybrane spektakle.</html>");
				}
				else // w przeciwnym wypadku wyswietl informacje
				{
					JOptionPane.showMessageDialog(null,"<html>Nie zaznaczono pozycji!</html>");
				}
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(null,""+e.toString());
			}
		}
		
		if(zrodlo == bMiesiacEdytuj)
		{
			try
			{
				
				boolean stan=false; // czy zaznaczono jakas pozycje
				for(int i=0;i<tabela2.getRowCount();i++)
				{
					if((boolean)tabela2.getValueAt(i,9))
					{
						stan = true;
						break;
					}
				}
				
				if(stan) // jesli jest zaznaczona jakas pozycja to wykonaj
				{
			
					for(int i=0;i<tabela2.getRowCount();i++)
					{
						if((boolean)tabela2.getValueAt(i,9))
						{
							JFrDodajMiesiacSpektakl = new JFrame("Dzie\u0144 "+tabela2.getValueAt(i,1)+" , "+tabela2.getValueAt(i,2));
							JFrDodajMiesiacSpektakl.setResizable (false);
					        //setLayout(new FlowLayout(FlowLayout.CENTER, 2, 2));
					        //wyswietlenie naszej ramki
							JFrDodajMiesiacSpektakl.setSize(305, 550);
							JFrDodajMiesiacSpektakl.setLayout(null);
							JFrDodajMiesiacSpektakl.setLocationRelativeTo(null);  
							JFrDodajMiesiacSpektakl.setVisible(true);
							JFrDodajMiesiacSpektakl.setLayout(null);
							
							lSktlGodz = new JLabel("Godzina Spektaklu",SwingConstants.CENTER);
							lSktlGodz.setBounds(5,  5,145, 50);
							JFrDodajMiesiacSpektakl.getContentPane().add(lSktlGodz);
							
							lSktlMin = new JLabel("Minuta Spektaklu",SwingConstants.CENTER);
							lSktlMin.setBounds(150, 5, 145, 50);
							JFrDodajMiesiacSpektakl.getContentPane().add(lSktlMin);
							
							txtSktlGodz = new JTextField("19",SwingConstants.CENTER);
							txtSktlGodz.setBounds(5,55, 145, 50);
							txtSktlGodz.setText(""+user.dajPodsumowanie().get(zmienna_indexowa_i).daj_liste().get(i).daj_h_rozpoczecia());
							txtSktlGodz.setHorizontalAlignment(JTextField.CENTER);
							JFrDodajMiesiacSpektakl.getContentPane().add(txtSktlGodz);
							
							txtSpktlMin = new JTextField("00",SwingConstants.CENTER);
							txtSpktlMin.setBounds(150, 55, 145, 50);
							txtSpktlMin.setHorizontalAlignment(JTextField.CENTER);
							txtSpktlMin.setText(""+user.dajPodsumowanie().get(zmienna_indexowa_i).daj_liste().get(i).daj_m_rozpoczecia());
							JFrDodajMiesiacSpektakl.getContentPane().add(txtSpktlMin);
							
							
							bMiesiacEdytuj2 = new JButton("Edytuj");
							bMiesiacEdytuj2.setBounds(5, 460, 290, 50);
							JFrDodajMiesiacSpektakl.getContentPane().add(bMiesiacEdytuj2);
							bMiesiacEdytuj2.addActionListener(this);
							
							combSpektakle = new JComboBox<Spektakl>();
							combSpektakle.setBounds(5, 105, 290, 50);
							((JLabel)combSpektakle.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
							
							JFrDodajMiesiacSpektakl.getContentPane().add(combSpektakle);
							combSpektakle.addActionListener(this);
	
							lSktlOdGodz = new JLabel("Od (H)",SwingConstants.CENTER);
							lSktlOdGodz.setBounds(5, 155, 145, 50);
							JFrDodajMiesiacSpektakl.getContentPane().add(lSktlOdGodz);
							
							lSktlOdMin = new JLabel("Od (M)",SwingConstants.CENTER);
							lSktlOdMin.setBounds(150, 155, 145, 50);
							JFrDodajMiesiacSpektakl.getContentPane().add(lSktlOdMin);
							
							txtSktlOdGodz = new JTextField();
							txtSktlOdGodz.setBounds(5, 205, 145, 50);
							txtSktlOdGodz.setHorizontalAlignment(JTextField.CENTER);
							txtSktlOdGodz.setText(""+(user.dajPodsumowanie().get(zmienna_indexowa_i).daj_liste().get(i).daj_h_rozpoczecia()-1));
							JFrDodajMiesiacSpektakl.getContentPane().add(txtSktlOdGodz);
							
							txtSktlOdMin = new JTextField();
							txtSktlOdMin.setBounds(150, 205, 145, 50);
							txtSktlOdMin.setHorizontalAlignment(JTextField.CENTER);
							txtSktlOdMin.setText(""+(user.dajPodsumowanie().get(zmienna_indexowa_i).daj_liste().get(i).daj_m_rozpoczecia()));
							JFrDodajMiesiacSpektakl.getContentPane().add(txtSktlOdMin);
							
							lSktlDoGodz = new JLabel("Do (H)",SwingConstants.CENTER);
							lSktlDoGodz.setBounds(5, 255, 145, 50);
							JFrDodajMiesiacSpektakl.getContentPane().add(lSktlDoGodz);
							
							lSktlDoMin = new JLabel("Do (M)",SwingConstants.CENTER);
							lSktlDoMin.setBounds(150, 255, 145, 50);
							JFrDodajMiesiacSpektakl.getContentPane().add(lSktlDoMin);
							
							//--- Tylko po to aby actionlistener w combo sie nie wysypywal
							txtSktlDoGodz = new JTextField();
							txtSktlDoGodz.setBounds(5, 305, 145, 50);
							txtSktlDoGodz.setHorizontalAlignment(JTextField.CENTER);
	
							txtSktlDoMin = new JTextField();
							txtSktlDoMin.setBounds(150, 305, 145, 50);
							txtSktlDoMin.setHorizontalAlignment(JTextField.CENTER);
							//---
							
							txtSktlDoGodzE = new JTextField();
							txtSktlDoGodzE.setBounds(5, 305, 145, 50);
							txtSktlDoGodzE.setHorizontalAlignment(JTextField.CENTER);
							txtSktlDoGodzE.setText(""+(user.dajPodsumowanie().get(zmienna_indexowa_i).daj_liste().get(i).daj_do_h()));
							JFrDodajMiesiacSpektakl.getContentPane().add(txtSktlDoGodzE);
							
							txtSktlDoMinE = new JTextField();
							txtSktlDoMinE.setBounds(150, 305, 145, 50);
							txtSktlDoMinE.setHorizontalAlignment(JTextField.CENTER);
							txtSktlDoMinE.setText(""+user.dajPodsumowanie().get(zmienna_indexowa_i).daj_liste().get(i).daj_do_m());
							JFrDodajMiesiacSpektakl.getContentPane().add(txtSktlDoMinE);
							
							lSktlDzien = new JLabel("Dzie\u0144",SwingConstants.CENTER);
							lSktlDzien.setBounds(5, 355, 145, 50);
							JFrDodajMiesiacSpektakl.getContentPane().add(lSktlDzien);
							
							txtSktlDzien = new JTextField();
							txtSktlDzien.setBounds(5, 405, 145, 50);
							txtSktlDzien.setHorizontalAlignment(JTextField.CENTER);
							txtSktlDzien.setText(""+user.dajPodsumowanie().get(zmienna_indexowa_i).daj_liste().get(i).daj_dzien());
							JFrDodajMiesiacSpektakl.getContentPane().add(txtSktlDzien);
							
							
							
							lSktlDyzur = new JLabel("Dy\u017Cur",SwingConstants.CENTER);
							lSktlDyzur.setBounds(150, 355, 145, 50);
							JFrDodajMiesiacSpektakl.getContentPane().add(lSktlDyzur);
							
							cboxDyzur = new JCheckBox();
							cboxDyzur.setBounds(210, 405, 145, 50);
							// sprawdzenie czy w danym spektaklu byl dyzur
								if(user.dajPodsumowanie().get(zmienna_indexowa_i).daj_liste().get(i).daj_dyzur().equals("D"))
								{
									cboxDyzur.setSelected(true);	
								}
							JFrDodajMiesiacSpektakl.getContentPane().add(cboxDyzur);
							zmienna_indexowa_i2=i;
	

								
							
							for(int j=0 ; j<user.dajBaze().size();j++)
							{
								if(user.dajBaze().get(j).daj_nazwe_spektaklu().equals(tabela2.getValueAt(i,2)))
								{
									combSpektakle.addItem(user.dajBaze().get(j));
								}
							}
							for(int j=0 ; j<user.dajBaze().size();j++)
							{
								if(!user.dajBaze().get(j).daj_nazwe_spektaklu().equals(tabela2.getValueAt(i,2)))
								{
									combSpektakle.addItem(user.dajBaze().get(j));
								}
							}
						}
					}
				}
				
				else // w przeciwnym wypadku wyswietl informacje
				{
					JOptionPane.showMessageDialog(null,"<html>Nie zaznaczono pozycji!</html>");
				}	
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(null,""+e.toString());
			}
		}
		
		
		
		
		if(zrodlo == bMiesiacEdytuj2)
		{
			try
			{
				boolean blad=false;
				
				if(!isNumeric(txtSktlDzien.getText())|| txtSktlDzien.getText().contains(",") || txtSktlDzien.getText().contains("."))
				{
					blad=true;
				}
				if(!isNumeric(txtSktlGodz.getText())|| txtSktlGodz.getText().contains(",") || txtSktlGodz.getText().contains("."))
				{
					blad=true;
				}
				if(!isNumeric(txtSpktlMin.getText())|| txtSpktlMin.getText().contains(",") || txtSpktlMin.getText().contains("."))
				{
					blad=true;
				}
				if(!isNumeric(txtSktlDoGodzE.getText())|| txtSktlDoGodzE.getText().contains(",") || txtSktlDoGodzE.getText().contains("."))
				{
					blad=true;
				}
				if(!isNumeric(txtSktlDoMinE.getText())|| txtSktlDoMinE.getText().contains(",") || txtSktlDoMinE.getText().contains("."))
				{
					blad=true;
				}
				if(!isNumeric(txtSktlGodz.getText())|| txtSktlGodz.getText().contains(",") || txtSktlGodz.getText().contains("."))
				{
					blad=true;
				}
				if(!isNumeric(txtSktlOdMin.getText())|| txtSktlOdMin.getText().contains(",") || txtSktlOdMin.getText().contains("."))
				{
					blad=true;
				}
				
				if(!blad)
				{
					if(Integer.parseInt(txtSktlDzien.getText())>0 && Integer.parseInt(txtSktlDzien.getText())<32)
					{	//System.out.println("1");
						if((Integer.parseInt(txtSktlGodz.getText())-1)>=0 && (Integer.parseInt(txtSktlGodz.getText())-1)<25)
						{	//System.out.println("2");
							if(	Integer.parseInt(txtSpktlMin.getText())==00 || 
								Integer.parseInt(txtSpktlMin.getText())==15 ||
								Integer.parseInt(txtSpktlMin.getText())== 30 ||
								Integer.parseInt(txtSpktlMin.getText())== 45 )
								
							{
								//System.out.println("3");
								if(Integer.parseInt(txtSktlDoGodzE.getText())>=0 && Integer.parseInt(txtSktlDoGodzE.getText())<25)
								{
									//System.out.println("4");
									
									if(	Integer.parseInt(txtSktlDoMinE.getText())==00 || 
										Integer.parseInt(txtSktlDoMinE.getText())==15 ||
										Integer.parseInt(txtSktlDoMinE.getText())== 30 ||
										Integer.parseInt(txtSktlDoMinE.getText())== 45 )
									{
										//System.out.println("5");
										if(Integer.parseInt(txtSktlGodz.getText())>=0 && Integer.parseInt(txtSktlGodz.getText())<25)
										{	//System.out.println("6");
											if(	Integer.parseInt(txtSktlOdMin.getText())==00 || 
												Integer.parseInt(txtSktlOdMin.getText())==15 ||
												Integer.parseInt(txtSktlOdMin.getText())== 30 ||
												Integer.parseInt(txtSktlOdMin.getText())== 45 )
											{
												//System.out.println("7");
												
													if(
															user.dajPodsumowanie().get(zmienna_indexowa_i).daj_liste().get(zmienna_indexowa_i2).zmien_nazwe_spektaklu(combSpektakle.getSelectedItem().toString()) &&
															user.dajPodsumowanie().get(zmienna_indexowa_i).daj_liste().get(zmienna_indexowa_i2).zmien_dyzur(cboxDyzur.isSelected()) &&
															user.dajPodsumowanie().get(zmienna_indexowa_i).daj_liste().get(zmienna_indexowa_i2).zmien_dzien(Integer.parseInt(txtSktlDzien.getText())) &&
															user.dajPodsumowanie().get(zmienna_indexowa_i).daj_liste().get(zmienna_indexowa_i2).zmien_godzine_przyjscia((Integer.parseInt(txtSktlGodz.getText())-1),(Integer.parseInt(txtSpktlMin.getText()))) &&
															user.dajPodsumowanie().get(zmienna_indexowa_i).daj_liste().get(zmienna_indexowa_i2).zmien_godzine_rozpoczecia((Integer.parseInt(txtSktlGodz.getText())),(Integer.parseInt(txtSpktlMin.getText()))) &&
															user.dajPodsumowanie().get(zmienna_indexowa_i).daj_liste().get(zmienna_indexowa_i2).zmien_godzine_wyjscia(Integer.parseInt(txtSktlDoGodzE.getText()),Integer.parseInt(txtSktlDoMinE.getText()))
														)
												{
													JFrDodajMiesiacSpektakl.dispose();
													JFrOknoMiesiaca.dispose();
													
													// sortowanie listy po zmianie 
													Collections.sort(user.dajPodsumowanie().get(zmienna_indexowa_i).daj_liste());
													
													ZapisDoPliku zapis = new ZapisDoPliku();
													zapis.zapisz(user, "user", "dat");
													JOptionPane.showMessageDialog(null,"<html>Edytowanie pomy\u015Blne</html>");
													
													
												}
												else{JOptionPane.showMessageDialog(null,"<html>Dodawanie nie pomy\u015Blne</html>");}
												
												
											
											}else{JOptionPane.showMessageDialog(null,"<html>B\u0142\u0105d nieprawid\u0142owa wartosc w "+lSktlOdMin.getText()+"</html>");}
										}else{JOptionPane.showMessageDialog(null,"<html>B\u0142\u0105d nieprawid\u0142owa wartosc w "+lSktlGodz.getText()+"</html>");}
									}else{JOptionPane.showMessageDialog(null,"<html>B\u0142\u0105d nieprawid\u0142owa wartosc w "+lSktlDoMin.getText()+"</html>");}
								}else{JOptionPane.showMessageDialog(null,"<html>B\u0142\u0105d nieprawid\u0142owa wartosc w "+lSktlDoGodz.getText()+"</html>");}
							}else{JOptionPane.showMessageDialog(null,"<html>B\u0142\u0105d nieprawid\u0142owa wartosc w "+lSktlMin.getText()+"</html>");}
						}else{JOptionPane.showMessageDialog(null,"<html>B\u0142\u0105d nieprawid\u0142owa wartosc w "+lSktlGodz.getText()+"</html>");}
					}else{JOptionPane.showMessageDialog(null,"<html>B\u0142\u0105d nieprawid\u0142owa wartosc w "+lSktlDzien.getText()+"</html>");}		
				} // koniec sprawdzenia czy pola sa na pewno liczbami
				else
				{
					JOptionPane.showMessageDialog(null,"<html>B\u0142\u0105d jakie\u015B pole nie jest liczb\u0105</html>");
				}
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(null,""+e.toString());
			}
		}
		
		
		if(zrodlo	== combSpektakle)
		{
			try
			{
				//System.out.println("dziala");
				//nadanie przewidywanego czasu rozpoczêcia dla pól textfield o zadanych nazwach zmiennycg
				txtSktlOdGodz.setText(""+(Integer.parseInt(txtSktlGodz.getText())-1));
				txtSktlOdMin.setText(txtSpktlMin.getText());
				
				Spektakl_praca x = new Spektakl_praca(1,(Integer.parseInt(txtSktlOdGodz.getText())-1)
					,Integer.parseInt(txtSpktlMin.getText())
					,(Integer.parseInt(txtSktlOdGodz.getText())+((Spektakl)combSpektakle.getSelectedItem()).daj_godzine_trwania_spektaklu())
					,(Integer.parseInt(txtSktlOdMin.getText())+((Spektakl)combSpektakle.getSelectedItem()).daj_minute_trwania_spektaklu())
					,((Spektakl)combSpektakle.getSelectedItem()).daj_godzine_trwania_spektaklu()
					,((Spektakl)combSpektakle.getSelectedItem()).daj_minute_trwania_spektaklu()
					,((Spektakl)combSpektakle.getSelectedItem()).daj_scene()
					,((Spektakl)combSpektakle.getSelectedItem()).daj_nazwe_spektaklu()
					,true);	
				
				// nadanie przewidywanych godzin i minut do pola textfield o zadanych nazwach zmiennych
				txtSktlDoGodz.setText(""+x.daj_do_h()); 
				txtSktlDoMin.setText(""+x.daj_do_m());
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(null,""+e.toString());
			}
		}
		
		
		if(zrodlo == bDodajMiesiacSpektakl)
		{
			try
			{
				JFrDodajMiesiacSpektakl = new JFrame("Dodaj spektakl");
				JFrDodajMiesiacSpektakl.setResizable (false);
		        //setLayout(new FlowLayout(FlowLayout.CENTER, 2, 2));
		        //wyswietlenie naszej ramki
				JFrDodajMiesiacSpektakl.setSize(305, 550);
				JFrDodajMiesiacSpektakl.setLayout(null);
				JFrDodajMiesiacSpektakl.setLocationRelativeTo(null);  
				JFrDodajMiesiacSpektakl.setVisible(true);
				JFrDodajMiesiacSpektakl.setLayout(null);
				
				lSktlGodz = new JLabel("Godzina Spektaklu",SwingConstants.CENTER);
				lSktlGodz.setBounds(5,  5,145, 50);
				JFrDodajMiesiacSpektakl.getContentPane().add(lSktlGodz);
				
				lSktlMin = new JLabel("Minuta Spektaklu",SwingConstants.CENTER);
				lSktlMin.setBounds(150, 5, 145, 50);
				JFrDodajMiesiacSpektakl.getContentPane().add(lSktlMin);
				
				txtSktlGodz = new JTextField("19",SwingConstants.CENTER);
				txtSktlGodz.setBounds(5,55, 145, 50);
				txtSktlGodz.setHorizontalAlignment(JTextField.CENTER);
				JFrDodajMiesiacSpektakl.getContentPane().add(txtSktlGodz);
				
				txtSpktlMin = new JTextField("00",SwingConstants.CENTER);
				txtSpktlMin.setBounds(150, 55, 145, 50);
				txtSpktlMin.setHorizontalAlignment(JTextField.CENTER);
				JFrDodajMiesiacSpektakl.getContentPane().add(txtSpktlMin);
				
				
				bMiesiacSpektakl_DodajSpektakl = new JButton("Dodaj");
				bMiesiacSpektakl_DodajSpektakl.setBounds(5, 460, 290, 50);
				JFrDodajMiesiacSpektakl.getContentPane().add(bMiesiacSpektakl_DodajSpektakl);
				bMiesiacSpektakl_DodajSpektakl.addActionListener(this);
				
				combSpektakle = new JComboBox<Spektakl>();
				combSpektakle.setBounds(5, 105, 290, 50);
				((JLabel)combSpektakle.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
				
				JFrDodajMiesiacSpektakl.getContentPane().add(combSpektakle);
				combSpektakle.addActionListener(this);

				lSktlOdGodz = new JLabel("Od (H)",SwingConstants.CENTER);
				lSktlOdGodz.setBounds(5, 155, 145, 50);
				JFrDodajMiesiacSpektakl.getContentPane().add(lSktlOdGodz);
				
				lSktlOdMin = new JLabel("Od (M)",SwingConstants.CENTER);
				lSktlOdMin.setBounds(150, 155, 145, 50);
				JFrDodajMiesiacSpektakl.getContentPane().add(lSktlOdMin);
				
				txtSktlOdGodz = new JTextField();
				txtSktlOdGodz.setBounds(5, 205, 145, 50);
				txtSktlOdGodz.setHorizontalAlignment(JTextField.CENTER);
				JFrDodajMiesiacSpektakl.getContentPane().add(txtSktlOdGodz);
				
				txtSktlOdMin = new JTextField();
				txtSktlOdMin.setBounds(150, 205, 145, 50);
				txtSktlOdMin.setHorizontalAlignment(JTextField.CENTER);
				JFrDodajMiesiacSpektakl.getContentPane().add(txtSktlOdMin);
				
				lSktlDoGodz = new JLabel("Do (H)",SwingConstants.CENTER);
				lSktlDoGodz.setBounds(5, 255, 145, 50);
				JFrDodajMiesiacSpektakl.getContentPane().add(lSktlDoGodz);
				
				lSktlDoMin = new JLabel("Do (M)",SwingConstants.CENTER);
				lSktlDoMin.setBounds(150, 255, 145, 50);
				JFrDodajMiesiacSpektakl.getContentPane().add(lSktlDoMin);
				
				txtSktlDoGodz = new JTextField();
				txtSktlDoGodz.setBounds(5, 305, 145, 50);
				txtSktlDoGodz.setHorizontalAlignment(JTextField.CENTER);
				JFrDodajMiesiacSpektakl.getContentPane().add(txtSktlDoGodz);
				
				txtSktlDoMin = new JTextField();
				txtSktlDoMin.setBounds(150, 305, 145, 50);
				txtSktlDoMin.setHorizontalAlignment(JTextField.CENTER);
				JFrDodajMiesiacSpektakl.getContentPane().add(txtSktlDoMin);
				
				lSktlDzien = new JLabel("Dzie\u0144",SwingConstants.CENTER);
				lSktlDzien.setBounds(5, 355, 145, 50);
				JFrDodajMiesiacSpektakl.getContentPane().add(lSktlDzien);
				
				txtSktlDzien = new JTextField();
				txtSktlDzien.setBounds(5, 405, 145, 50);
				txtSktlDzien.setHorizontalAlignment(JTextField.CENTER);
				JFrDodajMiesiacSpektakl.getContentPane().add(txtSktlDzien);
				
				Calendar cal = Calendar.getInstance();
				int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
				txtSktlDzien.setText(String.valueOf(dayOfMonth));
				
				lSktlDyzur = new JLabel("Dy\u017Cur",SwingConstants.CENTER);
				lSktlDyzur.setBounds(150, 355, 145, 50);
				JFrDodajMiesiacSpektakl.getContentPane().add(lSktlDyzur);
				
				cboxDyzur = new JCheckBox();
				cboxDyzur.setBounds(210, 405, 145, 50);
				JFrDodajMiesiacSpektakl.getContentPane().add(cboxDyzur);
				
			
				
				
				
				for(int i=0 ; i<user.dajBaze().size();i++)
				{
					combSpektakle.addItem(user.dajBaze().get(i));
				}
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(null,""+e.toString());
			}
		}
		
		if(zrodlo == bMiesiacSpektakl_DodajSpektakl)
		{
			try
			{
				boolean blad=false;
				
				//--------------------------------------------
				//sprawdzamy czy podane wartosci sa liczbami
				//-------------------------------------------
				
				if(!isNumeric(txtSktlDzien.getText()) || txtSktlDzien.getText().contains(",") || txtSktlDzien.getText().contains("."))
				{
					blad=true;
				}
				if(!isNumeric(txtSktlGodz.getText()) || txtSktlGodz.getText().contains(",") || txtSktlGodz.getText().contains("."))
				{
					blad=true;
				}
				if(!isNumeric(txtSpktlMin.getText()) || txtSpktlMin.getText().contains(",") || txtSpktlMin.getText().contains("."))
				{
					blad=true;
				}
				if(!isNumeric(txtSktlDoGodz.getText()) || txtSktlDoGodz.getText().contains(",") || txtSktlDoGodz.getText().contains("."))
				{
					blad=true;
				}
				if(!isNumeric(txtSktlDoMin.getText()) || txtSktlDoMin.getText().contains(",") || txtSktlDoMin.getText().contains("."))
				{
					blad=true;
				}
				if(!isNumeric(txtSktlGodz.getText()) || txtSktlGodz.getText().contains(",") || txtSktlGodz.getText().contains("."))
				{
					blad=true;
				}
				if(!isNumeric(txtSktlOdMin.getText()) || txtSktlOdMin.getText().contains(",") || txtSktlOdMin.getText().contains("."))
				{
					blad=true;
				}
				
				if(!blad)
				{
					if(Integer.parseInt(txtSktlDzien.getText())>0 && Integer.parseInt(txtSktlDzien.getText())<32)
					{	//System.out.println("1");
						if((Integer.parseInt(txtSktlGodz.getText())-1)>=0 && (Integer.parseInt(txtSktlGodz.getText())-1)<25)
						{	//System.out.println("2");
							if(	Integer.parseInt(txtSpktlMin.getText())==00 || 
								Integer.parseInt(txtSpktlMin.getText())==15 ||
								Integer.parseInt(txtSpktlMin.getText())== 30 ||
								Integer.parseInt(txtSpktlMin.getText())== 45 )
								
							{
								//System.out.println("3");
								if(Integer.parseInt(txtSktlDoGodz.getText())>=0 && Integer.parseInt(txtSktlDoGodz.getText())<25)
								{
									//System.out.println("4");
									
									if(	Integer.parseInt(txtSktlDoMin.getText())==00 || 
										Integer.parseInt(txtSktlDoMin.getText())==15 ||
										Integer.parseInt(txtSktlDoMin.getText())== 30 ||
										Integer.parseInt(txtSktlDoMin.getText())== 45 )
									{
										//System.out.println("5");
										if(Integer.parseInt(txtSktlGodz.getText())>=0 && Integer.parseInt(txtSktlGodz.getText())<25)
										{	//System.out.println("6");
											if(	Integer.parseInt(txtSktlOdMin.getText())==00 || 
												Integer.parseInt(txtSktlOdMin.getText())==15 ||
												Integer.parseInt(txtSktlOdMin.getText())== 30 ||
												Integer.parseInt(txtSktlOdMin.getText())== 45 )
											{
												//System.out.println("7");
											
											boolean flaga=	user.spektakl_praca(zmienna_indexowa_miesiac,zmienna_indexowa
														,Integer.parseInt(txtSktlDzien.getText()) //dzien
														,(Integer.parseInt(txtSktlGodz.getText())-1)	//godzina od ktorej jestesmy w pracy
														,(Integer.parseInt(txtSpktlMin.getText()))		//minuta od ktorej jestesmy w pracy
														,(Integer.parseInt(txtSktlDoGodz.getText())) // godzina do ktorej jestesmy w pracy
														,(Integer.parseInt(txtSktlDoMin.getText()))	// minuta do ktorej jestesmy w pracy
														,Integer.parseInt(txtSktlGodz.getText())	// godzina od ktorej zaczyna sie spektakl
														,Integer.parseInt(txtSpktlMin.getText())	// minuta od ktorej zaczyna sie spektakl
														,((Spektakl)combSpektakle.getSelectedItem()).daj_scene()	//scena na ktorej odbywa sie spektakl	
														,((Spektakl)combSpektakle.getSelectedItem()).daj_nazwe_spektaklu()	//nazwa spektaklu
														, cboxDyzur.isSelected());	// czy odbywany byl na danym spektaklu dyzur
												Collections.sort(user.dajPodsumowanie().get(user.get_spektakl_praca(zmienna_indexowa_miesiac,zmienna_indexowa)).daj_liste());
												if(flaga)
												{
													JFrDodajMiesiacSpektakl.dispose();
													JFrOknoMiesiaca.dispose();
													
													// sortowanie listy po zmianie 
													
													
													ZapisDoPliku zapis = new ZapisDoPliku();
													zapis.zapisz(user, "user", "dat");
													JOptionPane.showMessageDialog(null,"<html>Dodawanie pomy\u015Blne</html>");
												}
												else{JOptionPane.showMessageDialog(null,"<html>Dodawanie nie pomy\u015Blne</html>");}
												
												
											
											}else{JOptionPane.showMessageDialog(null,"<html>B\u0142\u0105d nieprawid\u0142owa wartosc w "+lSktlOdMin.getText()+"</html>");}
										}else{JOptionPane.showMessageDialog(null,"<html>B\u0142\u0105d nieprawid\u0142owa wartosc w "+lSktlGodz.getText()+"</html>");}
									}else{JOptionPane.showMessageDialog(null,"<html>B\u0142\u0105d nieprawid\u0142owa wartosc w "+lSktlDoMin.getText()+"</html>");}
								}else{JOptionPane.showMessageDialog(null,"<html>B\u0142\u0105d nieprawid\u0142owa wartosc w "+lSktlDoGodz.getText()+"</html>");}
							}else{JOptionPane.showMessageDialog(null,"<html>B\u0142\u0105d nieprawid\u0142owa wartosc w "+lSktlMin.getText()+"</html>");}
						}else{JOptionPane.showMessageDialog(null,"<html>B\u0142\u0105d nieprawid\u0142owa wartosc w "+lSktlGodz.getText()+"</html>");}
					}else{JOptionPane.showMessageDialog(null,"<html>B\u0142\u0105d nieprawid\u0142owa wartosc w "+lSktlDzien.getText()+"</html>");}		
				}
				else
				{
					JOptionPane.showMessageDialog(null,"<html>B\u0142\u0105d jakie\u015B pole nie jest liczb\u0105</html>");
				}
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(null,""+e);
			}
		}
		
		if(zrodlo==bMiesiacWroc)
		{
			JFrOknoMiesiaca.dispose();
		}
		
		

		
		
	}
}

