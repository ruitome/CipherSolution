import java.io.*;
import java.util.*;

public class Solution {

    private static final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static String removeDuplicatedChars(String keyword){

        int charLength = keyword.length();
        String modifiedKeyword="";
        for(int i=0;i<charLength;i++) {
            if(!modifiedKeyword.contains(keyword.charAt(i)+""))
                modifiedKeyword+=keyword.charAt(i);
        }
        return modifiedKeyword;
    }
    
    public static String match(String toSearch,String cipher){
        String plainText="";
        String toSearchNoSpaces = toSearch.replaceAll(" ", "");
        for(int i=0;i<toSearchNoSpaces.length();i++){
            int posAtCipher = cipher.indexOf(toSearchNoSpaces.charAt(i));
            plainText+=String.valueOf(alphabet.charAt(posAtCipher));
        }
        return plainText;
    }

    public static void main(String[] args) {
        int numberOfEntries=0;
        ArrayList<String> keywords;
        ArrayList<String> cipherText;
        keywords = new ArrayList<>();
        cipherText = new ArrayList<>();

        try{
            BufferedReader br = new BufferedReader( new InputStreamReader(System.in));
            
            numberOfEntries = Integer.parseInt(br.readLine());
            if(!(numberOfEntries > 0 && numberOfEntries < 11))
                throw new IOException("Number of Entries invalid!");

            for(int i=0;i<numberOfEntries;i++){
                String input = br.readLine();
                keywords.add(input);
                input = br.readLine();
                cipherText.add(input);
            }
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
        
        for(int i=0;i<numberOfEntries;i++){
            String actualKeyword = removeDuplicatedChars(keywords.get(i));
            int actualKeywordSize = actualKeyword.length();
            String attr=alphabet;
            for(int j=0;j<actualKeyword.length();j++){
                char actualElement = actualKeyword.charAt(j);    
                attr = attr.replaceAll(String.valueOf(actualElement),"");
            }
          
            String[] rows = new String[actualKeywordSize];
            
            for(int elemento=0;elemento<actualKeywordSize;elemento++){
              rows[elemento]=String.valueOf(actualKeyword.charAt(elemento));
              int pos=elemento;
              while(pos<attr.length()){
                  rows[elemento]=rows[elemento].concat(String.valueOf(attr.charAt(pos)));
                  pos+=actualKeywordSize;
              }
              
            }
           Arrays.sort(rows);
           String allTogetherNow="";
           for(int element=0;element<rows.length;element++){
               allTogetherNow+=(rows[element]);
           }
          String plainText="";
          String value = cipherText.get(i);
          plainText=match(value, allTogetherNow);
          int size = plainText.length();
          for(int k=0;k<size;k++,size=plainText.length())
              if(value.charAt(k)==' ')
                  plainText = plainText.substring(0, k) + " " + plainText.substring(k, size);
          System.out.println(plainText);  
           
        }
    }

}
