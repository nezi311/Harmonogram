import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;

public class Autor extends JFrame {




	public Autor() {
		super("Informacje");
		//ustawienie standardowej akcji po nacisnieciu przycisku zamkniecia
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //blokada zmiany rozmiaru okna
        setResizable (false);
        //setLayout(new FlowLayout(FlowLayout.CENTER, 2, 2));
        //wyswietlenie naszej ramki
        
        setSize(446,163);
        getContentPane().setLayout(null);
        
        JLabel lblAutor = new JLabel("Autor : Dawid Dominiak");
        lblAutor.setFont(new Font("Serif", Font.PLAIN, 20));
        lblAutor.setBounds(236, 11, 198, 27);
        getContentPane().add(lblAutor);
        
        JLabel lblNewLabel = new JLabel("Wersja Programu :  02112016");
        lblNewLabel.setFont(new Font("Serif", Font.PLAIN, 20));
        lblNewLabel.setBounds(195, 41, 239, 27);
        getContentPane().add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("Email : dawid.dominiak.94@gmail.com");
        lblNewLabel_1.setFont(new Font("Serif", Font.PLAIN, 20));
        lblNewLabel_1.setBounds(118, 71, 316, 27);
        getContentPane().add(lblNewLabel_1);
        
        JLabel lblNewLabel_2 = new JLabel("<html>\u00A9 Copyright 2016. All rights reserved.</html>");
        lblNewLabel_2.setFont(new Font("Serif", Font.PLAIN, 20));
        lblNewLabel_2.setBounds(127, 98, 307, 30);
        getContentPane().add(lblNewLabel_2);
        //wysrodkowanie ramki
        setLocationRelativeTo(null);  
        setVisible(true);
		
	
	}
}
