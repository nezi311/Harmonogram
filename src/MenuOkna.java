import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JSeparator;

public class MenuOkna extends JMenuBar
{
	JMenu Opcje = new JMenu("Opcje");
	JMenuItem Statystyki = new JMenuItem("Statystyki");
	JMenuItem Wypelnij_Baze = new JMenuItem("Wypelnij baze");
	JMenuItem Zmien_kolor_przyciskow = new JMenuItem("Zmie\u0144 kolor przycisk\u00F3w");
	
    JMenu Info = new JMenu("Info");
    JMenuItem autor = new JMenuItem("Autor");
    
       
    public MenuOkna()
    {
        //menu Plik
       // Opcje.add(Zmien_kolor_tla);
       // Opcje.add(Zmien_kolor_napisow);   
       // Opcje.add(Zmien_kolor_przyciskow);  
        //linia oddzielaj¹ca JMenuItem        
        //Opcje.add(new JSeparator());        
        //Opcje.add(zakoncz);    
    	
    	Opcje.add(Wypelnij_Baze);
    	Opcje.add(Statystyki);
        add(Opcje);  
    	
    	Info.add(autor);
    	add(Info);

       
    }
}
