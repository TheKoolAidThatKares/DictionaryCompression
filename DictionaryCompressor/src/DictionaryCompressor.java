import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

public class DictionaryCompressor 
{
	static ArrayList<String> tempNewSubstrings = new ArrayList<String>();		//Substrings that dont exist within input.
	static ArrayList<String> uniqueSubstrings = new ArrayList<String>();	//Substrings that exist once within input.
	static ArrayList<String> repeatedSubstrings = new ArrayList<String>();  //Substrings that repeat within input.
	static ArrayList<String> dictionary = new ArrayList<String>();
	//static ArrayList<Integer> lengthOfSubstrings = new ArrayList<Integer>();//Lengths of repeated substrings.
	
	
	static String[] baseInfo = {"0","1","2","3","4","5","6","7","8","9","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P"};
	
	static String subString, subStringRemoved, input, inputSaver, bigStringSaver, smallStringSaver;
	
	static int length = 0;
    static int base = 2;
    static int saved = 0;
    static int size = 3;
    
    static boolean done = false;
    
	public static void main(String[] args)
	{
        input = "11111111111000000000000";
        inputSaver = input;
        size = 3;
		do
		{
        longSubString(input, input.length());
		replace();
        System.out.println(input);
        tempNewSubstrings.clear();
        uniqueSubstrings.clear();
        repeatedSubstrings.clear();
        subString = "";
        subStringRemoved = "";
        bigStringSaver = "";
        smallStringSaver = "";
		}while(!done);
		System.out.println("You Saved: " + Integer.toString(inputSaver.length()-input.length()) + " Characters");
		System.out.println(dictionary.toString());
		
	}
    private static void longSubString(String str, int n)
    {
    	bigStringSaver = "";
    	for (int i = 0; i < n; i++) 
    		for (int j = i+1; j <= n; j++)
    		{
        	   subString=str.substring(i,j);
        	   subStringRemoved=str.replaceFirst(subString, "");

        	   if(str.substring(0, i).contains(subString)||str.substring(j, str.length()).contains(subString))
        	   {
        		   repeatedSubstrings.add(subString);
        		   if(subString.length() > bigStringSaver.length())
        		   		bigStringSaver = subString;
        	   }
    		   uniqueSubstrings.add(subString);
    		}
    	//tempNewSubstrings.add("");
    	shortSubString(bigStringSaver, n, bigStringSaver.length());
    	System.out.println("new: " + tempNewSubstrings);
    	System.out.println("unique: " + uniqueSubstrings);
    	System.out.println("repeated: " + repeatedSubstrings); //all the strings that repeat
    	System.out.println(bigStringSaver); //longest repeated string
    }
    private static void shortSubString(String bigStringSaver, int n, int o)
    {
    	//System.out.println(Integer.toString(Integer.parseInt("303245", 10), base));
		String add;
    	for (int i = 0; true; i++)
    	{
    		add = Integer.toString(Integer.parseInt(Integer.toString(i), 10), base);
    		if(add.length() < size)
    			tempNewSubstrings.add(add);
    		else
    		{
    			break;
    		}
    	}
		int k = tempNewSubstrings.size();
		for(int j = 0; j < k; j++)
		{
			add = "0" + tempNewSubstrings.get(j);
			System.out.println(add);
			if(add.length() < size)
				tempNewSubstrings.add(add);
			else
				break;
		}
    	System.out.println("tempnew 1 : "+tempNewSubstrings);
    	for( int j = 0; j < uniqueSubstrings.size(); j++)
    		for (int i = 0; i < tempNewSubstrings.size(); i++)
    			if(uniqueSubstrings.get(j).contains(tempNewSubstrings.get(i)))
    			{
    				tempNewSubstrings.remove(i);
    				i = i-1;
    			}
		System.out.println("tempnew 2 : "+tempNewSubstrings);
		if(tempNewSubstrings.size()>0)
			smallStringSaver = tempNewSubstrings.get(0);
		else
		{
			System.out.println("No Efficient Solution");
			done = true;
		}
    }
    private static void replace()
    {
    	System.out.println("Big: " + bigStringSaver + " Small: " + smallStringSaver);
    	if(done == false)
    	if(bigStringSaver.length() > smallStringSaver.length())
    	{
    		input = input.replaceAll(bigStringSaver, smallStringSaver); //Im worried that the replaceAll method may not be the best to use. Needs to be iterative
    		dictionary.add(smallStringSaver + "-" + bigStringSaver);
    	}
    		else
    	{
    		System.out.println("No Efficient Solution");
    		done = true;
    	}
    }
}
