package ArbreBriandais;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

import Interfaces.ITrieHybride;
import Interfaces.IBriandais; 
import Tools.FileRead;


public class MainBriandais {


	public static void main(String[] args) {

		String ex = "A quel genial profeseur de dactylographie sommes nous redevables de la superbe phrase ci dessous un modele du genre que toute dactylo connait par coeur puisqu elle fait appel a chacune des touches du clavier de la machine a ecrire"; 
		String[] chaineEx= ex.split(" ");  
		IBriandais ab = null; 
		int choix=1; 
		while (choix != 0) {
			Scanner sc = new Scanner(System.in);
			System.out
			.println("\n\n------------------------------------\n"
					+ "OPTIONS : \n"
					+ "--> 0. Quitter\n"
					+ "--> 1. Initialiser un Briandais avec l'exemple de base\n"
					+ "--> 2. Initialiser un Briandais a partir d'un fichier\n"
					+ "--> 3. L'arbre courrant est-il vide ?\n"
					+ "--> 4. Afficher le nombre de mot dans l'arbre\n"
					+ "--> 5. Afficher la liste des mots en ordre alphab�tique\n"
					+ "--> 6. Afficher le nombre de pointeurs vers Nil\n"
					+ "--> 7. Afficher l'hauteur de l'arbre\n"
					+ "--> 8. Afficher la profondeur moyenne\n"
					+ "--> 9. Afficher le nombre de mot ayant le meme prefixe\n"
					+ "--> 10. Rechercher un mot dans l'arbre\n"
					+ "--> 11. Supprimer un mot de l'arbre\n "
					+ "--> 12. Ajouter un mot dans l'arbre\n"
					+ "--> 13. Fusioner l'arbre courant avec un autre arbre a partir d'un fichier\n"
					+ "--> 14. Creer le fichier de donnees pour afficher l'arbre grace a GNUPLOT\n");

			choix = sc.nextInt();
			switch (choix) {
			case 1:
				ab = new Briandais(); 
				for(String w : chaineEx){
					ab = (IBriandais) ab.add(w); 
				}
				break;
			case 2:
				ab=new Briandais(); 
				Scanner sc2 = new Scanner(System.in); 
				System.out.println("\t Nom du fichier : "); 
				String fileName = sc2.nextLine(); 
				String[] words;
				try {
					words = FileRead.readWord(new File(fileName));

					for(String w : words){
						ab = (IBriandais) ab.add(w); 
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				break;
			case 3:
				System.out.println("L'arbre est vide : "+ab.isEmpty()); 
				break;
			case 4: 
				System.out.println("Nombre de mots dans l'arbre : "+ab.countWords()); 
				break;
			case 5:
				System.out.println("La liste des mots en ordre alphabetique :"
						+ ab.listWords());
				break;
			case 6:
				System.out.println("Nombre de pointeurs null : "+ab.countNull());
				break;
			case 7:
				System.out.println("Hauteur de l'arbre: "+ ab.height());
				break;

			case 8:
				System.out.println("Profondeur moyenne des feuilles : "+ab.averageDepth());
				break;
			case 9:
				Scanner word2 = new Scanner(System.in);
				System.out.println("\t Choisir le prefixe : ");
				String w2 = word2.nextLine();
				System.out.println("Il y'a " + ab.countPrefix(w2)
						+ " mots avec le prefixe \"" + w2+"\"");
				break;
			case 10:
				Scanner word1 = new Scanner(System.in);
				System.out
				.println("\t Taper le mot a rechercher : ");
				String w1 = word1.nextLine();
				System.out.println("Le mot \"" + w1 + "\" est-il present dans l'arbre ? " + ab.search(w1));
				break;

			case 11: 
				Scanner word3 = new Scanner(System.in);
				System.out
				.println("\t Taper le mot a supprimer : ");
				String w3 = word3.nextLine();
				if(!ab.search(w3)){
					System.out.println("Le mot \""+w3+"\" n'est pas present dans l'arbre"); 
					break;
				}else{
					ab= Briandais.delete(ab, w3); 
					if(!ab.search(w3)){
						System.out.println("Le mot \""+w3+"\" a ete supprime"); 
						break; 
					}else{
						System.out.println("Le mot \""+w3+"\" n'a pas ete supprime"); 
					}
				}
			case 12: 
				Scanner word4 = new Scanner(System.in);
				System.out.println("\t Taper le mot a ajouter : ");
				String w4 =word4.nextLine(); 
				ab = (IBriandais) ab.add(w4); 
				System.out.println("Le mot \""+w4+"\" a ete ajoute dans l'arbre"); 
				break; 
				
			case 13: 
				Scanner sc5 = new Scanner(System.in); 
				System.out.println("\t Nom du fichier : "); 
				String fileName2 = sc5.nextLine(); 
				String[] words2;
				IBriandais ab2 = new Briandais(); 
				try {
					words2 = FileRead.readWord(new File(fileName2));

					for(String w : words2){
						ab2 = (IBriandais) ab2.add(w); 
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				ab = Briandais.fusion(ab, ab2); 
				break; 
			case 14: 
				File file = new File("data/plotBriandais");
				try {
					PrintStream printStream = new PrintStream(file);
					System.setOut(printStream);
					ab.plotBriandais(); 
					printStream.close();
			        	} catch (FileNotFoundException e) {
			        		e.printStackTrace();
			    } 		

			case 0: 
				System.out.println("Au revoir!"); 
				break; 
			default:
				System.out.println("Vous n'avez rien choisi!");
				break; 
			}
		}
	}
}
