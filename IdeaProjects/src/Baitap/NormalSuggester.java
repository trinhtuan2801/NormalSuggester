package Baitap;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;

public class NormalSuggester {
    private HashMap2D tags = new HashMap2D();

    NormalSuggester(String link){

        File file = new File(link);
        int index1 = -1, index2 = -1, j;
        String str = "", readstr;
        String[] s1 = new String[100];
        String[] s2 = new String[100];

        try {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(file), "UTF8"));
            readstr = in.readLine();
            while ((readstr = in.readLine()) != null) {
                String[] bigstr = readstr.split(" ");
                for (String i : bigstr) {
                    if (i.equals("2")) {
                        tags.addKey(str);
                        for (j=0; j<=index1; j++) {
                            tags.addValue(str, s1[j], Integer.parseInt(s2[j]));}
                        index1 = -1;
                        index2 = -1;
                        str = "";

                    } else {
                        if ((i.equals("1")) | (i.equals("0"))) {
                            index2 ++;
                            s2[index2] = i;
                        } else {
                            index1 ++;
                            s1[index1] = i;
                            str = str.concat(i + " ");
                        }
                    }
                }
            }
            in.close();
        }
        catch (UnsupportedEncodingException e)
        {
            System.out.println("unsupported");
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

    }

    public ArrayList<String> suggest(String sentence) {
        sentence = sentence.toLowerCase();
        System.out.println("Câu gốc là: " + sentence);
        ArrayList<String> result = new ArrayList<String>();
        for (String key1 : tags.getOuter().keySet()) {
            System.out.println(key1 + " " + tags.getOuter().get(key1));
            HashMap<String,Integer> inner = tags.getOuter().get(key1);
            boolean check = true;
            for (String key2 : inner.keySet()) {
                System.out.println(key2);
                if (inner.get(key2) == 1) check = sentence.contains(key2);
                System.out.println(check);
                if (check == false) break;
            }
            if (check == true) result.add(key1);
        }
        return result;
    }

}

