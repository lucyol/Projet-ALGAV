
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
	
	public boolean isEmpty(){
		if (this == null || (this.key=='\0' && this.fils==null && this.next==null)){
			return true; 			
		}else{
			return false; 
		}
	}
	

	
	
	
	

}
