package ArbreBriandais;

import java.util.ArrayList;


import Interfaces.IArbre;
import Interfaces.IBriandais;


public class Briandais implements IBriandais{
	
	private char key;
	private IBriandais next;
	private IBriandais fils;
	private IBriandais pere = null;
	public static final char charVide = 0;

	/*Primitives*/
	public Briandais() {
		next = null;
		fils = null;
		pere = null;
		key = charVide;
	}

	public Briandais(char key, IBriandais fils, IBriandais frere) {
		this.next = frere;
		this.fils = fils;
		if (fils != null)
			((Briandais) fils).setPere(this);
		this.key = key;
	}

	public Briandais(String mot) {
		if (mot.isEmpty()) {
			new Briandais(charVide, null, null);
		} else {
			key = mot.charAt(0);
			next = null;
			fils = new Briandais(mot.substring(1));
			((Briandais) fils).setPere(this);
		}
	}

	public char getKey(){
		return this.key; 
	}

	public IBriandais getFils(){
		return this.fils; 
	}

	public IBriandais getNext(){
		return this.next;
	}
	
	@Override
	public void setFils(IBriandais fils) {
		this.fils=fils; 
	}

	@Override
	public void setNext(IBriandais next) {
		this.next =next; 
	}

	private void setPere(IBriandais pere) {
		this.pere = pere;
	}


	public boolean isEmpty(){
		if (this.key=='@' && this.fils==null && this.next==null){
			return true; 			
		}else{
			return false; 
		}
	}

	
	public IBriandais add(String word) { 
		if (this.isEmpty() && pere == null) {
			return new Briandais(word);
		}
		if (word.isEmpty()) {
			if (key == charVide) {
				return this;
			} else {
				if(pere!=null)
				   return new Briandais(charVide, null, this);
				else 
					return this;
			}
		} else {
			if (word.charAt(0) < key) {
				return new Briandais(word.charAt(0), new Briandais(
						word.substring(1)), this);
			} else {
				if (key < word.charAt(0)) {
					if (next != null) {
						return new Briandais(key, fils,
								(IBriandais) ((Briandais) next).add(word));
					} else {
						return new Briandais(key, fils, new Briandais(word));
					}

				} else {
					if (fils != null) {
						return new Briandais(key,
								(IBriandais) ((Briandais) fils).add(word
										.substring(1)), next);
					} else {
						return new Briandais(key, new Briandais(
								word.substring(1)), next);
					}
				}
			}
		}
	}

	
	
	/*Fonctions avancees*/
	
	/*Fonction de recherche dans l'arbre*/
	public boolean search(String word){
		if(this.isEmpty()){
			return this.pere != null; 
		}
		if(word.isEmpty()){
			return this.key == charVide; 
		}
		if(head(word) == this.key){
			if(this.fils != null){
				return this.fils.search(tail(word)); 
			}else{
				return false; 
			}
		}
		if(head(word) > this.key){
			if(this.next != null){
				return this.next.search(word);				
			}else{
				return false; 
			}		
		}else{
			return false; 
		}
	}
	
	/*Retourne le nombre de mots dans l'arbre*/
	public int countWords() {
		if (key == charVide) {
			if (isEmpty() && pere == null)
				return 0;
			if (next != null)
				return 1 + next.countWords();
			else
				return 1;
		} else {

			if (next != null && fils != null)
				return fils.countWords() + next.countWords();
			else if (next == null && fils != null) {
				return fils.countWords();
			} else if (next != null && fils == null) {
				return next.countWords();
			}
			
			return 0;
		}
	}
	
	/*Retourne une liste des mots dans l'arbre dans l'ordre alphabetique */
	public ArrayList<String> listWords() {
		return this.tmpListWords("");
	}


	private ArrayList<String> tmpListWords(String value) {
		ArrayList<String> liste = new ArrayList<String>();
		if (key == charVide) {
			liste.add(value);
			if (next != null) {
				liste.addAll(((Briandais) next).tmpListWords(value));
				return liste;
			}
			return liste;
		} else {
			if (fils != null) {
				liste.addAll(((Briandais) fils).tmpListWords(value + key));
				if (next != null)
					liste.addAll(((Briandais) next).tmpListWords(value));
				return liste;
			}
		}
		return liste;
	}
	
	
	/*Retourne le nombre de pointeur null dans l'arbre*/
	public int countNull(){
		if(fils==null && next == null){
			return 2; 
		}
		if(fils==null){
			return 1 + this.next.countNull(); 
		}
		if(next == null){
			return 1 + this.fils.countNull(); 
		}
		return this.fils.countNull() + this.next.countNull(); 
	}
	
	/*Retourne la hauteur de l'arbre*/
	public int height(){
		if(fils == null && next == null){
			return 0; 
		}
		if(fils == null){
			return next.height(); 
		}
		if(next == null){
			return 1 + fils.height();
		}

		return 1 + ((fils.height() > next.height()) ? fils.height(): next.height());
		
	}
	
	
	/* Retourne le nombre de mots ayant prefix comme prefixe dans l'arbre*/
	public int countPrefix(String prefix){
		
		if(prefix.isEmpty()){
			return this.countWords();
		}		
		if(head(prefix) < this.key){
			return 0; 
		}
		
		if(head(prefix) > this.key){
			return this.next.countPrefix(prefix); 
		}else{
			return this.fils.countPrefix(tail(prefix)); 
		}
	}
	
	/* Suprime le mot word de l'arbre*/

	@Override
	public IArbre delete(String word) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	/*Methodes utiles*/
	private static char head(String word){
		return word.charAt(0); 
	}
	
	private static String tail(String word){
		return word.substring(1);
	}




 
}






