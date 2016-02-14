package TrieHybrid;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

import ArbreBriandais.Briandais;
import Interfaces.IBriandais;
import Interfaces.ITrieHybride;
import Tools.FileRead;

public class MainTrieHybride {

	public static void main(String[] args) {

		ITrieHybride trie = null; 
		int choix=1; 
		while (choix !=0) {
			
			Scanner sc = new Scanner(System.in);
			System.out
					.println("\n\n------------------------------------\n"
							+ "Que souhaitez vous faire? : \n"
							+ "--> 0. Quitter\n"
							+ "--> 1. Initialiser un TrieHybride (equilibre) a partir d'un fichier\n"
							+ "--> 2. L'arbre est t'il equilibre?\n"
							+ "--> 3. Rechercher un mot dans le TrieHybrid\n"
							+ "--> 4. Afficher le nombre total de mot dans le TrieHybrid\n"
							+ "--> 5. Afficher la liste des mots en ordre alphabetique\n"
							+ "--> 6. Afficher le nombre de pointeurs vers Nil\n"
							+ "--> 7. Afficher la hauteur du TrieHybrid\n"
							+ "--> 8. Afficher la profondeur moyenne\n"
							+ "--> 9. Afficher le nombre de mots ayant le meme prefixe\n"
							+ "--> 10. Recherche un mot et le supprimer du TrieHybrid\n"
							+ "--> 11. Creer le fichier de donnees pour afficher le trie avec GNUPLOT\n");

			choix = sc.nextInt();
			switch (choix) {
			case 1:
				trie = new TrieHybride(); 
				Scanner sc2 = new Scanner(System.in); 
				System.out.println("\t Nom du fichier : "); 
				String fileName = sc2.nextLine(); 
				String[] words;
				try {
					words = FileRead.readWord(new File(fileName));

					for(String w : words){
						trie = TrieHybride.ajoutEquillibre(trie, w); 
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				break;
			case 2:
				System.out.println("--> L'arbre est-t-il equilibre? : "
						+ (TrieHybride.isEquilibre(trie) ? " OUI " : "NON"));
				break;
			case 3:
				Scanner word = new Scanner(System.in);
				System.out.println(" Tapez le mot que vous chercher : ");
				String w = word.nextLine();
				System.out.println("Le mot \"" + w + "\" a-t-il ete trouve ? :" + (trie.search(w)? " OUI " : "NON"));
				break;
			case 4:
				System.out
						.println("--> Le nombre total de mots dans le trie hybride/ hybride equilibre est : "
								+ trie.countWords() + " / " + "");

				break;
			case 5:
				System.out.println("La liste des mots en ordre alphabetique :"
						+ trie.listWords());
				break;
			case 6:
				System.out.println("--> Nombre de pointeurs null : "+trie.countNull());
				break;
			case 7:
				System.out.println("--> Hauteur du trie Hybride : "
						+ trie.height());
				break;

			case 8:
				System.out.println("--> Profondeur Moyenne du Trie : "+trie.averageDepth());
				break;
			case 9:
				Scanner word2 = new Scanner(System.in);
				System.out.println(" choisir le prefixe  ! ");
				String w2 = word2.nextLine();
				System.out.println("Il y'a " + trie.countPrefix(w2)
						+ " mots avec le prefixe \"" + w2+"\"");
				break;
			case 10:
				Scanner word1 = new Scanner(System.in);
				System.out
						.println(" Tapez le mot que vous voulez supprimer : ");
				String w1 = word1.nextLine();
				if (trie.search(w1)) {
					((ITrieHybride) trie.delete(w1)).affiche("");
					System.out.println("Nombre total apres suppression"
							+ trie.countWords() + "\n\n");
					System.out.println("\n\n");
				}
				break;
				
			case 11: 
				File file = new File("data/plotTrieHybride");
				try {
					PrintStream printStream = new PrintStream(file);
					System.setOut(printStream);
					trie.plotTrie(); 
					printStream.close(); 
			        	} catch (FileNotFoundException e) {
			        		e.printStackTrace();
			    } 		

			case 0: 
				System.out.println("Au revoir !"); 
				break; 
				
			default:
				System.out.println("Vous n'avez rien choisi!");
			}
		}
	}
}
