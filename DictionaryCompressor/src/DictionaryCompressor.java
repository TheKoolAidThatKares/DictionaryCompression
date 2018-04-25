import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

public class DictionaryCompressor 
{
	static ArrayList<String> newSubstrings = new ArrayList<String>();		//Substrings that dont exist within input.
	static ArrayList<String> tempNewSubstrings = new ArrayList<String>();		//Substrings that dont exist within input.
	static ArrayList<String> uniqueSubstrings = new ArrayList<String>();	//Substrings that exist once within input.
	static ArrayList<String> tempUniqueSubstrings = new ArrayList<String>();	//Substrings that exist once within input.
	static ArrayList<String> repeatedSubstrings = new ArrayList<String>();  //Substrings that repeat within input.
	//static ArrayList<Integer> lengthOfSubstrings = new ArrayList<Integer>();//Lengths of repeated substrings.
	
	
	static String[] baseInfo = {"0","1","2","3","4","5","6","7","8","9","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P"};
	
	static String subString;
	static String subStringRemoved;
	
	static int length = 0;
    static int base = 18;
    static int saved = 0;
	public static void main(String[] args)
	{
        String str = "ababcdefg";
        longSubString(str, str.length());
	}
    private static void longSubString(String str, int n)
    {
    	String stringSaver = "";
    	for (int i = 0; i < n; i++) 
    		for (int j = i+1; j <= n; j++)
    		{
        	   subString=str.substring(i,j);
        	   subStringRemoved=str.replaceFirst(subString, "");

        	   if(str.substring(0, i).contains(subString)||str.substring(j, str.length()).contains(subString))
        	   {
        		   repeatedSubstrings.add(subString);
        		   if(subString.length() > stringSaver.length())
        		   		stringSaver = subString;
        	   }
    		   uniqueSubstrings.add(subString);
    		}
    	//tempNewSubstrings.add("");
    	shortSubString(stringSaver, n, stringSaver.length());
    	System.out.println(uniqueSubstrings);
    	System.out.println(repeatedSubstrings);
    	System.out.println(stringSaver);

    }
    private static void shortSubString(String stringSaver, int n, int o)
    {
    	System.out.println(Integer.toString(Integer.parseInt("303245", 10), base));
    	for (int i = 0; true; i++)
    	{
    		tempNewSubstrings.add(Integer.toString(Integer.parseInt(Integer.toString(i), 10), base));
    		if(tempNewSubstrings.get(i).length() >= 3)
    			break;
    	}
    	System.out.println(tempNewSubstrings);
    	for( int j = 0; j < uniqueSubstrings.size(); j++)
    	for (int i = 0; i < tempNewSubstrings.size(); i++)
    		if(uniqueSubstrings.get(j).contains(tempNewSubstrings.get(i)))
    		{
    			tempNewSubstrings.remove(i);
    			i = i-1;
    		}
		System.out.println(tempNewSubstrings);
    }
}
