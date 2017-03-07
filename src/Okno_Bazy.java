import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class Okno_Bazy implements ActionListener 
{

	// Dane Uzytkownika
	private Uzytkownik user;
	// Dane do tabel
	Object[][] dane;

	// Okno bazy spektakli
	private JPanel BazaSpektakli_2, BazaSpektakli_1;
	private JFrame JFrOknoBazySpektakli;
	private Object[] naglowek;
	private JTable tabela1;
	private JButton bBazaSpektakli_DodajSpektakl, bBazaEdytuj, bBazaUsun, bBazaWroc;

	// Dodanie spektaklu
	private JTextField txtBazaGodz, txtBazaMin, txtBazaNazwaSpektaklu;
	private JComboBox<String> combScena;
	private JButton bBazaSpektakli_DodajSpektakl2;
	JFrame JFrBazaSpektakli_DodajSpektakl;

	// Edycja spektaklu
	private JFrame JFrChwilowa;
	private JComboBox<String> TempcombScena;
	private JTextField txtEdycjaNazwa,txtEdycjaGodzina,txtEdycjaMinuta;
	private JButton bBazaEdytujZapisz;
	private int zmienna_indexowa_baza;
	
	// Usuniecie Spektaklu

	public Okno_Bazy(Object[][] dane, Uzytkownik user) 
	{

		this.user = user;
		this.dane = dane;
		wczytaj_elementy_okna();

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
			JFrOknoBazySpektakli = new JFrame("Baza Spektakli");
			// JFrOknoBazySpektakli.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			// blokada zmiany rozmiaru okna
			JFrOknoBazySpektakli.setResizable(false);
			// setLayout(new FlowLayout(FlowLayout.CENTER, 2, 2));
			JFrOknoBazySpektakli.setSize(800, 630);
			JFrOknoBazySpektakli.setLayout(null);
			// wysrodkowanie ramki
			JFrOknoBazySpektakli.setLocationRelativeTo(null);
			// wyswietlenie naszej ramki
			BazaSpektakli_1 = new JPanel();
			BazaSpektakli_1.setBounds(0, 0, 650, 600);
			BazaSpektakli_1.setLayout(new GridLayout(0, 1));
			JFrOknoBazySpektakli.getContentPane().add(BazaSpektakli_1);
			// BazaSpektakli_1.setBorder(BorderFactory.createLineBorder(Color.black));
			BazaSpektakli_1.setVisible(true);

			BazaSpektakli_2 = new JPanel();
			BazaSpektakli_2.setBounds(650, 0, 150, 600);
			// BazaSpektakli_2.setLayout(new GridLayout(0,1));
			BazaSpektakli_2.setLayout(null);
			JFrOknoBazySpektakli.getContentPane().add(BazaSpektakli_2);
			// BazaSpektakli_2.setBorder(BorderFactory.createLineBorder(Color.red));
			BazaSpektakli_2.setVisible(true);

			dane = new Object[user.dajBaze().size()][6];
			naglowek = new Object[6];
			naglowek[0] = new String("L.P");
			naglowek[1] = new String("Nazwa Spektaklu");
			naglowek[2] = new String("(G)");
			naglowek[3] = new String("(M)");
			naglowek[4] = new String("Scena");
			naglowek[5] = new String("");
			for (int i = 0; i < user.dajBaze().size(); i++) 
			{
				dane[i][0] = new Integer(i + 1);
				dane[i][1] = new String(user.dajBaze().get(i).daj_nazwe_spektaklu());
				dane[i][2] = new Integer(user.dajBaze().get(i).daj_godzine_trwania_spektaklu());
				dane[i][3] = new Integer(user.dajBaze().get(i).daj_minute_trwania_spektaklu());
				dane[i][4] = new String(user.dajBaze().get(i).daj_scene());
				dane[i][5] = false;
			}

			DefaultTableModel model = new DefaultTableModel(dane, naglowek);
			tabela1 = new JTable(model) {

				private static final long serialVersionUID = 1L;

				/*
				 * @Override public Class getColumnClass(int column) { return
				 * getValueAt(0, column).getClass(); }
				 */
				@Override // wy�aczenie mozliwosci edycji komorek
				public boolean isCellEditable(int row, int column) {
					
					if (column == 5) 
					{
						return true;
					} else {
						return false;
					}
				}

				@Override // potrzebne do checkboxow w tabeli
				public Class getColumnClass(int column) 
				{
					switch (column) 
					{
					case 0:
						return Integer.class;
					case 1:
						return String.class;
					case 2:
						return Integer.class;
					case 3:
						return Integer.class;
					case 4:
						return String.class;
					default:
						return Boolean.class;
					}
				}
			};

			tabela1.setBounds(0, 0, 640, 600);
			tabela1.setPreferredScrollableViewportSize(new Dimension(640, 600));
			tabela1.setFillsViewportHeight(true);
			tabela1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			tabela1.getColumnModel().getColumn(0).setPreferredWidth(30);
			tabela1.getColumnModel().getColumn(1).setPreferredWidth(430);
			tabela1.getColumnModel().getColumn(2).setPreferredWidth(50);
			tabela1.getColumnModel().getColumn(3).setPreferredWidth(50);
			tabela1.getColumnModel().getColumn(4).setPreferredWidth(50);
			tabela1.getColumnModel().getColumn(5).setPreferredWidth(20);

			tabela1.setRowHeight(20);
			DefaultTableCellRenderer centerRemderer = new DefaultTableCellRenderer();
			centerRemderer.setHorizontalAlignment(JLabel.CENTER);
			tabela1.getColumnModel().getColumn(4).setCellRenderer(centerRemderer);
			tabela1.getColumnModel().getColumn(3).setCellRenderer(centerRemderer);
			tabela1.getColumnModel().getColumn(2).setCellRenderer(centerRemderer);
			tabela1.getColumnModel().getColumn(0).setCellRenderer(centerRemderer);

			JScrollPane scrollpane = new JScrollPane(tabela1);
			scrollpane.setVisible(true);
			BazaSpektakli_1.add(scrollpane);

			bBazaSpektakli_DodajSpektakl = new JButton("Dodaj");
			bBazaSpektakli_DodajSpektakl.setBounds(0, 0, 150, 50);
			BazaSpektakli_2.add(bBazaSpektakli_DodajSpektakl);
			bBazaSpektakli_DodajSpektakl.addActionListener(this);

			bBazaEdytuj = new JButton("Edytuj");
			bBazaEdytuj.setBounds(0, 50, 150, 50);
			BazaSpektakli_2.add(bBazaEdytuj);
			bBazaEdytuj.addActionListener(this);

			bBazaUsun = new JButton("Usu\u0144");
			bBazaUsun.setBounds(0, 100, 150, 50);
			BazaSpektakli_2.add(bBazaUsun);
			bBazaUsun.addActionListener(this);

			bBazaWroc = new JButton("Wr\u00F3\u0107");
			bBazaWroc.setBounds(0, 550, 150, 50);
			BazaSpektakli_2.add(bBazaWroc);
			bBazaWroc.addActionListener(this);

			JFrOknoBazySpektakli.setVisible(true);
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null,""+e.toString());
		}

	}

	void dodaj_do_bazy_spektakli() 
	{
		try
		{

			JFrBazaSpektakli_DodajSpektakl = new JFrame("Dodaj");
			// blokada zmiany rozmiaru okna
			JFrBazaSpektakli_DodajSpektakl.setResizable(false);
			// setLayout(new FlowLayout(FlowLayout.CENTER, 2, 2));
			JFrBazaSpektakli_DodajSpektakl.setSize(565, 140);
			JFrBazaSpektakli_DodajSpektakl.setLayout(null);
			JFrBazaSpektakli_DodajSpektakl.setLocationRelativeTo(null);

			JOptionPane.showMessageDialog(null,
					"<html>Czas trwania spektaklu musi by\u0107 wprowadzony w pole godzina dla godzin, oraz pole minuta dla minut.<br/> Np 2,5h to godzina = 2, minuta = 30. Warto\u015Bci wprowadzane do p\u00F3l musza by\u0107 ca\u0142kowite.</html>");

			JLabel labelBazaNazwaSpektaklu = new JLabel("Nazwa spektaklu", SwingConstants.CENTER);
			labelBazaNazwaSpektaklu.setBounds(5, 5, 250, 50);
			labelBazaNazwaSpektaklu.setBorder(BorderFactory.createLineBorder(Color.black));
			JFrBazaSpektakli_DodajSpektakl.add(labelBazaNazwaSpektaklu);
			txtBazaNazwaSpektaklu = new JTextField();
			txtBazaNazwaSpektaklu.setBounds(5, 55, 250, 50);
			txtBazaNazwaSpektaklu.setBorder(BorderFactory.createLineBorder(Color.black));
			txtBazaNazwaSpektaklu.setHorizontalAlignment(JTextField.CENTER);
			JFrBazaSpektakli_DodajSpektakl.add(txtBazaNazwaSpektaklu);

			JLabel labelBazaGodz = new JLabel("Godz", SwingConstants.CENTER);
			labelBazaGodz.setBounds(255, 5, 50, 50);
			labelBazaGodz.setBorder(BorderFactory.createLineBorder(Color.black));
			JFrBazaSpektakli_DodajSpektakl.add(labelBazaGodz);
			txtBazaGodz = new JTextField();
			txtBazaGodz.setBounds(255, 55, 50, 50);
			txtBazaGodz.setBorder(BorderFactory.createLineBorder(Color.black));
			txtBazaGodz.setHorizontalAlignment(JTextField.CENTER);
			JFrBazaSpektakli_DodajSpektakl.add(txtBazaGodz);

			JLabel labelBazaMin = new JLabel("Min", SwingConstants.CENTER);
			labelBazaMin.setBounds(305, 5, 50, 50);
			labelBazaMin.setBorder(BorderFactory.createLineBorder(Color.black));
			JFrBazaSpektakli_DodajSpektakl.add(labelBazaMin);
			txtBazaMin = new JTextField();
			txtBazaMin.setBounds(305, 55, 50, 50);
			txtBazaMin.setBorder(BorderFactory.createLineBorder(Color.black));
			txtBazaMin.setHorizontalAlignment(JTextField.CENTER);
			JFrBazaSpektakli_DodajSpektakl.add(txtBazaMin);

			JLabel labelBazaScena = new JLabel("Scena", SwingConstants.CENTER);
			labelBazaScena.setBounds(355, 5, 100, 50);
			labelBazaScena.setBorder(BorderFactory.createLineBorder(Color.black));
			JFrBazaSpektakli_DodajSpektakl.add(labelBazaScena);
			String[] temp = { "SK", "SD" };
			combScena = new JComboBox<>(temp);
			combScena.setBounds(355, 55, 100, 50);
			combScena.setBorder(BorderFactory.createLineBorder(Color.black));
			((JLabel) combScena.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
			JFrBazaSpektakli_DodajSpektakl.add(combScena);

			bBazaSpektakli_DodajSpektakl2 = new JButton("Dodaj");
			bBazaSpektakli_DodajSpektakl2.setBounds(455, 5, 100, 100);
			JFrBazaSpektakli_DodajSpektakl.add(bBazaSpektakli_DodajSpektakl2);
			bBazaSpektakli_DodajSpektakl2.addActionListener(this);

			JFrBazaSpektakli_DodajSpektakl.setVisible(true);
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null,""+e.toString());
		}

	}

	void edytuj_baze()
	{
		try
		{
			int temp=tabela1.getRowCount();
			int zaznaczone = 0;
			for(int i=temp-1;i>=0;i--)
			{
				if(((boolean)tabela1.getValueAt(i, 5)))
				{
					zaznaczone++;
				}
			}
			if(zaznaczone==0){
				JOptionPane.showMessageDialog(null,"<html>Zaznacz spektakl kt\u00F3ry chcesz edytowa\u0107</html>");
			}
			if(zaznaczone!=0 && zaznaczone>1)
			{
				JOptionPane.showMessageDialog(null,"<html>Tylko jeden spektakl mo\u017Ce by\u0107 zaznaczony!</html>");
			}
			else
			{
				for(int i=temp-1;i>=0;i--)
				{
					
					if(((boolean) tabela1.getValueAt(i,5)))
					{	
						//System.out.println(user.dajBaze().size()+" : "+i);
						JFrChwilowa = new JFrame("Edycja");
						 //blokada zmiany rozmiaru okna
						JFrChwilowa.setResizable (false);
				       //setLayout(new FlowLayout(FlowLayout.CENTER, 2, 2));
						JFrChwilowa.setSize(470,90);
						JFrChwilowa.setLayout(null);
						JFrChwilowa.setLocationRelativeTo(null);  
						
						
						txtEdycjaNazwa = new JTextField(user.dajBaze().get(i).daj_nazwe_spektaklu());
						txtEdycjaNazwa.setBounds(5, 5,200,50);
						JFrChwilowa.getContentPane().add(txtEdycjaNazwa);
						
						txtEdycjaGodzina = new JTextField(""+user.dajBaze().get(i).daj_godzine_trwania_spektaklu());
						txtEdycjaGodzina.setBounds(205, 5,50,50);
						JFrChwilowa.getContentPane().add(txtEdycjaGodzina);
						
						txtEdycjaMinuta = new JTextField(""+user.dajBaze().get(i).daj_minute_trwania_spektaklu());
						txtEdycjaMinuta.setBounds(255, 5,50,50);
						JFrChwilowa.getContentPane().add(txtEdycjaMinuta);
						
						bBazaEdytujZapisz = new JButton("<html><center>Zapisz<br/>zmiany</center></html>");
						bBazaEdytujZapisz.setBounds(355,5,100,50);
						bBazaEdytujZapisz.addActionListener(this);
						JFrChwilowa.getContentPane().add(bBazaEdytujZapisz);
						
						
						String[] combTemp = new String[2]; 
						if((user.dajBaze().get(i).daj_scene()).equals("SK"))
						{
							combTemp[0]="SK";
							combTemp[1]="SD";
						}
						else
						{
							combTemp[1]="SK";
							combTemp[0]="SD";
						}
						
						TempcombScena = new JComboBox<>(combTemp);
						TempcombScena.setBounds(305,5, 50, 50);
						//((JLabel)combScena.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
						JFrChwilowa.getContentPane().add(TempcombScena);
						zmienna_indexowa_baza=i;
						
						

						temp=tabela1.getRowCount();
						
						
						JFrChwilowa.setVisible(true);

					}
				}

			}
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

		if (zrodlo == bBazaSpektakli_DodajSpektakl) 
		{
			try
			{
				dodaj_do_bazy_spektakli();
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(null,""+e.toString());
			}
			
		}

		if (zrodlo == bBazaSpektakli_DodajSpektakl2) 
		{
			try
			{
				if (txtBazaGodz.getText().contains(",") || txtBazaGodz.getText().contains(".")) 
				{
					JOptionPane.showMessageDialog(null, "<html>B\u0142\u0105D!! Pole ''Godzina'' zawiera , lub .</html>");
				}
				if (txtBazaMin.getText().contains(",") || txtBazaMin.getText().contains(".")) 
				{
					JOptionPane.showMessageDialog(null, "<html>B\u0142\u0105D!! Pole ''Minuta'' zawiera , lub .</html>");
				}
				if (Integer.parseInt(txtBazaMin.getText()) >= 0 && Integer.parseInt(txtBazaGodz.getText()) > 0) {
					if (Integer.parseInt(txtBazaMin.getText()) % 15 != 0) 
					{
						JOptionPane.showMessageDialog(null,
								"<html>B\u0142\u0105D!!<br/> Minuty mog\u0105 przybiera\u0107 jedynie	warto\u015Bci :00, 15, 30, 45</html>");
					}
					if ((!(txtBazaMin.getText().contains(",") || txtBazaMin.getText().contains("."))
							&& !(txtBazaGodz.getText().contains(",") || txtBazaGodz.getText().contains(".")))
							&& !(Integer.parseInt(txtBazaMin.getText()) % 15 != 0)) 
					{
						if (user.dodaj_spektakl(Integer.parseInt(txtBazaGodz.getText()),
								Integer.parseInt(txtBazaMin.getText()), txtBazaNazwaSpektaklu.getText(),
								(String) combScena.getSelectedItem())) 
						{
							JOptionPane.showMessageDialog(null, "<html>Spektakl: " + txtBazaNazwaSpektaklu.getText()
									+ " zosta\u0142 pomy\u015Blnie dodany.</html>");
							ZapisDoPliku zapis = new ZapisDoPliku();
							zapis.zapisz(user, "user", "dat");
							JFrOknoBazySpektakli.dispose();
							JFrBazaSpektakli_DodajSpektakl.dispose();
							wczytaj_elementy_okna();

						} else 
						{
							JOptionPane.showMessageDialog(null, "<html>Nie uda\u0142o si\u0119 doda\u0107 spektaklu"
									+ txtBazaNazwaSpektaklu.getText() + "!</html>");
						}
					}
				} 
				else 
				{
					if ((Integer.parseInt(txtBazaMin.getText()) < 0)) 
					{
						JOptionPane.showMessageDialog(null,
								"<html>B\u0142\u0105d!! Pole minuta musi zawiera\u0107 warto\u015Bci > lub = 0</html>");
					}
					if ((Integer.parseInt(txtBazaGodz.getText()) <= 0)) 
					{
						JOptionPane.showMessageDialog(null,
								"<html>B\u0142\u0105d!! Pole godzina musi zawiera\u0107 warto\u015Bci > od 0</html>");
					}
				}
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(null,""+e.toString());
			}
		}

		if (zrodlo == bBazaWroc) 
		{
			JFrOknoBazySpektakli.dispose();
		}

		if (zrodlo == bBazaUsun) 
		{
			
			try
			{
				int temp = tabela1.getRowCount();
				boolean stan = false; // czy zaznaczono jakas pozycje
				for (int i = temp - 1; i >= 0; i--) {
					if (((boolean) tabela1.getValueAt(i, 5))) 
					{
						stan = true;
						break;
					}
				}

				if (stan) // jesli jest zaznaczona jakas pozycja to wykonaj
				{
					for (int i = temp - 1; i >= 0; i--) 
					{

						if (((boolean) tabela1.getValueAt(i, 5))) 
						{
							user.dajBaze().remove(i);
							temp = tabela1.getRowCount();
						}
					}
					ZapisDoPliku zapis = new ZapisDoPliku();
					zapis.zapisz(user, "user", "dat");
					JFrOknoBazySpektakli.dispose();
					wczytaj_elementy_okna() ;
					JOptionPane.showMessageDialog(null, "<html>Usuni\u0119to wybrane spektakle.</html>");
				} 
				else // w przeciwnym wypadku wyswietl informacje
				{
					JOptionPane.showMessageDialog(null, "<html>Nie zaznaczono pozycji!</html>");
				}
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(null,""+e.toString());
			}
		}
		
		if(zrodlo==bBazaEdytuj)
		{
			try
			{
				edytuj_baze();
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(null,""+e.toString());
			}
		}
		
		if(zrodlo==bBazaEdytujZapisz)
		{
			try
			{
				boolean blad=false;
				
				if(!isNumeric(txtEdycjaMinuta.getText()))
				{
					blad=true;
				}
				if(!isNumeric(txtEdycjaGodzina.getText()))
				{
					blad=true;
				}
				
				if(!blad){
					if((txtEdycjaMinuta.getText().contains(",") || txtEdycjaMinuta.getText().contains(".")))
					{
						JOptionPane.showMessageDialog(null,"<html>B\u0142\u0105d!! Pole ''Minuta'' zawiera , lub .</html>");
					}
					if((txtEdycjaGodzina.getText().contains(",") || txtEdycjaGodzina.getText().contains(".")))
					{
						JOptionPane.showMessageDialog(null,"<html>B\u0142\u0105d!! Pole ''Godzina'' zawiera , lub .</html>");
					}
					else
					{
						if((Integer.parseInt(txtEdycjaMinuta.getText())>=0) && (Integer.parseInt(txtEdycjaGodzina.getText())>0))
							{
							if((Integer.parseInt(txtEdycjaMinuta.getText())%15!=0))
							{
								JOptionPane.showMessageDialog(null,"<html>B\u0142\u0105d!!<br/> Minuty mog\u0105 przybiera\u0107 jedynie	warto\u015Bci :00, 15, 30, 45</html>");
							}
							if(((!(txtEdycjaMinuta.getText().contains(",") || txtEdycjaMinuta.getText().contains("."))) && (!(txtEdycjaGodzina.getText().contains(",") || txtEdycjaGodzina.getText().contains(".")))) && !(Integer.parseInt(txtEdycjaMinuta.getText())%15!=0))
							{
								user.dajBaze().get(zmienna_indexowa_baza).ustaw_nazwe_spektaklu(txtEdycjaNazwa.getText());
								user.dajBaze().get(zmienna_indexowa_baza).ustaw_godzine_trwania(Integer.parseInt(txtEdycjaGodzina.getText()));
								user.dajBaze().get(zmienna_indexowa_baza).ustaw_minute_trwania(Integer.parseInt(txtEdycjaMinuta.getText()));
								//System.out.println(txtEdycjaMinuta.getText());
								user.dajBaze().get(zmienna_indexowa_baza).ustaw_scene((String)TempcombScena.getSelectedItem());
								ZapisDoPliku zapis = new ZapisDoPliku();
								if(zapis.zapisz(user,"user","dat"))
								{
									JFrOknoBazySpektakli.dispose();
									wczytaj_elementy_okna() ;
									JOptionPane.showMessageDialog(null,"<html>Uda\u0142o si\u0119 zapisa\u0107 zmiany.</html>");
									JFrChwilowa.dispose(); 
								}
								else
								{
									JOptionPane.showMessageDialog(null,"<html>!! Nie uda\u0142o si\u0119 zapisa\u0107 zmian!! </html>");
								}
							
							}
							
						}else
						{
							if((Integer.parseInt(txtEdycjaMinuta.getText())<0)){JOptionPane.showMessageDialog(null,"<html>B\u0142\u0105d!! Pole minuta musi zawiera\u0107 warto\u015Bci > lub = 0</html>");}
							if((Integer.parseInt(txtEdycjaGodzina.getText())<=0)){JOptionPane.showMessageDialog(null,"<html>B\u0142\u0105d!! Pole godzina musi zawiera\u0107 warto\u015Bci > od 0</html>");}
						}
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null,"<html>Podane warto\u015Bci zawieraja nieodpowiednie znaki</html>");
				}
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(null,""+e.toString());
			}
			
			
		}

	}

}
