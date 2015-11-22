package Interfaces;
import java.util.ArrayList;


public interface IArbre {
	
	IArbre add(String word); 
	IArbre delete(String word); 
	boolean search(String word); 
	int countWords(); 
	ArrayList<String> listWords(); 
	boolean isEmpty(); 
	int countNull(); 
	int height(); 
	int countPrefix(String prefix); 

}
