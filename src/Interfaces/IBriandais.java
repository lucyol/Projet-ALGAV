package Interfaces;

public interface IBriandais extends IArbre{
	
	IBriandais getFils(); 
	IBriandais getNext(); 
	char getKey();
	void setFils(IBriandais fils);
	void setNext(IBriandais next); 
}
