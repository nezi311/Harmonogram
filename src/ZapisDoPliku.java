import java.io.*;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class ZapisDoPliku {

	public ZapisDoPliku(){

	}
	boolean zapisz(String dane){
		
		JFileChooser fs = new JFileChooser(new File("c:\\"));  // definiujemy nowy JFileChooser
		fs.setDialogTitle("Zapisz Plik");	// zmieniamy tytul okna JFileChoosera
		fs.setFileFilter(new FileTypeFilter(".html","Plik HTML")); // Tworzymy nowy filtr (z poprzednio utworzonej klasy)
		int result = fs.showSaveDialog(null); // gdy nacisniemy na save w JFileChooserze to :
		if (result == JFileChooser.APPROVE_OPTION)
		{
			String content = dane;	// Pobierze tresc z kontenera tekstu
			File fi = fs.getSelectedFile();	// Utworzy nowa sciezke do zapisu pliku

			try
			{
				FileWriter fw = new FileWriter(fi.getPath()+fs.getFileFilter().toString()); // deklaracja zapisu nowego pliku z podan¹ sciezka fi
				fw.write(content); // zapisuje podany text z kontenera   
				fw.flush();  // usuwa zapisana sciezke
				fw.close(); // zamykamy nasz plik
				return true;
			}
			catch(Exception e2)
			{
				JOptionPane.showMessageDialog(null,e2.getMessage());
				return false;
			}
		}
		return false;
		
	}
	boolean zapisz(Uzytkownik dane,String nazwa, String roz){
		try {
			
			FileOutputStream FOS = new FileOutputStream(nazwa+".dat");
			//System.out.println("Plik");
			ObjectOutputStream OOS = new ObjectOutputStream(FOS);
			//System.out.println("Obiekt");
			OOS.writeObject(dane);
			//System.out.println("Zapis");
			OOS.close();
			FOS.close();
			
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	
}
