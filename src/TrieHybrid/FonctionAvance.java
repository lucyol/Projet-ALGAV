package TrieHybrid;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import Interfaces.ITrieHybride;
import Tools.FileRead;

public class FonctionAvance {

	public static void main(String[] args) {

		String[] chaineInit = null;
		try {
			chaineInit = FileRead.readWord(new File ("test/Shakespeare/richardii.txt"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		ITrieHybride trie = new TrieHybride();
		for (String w : chaineInit) {
			trie = (ITrieHybride) trie.add(w);
		}

		while (0 == 0) {
			Scanner sc = new Scanner(System.in);
			System.out
					.println("\n\n------------------------------------\n"
							+ "Que souhaiter vous faire? : \n"
							+ "--> 1. L'arbre est t'il equilibré? \n"
							+ "--> 2. Afficher le TrieHybrid\n"
							+ "--> 3. Rechercher un mot dans le TrieHybrid\n"
							+ "--> 4. Afficher le nombre total de mot dans le TrieHybrid\n"
							+ "--> 5. Afficher la liste des mots en ordre alphabétique\n"
							+ "--> 6. Afficher le nombre de pointeurs vers Nil\n"
							+ "--> 7. Afficher l'hauteur du TrieHybrid\n"
							+ "--> 8. Afficher la profondeur moyenne\n"
							+ "--> 9. Choisir un mot et dire si il est préfixe ou non\n"
							+ "--> 10. Recherche un mot et le supprimer du TrieHybrid\n");

			int choix = sc.nextInt();
			switch (choix) {
			case 1:
				System.out.println("--> L'arbre est t'il equilibré? : "
						+ (TrieHybride.isEquilibre(trie) ? " OUI " : "NON"));
				break;
			case 2:
				System.out.println("--> Affichage du trie hybride: \n");
				System.out
						.println("************************************************ \n");
				trie.affiche("");
				System.out
						.println("************************************************ \n");
				break;
			case 3:
				Scanner word = new Scanner(System.in);
				System.out.println(" Taper le mot que vous chercher ! ");
				String w = word.nextLine();
				// boolean bb = trie.Recherche1(w);
				// System.out.println("Le mot" + " " + w + " "
				// + " a t'il été trouver ? :" + bb + "");
				break;
			case 4:
				System.out
						.println("--> Le nombre total de mots dans le trie hybride/ hybride equilibré est : "
								+ trie.countWords() + " / " + "");

				break;
			case 5:
				System.out.println("La liste des mots en ordre alphabétique :"
						+ trie.listWords());
				break;
			case 6:
				System.out.println(trie.countNull());
				break;
			case 7:
				System.out.println("--> Hauteur du trie Hybride: "
						+ trie.height());
				break;

			case 8:
				System.out.println(trie.profondeurMoyenne());
				break;
			case 9:
				Scanner word2 = new Scanner(System.in);
				System.out.println(" choisir le mot  ! ");
				String w2 = word2.nextLine();
				System.out.println("Il y'a " + trie.countPrefix(w2)
						+ " mots avec le préfixe " + w2);
				break;
			case 10:
				Scanner word1 = new Scanner(System.in);
				System.out
						.println(" Taper le mot que vous voulez supprimer ! ");
				String w1 = word1.nextLine();
				boolean bb1 = trie.search(w1);
				System.out.println("Le mot" + " " + w1 + " "
						+ " a t'il été trouver ? :" + bb1 + "");
				if (trie.search(w1)) {
					((ITrieHybride) trie.delete(w1)).affiche("");
					System.out.println("Nombre total apres suppression"
							+ trie.countWords() + "\n\n");
					System.out.println("\n\n");
				}
				break;

			default:
				System.out.println("Vous n'avez rien choisi! aurevoir");
			}
		}
	}
}
