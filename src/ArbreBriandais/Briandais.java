package ArbreBriandais;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

import java.util.ArrayList;

import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;

import Interfaces.IArbre;
import Interfaces.IBriandais;
import Interfaces.ITrieHybride;
import TrieHybrid.TrieHybride;


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
			if (head(word) < key) {
				return new Briandais(head(word), new Briandais(
						word.substring(1)), this);
			} else {
				if (key < head(word)) {
					if (next != null) {
						return new Briandais(key, fils,(IBriandais) ((Briandais) next).add(word));
					} else {
						return new Briandais(key, fils, new Briandais(word));
					}

				} else {
					if (fils != null) {
						return new Briandais(key,(IBriandais) ((Briandais) fils).add(tail(word)), next);
					} else {
						return new Briandais(key, new Briandais(tail(word)), next);
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

		return (((1+fils.height()) > next.height()) ? fils.height(): next.height());
		
	}
	
	/* Retourne la profondeur moyenne des feuilles de l'arbre*/
	public float averageDepth(){
		
		double s =(float) depthSum(0); 
		double c = (float) countLeaves(); 
		
		return (float) (s/c); 
	}
	
	private int depthSum(int depth){
		if(fils == null && next == null)
			return depth; 
		else if (fils == null && next != null)
			return ((Briandais) next).depthSum(depth); 
		else if (fils != null && next == null)
			return ((Briandais) fils).depthSum(++depth); 
		else
			return ((Briandais) next).depthSum(depth) + ((Briandais) fils).depthSum(depth+1); 
	}
	
	private int countLeaves(){
		if(fils == null && next == null)
			return 1; 
		else if(fils == null)
			return ((Briandais) next).countLeaves(); 
		else if(next == null)
			return ((Briandais) fils).countLeaves(); 
		else 
			return ((Briandais) fils).countLeaves()+((Briandais) next).countLeaves();
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

	public static IBriandais delete(IBriandais ab, String word) {
		
		if(ab==null){
			return null; 
		}
		
		if(ab.isEmpty() && word.isEmpty()){
			return ab.getNext(); 
		}
		
		if(ab.getKey() == head(word)){
			Briandais child = (Briandais) ab.getFils().delete(tail(word)); 
			if(child == null){
				return ab.getNext(); 
			}else{
				return new Briandais(ab.getKey(), child, ab.getNext()); 
			}
			
		}
		if(ab.getKey() > head(word)){
			return ab; 
		}
		if(ab.getKey() < head(word)){
			return new Briandais(ab.getKey(), ab.getFils(), (IBriandais) delete(ab.getNext(), word)); 
		}
		return null; 
	}
	

	public synchronized static IBriandais fusion(IBriandais ab1, IBriandais ab2){
		
		if(ab1 == null) { return ab2; }
		if(ab2 == null) { return ab1; }
		
		if(ab2.getKey() < ab1.getKey())
			return new Briandais(ab2.getKey(), ab2.getFils(), fusion(ab1, ab2.getNext())); 
		else if(ab2.getKey() > ab1.getKey())
			return new Briandais(ab1.getKey(), ab1.getFils(), fusion(ab2, ab1.getNext()));
		else
			return new Briandais(ab1.getKey(), fusion(ab1.getFils(), ab2.getFils()), fusion(ab1.getNext(), ab2.getNext())); 
			
		
	}
	
	public TrieHybride conversion(){
		if(isEmpty())
			return new TrieHybride();
		else{
			ArrayList<String> list = this.listWords();
			TrieHybride trie = new TrieHybride(); 
			for(String w : list){
				trie=(TrieHybride) trie.add(w); 
			}
			return trie; 
		}
	}
	
	/*Fonctions permettant une visualisation graphique grace a GNUPLOT*/
	public void plotBriandais(){	 
		tmpPlot(this, 0, 0, 0); 	
	}
	
	private static int tmpPlot(Briandais ab, long X ,long Y, int next){
		if(ab == null){
			return 0; 
		}
		
		int w;
		
		if(next ==1) {
			 if (ab.getKey() == charVide) 
			 	 System.out.println("//0 "+X+" "+Y+"\n");
		     else                   
		    	 System.out.println(ab.getKey()+" "+X+" "+Y+"\n"); 
		}
		
		 if (ab.getKey()== charVide) 
			 System.out.println(("//0 "+X+" "+Y)); 
		  else                   
			 System.out.println(ab.getKey()+" "+X+" "+Y); 
		 
		 
		 w =  tmpPlot((Briandais) ab.getFils(), X, Y + 10, 0);
		 w = w < 10?10:w;
		 
		 System.out.println(); 
		 
		 if (ab.getKey()== charVide) 
			 System.out.println(("//0 "+X+" "+Y)); 
		  else                   
		 	System.out.println(ab.getKey()+" "+X+" "+Y); 

		  return (w) + tmpPlot((Briandais) ab.getNext(), (X+w), Y, 1);
	}
	
	
	/*Methodes utiles*/
	private static char head(String word){
		return word.charAt(0); 
	}
	
	private static String tail(String word){
		return word.substring(1);
	}

	@Override
	public IArbre delete(String word) {
		// TODO Auto-generated method stub
		return null;
	}




 
}






