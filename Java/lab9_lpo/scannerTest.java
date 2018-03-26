package lab9_lpo;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
//import java.io.FileNotFoundException;

public class scannerTest {

	public static void main(String[] args) {
		
		 try{
			 File myfile = new File("C:\\Users\\alexs\\workspace\\lab9\\src\\lab9_lpo\\myFile.txt");
			 Scanner sc = new Scanner (myfile).useDelimiter("(\\W+)|(\\s+)");
			 wordCount cc = new wordCount(sc);
			 cc.PrintMap();
			 sc.close();
		 }catch(FileNotFoundException e){
			e.printStackTrace();
		 }
		
	}

}
