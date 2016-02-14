package Tools;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import ArbreBriandais.Briandais;
import ArbreBriandais.MultiBuild;
import Interfaces.IBriandais;
import Interfaces.ITrieHybride;
import TrieHybrid.TrieHybride;


public class TestsEtude {


	private static IBriandais testBriandais;
	private static IBriandais testBriandaisMulti; 
	private static ITrieHybride testTrieHybride = new TrieHybride();
	private static ITrieHybride testEquilibredTrieHybride = new TrieHybride();
	
	public static long initializeBrandais(String[] words) {
		
		testBriandais = new Briandais(); 
		
		long start = System.currentTimeMillis();
		for (String w : words) { 
			testBriandais = (IBriandais) testBriandais.add(w);
		}
		long end = System.currentTimeMillis();
		return end - start;
	}
	public static long initializeTrieHybride(String[] words) {
		long start = System.currentTimeMillis();
		for (String w : words) {
			testTrieHybride = (ITrieHybride) testTrieHybride.add(w);
		}
		long end = System.currentTimeMillis();
		return end - start;
	}
	

	
	
	public static long addWordsBriandais(String[] words) {
		long sum = 0;
		for (String w : words) {
			long start = System.nanoTime();
			testBriandais = (IBriandais) testBriandais.add(w);
			long end = System.nanoTime();
			sum += end - start;
		}
		return sum / words.length;
	}
	
	
	public static long addWordsToTrieHybride(String[] words) {
		long sumTime = 0;
		for (String w : words) {
			long start = System.nanoTime();
			testTrieHybride = (ITrieHybride) testTrieHybride.add(w);
			long end = System.nanoTime();
			sumTime += end - start;
		}
		return sumTime / words.length;
	}
	
	
	public static long deleteWordsBriandais(String[] words){
		long sum = 0;
		for(String w : words){
			long start = System.nanoTime(); 
			testBriandais = (IBriandais) Briandais.delete(testBriandais, w); 
			long end = System.nanoTime(); 
			sum += end-start; 
		}
		
		return sum/words.length; 
	}
	
	
	public static long deleteWordsToTrieHybride(String[] words) {
		long sumTime = 0;
		for (String w : words) {
			long start = System.nanoTime();
			testTrieHybride = (ITrieHybride) testTrieHybride.delete(w);
			long end = System.nanoTime();
			sumTime += end - start;
		}
		return sumTime / words.length;
	}

	
	public static long searchWordBriandais(String[] words){
		long sum = 0; 
		for(String w : words){
			long start = System.nanoTime();
			testBriandais.search(w); 
			long end = System.nanoTime(); 
			sum +=end-start;
		}
		return sum/words.length; 
	}
	
	public static long searchWordsToTrieHybride(String[] words) {
		long sumTime = 0;
		for (String w : words) {
			long start = System.nanoTime();
			testTrieHybride.Recherche(w);
			long end = System.nanoTime();
			sumTime += end - start;
		}
		return sumTime / words.length;
	}


	
	public static void afficheAdd() throws IOException{
		String[] dirs = {"test0","test1","test3", "Shakespeare"};  
		long timeB, timeH; 
		int nbWords; 
		String filename = "data/timeAdd2"; 
		BufferedWriter bw =  new BufferedWriter(new FileWriter(new File(filename))); 
		initializeBrandais(FileRead.readWord(new File("test/exemple_de_base.txt"))); 
		initializeTrieHybride(FileRead.readWord(new File("test/exemple_de_base.txt")));
		
		for(int i=0; i<dirs.length; i++){

			String[] words = FileRead.readDirectory("test/"+dirs[i]); 
			nbWords = words.length; 	
			timeB = addWordsBriandais(words); 
			timeH = addWordsToTrieHybride(words); 
			
			bw.write(nbWords+"\t"+timeB+"\t"+timeH+"\n"); 
		}
		bw.flush(); 
		bw.close(); 
	}
	
	public static long multiThread(String dir) throws IOException, InterruptedException{
		
		File d = new File(dir);
		String[] files = d.list();
		int nbFile = files.length;  
		
		ArrayList<MultiBuild> threads = new ArrayList<MultiBuild>(); 
		
		for(int i =0; i<nbFile; i++){
			threads.add(new MultiBuild(FileRead.readWord(new File(dir+"/"+files[i])))); 	
		}
		
		long start = System.currentTimeMillis(); 
		
		for(int i =0; i<nbFile; i++){
			threads.get(i).start(); 		
		}
		
		for (int i=0; i<nbFile; i++){
			threads.get(i).join();			
		}
		long end = System.currentTimeMillis(); 
		
		testBriandaisMulti = MultiBuild.getFinal(); 
		
		return end - start ;  
	}
	
	
	
	public static void main(String[] args) {
		
		String dirName = "test/Shakespeare"; 
		
		try {
			System.out.println("Etude Experimentale pour l'arbre BRIANDAIS et le trie Hybride "); 
			System.out.println("------------------------------------------\n"); 
			
			String[] words = FileRead.readDirectory(dirName); 
 
			//String exemple = "a quel bonjour genial profeseur de dactylographie sommes nous redevables de la superbe phrase ci dessous un modele du genre que toute dactylo connait par coeur puisqu elle fait appel a chacune des touches du clavier de la machine a ecrire";
			String [] addWords = FileRead.readWord(new File ("test/Shakespeare/richardii.txt"));
			System.out.println("Nombres de mots => " +words.length+"\n"); 
			
			
			long timeB = initializeBrandais(words); 			
			System.out.println("Nombres de mots Arbre Briandais=> " +testBriandais.countWords()); 
			
			System.out.println("Temps de construction BRIANDAIS => "+timeB +" nanosecond"); 
			long timeA;
			timeA = initializeTrieHybride(words);		
			System.out.println("Nombres de mots Tries Hybride => " +testTrieHybride.countWords()); 
			System.out.println("Temps de construction du Trie Hybride " + timeA+ " nanosecond");

	
			
			System.out.println("------------------------------------------\n"); 
			
			timeB = addWordsBriandais(addWords); 
			System.out.println("Temps d'ajout d'un mots dans un BRIANDAIS => "+timeB +" nanosecond"); // ?
			
			timeA = addWordsToTrieHybride(addWords);
			System.out.println("Temps d'ajout d'un mots dans un Trie Hybride : "+timeA + " nanosec");

			
			System.out.println("------------------------------------------\n"); 
			
			timeB = deleteWordsBriandais(addWords); 
			System.out.println("Temps de suppression d'un mots dans un BRIANDAIS => "+timeB +" nanosecond"); //?
			
			timeA = deleteWordsToTrieHybride(addWords);
			System.out
					.println("Temps de suppression d'un mots dans un Trie Hybride : "
							+ timeA + " nanosec");

			
			
			System.out.println("------------------------------------------\n"); 
			
			timeB = searchWordBriandais(addWords); 
			System.out.println("Temps de recherche d'un mots dans un BRIANDAIS => "+timeB+" nanosecond"); //?
			
			timeA= searchWordsToTrieHybride(addWords);
			System.out
					.println("Temps de recherche d'un mots dans un Trie Hybride : "
							+ timeA + " nanosec");

			
			
			timeB = multiThread(dirName); 
			System.out.println("Temps de construction BRIANDAIS en paralele => "+timeB +" ms"); 
			
			afficheAdd(); 
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		


	}

}
