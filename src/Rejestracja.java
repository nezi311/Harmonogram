import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;


public class Rejestracja extends JFrame implements ActionListener{
	private JPanel panel;
	private JTextField imie, nazwisko;
	private JButton bDodaj;
	private String imieS, nazwiskoS;
	private ZapisDoPliku zapis;
	private boolean flaga;
	public Rejestracja(){
		super("Tworzenie uzytkownika");
		this.flaga=false;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		setSize(400, 300);
		setLayout(null);
		setLocationRelativeTo(null);
		zapis = new ZapisDoPliku();
		tworzenie();
		
		
	}
	boolean flaga(){return flaga;}
	void tworzenie(){
		panel = new JPanel();
		panel.setVisible(false);
		panel.setLayout(null);
		panel.setBounds(0, 0, 400, 300);
		add(panel);
		
		imie = new JTextField("Imie");
		imie.setBounds(5,5,385,50);
		panel.add(imie);
		//imie.setText("chuje");
		
		nazwisko = new JTextField("Nazwisko");
		nazwisko.setBounds(5,60,385,50);
		panel.add(nazwisko);
		
		bDodaj = new JButton("Dodaj");
		bDodaj.setBounds(5, 125, 385, 140);
		panel.add(bDodaj);
		bDodaj.addActionListener(this);
		
		panel.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object zrodlo = e.getSource();
		
		if(zrodlo == bDodaj){
			
			
			Uzytkownik user = new Uzytkownik(imie.getText(), nazwisko.getText());
			zapis.zapisz(user,"user","dat");
			flaga=true;
			System.exit(0);
			
		}
		
	}
	
	
	

}// koniec 
