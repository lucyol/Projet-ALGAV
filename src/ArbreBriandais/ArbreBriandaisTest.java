package ArbreBriandais;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;

import Interfaces.IBriandais;



public class ArbreBriandaisTest {

	/**
	 * @param args
	 */

	public static void main(String[] args) {
		
		String ex = "A quel genial profeseur de dactylographie sommes nous redevables de la superbe phrase ci dessous un modele du genre que toute dactylo connait par coeur puisqu elle fait appel a chacune des touches du clavier de la machine a ecrire"; 
		String[] chaineEx= ex.split(" "); 
		IBriandais ab = new Briandais(); 
		IBriandais ab2 = new Briandais("marie"); 
		IBriandais ab3 = new Briandais("maritime"); 
		IBriandais ab5 = new Briandais("au"); 
		ab5 = (IBriandais) ab5.add("ba"); 
		ab5 = (IBriandais) ab5.add("cas"); 
		ab5 = (IBriandais) ab5.add("dos"); 
		
		int nbMots= chaineEx.length;  
		System.out.println("Nb mots dans la chaine = "+nbMots); 
		
		for(String word : chaineEx){
			ab = (IBriandais) ab.add(word); 
		}
		
		System.out.println(ab.isEmpty()); 
		System.out.println("Nb mots dans ab = "+ab.countWords()); 
		System.out.println(ab.listWords().toString()); 
		System.out.println("Recherche du mot \"dactylo\" => "+ ab.search("dactylo")); 
		System.out.println("Recherche du mot \"ecrit\" => "+ab.search("ecrit")); 
		System.out.println("Nb pointeurs null = "+ab.countNull()); 
		System.out.println("Hauteur de ab = "+ab.height()); 
		System.out.println("Profondeur moyenne des feuilles de l'arbre = "+ab.averageDepth()); 
		System.out.println("Nb de mots ayant pour prefixe da = "+ ab.countPrefix("da"));
		ab = (IBriandais) Briandais.delete(ab, "clavier"); 
		System.out.println("Suppresion du mot \"clavier\" => " +(!ab.search("clavier"))); 
		
		ab3 = (IBriandais) ab3.add("chaussure"); 
		
		ab2 = Briandais.fusion(ab2, ab3); 
		
		System.out.println("Fusion ab2, ab3 =>" +ab2.listWords()); 
		File file = new File("data/plotBriandais");
		try {
			PrintStream printStream = new PrintStream(file);
			System.setOut(printStream);
			ab.plotBriandais(); 
	        	} catch (FileNotFoundException e) {
	        		e.printStackTrace();
	    } 				
	}	
	
}
