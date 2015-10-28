

public class ArbreBriandaisTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		ArbreBriandais ab1= new ArbreBriandais(); 
		ArbreBriandais ab2= new ArbreBriandais('\0', null, null); 
		ArbreBriandais ab3= new ArbreBriandais("\0");
		
		System.out.println("ab1.isEmpty() = "+ ab1.isEmpty()); 		
		System.out.println("ab2.isEmpty() = "+ ab2.isEmpty());
		System.out.println("ab3.isEmpty() = "+ ab3.isEmpty()); 
	}

}
