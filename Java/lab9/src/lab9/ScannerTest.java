package lab9;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
//import java.io.FileNotFoundException;

public class ScannerTest {

    public static void main(String[] args) {

        try{
            File myfile = new File("D:\\Documenti\\LPO_LAB\\Java\\lab9\\src\\lab9\\myFile.txt");
            Scanner sc = new Scanner (myfile).useDelimiter("(\\W+)|(\\s+)");
            wordCount cc = new wordCount(sc);
            cc.PrintMap();
            sc.close();
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }

    }

}