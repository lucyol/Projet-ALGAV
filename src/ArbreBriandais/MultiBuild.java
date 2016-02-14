package ArbreBriandais;

import Interfaces.IBriandais;

public class MultiBuild extends Thread {
	
	private static IBriandais abFinal = new Briandais();  
	private IBriandais ab = new Briandais(); 
	private String[] words; 
	
	public MultiBuild(String[] words){
		this.words = words;
	}

	public void run(){	
		for(String w : words){
			ab= (IBriandais) ab.add(w); 
		}
		abFinal = Briandais.fusion(ab, abFinal); 
	}
	
	public static IBriandais getFinal(){
		return abFinal; 
	}

}
