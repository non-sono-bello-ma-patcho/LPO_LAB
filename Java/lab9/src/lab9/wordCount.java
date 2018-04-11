package lab9;
import java.util.Scanner;
//import java.io.FileNotFoundException;
import java.util.HashMap;

public class wordCount {
    private Scanner sc;
    private HashMap<String,Integer> hs;

    public wordCount(Scanner scanner){
        sc = scanner;
        hs = new HashMap<String,Integer>();
        HashBuilder();
    }

    private void HashBuilder(){
        while(this.sc.hasNext()){
            if(hs.isEmpty())
                hs.put(sc.next(),1 );
            else{
                String temp = sc.next();
                if(hs.containsKey(temp))
                    hs.put(temp, hs.get(temp)+1);
                else
                    hs.put(temp, 1);
            }
        }
    }

    public void PrintMap(){
        for (String  name: hs.keySet()){

            String key =name.toString();
            String value = hs.get(name).toString();
            System.out.println(key + " " + value);
        }
    }

}
