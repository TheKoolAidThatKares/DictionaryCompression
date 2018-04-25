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
	
	static String subString, subStringRemoved, input, inputSaver, bigStringSaver, smallStringSaver;
	
	static int length = 0;
    static int base = 36;
    static int saved = 0;
    
    static boolean done = false;
    
	public static void main(String[] args)
	{
        input = "jfp4qp893gegh9pw85hp89turuhkgsldjmvh59vhori4398j493tup984t5uhgopuyt9pq384gh9erhgp9q85utqp598tuqp598";
        inputSaver = input;
		do
		{
        longSubString(input, input.length());
		replace();
        System.out.println(input);
        newSubstrings.clear();
		}while(!done);
		System.out.println("You Saved: " + Integer.toString(inputSaver.length()-input.length()) + " Characters");
		
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
    	System.out.println("unique: " + uniqueSubstrings);
    	System.out.println("repeated: "+repeatedSubstrings); //all the strings that repeat
    	System.out.println(bigStringSaver); //longest repeated string
    }
    private static void shortSubString(String bigStringSaver, int n, int o)
    {
    	//System.out.println(Integer.toString(Integer.parseInt("303245", 10), base));
		String add;
    	for (int i = 0; true; i++)
    	{
    		add = Integer.toString(Integer.parseInt(Integer.toString(i), 10), base);
    		if(add.length() < 3)
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
			smallStringSaver = tempNewSubstrings.get(0);
    }
    private static void replace()
    {
    	System.out.println(bigStringSaver + smallStringSaver);
    	if(bigStringSaver.length() > smallStringSaver.length())
    		input = input.replaceAll(bigStringSaver, smallStringSaver); //Im worried that the replaceAll method may not be the best to use. Needs to be iterative
    	else
    	{
    		System.out.println("No Efficient Solution");
    		done = true;
    	}
    }
}
