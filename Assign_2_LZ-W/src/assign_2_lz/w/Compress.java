package assign_2_lz.w;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.Scanner;
import java.util.ArrayList;

/**
 *
 * @author Omar
 */
public class Compress {

 static ArrayList<String> dec;
    //ABAABABBAABAABAAAABABBBBBBBB
    
    public static void Comp(String x) throws IOException {
        dec = new ArrayList<String>();
        for (int i = 0; i < 128; ++i) {
            String temp = "";
            temp += (char) i;
            dec.add(temp);
        }
        ArrayList<Integer> Tags;
        Tags = new ArrayList<>();
        String Cuurent = "";
        String privious = "";
        privious += x.charAt(0);
        for (int i = 1; i < x.length(); i++) 
        {
            Cuurent += x.charAt(i);
            //ex: AB-> Not in the dec
            if(!dec.contains(privious + Cuurent))
            {
                dec.add(privious + Cuurent);
                Tags.add(dec.indexOf(privious));
                // Handling--> AB--> 65, 66
                if(i == x.length()-1)
                {
                    Tags.add(dec.indexOf(Cuurent));
                }
                privious = Cuurent;
                Cuurent = "";
            }
            //ex: AB-> in the dec
            else
            {
                privious += Cuurent;
                Cuurent = "";
                //Handling--> 
                if(i == x.length()-1)
                {
                    Tags.add(dec.indexOf(privious));
                }
            }
        }
        //Handling-> A "Only" 
        if(x.length() == 1)
            Tags.add(dec.indexOf(x));

        String Name = "C:\\Users\\Omar\\Documents\\NetBeansProjects\\Assign_2_LZ-W\\tags.txt";
        File file = new File(Name);
        FileWriter FWW = new FileWriter(file);
        for(int i = 0 ; i<Tags.size() ; i++){
            FWW.write(String.valueOf(Tags.get(i)));
            FWW.write(" ");
        }
        FWW.close();
       // return Tags;
    }
   
    
// read from file 
    public static ArrayList<Integer> Read_from_file() throws IOException{
        FileReader f = new FileReader("Tags.txt");
        ArrayList Tags = new ArrayList();
        int c;
        while((c = f.read()) != -1) 
        {
            Tags.add(c);
        }
        f.close();
        return Tags;
    }
}