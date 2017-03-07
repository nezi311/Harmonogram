import java.io.File;
import javax.swing.filechooser.*;
public class FileTypeFilter extends FileFilter{
	// Tworzymy nasz wlasny filtr
	
	private final String extension; // rozszerzenie
	private final String description; // opis
	
	public FileTypeFilter(String extension, String description)
	{
		this.extension=extension;
		this.description=description;
	}
	
	@Override
	public boolean accept(File file) // akceptujemy 
	{
		if(file.isDirectory())	// jesli sciezka istnieje zwracamy true
		{
			return true;
		}
		return file.getName().endsWith(extension); // jesli nie zwracamy nazwe pliku z rozszerzenem
	}

	@Override
	public String getDescription() { // zwraca opis
		return description + String.format(" (*%s)",extension);
	}
	
	public String toString()
	{
		return ""+extension+"";
	}

}
