package Interfaces;

import java.util.ArrayList;



public interface ITrieHybride extends IArbre{
	
	boolean isTrieHybride();

	char getKey();

	ITrieHybride getPere();

	ITrieHybride getFilsCentrale();

	ITrieHybride getFilsGauche();

	ITrieHybride getFilsDroit();
	
	void setPere(ITrieHybride x);

	void setFilsGauche(ITrieHybride arbre);

	void setFilsDroit(ITrieHybride arbre);

	void setFilsCentral(ITrieHybride arbre);

	void setIsMot();

	boolean IsMot();

	int getValeur();

	void setnotnode();

	int nombreNoeuds();

	int sommeProfondeur(int profondeur);

	ArrayList<String> ListeMot(String value);

	void affiche(String value);

	boolean Recherche(String substring);

	void plotTrie();
	
}
