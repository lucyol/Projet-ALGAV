package ArbreBriandais;

import Interfaces.IBriandais;



public class ArbreBriandaisTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		String ex = "a quel genial profeseur de dactylographie sommes nous redevables de la superbe phrase ci dessous un modele du genre que toute dactylo connait par coeur puisqu elle fait appel a chacune des touches du clavier de la machine a ecrire"; 
		String[] chaineEx= ex.split(" "); 
		IBriandais ab = new Briandais(); 
		
		int nbMots= chaineEx.length;  
		System.out.println("Nb mots dans la chaine = "+nbMots); 
		
		for(String word : chaineEx){
			ab = (IBriandais) ab.add(word); 
		}
		
		System.out.println(ab.isEmpty()); 
		System.out.println("Nb mots dans ab = "+ab.countWords()); 
		System.out.println(ab.listWords().toString()); 
		System.out.println(ab.search("dactylo")); 
		System.out.println(ab.search("ecrit")); 
		System.out.println("Nb pointeurs null = "+ab.countNull()); 
		System.out.println("Hauteur de ab = "+ab.height()); 
		System.out.println("Nb de mots ayant pour prefixe da = "+ ab.countPrefix("da"));
		
	}

}
