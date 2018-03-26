package lab9_lpo;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
//import java.io.FileNotFoundException;

public class scannerTest {

	public static void main(String[] args) {
		
		 try{
			 Scanner sc = new Scanner (new File("C:\\Users\\alexs\\workspace\\lab9\\src\\lab9_lpo\\myFile.txt"));
			 wordCount cc = new wordCount(sc);
			 cc.PrintMap();
		 }catch(FileNotFoundException e){
			e.printStackTrace();
		}
		
	}

}