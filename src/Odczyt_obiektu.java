import java.io.*;

public class Odczyt_obiektu {
	public Odczyt_obiektu(){
	}
	
	
	Uzytkownik Odczytaj(String nazwa){
		try{
        FileInputStream FIS= new FileInputStream(nazwa+".dat");
        ObjectInputStream OIS = new ObjectInputStream(FIS);
		try{
			
			Object x = OIS.readObject();
			
			OIS.close();
			FIS.close();
			
			return (Uzytkownik) x;
		}catch(EOFException e){
			
			return null;
		}
		}catch(Exception e){
			return null;
		}
		
		
	}
}
