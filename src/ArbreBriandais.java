
public class ArbreBriandais {
	private char key; 
	private ArbreBriandais fils; 
	private ArbreBriandais next; 

	/*Primitives*/
	public ArbreBriandais(){
		this('@', null, null);		
	}

	public ArbreBriandais(char key, ArbreBriandais fils, ArbreBriandais next){
		this.key=key; 
		this.fils=fils; 
		this.next=next;
	}
/*On utilise une fonction statique pour cette primitive de construction 
 * parce que java n'autorise pas l'utilisation recursive des constructeurs*/
	public static ArbreBriandais arbreBriandais (String word){	
		if(word.equals("")){
			return new ArbreBriandais(); 
		}
		 return new ArbreBriandais(head(word), arbreBriandais(tail(word)),null); 
	}

	public char getKey(){
		return this.key; 
	}

	public ArbreBriandais getFils(){
		return this.fils; 
	}

	public ArbreBriandais getNext(){
		return this.next;
	}

	public boolean isEmpty(){
		if (this.key=='@' && this.fils==null && this.next==null){
			return true; 			
		}else{
			return false; 
		}
	}

	public ArbreBriandais addAB(String word){

		if(this.isEmpty()){
			if(word.equals("")){
				return this; 
			}
			return  arbreBriandais(word); 
		}

		if(word.equals("")){
			if(this.key == '@'){
				return this; 
			}
			return new ArbreBriandais('@', null, this); 
		}

		if(word.charAt(0)- this.key <0){

			return new ArbreBriandais(head(word), arbreBriandais(tail(word)), this); 

		}else if(head(word) - this.key > 0){
			return new ArbreBriandais(this.key, this.fils, this.next.addAB(word)); 
		}else{
			if(this.fils.isEmpty()){
				if(word.equals("")){
					return this; 
				}
				return new ArbreBriandais(this.key, new ArbreBriandais('@', null, arbreBriandais(tail(word))), this.next); 
			}
			return new ArbreBriandais(this.key, this.fils.addAB(tail(word)), this.next); 
		}
	}
	
	private static char head(String word){
		return word.charAt(0); 
	}
	
	private static String tail(String word){
		return word.substring(1); 
	}


}




