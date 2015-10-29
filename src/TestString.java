
public class TestString {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		String word = ""; 
		String word2 = "B";
		
		System.out.println("1 "+word.equals('\0')); 
		
		System.out.println("2 "+word.equals("")); 
		
		System.out.println("3 "+word2.substring(1).equals("")); 
		
		System.out.println("4 "+ (word.charAt(0) == '\0')); 
		
		System.out.println("5 "+ word.substring(1).equals(""));
	}

}
