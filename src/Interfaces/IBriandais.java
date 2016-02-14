package Interfaces;

import java.io.IOException;

public interface IBriandais extends IArbre{
	
	IBriandais getFils(); 
	IBriandais getNext(); 
	char getKey();
	void setFils(IBriandais fils);
	void setNext(IBriandais next);
	void plotBriandais();
}
