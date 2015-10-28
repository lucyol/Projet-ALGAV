
public class ArbreBriandais {
	private char key; 
	private ArbreBriandais fils; 
	private ArbreBriandais next; 
	
	/*Primitives*/
	public ArbreBriandais(){
		key='\0'; 
		fils=null; 
		next=null; 		
	}
	
	public ArbreBriandais(char key, ArbreBriandais fils, ArbreBriandais next){
		this.key=key; 
		this.fils=fils; 
		this.next=next;
	}
	
	public ArbreBriandais (String word){
		if(word.equals("\0")){
			 new ArbreBriandais(); 
		}else{
			new ArbreBriandais(word.charAt(0), new ArbreBriandais(word.substring(1)),null); 
		}
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
		if (this == null || (this.key=='\0' && this.fils==null && this.next==null)){
			return true; 			
		}else{
			return false; 
		}
	}
	
	public ArbreBriandais addAB(String word){
		
		if(this.isEmpty()){
			if(word.equals("\0")){
				return this; 
			}
			return new ArbreBriandais(word); 
		}
		
		if(word.equals("\0")){
			if(this.key == '\0'){
				return this; 
			}
			return new ArbreBriandais('\0', null, this); 
		}
		
		if(word.charAt(0)- this.key <0){

			return new ArbreBriandais(word.charAt(0), new ArbreBriandais(word.substring(1)), this); 

		}else if(word.charAt(0) - this.key > 0){
			return new ArbreBriandais(this.key, this.fils, this.next.addAB(word)); 
		}else{
			if(this.fils.isEmpty()){
				if(word.equals("\0")){
					return this; 
				}
				return new ArbreBriandais(this.key, new ArbreBriandais('\0', null, new ArbreBriandais(word.substring(1))), this.next); 
			}
			return new ArbreBriandais(this.key, this.fils.addAB(word.substring(1)), this.next); 
		}
	}


	}

	
	
	
	

}
