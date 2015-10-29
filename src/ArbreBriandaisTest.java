

public class ArbreBriandaisTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		ArbreBriandais ab1= new ArbreBriandais(); 
		ArbreBriandais ab2= new ArbreBriandais('@', null, null); 
		ArbreBriandais ab3=  ArbreBriandais.arbreBriandais("");
		ArbreBriandais ab4 = new ArbreBriandais('B', null, null); 
		ArbreBriandais ab5 = ArbreBriandais.arbreBriandais("Bonjour"); 
		
		System.out.println("ab1.isEmpty() = "+ ab1.isEmpty()); 		
		System.out.println("ab2.isEmpty() = "+ ab2.isEmpty());
		System.out.println("ab3.isEmpty() = "+ ab3.isEmpty()); 
		System.out.println("ab4.isEmpty() = "+ ab4.isEmpty());
		System.out.println("ab5.isEmpty() = "+ ab5.isEmpty());
		
		System.out.println(ab5.toString()); 
	}

}
