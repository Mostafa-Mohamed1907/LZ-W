package assign_2_lz.w;

import java.io.BufferedReader;
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
public class DeCompress {

    static ArrayList<String> dec = new ArrayList<String>();
    static ArrayList<Integer> Tags = new ArrayList<Integer>();
   
    
    public static void DeComp() throws IOException {
        
         for (int i = 0; i < 128; ++i) {
            String temp = "";
            temp += (char) i;
            dec.add(temp);
        }
        String Name = "C:\\Users\\Omar\\Documents\\NetBeansProjects\\Assign_2_LZ-W\\tags.txt";
        File file = new File(Name);
        Scanner in = new Scanner(file);
        while(in.hasNext()){
            String t ;
            t = in.next();
             int u;
            u  = Integer.parseInt(t);
            Tags.add(u);
        }
        
        String orignalContent = "";
        String subContent;
        int current_location = 0;
        //current_location: بيحددلى انا واقف فين فى ال سترينج عشان اقدر احدد ال سبسترنج اللى هحطها فى الديكشنرى
        //current_location: انا هبتدى منين فى ال سترينج اخد الحروف عشان اشوف موجودة فى الديكشنرى ولا
        for(int i = 0; i < Tags.size(); i++)
        {
            // tag--> index
            //Handling: لازم يكون التاج موجود جوة الديكشنرى
            if(Tags.get(i) < dec.size())
            {
                //return element at this position
                orignalContent += dec.get(Tags.get(i));
                //بشوف ال سترينج موجود فى الديكشنرى ولا لا موجود زود علية حرف كمان وارجع شوف تان لغاية ما ملاقيش فبحطة فى الديكشنرى
                for(int x = current_location + 1; x <= orignalContent.length(); x++)
                {
                    subContent = orignalContent.substring(current_location, x);
                    //لو ال سب سترنج دة مش موجود فى الديكشنرى
                    if(dec.indexOf(subContent) == -1)
                    {
                        dec.add(subContent);
                        current_location += subContent.length()-1;
                        break;
                    }
                }
            }
            else
            {
                //لو جالى تاج ولسة مجاش فى الديكشنرى
                //بتخد التاج للى قبلية اوزود علية اول حرف فى التاج دة تانى
                //Concatenate ALL Symbols picked from Previous step and first Symbol picked from current step
                subContent = dec.get(Tags.get(i-1)) + orignalContent.charAt(current_location);
                dec.add(subContent);
                current_location += subContent.length()-1;
                orignalContent += subContent;
            }
        }
        System.out.println("The String line is: " + orignalContent); 
    }
}