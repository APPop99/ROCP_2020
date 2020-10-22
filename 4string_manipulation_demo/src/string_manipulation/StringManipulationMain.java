package string_manipulation;

public class StringManipulationMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String s="hello hi good morning I am enjoying programming with java";
		System.out.println("Input string: \n"+s);
				
		StringBuilder sb1=new StringBuilder();
		String ar[]=s.split(" ");
				
		for(String s1:ar) 
		{
			sb1.append(Character.toUpperCase(s1.charAt(0))).append(s1.substring(1)).append(" ");
		}
		System.out.println("\nEvery FIRST letter of every word is an Uppercase Character:");
		System.out.println(sb1.toString().trim());

//Task - Convert every word's last char to uppercase
				
		//transform every last letter of every word in Uppercase
		StringBuilder sb2=new StringBuilder();
		for(String word:ar) 
		{
			sb2.append(word.substring(0, word.length()-1));
			sb2.append(Character.toUpperCase(word.charAt(word.length()-1))).append(" ");
		}
		System.out.println("\nEvery LAST letter of every word is an Uppercase Character:");
		System.out.println(sb2.toString().trim());			
		//transform every first and last letter of every word in upper case		
		StringBuilder sb3=new StringBuilder();
		
		s = sb1.toString().trim();
		ar=s.split(" ");
		for(String word:ar) 
		{
			sb3.append(word.substring(0, word.length()-1));
			sb3.append(Character.toUpperCase(word.charAt(word.length()-1))).append(" ");
		}
		System.out.println("\nEvery FIRST and LAST letter of the word is an Uppercase Character:");
		System.out.println(sb3.toString().trim());		
	}

}
